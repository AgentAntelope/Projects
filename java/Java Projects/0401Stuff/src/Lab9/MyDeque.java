package Lab9;
// CS 401 Fall 2009
// MyDeque class to implement a simple deque
// Complete the methods indicated.  Be careful about the
// special cases indicated.  See lab for details on how
// to implement the methods.

public class MyDeque implements SimpleDeque
{
	Object [] theData;
	int numItems;

	public MyDeque(int maxItems)
	{
		theData = new Object[maxItems];
		numItems = 0;
	}
	
	public void addFront(Object X)
	{
	
		if(numItems < theData.length)
		{
			for(int i = 0; i < theData.length; i++)
			{
				Object Temp = theData[i];
				theData[i] = X;
				X = Temp;
			}
			numItems++;
		}
		
		// Add new item at front of list (shifting old items
		// to right first).  If the list is full, do not add
		// the item (just do nothing).
	}

	public void addRear(Object X)
	{
		if(numItems < theData.length)
		{
				theData[numItems] =  X;
			numItems++;
		}
		// Add new item at rear of list.  If the list is full,
		// do not add the item (just do nothing).
	}

	public Object removeFront()
	{
	
		if(numItems > 0)
		{
			Object Temp = theData[0];
			for (int i = 0; i < theData.length-1; i++)
			{
				theData[i]=theData[i+1];
			}
			numItems--;
			return Temp;
		}
		// Remove and return front item from list, shifting remaining
		// items to the left to fill the spot.  If list is empty,
		// return null.
		return null;
	}

	public Object removeRear()
	{
		if(numItems > 0)
		{
		Object Temp = theData[numItems-1];
		theData[numItems-1] = null;
		numItems--;
		return Temp;
		}
		
		return null;
		// Remove and return rear item from list.  If list is empty,
		// return null.
	}
}
