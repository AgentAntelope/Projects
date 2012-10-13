/**  @Author: Sean Myers (Iph)
 *  Email: Seanmyers0608@gmail.com
 *  Project: NetworkFlow
 *  
 *  Note to anyone grading: Dr. Aronis wants us to implement our own
 *  queue but we are allowed to use an ArrayList...so..I used an ArrayList. 
 *  I could have used a LinkedList but seeing as how I am not one to 
 *  recreate the wheel,this should do :)
 */
import java.util.ArrayList;

public class netQueue<T> {
	ArrayList<T> a;
	public netQueue(){
		a = new ArrayList<T>();
	}
	public void push(T b){
		a.add(b);
	}
	public T pop(){
		return a.remove(0);
	}
	public boolean isEmpty(){
		if(a.isEmpty()){
			return true;
		}
		return false;
	}
}
