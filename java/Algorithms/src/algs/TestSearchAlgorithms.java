//////////////////////////////////////////////////////////////////////
///
///  Contents: Compare performance hash table and patricia.
///  Author:   John Aronis
///  Date:     November 2010
///
//////////////////////////////////////////////////////////////////////

package algs;
import java.util.Date ;
import java.util.Random ;

public class TestSearchAlgorithms {

  public static int[] SIZES           = { 10000, 30000, 50000, 70000, 90000 } ;
  public static int   PROBES          = 2000 ;
  public static int   SIZE            = 10000 ;
  public static int   KEY_LENGTH      = 20 ;
  public static int[] KEY_LENGTHS     = { 20, 100, 500, 1000, 2000 } ;
  public static int   CAPACITY        = 100000 ;
  public static int   PROBE_INTERVAL  = 8 ;

  public static Random R = new Random() ;

  public static void main(String[] args) {
    Patricia P ;
    HashTable H ;
    String[] keys = new String[CAPACITY] ;


    System.out.println("INSERTION TIMES FOR INCREASING NUMBER OF ITEMS") ;
    System.out.printf("Patricia   ") ;
    for (int size : SIZES) {
      P = new Patricia() ;
      for (int i = 0 ; i < size ; i++) { P.put(key(KEY_LENGTH),"foo") ; }
      for (int i = 0 ; i < PROBES ; i++) { keys[i] = key(KEY_LENGTH) ; }
      startTimer() ;
      for (int i = 0 ; i < PROBES ; i++) { P.put(keys[i],"foo") ; }
      System.out.printf("%10d",getElapsedTime()) ;
    }
    System.out.println() ;
    System.out.printf("Hash Table ") ;
    for (int size : SIZES) {
      H = new HashTable(CAPACITY,PROBE_INTERVAL) ;
      for (int i = 0 ; i < size ; i++) { H.put(key(KEY_LENGTH),"foo") ; }
      for (int i = 0 ; i < PROBES ; i++) { keys[i] = key(KEY_LENGTH) ; }
      startTimer() ;
      for (int i = 0 ; i < PROBES ; i++) { H.put(keys[i],"foo") ; }
      System.out.printf("%10d",getElapsedTime()) ;
    }
    System.out.println() ;

    System.out.println("RETRIEVAL (MISSES) TIMES FOR INCREASING NUMBER OF ITEMS") ;
    System.out.printf("Patricia   ") ;
    for (int size : SIZES) {
      P = new Patricia() ;
      for (int i = 0 ; i < size ; i++) { P.put(key(KEY_LENGTH),"foo") ; }
      for (int i = 0 ; i < PROBES ; i++) { keys[i] = key(KEY_LENGTH) ; }
      startTimer() ;
      for (int i = 0 ; i < PROBES ; i++) { P.get(keys[i]) ; }
      System.out.printf("%10d",getElapsedTime()) ;
    }
    System.out.println() ;
    System.out.printf("Hash Table ") ;
    for (int size : SIZES) {
      H = new HashTable(CAPACITY,PROBE_INTERVAL) ;
      for (int i = 0 ; i < size ; i++) { H.put(key(KEY_LENGTH),"foo") ; }
      for (int i = 0 ; i < PROBES ; i++) { keys[i] = key(KEY_LENGTH) ; }
      startTimer() ;
      for (int i = 0 ; i < PROBES ; i++) { H.get(keys[i]) ; }
      System.out.printf("%10d",getElapsedTime()) ;
    }
    System.out.println() ;


    System.out.println("INSERTION TIMES FOR INCREASING KEY LENGTHS") ;
    System.out.printf("Patricia   ") ;
    for (int keyLength : KEY_LENGTHS) {
      P = new Patricia() ;
      for (int i = 0 ; i < SIZE ; i++) { P.put(key(keyLength),"foo") ; }
      for (int i = 0 ; i < PROBES ; i++) { keys[i] = key(keyLength) ; }
      startTimer() ;
      for (int i = 0 ; i < PROBES ; i++) { P.put(keys[i],"foo") ; }
      System.out.printf("%10d",getElapsedTime()) ;
    }
    System.out.println() ;
    System.out.printf("Hash Table ") ;
    for (int keyLength : KEY_LENGTHS) {
      H = new HashTable(CAPACITY,PROBE_INTERVAL) ;
      for (int i = 0 ; i < SIZE ; i++) { H.put(key(keyLength),"foo") ; }
      for (int i = 0 ; i < PROBES ; i++) { keys[i] = key(keyLength) ; }
      startTimer() ;
      for (int i = 0 ; i < PROBES ; i++) { H.put(keys[i],"foo") ; }
      System.out.printf("%10d",getElapsedTime()) ;
    }
    System.out.println() ;


    System.out.println("RETRIEVAL (MISSES) TIMES FOR INCREASING KEY LENGTHS") ;
    System.out.printf("Patricia   ") ;
    for (int keyLength : KEY_LENGTHS) {
      P = new Patricia() ;
      for (int i = 0 ; i < SIZE ; i++) { P.put(key(keyLength),"foo") ; }
      for (int i = 0 ; i < PROBES ; i++) { keys[i] = key(keyLength) ; }
      startTimer() ;
      for (int i = 0 ; i < PROBES ; i++) { P.get(keys[i]) ; }
      System.out.printf("%10d",getElapsedTime()) ;
    }
    System.out.println() ;
    System.out.printf("Hash Table ") ;
    for (int keyLength : KEY_LENGTHS) {
      H = new HashTable(CAPACITY,PROBE_INTERVAL) ;
      for (int i = 0 ; i < SIZE ; i++) { H.put(key(keyLength),"foo") ; }
      for (int i = 0 ; i < PROBES ; i++) { keys[i] = key(keyLength) ; }
      startTimer() ;
      for (int i = 0 ; i < PROBES ; i++) { H.get(keys[i]) ; }
      System.out.printf("%10d",getElapsedTime()) ;
    }
    System.out.println() ;

  }

  public static String key(int length) {
    String result = "key" ;
    for (int i = 0 ; i < length-3 ; i++) { result = result + R.nextInt(10) ; }
    return result ;
  }

  public static long startTime ;
  public static void startTimer() { startTime = ( (new Date()).getTime() ) ; }
  public static long getElapsedTime() { return ( (new Date()).getTime() - startTime ) ; }

}

/// End-of-File
