import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

/**
 * @author sean
 * Purpose of this class: Create user permissions on client-side to relieve
 * server load.
 */
public class User {
	private String username;
	private Hashtable<Integer, Integer> accountIDPermission;
	private Hashtable<String, Integer> schoolPermission;
	private LinkedList<TransactionNode> pastTransactions;
	private String password;
	public boolean exitStatus;
	public User(databaseConnect a){
		pastTransactions = new LinkedList<TransactionNode>();
		schoolPermission = new Hashtable<String,Integer>();
		accountIDPermission = new Hashtable<Integer, Integer>();
		getUser(a.getConnection());
		try {
			setPermissions(a.getConnection());
			outputTime(a.getConnection());

		} catch (SQLException e) {
			System.out.println("There was a problem logging in, please try again later..");
			exit();
		}
	}
	private void outputTime(Connection a) throws SQLException{
		PreparedStatement b = a.prepareStatement("select last_login_timestamp from users where username = ?");
		b.setString(1, username);
		ResultSet c = b.executeQuery();
		if(c.next()){
			Timestamp d = c.getTimestamp("last_login_timestamp");
			System.out.println("Your last login was: "+d.toString());
		}
		else{
			System.out.println("Welcome to your first login!");
		}
		a.commit();
	}
	public void addTransaction(TransactionNode a){
		pastTransactions.add(a);
	}
	public void getUser(Connection a){
		String testUser = null;
		while(true){
		Scanner read = new Scanner(System.in);
		System.out.printf("Please enter your username: ");
		username = read.nextLine();
		if(username.equals("-1")){
			exit();
			break;
		}
		try {
			PreparedStatement b= a.prepareStatement("select username,password from users where username = ?");
			b.setString(1, username);
			ResultSet c =b.executeQuery();
			if(c.next())
			testUser = c.getString("username");
			if(testUser != null){
				password = c.getString("password");
				break;
			}
			System.out.println("Username does not exist, try again (or -1 to quit) ");
			
		} catch (SQLException e) {
			System.out.println("There was a problem with loading your user file, now exiting the program.");
			e.printStackTrace();
			exit();
			}
		}
		username = testUser;
		promptPassword();
	}
	public void promptPassword(){
		while(true){
			String pwTemp;
			Scanner read = new Scanner(System.in);
			System.out.printf("Please enter your password: ");
			pwTemp = read.nextLine();
			if(pwTemp.equals("-1")){
				exit();
				break;
			}
			else if(pwTemp.equals(password)){
				break;
			}
			System.out.println("You entered the wrong password, please try again, or type -1 to exit: ");
		}
		
		
	}
	/**
	 * Sets up the user permissions, call only on set-up.
	 */
	private void setPermissions(Connection c) throws SQLException{
	
		PreparedStatement permissions = c.prepareStatement("SELECT * from permissions where username = ?");
		permissions.setString(1, username);
		ResultSet r = permissions.executeQuery();
		while(r.next()){
			parsePermissions(r.getString("school_id"), r.getString("dept_code"), r.getInt("account_id"), c);
		}
		
	}
	/**
	 * @param schoolID
	 * @param deptCode
	 * @param accountID
	 * @param a
	 * @throws SQLException
	 * 
	 * Takes one permission tuple and parses it into the individual permissions.
	 */
	private void parsePermissions(String schoolID, String deptCode, int accountID, Connection a) throws SQLException{
		//Superuser
		if(schoolID == null && deptCode == null && accountID == 0){
			schoolPermission = null;
			accountIDPermission = null;
		}
		
		//Has access to all the school
		else if(schoolID != null && deptCode == null && accountID == 0){
			addSchoolCode(schoolID, a);
		}
		
		//Has access to all the department accounts.
		else if(deptCode != null && accountID == 0){
			//Add all account id's to the table that fit in this.
			addDeptCode(deptCode, a);
		}
		//Has access to one account.
		else{
			if(!accountIDPermission.containsKey(accountID)){
				accountIDPermission.put(accountID, 0);
			}		}
	}
	/**
	 * @param dept
	 * @param c
	 * @throws SQLException
	 * purpose: using dept, add all the accountIDPermissions from that dept. No
	 * need for a hashtable for dept from the looks of it. Can add one later if needed.
	 */
	private void addDeptCode(String dept, Connection c) throws SQLException{
		PreparedStatement deptCodes = c.prepareStatement("select account_id from accounts where dept_code = ?");
		deptCodes.setString(1, dept);
		ResultSet dep = deptCodes.executeQuery();
		while(dep.next()){
			int idCode =dep.getInt("accound_id");
			if(!accountIDPermission.containsKey(idCode)){
				accountIDPermission.put(idCode, 0);
			}
		}
		
	}
	
	/**
	 * @param school-school code to be added
	 * @param c-Connection
	 * @throws SQLException
	 * purpose: To add the school codes into the accountID permissions for easier
	 * access. 
	 */
	private void addSchoolCode(String school, Connection c) throws SQLException{
		PreparedStatement deptCodes = c.prepareStatement("select school_id, dept_code, account_id from accounts natural inner join ( select *" +
				"from schools natural inner join departments) where school_id = ?");
		deptCodes.setString(1, school);
		ResultSet sch = deptCodes.executeQuery();
		if(!schoolPermission.containsKey(school)){
			schoolPermission.put(school, 1);
		}
		while(sch.next()){
			int idCode =sch.getInt("account_id");
			if(!accountIDPermission.containsKey(idCode)){
				accountIDPermission.put(idCode, 1);
			}
		}
		
	}
	public void exitMain(Connection a){
		try {
			PreparedStatement end =a.prepareStatement("update users set last_login_timestamp = CURRENT_TIMESTAMP where username = ?");
			end.setString(1, username);
			end.executeUpdate();
			a.commit();
			System.out.println("All transactions made during this session: ");
			for(TransactionNode tr: pastTransactions){
				System.out.println(tr.toString());
			}
			System.out.println("Goodbye.");
			a.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
	}
	public String getUserName(){
		return username;
	}
	public Boolean checkIDPermission(int id){
		if(accountIDPermission.containsKey(id))
			return false;
		return true;
	}
	public Boolean checkschoolPermission(String id){
		if(schoolPermission.containsKey(id))
			return false;
		return true;
	}
	public Integer[] permissionID(){
		Set<Integer> rawr = accountIDPermission.keySet();
		Integer[] keys = new Integer[rawr.size()];
		keys = rawr.toArray(keys);
		return keys;
	}
	public String[] SchoolID(){
		Set<String> rawr = schoolPermission.keySet();
		String[] keys = new String[rawr.size()];
		keys = rawr.toArray(keys);
		return keys;
	}
	public void exit(){
		System.out.println("Have a nice day.");
		System.exit(0);
	}
	public static void main(String[] args){
		User Sean = new User(new databaseConnect());
		System.out.println("welcome to the iRacS");
	}
}
