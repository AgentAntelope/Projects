import java.util.Scanner;
import java.io.*;
public class ExamplePlayer{

	public static void main(String[] args) throws IOException{
		Scanner infile = new Scanner(new File("bob.txt"));
		//Pull info from fil
		String firstName = infile.nextLine();
		//Read last name
		int remainingMoney = Integer.parseInt(infile.nextLine());
		//read rounds played
		//read rounds won

		//Initiate it here
		Player p = new Player(firstName, remainingMoney);

		System.out.printf("You are %s, and now you have: %d", p.getFirstName(), p.getMoney());
	}
}