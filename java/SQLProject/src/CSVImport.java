import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import oracle.jdbc.OraclePreparedStatement;


public class CSVImport {

	private static Connection db;
	public static void parseCSV(String table, ArrayList<String> csv, Connection dab){
		db = dab;

		for(String CSV: csv){
		try{
				String[] a = CSV.split(",");
				if(table.equalsIgnoreCase("accounts")){
					accountTable(a);
				}
				else if(table.equalsIgnoreCase("transactions")){
					transactionsTable(a);
				}

				else if(table.equalsIgnoreCase("users")){
					String sql = usersTable(a);
					Statement mainState = db.createStatement();
					mainState.execute(sql);
				}
				else if(table.equalsIgnoreCase("permissions")||table.equalsIgnoreCase("departments")||table.equalsIgnoreCase("maxcount")||table.equalsIgnoreCase("people")
						||table.equalsIgnoreCase("schools")){
					Statement mainState = db.createStatement();
					mainState.execute("insert into "+ table +" values("+ CSV + ")");
				}
				else{
					System.out.println("The table specified does not exist.");
					break;
				}
				System.out.println("Row added.");
				db.commit();
			}
		catch(SQLException e){
			if(e.getErrorCode()== 1){
				System.out.println("Unique primary key already there, skipping this row...");
			}
			else if(e.getErrorCode()== 2291)
				System.out.println("No foreign key found, skipping this row...");
			else{
				System.out.println(e.getErrorCode());
				e.printStackTrace();
				return;
			}
		}
		}
	}
	
	private static String usersTable(String[] subcomp){
		String sql = "insert into users values(" + removeQuotes(subcomp[0])+ ", " + removeQuotes(subcomp[1])+ ", " 
		+ removeQuotes(subcomp[2])+ ", "+ removeQuotes(subcomp[3])+ ", " + removeQuotes(subcomp[4])+ ", " + removeQuotes(subcomp[5])+ ", " 
		+ removeQuotes(subcomp[6])+ ", " + removeQuotes(subcomp[7])+ ") " ;
		return sql;
	}
	private static void accountTable(String[] subcomp) throws SQLException{
		Statement mainState;
		mainState =db.createStatement();
		String to_date1 = "to_date("+subcomp[5] + ", 'yyyy/mm/dd')";
		String to_date2 = "to_date("+subcomp[6] + ", 'yyyy/mm/dd')";
		mainState.execute("insert into accounts values("+ subcomp[0] + "," + subcomp[1] + ","+ subcomp[2] + ","+ subcomp[3] + ","
				+ subcomp[4] + ","+to_date1 + ","+ to_date2 + "," + subcomp[7] + ","+ subcomp[8]+ ")");
	}
	private static void transactionsTable(String[] subcomp) throws SQLException{
		Statement mainState;
		mainState =db.createStatement();
		String to_date1 = "to_date("+subcomp[2] + ", 'yyyy/mm/dd')";
		String to_date2 = "to_date("+subcomp[3] + ", 'yyyy/mm/dd')";
		String sql = "insert into transactions values(" + subcomp[0] + ", "+ subcomp[1] + ", " 
		+ to_date1 + ", "+ to_date2 + ", "+ subcomp[4] + ", "+ subcomp[5] + ", "+ subcomp[6] + ", "+ subcomp[7] + ", "
		+ subcomp[8] + ", "+ subcomp[9] + ", "+ subcomp[10] + ", "+ subcomp[11] + ", "+ subcomp[12] + ")";
		mainState.execute(sql);
	}
	
	
	private static String removeQuotes(String a){
		if(a.startsWith("'")){
			a =a.replace("'", "");
			a= "'" + a + "'";

		}
		else if(a.startsWith(" '")){
			a =a.replace(" '", "");
			a =a.replace("'", "");

			a= "'" + a + "'";
		}
		else if(a.startsWith("\"")){
			a=a.replace("\"", "");
			a= "'" + a + "'";

		}
		return a;
	}

}
