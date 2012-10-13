package MazeRunner;

public class PriorityQueue<T extends Comparable> {
	private T[] heap;
	 private int index;
	 
	@SuppressWarnings("unchecked")
	public PriorityQueue () { 
	        heap = (T[]) new Comparable[20]; 
	        index = 0; 
	    } 

	    public boolean isEmpty () { 
	        return index == 0; 
	    } 
	    
	    public void add (T item) { 

	        heap[index] = item; 
	        index++; 
	    } 
	    public T remove () { 
	        if (index == 0) return null; 

	        int maxIndex = 0; 

	        // find the index of the item with the highest priority 
	        for (int i=1; i<index; i++) { 
	            if (heap[i].compareTo(heap[maxIndex]) > 0) { 
	                maxIndex = i; 
	            } 
	        } 
	        T result = heap[maxIndex]; 

	        // move the last item into the empty slot 
	        index--; 
	        heap[maxIndex] = heap[index]; 
	        return result; 
	   } 

}
