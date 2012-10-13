
import java.sql.*;  //import the file containing definitions for the parts
                    //needed by java for database connection and manipulation

public class JavaDemo
{
  private Connection connection; //used to hold the jdbc connection to the DB
  private Statement statement; //used to create an instance of the connection
  private ResultSet resultSet; //used to hold the result of your query (if one
                               // exists)
  private String query;  //this will hold the query we are using
  private String username, password;

  public JavaDemo()
  {
    /*Making a connection to a DB causes certian exceptions.  In order to handle
    these, you either put the DB stuff in a try block or have your function
    throw the Execptions and handle them later.  For this demo I will use the
    try blocks*/
    username = "stm52"; //This is your username in oracle
    password = "7108"; //This is your password in oracle
    try{
	  //  Class.forName("oracle.jdbc.driver.OracleDriver");
      //Register the oracle driver.  This needs the oracle files provided
      //in the oracle.zip file, unzipped into the local directory and 
      //the class path set to include the local directory
      DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
      //This is the location of the database.  This is the database in oracle
      //provided to the class
      String url = "jdbc:oracle:thin:@db10.cs.pitt.edu:1521:dbclass"; 
      connection = DriverManager.getConnection(url, username, password); 
      //create a connection to DB on db10.cs.pitt.edu
    }
    catch(Exception Ex)  //What to do with any exceptions
    {
      System.out.println("Error connecting to database.  Machine Error: " +
            Ex.toString());
	Ex.printStackTrace();
    }
    System.out.println("Success!");

    int counter = 1;
    /*We will now perform a simple query to the database, asking it for all the
    records it has.  For your project, performing queries will be similar*/
    try{
      statement = connection.createStatement(); //create an instance
      query = "SELECT * FROM bank"; //sample query one

      resultSet = statement.executeQuery(query); 
      while(resultSet.next()) //this not only keeps track of if another record
                              //exists but moves us forward to the first record
      {
        System.out.println("Record " + counter + ": " +
             resultSet.getString(1) + ", " + 
             resultSet.getString(2) + ", " +  
             resultSet.getString(3)); //since type date, getDate.
		counter++;
      }
      connection.close();
    
    }
    catch(Exception Ex)
    {
      System.out.println("Error running the sample queries.  Machine Error: " +
            Ex.toString());
    }

    System.out.println("Good Luck");
  }

  public static void main(String args[])
  {
    JavaDemo demo = new JavaDemo();
  }
}
