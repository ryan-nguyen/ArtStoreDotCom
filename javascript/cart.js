var subtotal = 0.0;

$(function() {
	var userstring = getCookie("user").split(",");
	var userEmail = userstring[3];
	$.ajax({url: "ArtStoreAjax", data: {"findId": userEmail}, method: "POST", success: displayCart});
	$("#checkoutButton").click(setPrice);
});

function displayCart(productString){
	$("#cartcontents").html("");
	
	var s = productString.split("=====");
	if(s.length<=1){
		$("#cartcontents").append("<p>Your cart is empty.<p>");
		$("#checkoutButton").hide();
	}else {
		for(var i = 1; i < s.length; i++) {
			var product = s[i].split(",");					
			var img = product[0];
			var name = product[1];
			var description = product[3];
			var price = product[4];
					
			var decimalprice = parseFloat(price);
			subtotal = decimalprice + subtotal;
					
			$("#cartcontents").prepend(
					"<div class=\"well\" id=\"cartItem" + i + "\">\n" + 
					"        <div class=\"media\">\n" + 
					"            <div class=\"media-left media-top\">\n" + 
					"                <a href=\"product.html#" + name + "\">\n" + 
					"                <img class=\"media-object\" src=\"" + img + "\" width=\"150\" height=\"150\">\n" + 
					"            </a>\n" + 
					"            </div>\n" + 
					"            <div class=\"media-body\" id=\"prodinfo\">\n" + 
					"                <h4 class=\"media-heading\" id=\"prodname" + i + "\">" + name + "</h4><br>\n" + 
					"                <p>" + description + "</p>\n" + 
					"            </div>\n" + 
					"            <div class=\"media-right\">\n" + 
					"                <h5 class=\"media-heading\" id=\"productPrice" + i + "\">$" + price + "</h5><br>\n" + 
					"                <p><button type=\"button\" class=\"removebut btn btn-danger\" id=\"removebut" + i + "\" onclick=\"removeItem(" + i + ")\"><span class=\"glyphicon glyphicon-remove\"></span> Remove Item</button></p>\n" + 
					"            </div>\n" + 
					"        </div>\n" + 
					"    </div>\n"
			);
		}
				
		var merchtotalstring = "Merch Total: $" + subtotal.toFixed(2);
		$("#merchtotalid").html(merchtotalstring);
				
		var total = subtotal.toFixed(2);
		
		if(total != "0.00"){
			$("#totalpriceid").html("$" + total);
		} else {
			$("#checkoutButton").hide();
			$("#cartcontents").html("");
			$("#cartcontents").append("<p>Your cart is empty.<p>");
		}
	}
}

function removeItem(i) {
	var tmp = $("#productPrice" + i).text().split("$");
	var deletePrice = parseFloat(tmp[1]);
	subtotal = subtotal - deletePrice;
	var total = subtotal.toFixed(2);
	
	var product = $("#prodname" + i).html();
	$("#cartItem" + i).remove();
	$.ajax( {url: "ArtStoreAjax", data: {"productToRemove": product}, method: "POST"});
	
	if(total != "0.00"){
		$("#totalpriceid").html("$" + total);
	} else {
		$("#checkoutButton").hide();
		$("#totalpriceid").html("$" + total);
		$("#cartcontents").html("");
		$("#cartcontents").append("<p>Your cart is empty.<p>");
	}
}

function setPrice() {
	createCookie("price", subtotal, 1);
}
