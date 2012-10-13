package Lab9;
// CS 0401 Fall 2009
// Driver Program for Lab9.  This program should run with the supplied
// SimpleDeque interface and the MyDeque class that you complete.

public class Lab9
{
	public static void main(String [] args)
	{
		SimpleDeque things = new MyDeque(8);

		doQueue1(things);
		doQueue2(things);
		doStack(things);
	}

	public static void doQueue1(SimpleDeque S)
	{
		System.out.println("Queue adds at rear and removes at front");
		for (int i = 0; i < 5; i++)
			S.addRear(new Integer(i));
		Object item = S.removeFront();
		while (item != null)
		{
			System.out.print(item + " ");
			item = S.removeFront();
		}
		System.out.println();
		System.out.println();
	}

	public static void doQueue2(SimpleDeque S)
	{
		System.out.println("Queue adds at front and removes at rear");
		for (int i = 0; i < 10; i++)
			S.addFront(new Double(2*i));
		Object item = S.removeRear();
		while (item != null)
		{
			System.out.print(item + " ");
			item = S.removeRear();
		}
		System.out.println();
		System.out.println();
	}

	public static void doStack(SimpleDeque S)
	{
		System.out.println("Stack adds and removes at rear");
		String [] names = {"Herb", "Bertha", "Ingrid", "Ingmar", "Marge"};
		for (int i = 0; i < names.length; i++)
			S.addRear(names[i]);
		Object item = S.removeRear();
		while (item != null)
		{
			System.out.print(item + " ");
			item = S.removeRear();
		}
		System.out.println();
		System.out.println();
	}
}

