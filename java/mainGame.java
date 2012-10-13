import java.io.*;
import java.util.Scanner;
public class mainGame{

	public static void main(String [] args) throws IOException{

		Scanner file = new Scanner(new File("bob.txt"));
		String name = file.nextLine();
		int remainingMoney = Integer.parseInt(file.nextLine());
		int roundsPlayed = Integer.parseInt(file.nextLine());
		int wonGames = Integer.parseInt(file.nextLine());
		System.out.printf("%s: Remaining: %d\n Rounds Played:%d\n Rounds won:%d\n", name, remainingMoney, roundsPlayed, wonGames);
		int count = 0;

		//PrintWriter printer = new PrintWriter(new FileOutputStream("bob.txt", true));
		//printer.println("10");
		//printer.close();
	}

}