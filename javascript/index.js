var category = "All Categories";

$(function() {
	$.ajax({
		url : "ArtStoreAjax",
		data : {
			"category" : "All Categories"
		},
		method : "POST",
		success : addProducts
	});
	$("#categoryDropdown li a").click(changeCategory);
	var tags = [ "pen", "pencil", "colored pencils", "markers", "crayons",
			"paint brush", "paint palette", "oil paints", "acrylic paints",
			"finger paints", "paper", "notebook", "canvas", "easel", "tile" ];
	$("#searchBar").autocomplete({
		source : tags
	});
	$("#searchButton").click(filterBySearch);
});

function addProducts(products) {
	$("#productContainer").html("");

	var s = products.split("=====");
	if(s.length <= 1){
		$("#productContainer").html(
				"<div class=\"container text-center\" id=\"noItems\">\n" + 
				"    <div class=\"jumbotron\">\n" + 
				"        <h1>No Items</h1>\n" + 
				"    </div>\n" + 
				"</div>\n"		
		);
	}
	for (var i = 1; i < s.length; i++) {
		var product = s[i].split(",");

		var img = product[0];
		var name = product[1];
		var price = product[4];

		$("#productContainer")
				.append(
						"         <div class=\"col-sm-6 col-md-3 text-center\">\n"
								+ "\t          <div style=\"height:350px;width:250px\" class=\"thumbnail\">\n"
								+ "               <a href=\"product.html#"
								+ name + "\">" + "\t             <img src=\""
								+ img + "\" alt=\"thumbnail\">\n"
								+ "\t             <div class=\"caption\">\n"
								+ "\t                <h3>" + name + "</h3>\n"
								+ "\t                <p>$" + price + "</p>\n"
								+ "\t             </div></a>\n"
								+ "\t           </div>\n" + "         </div>\n");
	}//for
}//addProducts

function changeCategory() {
	category = $(this).text();
	$("#searchBar").val("");
	$("#search_concept").html(category);
	$.ajax({
		url : "ArtStoreAjax",
		data : {
			"category" : category
		},
		method : "POST",
		success : addProducts
	});
}

function filterBySearch() {
	var search = $("#searchBar").val();
	
	$.ajax({
		url : "ArtStoreAjax",
		data : {
			"searchInput" : search,
			"searchCategory" : category
		},
		method : "POST",
		success : addProducts
	});
}
