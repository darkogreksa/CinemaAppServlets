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
	
	var nazivFilterInput = $('#nazivFilterInput');
	var sedisteFilterInput = $('#sedisteFilterInput');
	var vremePrikazivanjaOdFilterInput = $('#vremePrikazivanjaOdFilterInput');
	var korisnikFilterInput = $('#korisnikFilterInput');
	var salaFilterInput = $('#salaFilterInput');
	var tipProjekcijeFilterInput = $('#tipProjekcijeFilterInput');

	var karteTable = $('#karteTable');
	
	function getKarte() {
		
		var nazivFilter = nazivFilterInput.val();
		var sedisteFilter = sedisteFilterInput.val();
//		var vremePrikazivanjaOdFilter = vremePrikazivanjaOdFilterInput.val();
		var korisnikFilter = korisnikFilterInput.val();
		var salaFilter = salaFilterInput.val();
		var tipProjekcijeFilter = tipProjekcijeFilterInput.val();
		console.log('nazivFilter: ' + nazivFilter);
		console.log('sedisteFilter: ' + sedisteFilter);
//		console.log('vremePrikazivanjaOdFilter: ' + vremePrikazivanjaOdFilter);
		console.log('korisnikFilter: ' + korisnikFilter);
		console.log('salaFilter' + salaFilter);
		console.log('tipProjekcijeFilter: ' + tipProjekcijeFilter);
		
		var params = {
				'nazivFilter': nazivFilter,
				'sedisteFilter': sedisteFilter,
//				'vremePrikazivanjaOdFilter': vremePrikazivanjaOdFilter,
				'korisnikFilter': korisnikFilter,
				'salaFilter': salaFilter,
				'tipProjekcijeFilter': tipProjekcijeFilter
		}
		
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
								'<td>' + filteredKarte[it].projekcija.film.naziv + '</td>' +
								'<td><a href="Karta.html?id=' + filteredKarte[it].id + '">' + filteredKarte[it].sediste.redniBroj +  '</a></td>' +
								'<td><a href="Projekcija.html?id=' + filteredKarte[it].id + '">' + filteredKarte[it].projekcija.vremePrikazivanja +  '</a></td>' +
								'<td><a href="Korisnik.html?username=' + filteredKarte[it].korisnik.username + '">' + filteredKarte[it].korisnik.username +  '</a></td>' +
								'<td>' + filteredKarte[it].projekcija.sala.naziv + '</td>' +
								'<td>' + filteredKarte[it].projekcija.tipProjekcije.naziv + '</td>' +
							'</tr>'
					)
				}
			}
		});
	}
	

	nazivFilterInput.on('keyup', function(event) {
		getKarte();

		event.preventDefault();
		return false;
	});
	sedisteFilterInput.on('keyup', function(event) {
		getKarte();

		event.preventDefault();
		return false;
	});
//	vremePrikazivanjaOdFilterInput.on('keyup', function(event) {
//		getProjekcije();
//
//		event.preventDefault();
//		return false;
//	});
	korisnikFilterInput.on('keyup', function(event) {
		getKarte();

		event.preventDefault();
		return false;
	});
	salaFilterInput.on('keyup', function(event) {
		getKarte();

		event.preventDefault();
		return false;
	});
	tipProjekcijeFilterInput.on('keyup', function(event) {
		getKarte();

		event.preventDefault();
		return false;
	});
	
	getKarte();
});
	