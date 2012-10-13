package Java;

//The "Summative1" class.
public class Summative1
{
    public static void main (String[] args)
    {
        int userScore = 0;
        int compScore = 0;
        String userMove, compMove;
        int winner;

        System.out.println ("ROCK - PAPER - SCISSORS");
        System.out.println ("This game plays 10 rounds.");

        for (int rnd = 1 ; rnd <= 10 ; rnd++)
        {

            userMove = getUserMove ();
            compMove = getComputerMove ();
            System.out.println ("Computer's move: " + compMove);
            
             winner = determineRoundWinner(compMove, userMove);
             
            
            
           
            if (winner == 1) {
                System.out.println(compMove + " beats " + 
                                   userMove + 
                                   " the computer wins this round.");
                compScore++;
            }
            else if (winner == -1) {
                System.out.println(userMove + " beats " + 
                                   compMove + 
                                   " the user wins this round.");
                userScore++;
            }
            else {
                System.out.println(userMove + " ties " + 
                                   compMove + 
                                   " nobody wins this round.");
            }
            
            System.out.println("Score: User=" + userScore + 
                               " Computer=" + compScore);
            System.out.println();
       
        
        displayGameWinner(userScore, compScore);
   
          static String getUserMove() {
        String userMove = "club";
        
         while (!userMove.equals("ROCK") &&
               !userMove.equals("PAPER") &&
               !userMove.equals("SCISSORS")) {

            System.out.print("Enter your move " +
                               "[ROCK, PAPER, SCISSORS]: ");
                               
                               
                       userMove = System.in.readLine();
            userMove = userMove.toUpperCase();
        }

        return userMove;
    }
    
     static String getComputerMove() {
        int compMoveInt;
        String compMove;
        
               if (compMoveInt == 1) {
            compMove = "ROCK";
        }
        else if (compMoveInt == 2) {
            compMove = "PAPER";
        }
        else {
            compMove = "SCISSORS";
        }

        return compMove;
    }
   

      public static int randomInt(int lowEnd, int highEnd) {
        int theNum;
        {
        
        theNum = (int)(Math.random() * (highEnd - lowEnd + 1)) + lowEnd;

        return theNum;
        
            static int determineRoundWinner(String userMove,String compMove) {
       
            	int winner;
        
          if (compMove.equals("ROCK") && 
                 userMove.equals("SCISSORS")) {
            winner = 1;
            
          }
          
            else if (compMove.equals("PAPER") &&
                 userMove.equals("ROCK")) {
            winner = 1;
        }
        
           else if (compMove.equals("SCISSORS") &&
                 userMove.equals("PAPER")) {
            winner = 1;
        }
        
              else {
            winner = -1;
        }

        return winner;
    }

        static void displayGameWinner(int userScore,
                                  int compScore) {

        System.out.println("\n\nFinal Score:");
        System.out.println("       User=" + userScore + 
                           "   Computer=" + compScore);
        System.out.println();

        if (userScore > compScore) {
            System.out.println("The user wins!");
        }
        else if (compScore > userScore) {
            System.out.println("The computer wins!");
        }
        else {
            System.out.println("Its a tie, nobody wins.");
        }
    }
{


        } // main method
    } // Summative1 class