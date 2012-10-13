package Project1;
import javax.swing.JOptionPane;

public class MainClasses {

	public static void main(String[] args) {
		Boolean checktrue = true;
		int topnumber = 0;
		while (checktrue) {
			String topNumber = "";
			topNumber = JOptionPane
					.showInputDialog("How many customers are there?");
			topnumber = Integer.parseInt(topNumber);
			if (topnumber >= 1)
				checktrue = false;
			else
				JOptionPane
						.showMessageDialog(null,
								"You have entered an invalid number, please try again.");

		}
		Customer customerz[] = new Customer[topnumber + 1];
		int i = 1;
		while (!checktrue) {
			String State = "";
			customerz[i] = new Customer();
			customerz[i].name = JOptionPane
					.showInputDialog("Hello customer, what is your name?");
			State = JOptionPane
					.showInputDialog("Press 1 if you live in PA or 2 if you do not");
			customerz[i].state = Integer.parseInt(State);
			System.out.println("Hello " + customerz[i].name
					+ " , Welcome to Final Frontier Online Store");
			System.out
					.println("Please take a look at our quality goods below:");
			customerz[i].ChoiceSystem();
			if (i >= topnumber)
				checktrue = true;
			i++;

		}
	}
}
