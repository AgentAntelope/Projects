import java.util.*;

public class CyclicList<E> extends ArrayList<E>{
	
	public static void main(String[] args){
		System.out.println("Hello world");
	}
	public void shiftLeft(){
		E temp = this.remove(this.size() - 1);
		this.add(0, temp)
	}
	public void shiftRight(){
		E temp = this.remove(0);
		this.add(temp)
	}

}