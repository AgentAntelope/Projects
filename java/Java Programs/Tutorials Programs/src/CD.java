// CS 0401 Fall 2008
// Simple class to represent a music CD.  As we discussed in lecture, if
// we want to represent a general CD (could contain data or music or some
// other media) the class would be more complex.

// The class here is very simple -- containing only a single constructor and
// a toString() method [and compareTo method, which will be discussed later].
// As an exercise you may want to add some additional constructors, accessors
// and mutators.

// See CDTest.java for a simple test program.  If you add to this class you
// can also add to CDTest.java to demonstrate the new features.

import java.util.*;  // needed for Date
import java.text.*;  // needed for DateFormat
public class CD implements Comparable<CD> // See more info below
{
	// We discussed last class whether the music type should be
	// included in the CD itself or within the Song type (since compilations
	// could have multiple types).  In this case I have put it into the Song
	// type.  The process of using data of one class as instance data in
	// another class is COMPOSITION.  The idea is that we are using pre-existing
	// classes to compose a new class.  The functionality of the new class is
	// at least partially based on the functionality of the classes used to build
	// it.
	private String title;
	private Song [] songList;
	private Date releaseDate;  // We are storing the release date as a
	             // Date object.  This will allow us to manipulate it
	             // as a Date if we so choose.  Note that this could be
	             // stored in various ways
	int tracks;  // This could be an array or even an object
	private int length;  // in seconds

	public CD(String t, String d, Song [] songs)
	{
		title = new String(t);

		// Code below is used to convert a String representation of a
		// date (ex: 10/21/2004) into a Date object.  Don't worry too
		// much about the details at this point (i.e. the
		// DateFormat class and the try - catch block).  We may cover
		// these later.
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		try
		{
			releaseDate = df.parse(d);
		}
		catch (ParseException e)
		{
			releaseDate = null;
		}
		tracks = songs.length;
		length = 0;
		// Make a new array for the songs, the copy the songs from the parameter
		// into it.  Use getLength() method to get track lengths in seconds.
		songList = new Song[songs.length];
		for (int i = 0; i < songList.length; i++)
		{
			songList[i] = songs[i];
			length += songList[i].getLength();
		}
	}

	public String toString()
	{
		// Note how the resulting String is created here:  We append many
		// values to a StringBuffer and then output the StringBuffer as a
		// String.  If the object were simpler we could probably avoid
		// using the StringBuffer.
		StringBuffer S = new StringBuffer();
		S.append("CD: " + title + "\n");

		// As we discussed in lecture last week, it is good to set reference
		// variables to null if they are not referring to a valid object.
		// That way we can still test the reference.
		if (releaseDate != null)
		{
			S.append("Release Date: " + releaseDate.toString() + "\n");
		}
		else
		{
			S.append("No release date \n");
		}

		// Even though the length is stored as a single integer, users don't
		// typically want to see it represented that way.  Thus, we instead
		// show it as minutes and seconds.
		int min = length / 60;
		int sec = length % 60;
		S.append("Number of tracks: " + tracks + "\n");
		S.append("Length: " + min + " min. " + sec + " sec. \n");
		S.append("Songs: \n");
		// Note here that the toString() for a CD includes a call to toString()
		// for each Song in the CD.  Even though the method name is the same,
		// the methods are different, since they are called from different
		// objects.
		for (int i = 0; i < songList.length; i++)
			S.append(songList[i].toString());
		return S.toString();
	}

	// This method allows us to compare CDs in a regular way.  See how it is
	// used in this example.  We will discuss it more formally later.
	public int compareTo(CD rhs)
	{
		//return (length - rhs.length);
		return (this.title.compareTo(rhs.title));
	}
}

