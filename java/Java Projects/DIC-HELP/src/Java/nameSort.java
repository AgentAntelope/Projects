package Java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class nameSort
{
   public static void main(String[] args)
      {
      Scanner console = new Scanner(System.in);
      try
      {
         Scanner in = new Scanner(new FileInputStream("names.txt"));
         PrintStream out1 = new PrintStream(new File("namesSorted.txt"));
         PrintStream out2 = new PrintStream(new File("namesReverseSorted.txt"));

         String [] stringArray = new String [10];
         int i = 0;
         int stringArraySize = 0;

         while(in.hasNext())
         {
           String name = in.nextLine();
           stringArray[stringArraySize] = name;
           stringArraySize++;
         }
         printArray(stringArray);
         sort(stringArray);
         for (int j = 0 ; j <stringArraySize;j++)
         {
         System.out.println( stringArray[j]);
         }

         out1.println(stringArray);


      }//end try

      catch(FileNotFoundException e)
      {
        System.err.println("Cannot find input file " );
        System.exit(1);   // abnormal termination status code
      }
      catch(IOException e)
      {
        System.err.println("Cannot open input file " );
        System.exit(2);
      }
   }

   /**
         Merges two adjacent subranges of an array
         @param a the array with entries to be merged
         @param from the index of the first element of the
            first range
         @param mid the index of the last element of the
            first range
         @param to the index of the last element of the
            second range
      */
      public static void merge(String[] a,
        int from,  int mid, int to)
      {  int n = to - from + 1;
            // size of the range to be merged

         // merge both halves into a temporary array b
         String[] b = new String[n];

         int i1 = from;
            // next element to consider in the first range
         int i2 = mid + 1;
            // next element to consider in the second range
         int j = 0;
            // next open position in b

         // as long as neither i1 nor i2 past the end, move
         // the smaller element into b
         while (i1 <= mid && i2 <= to)
         { 
        	 System.out.println(a[i1] + " " +  a[i2] + " " + i2);
        	 if (a[i1].compareTo(a[i2])<0)
            {  b[j] = a[i1];
               i1++;
            }
            else
            {  b[j] = a[i2];
               i2++;
            }
            j++;
         }

         // note that only one of the two while loops
         // below is executed

         // copy any remaining entries of the first half
         while (i1 <= mid)
         {  b[j] = a[i1];
            i1++;
            j++;
         }

         // copy any remaining entries of the second half
         while (i2 <= to)
         {  b[j] = a[i2];
            i2++;
            j++;
         }

         // copy back from the temporary array
         for (j = 0; j < n; j++)
            a[from + j] = b[j];
      }

      /**
         Sorts a range of an array, using the merge sort
         algorithm.
         @param a the array to sort
         @param from the first index of the range to sort
         @param to the last index of the range to sort
      */
      public static void mergeSort(String [] a, int from, int to)
      {  if (from == to) return;
         int mid = (from + to) / 2;
          // sort the first and the second half
         mergeSort(a, from, mid); 
         mergeSort(a, mid + 1, to);
         printArray(a);
         merge(a, from, mid, to);
      }

      /**
         Sorts an array, using the merge sort algorithm.
         @param a the array to sort
      */
      public static void sort(String[] a)
      {
    	  mergeSort(a, 0, a.length - 1);
   }
      public static void printArray(String[] s)
      {
    	  for(int i = 0; i < s.length; i++)
    	  {
    		  System.out.print(s[i] + ". ");
    	  }
    	  System.out.println();
      }


}