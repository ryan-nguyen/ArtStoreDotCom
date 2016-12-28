package edu.uga.cs4300.logiclayer;

import java.util.ArrayList;

import edu.uga.cs4300.objectlayer.Product;
import edu.uga.cs4300.objectlayer.User;
import edu.uga.cs4300.persistlayer.ArtStorePersist;

public class ArtStoreLogicImpl {
	
	ArtStorePersist persist = new ArtStorePersist();
	
	//registers user into database
	public int registerUser(String fname, String lname, String password, String email, String address) {
		User user = new User(fname, lname, password, email, address);
		return persist.userRegisterAP(user);
	}//registerUser
	
	//checks if user exists by email
	public boolean exists(String email){
		User user = persist.findUserByUsername(email);
		if(user.getEmail() != null) {
			return true;
		} else {
			return false;
		}
	}//exists
	
	//returns user object by user email
	public User getUser(String email) {
		return persist.findUserByUsername(email);
	}//getUser
	
	//checks if passwords match
	public boolean passwordMatches(User user, String password) {
		if(user.getPassword().equals(password) ) {
			return true;
		} else {
			return false;
		}
	}//passwordMatches
	
	//gets all products with same category
	public ArrayList<Product> getProductsByCategory(String category) {
		return persist.getProducts(category);
	}
	
	//returns a single product from database by name
	public Product getSingleProduct(String productName){
		return persist.getProductByName(productName);
	}//getSingleProduct
	
	//updates user's information
	public void updateUser(User user, String oldEmail) {
		persist.persistUpdateUser(user, oldEmail);
	}
	
	//adds product to the cart
	public void addProductToCart(String productToAdd, String userEmail){
		String userId = persist.persistGetUserIdByEmail(userEmail);
		String productId = persist.persistGetProductIdByName(productToAdd);
		persist.persistAddToCart(productId, userId);
	}
	
	//returns user's id by userEmail
	public String getUserIdByEmail(String userEmail){
		return persist.persistGetUserIdByEmail(userEmail);
	}
	
	//returns list of products in the cart
	public ArrayList<Product> getCartProducts(String userId){
		return persist.persistGetCartProducts(userId);
	}
	
	//removes a product from the cart
	public void removeProductFromCart(String productToRemove, String userEmail){
		String userId = persist.persistGetUserIdByEmail(userEmail);
		String productId = persist.persistGetProductIdByName(productToRemove);
		persist.persistRemoveFromCart(productId, userId);
	}
	
	//returns list of products in user's history
	public ArrayList<Product> getUserHistory(String email) {
		String userId = persist.persistGetUserIdByEmail(email);
		return persist.persistGetUserHistory(userId);
	}
	
	//used for adding items from cart to history
	public void checkout(String userEmail){
		String userId = persist.persistGetUserIdByEmail(userEmail);
		persist.persistCheckout(userId);
	}
	
	//deletes items in cart
	public void emptyCart(String userEmail){
		String userId = persist.persistGetUserIdByEmail(userEmail);
		persist.persistEmptyCart(userId);
	}
}
