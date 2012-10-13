package Lab9;
// CS 0401 Fall 2009
// SimpleDeque interface
public interface SimpleDeque
{
	public void addFront(Object X); // Add Object X at front of list
	public void addRear(Object X);  // Add Object X at rear of list 
	// If array is full, add methods should do nothing

	public Object removeFront();  // Remove and return object at 
								  // front of list
	public Object removeRear();  // Remove and return object at
								 // rear of list
	// If the deque is empty, remove methods should do nothing
}