/* CS0401 Fall 2009
   Java Example 23 -- Simple introduction to Java exceptions
*/

import java.io.*;   // needed for BufferedReader class

public class ex23
{
	static int getint1(BufferedReader str)
	{
		int val;           // Function to get an int from standard input.
		String instring;   // The IOexception must be caught or propagated (it is
		while (true)       // a checked exception) although in this case it will
		{                  // likely never be thrown (something must go wrong with
			// the IO itself).  The NumberFormatException can easily be thrown
			// though, by typing in a bogus integer.  When an exception is thrown,
			// the rest of the try block is abandoned and control reverts to the
			// approp. catch block.  Embedding the try within a loop allows
			// error-checking input.

			try
			{
				instring = str.readLine();    // read in a string
				val = Integer.parseInt(instring);  // convert to int
				break;  // break out of loop -- everything worked
			}
			catch (IOException e1)
			{
				System.out.println("IO problem!");
			}
			catch (NumberFormatException e2)
			{
				System.out.println("Not an integer, please re-enter!");
				//e2.printStackTrace();
			}
		}
		return val;
	}

    static int getint2(BufferedReader str) throws IOException
    {
         String instring = str.readLine();
         int val = Integer.parseInt(instring);
         System.out.println("About to complete getint2");
         return val;
    }

	static void testEx(BufferedReader str) throws IOException
		// IOException is a CHECKED exception in Java.  This means
		// that it either must be caught or stated to be propagated
		// by any method in which it can be thrown.  NumberFormat-
		// Exception is a RunTimeException -- these do NOT have to
		// be explicitly stated to be propagated (as shown in
		// getint2 above)
	{
		try
		{
			System.out.println("About to call getint2 from testEx");
			int i = getint2(str);
			System.out.println("You entered " + i);
		}
		catch (NumberFormatException e)
		{
			System.out.println("Caught exception " + e + " in testEx");
		}
		finally
		{
			System.out.println("Executing finally in testEx");
		}
	}

	public static void main(String [] argv)
	{
		// Up until now we have been (mostly) reading in data from the
		// keyboard via a Scanner object.  However, it can also be read in
		// using a BufferedReader object.  See more details on the
		// BufferedReader class in the Java API.
		InputStreamReader ISR = new InputStreamReader(System.in);
		BufferedReader BR = new BufferedReader(ISR);

		try
		{
			int [] A = new int[10];

			System.out.println("About to call getint1 from main");
			int i1 = getint1(BR);
			System.out.println("You entered " + i1);
			System.out.println("About to call getint2 from main");
			int i2 = getint2(BR);
			System.out.println("You entered " + i2);
			System.out.println("About to call testEx from main");
			testEx(BR);
			System.out.println("No exception back in main");

			System.out.println("Assigning to index " + i1 + " of "
				+ "array of size " + A.length);
			A[i1] = i1;
			System.out.println("Main about to end");
		}
		catch (Exception e)  // catch any exception e here
		{
			System.out.println();
			System.out.println("Exception " + e);  // let's find out
			e.printStackTrace(System.out);         // about it
			System.out.println("Caught it in main");
		}
		finally
		{
			System.out.println("Done with main!");
		}
	}
}