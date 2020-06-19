$(document).ready(function() {	
//	$('#logoutLink').on('click', function(event) {
//		$.get('LogoutServlet', function(data) {
//			console.log(data);
//
//			if (data.status == 'unauthenticated') {
//				window.location.replace('Login.html');
//				return;
//			}
//		});
//	
//		event.preventDefault();
//		return false;
//	});
	
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	console.log(id);
	
	
	function getFilm() {
		$.get('FilmServlet', {'id': id}, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				
				var film = data.film;
				$('#naziv').text(film.naziv);
				$('#reziser').text(film.reziser);
				$('#glumci').text(film.glumci);
				$('#zanrovi').text(film.zanrovi);
				$('#trajanje').text(film.trajanje);
				$('#distributer').text(film.distributer);
				$('#zemljaPorekla').text(film.zemljaPorekla);
				$('#godinaProizvodnje').text(film.godinaProizvodnje);
				$('#opis').text(film.opis);
			}
		});
	}
	getFilm()
});