$(document).ready(function() {
	$('#logoutLink').on('click', function(event) {
		$.get('LogoutServlet', function(data) {
			console.log(data);

//			if (data.status == 'unauthenticated') {
//				window.location.replace('Login.html');
//				return;
//			}
		});
	
		event.preventDefault();
		return false;
	});
	
	var usernameFilterInput = $('#usernameFilterInput');
	var datumFilterInput = $('#datumFilterInput');
	var roleFilterInput = $('#roleFilterInput');
	
	var korisniciTable = $('#korisniciTable');
	var backLink = $('#backLink');
	var logoutLink = $('#logoutLink');

	function getKorisnici() {
		var usernameFilter = usernameFilterInput.val();
		var datumFilter = datumFilterInput.val();
		var roleFilter = roleFilterInput.val();
		console.log('usernameFilter: ' + usernameFilter);
		console.log('datumFilter: ' + datumFilter);
		console.log('roleFilter: ' + roleFilter);
		
		var params = {
				'usernameFilter': usernameFilter,
				'datumFilter': datumFilter,
				'roleFilter': roleFilter
		};
		
		$.get('KorisniciServlet', params, function(data) {
			console.log(data);
			
			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			
			if(data.status == 'success') {
				korisniciTable.find('tr:gt(1)').remove();
				
				var filteredKorisnici = data.filteredKorisnici;
				for(it in filteredKorisnici) {
					korisniciTable.append(
							'<tr>' +
							'<td><a href="Korisnik.html?id=' + filteredKorisnici[it].id + '">' + filteredKorisnici[it].username +  '</a></td>' +
							'<td>' + filteredKorisnici[it].datumRegistracije + '</td>' +
							'<td>' + filteredKorisnici[it].role + '</td>' +
						'</tr>'
					)
				}
			}
		});
	}
	
	usernameFilterInput.on('keyup', function(event) {
		getKorisnici();

		event.preventDefault();
		return false;
	});
	datumFilterInput.on('keyup', function(event) {
		getKorisnici();

		event.preventDefault();
		return false;
	});
	roleFilterInput.on('keyup', function(event) {
		getKorisnici();

		event.preventDefault();
		return false;
	});
	
	getKorisnici()
});