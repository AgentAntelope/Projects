package NOfQueens;
/*
 * Sean Myers
 * stm52@pitt.edu
 * 2/6/09
 * 
 */
public class DemonstrationOne {

	public static void main(String[]Args)
	{
		int[] Sizes = { 8, 10, 12, 14, 16, 18, 20 } ;
		NQueen NQueens = new NQueen();
	    System.out.println("Solutions to 4-Queen Problem:") ;
	    NQueens.Printing = true ;
	    NQueens.Pruning = true;
	    NQueens.Solve(4) ;

	    
	    for ( int SIZE : Sizes ) {
	        System.out.println("------------------------------------------------") ;
	        System.out.println("Number of Queens: " + SIZE) ;
	        NQueens.RecursiveCalls = 0 ;
	        NQueens.Solutions = 0 ;
	        NQueens.Pruning = false ;
	        NQueens.Printing = false ;
	        NQueens.Solve(SIZE) ;
	        System.out.println("CALLS WITH NO PRUNING = " + NQueens.RecursiveCalls) ;
	        System.out.println("SOLUTIONS WITH NO PRUNING = " + NQueens.Solutions) ;
	        NQueens.RecursiveCalls = 0 ;
	        NQueens.Solutions = 0 ;
	        NQueens.Pruning = true ;
	        NQueens.Printing = false ;
	        NQueens.Solve(SIZE) ;
	        System.out.println("CALLS WITH PRUNING = " + NQueens.RecursiveCalls) ;
	        System.out.println("SOLUTIONS WITH PRUNING = " + NQueens.Solutions) ;
	      }


	}
}
