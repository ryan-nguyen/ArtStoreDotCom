package logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistlayer.ArtStorePersist;
import objectlayer.User;

public class ArtStoreLogicImpl {
	
	//empty constructor
	public ArtStoreLogicImpl(){
	}
	
	//gets User object from database, using username
	public User getUser(String username){
		ArtStorePersist ap = new ArtStorePersist();
		ResultSet rs = ap.findUserByUsername(username);
		User user = new User();
		try{
			rs.next();
			user.setId(rs.getString("id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setAddress(rs.getString("address"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
		} catch (SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
  //checks database to see if username already exists or not
	public boolean exists(String username){
		ArtStorePersist ap = new ArtStorePersist();
		ResultSet rs = ap.findUserByUsername(username);
		try{
			//rs.isBeforeFirst() returns true if the cursor is before the first element
			//it returns false if the cursor is not before the first OR there is no first
			if(!rs.isBeforeFirst()){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public int userRegister(String firstname, String lastname, String address, String email, String password){
		ArtStorePersist ap = new ArtStorePersist();
		return ap.userRegisterAP(firstname, lastname, address, email, password);
	}
}
