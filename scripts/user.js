$(function() {
	applyUserInfo()
	$("#editButton").click(editForm);
	$("#saveButton").hide();
	$("#saveButton").click(saveForm);
});

function editForm() {
	$("#editButton").hide();
	$("#saveButton").show();
	
	var fname = $("#fname").text();
	var lname = $("#lname").text();
	var address = $("#address").text();
	var email = $("#email").text();
	var password = $("#Mypassword").text();
	
	$("#fname").html("<input type=\"text\" id=\"fnameInput\" value=\"" + fname + "\">");
	$("#lname").html("<input type=\"text\" id=\"lnameInput\" value=\"" + lname + "\">");
	$("#address").html("<input type=\"text\" id=\"addressInput\" value=\"" + address + "\">");
	$("#email").html("<input type=\"text\" id=\"emailInput\" value=\"" + email + "\">");
	$("#Mypassword").html("<input type=\"text\" id=\"passwordInput\" value=\"" + password + "\">");
}

function saveForm() {
	var fname = $("#fnameInput").val();
	var lname = $("#lnameInput").val();
	var address = $("#addressInput").val();
	var email = $("#emailInput").val();
	var password = $("#passwordInput").val();
	
	var p = getCookie("user").split(",");
	
	if(password === "**********") {
		password = p[2];
	}
	
	$("#fname").html("<p>" + fname + "</p>");
	$("#lname").html("<p>" + lname + "</p>");
	$("#address").html("<p>" + address + "</p>");
	$("#email").html("<p>" + email + "</p>");
	$("#Mypassword").html("<p>**********</p>");
	
	$.ajax({url: "ArtStoreAjax", 
			data: {"fname": fname,
					"lname": lname,
					"address": address,
					"email": email,
					"Mypassword": password},
			method: "POST"
	});

	$("#editButton").show();
	$("#saveButton").hide();
}

function applyUserInfo(){
	var userstring = getCookie("user").split(",");
	
	var fname = userstring[0];
	var lname = userstring[1];
	var password = userstring[2];
	var email = userstring[3];
	var address = userstring[4];
	
	$("#fname").html(fname);
	$("#lname").html(lname);
	$("#address").html(address);
	$("#email").html(email);
	$("#Mypassword").html("**********");
}