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
	
	var projekcijaTable = $('#projekcijaTable');
	
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
				
				$('#film').text(projekcija.film.naziv);
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
					$.post('ProjekcijaServlet', function(data) {
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
	getProjekcija()
});