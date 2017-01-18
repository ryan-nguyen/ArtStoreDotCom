$(function() {
	$('#login-form-link').click(logIn);
	$('#register-form-link').click(register);
});

function logIn() {
	$("#login-form").delay(100).fadeIn(100);
	$("#register-form").fadeOut(100);
	$('#register-form-link').removeClass('active');
	$(this).addClass('active');
}

function register() {
	$("#register-form").delay(100).fadeIn(100);
	$("#login-form").fadeOut(100);
	$('#login-form-link').removeClass('active');
	$(this).addClass('active');
}

function validateForm() {
	var fName = $("#firstname").val();
	var lName = $("#lastname").val();
	var email = $("#email").val();
	var password = $("#Mypassword").val();
	var confirmPW = $("#confirm-password").val();
	var address = $("#address1").val();
	
	$.trim(fName);
	$.trim(lName);
	$.trim(email);
	$.trim(password);
	$.trim(confirmPW);
	$.trim(address);
	
	if(fName === "" || lName === "" ||
			email === "" || password === "" || 
			confirmPW === "" || address === "") {
		alert("All Fields Must Be Filled Out");
		return false;
	}
	
	if(password != confirmPW) {
		alert("Passwords Do Not Match");
		return false;	
	}
	
	return true;	
}

