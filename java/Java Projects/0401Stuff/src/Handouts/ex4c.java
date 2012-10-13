package Handouts;
/* CS 0401 Fall 2009 Java Example 4c (compare to Example 4a)
   This handout will also read in some scores from a user and then determine the
   average.

   In this version, the user knows how many scores there are and the number of
   scores is read in initially.  However, rather than a while loop, which was used
   in ex4a, we are using a for loop.

   I have also included a do-while loop, to show a use for that control structure,
   and to show an alternative way of ensuring data in the correct range.  Note
   that ex4a has a single loop, with the count only being incremented if the data
   is correct.  Thus, the loop could execute more than numItems times.

   In this version, the outer loop will execute exactly numItems times.  Incorrect
   data is handled by an inner do-while loop, which iterates until the data is
   correct.
*/
import java.util.Scanner;

public class ex4c
{
	public static void main (String [] args)
	{
		Scanner inScan = new Scanner(System.in);

		int numItems = 0;
		double currItem = 0.0;
		double sum = 0.0, ave;

		System.out.println("How many scores are there?");
		numItems = inScan.nextInt();

		for (int count = 1; count <= numItems; count++)
		{
			// A do loop always executes the loop body at least one time.
			// It is good for reading input and ensuring that it is
			// correct, as shown below.
			do
			{
				System.out.println("Enter item " + count + " : ");
				currItem = inScan.nextDouble();
			}
			while (currItem < 0.0 || currItem > 100.0);  // Compare this
			    // condition to the condition tested in the if statement
				// in ex4a.  In ex4a, we were testing to see if the data
				// was valid, while here we are testing to see if it is
				// NOT valid.  Thus the conditions are the opposite of
				// each other.  By DeMorgan's Law,
				// !(A && B) == (!A) || (!B)
				// With some examination you should see that the if
				// condition in ex4a and the condition above demonstrate
				// DeMorgan's Law

			sum += currItem;
		}

		if (numItems > 0)  // make sure we don't divide by zero
		{
			ave = sum / numItems;
			System.out.println("The average is " + ave);
		}
		else
		System.out.println("No data was entered");
	}

}