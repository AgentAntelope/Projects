package ProjectBag;

public interface BagInterface<E>{
	   //returns true if this Bag is empty; otherwise, returns false.
	   public boolean isEmpty();

	   //Returns the number of distinct objects in this Bag. When a bag is 
	   //empty 0 is returned.
	   public int length();

	   //If item is not in this Bag, add the item to the bag and increase 
	   //the length of this Bag by 1; otherwise, increase the frequency 
	   // of the item by 1. Return true on a successful operation.
	   public boolean add(E item);

	   //If the item is in this Bag and its frequency > 1, decrease the 
	   //frequency by 1; otherwise, remove the item from this Bag. In 
	   //either case, return a reference to this item back to the caller.
	   //If the item is not in this Bag return null.
	   public E remove(E item);

	   //Returns true whenever the item is in this Bag; otherwise false.
	   public boolean contains(E item);

	   //Returns the address (an integer) of the item in this Bag. If the 
	   //item is not in this Bag return -1. A valid address is >= 0 and < 
	   //length().
	   public int getAddress(E item);

	   //Returns a reference to the item in this Bag with the specified 
	   //address; otherwise, returns null if the address is invalid. A 
	   //valid address is >= 0 and < length().
	   public E getItem(int address);

	   //It the item is in this Bag returns the frequency of the item. If 
	   //the item is not in this Bag return 0.
	   public int getFrequency(E item);

	   //Returns the frequency of the item stored at this address provided 
	   //the address is valid, otherwise, return -1.
	   public int getFrequency(int address);

	   //Replaces the item in the Bag with the given frequency. If the item 
	   //is not in the Bag, add this item with the given frequency. 
	   public void replaceCount(E item, int freq);
	}
