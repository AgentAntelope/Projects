// CS 401 Lab 5
// Fill in the indicated code sections to complete this class.  Then test it
// by compiling and executing Lab5.java.

public class MyRectangle
{
	// Declare instance variables here

	public MyRectangle()
	{
		// Default constructor -- initialize all instance variables
		// to 0
	}

	public MyRectangle(int x, int y, int w, int h)
	{
		// Initialize instance variables based on parameters shown.
		// Be careful to get the order correct!

	}

	public int area()
	{
		// Return the area of this Rectangle
	}

	// I have written this method for you.  Note how a StringBuilder is
	// utilized, since (as we discussed in lecture) it can be modified
	// without having to create a new object each time (unlike a String).
	public String toString()
	{
	}

	public boolean isInside(int x, int y)
	{
		// This is the trickiest of the methods to write.  This should
		// return true if point (x,y) is anywhere within the borders of the
		// current MyRectangle (including the borders themselves).  Use a
		// pencil and paper to figure out how this can be determined with
		// just a few simple relational operations.
	}

	public void setSize(int w, int h)
	{
		// Update width and height as shown
	}

	public void setPosition(int x, int y)
	{

		// Update startX and startY as shown
	}
	public int getY(){
	}

	public int getX(){
	}
	public int getHeight(){
	}

	public int getWidth(){
	}
}