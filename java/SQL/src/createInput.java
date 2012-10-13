import java.util.Scanner;


public class createInput {
	public static void main(String[]args){
		Scanner a = new Scanner(System.in);
		System.out.println("Enter an email: ");
		String email = a.nextLine();
		String first = a.nextLine();
		String last = a.nextLine();
		String phone = a.nextLine();
		String City = a.nextLine();
		String Country = a.nextLine();
		
		System.out.println("insert into contacts values('"+ email + "','"+ first
				+ "','" + last+ "','" +phone+ "','" +City+ "','" +Country+ "');" );
		
	}
}
