package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessImpl extends DbAccessConfiguration implements DbAccessInterface {

	Statement state = null;
	
	@Override
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName(DB_DRIVE_NAME);
			con = DriverManager.getConnection(DB_CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return con;
	}

	@Override
	public ResultSet retrieve(Connection con, String query) {
		ResultSet results = null;

		try {
			state = con.createStatement();
			results = state.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}

	@Override
	public int create(Connection con, String query) {
		try {
			state = con.createStatement();
			state.executeUpdate(query);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int update(Connection con, String query) {
		try {
			state = con.createStatement();
			state.executeUpdate(query);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int delete(Connection con, String query) {
		try {
			state = con.createStatement();
			state.executeUpdate(query);
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void disconnect(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
