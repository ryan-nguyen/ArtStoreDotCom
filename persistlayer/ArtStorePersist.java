package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.uga.cs4300.objectlayer.Product;
import edu.uga.cs4300.objectlayer.User;

public class ArtStorePersist {
	
	/**
	 * Empty constructor
	 */
	public ArtStorePersist() {
	}

	/**
	 * Gets a ResultSet object and converts it into a more usable User object
	 * 
	 * @param email	The user's email
	 * @return		The user object containing all the user's info.
	 */
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

	/**
	 * Registers the user by putting them into the database.
	 * 
	 * @param user	A User object that contains all the information to register someone into the user table in the database
	 * @return		An int indicating success or failure
	 */
	public int userRegisterAP(User user) {
		String sql = "INSERT INTO users " + "VALUES(DEFAULT,'" + user.getFname() + "','" + user.getLname() + "','"
				+ user.getAddress() + "','" + user.getEmail() + "','" + user.getPassword() + "')";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		int rs = dbaccess.create(con, sql);
		return rs;
	}

	/**
	 * Gets all the products from the database by their category. If category is blank it will get all Products.
	 * 
	 * @param category	A String which describes the category. The three categories are "surfaces", "painting", or "drawing".
	 * @return			An ArrayList of all the Products of that one category.
	 */
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

	/**
	 * Gets all the information of one product from the database when given the name of a product
	 * 
	 * @param productName	The name of a product
	 * @return				A Product objecting containing all the info about that product.
	 */
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
	
	/**
	 * Updates all of the user's info if they put in the their old email
	 * 
	 * @param user		A product object containing all the User's info
	 * @param oldEmail	The user's old emial
	 */
	public void persistUpdateUser(User user, String oldEmail) {
		String sql = "update users set first_name=\"" + user.getFname() + "\"," + "last_name=\"" + user.getLname()
				+ "\"," + "address=\"" + user.getAddress() + "\"," + "email=\"" + user.getEmail() + "\","
				+ "password=\"" + user.getPassword() + "\"" + "where email=\"" + oldEmail + "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.update(con, sql);
	}

	/**
	 * Gets a user's ID when given the user's email.
	 * 
	 * @param 	userEmail	The user's email.
	 * @return	The user's ID.
	 */
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

	/**
	 * Get's the ID of a product when given the name of a product
	 * 
	 * @param productname	A String containing a product name
	 * @return				The ID of a product.
	 */
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

	/**
	 * Adds an item into the user's cart
	 * 
	 * @param productId	The ID of the product
	 * @param userId	The ID of the user
	 */
	public void persistAddToCart(String productId, String userId) {
		String sql = "Insert into cart values((select id from users where id=\"" + userId
				+ "\"), (select id from products where id=\"" + productId + "\"));";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.create(con, sql);
	}

	/**
	 * Gets all of the products from a user's cart by using their user ID
	 * 
	 * @param userId	The user's ID
	 * @return			An ArrayList of all of the products in a user's cart;
	 */
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
	
	/**
	 * Deletes a item from the user's cart
	 * 
	 * @param productId	The ID of the product being deleted
	 * @param userId	The ID of the user requesting a product to be deleteed.
	 */
	public void persistRemoveFromCart(String productId, String userId) {
		String sql = "delete from cart where user_id =\"" + userId + "\" AND product_id =\"" + productId
				+ "\" limit 1;";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.delete(con, sql);
	}

	/**
	 * Creates an ArrayList containing all of the Products the user has bought
	 * 
	 * @param userId	The ID of the user
	 * @return			An ArayList containing all of the products that have been purchased by the user
	 */
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

	/**
	 * Copies all of the user's cart into the user's history
	 * 
	 * @param userId	The ID of the user who is having their cart copied to their history.
	 */
	public void persistCheckout(String userId) {
		String sql = "INSERT INTO `history` (user_id, product_id) SELECT * FROM `cart` WHERE cart.user_id=\"" + userId
				+ "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.create(con, sql);
	}

	/**
	 * Removes all the items from the user's cart
	 * 
	 * @param userId	The ID of the user who is having their cart deleted.
	 */
	public void persistEmptyCart(String userId) {
		String sql = "DELETE FROM cart WHERE user_id=\"" + userId + "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		dbaccess.delete(con, sql);
	}
}
