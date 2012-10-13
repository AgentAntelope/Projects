package MergeSort;
public class MergeSort
{
    public static Object[] b;    // Holds the temporary array           

    public static<T extends Comparable<T>> void mergeSort(T[] a)
    {
        int n=a.length;
        b =new Object[n/2+1];  
        //Changed n to n/2 since it seems more logical to have half the array in the temporary
        //array.
        mergesort(a,0, n-1);
    }

    private static<T extends Comparable<T>> void mergesort(T[]a,int low, int high)
    {
        if (low<high) //Base case is the non-existent else statement so it can propogate upwards
        {
            int middle=(low+high)/2;
            mergesort(a,low, middle); 
            //Cuts the array into smaller bits starting from lower end, to middle recursively
            mergesort(a,middle+1, high);
            //Cuts the array into smaller bits starting from middle + 1 to end, recursively
            merge(a , low, middle, high);
            //Puts the pieces back together
        }
    }

private static<T extends Comparable<T>> void merge(T[]a,int low, int middle, int high)
    {
        int tempArrayIter, mainArrayValueIter, rightValuePlace;
/*
 * Use tempArrayIter for iteration through the temporary array.
 * Use mainArrayValueIter to start at the low value and use it to iterate halfway through
 * array B. Then use it as the middle value for the Main Array A(hence the name)
 */
        tempArrayIter=0; mainArrayValueIter=low;
        // copies first half of array a to the temporary array b
        while (mainArrayValueIter<=middle)
        {
            b[tempArrayIter]=a[mainArrayValueIter];
            mainArrayValueIter++;
            tempArrayIter++;
        }

        tempArrayIter=0; rightValuePlace=low;
        
        while (rightValuePlace<mainArrayValueIter && mainArrayValueIter<=high)
        {
        	/*
        	 * Now that the first half of the array is in Array B, we start to compare the
        	 * 2 values. If the starting numbers of B(using tempArrayIter for iteration) is 
        	 * less than the halfway values of A(using mainArrayValueIter for iteration).
        	 *  We place them in the right spots using rightValuePlace.
        	 * 
        	 */
        	T temp = (T)b[tempArrayIter];
        
            if ((temp).compareTo(a[mainArrayValueIter]) < 0)
            {
                a[rightValuePlace]=(T)b[tempArrayIter];
                rightValuePlace++;
                tempArrayIter++;
            }
            else
            {
                a[rightValuePlace]=a[mainArrayValueIter];
                rightValuePlace++;
                mainArrayValueIter++;
            }
        }
        /* copy back remaining elements of Temporary Array (if any) if
         * rightValuePlace doesn't equal mainArrayValueIter, then there are elements
         * in the temporary array that are larger than the elements we already sorted,
         * and there is no need to compare so we should just put them in iteratively.
         *
         */
        while (rightValuePlace < mainArrayValueIter)
        {
            a[rightValuePlace]=(T)b[tempArrayIter];
            tempArrayIter++;
            rightValuePlace++;
        }
    }

}  
