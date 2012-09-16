
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 *
 * Steven Forrest and Sean Myers
 * This is the main File of the program. It contains either the code for each
 * function or the link to the other files
 *
 */
public class Transactions {

    static Connection connection; //used to hold the jdbc connection to the DB
    static Statement statement; //used to create an instance of the connection
    static ResultSet resultSet; //used to hold the result of your query (if one
    // exists)
    static String query;  //this will hold the query we are using
    static String TODAY;    //holds the value of TODAY
    static String TODAY2;   //holds the value of TODAY2
    static User mainUser;   //main user logged in


    /*
     *
     * Prints out the main screen for the program
     *
     */
    public static void printmenu() {

        System.out.println("===== Welcome to iRACS v1.0 (built by sgf4 and stm52) ====");
        System.out.println("");
        System.out.println("What do you want to do next?");
        System.out.println("[01] delete table             [07] list transactions");
        System.out.println("[02] csv import               [08] update transaction");
        System.out.println("[03] set today                [09] delete transaction");
        System.out.println("[04] show accounts            [10] account balance");
        System.out.println("[05] show account details     [11] school accounts");
        System.out.println("[06] add transaction          [12] Logout");
        System.out.println("(Give zzz to any prompt in any task to return back to this menu)");
        System.out.println("");
    }

