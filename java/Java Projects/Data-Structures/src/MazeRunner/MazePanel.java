package MazeRunner;
//////////////////////////////////////////////////////////////////////
///
///  Contents: Panel to display maze.
///  Author:   John Aronis
///  Date:     December 2007
///
//////////////////////////////////////////////////////////////////////

import java.awt.* ;
import javax.swing.* ;

public class MazePanel extends JPanel {

  public static int SIDE = 30 ;
  public static Color startColor   = Color.red ;
  public static Color goalColor    = Color.yellow ;
  public static Color openColor    = Color.white ;
  public static Color closedColor  = Color.black ;
  public static Color visitedColor = Color.black ;
  public static Color turtleColor  = Color.green ;

  public Maze maze ;
  public int rows ;
  public int columns ;
  public int height ;
  public int width ;

  public MazePanel(Maze m) {
    maze = m ;
    columns = m.columns ;
    rows = m.rows ;
    width = SIDE * columns ;
    height = SIDE * rows ;
    setPreferredSize( new Dimension(width,height) ) ;
  }

  public void drawBox(int column, int row, int side, Color color, Graphics g) {
    int centerX = (column*SIDE) + (SIDE/2) ;
    int centerY = (row*SIDE) + (SIDE/2) ;
    int radius = side / 2 ;
    g.setColor(color) ;
    g.fillRect( centerX-radius , centerY-radius , side , side ) ;
  }

  public void drawDot(int column, int row, int diameter, Color color, Graphics g) {
    int centerX = (column*SIDE) + (SIDE/2) ;
    int centerY = (row*SIDE) + (SIDE/2) ;
    int radius = diameter / 2 ;
    g.setColor(color) ;
    g.fillOval( centerX-radius , centerY-radius , diameter , diameter ) ;
  }

  public void paintComponent(Graphics g) {
    Point p ;
    for (int r = 0 ; r < rows ; r++) { for (int c = 0 ; c < columns ; c++) {
      p = new Point(r,c) ;
      if ( maze.isOpen(p) ) drawBox(c,r,SIDE,openColor,g) ;
      if ( maze.isClosed(p) ) drawBox(c,r,SIDE,closedColor,g) ;
      if ( maze.isStart(p) ) drawBox(c,r,SIDE,startColor,g) ;
      if ( maze.isGoal(p) ) drawBox(c,r,SIDE,goalColor,g) ;
      if ( maze.isVisited(p) ) drawDot(c,r,SIDE/2,visitedColor,g) ;
      if ( maze.isCurrent(p) ) drawDot(c,r,SIDE/2,turtleColor,g) ;
    }}
  }

}

/// End-of-File