
public class HangPlayer {
private int losses = 0;
private int wins = 0;
private String name;

public void SetName(String name)
{
	this.name = name;
}
public String GetName()
{
	return name;
}

public void IncrLosses()
{
	losses++;
}
public void IncrWins()
{
	wins++;
}
public String Results()
{
	StringBuffer Results = new StringBuffer();
	Results.append("Final Results: \n");
	Results.append("Name:" + name + "\n");
	Results.append("Wins: "+ wins +"\n");
	Results.append("Losses: "+ losses+ "\n");
	
	return Results.toString();

}
}
