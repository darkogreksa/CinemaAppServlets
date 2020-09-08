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
	
	var adminForm = $('#adminForm');
	
	function getFilm() {
		$.get('FilmServlet', {'id': id}, function(data) {
			console.log(data);
			
			if(data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			if(data.status == 'success') {
				
				var film = data.film;
				
				var nazivInput = $('#nazivInput');
				var reziserInput = $('#reziserInput');
				var glumciInput = $('#glumciInput');
				var zanroviInput = $('#zanroviInput');
				var trajanjeInput = $('#trajanjeInput');
				var distributerInput = $('#distributerInput');
				var zemljaPoreklaInput = $('#zemljaPoreklaInput');
				var godinaProizvodnjeInput = $('#godinaProizvodnjeInput');
				var opisInput = $('#opisInput');
				nazivInput.val(film.naziv);
				reziserInput.val(film.reziser);
				glumciInput.val(film.glumci);
				zanroviInput.val(film.zanrovi);
				trajanjeInput.val(film.trajanje);
				distributerInput.val(film.distributer);
				zemljaPoreklaInput.val(film.zemljaPorekla);
				godinaProizvodnjeInput.val(film.godinaProizvodnje);
				opisInput.val(film.opis);

				
				$('#updateSubmit').on('click', function(event) {
					var naziv = nazivInput.val();
					var reziser = reziserInput.val();
					var glumci = glumciInput.val();
					var zanrovi = zanroviInput.val();
					var trajanje = trajanjeInput.val();
					var distributer = distributerInput.val();
					var zemljaPorekla = zemljaPoreklaInput.val();
					var godinaProizvodnje = godinaProizvodnjeInput.val();
					var opis = opisInput.val();
					console.log('naziv: ' + naziv);
					console.log('reziser: ' + reziser);
					console.log('glumci: ' + glumci);
					console.log('zanrovi: ' + zanrovi);
					console.log('trajanje: ' + trajanje);
					console.log('distributer: ' + distributer);
					console.log('zemljaPorekla: ' + zemljaPorekla);
					console.log('godinaProizvodnje: ' + godinaProizvodnje);
					console.log('opis: ' + opis);

					params = {
						'action': 'update',
						'id': id, 
						'naziv': naziv, 
						'reziser': reziser,
						'glumci': glumci,
						'zanrovi': zanrovi,
						'trajanje': trajanje,
						'distributer': distributer,
						'zemljaPorekla': zemljaPorekla,
						'godinaProizvodnje': godinaProizvodnje,
						'opis': opis
					};
					console.log(params);
					$.post('FilmServlet', params, function(data) {
						if (data.status == 'unauthenticated') {
							window.location.replace('Login.html');
							return;
						}

						if (data.status == 'success') {
							window.location.replace('Filmovi.html');
							return;
						}
					});

					event.preventDefault();
					return false;
				});
				$('#deleteSubmit').on('click', function(event) {
					params = {
						'action': 'delete',
						'id': id, 
					};
					console.log(params);
					$.post('FilmServlet', params, function(data) {
						if (data.status == 'unauthenticated') {
							window.location.replace('Login.html');
							return;
						}

						if (data.status == 'success') {
							window.location.replace('Filmovi.html');
							return;
						}
					});

					event.preventDefault();
					return false;
				});
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
			if (data.status == 'success') {
				if (data.loggedInUserRole != 'ADMIN') {
					$('#adminForm').hide();
				}
			}
		});
	}
	getFilm()
	getAdminInterface()
});