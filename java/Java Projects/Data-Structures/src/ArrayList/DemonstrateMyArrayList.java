package ArrayList;
///
/// Contents: Demonstrate MyArrayList
/// Author:   John Aronis
/// Date:     December 2009
///

import java.util.Iterator ;

public class DemonstrateMyArrayList {

    public static int INITIAL_SIZE = 1000 ;
    public static int INCREMENT    = 1000 ;
    public static int MAX_SIZE     = 10000 ;

  public static void main(String[] args) {
    MyArrayList<String> A ;;

    System.out.println("\nDEMONSTRATE BASIC OPERATIONS:\n") ;
    A = new MyArrayList<String>() ;
    A.add("fee") ;
    A.add("fie") ;
    A.add("foo") ;
    A.add("bar") ;
    A.add(2,"foe") ;
    A.set(3,"fum") ;
    A.remove(4);
    System.out.println( A.contains("fie") ) ;
    System.out.println( A.indexOf("fie") ) ;
    System.out.println( A.contains("hello") ) ;
    System.out.println( A.indexOf("hello") ) ;
    System.out.println( A.size() ) ;

    System.out.println("\nDEMONSTRATE USE OF ITERATOR:\n") ;
    Iterator I = A.iterator() ;
    System.out.println( A ) ;
    while ( I.hasNext() ) {
      if ( I.next().equals("fie") ) I.remove() ;
    }
    System.out.println( A ) ;

    System.out.println("\nDEMONSTRATE ENHANCED FOR-LOOP:\n") ;
    for ( String s : A ) { System.out.print( s + " " ) ; }
    System.out.println() ;

    System.out.println("\nCOUNT OPERATIONS PER ADDS AT BACK:\n") ;
    System.out.printf("        adds   copies/add    shifts/add \n") ;
    for (int s=INITIAL_SIZE ; s<=MAX_SIZE ; s+=INCREMENT) {
      A = new MyArrayList<String>() ;
      MyArrayList.COPIES = 0 ;
      MyArrayList.SHIFTS = 0 ;
      for (int i=0 ; i<s ; i++) { A.add("foo") ; }
      System.out.printf("%12d %12d %12d \n",s,MyArrayList.COPIES/s,MyArrayList.SHIFTS/s) ;
    }

    System.out.println("\nCOUNT OPERATIONS PER ADDS AT FRONT:\n") ;
    System.out.printf("        adds   copies/add    shifts/add \n") ;
    for (int s=INITIAL_SIZE ; s<=MAX_SIZE ; s+=INCREMENT) {
      A = new MyArrayList<String>() ;
      MyArrayList.COPIES = 0 ;
      MyArrayList.SHIFTS = 0 ;
      for (int i=0 ; i<s ; i++) { A.add(0,"foo") ; }
      System.out.printf("%12d %12d %12d \n",s,MyArrayList.COPIES/s,MyArrayList.SHIFTS/s) ;
    }

  }

}

/// End-of-File
