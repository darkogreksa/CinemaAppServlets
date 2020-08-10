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
				
				var korisnik = data.korisnik;
				$('#username').text(korisnik.username);
				$('#datumRegistracije').text(korisnik.datumRegistracije);
				$('#role').text(korisnik.role);
			}
		});
	}
	getKorisnik()
});