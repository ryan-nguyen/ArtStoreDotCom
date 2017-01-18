$(function() {
	  checkIfLoggedIn();
	  $("#logoutButton").click(logout);
});

function checkIfLoggedIn() {
	var loggedIn = getCookie("log");
	
	if(loggedIn === "true") {
		$("#loginButton").hide();
		$("#logoutButton").show();
		$("#userButton").show();
		$("#cartButton").show();
	} else {
		$("#logoutButton").hide();
		$("#loginButton").show();
		$("#userButton").hide();
		$("#cartButton").hide();
	}
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
} 

function createCookie(name, value, days) {
    var expires;

    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toGMTString();
    } else {
        expires = "";
    }
    document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
}

function eraseCookie(name) {
    createCookie(name, "", -1);
}

function logout() {
	eraseCookie("user");
	eraseCookie("log");
	createCookie("log", "false", 1);
	checkIfLoggedIn();
}
