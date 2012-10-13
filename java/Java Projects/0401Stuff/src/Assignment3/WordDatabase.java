package Assignment3;
import java.io.*;
import java.util.*;


public class WordDatabase 
{
	
	private String[] Words; //Stores the Words from the text file
	private String[] TrashWords; //Stores the used words from the GetNextWord method
	private int TrashNumber = 0;//Makes it so I can have an exit clause in GetNextWord
	//Also makes for easy storing and searching when comparing the TrashWords and Words
	
	/*
	 * Asks for the name of the text file, though there is no
	 * exception if the text file is not there.. Then, 
	 */
public void LoadWords(Scanner inKey)throws IOException
 {
	System.out.println("What is the name of the Text file?");
	String FileName = inKey.nextLine();
	Scanner S = new Scanner(new FileInputStream(FileName));
	int numTimes = Integer.parseInt(S.nextLine());
	Words =new String[numTimes];
	TrashWords = new String[numTimes];
	for(int i = 0; i < numTimes; i++)
	{
		Words[i] = S.nextLine();
	}
	S.close();
 }
/* This one is a little hard to trace so I will break it up
 * I use the TrashWords to keep track of the words used and if
 * the TrashNumber equals the Words.length, then all of the words
 * have been used up and the program will send back a null. Since
 * this is a small program, after taking the random number, I just run
 * a for loop and a nested if to check if the word is one of the TrashWords.
 * if it is, the while condition is met, and the program will reiterate.
 * I believe there shouldn't be any infinite loop since I made it that
 * if all TrashWords array is filled, the do will never execute.
 */
public String GetNextWord()
{
	Boolean WordRepeated = false;
	String ChosenWord;
	Random NextWord = new Random();
	 if(TrashNumber != Words.length)
	 {
	do{
	WordRepeated = false;
	int i;
	i = NextWord.nextInt(Words.length);
	 ChosenWord = Words[i];

	 for(int t = 0; t < TrashNumber; t++)
	 		{
		 if(TrashWords[t].equals(Words[i]))
				 {
			 WordRepeated = true;
				 }
	 		}
	 	}
	while(WordRepeated);
	
	TrashWords[TrashNumber] = ChosenWord;
	TrashNumber++;
	
	return ChosenWord;
	 }
	 else
		 return null;
}

public void HangManPicture(int guess)
  {
	if (guess==0)
	{
		System.out.println("  O----|");
		System.out.println("  |   ");
		System.out.println("  |   ");
		System.out.println("  |   ");
		System.out.println(" ---  ");
	}
	if (guess==1)
	{
		System.out.println("  O----|");
		System.out.println("  |    O");
		System.out.println("  |   ");
		System.out.println("  |   ");
		System.out.println(" ---    ");
	}
	else if (guess==2)
	{
		System.out.println("  O----|");
		System.out.println("  |    O");
		System.out.println("  |    |");
		System.out.println("  |   ");
		System.out.println(" ---    ");
	}
	else if (guess==3)
	{
		System.out.println("  O----|");
		System.out.println("  |    O");
		System.out.println("  |   -|");
		System.out.println("  |  ");
		System.out.println(" ---    ");
	}
	else if (guess==4)
	{
		System.out.println("  O----|");
		System.out.println("  |    O");
		System.out.println("  |   -|-");
		System.out.println("  |   ");
		System.out.println(" ---    ");
	}
	else if (guess==5)
	{
		System.out.println("  O----|");
		System.out.println("  |    O");
		System.out.println("  |   -|-");
		System.out.println("  |     \\");
		System.out.println(" ---    ");
	}
	else if (guess==6)
	{
		System.out.println("  O----|");
		System.out.println("  |    O");
		System.out.println("  |   -|-");
		System.out.println("  |   / \\");
		System.out.println(" ---    ");
	}
  }
}
