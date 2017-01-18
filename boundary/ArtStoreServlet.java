package edu.uga.cs4300.boundary;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs4300.logiclayer.ArtStoreLogicImpl;
import edu.uga.cs4300.objectlayer.User;


@WebServlet("/ArtStoreServlet")
public class ArtStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ArtStoreServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		ArtStoreLogicImpl logic = new ArtStoreLogicImpl();

		String formName = request.getParameter("formName");

		if (formName != null) {
			
			//Log user in by creating a cookie for the session
			if (formName.equals("login")) {
				String username, password;

				username = request.getParameter("username");
				password = request.getParameter("password");

				if (logic.exists(username)) {
					User user = logic.getUser(username);
					if (logic.passwordMatches(user, password)) {
						Cookie cookie = new Cookie("log", "true");
						cookie.setPath("/");
						cookie.setMaxAge(60*60*24*365);
						response.addCookie(cookie);
						
						cookie = new Cookie("user", user.toString());
						cookie.setPath("/");
						cookie.setMaxAge(60*60*24*365);
						response.addCookie(cookie);
						
						response.sendRedirect("index.html");
					} else {
						// ERROR wrong password
						response.sendRedirect("login.html");
					}
				} else {
					// ERROR user does not exist
					response.sendRedirect("login.html");
				}
			} // if login

			// Register User to database
			if (formName.equals("register")) {
				String fname, lname, password, email, address;

				fname = request.getParameter("firstname");
				lname = request.getParameter("lastname");
				password = request.getParameter("Mypassword");
				email = request.getParameter("email");
				address = request.getParameter("address1");

				if (logic.exists(email)) {
					// ERROR email already exists
					response.sendRedirect("login.html");
				} else {
					if (logic.registerUser(fname, lname, password, email, address) >= 0) {
						response.sendRedirect("login.html");
					} else {
						// ERROR could not register user
						response.sendRedirect("login.html");
					}
				}
			} // if register
		}

	}// doPost
}
