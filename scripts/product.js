var productName = "";

$(function() {
	  var url = window.location.href;
	  var array = url.split("#");
	  productName = array[1];
	  $.ajax({url: "ArtStoreAjax", data: {"productName": productName}, method: "POST", success: fillProductPage});
	  $("#addtocartButton").click(addProduct);
});

function fillProductPage(product){
	product = product.split(",");
	
	var img = product[0];
	var name = product[1];
	var category = product[2];
	var description = product[3];
	var price = product[4];
	
	$("#productName").html(name);
	$("#productPrice").html("$" + price);
	var imgstring = "<img class=\"media-object\" src =\"" + img + "\" alt =\"" + name + " image\" width=\"350\" height=\"350\">";
	$("#productImage").html(imgstring);
	$("#productDescription").html(description);
}//fillProductPage

function addProduct(){
	if(getCookie("user")!=""){
		$("#checkmark").remove();
		$("#checkmarktext").remove();
		$("#productbody").append("<p id=\"checkmarktext\"><i id=\"checkmark\" style=\"display:inline;color: #3a7d34;\">&#10004;</i> added to cart</p>");
		$.ajax({url: "ArtStoreAjax", data: {"productToAdd": productName}, method: "POST"});
	} else {
		alert("Please log in.");
	}
}