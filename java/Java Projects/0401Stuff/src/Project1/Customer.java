package Project1;
import java.util.*;
import javax.swing.JOptionPane;

public class Customer {
	Scanner inkey = new Scanner(System.in);

	public int Model = 0;
	public int ModelCost = 0;
	public int MovieCost = 0;
	public int Movies = 0;
	public String name = "";
	public int state = 0;
	public int SubTotal = 0;
	public int Telescope = 0;
	public int TelescopeCost = 0;
	public double Total = 0;

	public void ChoiceSystem() {

		System.out.println("1. Mini Hubble Telescope - Only 100$");
		System.out
				.println("2. Space Shuttle Model - 50$ (Special Discount 40$ per model if you buy 2 or more");
		System.out
				.println("3. Star Trek Movie Blue Ray -20$ (Limited Time Offer:Buy 5, get 1 FREE!)");
		System.out
				.println("Remember, if you buy over 200$ worth of merchandise, you get 10% off your total");
		if (Telescope != 0)
			System.out.println("You want to buy " + Telescope + " Telescopes");
		if (Model != 0)
			System.out.println("You want to buy " + Model + " Models");
		if (Movies != 0)
			System.out.println("You want to buy " + Movies + " Movies");
		if (SubTotal != 0)
			System.out.println("Your current SubTotal is " + SubTotal + "$");
		System.out
				.println("Please type in one of the numbers above to purchase the product or see more details on the product");
		System.out
				.println("If you want to continue to checkout or you want to exit enter 0");
		int choice = inkey.nextInt();

		if (choice == 0)
			exitCustomer();
		if (choice == 1)
			TelescopeDisplay();
		if (choice == 2)
			ModelDisplay();
		if (choice == 3)
			MovieDisplay();
	}

	public void exitCustomer() {
		TotalCost();
		if (Total > 0) {
			String exit = JOptionPane.showInputDialog("Your total cost is: "
					+ Total + "$ please enter your Credit Card below");
			JOptionPane.showMessageDialog(null,
					"Thanks for coming to our store " + name + " you bought"
							+ Telescope + " Telescopes " + Model
							+ " Models and " + Movies + " Movies");

		} else
			JOptionPane
					.showMessageDialog(null,
							"Thanks for coming to our store, please tell your friends about us!");

	}

	public void ModelDisplay() {
		Boolean keeptrue = true;
		do {
			String model = "";
			model = JOptionPane
					.showInputDialog("Space Shuttle Models each cost 50$ unless you buy 2 or more in which each costs 40$ How many do you want?");
			Model = Integer.parseInt(model);

			if (Model >= 0) {
				if (Model >= 2) {
					ModelCost = Model * 40;
				} else {
					ModelCost = Model * 50;
				}
				keeptrue = false;
				SubTotal();
			} else
				JOptionPane
						.showMessageDialog(null,
								"You have entered an invalid number, please try again.");
		} while (keeptrue);
		ChoiceSystem();
	}

	public void MovieDiscount(int MovieNumber) {
		int multiplier = 1;
		int compareMovie = 5;
		int CompareMovie = 0;
		while (MovieNumber > CompareMovie) {
			CompareMovie = compareMovie * multiplier;
			multiplier++;
		}
		if (MovieNumber == CompareMovie && MovieNumber > 0) {
			int numberfree = MovieNumber + 1;
			compareMovie = CompareMovie / 5;
			String YesNo = JOptionPane
					.showInputDialog("Do you wish to get 1 more, it is free since you got "
							+ MovieNumber + " Enter 1 for yes, 2 for no");
			int checknumber = Integer.parseInt(YesNo);
			if (checknumber == 1) {
				Movies = numberfree;
				int MoviesFree;
				MoviesFree = Movies / 5;
				Movies = Movies - MoviesFree;
			} else {
				int MoviesFree;
				MoviesFree = (Movies / 5) - 1;
				Movies = Movies - MoviesFree;
			}
		} else {
			int MoviesFree;
			MoviesFree = Movies / 5;
			Movies = Movies - MoviesFree;
		}
		MovieCost = Movies * 20;
	}

	public void MovieDisplay() {
		Boolean keeptrue = true;
		do {
			String movie = "";
			movie = JOptionPane
					.showInputDialog("For every 5 Star Trek Blue Ray you get 1 free. Each cost 20$ How many do you want?");
			Movies = Integer.parseInt(movie);

			if (Movies >= 0) {

				MovieDiscount(Movies);
				System.out.println(Movies);
				SubTotal();
				keeptrue = false;

			} else
				JOptionPane
						.showMessageDialog(null,
								"You have entered an invalid number, please try again.");
		} while (keeptrue);
		ChoiceSystem();
	}

	public void SubTotal() {
		SubTotal = TelescopeCost + ModelCost + MovieCost;
	}

	public void TelescopeDisplay() {
		Boolean keeptrue = true;
		do {
			String telescope = "";
			telescope = JOptionPane
					.showInputDialog("Telescopes cost 100$ per. How many do you want?");
			Telescope = Integer.parseInt(telescope);

			if (Telescope >= 0) {
				TelescopeCost = Telescope * 100;
				keeptrue = false;
				SubTotal();

			} else
				JOptionPane
						.showMessageDialog(null,
								"You have entered an invalid number, please try again.");
		} while (keeptrue);
		ChoiceSystem();

	}

	public void TotalCost() {
		if (SubTotal >= 200)
			Total = SubTotal * .90;
		else if (state == 1)
			Total = SubTotal * 1.06;
		else if (state == 1 && SubTotal >= 200)
			Total = SubTotal * .96;
		else
			Total = SubTotal;
	}
}