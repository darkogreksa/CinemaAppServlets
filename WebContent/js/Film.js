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
//				$('#naziv').text(film.naziv);
//				$('#reziser').text(film.reziser);
//				$('#glumci').text(film.glumci);
//				$('#zanrovi').text(film.zanrovi);
//				$('#trajanje').text(film.trajanje);
//				$('#distributer').text(film.distributer);
//				$('#zemljaPorekla').text(film.zemljaPorekla);
//				$('#godinaProizvodnje').text(film.godinaProizvodnje);
//				$('#opis').text(film.opis);
				
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
					var naziv = nameInput.val();
					var reziser = priceInput.val();
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
						'name': name, 
						'price': price
					};
					console.log(params);
					$.post('ProductServlet', params, function(data) {
						if (data.status == 'unauthenticated') {
							window.location.replace('Login.html');
							return;
						}

						if (data.status == 'success') {
							window.location.replace('WebShop.html');
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
	getFilm()
});