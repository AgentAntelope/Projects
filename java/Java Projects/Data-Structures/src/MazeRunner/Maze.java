package MazeRunner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class Maze{
	
	
public int columns;
public int rows;
private ArrayList<String> Lines;
public char[][] MazeMap;
private Point Current;

public Maze(String fileName) 
	{
	Lines = new ArrayList();
	try{
	rows = 0;
	Scanner fileReader = new Scanner(new FileInputStream(fileName));
	while(fileReader.hasNext())
		{
		Lines.add(fileReader.nextLine());
		rows++;
		}
	
	columns = Lines.get(0).length();
	MazeMap = new char[rows][columns];
	for(int i = 0; i < rows; i++)
		{
		for(int j = 0; j < columns; j++)
		{
			MazeMap[i][j]= Lines.get(i).charAt(j);
		if(MazeMap[i][j] == 'S')
			Current = new Point(i,j);
		}
		}
	}
	catch(FileNotFoundException e){
		System.out.println("System could not find the file you entered");}
	

	//for(int i = 0; i < Lines.get(0).length(); i++)
		
	
	
	}
public Point getStart()
	{
	for(int i = 0; i < rows; i++)
	{
		for(int j = 0; j < columns; j++)
		{
			if(MazeMap[i][j] == 'S')
				return new Point(i,j);

		}
	}
	return null;
	}


public Point getGoal()
	{
	for(int i = 0; i < rows; i++)
		{
		for(int j = 0; j < columns; j++)
			{
		if(MazeMap[i][j] == 'G')
			return new Point(i,j);
			}
		}
	return null;
	}

public void setCurrent(Point current) {
	Current = current;
}
public boolean isOpen(Point p) {

	if(MazeMap[p.y][p.x] == ' ')
		return true;
	else
	return false;
}
public boolean isClosed(Point p) {
	if(MazeMap[p.y][p.x] == '*')
		return true;
	else
	return false;
}
public boolean isStart(Point p) {
	if(MazeMap[p.y][p.x] == 'S')
		return true;
	else
	return false;
}
public boolean isGoal(Point p) {
	if(MazeMap[p.y][p.x] == 'G')
		return true;
	else
	return false;
}
public boolean isVisited(Point p) {
	if(MazeMap[p.y][p.x] == 'V')
		return true;
	else
	return false;
}
public boolean isCurrent(Point p) {
	if(p.y == Current.y && p.x == Current.x)
		return true;
	else
	return false;
}
public void markVisited(Point current2) {
	if(MazeMap[current2.y][current2.x] == 'S')
	{}
	else
	MazeMap[current2.y][current2.x]= 'V';
}
public Point[] moves(Point current2) {
	ArrayList<Point> possibleMoves = new ArrayList<Point>();
	if(MazeMap[current2.y+1][current2.x]==' ' || MazeMap[current2.y+1][current2.x] == 'G')
		possibleMoves.add(new Point(current2.y+1,current2.x));
	if(MazeMap[current2.y-1][current2.x]==' ' || MazeMap[current2.y-1][current2.x] == 'G')
		possibleMoves.add(new Point(current2.y-1,current2.x));
	if(MazeMap[current2.y][current2.x+1]==' ' || MazeMap[current2.y][current2.x+1] == 'G')
		possibleMoves.add(new Point(current2.y,current2.x+1));
	if(MazeMap[current2.y][current2.x-1]==' ' || MazeMap[current2.y][current2.x-1] == 'G')
		possibleMoves.add(new Point(current2.y,current2.x-1));
	
	if(possibleMoves.size()==0)
		return null;
	Point[] a = new Point[possibleMoves.size()];
	for(int i = 0; i < possibleMoves.size(); i++)
		a[i]= possibleMoves.get(i);
	return a;
}


}