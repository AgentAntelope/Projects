// CS 401 Fall 2009
// This is a modification of the SortAll class (which was a modification
// of Gaddis selectionSort) to incorporate parameterized generic types.  
// Note the difference in the method declarations from those in SortAll.java.
// This way seems (and is, at least syntactically) much more complicated.
// However, it is "safer" from a typing point of view.

public class SortAllT
{
   // Declaration that this method will work with any type that
   // extends Comparable.  The ? is a wildcard, indicating that
   // we are not specifying the actual type.  This allows super and
   // subclass objects to be sorted within the same array
   public static <T extends Comparable<? super T>> 
			void selectionSort (T[] array)
   {	
	  int startScan, index, minIndex;
      T minValue; 	// Note that T is now a type that can be used in
      				// this method

      for (startScan = 0; startScan < (array.length-1); startScan++)
      {
         minIndex = startScan;
         minValue = array[startScan];
         for(index = startScan + 1; index < array.length; index++)
         {
            if (array[index].compareTo(minValue) < 0)
            {
               minValue = array[index];
               minIndex = index;
            }
         }
         array[minIndex] = array[startScan];
         array[startScan] = minValue;
      }
 
   }
}
