$(document).ready(function() { // izvršava se nakon što se izgradi DOM stablo HTML dokumenta
	// keširanje referenci da se ne bi ponavljale pretrage kroz DOM stablo
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');

	$('#loginSubmit').on('click', function(event) { // izvršava se na klik na dugme
		var username = usernameInput.val();
		var password = passwordInput.val();
		console.log('username: ' + username);
		console.log('password: ' + password);		

		var params = {
			'username': username, 
			'password': password
		}
		// kontrola toka se račva na 2 grane
		$.post('LoginServlet', params, function(data) { // u posebnoj programskoj niti se šalje (asinhroni) zahtev
			// tek kada stigne odgovor izvršiće se ova anonimna funkcija
			console.log('stigao odgovor!')
			console.log(data);

			if (data.status == 'failure') {
				usernameInput.val('');
				passwordInput.val('');

				return;
			}
			if (data.status == 'success') {
				window.location.replace('Filmovi.html');
			}
		});
		// program se odmah nastavlja dalje, pre nego što stigne odgovor
		console.log('poslat zahtev!')

		// zabraniti da browser obavi podrazumevanu akciju pri događaju
		event.preventDefault();
		return false;
	});
});