import java.sql.*; 

public class dbCon {
    public void runMe (String host, String database, String user, String password)
      throws Exception {
        
        /* run driverTest method shown below */
        driverTest();
        
        /* make the connection to the database */
        Connection conMe = makeCon (host, database, user, password);

        /* now run a select query of the intended database */
        exeQuery (conMe, "select * from contacts");
        
        /* close the database */
        conMe.close();
    } 
    protected void driverTest () throws Exception {

    }
    
    protected Connection makeCon (String host, String database, String user, String password)
    throws Exception {

      String url = "";
      try {
          url = "jdbc:mysql://" + host  + ":3306/" + database;
            Connection con = DriverManager.getConnection(url, user, password);
      System.out.println("Connection established to " + url + "...");
      return con;
      } catch (java.sql.SQLException e) {
      System.out.println("Connection couldn't be established to " + url);
      throw (e);
      }
} 
    
    protected void exeQuery(Connection con, String sqlStatement)
    throws Exception {

          try {
                  Statement cs = con.createStatement();
                  ResultSet sqls = cs.executeQuery(sqlStatement);

                  while (sqls.next()) {
                          String id = (sqls.getObject("fname").toString());
                          String data = (sqls.getObject("lname").toString());
                          System.out.println(id + " " + data);
                  }

                  sqls.close();
                 

          } catch (SQLException e) {
                  System.out.println ("Error executing sql statement");
                  throw (e);
          }
} 
    
    public static void main (String args[]) throws Exception {
        
            new dbCon().runMe("205.178.146.95", "experiments1", "myers", "BandB1216!");
         
    }
/* this bracket closes the class */
    
}
