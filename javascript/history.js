$(function() {
	var userstring = getCookie("user").split(",");
	var userEmail = userstring[3];
	$.ajax({url: "ArtStoreAjax", data: {"getHistory": userEmail}, method: "POST", success: displayHistory});
});

function displayHistory(products) {
	$("#historyProducts").html("");
	
	var productArray = products.split("=====");
	
	if(productArray.length == 1) {
		$("#historyProducts").html("You have not purchased any items");
	}
	
	for(var i = 1; i < productArray.length; i++) {
		var item = productArray[i].split(",");
		
		var img = item[0];
		var name = item[1];
		var description = item[3];
		var price = item[4];
		var date = item[5];
		
		date = modifyDate(date);
		$("#historyProducts").prepend(
				"<div class=\"well well-lg\">\n" + 
				"<div class=\"media\">\n" + 
				"<a class=\"media-left\" href=\"product.html#" + name + "\">\n" + 
				"<img class=\"media-object\" src=\"" + img + "\" width=\"150\" height=\"150\">\n" + 
				"</a>\n" + 
				"<div class=\"media-body\">\n" + 
				"<h6 class=\"media-heading\" id=\"productDate\">" + date + "</h6>\n" + 
				"<h4 class=\"media-heading\" id=\"productName\">" + name + "</h4>\n" + 
				"<h5 class=\"media-heading\" id=\"productPrice\">" + price + "</h5><br>\n" + 				
				"<p id=\"prductDescription\">" + description + "</p>\n" + 
				"</div>\n" + 
				"</div>\n" + 
				"</div>\n"
		);
	}
}

function modifyDate(date){
	var yearstring = date.substring(0,4);
	var monthstring = date.substring(5,7);
	var daystring = date.substring(8,10);
	var hourstring = date.substring(11,13);
	var hourint = parseInt(hourstring);
	var minutestring = date.substring(14,16);
	
	if(hourint>=12) {
		hourint = hourint - 12;
		hourstring = hourint;
		minutestring = minutestring + "pm";
	} else if(hourint==0){
		hourint = hourint + 12;
		hourstring = hourint;
		minutestring = minutestring + "am";
	} else {
		minutestring = minutestring + "am";
	}
	
	switch(monthstring) {
		case "01":
			monthstring = "January";
		case "02":
			monthstring = "February";
		case "03":
			monthstring = "March";
		case "04":
			monthstring = "April";
		case "05":
			monthstring = "May";
		case "06":
			monthstring = "June";
		case "07":
			monthstring = "July";
		case "08":
			monthstring = "August";
		case "09":
			monthstring = "September";
		case "10":
			monthstring = "October";
		case "11":
			monthstring = "November";
		case "12":
			monthstring = "December";
	}
	
	date = monthstring + " " + daystring + ", " + yearstring + " " + hourstring + ":" + minutestring;
	return date;
}
