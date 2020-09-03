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
	var trajanjeOdFilterInput = $('#trajanjeOdFilterInput');
	var trajanjeDoFilterInput = $('#trajanjeDoFilterInput');
	var zanrFilterInput = $('#zanrFilterInput');
	var godinaProizvodnjeOdFilterInput = $('#godinaProizvodnjeOdFilterInput');
	var godinaProizvodnjeDoFilterInput = $('#godinaProizvodnjeDoFilterInput');
	var distributerFilterInput = $('#distributerFilterInput');
	var zemljaPoreklaFilterInput = $('#zemljaPoreklaFilterInput');
	
	var filmoviTable = $('#filmoviTable');
	var loginLink = $('#loginLink');
	var dodajFilmLink = $('#dodajFilmLink');
	var korisniciLink = $('#korisniciLink');
	var karteLink = $('#karteLink');
	var backLink = $('#backLink');
	var adminParagraph = $('#adminParagraph');
	var projekcijeLink = $('#projekcijeLink');
	
	function getFilmovi() {
		var nazivFilter = nazivFilterInput.val();
		var trajanjeOdFilter = trajanjeOdFilterInput.val();
		var trajanjeDoFilter = trajanjeDoFilterInput.val();
		var zanrFilter = zanrFilterInput.val();
		var godinaProizvodnjeOdFilter = godinaProizvodnjeOdFilterInput.val();
		var godinaProizvodnjeDoFilter = godinaProizvodnjeDoFilterInput.val();
		var distributerFilter = distributerFilterInput.val();
		var zemljaPoreklaFilter = zemljaPoreklaFilterInput.val();
		console.log('nazivFilter: ' + nazivFilter);
		console.log('trajanjeOdFilter: ' + trajanjeOdFilter);
		console.log('trajanjeDoFilter' + trajanjeDoFilter);
		console.log('zanrFilter: ' + zanrFilter);
		console.log('godinaProizvodnjeOdFilter: ' + godinaProizvodnjeOdFilter);
		console.log('godinaProizvodnjeDoFilter: ' + godinaProizvodnjeDoFilter);
		console.log('distributerFilter: ' + distributerFilter);
		console.log('zemljaPoreklaFilter: ' + zemljaPoreklaFilter);
		
		var params = {
				'nazivFilter': nazivFilter,
				'trajanjeOdFilter': trajanjeOdFilter,
				'trajanjeDoFilter': trajanjeDoFilter,
				'zanrFilter': zanrFilter,
				'godinaProizvodnjeOdFilter': godinaProizvodnjeOdFilter,
				'godinaProizvodnjeDoFilter': godinaProizvodnjeDoFilter,
				'distributerFilter': distributerFilter,
				'zemljaPoreklaFilter': zemljaPoreklaFilter
		};
		$.get('FilmsServlet', params, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				filmoviTable.find('tr:gt(1)').remove();
				
				var filteredFilms = data.filteredFilms;
				for (it in filteredFilms) {
					filmoviTable.append(
							'<tr>' +
								'<td><a href="Film.html?id=' + filteredFilms[it].id + '">' + filteredFilms[it].naziv +  '</a></td>' +
								'<td>' + filteredFilms[it].trajanje + '</td>' +
								'<td>' + filteredFilms[it].zanrovi + '</td>' +
								'<td>' + filteredFilms[it].godinaProizvodnje + '</td>' +
								'<td>' + filteredFilms[it].distributer + '</td>' +
								'<td>' + filteredFilms[it].zemljaPorekla + '</td>' +
							'</tr>'
					)
				}
			}
		});
	}
	
	function getAdminInterface() {
		$.get('UserServlet', {'action': 'loggedInUserRole'}, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}

			adminParagraph.empty();
			if (data.status == 'success') {
				adminParagraph.empty();
				if (data.loggedInUserRole == 'ADMIN') {
					$('#adminParagraph').append('<a href="DodajFilm.html" id="dodajFilmLink">Dodavanje filma</a>' +
					 		'<a href="Korisnici.html" id="korisniciLink">Upravljanje korisnicima</a>');
				}
			}
		});
	}
	
	// na svaki od ovih događaja se osvežava lista proizvoda
	nazivFilterInput.on('keyup', function(event) {
		getFilmovi();

		event.preventDefault();
		return false;
	});
	trajanjeOdFilterInput.on('keyup', function(event) {
		getFilmovi();

		event.preventDefault();
		return false;
	});
	trajanjeDoFilterInput.on('keyup', function(event) {
		getFilmovi();

		event.preventDefault();
		return false;
	});
	zanrFilterInput.on('keyup', function(event) {
		getFilmovi();

		event.preventDefault();
		return false;
	});
	godinaProizvodnjeOdFilterInput.on('keyup', function(event) {
		getFilmovi();

		event.preventDefault();
		return false;
	});
	godinaProizvodnjeDoFilterInput.on('keyup', function(event) {
		getFilmovi();

		event.preventDefault();
		return false;
	});
	distributerFilterInput.on('keyup', function(event) {
		getFilmovi();

		event.preventDefault();
		return false;
	});
	zemljaPoreklaFilterInput.on('keyup', function(event) {
		getFilmovi();

		event.preventDefault();
		return false;
	});
	
	getFilmovi();
	getAdminInterface();
});
	