/*
Name:Sean Myers   Assignment #:4

Lab Day and Time: Thursday 12:15+
Lab Instructor: Michael

Program Due Date:11/20/09

Handed in Date: 11/4/09 9:30

Source code file name(s): 
Assign4.java, HangPlayer.java, WordServer.java, HangFigure.java, Word.java

Compiled (.class) file name(s):
 Assign4.class, HangPlayer.class, WordServer.class, HangFigure.class, Word.class

Does your program compile without error?: Yes

Does your program run without error?: Yes


*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class Assignment4
{
private JFrame F;
private JPanel Buttons, GameInfo;
private MyPanel HangFigure;
private JLabel Status, GameStuff, JWord, Guess, CurrentGuess;
private JLabel StatusUpdate, GameStuffUpdate, WordUpdate, GuessUpdate;
private JTextField EnterGuess;
private JButton Start, End, NewWord;
private Container Contents;
private GameListener HangListener;
private WordServer WordData;
private Word word;
private int guess;
private HangFigure Paul;
private StringBuffer GuessesTried;
private HangPlayer You;
private Boolean WordNewLoss = false;
public Assignment4()
{
	You = new HangPlayer();
	Paul = new HangFigure();
	WordData = new WordServer();
	word = null;
	GuessesTried = new StringBuffer();
	
	//Creates the JFrame
	F = new JFrame("Hangman!");
	F.setSize(500,175);
	F.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	F.setVisible( true );
	
	//Creates all the buttons and adds their Listeners
	Start = new JButton("Start Game");
	End = new JButton("End Game");
	NewWord = new JButton("New Word");
	
	//Initializes the Labels
	Status = new JLabel("Game Status:");
	StatusUpdate = new JLabel("Game not started");
	GameStuff = new JLabel("Game Info:");
	GameStuffUpdate = new JLabel("");
	JWord = new JLabel("Current Word:");
	WordUpdate = new JLabel("");
	Guess = new JLabel("Your Guesses:");
	GuessUpdate = new JLabel("");
	CurrentGuess = new JLabel("Current Guess:");
	EnterGuess = new JTextField(6);
	HangFigure = new MyPanel(150,150);
	
	//Adds ActionListener
	HangListener = new GameListener();
	Start.addActionListener(HangListener);
	End.addActionListener(HangListener);
	NewWord.addActionListener(HangListener);
	EnterGuess.addActionListener(HangListener);
	
	//Disables these functions at the start of the program
	End.setEnabled(false);
	NewWord.setEnabled(false);
	EnterGuess.setEnabled(false);
	
	//Adds all the JLabels to the Content Panel
	GameInfo = new JPanel();
	GameInfo.setLayout(new GridLayout(5,2));
	GameInfo.add(Status);
	GameInfo.add(StatusUpdate);
	GameInfo.add(GameStuff);
	GameInfo.add(GameStuffUpdate);
	GameInfo.add(JWord);
	GameInfo.add(WordUpdate);
	GameInfo.add(Guess);
	GameInfo.add(GuessUpdate);
	GameInfo.add(CurrentGuess);
	GameInfo.add(EnterGuess);
	
	
	//Adds all the buttons to the Buttons Content Panel
	Buttons = new JPanel();
	Buttons.setLayout(new GridLayout(3,1));
	Buttons.add(Start);
	Buttons.add(End);
	Buttons.add(NewWord);
	
	
	//Puts all the Panels onto the Pane`
	Contents = F.getContentPane();
	Contents.add(Buttons, BorderLayout.EAST);
	Contents.add(GameInfo, BorderLayout.CENTER);
	Contents.add(HangFigure, BorderLayout.WEST);
}
class GameListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		if(Start == e.getSource())
		{
			String FileName = JOptionPane.showInputDialog(F, "What is the name of the file?");
			You = new HangPlayer();
			You.SetName(JOptionPane.showInputDialog(F, "What is your name?"));			
			GameStuffUpdate.setText("Welcome to Hangman, " + You.GetName());
			F.pack();
			System.out.println(FileName);
			try {
				WordData.loadWords(FileName);
			} catch (Exception e1) {}
		
			//After loading the words, these buttons will be enabled/disabled
			End.setEnabled(true);
			NewWord.setEnabled(true);
			Start.setEnabled(false);
			StatusUpdate.setText("Game in Progress");//Updates game status
			
		}
		if(NewWord == e.getSource())
		{
			//Start re-initializing things like the figure and guesses
		
			Paul = new HangFigure();//Poor Paul
			HangFigure.repaint();
			guess = 0;
			GuessesTried = new StringBuffer();
			GuessUpdate.setText("");
			GameStuffUpdate.setText("");
			//End
			if(WordNewLoss)
			{
				You.IncrLosses();
				WordNewLoss = true;
			}
			EnterGuess.setEnabled(true);
			
			String NextWord =WordData.getNextWord();
			if(NextWord != "")
			{
				word = new Word(NextWord);
				System.out.println(NextWord);
				WordUpdate.setText(word.getHidden());
				
			}
			else
			{
				NewWord.setEnabled(false);
				EnterGuess.setEnabled(false);
				
				Start.setEnabled(true);
				StatusUpdate.setText("There are no more words");
			}
		}
		if(End == e.getSource())
			{
			
			JOptionPane.showMessageDialog(F,You.Results());
			End.setEnabled(false);
			NewWord.setEnabled(false);
			EnterGuess.setEnabled(false);
			Start.setEnabled(true);
			StatusUpdate.setText("Thanks for playing");
			GameStuffUpdate.setText("");
			}
		
		if(EnterGuess == e.getSource())
		{
		
			//Gets the text
			String StringGuess = EnterGuess.getText();
			EnterGuess.setText("");
			
			//Makes it so that only the first letter is taken
			char CharGuess = StringGuess.charAt(0);
			
			//Checks for re-used letters
			Boolean ReusedLetters = false;
			for(int i = 0; i < GuessesTried.length(); i++)
			{
				if(GuessesTried.charAt(i)== CharGuess)
				{
					ReusedLetters = true;
					GameStuffUpdate.setText("That letter has been used");
				}		
			}
			
			if(!ReusedLetters)
			{
				GuessesTried.append(CharGuess);
			Boolean WordRight =word.LetterReveal(CharGuess);
			WordUpdate.setText(word.getHidden());
			
			//If you are wrong, this starts up
			if(!WordRight)
			{
				GameStuffUpdate.setText("Sorry, there is no "+CharGuess +".");
				Paul.addChunk();
				HangFigure.repaint();
				guess++;
				//After 6 guesses, you lose.
				if(guess == 6)
				{
					EnterGuess.setEnabled(false);
					GameStuffUpdate.setText("Sorry, the word was: \n" +
							word.getActual());
					You.IncrLosses();
					WordNewLoss = false;
					F.pack();
				}
			}
			else
			{
				GameStuffUpdate.setText(CharGuess +" was found!");
				Boolean CheckWin =word.checkWin();
					if(CheckWin)
					{
						EnterGuess.setEnabled(false);
						GameStuffUpdate.setText("You have gotten the word!");
						You.IncrWins();
						F.pack();
					}
			}
				
		}
			GuessUpdate.setText(GuessesTried.toString());
	}
	}


}
	public static void main (String[]Args)
	{
		new Assignment4();
	}
	class MyPanel extends JPanel
	{
		private int width, height;
		public MyPanel(int w, int h)
		{
			width = w;
			height = h;
		}

		// This method is implicitly called through the JFrame to see how much
		// space the JPanel needs.  Otherwise, the JPanel will be given a default,
		// very small size.
		public Dimension getPreferredSize()
		{
			return new Dimension(width, height);
		}
		public void paintComponent (Graphics g)       // Pass the graphics object
		// to the Panel so it can
		// draw its shapes
	{
		super.paintComponent(g);         // don't forget this line!
		Graphics2D g2d = (Graphics2D) g;
		if (Paul != null)		// Note that we are calling the draw() method in
			Paul.draw(g2d);	// in the HangFigure class and passing the
							// Graphics2D object into it.  This way, our JPanel
					// does not have to know the specifics of how to draw the
					// figure -- it only has to know that the figure has a
					// draw() method.
	}
	}
 
}
