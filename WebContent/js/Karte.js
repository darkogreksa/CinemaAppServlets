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
	
	var karteTable = $('#filmoviTable');
	
	function getFilmovi() {
		
		$.get('KarteServlet', params, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				karteTable.find('tr:gt(1)').remove();
				
				var filteredKarte= data.filteredKarte;
				for (it in filteredKarte) {
					karteTable.append(
							'<tr>' +
								'<td>' + filteredKarte[it].film.naziv + '</td>' +
								'<td><a href="Karta.html?id=' + filteredKarte[it].id + '">' + filteredKarte[it].sediste +  '</a></td>' +
								'<td><a href="Projekcija.html?id=' + filteredKarte[it].id + '">' + filteredKarte[it].vremeProdaje +  '</a></td>' +
								'<td><a href="Korisnik.html?id=' + filteredKarte[it].id + '">' + filteredKarte[it].korisnik.username +  '</a></td>' +
								'<td>' + filteredKarte[it].sala.naziv + '</td>' +
								'<td>' + filteredKarte[it].tipProjekcije.naziv + '</td>' +
							'</tr>'
					)
				}
			}
		});
	}
	
	getKarte();
});
	