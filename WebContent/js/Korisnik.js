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
	
	
	function getKorisnik() {
		$.get('KorisnikServlet', {'username': username}, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				var usernameInput = $('#usernameInput');
				usernameInput.val(korisnik.username);
				
				var korisnik = data.korisnik;
//				$('#username').text(korisnik.username);
				$('#datumRegistracije').text(korisnik.datumRegistracije);
				$('#role').text(korisnik.role);
				
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
						'username': username, 
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
	getKorisnik()
});