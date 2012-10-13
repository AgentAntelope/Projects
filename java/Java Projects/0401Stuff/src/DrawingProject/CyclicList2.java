import java.util.*;

public class CyclicList<E>{
	ArrayList<E> array;

	public static void main(String[] args){
		ArrayList<E> a = new ArrayList<>();
	}
	public CyclicList<E>(ArrayList<E> arr){
		array = arr;
	}
	public void shiftLeft(){
		E temp = array.remove(array.size() - 1);
		array.add(0, temp);
	}
	public <E> void shiftRight(){
		E temp = array.remove(0);
		array.add(temp);
	}
}