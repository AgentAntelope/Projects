///
/// Contents: JPanel to display points and their convex hull.
/// Author:   John Aronis
/// Email:    aronis@cs.pitt.edu
/// Date:     Octoer 2008
///

import java.awt.* ;
import javax.swing.* ;
import java.util.ArrayList ;

public class Graph extends JPanel {

  public Color BACKGROUND = Color.white ;
  public Color HULL = Color.red ;
  public Color INTERIOR = Color.blue ;
  public Color ANCHOR = Color.green ;
  public int DOT = 6 ;
  public int BORDER = 30 ;
  public int width ;
  public int  height ;
  public ArrayList<Point> interior ;
  public ArrayList<Point> hull ;

  public Graph(int width, int height, ArrayList<Point> interior, ArrayList<Point> hull) {
    this.width = width ;
    this.height = height ;
    setPreferredSize(new Dimension(width+(2*BORDER),height+(2*BORDER))) ;
    this.interior = interior ;
    this.hull = hull ;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g) ;
    Point start, current, previous ;
    g.setColor(BACKGROUND) ;
    g.fillRect(0,0,width+(2*BORDER),height+(2*BORDER)) ;
    g.setColor(HULL) ;
    start = previous = current = hull.get(0) ;
    for (int p = 0 ; p < hull.size() ; p++) {
      current = hull.get(p) ;
      g.drawLine((int)previous.x+BORDER,(int)previous.y+BORDER,(int)current.x+BORDER,(int)current.y+BORDER) ;
      previous = current ;
    }
    g.drawLine((int)previous.x+BORDER,(int)previous.y+BORDER,(int)start.x+BORDER,(int)start.y+BORDER) ;
    g.setColor(INTERIOR) ;
    for (int p = 0 ; p < interior.size() ; p++) {
      current = interior.get(p) ;
      g.fillOval((int)current.x+BORDER-DOT/2,(int)current.y+BORDER-DOT/2,DOT,DOT) ;
    }
    g.setColor(ANCHOR) ;
    g.fillOval((int)hull.get(0).x+BORDER-DOT,(int)hull.get(0).y+BORDER-DOT,2*DOT,2*DOT) ;
  }

}

/// End-of-File
