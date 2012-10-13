package Lab10;
// CS 0401 Fall 2009
// Lab 10
// You must modify this file so that it works as described in the lab handout.
import java.util.*;
import java.io.*;
public class lab10
{
	public static void main(String [] args)
	{
		Scanner inScan, fScan = null;
		int [] A = new int[5];
		inScan = new Scanner(System.in);
		System.out.println("Please enter the file to read from: ");
		Boolean EnterFile = true;
		while(EnterFile)
	{
		try{
		String fName = inScan.nextLine();
		fScan = new Scanner(new File(fName));
		EnterFile = false;
		}
		catch(IOException e1)
		{
			System.out.println("Wrong file name, please enter again.");
		}
	}
		String nextItem = "";
		int nextInt = 0;
		int i = 0;
		
		while (fScan.hasNextLine())
		{
			try{
			nextItem = fScan.nextLine();
			nextInt = Integer.parseInt(nextItem);
			A[i] = nextInt;
			i++;
			}
			catch(NumberFormatException e)
			{
				System.out.println("The item--"+ nextItem + " -- is not a valid integer, disregarding..");
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				int[] newArray = new int[A.length*2];

				for(int I = 0; I < A.length; I++)
				{
					newArray[I] = A[I];
				}
				A = newArray;
				A[i] = nextInt;
				i++;
				System.out.println("Resizing array to " + A.length);
			}
		}

		System.out.println("Here are your " + i + " items:");
		for (int j = 0; j < i; j++)
		{
			System.out.println(A[j] + " ");
		}
	}
}

