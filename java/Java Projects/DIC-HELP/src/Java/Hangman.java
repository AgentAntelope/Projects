package Java;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.Random;

public class Hangman extends JFrame
{

                private String theword;
                private static int score = 0;
                private String gallows[]={"image1.jpg","image2.jpg","image3.jpg","image4.jpg","image5.jpg","image6.jpg","image7.jpg"};
                private JLabel label;
                private ImageIcon icon[];
                 private JButton letters[];
                 private String MyLetter[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        private  static JLabel lName;
        private static String fName = "New Player";
        private static JLabel secret[];
        private static JLabel seesecret[];
        private String word[] = {"borrowings","prepacking","reflations","recognized",
        "leadership","jobholders","interstate","glassworks","floodlight",
        "disrupting"};
                private char[] SecretWord;
                private int guess=0;


    public Hangman()
        {
                super("Hangman Game");
                Container c = getContentPane();

                c.setLayout(null);


                        icon = new ImageIcon[gallows.length];


                for(int i=0;i<gallows.length;i++)
                        {
                            //icon[i] = new ImageIcon(getClass().getResource(gallows[i]));
                        }


                label = new JLabel(icon[0]);
                label.setSize(400,400);
                label.setBounds(20,20,400,400);

                        ButtonHandle handle = new ButtonHandle();


                        letters = new JButton[26];



                for (int j = 0; j <letters.length; j++)
                {
                        letters[j] = new JButton(MyLetter[j]);
                        letters[j].setSize(50,50);
                        if(j<13){

                        letters[j].setBounds(50 + (j*60),450,50,50);
                        }
                        else {

                                letters[j].setBounds(50 + (j-13)*60,510,50,50);
                        }

                        letters[j].addActionListener(handle);
                        c.add(letters[j]);

                 }

                        Randomword rword = new Randomword();
                        rword.Secretword();

                        lName = new JLabel("Welcome, " + fName);
                        lName.setBounds(450,20,200,100);
                        c.add(lName);


                        secret = new JLabel[10];
                        for( int w=0;w<SecretWord.length;w++)
                        {
                                secret[w]= new JLabel(SecretWord[w]+"");
                                secret[w].setBounds(450+(w*15),300,50,30);
                                c.add(secret[w]);

                        }

                        seesecret = new JLabel[10];
                        for( int w=0;w<theword.length();w++)
                        {
                                seesecret[w]= new JLabel(theword.charAt(w)+"");
                                seesecret[w].setBounds(450+(w*15),250,50,30);
                                seesecret[w].setVisible(false);
                                c.add(seesecret[w]);
                        }



                c.add(label);


                 // Create game Menu
         JMenuBar bar = new JMenuBar();
         JMenu menu = new JMenu("Start");
         menu.setMnemonic('S');            // set mnemonic to S



                // Create Start Items
                JMenuItem start = new JMenuItem("Start Game");
                start.setMnemonic( 'G' ); // set mnemonic to t
                menu.add( start ); // add start item to game menu
                start.addActionListener(

                new ActionListener() // anonymous inner class
                {
            // Starts game
            public void actionPerformed( ActionEvent event )
                {
                                fName = JOptionPane.showInputDialog("Please enter your name");
                                lName.setText(fName);



                        } // end method actionPerformed
                 } // end anonymous inner class
                ); // end call to addActionListener


                        JMenuItem quit = new JMenuItem("Quit Game");
                quit.setMnemonic( 'Q' ); // set mnemonic to t
                menu.add( quit ); // add start item to game menu
                quit.addActionListener(

                new ActionListener() // anonymous inner class
                {
            // Quits current game and displays Score.
            public void actionPerformed( ActionEvent event )
                {
                                JOptionPane.showMessageDialog(null,"Your score is " + score,null,JOptionPane.PLAIN_MESSAGE);
                        } // end method actionPerformed
                 } // end anonymous inner class
                ); // end call to addActionListener

                        menu.addSeparator();

                // Create Start Items
                JMenuItem About = new JMenuItem("About");
                About.setMnemonic( 'A' ); // set mnemonic to t
                menu.add( About ); // add About item to game menu
                About.addActionListener(

                new ActionListener() // anonymous inner class
                {
            // gives instruction how to play when AboutItem is clicked
            public void actionPerformed( ActionEvent event )
                {
                JOptionPane.showMessageDialog(null,
                "Select a letter by click on the buttons.\n You have 6 attempts to guess the word.");

                        } // end method actionPerformed
                 } // end anonymous inner class
                ); // end call to addActionListener

                        menu.addSeparator();

                        // Create exit item
                JMenuItem exitItem = new JMenuItem( "Exit" );
                exitItem.setMnemonic( 'x' ); // set mnemonic to x
                menu.add( exitItem ); // add exit item to file menu
                exitItem.addActionListener(

                 new ActionListener() // anonymous inner class
                 {
            // terminate application when user clicks exitItem
            public void actionPerformed( ActionEvent event )
                {
               System.exit( 0 ); // exit application
                        } // end method actionPerformed
                 } // end anonymous inner class
                ); // end call to addActionListener

         // Add Game Menu to Bar and Bar to JFRAME
             bar.add(menu);
             this.setJMenuBar(bar);

                this.setSize(900,685);
                this.setVisible(true);


                Scanner input = new Scanner(System.in);


        }

    public static void main(String[] args)
    {
        // create JFrame with EXIT ON CLOSE
      // JFrame hangman = new JFrame("Hangman Game");

       Hangman hangman = new Hangman();
       hangman.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

   }
        private class ButtonHandle implements ActionListener
                        {
                                public void actionPerformed(ActionEvent events)
                                {
                                        for(int j=0;j<26;j++)
                                        {
                                                if(events.getSource()== letters[j])
                                                {
                                                   JOptionPane.showMessageDialog(null,MyLetter[j]);

                                                 for( int w=0;w<SecretWord.length;w++)
                                                {
                                                   if(MyLetter[j].equals(SecretWord[w]))
                                                    {
                                                        seesecret[w].setVisible(true);
                                                        }
                                                        guess++;
                                                }
                                                if(guess == 1)
                                                {label = new JLabel(icon[1]);
                                                }
                                                if(guess ==2)
                                                {               label = new JLabel(icon[2]);
                                                        }
                                                        if(guess == 3)
                                                {               label = new JLabel(icon[3]);
                                                        }
                                                        if(guess == 4)
                                                {               label = new JLabel(icon[4]);
                                                        }
                                                        if(guess == 5)
                                                {               label = new JLabel(icon[5]);
                                                        }
                                                        if(guess == 6)
                                                {               label =  new JLabel(icon[6]);
                                                        }
                                                        if(guess == 7)
                                                {               label =  new JLabel(icon[7]);
                                                                score+=10;
                                                        }


                                                }
                                        }
                                }
                        }


        private class Randomword
        {
                Random words = new Random();

                public String Secretword()
        {

        theword = word[words.nextInt(word.length)];

                SecretWord = theword.toCharArray();


                JOptionPane.showMessageDialog(null,theword);

                return theword;
                }
        }
}