///
/// Contents: Point in plane.
/// Author:   John Aronis
/// Date:     Octoer 2008
///

public class Point implements Comparable {

  public double x, y ;

  public Point(double x, double y) { this.x = x ; this.y = y ; }

  public String toString() { return "<" + this.x + "," + this.y + ">" ; }

public int compareTo(Object arg0) {
	return 0;
}

}

/// End-of-File
