import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class testshit {
	public testshit() throws ParseException{


		
	}
	@SuppressWarnings("deprecation")
	public static void main(String[]Args) throws ParseException, SQLException{
		databaseConnect db = new databaseConnect();
		String a = "101, '04', '12345', '58753', '000000', '2010/09/15', '2012/09/14', 'RDF', 'Pitt'";
		String[] subcomp = a.split(", ");
		PreparedStatement mainState;
		System.out.println(removeQuotes(subcomp[5]));
		java.sql.Date rawr;

		
		mainState =db.getConnection().prepareStatement("INSERT into accounts values(? ? ? ? ? ? ? ? ?)");
		mainState.setInt(1, Integer.parseInt(subcomp[0]));
	//	mainState.setString(2, removeQuotes(subcomp[1]));
		//mainState.setString(3, removeQuotes(subcomp[2]));
		//mainState.setString(4, removeQuotes(subcomp[3]));
		//mainState.setString(5, removeQuotes(subcomp[4]));
		mainState.setNull(2, java.sql.Types.CHAR);
		mainState.setNull(3, java.sql.Types.CHAR);
		mainState.setNull(4, java.sql.Types.CHAR);
		mainState.setNull(5, java.sql.Types.CHAR);
		mainState.setNull(9, java.sql.Types.VARCHAR);
		mainState.setNull(8, java.sql.Types.VARCHAR);
		mainState.setNull(6, java.sql.Types.DATE);
		mainState.setNull(7, java.sql.Types.DATE);

		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		//rawr =new java.sql.Date( formatter.parse(removeQuotes(subcomp[5])).getDate());
		//mainState.setDate(6, rawr);
		//rawr =new java.sql.Date( formatter.parse(removeQuotes(subcomp[6])).getDate());
		//mainState.setDate(7, rawr);
		//mainState.setString(8, removeQuotes(subcomp[7]));
		//mainState.setString(9, removeQuotes(subcomp[8]));
		mainState.executeUpdate();
		
	}
	private static String removeQuotes(String a){
		if(a.startsWith("'")){
			a = a.replace("'", "");
		}
		else if(a.startsWith("\""))
			a.replace("\"", "");
		return a;
	}
}
