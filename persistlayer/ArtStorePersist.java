package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtStorePersist {
  //empty constructor
	public ArtStorePersist(){
	}
	
	//returns resultset of users with username parameter
	public ResultSet findUserByUsername(String username){
		String sql = "select * from users where email = \"" + username + "\";";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		ResultSet rs = dbaccess.retrieve(con, sql);
		return rs;
	}
	
	public int userRegisterAP(String firstname, String lastname, String address, String cardnumber, String email, String password){
		String sql = "insert into users values(DEFAULT, " + firstname + ", \"" + lastname + ", \"" + address + ", \"" + cardnumber + ", \""
				+ email + ", \"" + password + "\")";
		DbAccessImpl dbaccess = new DbAccessImpl();
		Connection con = dbaccess.connect();
		int rs = dbaccess.create(con, sql);
		return rs;
	}
}
