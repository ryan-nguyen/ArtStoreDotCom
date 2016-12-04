package logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistlayer.ArtStorePersist;
import objectlayer.User;

public class ArtStoreLogicImpl {
	ArtStorePersist ap = new ArtStorePersist();
	
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
			user.setFname(rs.getString("first_name"));
			user.setLname(rs.getString("last_name"));
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
	
	public int userRegister(String fname, String lname, String address, String email, String password){
		User user = new User(fname, lname, password, email, address);
		return ap.userRegisterAP(user);
	}
}
