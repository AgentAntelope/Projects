package MazeRunner;

public class Point implements Comparable{
public int x;
public int y;
public int distanceToGoal;

//Param: Point(y,x) still creates x,y right..
public Point(int rows, int columns)
{
	x = columns;
	y = rows;
}

public void setDistance(Point goal) {
	distanceToGoal =Math.abs(x-goal.x) + Math.abs(y-goal.y);
}

public int compareTo(Object Point2) {
	Point point2 = (Point)Point2;
	int compareDistance = point2.distanceToGoal - this.distanceToGoal;
	return compareDistance;
}

public boolean equals(Point p)
{
	if(this.x == p.x && this.y == p.y)
		return true;
	else
		return false;
}

}
