package SluggerPercent;
/*
 * Sean Myers
 * CS401 - Lab - Thursday 12:15
 * Explanation:
 *My program is rather procedural in coding
 *it takes the methods of the Player object
 *and executes them in order, or else they would not work (singles only adds single to
 *the checkInput, and double method only adds double + single for checkInput)
 *Though, with slight editing, it can easily be made
 *into where someone could choose to input a single, double,triple, etc out of order.
 *Uses the Buffer string in the toString Method and outputs outside the toString.
 *Uses arrays so that at the end it is sorted from best to worst. Uses JOptionPane
 * for input and general direction.
 *
 */
import javax.swing.JOptionPane;

public class SluggerMain {


	public static void main(String[] args)
	{
	String PlayerCount = JOptionPane.showInputDialog("How many players do you want to Calculate?");
	int playerCount = Integer.parseInt(PlayerCount);
	int BaseSum = 0, AtBatSum = 0;
	
	Player[] player = new Player[playerCount];
	
	for(int i = 0; i < player.length; i++ )
		{
		player[i]= new Player();
		player[i].Name();
		player[i].InputBat();
		player[i].InputSingle();
		player[i].InputDouble();
		player[i].InputTriple();
		player[i].InputHomerun();
		player[i].CalculateSlugPercent();
		AtBatSum = player[i].ReturnAtBats()+ AtBatSum;
		BaseSum = player[i].ReturnBases() + BaseSum;
		JOptionPane.showMessageDialog(null, player[i].toString());
		}
	for(int i = 0; i < player.length; i++)
	{
	  Player compare;
		for (int j = i+1; j < player.length; j++)
		{

			if(player[j].CalculateSlugPercent() > player[i].CalculateSlugPercent())
				{
				compare = player[j];
				player[j] = player[i];
				player[i] = compare;
				}
		}
	}
	JOptionPane.showMessageDialog(null, "Click Okay and check the console for a sorted list of slug percentages\n and weighted percentages");

	for(int i=0; i < player.length; i++ )
	{
		System.out.println(player[i].toString());
		System.out.println(" ");
	}
double WeightedAvg = (double)BaseSum/AtBatSum;
System.out.println("The Weighted Average is: " + WeightedAvg);
	}

}
