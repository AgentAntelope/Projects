package LinkedList;

public class Linked {
public class Node{
	public int contents;
	public Node next;
 }

public Node head;
public Linked()
	{
	head = new Node();
	}
public void add(int c){
	Node newNode = new Node();
	newNode.contents = c;
	newNode.next = head.next;
	head.next = newNode;
}
public String toString()
{
	String Result = "";
	Node Current = head;
	while(Current.next!= null)
	{
		Result += Current.next.contents + "\n";
		Current = Current.next;
	}
	return Result;
}
public static void main(String[]Args)
	{ 
	Linked L = new Linked();
	L.add(10);
	L.add(20);
	L.add(30);
	System.out.println(L);
	}
}
