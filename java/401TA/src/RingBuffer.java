
public class RingBuffer {
	Double[] buffer;
	int first;
	int last;
	RingBuffer(int capacity){
		buffer = new Double[capacity];
		first = last = 0;
		// create an empty ring buffer, with given max capacity   
	}

	int size(){
		int counter = 0;
		for(int i = 0; i < buffer.length; i++){
			if(buffer[i] != null){
				counter++;
			}
		}
		return counter;
		// return number of items currently in the buffer   
	}
	boolean isEmpty(){
		return size()==0;
		// is the buffer empty (size equals zero)?
	}

	boolean isFull(){
		return size()== buffer.length;
		// is the buffer full (size equals capacity)? 
	}

	void enqueue(double x){
		// add item x to the end 
		buffer[last] = x;
		last++;
		last %= buffer.length;
	}

	double dequeue(){
		// delete and return item from the front 
		double ret = buffer[first];
		buffer[first] = null;
		first++;
		first%=buffer.length;
		return ret;
	}

	double peek(){
		return buffer[first];
		// return (but do not delete) item from the front
	}
	public static void main(String[] args){
	       int N = 10;
	       RingBuffer buffer = new RingBuffer(N);
	        for (int i = 1; i <= N; i++) {
	           buffer.enqueue(i);
	        }      
	 
	       double t = buffer.dequeue();
	       buffer.enqueue(t);
	       System.out.println("Size after wrap-around is " + buffer.size());
	       while (buffer.size() >= 2) {
	           double x = buffer.dequeue();
	           double y = buffer.dequeue();
	          buffer.enqueue(x + y);
	       }
	       System.out.println(buffer.peek());
	}
}
