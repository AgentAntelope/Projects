package ProjectBag;

import java.util.Iterator;

public class LBag<E> implements BagInterface<E>, Iterable<E> {

	private Node Head;
	private int Length;
	private class Node{
		E Data;
		Node next;
		int Frequency;
		public Node(E data){
			Data = data;
		}
		public Node(E data, int frequency){
			Frequency = frequency;
			Data = data;
		}

	}
	public LBag(){
		Head = null;
		Length = 0;
	}
	@Override
	public boolean add(E item) {
		Node newNode = new Node(item);

		if(isEmpty()){
		Head = newNode;
		Length++;
		}
		else{
			newNode.next = Head;
			Head = newNode;
			Length++;
		}
		return true;
	}

	@Override
	public boolean contains(E item) {
		Node Iter = Head;
		
		while(Iter != null){
			if(Iter.Data.equals(item)){
				return true;
			}
			else{
				Iter = Iter.next;
			}
		}
		return false;
	}

	@Override
	public int getAddress(E item) {
	Node Iter = Head;
	int currLength = 0;	
		while(Iter != null){
			if(Iter.Data.equals(item)){
				return currLength;
			}
			else{
				Iter = Iter.next;
				currLength++;
			}
		}
		return -1;
	}

	@Override
	public int getFrequency(E item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFrequency(int address) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E getItem(int address) {
		Node begin = Head;
		for(int i = 0; i < address; i++ ){
			begin = begin.next;
		}
		return begin.Data;
	}

	@Override
	public boolean isEmpty() {
		if(Head == null)
		return true;
		else
			return false;
	}

	@Override
	public int length() {
		return Length;
	}

	@Override
	public E remove(E item) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void replaceCount(E item, int freq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedIterator(Head);
	}
	private class LinkedIterator implements Iterator<E>{
		Node start;
		public LinkedIterator(Node head){
			start = head;
		}
		public boolean hasNext() {
			if(start == null)
				return false;
			else{
				return true;
			}
		}

		@Override
		public E next() {
			E send = start.Data;
			start = start.next;
			return send;
		}

		@Override
		public void remove() {}
		
	}

}
