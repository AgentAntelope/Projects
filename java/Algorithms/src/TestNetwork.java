//////////////////////////////////////////////////////////////////////
///
///  Contents: Test Network Flow Program
///  Author:   John Aronis
///  Date:     December 2006
///
//////////////////////////////////////////////////////////////////////

public class TestNetwork {

  public static void main(String[] args) {
    Network network = new Network("A B C D E F") ;
    network.setCap("A","B",6) ;
    network.setCap("A","C",8) ;
    network.setCap("B","D",6) ;
    network.setCap("B","E",3) ;
    network.setCap("C","D",3) ;
    network.setCap("C","E",3) ;
    network.setCap("D","F",8) ;
    network.setCap("E","F",6) ;
    System.out.println("Original network:") ;
    network.print() ;
    network.maximizeFlow("A","F") ;
    System.out.println("Network with flow values:") ;
    network.print() ;
  }

}

/// End-of-File
