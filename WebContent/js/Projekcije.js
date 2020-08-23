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
	
	var filmFilterInput = $('#filmFilterInput');
	var tipProjekcijeFilterInput = $('#tipProjekcijeFilterInput');
	var salaFilterInput = $('#salaFilterInput');
	var vremePrikazivanjaOdFilterInput = $('#vremePrikazivanjaOdFilterInput');
	var cenaOdFilterInput = $('#cenaOdFilterInput');
	var cenaDoFilterInput = $('#cenaDoFilterInput');

	
	var projekcijeTable = $('#projekcijeTable');
	
	function getProjekcije() {
		var filmFilter = filmFilterInput.val();
		var tipProjekcijeFilter = tipProjekcijeFilterInput.val();
		var salaFilter = salaFilterInput.val();
//		var vremePrikazivanjaOdFilter = vremePrikazivanjaOdFilterInput.val();
		var cenaOdFilter = cenaOdFilterInput.val();
		var cenaDoFilter = cenaDoFilterInput.val();
		console.log('filmFilter: ' + filmFilter);
		console.log('tipProjekcijeFilter: ' + tipProjekcijeFilter);
		console.log('salaFilter' + salaFilter);
//		console.log('vremePrikazivanjaOdFilter: ' + vremePrikazivanjaOdFilter);
		console.log('cenaOdFilter: ' + cenaOdFilter);
		console.log('cenaDoFilter: ' + cenaDoFilter);
		
		var params = {
				'filmFilter': filmFilter,
				'tipProjekcijeFilter': tipProjekcijeFilter,
				'salaFilter': salaFilter,
//				'vremePrikazivanjaOdFilter': vremePrikazivanjaOdFilter,
				'cenaOdFilter': cenaOdFilter,
				'cenaDoFilter': cenaDoFilter
		};
		$.get('ProjekcijeServlet', params, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				projekcijeTable.find('tr:gt(1)').remove();
				
				var filteredProjekcije = data.filteredProjekcije;
				for (it in filteredProjekcije) {
					projekcijeTable.append(
							'<tr>' +
								'<td><a href="Projekcija.html?id=' + filteredProjekcije[it].id + '">' + filteredProjekcije[it].film.naziv +  '</a></td>' +
								'<td>' + filteredProjekcije[it].tipProjekcije.naziv + '</td>' +
								'<td>' + filteredProjekcije[it].sala.naziv + '</td>' +
								'<td>' + filteredProjekcije[it].vremePrikazivanja + '</td>' +
								'<td>' + filteredProjekcije[it].cenaKarte + '</td>' +
							'</tr>'
					)
				}
			}
		});
	}
	
	// na svaki od ovih događaja se osvežava lista proizvoda
	filmFilterInput.on('keyup', function(event) {
		getProjekcije();

		event.preventDefault();
		return false;
	});
	tipProjekcijeFilterInput.on('keyup', function(event) {
		getProjekcije();

		event.preventDefault();
		return false;
	});
	salaFilterInput.on('keyup', function(event) {
		getProjekcije();

		event.preventDefault();
		return false;
	});
	vremePrikazivanjaOdFilterInput.on('keyup', function(event) {
		getProjekcije();

		event.preventDefault();
		return false;
	});
	cenaOdFilterInput.on('keyup', function(event) {
		getProjekcije();

		event.preventDefault();
		return false;
	});
	cenaDoFilterInput.on('keyup', function(event) {
		getProjekcije();

		event.preventDefault();
		return false;
	});
	
	getProjekcije();
});
	