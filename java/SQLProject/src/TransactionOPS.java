import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.*;

public class TransactionOPS {
	public static void addTransaction(User mainUser, Connection a, Scanner read){
		System.out.print("Please enter an account ID to add to: ");
    	String ac_ID=read.nextLine();
    	if(ac_ID.equals("zzz")){
    		return;
    	}
    	int accID;
    	try{
	    	accID =Integer.parseInt(ac_ID );
	    	
    	if(mainUser.checkIDPermission(accID)){
    		System.out.println("The entry you entered either doesn't exist or you do not have permission to insert into it.");
    		return;
    	}
    	System.out.print("Please enter the date this happened(yyyy/mm/dd): ");
    	String date_happen = read.nextLine();
    	if(date_happen.equals("zzz"))
    		return;
    	System.out.print("Please enter the date it was posted(yyyy/mm/dd): ");
    	String date_post = read.nextLine();
    	if(date_post.equals("zzz"))
    		return;
    	System.out.print("Please enter the 1 or -1 to represent budget/expense: ");
    	String t_sign = read.nextLine();
    	if(t_sign.equals("zzz"))
    		return;
    	System.out.print("Please enter the amount: ");
    	String amount = read.nextLine();
    	if(amount.equals("zzz"))
    		return;
    	System.out.print("Please enter the whether this is paid (1) or not (0): ");
    	String t_isPaid = read.nextLine();
    	if(t_isPaid.equals("zzz"))
    		return;
    	System.out.print("Please enter the whether this is actual (1) or not (0): ");
    	String t_isActual = read.nextLine();
    	if(t_isActual.equals("zzz"))
    		return;
    	System.out.print("Please enter the subcode: ");
    	String t_subcode = read.nextLine();
    	if(t_subcode.equals("zzz"))
    		return;
    	System.out.print("Please enter the reference number(or NULL): ");
    	String t_rnum = read.nextLine();
    	if(!t_rnum.equalsIgnoreCase("NULL")){
    		t_rnum = "'" + t_rnum + "'";
    	}
    	if(t_rnum.equals("zzz"))
    		return;
    	System.out.println("Please a description of the transaction: ");
    	String desc = read.nextLine();
    	if(!desc.equalsIgnoreCase("NULL")){
    		desc = "'" + desc + "'";
    	}
    	if(desc.equals("zzz"))
    		return;
        String flag = "temp";
        while (!flag.equals("y") && !flag.equals("n") && !flag.equals("zzz")) {
            System.out.print("\nDo you want derived transaction(y/n)?: ");
            flag = read.nextLine();
        }
    	if(flag.equals("zzz"))
    		return;
    	////Transaction Start///////////////////////////////////////////////
    	PreparedStatement counter = a.prepareStatement("select max_value from maxcount where counter_name = 'tx_id'");
    	ResultSet mar = counter.executeQuery();
    	mar.next();
    	int tx_id =mar.getInt("max_value");
    	
    	String sql = "insert into transactions values(1, "+ tx_id +", to_date('"+ date_happen + "','yyyy/mm/dd') , to_date('"+ date_post + "','yyyy/mm/dd'), "
    	+ t_sign + ", " + amount + ", " + t_isPaid+ ", " + t_isActual + ", " + ac_ID + ", '"+ t_subcode+ "', " +  t_rnum + ", "+ desc + ", " + "NULL)";
    	System.out.println("Transaction added.");
    	Statement insertMain = a.createStatement();
    	insertMain.executeUpdate(sql);
    	mainUser.addTransaction(new TransactionNode("performed an insert on ", ""+tx_id));
    	counter = a.prepareStatement("select max_value from maxcount where counter_name = 'i_log_id'");
    	mar = counter.executeQuery();
    	mar.next();
    	int idLog =mar.getInt("max_value");
    	counter= a.prepareStatement("insert into inserttransactionlog values( ?, ?, CURRENT_TIMESTAMP, ?, ?)");
    	counter.setInt(1, idLog);
    	counter.setInt(2, tx_id);
    	counter.setString(3, mainUser.getUserName());
    	counter.setString(4, "Insert "+ tx_id+ "transaction ID, reason:" + desc);
    	counter.executeUpdate();
    	if(flag.equals("y")){
    		insertDerivedTransactions(a, date_happen, date_post, t_sign,Integer.parseInt(amount), t_isActual, t_subcode, ac_ID, t_rnum, desc, t_isPaid,tx_id,-1, mainUser);
    	}
    	a.commit();
    	}
    	catch(NumberFormatException e){
    		System.out.println("You input an invalid number, returning to main menu..");
    		return;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		if(e.getErrorCode() == 8177){
    			System.out.println("This process timed out. Try again later..");
    		}
    		if(e.getErrorCode() == 2291)
    			System.out.println("You input a wrong new value that doesn't match up with our data, please try again...");

    		else{
    		   			
    			System.out.println("There is a problem with the connection, please wait a minute before trying again..");
    		   }
    		}
	}
		public static int insertDer(Connection a, String date_happen, String date_post, String t_sign, int amount, String t_isActual, String t_subcode, String ac_ID, String t_rnum, String desc, String t_isPaid , int der_tx, User mainUser) throws SQLException{
	    	PreparedStatement counter = a.prepareStatement("select max_value from maxcount where counter_name = 'tx_id'");
	    	ResultSet mar = counter.executeQuery();
	    	mar.next();
	    	int tx_id =mar.getInt("max_value");
	    	String desc1 = "'"+ desc+ "'";
	    	String sql = "insert into transactions values(1, "+ tx_id +", to_date('"+ date_happen + "','yyyy/mm/dd') , to_date('"+ date_post + "','yyyy/mm/dd'), "
	    	+ t_sign + ", " + amount + ", " + t_isPaid+ ", " + t_isActual + ", " + ac_ID + ", '"+ t_subcode+ "', " +  t_rnum + ", "+ desc1 + ", " + der_tx+")";
	    	System.out.println("Derived transaction added.");
	    	Statement insertMain = a.createStatement();
	    	insertMain.executeUpdate(sql);
	    	counter = a.prepareStatement("select max_value from maxcount where counter_name = 'i_log_id'");
	    	mar = counter.executeQuery();
	    	mar.next();
	    	int idLog =mar.getInt("max_value");
	    	counter= a.prepareStatement("insert into inserttransactionlog values( ?, ?, CURRENT_TIMESTAMP, ?, ?)");
	    	counter.setInt(1, idLog);
	    	counter.setInt(2, tx_id);
	    	counter.setString(3, mainUser.getUserName());
	    	counter.setString(4, "Insert "+ tx_id+ "transaction ID, reason:" + desc);
	    	counter.executeUpdate();
	    	return tx_id;
		}
	  public static void insertDerivedTransactions(Connection a, String date_happen, String date_post, String t_sign, int amount, String t_isActual, String t_subcode, String ac_ID, String t_rnum, String desc, String t_isPaid , int der_tx, int level, User mainUser ) throws SQLException{
		  try {
			  if(level!= -1){
	    		der_tx = insertDer(a, date_happen, date_post, t_sign, amount, t_isActual, t_subcode, ac_ID, t_rnum, desc , t_isPaid, der_tx, mainUser);	
			  }
			PreparedStatement main = a.prepareStatement("select * from subcodes where subcode = ?");
			main.setString(1, t_subcode);
			ResultSet main1 = main.executeQuery();
			String FBcode = null, INDcode = null, description = null;
			double FBrate = 0, INDrate = 0;
			if(main1.next()){
				FBcode = main1.getString("FBcode");
				INDcode = main1.getString("INDcode");
				FBrate = main1.getDouble("FBrate");
				INDrate = main1.getDouble("INDrate");
				description = main1.getString("subcode_name");
			}
			if(FBcode != null){
				int newAmt = (int) (FBrate * amount);
	    		insertDerivedTransactions(a, date_happen, date_post, t_sign, newAmt, t_isActual, FBcode, ac_ID, t_rnum,description , t_isPaid, der_tx, 1, mainUser);	

			}
			if(INDcode!= null){
				int newAmt = (int) (INDrate*amount);
	    		insertDerivedTransactions(a, date_happen, date_post, t_sign, newAmt, t_isActual, INDcode, ac_ID, t_rnum, "Indirect Costs" , t_isPaid, der_tx, 1,mainUser);	
				
			}
			if(INDcode == null && FBcode == null){
				return;
			}
		  
		  } catch (SQLException e) {
			System.out.println("The subcode you entered is not valid. Transaction did not go through");
			if(e.getErrorCode() == 2291)
    			System.out.println("You input a wrong new value that doesn't match up with our data, please try again...");

			a.rollback();
		}
	  }
	  public static void deleteTransaction(Connection a, Scanner b, User mainUser) throws SQLException{
	    	System.out.print("Please enter a transaction ID to delete: ");
	    	String tx_ID=b.nextLine();
	    	if(tx_ID.equals("zzz")){
	    		return;
	    	}
	    	PreparedStatement delTranState = a.prepareStatement("select tx_id, account_id from transactions where tx_id = ? and is_valid = 1");
	    	try{
	    	delTranState.setInt(1,Integer.parseInt(tx_ID ));
	    	}
	    	catch(Exception e){
	    		System.out.println("You input an invalid number, returning to main menu..");
	    		return;
	    	}
	    	ResultSet mar = delTranState.executeQuery();
	    	int accID= -1;
	    	if(mar.next()){
	    		accID = mar.getInt("account_id");
	    	}

	    	if(mainUser.checkIDPermission(accID)){
	    		System.out.println("The entry you entered either doesn't exist or you do not have permission to delete it.");
	    		return;
	    	}
	    	if(!confirmation()){
	    		return;
	    	}
	    	
	    	deleteCascade(Integer.parseInt(tx_ID), a, mainUser);
	    	
	    	mainUser.addTransaction(new TransactionNode("performed a delete on ",  tx_ID));
	    	System.out.println("deleted transaction ID: " + tx_ID);
	    	a.commit();
	    }
	  	public static void deleteCascade(int tx_ID, Connection a, User mainUser) throws SQLException{
	    	PreparedStatement delTranState;
			try {
				delTranState = a.prepareStatement("update transactions set is_valid = 0 where tx_id = ?");
		    	delTranState.setInt(1,tx_ID );
		    	delTranState.executeUpdate();
		    	delTranState = a.prepareStatement("select max_value from maxcount where counter_name = 'd_log_id'");
		    	ResultSet mar = delTranState.executeQuery();
		    	mar.next();
		    	int idLog =mar.getInt("max_value");
		    	delTranState = a.prepareStatement("insert into deletetransactionlog values( ?, ?, CURRENT_TIMESTAMP, ?)");
		    	delTranState.setInt(1, idLog);
		    	delTranState.setInt(2, tx_ID);
		    	delTranState.setString(3, mainUser.getUserName());
		    	delTranState.executeUpdate();
		    	delTranState = a.prepareStatement("select tx_ID,derived_from_tx from transactions where derived_from_tx=?");
		    	delTranState.setInt(1, tx_ID);
		    	ResultSet delCasc = delTranState.executeQuery();
		    	
		    	while(delCasc.next()){
		    		if(delCasc.getInt("tx_ID") != 0){
		    			deleteCascade(delCasc.getInt("tx_ID"), a, mainUser);
		    		}
		    	}
			} catch (SQLException e) {
				a.rollback();
				if(e.getErrorCode() == 2291)
	    			System.out.println("You input a wrong new value that doesn't match up with our data, please try again...");
			}catch(Exception e){
				System.out.println("Something went wrong, try again.");
			}

	  	}
	  	public static void updateAmtCascade(int tx_ID, Connection a, User mainUser, int oldAmount, int amount) throws SQLException{
	    	PreparedStatement upTranState;
			try {
				upTranState = a.prepareStatement("update transactions set amount = ? where tx_id = ?");
		    	upTranState.setInt(1, amount);
				upTranState.setInt(2,tx_ID );
		    	upTranState.executeUpdate();
		    	upTranState = a.prepareStatement("select max_value from maxcount where counter_name = 'u_log_id'");
		    	ResultSet mar = upTranState.executeQuery();
		    	mar.next();
		    	int idLog =mar.getInt("max_value");
		    	upTranState = a.prepareStatement("insert into updatetransactionlog values(?,?, CURRENT_TIMESTAMP, ?, 'amount', ?, ?)");
		    	upTranState.setInt(1, idLog);
		    	upTranState.setInt(2, tx_ID);
		    	upTranState.setString(3, mainUser.getUserName());
		    	upTranState.setString(4, ""+oldAmount);
		    	upTranState.setString(5, ""+amount);
		    	upTranState.executeUpdate();
		    	upTranState = a.prepareStatement("select tx_ID, subcode from transactions where derived_from_tx=?");
		    	upTranState.setInt(1, tx_ID);
		    	ResultSet upCasc = upTranState.executeQuery();
		    	while(upCasc.next()){
		    		if(upCasc.getInt("tx_ID") != 0){
		    			System.out.println("tx_id:"+upCasc.getInt("tx_ID"));
		    			upTranState = a.prepareStatement("select FBrate, INDrate from transactions natural join subcodes where tx_id = ?");
		    			upTranState.setInt(1, tx_ID);
		    			ResultSet fbMe = upTranState.executeQuery();
		    			double FBrate = 0;
		    			double INDrate = 0;
		    			String subcode = null;
		    			if(fbMe.next()){
		    				FBrate =fbMe.getDouble("FBrate");
		    				INDrate = fbMe.getDouble("INDrate");
		    				subcode = upCasc.getString("subcode");
		    			}
		    			if(subcode!= null){
			    			if(FBrate != 0 && !subcode.equals("8350")){
			    				int tempamt = (int) (amount * FBrate);
			    				updateAmtCascade(upCasc.getInt("tx_ID"), a, mainUser, amount,tempamt);
			    			}
	
			    			if(INDrate != 0 && subcode.equals("8350")){
			    				int tempamt = (int) (amount * INDrate);
			    				updateAmtCascade(upCasc.getInt("tx_ID"), a, mainUser, amount,tempamt);
			    			}
		    			}
		    		}
		    	}
			} catch (SQLException e) {
				a.rollback();
				if(e.getErrorCode() == 2291)
	    			System.out.println("You input a wrong new value that doesn't match up with our data, please try again...");

				System.out.printf("Transaction time out, please try again later...");
				}
			catch(Exception e){
				System.out.println("Something went wrong, try again.");
			}
			}
	  	private static void insertSub(Connection a, ResultSet b, String subcode) throws SQLException{
	  		PreparedStatement counter = a.prepareStatement("select max_value from maxcount where counter_name = 'tx_id'");
	    	ResultSet mar = counter.executeQuery();
	    	mar.next();
	    	int tx_id =mar.getInt("max_value");
	    	System.out.println("account id:"+ b.getInt("account_id")+ "reference num:" + b.getString("reference_num") + " derived:" + b.getInt("derived_from_tx"));
	    	counter = a.prepareStatement("insert into transactions values(1, ? , ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, NULL)");
	    	counter.setInt(1, tx_id);
	    	counter.setDate(2, b.getDate("date_happen"));
	    	counter.setDate(3, b.getDate("date_posted"));
	    	counter.setInt(4, b.getInt("sign"));
	    	counter.setInt(5, b.getInt("amount"));
	    	counter.setInt(6, b.getInt("is_paid"));
	    	counter.setInt(7, b.getInt("is_actual"));
	    	counter.setInt(8, b.getInt("account_id"));
	    	counter.setString(9, subcode);
	    	counter.setString(10, b.getString("reference_num"));
	    	counter.setString(11, b.getString("description"));
	    	
	    	counter.execute();


	  	}
	  	private static void subcodeCascade(int tx_id, Connection a, User mainUser, String subcode) throws SQLException{
	  		PreparedStatement b = a.prepareStatement("select * from transactions where tx_id = ?");
	  		b.setInt(1, tx_id);
	  		ResultSet old = b.executeQuery();
	  		old.next();
	  		deleteCascade(tx_id, a, mainUser);
	  		insertSub(a, old, subcode);
	  	}
	  	public static void updateTransaction(User mainUser, Connection a, Scanner read){
	  	 	System.out.print("Please enter a transaction ID to update: ");
	    	String tx_ID=read.nextLine();
	    	try{
	    	if(tx_ID.equals("zzz")){
	    		return;
	    	}
	    	PreparedStatement upTranState = a.prepareStatement("select * from transactions where tx_id = ? and is_valid = 1");
	    	try{
	    	upTranState.setInt(1,Integer.parseInt(tx_ID ));
	    	}
	    	catch(Exception e){
	    		System.out.println("You input an invalid number, returning to main menu..");
	    		return;
	    	}
	    	ResultSet mar = upTranState.executeQuery();
	    	int accID= -1;
	    	if(mar.next()){
	    		accID = mar.getInt("account_id");
	    	}
	    	if(mainUser.checkIDPermission(accID)){
	    		System.out.println("The entry you entered either doesn't exist or you do not have permission to update it.");
	    		return;
	    	}
	    	/////////////Input 2//////////////////////////////
	    	System.out.print("Please enter the attribute you want to update(choose: date_happen, date_posted, sign, amount, is_paid, is_actual, subcode, reference_num, or description): ");
	    	String attr_name = read.nextLine();
	    	if(attr_name.equals("zzz")){
	    		a.rollback();
	    		return;
	    	}
	    	System.out.print("Please enter the new value: ");
	    	String new_value = read.nextLine();
	    	String old_value = null;
	    	String sql = null;
	    	if(attr_name.equals("date_happen")|| attr_name.equals("date_posted")){
	    		sql = "update transactions set derived_from_tx=NULL, "+ attr_name + " = to_date('"+new_value+"', 'yyyy/mm/dd') where tx_id = " + tx_ID;
	    		Date date_attr =mar.getDate(attr_name);
	    		old_value = date_attr.toString();
	    	}
	    	else if(attr_name.equals("sign")||attr_name.equals("is_actual") ||attr_name.equals("is_paid")){
	    		sql = "update transactions set derived_from_tx=NULL, "+ attr_name + " = '" + new_value+ "' where tx_id = " + tx_ID;
	    		old_value = ""+ mar.getInt(attr_name);

	    	}
	    	else if(attr_name.equals("reference_num")||attr_name.equals("description")){
	    		sql = "update transactions set derived_from_tx=NULL, "+ attr_name + " = '" + new_value+ "' where tx_id = " + tx_ID;
	    		old_value = mar.getString(attr_name);
	    	}
	    	else if(attr_name.equals("amount")){
	    		updateAmtCascade(Integer.parseInt(tx_ID), a, mainUser,mar.getInt("amount"), Integer.parseInt(new_value));
	    	}
	    	else if(attr_name.equals("subcode")){
	    		subcodeCascade(Integer.parseInt(tx_ID), a, mainUser, new_value);
	    		old_value = mar.getString(attr_name);
	    	}
	    	else{
	    		
	    		System.out.println("Not a valid attribute");
	    		return;
	    	}
	    	upTranState = a.prepareStatement("select max_value from maxcount where counter_name = 'u_log_id'");
	    	mar = upTranState.executeQuery();
	    	mar.next();
	    	int idLog =mar.getInt("max_value");
	    	String sql2 = "insert into updatetransactionlog values(" + idLog +", "+ tx_ID+ ", CURRENT_TIMESTAMP, '"+mainUser.getUserName() 
	    	+"', '"+attr_name +"', '"+ old_value+ "', '" + new_value+"')";
	    	Statement one;
	    	if(!attr_name.equals("amount") && !attr_name.equals("subcode")){
	    		one = a.createStatement();
	    		one.executeUpdate(sql);
	    	
	    	}
	    	if(!attr_name.equals("amount")){
	    		one = a.createStatement();
	    		one.executeUpdate(sql2);
	    	}
	    	mainUser.addTransaction(new TransactionNode("performed an update on ", ""+tx_ID));
	    	a.commit();
	    	/////////////////////////////////////////////////
	    	}
	    	catch(SQLException e){
	    		if(e.getErrorCode() == 2291)
	    			System.out.println("You input a wrong new value that doesn't match up with our data, please try again...");
	    	}catch(Exception e){
				System.out.println("Something went wrong, try again.");
			}
	  	}
	    public static boolean confirmation() {
	        String flag = "temp";
	        Scanner read = new Scanner(System.in);
	        while (!flag.equals("y") && !flag.equals("n")) {
	            System.out.print("\nAre you sure you would like to do this (yes=y | no=n):");
	            flag = read.nextLine();
	        }
	        if (flag.equals("y")) {
	            return true;
	        } else {
	            return false;
	        }
	    }
}
