
public class MainNodeTest {

	public static void main(String[]Args)
	{
		Node head = new Node("A", null);
		 head = new Node("B", head);
		head = new Node("C", head);
		head.next = new Node("D", head.next);
		System.out.println(head.next.next.Contents);

	}
}
