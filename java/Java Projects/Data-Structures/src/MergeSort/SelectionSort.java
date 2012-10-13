package MergeSort;

public class SelectionSort {
	public static <T extends Comparable<T>> void selectionSort(T[] a) 
	{ 
	    for (int i=0; i < a.length; i++) 
	    { 
	        int min = i; 
	        T Temp = a[i]; 
	        for (int j = i + 1; j < a.length; j++)  
	        { 
	            if (a[j].compareTo(Temp) < 0) 
	            { 
	                min = j; 
	                Temp = a[j]; 
	            } 
	        } 
	        a[min] = a[i]; 
	        a[i] = Temp; 
	    } 
	}  
}
