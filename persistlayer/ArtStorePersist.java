package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.uga.cs4300.objectlayer.Product;
import edu.uga.cs4300.objectlayer.User;

public class ArtStorePersist {
	// empty constructor
	public ArtStorePersist() {
	}

	// returns resultset of users with username parameter
	public User findUserByUsername(String email) {
		User user = new User();

		String sql = "select * from users where email = '" + email + "';";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		ResultSet rs = dbaccess.retrieve(con, sql);

		try {
			rs.next();
			user.setFname(rs.getString("first_name"));
			user.setLname(rs.getString("last_name"));
			user.setAddress(rs.getString("address"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	//returns int, which indicates success or failure of query
	public int userRegisterAP(User user) {
		String sql = "INSERT INTO users " + "VALUES(DEFAULT,'" + user.getFname() + "','" + user.getLname() + "','"
				+ user.getAddress() + "','" + user.getEmail() + "','" + user.getPassword() + "')";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		int rs = dbaccess.create(con, sql);
		return rs;
	}

	//gets all products from database or products with category restriction and returns arraylist of those products
	public ArrayList<Product> getProducts(String category) {
		ArrayList<Product> products = new ArrayList<Product>();
		String sql = null;

		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();

		if (category.equals("All Categories")) {
			sql = "select * from products;";
		} else {
			sql = "select * from products where category='" + category + "';";
		}
		ResultSet rs = dbaccess.retrieve(con, sql);

		try {
			while (rs.next()) {
				Product product = new Product();

				product.setImg(rs.getString("img"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));

				double price = Double.parseDouble(rs.getString("price"));
				product.setPrice(price);

				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	//returns product by productname
	public Product getProductByName(String productName) {
		Product product = new Product();
		String sql = "select * from products where name ='" + productName + "';";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		ResultSet rs = dbaccess.retrieve(con, sql);
		try {
			rs.next();
			product.setImg(rs.getString("img"));
			product.setName(rs.getString("name"));
			product.setDescription(rs.getString("description"));
			product.setCategory(rs.getString("category"));
			double price = Double.parseDouble(rs.getString("price"));
			product.setPrice(price);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	//updates user's info where oldemail matches
	public void persistUpdateUser(User user, String oldEmail) {
		String sql = "update users set first_name=\"" + user.getFname() + "\"," + "last_name=\"" + user.getLname()
				+ "\"," + "address=\"" + user.getAddress() + "\"," + "email=\"" + user.getEmail() + "\","
				+ "password=\"" + user.getPassword() + "\"" + "where email=\"" + oldEmail + "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.update(con, sql);
	}

	//returns id of user by email
	public String persistGetUserIdByEmail(String userEmail) {
		String sql = "select * from users where email =\"" + userEmail + "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		ResultSet rs = dbaccess.retrieve(con, sql);
		String id = "";

		try {
			rs.next();
			id = rs.getString("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	//returns id of product by product name
	public String persistGetProductIdByName(String productname) {
		String sql = "select * from products where name=\"" + productname + "\";";
		String id = "";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		ResultSet rs = dbaccess.retrieve(con, sql);

		try {
			rs.next();
			id = rs.getString("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	//adds a product to a user's cart
	public void persistAddToCart(String productId, String userId) {
		String sql = "Insert into cart values((select id from users where id=\"" + userId
				+ "\"), (select id from products where id=\"" + productId + "\"));";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.create(con, sql);
	}

	//returns a list of products in a user's cart by userId
	public ArrayList<Product> persistGetCartProducts(String userId) {
		ArrayList<Product> products = new ArrayList<Product>();
		String sql = "select * from cart join products where product_id = id AND user_id = \"" + userId + "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		ResultSet rs = dbaccess.retrieve(con, sql);

		try {
			while (rs.next()) {
				Product product = new Product();

				product.setImg(rs.getString("img"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));

				double price = Double.parseDouble(rs.getString("price"));
				product.setPrice(price);

				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}
	
	//removes a product from a user's cart
	public void persistRemoveFromCart(String productId, String userId) {
		String sql = "delete from cart where user_id =\"" + userId + "\" AND product_id =\"" + productId
				+ "\" limit 1;";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.delete(con, sql);
	}

	//returns a list of products from user's history
	public ArrayList<Product> persistGetUserHistory(String userId) {
		ArrayList<Product> products = new ArrayList<Product>();
		String sql = "select * from history join products where product_id = id AND user_id = \"" + userId + "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		ResultSet rs = dbaccess.retrieve(con, sql);

		try {
			while (rs.next()) {
				Product product = new Product();

				product.setImg(rs.getString("img"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				product.setPurchaseDate(rs.getString("purchase_date"));
				
				double price = Double.parseDouble(rs.getString("price"));
				product.setPrice(price);

				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	//adds cart contents into history
	public void persistCheckout(String userId) {
		// String sql = "Insert into history values ((select id from cart where
		// id=\"" + userId +
		// "\"), (select product_id from products);";
		String sql = "INSERT INTO `history` (user_id, product_id) SELECT * FROM `cart` WHERE cart.user_id=\"" + userId
				+ "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.create(con, sql);
	}

	//removes cart items
	public void persistEmptyCart(String userId) {
		String sql = "DELETE FROM cart WHERE user_id=\"" + userId + "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.delete(con, sql);
	}
}
