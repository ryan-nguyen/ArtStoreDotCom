$(function() {
	$("#checkoutButton").click(checkout);
	setTotals();
	getUser();
});

function setTotals() {
	var price = parseFloat(getCookie("price"));
	var shipping = 5.00;
	var tax = price * 0.07;
	var total = price + shipping + tax;
	
	$("#subtotal").html("$" + price.toFixed(2));
	$("#tax").html("$" + tax.toFixed(2));
	$("#total").html("$" + total.toFixed(2));
}

function getUser() {
	var user = getCookie("user");
	var s = user.split(",");
	
	$("#fname").html(s[0]);
	$("#lname").html(s[1]);
	$("#address").html(s[4]);
}

function checkout() {
	$(".errorMessage").remove();

	var userstring = getCookie("user").split(",");
	var userEmail = userstring[3];
//	alert("Purchase successful.");
	var error = errorDisplay();

	if(error=="false"){
		$.ajax({url: "ArtStoreAjax", data: {"checkout": userEmail}, method: "POST"});
		$("#checkoutButton").attr("href", "history.html");
	}
}

function errorDisplay(){
	var errorBool = "false";
	if($.trim($("#cardNumber").val()) == "") {
		$("#cardNumberDiv").append("<p class=\"errorMessage\">field cannot be left blank<p>");
		$("#checkoutButton").attr("href", null);
		errorBool = "true";
	}
	if(($("#cardNumber").val().length != 16) || (isNaN($("#cardNumber").val()))){ 
		$("#cardNumberDiv").append("<p class=\"errorMessage\">must be a 16 digit number<p>")
		$("#checkoutButton").attr("href", null);
		errorBool = "true";
	}
	if($.trim($("#cardName").val()) == ""){
		$("#cardNameDiv").append("<p class=\"errorMessage\">field cannot be left blank<p>");
		$("#checkoutButton").attr("href", null);
		errorBool = "true";
	}
	if($.trim($("#securityCode").val()) == ""){
		$("#securityCodeDiv").append("<p class=\"errorMessage\">field cannot be left blank<p>");
		$("#checkoutButton").attr("href", null);
		errorBool = "true";
	}

	if($("#securityCode").val().length != 3|| (isNaN($("#securityCode").val()))){ 
		$("#securityCodeDiv").append("<p class=\"errorMessage\">must be a 3 digit number<p>")
		$("#checkoutButton").attr("href", null);
		errorBool = "true";
	}
	
	if($.trim($("#expiration").val()) == ""){
		$("#expirationDiv").append("<p class=\"errorMessage\">field cannot be left blank<p>");
		$("#checkoutButton").attr("href", null);
		errorBool = "true";
	}
	
	var expiration = $("#expiration").val();
	if((expiration.length != 7) || (isNaN(expiration.substring(0,2))) ||
			(isNaN(expiration.substring(3,7))) || (expiration.substring(2,3)!=("/")) ||
			(parseInt(expiration.substring(0,2))>12)){
		$("#expirationDiv").append("<p class=\"errorMessage\">(MM/YYYY)<p>");
		$("#checkoutButton").attr("href", null);
		errorBool = "true";
	}
	return errorBool;
}
