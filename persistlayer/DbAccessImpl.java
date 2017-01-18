package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessImpl extends DbAccessConfiguration implements DbAccessInterface {

	Statement state = null;
	
	
	@Override
	/**
	 * Constructor that creates a Connection object to connect to the database by using the information
	 * in DbAccessConfiguration.
	 */
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
	/**
	 * Returns a ResultsSet after executing a query to the database.
	 * 
	 * @param con	Connection to the database.
	 * @param query	The query going to the database.
	 * @return		The result of the query to the database.
	 */
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
	/**
	 * Executes a query in the database. Used for Insert and calls like that.
	 * 
	 * @param query	A string containing the SQL query.
	 * @param con	The connection to the database.
	 * return		An int indicating whether the query was successful or not.
	 */
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
	/**
	 * Executes a query in the database. Used for Update and calls like that.
	 * 
	 * @param query	A String containing the SQL query.
	 * @param con	The connection to the database.
	 * @return		An int indicating whether the query was successful or not.
	 */
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
	/**
	 * Executes a query in the database. Used for Delete and calls like that.
	 * 
	 * @param con	A String containing the SQL query.
	 * @param query	The connection to the database.
	 * @return		An int indicating whether the query was successful or not.
	 */
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
	/**
	 * Closes the connection to the database.
	 * 
	 * @param con	The connection to the database.
	 */
	public void disconnect(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
