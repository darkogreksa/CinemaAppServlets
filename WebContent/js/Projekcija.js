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
	
//	var projekcijaTable = $('#projekcijaTable');
	
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	console.log(id);
	
	
	function getProjekcija() {
		$.get('ProjekcijaServlet', {'id': id}, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				
				var projekcija = data.projekcija;
				
				$('#nazivFilma').text(projekcija.film.naziv);
				$('#tipProjekcije').text(projekcija.tipProjekcije.naziv);
				$('#sala').text(projekcija.sala.naziv);
				$('#vremePrikazivanja').text(projekcija.vremePrikazivanja);
				$('#cenaKarte').text(projekcija.cenaKarte);
				
				$('#deleteSubmit').on('click', function(event) {
					params = {
						'action': 'delete',
						'id': id, 
					};
					console.log(params);
					$.post('ProjekcijaServlet', params, function(data) {
						if (data.status == 'unauthenticated') {
							window.location.replace('Login.html');
							return;
						}

						if (data.status == 'success') {
							window.location.replace('Karte.html');
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
	
	getProjekcija()
	getAdminInterface()
});