    /*
     *
     * Method to check to see if a given string should return back to the main screen
     */
    public static boolean checkzzz(String s) {
        if (s.equals("zzz")) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Sets the current date
     * Creates the TODAY as yyyy-mm-dd
     * Creates the TODAY2 as yyyy/mm/dd
     */
    public static void getDate() {

        Calendar cal = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(cal.getTime().getTime());
        TODAY = date.toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date2 = new java.util.Date();
        TODAY2 = dateFormat.format(date2);

    }

    /*
     * Method to confirm the choice of the user. Will repeat till the correct
     * value
     *
     */
    public static boolean confirmation() {
        String flag = "temp";
        Scanner read = new Scanner(System.in);
        while (!flag.equals("y") && !flag.equals("n")) {
            System.out.print("\nAre you sure you would like to do this (yes=y | no=n):");
            flag = read.nextLine();
            if (flag.equals("zzz")) {
                break;
            }
        }
        if (flag.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    public static void setDate() {
        String date;
        Scanner read = new Scanner(System.in);
        System.out.print("\nChoose the new Date:");
        date = read.nextLine();
        if (checkzzz(date) != true) {
            int year = Integer.parseInt(date.substring(0, 4));
            int ret = 1;
            if (year <= 1970 || year > 2020) {
                System.out.println("This is not a valid Year");
                ret = 0;
            }
            int month = Integer.parseInt(date.substring(5, 7));
            if (month < 1 || month > 12) {
                System.out.println("This is not a valid Month");
                ret = 0;
            }
            int day = Integer.parseInt(date.substring(8, 10));
            if (day < 1 || day > 31) {
                System.out.println("This is not a valid Day");
                ret = 0;
            }
            if (ret == 1) {
                Calendar cal = Calendar.getInstance();
                cal.set(cal.YEAR, year);
                cal.set(cal.MONTH, month - 1);
                cal.set(cal.DATE, day);
                java.sql.Date in = new java.sql.Date(cal.getTime().getTime());
                TODAY = in.toString();

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                java.util.Date date2 = new java.util.Date(date);
                TODAY2 = dateFormat.format(date2);
                System.out.println(TODAY2);

            }
        }
    }

    public static String formatdate(String date) {
        if (checkzzz(date) != true) {
            int year = Integer.parseInt(date.substring(0, 4));
            int ret = 1;
            if (year <= 1970 || year > 2020) {
                System.out.println("This is not a valid Year");
                ret = 0;
            }
            int month = Integer.parseInt(date.substring(5, 7));
            if (month < 1 || month > 12) {
                System.out.println("This is not a valid Month");
                ret = 0;
            }
            int day = Integer.parseInt(date.substring(8, 10));
            if (day < 1 || day > 31) {
                System.out.println("This is not a valid Day");
                ret = 0;
            }
            if (ret == 1) {
                Calendar cal = Calendar.getInstance();
                cal.set(cal.YEAR, year);
                cal.set(cal.MONTH, month - 2);
                cal.set(cal.DATE, day);
                java.sql.Date in = new java.sql.Date(cal.getTime().getTime());
                return in.toString();
            }

        }
        return null;
    }

    public static void print_result(int num) {
        try {
            int counter = 1;

            while (resultSet.next()) {
                int i = 1;
                while (i < num) {
                    System.out.print(resultSet.getString(i) + "  ");
                    i++;
                    counter++;
                }
                System.out.println("");


            }
        } catch (SQLException e) {

            System.out.println("The program has encountered an error. Please try again if problem persists contact System Admin");
            System.out.println("Error" + e.toString());
            e.printStackTrace();
            System.out.println(e.getErrorCode());

        }
    }

    public static void deletetable(Connection connection) {
        String table;
        Scanner read = new Scanner(System.in);
        System.out.print("\nEnter the table to be deleted:");
        table = read.nextLine();
        if (checkzzz(table) != true) {
            if (confirmation() == true) {
                query = "DELETE FROM " + table;
                try {
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(query);
                    System.out.println("The table " + table + " has been deleted succesfully");

                } catch (SQLException e) {
                    if (e.getErrorCode() == 2292) {
                        System.out.println("This table cannot be deleted because other tables have foreign key reference to it");
                        System.out.println("This table has NOT be deleted");
                    } else {
                        System.out.println("The program has encountered an error. Please try again if problem persists contact System Admin");
                        System.out.println("Error" + e.toString());
                        e.printStackTrace();
                        System.out.println(e.getErrorCode());
                    }
                }
            }
        }
    }

    public static void list_transactions() {
        String acc;
        String sub;
        String refnum;
        String paid;
        String actual;
        String date;
        int count = 1;

        String today = TODAY2;
        int year = Integer.parseInt(today.substring(0, 4));
        int month = Integer.parseInt(today.substring(5, 7));
        int day = Integer.parseInt(today.substring(8, 10));
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, year);
        cal.set(cal.MONTH, month - 1);
        cal.set(cal.DATE, day);
        java.sql.Date in = new java.sql.Date(cal.getTime().getTime());
        today = in.toString();



        Scanner read = new Scanner(System.in);
        System.out.print("\nChoose an Account Number for Information on:");
        acc = read.nextLine();
        if (checkzzz(acc) != true) {
            int check = Integer.valueOf(acc);
            if (!mainUser.checkIDPermission(check)) {

                System.out.print("\nChoose the date range:");
                date = read.nextLine();
                if (checkzzz(date) != true) {

                    System.out.print("\nChoose a Subcode for information on(or select all):");
                    sub = read.nextLine();
                    if (checkzzz(sub) != true) {

                        System.out.print("\nChoose a Reference Number (or select all):");
                        refnum = read.nextLine();
                        if (checkzzz(refnum) != true) {

                            System.out.print("\nChoose where they are paid(0 or 1 or all):");
                            paid = read.nextLine();
                            if (checkzzz(paid) != true) {

                                System.out.print("\nChoose where they are Actual(0 or 1 or all):");
                                actual = read.nextLine();
                                if (checkzzz(actual) != true) {

                                    query = "select * from transactions"
                                            + " where transactions.account_id=" + acc;

                                    if (!sub.equals("all")) {

                                        query = query + " and transactions.subcode=" + sub;
                                    }
                                    if (!refnum.equals("all")) {

                                        query = query + " and transactions.reference_num=" + refnum;
                                    }
                                    if (!paid.equals("all")) {

                                        query = query + " and transactions.is_paid=" + paid;
                                    }
                                    if (!actual.equals("all")) {

                                        query = query + " and transactions.is_actual=" + actual;
                                    }

                                    if (date.equals("all")) {
                                        //break if this is all.
                                    } else if (date.equals("today -")) {
                                        query = query + " and transactions.date_posted >= to_date('" + today + "', 'yyyy-mm-dd')";

                                    } else if (date.equals("- today")) {
                                        query = query + " and transactions.date_posted <= to_date('" + today + "', 'yyyy-mm-dd')";

                                    } else if (date.charAt(0) == '-' && !date.equals("- today")) {
                                        date = date.substring(2, date.length());
                                        String temp = date;
                                        year = Integer.parseInt(temp.substring(0, 4));
                                        month = Integer.parseInt(temp.substring(5, 7));
                                        day = Integer.parseInt(temp.substring(8, 10));
                                        cal = Calendar.getInstance();
                                        cal.set(cal.YEAR, year);
                                        cal.set(cal.MONTH, month - 1);
                                        cal.set(cal.DATE, day);
                                        in = new java.sql.Date(cal.getTime().getTime());
                                        temp = in.toString();

                                        query = query + " and transactions.date_posted <= to_date('" + (temp) + "', 'yyyy/mm/dd')";
                                    } else if (date.charAt(date.length() / 2) == '-') {
                                        String date2 = date.substring((date.length() / 2) + 2, date.length());
                                        date = date.substring(0, (date.length() / 2) - 1);
                                        query = query + " and transactions.date_posted >= to_date('" + formatdate(date) + "', 'yyyy/mm/dd')";
                                        query = query + " and transactions.date_posted <= to_date('" + formatdate(date2) + "', 'yyyy/mm/dd')";


                                    } else if (date.charAt(date.length() - 1) == '-' && !date.equals("today -")) {
                                        date = date.substring(0, date.length() - 1);

                                        String temp = date;
                                        year = Integer.parseInt(temp.substring(0, 4));
                                        month = Integer.parseInt(temp.substring(5, 7));
                                        day = Integer.parseInt(temp.substring(8, 10));
                                        cal = Calendar.getInstance();
                                        cal.set(cal.YEAR, year);
                                        cal.set(cal.MONTH, month - 1);
                                        cal.set(cal.DATE, day);
                                        in = new java.sql.Date(cal.getTime().getTime());
                                        temp = in.toString();

                                        query = query + " and transactions.date_posted >= to_date('" + temp + "', 'yyyy/mm/dd')";
                                    } else {
                                        System.out.println("Valid Date was not entered. Valid dates are:"
                                                + "\n yyyy/mm/dd - yyyy/mm/dd"
                                                + "\n - yyyy/mm/dd "
                                                + "\n yyyy/mm/dd - "
                                                + "\n - today "
                                                + "\n today - "
                                                + "\n all");
                                        count = 0;
                                    }

                                }
                                if (count == 1) {

                                    try {
                                        Statement mainState;
                                        mainState = connection.createStatement();
                                        resultSet = mainState.executeQuery(query);
                                        System.out.println("Valid, Tax ID, Date Happen, Date Posted, Sign, Amount, Paid, Actual,"
                                                + "Account ID, Subcode, Reference Number, Description, Derived from");
                                        print_result(12);
                                    } catch (SQLException e) {

                                        System.out.println("The program has encountered an error. Please try again if problem persists contact System Admin");
                                        System.out.println("Error" + e.toString());
                                        e.printStackTrace();
                                        System.out.println(e.getErrorCode());

                                    }
                                }
                            }
                        }
                    }
                }

            } else {
                System.out.println("You Do not have permission to Search for this. Please contact System Administrator");
            }
        }
    }

    public static void importCSV(Connection connection, Scanner br) throws IOException {
        System.out.print("Please input a table: ");
        String tableName = null;

        tableName = br.nextLine();
        if (tableName.equals("zzz")) {
            br.close();
            return;
        }
        System.out.print("Please input a file name: ");
        String fileName = br.nextLine();
        if (fileName.equals("zzz")) {
            br.close();
            return;
        }
        File fromFile = new File(fileName);
        Scanner freader;
        try {
            freader = new Scanner(fromFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Choose this option and try again..");
            return;
        }
        ArrayList<String> infiniteFile = new ArrayList<String>();
        while (freader.hasNextLine()) {
            infiniteFile.add(freader.nextLine());
        }
        CSVImport.parseCSV(tableName, infiniteFile, connection);
        freader.close();



    }

    public static void show_account_details() {
        String acc;
        Scanner read = new Scanner(System.in);
        System.out.print("\nChoose an Account Number for Information on:");
        acc = read.nextLine();

        int check = Integer.valueOf(acc);
        if (!mainUser.checkIDPermission(check)) {
            if (checkzzz(acc) != true) {
                query = "select accounts.account_id,accounts.purpose,accounts.project,accounts.start_date,accounts.end_date,accounts.title,"
                        + "users.first_name,users.last_name,users.email,departments.dept_name,schools.school_name"
                        + " from accounts, people, users, departments, schools where accounts.account_id=" + acc
                        + " and accounts.account_id=people.account_id"
                        + " and people.username=users.username"
                        + " and users.dept_code=departments.dept_code"
                        + " and departments.school_id=schools.school_id";


                try {
                    Statement mainState;
                    mainState = connection.createStatement();
                    resultSet = mainState.executeQuery(query);
                    ResultSet temp = resultSet;
                    System.out.println("Account ID, Purpose, Project, Start Date, End Date, Title, First Name, Last Name, Email, Department Name, School Name");
                    print_result(12);

                } catch (SQLException e) {


                    System.out.println("The program has encountered an error. Please try again if problem persists contact System Admin");
                    System.out.println("Error" + e.toString());
                    e.printStackTrace();
                    System.out.println(e.getErrorCode());

                }

            }
        } else {

            System.out.println("You do not have permissions for this Account.");
        }
    }

    public static void showAccounts(Connection a, Scanner b){
    	try {
    		System.out.println("Show all active(y/n)");
    		String choice =b.nextLine();
    		for(int i:mainUser.permissionID()){
    			String sql;
    			if(choice.equals("y")){
    				sql = "select * from accounts where start_date is not null"+
    				" and end_date is not null and end_date > to_date('" + TODAY + "', 'yyyy/mm/dd') and account_id="+i;
    			}
    			else{
    				sql = "select * from accounts where account_id="+i;
    			}
				Statement show =a.createStatement();
				ResultSet showAccounts =show.executeQuery(sql);
				while(showAccounts.next()){
				System.out.println("Account: "+ showAccounts.getInt("account_id") +"  Project:"+ showAccounts.getString("project")+"  Dept Code: "+ showAccounts.getString("dept_code") + " Start: " + showAccounts.getDate("start_date").toString()
						+ "  Purpose: " + showAccounts.getString("purpose")+ "  Title: " + showAccounts.getString("title")+ "   Funding Source: " + showAccounts.getString("funding_source"));
				}
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public static void school_acc() {


        String acc;
        Scanner read = new Scanner(System.in);
        System.out.print("\nChoose a School ID for information on:");
        acc = read.nextLine();
        if (!mainUser.checkschoolPermission(acc)) {
            if (checkzzz(acc) != true) {

                query = "select departments.dept_code as total, sum(transactions.amount) as sum"
                        + " from accounts, departments, schools, transactions where schools.school_id=" + acc
                        + " and accounts.account_id=transactions.account_id"
                        + " and departments.school_id=schools.school_id"
                        + " and accounts.dept_code=departments.dept_code"
                        + " and transactions.sign=1"
                        + " GROUP BY schools.school_id,departments.dept_code";

                String query_2 = "select departments.dept_code as total, sum(transactions.amount) as sum"
                        + " from accounts, departments, schools, transactions where schools.school_id=" + acc
                        + " and accounts.account_id=transactions.account_id"
                        + " and departments.school_id=schools.school_id"
                        + " and accounts.dept_code=departments.dept_code"
                        + " and transactions.sign=-1"
                        + " GROUP BY schools.school_id,departments.dept_code";


                String final_query = " select temp.total,temp.sum-temp2.sum from (" + query + ") temp, (" + query_2 + ") temp2";



                try {
                    Statement mainState;
                    mainState = connection.createStatement();
                    resultSet = mainState.executeQuery(final_query);
                    System.out.println("The Balance for School " + acc + ":");
                    print_result(3);
                } catch (SQLException e) {

                    System.out.println("The program has encountered an error. Please try again if problem persists contact System Admin");
                    System.out.println("Error" + e.toString());
                    e.printStackTrace();
                    System.out.println(e.getErrorCode());

                }
            }
        } else {
            System.out.println("You Do not have permission to Search for this. Please contact System Administrator");
        }
    }

    public static void acc_balance() {
        String acc;
        String paid;
        String actual;
        String date;

        String temp = TODAY2;
        int year = Integer.parseInt(temp.substring(0, 4));
        int month = Integer.parseInt(temp.substring(5, 7));
        int day = Integer.parseInt(temp.substring(8, 10));
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, year);
        cal.set(cal.MONTH, month - 2);
        cal.set(cal.DATE, day);
        java.sql.Date in = new java.sql.Date(cal.getTime().getTime());
        temp = in.toString();
        String query_2;
        String query_tot = null;
        String query_tot2 = null;

        Scanner read = new Scanner(System.in);
        System.out.print("\nChoose an Account Number for Information on:");
        acc = read.nextLine();
        if (checkzzz(acc) != true) {
            System.out.print("\nChoose the date range:");
            date = read.nextLine();
            if (checkzzz(date) != true) {

                System.out.print("\nChoose where they are paid(0 or 1 or all):");
                paid = read.nextLine();
                if (checkzzz(paid) != true) {

                    System.out.print("\nChoose where they are Actual(0 or 1 or all):");
                    actual = read.nextLine();
                    if (checkzzz(actual) != true) {

                        Integer[] acc_per = mainUser.permissionID();


                        if (!acc.equals("all")) {

                            query = "select transactions.subcode as sub,sum(transactions.amount) as total,transactions.reference_num as num from transactions"
                                    + " where transactions.account_id=" + acc
                                    + " and transactions.sign=1";
                            query_tot = "select sum(transactions.amount) as total from transactions"
                                    + " where transactions.account_id=" + acc
                                    + " and transactions.sign=1";

                            query_2 = "select transactions.subcode as sub, sum(transactions.amount) as total, transactions.reference_num as num from transactions"
                                    + " where transactions.account_id=" + acc
                                    + " and transactions.sign=-1";
                            query_tot2 = "select sum(transactions.amount)  as total from transactions"
                                    + " where transactions.account_id=" + acc
                                    + " and transactions.sign=-1";
                        } else {
                            query = "select transactions.subcode as sub,sum(transactions.amount) as total,transactions.reference_num as num from transactions"
                                    + " where  transactions.sign=1";

                            query_tot = "select sum(transactions.amount) as total from transactions"
                                    + " where  transactions.sign=1";

                            query_2 = "select transactions.subcode as sub, sum(transactions.amount) as total, transactions.reference_num as num from transactions"
                                    + " where  transactions.sign=-1";
                            query_tot2 = "select sum(transactions.amount)  as total from transactions"
                                    + " where  transactions.sign=-1";


                            
                        }
                        if (!paid.equals("all")) {

                            query = query + " and transactions.is_paid=" + paid;
                            query_2 = query_2 + " and transactions.is_paid=" + paid;
                            query_tot = query_tot + " and transactions.is_paid=" + paid;
                            query_tot2 = query_tot2 + " and transactions.is_paid=" + paid;

                        }
                        if (!actual.equals("all")) {

                            query = query + " and transactions.is_actual=" + actual;
                            query_2 = query_2 + " and transactions.is_actual=" + actual;
                            query_tot = query_tot + " and transactions.is_actual=" + actual;
                            query_tot2 = query_tot2 + " and transactions.is_actual=" + actual;

                        }
                        int count = 1;
                        if (date.equals("all")) {
                            //break if this is all.
                        } else if (date.equals("today -")) {


                            query = query + " and transactions.date_posted >= to_date('" + temp + "', 'yyyy-mm-dd')";
                            query_2 = query_2 + " and transactions.date_posted >= to_date('" + temp + "', 'yyyy-mm-dd')";

                            query_tot = query_tot + " and transactions.date_posted >= to_date('" + temp + "', 'yyyy-mm-dd')";
                            query_tot2 = query_tot2 + " and transactions.date_posted >= to_date('" + temp + "', 'yyyy-mm-dd')";
                        } else if (date.equals("- today")) {
                            query = query + " and transactions.date_posted <= to_date('" + temp + "', 'yyyy-mm-dd')";
                            query_2 = query_2 + " and transactions.date_posted <= to_date('" + temp + "', 'yyyy-mm-dd')";

                            query_tot = query_tot + " and transactions.date_posted <= to_date('" + temp + "', 'yyyy-mm-dd')";
                            query_tot2 = query_tot2 + " and transactions.date_posted <= to_date('" + temp + "', 'yyyy-mm-dd')";
                        } else if (date.charAt(0) == '-' && !date.equals("- today")) {
                            date = date.substring(2, date.length());
                            String tem = date;
                            year = Integer.parseInt(tem.substring(0, 4));
                            month = Integer.parseInt(tem.substring(5, 7));
                            day = Integer.parseInt(tem.substring(8, 10));
                            cal = Calendar.getInstance();
                            cal.set(cal.YEAR, year);
                            cal.set(cal.MONTH, month - 1);
                            cal.set(cal.DATE, day);
                            in = new java.sql.Date(cal.getTime().getTime());
                            tem = in.toString();




                            query = query + " and transactions.date_posted <= to_date('" + tem + "', 'yyyy/mm/dd')";
                            query_2 = query_2 + " and transactions.date_posted <= to_date('" + tem + "', 'yyyy/mm/dd')";

                            query_tot = query_tot + " and transactions.date_posted <= to_date('" + tem + "', 'yyyy/mm/dd')";
                            query_tot2 = query_tot2 + " and transactions.date_posted <= to_date('" + tem + "', 'yyyy/mm/dd')";

                        } else if (date.charAt(date.length() / 2) == '-') {
                            String date2 = date.substring((date.length() / 2) + 2, date.length());
                            date = date.substring(0, (date.length() / 2) - 1);

                            query = query + " and transactions.date_posted >= to_date('" + formatdate(date) + "', 'yyyy/mm/dd')";
                            query = query + " and transactions.date_posted <= to_date('" + formatdate(date2) + "', 'yyyy/mm/dd')";
                            query_2 = query_2 + " and transactions.date_posted >= to_date('" + formatdate(date) + "', 'yyyy/mm/dd')";
                            query_2 = query_2 + " and transactions.date_posted <= to_date('" + formatdate(date2) + "', 'yyyy/mm/dd')";

                            query_tot = query_tot + " and transactions.date_posted >= to_date('" + formatdate(date) + "', 'yyyy/mm/dd')";
                            query_tot = query_tot + " and transactions.date_posted <= to_date('" + formatdate(date2) + "', 'yyyy/mm/dd')";
                            query_tot2 = query_tot2 + " and transactions.date_posted >= to_date('" + formatdate(date) + "', 'yyyy/mm/dd')";
                            query_tot2 = query_tot2 + " and transactions.date_posted <= to_date('" + formatdate(date2) + "', 'yyyy/mm/dd')";
                        } else if (date.charAt(date.length() - 1) == '-' && !date.equals("today -")) {


                            date = date.substring(0, date.length() - 1);
                            String tem = date;
                            year = Integer.parseInt(tem.substring(0, 4));
                            month = Integer.parseInt(tem.substring(5, 7));
                            day = Integer.parseInt(tem.substring(8, 10));
                            cal = Calendar.getInstance();
                            cal.set(cal.YEAR, year);
                            cal.set(cal.MONTH, month - 1);
                            cal.set(cal.DATE, day);
                            in = new java.sql.Date(cal.getTime().getTime());
                            tem = in.toString();



                            query = query + " and transactions.date_posted <= to_date('" + tem + "', 'yyyy/mm/dd')";
                            query_2 = query_2 + " and transactions.date_posted <= to_date('" + tem + "', 'yyyy/mm/dd')";

                            query_tot = query_tot + " and transactions.date_posted <= to_date('" + tem + "', 'yyyy/mm/dd')";
                            query_tot2 = query_tot2 + " and transactions.date_posted <= to_date('" + tem + "', 'yyyy/mm/dd')";
                        } else {
                            System.out.println("Valid Date was not entered. Valid dates are:"
                                    + "\n yyyy/mm/dd - yyyy/mm/dd"
                                    + "\n - yyyy/mm/dd "
                                    + "\n yyyy/mm/dd - "
                                    + "\n - today "
                                    + "\n today - "
                                    + "\n all");
                            count = 0;
                        }
                        if (count == 1) {
                            query = query + " group by transactions.sign,transactions.subcode,transactions.reference_num ";
                            query_2 = query_2 + " group by transactions.sign,transactions.subcode,transactions.reference_num ";

                            String final_query = " select temp.sub, temp.total-temp2.total from (" + query + ") temp full outer join (" + query_2 + ") temp2 on temp.sub=temp2.sub";
                            String other = " select temp.total-temp2.total from (" + query_tot + ") temp, (" + query_tot2 + ") temp2";
                            String other_ref = " select temp.num, temp.total-temp2.total from (" + query + ") temp full  outer join (" + query_2 + ") temp2 on temp.num = temp2.num";
                            String other_comb = " select temp.sub, temp.num, temp.total-temp2.total from (" + query + ") temp full outer join (" + query_2 + ") temp2"
                                    + " on temp.num = temp2.num and  temp.sub = temp2.sub";
                            try {
                                Statement mainState;
                                mainState = connection.createStatement();

                                resultSet = mainState.executeQuery(other);
                                System.out.println("Total:");
                                print_result(2);

                                resultSet = mainState.executeQuery(final_query);
                                System.out.println("The Results: (Subcode,Amount)");
                                print_result(3);

                                resultSet = mainState.executeQuery(other_ref);
                                System.out.println("The Results: (Reference Number,Amount)");
                                print_result(3);

                                resultSet = mainState.executeQuery(other_comb);
                                System.out.println("Combination: (Subcode,Reference Number,Amount)");
                                print_result(4);


                            } catch (SQLException e) {

                                System.out.println("The program has encountered an error. Please try again if problem persists contact System Admin");
                                System.out.println("Error" + e.toString());
                                e.printStackTrace();
                                System.out.println(e.getErrorCode());

                            }

                        }
                    }
                }
            }
        }
    }

    /*
     * Main Method: Creates the co
     *
     *
     */
    public static void main(String[] args) {

        String select = null;                                   //selection from the users
        getDate();                                              //set up current date
        databaseConnect main = new databaseConnect();           //set up a new db connection
        connection = databaseConnect.getConnection();           //determine the connection
        int swit = 0;                                           //different options
        mainUser = new User(main);                              //log in with a new user
       while (swit != 12) {                                    //switch statement
            System.out.println("\n\n");
            printmenu();                                        //print out the menu
            Scanner read = new Scanner(System.in);
            System.out.print("Your selection =");
            if (read.hasNext()) {
                select = read.nextLine();
            }

            swit = Integer.parseInt(select);

            switch (swit) {
                case 1:
                    deletetable(connection);
                    break;
                case 2:
			try {
				importCSV(connection,read);
			} catch (IOException e) {
			System.out.println("The filename you input was not found.");				
			}
                    break;
                case 3:
                    setDate();
                    break;
                case 4:
                showAccounts(connection,read);
                    break;
                case 5:
                    show_account_details();
                    break;
                case 6:
                	TransactionOPS.addTransaction(mainUser, connection, read);
                    break;
                case 7:
                    list_transactions();
                    break;
                case 8:
                	TransactionOPS.updateTransaction(mainUser, connection, read);
                    break;
                case 9:
    				try {
    					TransactionOPS.deleteTransaction(connection, read, mainUser);
    				} catch (SQLException e) {
    					
    					System.out.println("A problem ocurred while deleting the transaction, try again later");
    				}
                    break;
                case 10:
                    acc_balance();
                    break;
                case 11:
                    school_acc();
                    break;
                case 12:
                	mainUser.exitMain(connection);
                    break;
                default:
                    System.out.println("This is not a Valid Option. Please try again.");
                    break;
            }
        }
    }
}
