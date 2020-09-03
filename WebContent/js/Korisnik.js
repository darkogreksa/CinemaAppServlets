$(document).ready(function() {	
	$('#logoutLink').on('click', function(event) {
		$.get('LogoutServlet', function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
		});
	
		event.preventDefault();
		return false;
	});
	
	var username = window.location.search.slice(1).split('&')[0].split('=')[1];
	console.log(username);
	
	var adminForm = $('#adminForm');
	
	function getKorisnik() {
		$.get('KorisnikServlet', {'username': username}, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				var korisnik = data.korisnik;
				
				var usernameInput = $('#usernameInput');
				var datumRegistracijeInput = $('#datumRegistracijeInput');
				var roleInput = $('#roleInput');
				usernameInput.val(korisnik.username);
				datumRegistracijeInput.val(korisnik.datumRegistracije);
				roleInput.val(korisnik.role);
				
				$('#updateSubmit').on('click', function(event) {
					var username = usernameInput.val();
					console.log('username: ' + username);

					params = {
						'action': 'update',
						'username': username
					};
					console.log(params);
					$.post('KorisnikServlet', params, function(data) {
						if (data.status == 'unauthenticated') {
							window.location.replace('Login.html');
							return;
						}

						if (data.status == 'success') {
							window.location.replace('Korisnici.html');
							return;
						}
					});

					event.preventDefault();
					return false;
				});
				$('#deleteSubmit').on('click', function(event) {
					params = {
						'action': 'delete',
						'username': username
					};
					console.log(params);
					$.post('KorisnikServlet', params, function(data) {
						if (data.status == 'unauthenticated') {
							window.location.replace('Login.html');
							return;
						}

						if (data.status == 'success') {
							window.location.replace('Korisnici.html');
							return;
						}
					});

					event.preventDefault();
					return false;
				});
			}
		});
	}
	
	function getAdminInterface() {
		$.get('UserServlet', {'action': 'loggedInUserRole'}, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if (data.status == 'success') {
				if (data.loggedInUserRole != 'ADMIN') {
					$('#adminForm').hide();
				}
			}
		});
	}
	getKorisnik()
	getAdminInterface()
});