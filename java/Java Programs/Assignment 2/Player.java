import javax.swing.JOptionPane;

public class Player
{
	//All the variables
private int Hits, singleHit, doubleHit, tripleHit, homeRun, checkInput, bases, AtBats;
private String fName;

//Asks for the name of the player
public void Name()
{
	 fName = JOptionPane.showInputDialog("What is your name?");
}


//Asks for how many at bats, checks to see if value entered is negative
//if it is, it will re-run the method
public void InputBat()
{
	String A=JOptionPane.showInputDialog(" How many at bats have you had this year?");
AtBats= Integer.parseInt(A);
if (AtBats < 0)
{
	JOptionPane.showMessageDialog(null, "You have entered a negative number, please try again");
	InputBat();
}
}

//Starts the procedural code, first Singles, then doubles, triples, lastly home runs.
//Each checks to see if it is right than the number hit and will re-run the method
//if wrong
public void InputSingle()
	{
	String A = JOptionPane.showInputDialog("How many singles did you have?");
	singleHit = Integer.parseInt(A);
	checkInput = singleHit;
	if(checkInput > AtBats || singleHit < 0)
	{
		JOptionPane.showMessageDialog(null, "You have input a wrong number, please try again");
		InputSingle();
	}

	}
public void InputDouble()
{
	String A = JOptionPane.showInputDialog("How many doubles did you have?");
	doubleHit = Integer.parseInt(A);	
	checkInput = singleHit + doubleHit;
	if(checkInput > AtBats || doubleHit < 0)
	{
		JOptionPane.showMessageDialog(null, "You have input a wrong number, please try again");
		InputDouble();
	}

}
public void InputTriple()
{
	String A = JOptionPane.showInputDialog("How many triples did you have?");
	tripleHit = Integer.parseInt(A);	
	checkInput = singleHit + doubleHit+ tripleHit;
	if(checkInput > AtBats || tripleHit < 0)
	{
		JOptionPane.showMessageDialog(null, "You have input a wrong number, please try again");
		InputTriple();
	}
}
public void InputHomerun()
{
	String A = JOptionPane.showInputDialog("How many homeruns did you have?");
	homeRun = Integer.parseInt(A);		
	checkInput = singleHit + doubleHit+ tripleHit+ homeRun;
	if(checkInput > AtBats || homeRun < 0)
	{
		JOptionPane.showMessageDialog(null, "You have input a wrong number, please try again");
		InputHomerun();
	}
}
public double CalculateSlugPercent()
{
	double slug;
	bases = singleHit+(doubleHit*2)+(tripleHit*3) + (homeRun*4);
	slug = (double)bases/AtBats;
	return slug;
}
public int ReturnBases()
{
	return bases;
}
public int ReturnAtBats()
{
	return AtBats;
}
public String toString()
{
	StringBuffer S = new StringBuffer();
	
	double slugFrom = CalculateSlugPercent();
	S.append(fName+" stats are: \n");
	S.append("At bats: " + AtBats +"\n");
	S.append("Singles: " + singleHit +"\n");
	S.append("Doubles: " + doubleHit+ "\n");
	S.append("Triples: " + tripleHit+ "\n");
	S.append("Home Runs: "+ homeRun+ "\n");
	S.append("Slug Percentage: " + slugFrom + "\n");
	
	return S.toString();
}

}
