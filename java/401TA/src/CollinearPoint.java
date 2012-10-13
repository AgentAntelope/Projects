import java.util.*;

public class CollinearPoint extends Point{
    
   
    public CollinearPoint(int x, int y){
        //Your Code Here
    	super(x,y);
    }
    
    // compare points by slope
    public final Comparator<CollinearPoint> SLOPE_ORDER = new
    		Comparator<CollinearPoint>()
    		       {
    		               public int compare(CollinearPoint one, CollinearPoint two)
    		               {
    		            	   		double ninf = Double.NEGATIVE_INFINITY;
    		            	   		double pinf = Double.POSITIVE_INFINITY;
    		            	   	double slope1, slope2;
    		            	   	slope1 = slope(one);
    		            	   	slope2 = slope(two);

    		                       if(slope1 == slope2)
    		                               return 0;
    		                       else if(slope1 == ninf)
    		                               return -1;
    		                       else if(slope1 == pinf)
    		                               return 1;
    		                       else{
    		                    	   if(slope1 > slope2){
    		                    		   return 1;
    		                    	   }
    		                    	   else{
    		                    		   return -1;
    		                    	   }
    		                       }
    		                              
    		               }
    		       };
    public double slope(CollinearPoint other){
    	double slopePQ = Double.NEGATIVE_INFINITY;
       	if(this.compareTo(other) != 0){
    		if((other.x()-this.x()) == 0){
    			slopePQ = Double.POSITIVE_INFINITY;
    		}
    		else{
    			slopePQ = ((double)(other.y()-this.y()))/(other.x()-this.x());
    		}
        	
    	}
    	return slopePQ;
    }
    
    // are the 3 points p, q, and r collinear?
    public static boolean areCollinear(CollinearPoint p, CollinearPoint q, CollinearPoint r) {
         //Your Code Here
    	if(p.equals(q)|| p.equals(r) || q.equals(r)){
    		return true;
    	}
    	else if(p.x()-q.x() == 0 && p.x()-r.x() == 0){
    		return true;
    	}
    	else{
    		double slopePQ = (double)(q.y()-p.y())/(q.x()-p.x());
    		//System.out.println(slopePQ+ " ");
    		double slopePR = (double)(r.y()-p.y())/(r.x()-p.x());

        	return slopePQ == slopePR;
    		
    	}

    }

    
    // are the 4 points p, q, r, and s collinear?
    public static boolean areCollinear(CollinearPoint p, CollinearPoint q, CollinearPoint r, CollinearPoint s) {
         //Your Code Here
    	return areCollinear(p,q,r) && areCollinear(p,r,s) && areCollinear(q,r,s);
    }
    
}
    