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

	var salaInput = $('#salaInput');
	var tipProjekcijeInput = $('#tipProjekcijeInput');
	var dateInput = $('#dateInput');
	var timeInput = $('#timeInput');
	var cenaInput = $('#cenaInput');
	var filmInput = $('#filmInput');

	$('#projekcijaSubmit').on('click', function(event) {
		var sala = salaInput.val();
		var tipProjekcije = tipProjekcijeInput.val();
		var date = dateInput.val();
		var time = timeInput.val();
		var cena = cenaInput.val();
		var film = filmInput.val();
		console.log('sala: ' + sala);
		console.log('tipProjekcije: ' + tipProjekcije);
		console.log('date: ' + date);
		console.log('time: ' + time);
		console.log('cena: ' + cena);
		console.log('film: ' + film);

		params = {
			'action': 'add', 
			'sala': sala, 
			'tipProjekcije': tipProjekcije,
			'date': date,
			'time': time,
			'cena': cena,
			'film': film
		};
		$.post('ProjekcijaServlet', params, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('Projekcije.html');
			}
		});
		
		event.preventDefault();
		return false;
	});
});