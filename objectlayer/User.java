package edu.uga.cs4300.objectlayer;

public class User {

	String fname;
	String lname;
	String password;
	String email;
	String address;
	
	public User(String fname, String lname, String password, String email, String address) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.email = email;
		this.address = address;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
