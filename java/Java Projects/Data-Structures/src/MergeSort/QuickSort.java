package MergeSort;

public class QuickSort<T extends Comparable<T>> {
 
	
    public static<T extends Comparable<T>> void quickSort( T [ ] a ) {
        quicksort( a, 0, a.length - 1 );
    }
    
    

    private static<T extends Comparable<T>> void quicksort( T[ ] a, int low, int high ) {
    	/*
    	 * So, this is what we are going to try to do here:
    	 * I decided to constantly use a swapElements unit since I found it easier to work
    	 * with and VERY reusable. First, I recursively call this till about 5 elements are
    	 * left in a partition, then I use insertionSort to organize the rest. This way I can
    	 * constantly use Median of 3, till the base case. I arbitrarily made it 5.
    	 */
    	if( low + 5 > high )
            insertionSort( a, low, high );
        else {
            // Median of 3
            int middle = ( low + high ) / 2;
            if( a[ middle ].compareTo( a[ low ] ) < 0 )
                swapElements( a, low, middle );
            if( a[ high ].compareTo( a[ low ] ) < 0 )
                swapElements( a, low, high );
            if( a[ high ].compareTo( a[ middle ] ) < 0 )
                swapElements( a, middle, high );
            
            // Place pivot at position high - 1
            swapElements( a, middle, high - 1 );
            T pivot = a[ high - 1 ];
            
            // Begin partitioning
            
            /*
             * What's going on here:
             * Make it so that you set i to the lowest and j to the high.
             * First, find the first element that is higher than the pivot.
             * Then, find an element on the higher side that is higher than the pivot.
             * Swap them. 
             * Do this till the Increments meet up.
             */
            int i, j;
            for( i = low, j = high - 1; ; ) {
                while( a[ ++i ].compareTo( pivot ) < 0 )
                    ;
                while( pivot.compareTo( a[ --j ] ) < 0 )
                    ;
                if( i >= j )
                    break;
                swapElements( a, i, j );
            }
            
            // Restore pivot
            swapElements( a, i, high - 1 );
            
            quicksort( a, low, i - 1 );    // Sort small elements
            quicksort( a, i + 1, high );   // Sort large elements
        }
    }
    
 
    public static<T extends Comparable<T>>  void swapElements( T [ ] a, int index1, int index2 ) {
        T temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
    

    private static<T extends Comparable<T>> void insertionSort( T [ ] a, int low, int high ) {
        for( int i = low + 1; i <= high; i++ ) {
            T temp = a[i];
            int j;
            
            for( j = i; j > low && temp.compareTo( a[j-1] ) < 0; j-- )
                a[j] = a[j-1];
            a[j] = temp;
        }
    }
}
