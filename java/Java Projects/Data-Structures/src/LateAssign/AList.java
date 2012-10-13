package LateAssign;
/* AList with inner-class iterator */
import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AList<T> implements ListInterface<T>, Serializable
{
    private T[] entry; // array of list entries
    private int length;     // current number of entries in list
    private static final int MAX_SIZE = 10; // Initial size

    public AList()
    {
        length = 0;
        entry = (T[])(new Object[MAX_SIZE]);
    } // end default constructor

    public AList(int maxSize)
    {
        length = 0;
        entry = (T[])(new Object[maxSize]);
    } // end constructor


    /** Task: adds an element to the list when the list is not full but doubles the size of the list when full then
      * adds the element
      */
    public boolean add(T newEntry)
    {
        if (isArrayFull())
            doubleArray();

        // add new entry after last current entry
        entry[length] = newEntry;
        length++;

        return true;
    } // end add



    /** Task: Doubles the size of the array of list entries. */
    private void doubleArray()
    {
        T[] oldList = entry;      // save reference to array of
                                       // list entries
        int oldSize = oldList.length;  // save old max size of array

        entry = (T[])(new Object[2*oldSize]); // double size of array

        // copy entries from old array to new, bigger array
        for (int index = 0; index < oldSize; index++)
            entry[index] = oldList[index];
    } // end doubleArray


    private boolean isArrayFull()
    {
        return isFull();
    }


    //Need to change this function to double the size of array when isFull
    public boolean add(int newPosition, T newEntry)
    {
        boolean isSuccessful = true;

        if (isArrayFull())
            doubleArray();

        if ((newPosition >= 1) && (newPosition <= length+1))
        {
            makeRoom(newPosition);
            entry[newPosition-1] = newEntry;
            length++;
        }
        else
            isSuccessful = false;

        return isSuccessful;
    } // end add



    /** Task: Makes room for a new entry at newPosition.
     *  Precondition: 1 <= newPosition <= length+1;
     *                length is list's length before addition. */
    private void makeRoom(int newPosition)
    {
        // move each entry to next higher position, starting at end of
        // list and continuing until the entry at newPosition is moved
        for (int index = length; index >= newPosition; index--)
            entry[index] = entry[index-1];
    } // end makeRoom


    public T remove(int givenPosition)
    {
        T result = null;  // return value

        if ((givenPosition >= 1) && (givenPosition <= length))
        {
            result = entry[givenPosition-1]; // get entry to be removed

            // move subsequent entries toward entry to be removed,
            // unless it is last in list
            if (givenPosition < length)
                removeGap(givenPosition);

            length--;
        } // end if

        return result;  // return reference to removed entry,
                        // or null if givenPosition is invalid
    } // end remove



    /** Task: Shifts entries that are beyond the entry to be removed
     *        to next lower position.
     *  Precondition: 1 <= givenPosition <= length;
     *                length is listâs length before removal. */
    private void removeGap(int givenPosition)
    {
        for (int index = givenPosition; index < length; index++)
        entry[index-1] = entry[index];
    } // end removeGap



    public boolean replace(int givenPosition, T newEntry)
    {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= length))
            entry[givenPosition-1] = newEntry;
        else
            isSuccessful = false;

        return isSuccessful;
    } // end replace



    public T getEntry(int givenPosition)
    {
        T result = null;  // result to return

        if ((givenPosition >= 1) && (givenPosition <= length))
            result = entry[givenPosition-1];

        return result;
    } // end getEntry



    public boolean contains(T anEntry)
    {
        boolean found = false;
        for (int index = 0; !found && (index < length); index++)
        {
            if (anEntry.equals(entry[index]))
                found = true;
        } // end for

        return found;
    } // end contains



    /** Task: Removes all entries from the list. */
    public void clear()
    {
        length = 0;
    }



    public int getLength()
    {
        return length;
    } // end getLength



    public boolean isEmpty()
    {
        return length == 0;
    } // end isEmpty



    public boolean isFull()
    {
        return length == entry.length;
    } // end isFull



    public void display()
    {
        if(isEmpty())
           System.out.print("[]");
        else{
           System.out.print("[");

           for (int index = 0; index < length-1; index++)
               System.out.print(entry[index] + ",");

           System.out.print(entry[length-1] + "]");
        }
    } // end display


     public Iterator<T> getIteratorForAList(){
         return new IteratorForAList();
     }


    private class IteratorForAList implements Iterator<T>{
       private int nextPosition;
       private boolean wasNextCalled;

       public IteratorForAList(){
           nextPosition = 0;
           wasNextCalled = false;
       }


       public boolean hasNext(){
              return nextPosition != length;
       }


       public T next(){
              if(hasNext()){
                  nextPosition++;
                  wasNextCalled = true;
                  return entry[nextPosition-1];
              }
              else{
                  throw new NoSuchElementException();
              }
       }


       public void remove(){
              if(wasNextCalled){
                  wasNextCalled = false;
                  AList.this.remove(nextPosition);
                  nextPosition--;
              }
              else
                 throw new IllegalStateException();
       }

    }
} // end AList