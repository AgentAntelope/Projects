package MazeRunner;
//////////////////////////////////////////////////////////////////////
///
///  Contents: Best-First Maze Search
///  Author:   John Aronis
///  Date:     January 2007
///
//////////////////////////////////////////////////////////////////////

import java.awt.* ;
import javax.swing.* ;
import java.util.Date ;

public class MazeRunner {

  private static int PAUSE = 500 ;


  public static void main(String[] args) {

    ///
    /// Create some variables:
    ///
    Point current, goal ;
    String file = "maze1.txt" ;
    Maze maze ;

    ///
    /// Create the maze:
    ///
    maze = new Maze(file) ;

    ///
    /// Make a JPanel for the maze and drop it in JFrame:
    ///
    JFrame frame = new JFrame("Maze Runner") ;
    MazePanel panel = new MazePanel(maze) ;
    frame.getContentPane().add(panel) ;
    frame.pack() ;
    frame.setVisible(true) ;
    frame.repaint() ;
    sleep(2*PAUSE) ;
    
    ///
    /// Get ready to search:
    ///
    current = maze.getStart() ;
    goal = maze.getGoal() ;
    current.setDistance(goal) ;
    ///
    /// Search!
    ///
    
    PriorityQueue<Point> PQ = new PriorityQueue<Point>() ;
    PQ.add(current) ;
    while ( !PQ.isEmpty() ) {
      current = PQ.remove() ;
      maze.setCurrent(current) ;
      frame.repaint() ;
      sleep(PAUSE) ;
      if ( current.equals(goal) ) { System.out.println("SUCCESS") ; return ; }
      Point[] move = maze.moves(current);
      if(move != null)
      {
      for (int i = 0; i < move.length; i++ ) {
        move[i].setDistance(goal) ;
        PQ.add(move[i]) ;
      }
      }
      maze.markVisited(current) ;
    }
    System.out.println("FAILURE") ;
  }

  public static void sleep(long milliseconds) {
    Date d ;
    long start, now ;
    d = new Date() ;
    start = d.getTime() ;
    do { d = new Date() ; now = d.getTime() ; } while ( (now - start) < milliseconds ) ;
  }

}

/// End-of-File