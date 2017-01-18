package edu.uga.cs4300.boundary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs4300.logiclayer.ArtStoreLogicImpl;
import edu.uga.cs4300.objectlayer.Product;
import edu.uga.cs4300.objectlayer.User;

@WebServlet("/ArtStoreAjax")
public class ArtStoreAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ArtStoreAjax() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArtStoreLogicImpl logic = new ArtStoreLogicImpl();

		//returns a string containing all products of a category
		if (request.getParameterMap().containsKey("category")) {
			String category = request.getParameter("category");
			ArrayList<Product> products = logic.getProductsByCategory(category);

			String s = "";

			for (Product product : products) {
				s = s + "=====" + product.toString();
			}

			s.trim();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(s);
		}

		//returns a string containing information of a single product
		if (request.getParameterMap().containsKey("productName")) {
			String productName = request.getParameter("productName");
			productName = productName.replaceAll("%20", " ");
			Product product = logic.getSingleProduct(productName);
			String productString = product.toString();
			productString.trim();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(productString);
		}
		
		//adds product to cart by user's email
		if (request.getParameterMap().containsKey("productToAdd")) {
			String productToAdd = request.getParameter("productToAdd");
			productToAdd = productToAdd.replaceAll("%20", " ");
			String[] user = null;
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				  String name = cookies[i].getName();
				  String value = cookies[i].getValue();
				  if(name.equals("user")) {
					  user = value.split(",");
				  }
			}
			String userEmail = user[3];
			
			logic.addProductToCart(productToAdd, userEmail);
		}
		
		//takes user's email and returns a string that contains a list of products in user's cart
		if(request.getParameterMap().containsKey("findId")){
			String userEmail = request.getParameter("findId");
			String userId = logic.getUserIdByEmail(userEmail);
			ArrayList<Product> products = logic.getCartProducts(userId);
			String s = "";
			
			for(Product product: products) {
				s = s + "=====" + product.toString();
			}
			s.trim();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(s);
		}
		
		//returns a string of product strings under search input
		if (request.getParameterMap().containsKey("searchInput")) {
			String search = request.getParameter("searchInput");
			String category = request.getParameter("searchCategory");
			ArrayList<Product> products = logic.getProductsByCategory(category);
			ArrayList<Product> myProducts = new ArrayList<Product>();
			
			search = search.toLowerCase();
				
			for(Product p : products) {
				if(p.getName().toLowerCase().contains(search)) {
					myProducts.add(p);
				}
			}

			String s = "";

			for (Product product : myProducts) {
				s = s + "=====" + product.toString();
			}

			s.trim();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(s);
		}
		
		//updates user's information by old email
		if (request.getParameterMap().containsKey("fname")) {
			String fname, lname, email, address, password;
			String[] user = null;
			String oldEmail;
			
			fname = request.getParameter("fname");
			lname = request.getParameter("lname");
			email = request.getParameter("email");
			address = request.getParameter("address");
			password = request.getParameter("Mypassword");
			
			Cookie[] cookies = request.getCookies();

			for (int i = 0; i < cookies.length; i++) {
			  String name = cookies[i].getName();
			  String value = cookies[i].getValue();
			  if(name.equals("user")) {
				  user = value.split(",");
			  }
			}
			
			oldEmail = user[3];
			
			User u = logic.getUser(oldEmail);
			
			u.setFname(fname);
			u.setLname(lname);
			
			if(logic.exists(email)) {
				email = oldEmail;
			}
			
			u.setEmail(email);
			u.setAddress(address);
			u.setPassword(password);
			
			Cookie cookie = new Cookie("user", u.toString());
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			
			logic.updateUser(u, oldEmail);
		}//if
		
		//removes a product from the user's cart
		if(request.getParameterMap().containsKey("productToRemove")){
			String productToRemove = request.getParameter("productToRemove");
			productToRemove = productToRemove.replaceAll("%20", " ");
			String[] user = null;
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				 String name = cookies[i].getName();
				 String value = cookies[i].getValue();
				 if(name.equals("user")) {
					 user = value.split(",");
				 }
			}
			String userEmail = user[3];
			
			logic.removeProductFromCart(productToRemove, userEmail);
		}//if
		
		//returns a list of products in the user's history
		if(request.getParameterMap().containsKey("getHistory")){
			String email = request.getParameter("getHistory");
			ArrayList<Product> historyItems = logic.getUserHistory(email);
			
			String s = "";

			for (Product product : historyItems) {
				s = s + "=====" + product.toString() + "," + product.getPurchaseDate();
			}

			s.trim();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(s);
		}//if
		
		//deletes cart and adds to history when checking out
		if (request.getParameterMap().containsKey("checkout")) {
			String userEmail = request.getParameter("checkout");
			logic.checkout(userEmail);
			logic.emptyCart(userEmail);
		}
	}

}
