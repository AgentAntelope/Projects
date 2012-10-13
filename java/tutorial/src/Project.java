import javax.swing.JOptionPane;

public class Project
{

	public static void main(String args[])
	{
	
		String amount_people, price;
		double pe, pr, x;
		
		amount_people = JOptionPane.showInputDialog("Please insert number people here:");
		price = JOptionPane.showInputDialog("Please insert the price here:");
		pe = Double.parseDouble(amount_people);
		pr= Double.parseDouble(price);
		x = pr/pe;
		
		System.out.println("The amount each person owes is " + x);
	
	}
}