/*
    Written by Thao Pham
    Purpose: Demo JDBC for CS1555 Class

    IMPORTANT (otherwise, your code may not compile)	
    Same as using sqlplus, you need to set oracle environment variables by 
    sourcing bash.env or tcsh.env
*/

import java.sql.*;  //import the file containing definitions for the parts
                    //needed by java for database connection and manipulation

public class TranDemo1
{
  private Connection connection; //used to hold the jdbc connection to the DB
  private Statement statement; //used to create an instance of the connection
  private ResultSet resultSet; //used to hold the result of your query (if one
                               // exists)
  private String query;  //this will hold the query we are using
  private String username, password;

  public TranDemo1()
  {
    
    username = "stm52"; //This is your username in oracle
    password = "7108"; //This is your password in oracle
    try{
      
      DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
      
      String url = "jdbc:oracle:thin:@db10.cs.pitt.edu:1521:dbclass"; 
      
      connection = DriverManager.getConnection(url, username, password); 
      
    }
    catch(Exception Ex)  
    {
      System.out.println("Error connecting to database.  Machine Error: " +
            Ex.toString());
    }
////////////////////////////////////////////////////
///////////////////EXAMPLE 1////////////////////////
   
    try{
	connection.setAutoCommit(false); //the default is true and every statement executed is considered a transaction.
    	connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	statement = connection.createStatement();

	query = "update class set max_num_students = 5 where classid = 1";
    	int result = statement.executeUpdate(query); 
 	
	//sleep for 30 seconds, so that we have time to switch to the other transaction
	Thread.sleep(30000);

	//now rollback to end the transaction and release the lock on data. 
        //You can use connection.commit() instead for this example, I just don't want to change the value
	connection.rollback();
	connection.close();
    }	
    catch(Exception Ex)
    {
      System.out.println("Machine Error: " +
            Ex.toString());
    }


/////////////////////////////////////////////////////
//////////EXAMPLE 2 + 3//////////////////////////////
////////////////////////////////////////////////////
/*

try{
	connection.setAutoCommit(false); //the default is true and every statement executed is considered a transaction.
    	connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); //which is the default
	//connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); 
	statement = connection.createStatement();

	
	//read the maximum and current number of students in the class
	 query = "SELECT max_num_students, cur_num_students FROM class where classid = 1";
     	 resultSet = statement.executeQuery(query);
      
	//note that there is no sleep here in this transaction

	int max, cur;
	if(resultSet.next())
      	{	
       	     max = resultSet.getInt(1);
             cur = resultSet.getInt(2);
	     
             //sleep for 30 seconds, so that we have time to switch to the other transaction
	     Thread.sleep(30000);
	
	     if(cur<max){

	     	query = "update class set cur_num_students = cur_num_students +1 where classid = 1";
    	    	int result = statement.executeUpdate(query); 
	 	//query = "insert into register values ('John', 1)";
    	     	//result = statement.executeUpdate(query); 
 	
	      }
	      else{ System.out.println("The class is full");}

	}

	
	connection.commit();

	connection.close();
    }	
    catch(Exception Ex)
    {
      System.out.println("Machine Error: " +
            Ex.toString());
    }
*/
/////////////////////////////////////////////////////////////////////////
////// EXAMPLE 4 /////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
/*
try{
	connection.setAutoCommit(false); //the default is true and every statement executed is considered a transaction.
    	connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); //which is the default
	statement = connection.createStatement();

	
	//read the maximum and current number of students in the class
	 query = "SELECT max_num_students, cur_num_students FROM class where classid = 1 for update of cur_num_students";
     	 resultSet = statement.executeQuery(query);
      
	//note that there is no sleep here in this transaction

	int max, cur;
	if(resultSet.next())
      	{	
       	     max = resultSet.getInt(1);
             cur = resultSet.getInt(2);
	     
             //sleep for 30 seconds, so that we have time to switch to the other transaction
	     Thread.sleep(30000);
	
	     if(cur<max){

	     	query = "update class set cur_num_students = cur_num_students +1 where classid = 1";
    	    	int result = statement.executeUpdate(query); 

	 	//query = "insert into register values ('John', 1)";
    	     	//result = statement.executeUpdate(query); 
 	
	      }
              else{ System.out.println("The class is full");}

	}
	
	connection.commit();

	connection.close();
    }	
    catch(Exception Ex)
    {
      System.out.println("Machine Error: " +
            Ex.toString());
    }
*/
/////////////////////////////////////////////////////////////////////////
////// EXAMPLE 5 /////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

/*   try{
	connection.setAutoCommit(false); //the default is true and every statement executed is considered a transaction.
    	connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); //which is the default
	statement = connection.createStatement();

   	query = "update class set max_num_students = 10 where classid = 1";
    	int result = statement.executeUpdate(query); 

	Thread.sleep(30000);
	 	
 	query = "update class set max_num_students = 10 where classid = 2";
    	result = statement.executeUpdate(query); 

	connection.commit();

	connection.close();
    }	
    catch(Exception Ex)
    {
      System.out.println("Machine Error: " +
	    Ex.toString());
    }
*/
}

  public static void main(String args[])
  {
    TranDemo1 demo = new TranDemo1();
  }
}
