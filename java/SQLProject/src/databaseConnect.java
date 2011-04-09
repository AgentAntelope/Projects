/**
 * @author Sean Myers, Steven Forrest
 * Purpose of this class: To abstract details of login and 
 *    returning a successful connection.
 * 
 */

import java.sql.*;
public class databaseConnect {
	private static Connection connection; //Private, but accessible through get___statements
	public databaseConnect(){
	    try{
	    	//Login info into one of the databases
	        String username = "stm52";
	        String password = "7108";
	        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
	        String url = "jdbc:oracle:thin:@db10.cs.pitt.edu:1521:dbclass"; 
	        //////////////////////////////////////
	        
	        //Attempts to connect with info provided
	        connection = DriverManager.getConnection(url, username, password);
	        ////////////////////////////////////////
	        
	        //Sets Commit levels and transaction level to Serializeable
	        connection.setAutoCommit(false); 
	    	connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);	     
	    	///////////////////////////////////////////////////////////
	    }
	      catch(Exception Ex)  
	      {
	        System.out.println("Error connecting to database, please restart the program and try again");
	      }
	}
    public static Connection getConnection(){
    	return connection;
    }
    public static void quit(){
    	try {
			connection.close();
		} 
    	catch (SQLException e) {
			System.out.println("Connection already closed");
		}
    }
	
	public static void main(String[]Args){
		new databaseConnect();
	}
}
