package boundary;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
/*import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/
import javax.servlet.*;
import javax.servlet.http.*;

import logiclayer.ArtStoreLogicImpl;
import objectlayer.User;

/**
 * Servlet implementation class ArtStoreLogin
 */
@WebServlet("/ArtStoreLogin")
public class ArtStoreLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtStoreLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		ArtStoreLogicImpl alp = new ArtStoreLogicImpl();
	
		String username = request.getParameter("username");		
		if(username!=null){
					String password = request.getParameter("password");
			if(!alp.exists(username)){
				//code for when the user does not exist....
			} else {
				User user = alp.getUser(username);
				if(user.getPassword().equals(password)){
					//code for when the username matches the password
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					//redirect to home page
				} else {
					//code for when the username doesnt match the password
					//redirect to login show error message??
				}
			}
		}
		
		String regfirstname = request.getParameter("regfirstname");
		if(regfirstname!=null){
			String reglastname = request.getParameter("reglastname");
			String regaddress = request.getParameter("regaddress");
			String regcardnumber = request.getParameter("regcardnumber");
			String regemail = request.getParameter("regemail");
			String regpassword = request.getParameter("regpassword");
			
			if(!alp.exists(regemail)){
				//User user = new User(regfirstname, reglastname, regaddress, regemail, regcardnumber, regpassword);
				int on = alp.userRegister(regfirstname, reglastname, regaddress, regemail, regcardnumber, regpassword);
				if(on==0){
					//successfully added user to database
					//return to login page
				}else{
					//failed to add user to database
					//return to register with error??
				}
			} else {
				//email already exists, redirect to register page with error?
			}
		}
	}

}
