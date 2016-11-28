package edu.uga.cs4300.objectlayer;

public class Product {

	String img;
	String name;
	String category;
	String description;
	double price;

	public Product(String img, String name, String category, String description, double price) {
		super();
		this.img = img;
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
	}

	public Product() {
		this(null, null, null, null, 0);
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
