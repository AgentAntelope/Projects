package Assignment3;

public class HangPlayer {
private int losses;
private int wins;
private String name;

public void SetName(String name)
{
	this.name = name;
}

public void IncrLosses()
{
	losses++;
}
public void IncrWins()
{
	wins++;
}
public void Results()
{
	System.out.println("Final Results:");
	System.out.println("Name:" + name);
	System.out.println("Wins: "+ wins);
	System.out.println("Losses: "+ losses);
	System.out.println("");
	System.out.println("THANKS FOR PLAYING!!");
}
}
