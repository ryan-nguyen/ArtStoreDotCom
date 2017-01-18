package edu.uga.cs4300.objectlayer;

public class User {

	String fname;
	String lname;
	String password;
	String email;
	String address;
	
	/**
	 * A constructor creating a new User object.
	 * 
	 * @param fname		The user's first name.
	 * @param lname		The user's last name.
	 * @param password	The user's password.
	 * @param email		The user's email address.
	 * @param address	The user's physical address.
	 */
	public User(String fname, String lname, String password, String email, String address) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.email = email;
		this.address = address;
	}
	
	/**
	 * An empty constructor
	 */
	public User() {
		this(null, null, null, null, null);
	}
	
	/**
	 * Gets the user's first name.
	 * 
	 * @return	A String containing the user's first name.
	 */
	public String getFname() {
		return fname;
	}
	
	/**
	 * Sets the user's first name.
	 * 
	 * @param fname	A String containing the user's first name.
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/**
	 * Gets the user's last name.
	 * 
	 * @return	A String containing the user's last name.
	 */
	public String getLname() {
		return lname;
	}
	
	/**
	 * Sets the user's last name.
	 * 
	 * @param lname	A String containing the user's last name.
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/**
	 * Gets the user's password.
	 * 
	 * @return	A string containing the user's password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Sets the user's password
	 * 
	 * @param password	A String containing the user's password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the user's email
	 * 
	 * @return	A String containing the user's email.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the user's email
	 * 
	 * @param email	A String containing the user's email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the user's address.
	 * 
	 * @return	A String containing the user's address.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the user's address.
	 * 
	 * @param address	A String containing the user's address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * A toString method which converts all of the user information to a legible String.
	 * Each piece of information is separated by a single comma.
	 * 
	 * @return	A String containing all of the user's information.
	 */
	public String toString() {
		return fname + "," + lname + "," + password + "," + email + "," + address;
	}
}
