import java.util.*;

public class Point implements Comparable<Point> {
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate
   
    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        //Your Code Here
    	if(this.y == that.y){
    		return this.x-that.x; 
    	}
    	else{
    		return this.y - that.y;
    	}
    }

    
    public int x(){
        return x;
    }
    
    
    public int y(){
        return y;
    }
    
    
    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
