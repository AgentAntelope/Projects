// CS 0401 Fall 2009
// This handout is the same as ex22.java, but with the generic typing
// facilities added in Java 1.5. I have removed the comments from the previous
// version and added comments to highlight the differences in the two.  See
// also class SortAllT.java and class CDT.java.

import java.io.*;
import java.util.*;
public class ex22T
{
	// We don't really need generic typing here, since we are not using
	// anything more specific than the Object class
	public static void showData(Object [] Ar, int perLine)
	{
		System.out.println("The data is: ");
		for (int i = 0; i < Ar.length-1; i++)
		{
			System.out.print(Ar[i] + ", ");
			if ((i+1) % perLine == 0)
				System.out.println();
		}
		if (Ar.length > 0)
			System.out.println(Ar[Ar.length-1]);
		System.out.println();
	}

	public static void main(String [] args)
	{
		String [] S = {	"Talking Heads", "Midnight Oil", "Beatles", 
						"Metallica", "U2", "Tori Amos", "Sarah McLachlan",
						"NIN", "Cranberries", "Garbage"};
		Integer [] A = new Integer[23];
		for (int i = 0; i < A.length; i++)
			A[i] = new Integer((i * 11)%(A.length));
		
		showData(S, 5); System.out.println();
		showData(A, 12); System.out.println();

		// Calling the selectionSort method defined in the SortAllT class
		// Compare this version to the version in SortAll.java
		SortAllT.selectionSort(S);
		SortAllT.selectionSort(A);

		showData(S, 5); System.out.println();
		showData(A, 12); System.out.println();
	
		Scanner fScan = null;
		try
		{
			fScan = new Scanner(new File("songs.txt"));
		}
		catch (IOException e)
		{}
		
		int numAlbums = Integer.parseInt(fScan.nextLine()); 
		CD [] myCDs = new CD[numAlbums];
		for (int i = 0; i < myCDs.length; i++)
		{
			String aTitle = fScan.nextLine();
			String rDate = fScan.nextLine();
			int numSongs = Integer.parseInt(fScan.nextLine());
			Song [] theSongs = new Song[numSongs];
			for (int j = 0; j < theSongs.length; j++)
			{
				String songString = fScan.nextLine();
				String [] songInfo = songString.split(",");
				Song currSong = new Song(songInfo[0], songInfo[1], songInfo[2],
					songInfo[3]);
				theSongs[j] = currSong;
			}
			CD theCD = new CD(aTitle, rDate, theSongs);
			myCDs[i] = theCD;
		}

		showData(myCDs, 1);
	    System.out.println();
		SortAllT.selectionSort(myCDs);
		showData(myCDs, 1);

		// This code will give the same run-time error as the version
		// in ex22.java.
		Comparable[] A2 = new Comparable[4];
		A2[0] = new String("Wacky");
		A2[1] = new Integer(40);
		A2[2] = new Float(55.5);
		A2[3] = myCDs[0];
		SortAllT.selectionSort(A2);
	}
}




