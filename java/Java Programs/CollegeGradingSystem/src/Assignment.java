
public class Assignment {
	public int gradeReceived, gradeTotal;
	public String assignName;
	public boolean isChangeable, canBeRemoved; //Might be used later in the program
	
	public Assignment(int Received, int Total, String Name)
	{
		gradeReceived = Received;
		gradeTotal = Total;
		assignName = Name;
	}
	
	public Assignment(int Received, int Total)
	{
		this(Received, Total, " ");
	}
	
	public Assignment(int Total)
	{
		this(0, Total, " ");
	}
}
