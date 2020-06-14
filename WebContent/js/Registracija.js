$(document).ready(function() {
	var usernameInput = $('#usernameInput');
	var passwordInput = $('#passwordInput');
	var repeatedPasswordInput = $('#repeatedPasswordInput');

	var messageParagraph = $('#messageParagraph');

	$('#registrationSubmit').on('click', function(event) {
		var username = usernameInput.val();
		var password = passwordInput.val();
		var repeatedPassword = repeatedPasswordInput.val();
		console.log('username: ' + username);
		console.log('password: ' + password);
		console.log('repeated: ' + repeatedPassword);

		if (password != repeatedPassword) {
			messageParagraph.text('Lozinke se ne podudaraju!');

			event.preventDefault();
			return false;
		}
		
		var params = {
			'username': username, 
			'password': password
		}
		$.post('RegistracijaServlet', params, function(data) {
			console.log(data);

			if (data.status == 'failure') {
				messageParagraph.text(data.message);
				return;
			}
			if (data.status == 'success') {
				window.location.replace('Filmovi.html');
			}
		});

		event.preventDefault();
		return false;
	});
});