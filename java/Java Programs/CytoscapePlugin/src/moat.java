import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class moat {
	static class Point implements Comparable {
		public int x; public int y;
		Point pv;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void setPivot(Point pv) {
			this.pv = pv;
		}
		public int compareTo(Object o) {
			Point p = (Point)o;
			double a1 = ((y-pv.y)*1.0)/(x-pv.x);
			if(a1<0) a1 = 2+a1;
			double a2 = ((p.y-pv.y)*1.0)/(p.x-pv.x);
			if(a2<0) a2 = 2+a2;
			if(a1>a2) return 1;
			else if(a1<a2) return -1;
			else return y-p.y;
		}
	}
	static Point []points;
	 public static void main (String [] args) throws IOException {
		    
		 // Use BufferedReader rather than RandomAccessFile; it's much faster
		    BufferedReader f = new BufferedReader(new FileReader("moat.in"));
		                                                  // input file name goes above
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moat.out")));

		    int N = Integer.parseInt(f.readLine());
		    points = new Point[N];
		    int lx = Integer.MAX_VALUE, ly = Integer.MAX_VALUE;
		    Point p = null;
		    for(int i=0; i<N; i++) {
		    	StringTokenizer st = new StringTokenizer(f.readLine());
		    	int x = Integer.parseInt(st.nextToken());
		    	int y = Integer.parseInt(st.nextToken());
		    	
		    	points[i] = new Point(x,y);
		    	if(y<ly) {
		    		ly =y;
		    		lx = x;
		    		p = points[i];
		    	}
		    	else if(y==ly && x<lx) {
		    		lx = x;
		    		p = points[i];
		    	}
		    }
		    for(int i=0; i<N; i++) {
		    	points[i].setPivot(p);
		    }
		    Arrays.sort(points);
		    for(int i=0; i<points.length; i++)
		    {
		    	System.out.println(points[i].x+" "+points[i].y);
		    }
		    Stack stack = new Stack();
		    stack.push(points[0]);
		    stack.push(points[1]);
		    for(int i=2; i<points.length; i++) {
		    	Point top = (Point)stack.pop();
		    	Point second = (Point)stack.pop();
		        int o = Cross_product(second, top, points[i]);
		        stack.push(second);
		        stack.push(top);
		        if(o == 0) {
		        	stack.pop();
		        	stack.push(points[i]);
		        }
		        else if (o > 0) {
		        	stack.push(points[i]);
		        }
		        else {
		                while (o <= 0 && stack.size() > 2) {
		                        stack.pop();
		                        top = (Point)stack.pop();
		        		    	second = (Point)stack.pop();
		                        o = Cross_product(second, top, points[i]);
		                        stack.push(second);
		        		        stack.push(top);
		                }
		                stack.push(points[i]);
		        }
		    }
		    System.out.println("Done");
			while(stack.size()>0) {
				Point pp = (Point)stack.pop();
				System.out.println(pp.x+" "+pp.y);
			}
		    //out.println(ans);                        
		    out.close();                                 
		    System.exit(0); 
	 }
	 
	 private static int Cross_product(Point p1, Point p2, Point p3) {
		 return (p2.x - p1.x)*(p3.y - p1.y) - (p3.x - p1.x)*(p2.y - p1.y);	
	 }
}
