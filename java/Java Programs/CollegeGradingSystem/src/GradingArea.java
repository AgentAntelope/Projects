import java.util.ArrayList;


public class GradingArea {
private int overallGradeWorth, currentPercent, totalPointsInArea, totalPointsReceived, assignNum;
private ArrayList<Assignment> Assignments;

	public GradingArea(int Percent)
	{
		Assignments = new ArrayList<Assignment>();
		overallGradeWorth = Percent;
		currentPercent = 0;
		totalPointsInArea = 0;
		totalPointsReceived = 0;
	}


	public void add(Assignment X)
	{
		Assignments.add(X);
		/*
		totalPointsInArea +=X.gradeTotal;
		totalPointsReceived += X.gradeReceived;
		assignNum = Assignments.size();
		*/
	}
	public void calculatePercents()
	{
		
	}

	
}
