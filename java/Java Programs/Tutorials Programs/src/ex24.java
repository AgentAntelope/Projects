// CS 0401 Fall 2009
// Example to compare the sizes of files of text versus binary files of
// primitive types
// Also demonstrated here are two other useful items:
// 1) Utilizing command line arguments in Java (we used these already)
// 2) Generating random numbers (we used these already in Lab and Assig. 2)

import java.io.*;
import java.util.*;

public class ex24
{;
		DataOutputStream iBinOut = new DataOutputStream(new FileOutputStream(
			"intData.bin"));
		PrintWriter iTextOut = new PrintWriter(new FileOutputStream(
			"intData.txt"));

		// Create a new Random o
	public static void main(String [] args) throws Exception
	{
		// Create our output files -- two text and two binary.
		DataOutputStream dBinOut = new DataOutputStream(new FileOutputStream(
			"doubleData.bin"));
		PrintWriter dTextOut = new PrintWriter(new FileOutputStream(
			"doubleData.txt"))bject.  With no arguments to the constructor,
		// the object will be "seeded" by the current time of day.  The seed
		// is simply the value used to initialiate the pseudo-random
		// sequence.  Once we have the Random object we can use it to
		// generate various random values using the methods defined in the
		// Random class. See the API for more information.
		Random R = new Random();

		// The array args[] in the parameter list of the main method above
		// is simply an array of Strings, parsed using spaces as
		// separators.  Therefore, args[0] is the first argument, args[1]
		// is the second, and so on.  Since we want integers here, we must
		// use the Integer.parseInt() method to convert them
		int numValues = Integer.parseInt(args[0]);
		int intRange = Integer.parseInt(args[1]);

		for (int i = 0; i < numValues; i++)
		{
			double nextD = R.nextDouble();
			dBinOut.writeDouble(nextD);
			dTextOut.print(nextD + " ");  // When writing data as text, we
			// need to add a space so we can read it in
			// properly.  However, we DON'T need any newline
			// characters -- that is just a convenience for
			// viewing the file.
			 
			int nextI = R.nextInt(intRange);
			
			// Note how the range of the random integer values greatly
			// influences the size of the text file, but has no impact at
			// all on the size of the binary file.  This is because the
			// text file gets a sequence of character digits for each
			// number, so the more digits the longer the file.  The binary
			// file, on the other hand, gets 4 bytes regardless of the
			// value of the integer.
			iBinOut.writeInt(nextI);
			iTextOut.print(nextI + " ");
		}
		dBinOut.close();
		dTextOut.close();
		iBinOut.close();
		iTextOut.close();
	}
}

