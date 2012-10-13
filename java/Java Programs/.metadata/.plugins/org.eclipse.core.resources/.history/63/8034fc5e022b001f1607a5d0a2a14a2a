///
/// Contents: Demonstrate Convex Hull Program
/// Author:   John Aronis
/// Email:    aronis@cs.pitt.edu
/// Date:     October 2008, Modified December 2009
///

import java.util.Random ;
import java.util.ArrayList ;
import java.awt.* ;
import javax.swing.* ;

public class DemonstrateConvexHull {

  public static int WIDTH = 500 ;
  public static int HEIGHT = 500 ;
  public static int POINTS = 120 ;

  public static void main(String[] args) {
    // Create random set of points:
    Random r = new Random() ;
    ArrayList<Point> points = new ArrayList<Point>() ;
    for (int p = 0 ; p < POINTS ; p++) {
      points.add(new Point(r.nextInt(WIDTH),r.nextInt(HEIGHT))) ;
    }
    // Find convex hull:
    ArrayList<Point> perimeter = ConvexHull.hull(points) ;
    // Display hull:
    JFrame frame = new JFrame("Convex Hull of Points") ;
    Graph graph = new Graph(WIDTH,HEIGHT,points,perimeter) ;
    frame.getContentPane().add(graph) ;
    frame.pack() ;
    frame.setVisible(true) ;
    graph.repaint() ;
  }

}

/// End-of-File
