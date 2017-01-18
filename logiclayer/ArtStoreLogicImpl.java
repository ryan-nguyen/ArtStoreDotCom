package edu.uga.cs4300.logiclayer;

import java.util.ArrayList;

import edu.uga.cs4300.objectlayer.Product;
import edu.uga.cs4300.objectlayer.User;
import edu.uga.cs4300.persistlayer.ArtStorePersist;

public class ArtStoreLogicImpl {
	
	ArtStorePersist persist = new ArtStorePersist();
	
	/**
	 * Creates a User object them and then call the function to register them in the database.
	 * 
	 * @param fname		The user's first name.
	 * @param lname		The user's last name.
	 * @param password	The user's password.
	 * @param email		The user's email.
	 * @param address	The user's address.
	 * @return			An int indicating success or failure based on the result of the command in the database.
	 */
	public int registerUser(String fname, String lname, String password, String email, String address) {
		User user = new User(fname, lname, password, email, address);
		return persist.userRegisterAP(user);
	}
	
	/**
	 * Checks to see whether that email already exists or not.
	 * 
	 * @param email	A String containing the email
	 * @return		A Boolean. True means the email already exists, False means it does not.
	 */
	public boolean exists(String email){
		User user = persist.findUserByUsername(email);
		if(user.getEmail() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Gets all of a User's information given their email.
	 * 
	 * @param email	The user's email
	 * @return		A User object which contains all of the matching user's information.
	 */
	public User getUser(String email) {
		return persist.findUserByUsername(email);
	}
	
	/**
	 * Checks to see if the User's password matches the one passed in.
	 * 
	 * @param user		A User object containing all of the user's information.
	 * @param password	A String containing the password being checked against the user's.
	 * @return			A Boolean where True indicated that they match, and false indicating that they don't match.
	 */
	public boolean passwordMatches(User user, String password) {
		if(user.getPassword().equals(password) ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Gets all of the products of one category
	 * 
	 * @param category	A String containing the category. Should be "surfaces", "painting", or "drawing".
	 * @return			An ArrayList containing all the products of one category.
	 */
	public ArrayList<Product> getProductsByCategory(String category) {
		return persist.getProducts(category);
	}
	
	/**
	 * Gets the all of the product's information based on its name
	 * 
	 * @param productName	A String containing the product's name.
	 * @return				A Product object which has all the information of the requested product.
	 */
	public Product getSingleProduct(String productName){
		return persist.getProductByName(productName);
	}
	
	/**
	 * Updates all of the user's information in the database.
	 * 
	 * @param user		A User object containing all the changes wanted in the database.
	 * @param oldEmail	The user's old email which is used to find them in the database.
	 */
	public void updateUser(User user, String oldEmail) {
		persist.persistUpdateUser(user, oldEmail);
	}
	
	/**
	 * Adds a product to the user's cart in the database.
	 * 
	 * @param productToAdd	A String containing the name of the product being added.
	 * @param userEmail		A String containing the email of the user who is added something to their cart.
	 */
	public void addProductToCart(String productToAdd, String userEmail){
		String userId = persist.persistGetUserIdByEmail(userEmail);
		String productId = persist.persistGetProductIdByName(productToAdd);
		persist.persistAddToCart(productId, userId);
	}
	
	/**
	 * Gets the user's ID based on their corresponding email.
	 * 
	 * @param userEmail	A String containing the user's email.
	 * @return			A String containing the user's ID.
	 */
	public String getUserIdByEmail(String userEmail){
		return persist.persistGetUserIdByEmail(userEmail);
	}
	
	/**
	 * Gets all of the products in the user's cart.
	 * 
	 * @param userId	A String containing the user's ID.
	 * @return			An ArrayList of the Products in the user's cart.
	 */
	public ArrayList<Product> getCartProducts(String userId){
		return persist.persistGetCartProducts(userId);
	}
	
	/**
	 * Removes a single product from the user's cart.
	 * 
	 * @param productToRemove	A String containing the product's name
	 * @param userEmail			A String containing the user's email
	 */
	public void removeProductFromCart(String productToRemove, String userEmail){
		String userId = persist.persistGetUserIdByEmail(userEmail);
		String productId = persist.persistGetProductIdByName(productToRemove);
		persist.persistRemoveFromCart(productId, userId);
	}
	
	/**
	 * Returns an ArrayList of all the products in a user's history.
	 * 
	 * @param email	A String containing the user's email
	 * @return		An ArrayList of Products containing all the products in a user's history.
	 */
	public ArrayList<Product> getUserHistory(String email) {
		String userId = persist.persistGetUserIdByEmail(email);
		return persist.persistGetUserHistory(userId);
	}
	
	/**
	 * Gets the user's ID by their email and passes it to the checkout function.
	 * 
	 * @param userEmail	A String containing the user's email.
	 */
	public void checkout(String userEmail){
		String userId = persist.persistGetUserIdByEmail(userEmail);
		persist.persistCheckout(userId);
	}
	
	/**
	 * Empties the user's cart.
	 * 
	 * @param userEmail	A String containing the user's email.
	 */
	public void emptyCart(String userEmail){
		String userId = persist.persistGetUserIdByEmail(userEmail);
		persist.persistEmptyCart(userId);
	}
}
