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
	
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	console.log(id);

//	var kartaTable = $('#kartaTable');
	
	function getKarta() {
		$.get('KartaServlet', {'id': id}, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				var karta = data.karta;
				
				$('#nazivFilma').text(karta.projekcija.film.naziv);
				$('#sediste').text(karta.sediste.redniBroj);
				$('#sala').text(karta.projekcija.sala.naziv);
				$('#vremePrikazivanja').text(karta.projekcija.vremePrikazivanja);
				$('#korisnik').text(karta.korisnik.username);
				$('#tipProjekcije').text(karta.projekcija.tipProjekcije.naziv);
			}
		});
	}
	getKarta();
});
	