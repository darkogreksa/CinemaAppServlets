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
		$.get('UserServlet', {'action': 'loggedInUser'}, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				var korisnik = data.korisnik;
				
				$('#username').text(loggedInUser.username);
				$('#datumRegistracije').text(loggedInUser.datumRegistracije);
				$('#role').text(loggedInUser.role);
			}
		});
	}
	getKorisnik()
});