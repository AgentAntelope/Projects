package Handouts;
/* CS 0401 Fall 2009 Java Example 4b (compare to Example 4a)
   This handout demonstrates some Java control statements, including if statement
   and while loop.  It will read in some scores from a user and then calculates
   the average.

   In this version, the user does not know the number of scores in
   advance, so a sentinel is used to indicate end of the data.  Note that the
   way this version is written, as soon as an invalid score is entered the
   input stops -- unlike 4a which forces the user to re-enter invalid scores.
   Think about how you would allow both an indefinite number of scores and
   force the user to renter invalid scores.
*/
import java.util.Scanner;

public class ex4b
{
	public static void main (String [] args)
	{
		Scanner inScan = new Scanner(System.in);

		int numItems = 0;
		double currItem = 0.0;
		double sum = 0.0, ave;

		System.out.println("Enter the next item (or invalid value to quit) ");
		currItem = inScan.nextDouble();

		while (currItem >= 0.0 && currItem <= 100.0)
		{
			sum += currItem;
			numItems++;
			System.out.println("Enter the next item (or invalid value to quit) ");
			currItem = inScan.nextDouble();
		}

		if (numItems > 0)
		{
			ave = sum / numItems;
			System.out.println("The average is " + ave);
		}
		else
			System.out.println("No data was entered");
	}

}