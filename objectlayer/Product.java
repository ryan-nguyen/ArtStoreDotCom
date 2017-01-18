package edu.uga.cs4300.objectlayer;

public class Product {

	String img;
	String name;
	String category;
	String description;
	double price;
	String purchase_date;
	
	/**
	 * A constructor creating a new Product object.
	 * 
	 * @param img			The product's image URL.
	 * @param name			The product's name.
	 * @param category		The product's category.
	 * @param description	The product's description.
	 * @param price			The product's price.
	 */
	public Product(String img, String name, String category, String description, double price) {
		super();
		this.img = img;
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
	}

	/**
	 * An empty constructor.
	 */
	public Product() {
		this(null, null, null, null, 0);
	}

	/**
	 * Gets the image URL of the product.
	 * 
	 * @return	A String containing the image URL of the product.
	 */
	public String getImg() {
		return img;
	}

	/**
	 * Sets the image URL for this product.
	 * 
	 * @param img	A String containing the image URL
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * Gets the name of a product.
	 * 
	 * @return	A String containing the name of the product.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the product.
	 * 
	 * @param name	A String containing the name of the product.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the category of the product.
	 * 
	 * @return	A String containing the category of the product.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category of the product.
	 * 
	 * @param category	A String containing the category of the product.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the price of the product.
	 * 
	 * @return	A Double containing the price of the product.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the product.
	 * 
	 * @param price	A Double containing the price of the product.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the description of the product.
	 * 
	 * @return	A String containing the description of the product.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the product.
	 * 
	 * @param description	A String containing the description of the product.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * A toString method that gets all of the products information in a legible manner.
	 * Each piece of information about the product is separated by a single comma.
	 * 
	 * @return	A String containing all of the product's information.
	 */
	public String toString() {
		return img + "," + name + "," + category + "," + description + "," + price;	
	}
	
	/**
	 * Gets the purchase date of the product.
	 * 
	 * @return A String containing the purchase date of the product.
	 */
	public String getPurchaseDate() {
		return purchase_date;
	}
	
	/**
	 * Sets the purchase date of the product.
	 * 
	 * @param purchaseDate	A String containing the purchase date of the product.
	 */
	public void setPurchaseDate(String purchaseDate) {
		this.purchase_date = purchaseDate;
	}
}
