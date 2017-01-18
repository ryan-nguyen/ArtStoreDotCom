package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DbAccessInterface {
	
	/**
	 * Constructor for a Connection object.
	 * 
	 * @return	An instance of a Connection object.
	 */
	public Connection connect();

	/**
	 * Returns a ResultSet based on a mySQL query
	 * 
	 * @param con		Connection to a database.
	 * @param query		A String containing the SQL query.
	 * @return			A ResultSet that has the results of the query.
	 */
	public ResultSet retrieve(Connection con, String query);

	/**
	 * Runs a SQL command that will add something to the database.
	 * 
	 * @param con	Connection to the database.
	 * @param query	A String containing the SQL query.
	 * @return		An int indicating whether the command was successful or not.
	 */
	public int create(Connection con, String query);

	/**
	 * Runs a SQL command that will update something in the database.
	 * 
	 * @param con	Connection object to the database.
	 * @param query	A String containing the SQL query.
	 * @return		An int indicating whether the command was successful or not.
	 */
	public int update(Connection con, String query);

	/**
	 * Runs a SQL command that will remove something from the database.
	 * 
	 * @param con	Connection to the database.
	 * @param query	A String containing the SQL query.
	 * @return		An int indicating whether the command was successful or not.
	 */
	public int delete(Connection con, String query);

	/**
	 * Disconnects from the database.
	 * 
	 * @param con	Connection to the database.
	 */
	public void disconnect(Connection con);
}
