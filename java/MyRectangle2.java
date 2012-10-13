// CS 401 Lab 5
// Fill in the indicated code sections to complete this class.  Then test it
// by compiling and executing Lab5.java.

public class MyRectangle
{
	// Declare instance variables here
	private int x, y, w, h;

	public MyRectangle()
	{
		// Default constructor -- initialize all instance variables
		// to 0
		x = 0;
		y = 0;
		w = 0;
		h = 0;
	}

	public MyRectangle(int x, int y, int w, int h)
	{
		// Initialize instance variables based on parameters shown.
		// Be careful to get the order correct!
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public int area()
	{
		return w * h;
		// Return the area of this Rectangle
	}

	// I have written this method for you.  Note how a StringBuilder is
	// utilized, since (as we discussed in lecture) it can be modified
	// without having to create a new object each time (unlike a String).
	public String toString()
	{
		return "";
	}

	public boolean isInside(int x, int y)
	{
		return x <= (this.x + w) && x >= this.x && y <= (this.y + h) && y >= this.y;
		// This is the trickiest of the methods to write.  This should
		// return true if point (x,y) is anywhere within the borders of the
		// current MyRectangle (including the borders themselves).  Use a
		// pencil and paper to figure out how this can be determined with
		// just a few simple relational operations.
	}

	public void setSize(int w, int h)
	{
		// Update width and height as shown
		this.w = w;
		this.h = h;
	}

	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
		// Update startX and startY as shown
	}
	public int getY(){
		return this.y;
	}

	public int getX(){
		return this.x;
	}
	public int getHeight(){
		return this.h;
	}

	public int getWidth(){
		return this.w;
	}
}