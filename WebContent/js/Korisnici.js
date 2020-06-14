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
	
	var korisniciTable = $('#korisniciTable');
	var backLink = $('#backLink');

	$.get('KorisniciServlet', function(data) {
		console.log(data);
		
		if (data.status == 'unauthenticated') {
			window.location.replace('Login.html');
			return;
		}
		
		if(data.status == 'success') {
			korisniciTable.find('tr:gt(1)').remove();
		}
	});
});