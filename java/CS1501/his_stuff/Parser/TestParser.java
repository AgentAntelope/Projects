///
///  Contents: Demonstrate CFG parser.
///  Author:   John Aronis
///  Date:     January 2010
///

public class TestParser {

  public static void main(String[] args) {

    System.out.println("===== TEST SIMPLE GRAMMAR =====") ;
    Parser.initialize() ;
    Parser.rule("SS","ART","NOUN") ;
    Parser.rule("S","NP","VP") ;
    Parser.rule("NP", "ART", "NN") ;
    Parser.rule("NN","ADJ","NN") ;
    Parser.rule("NN","NOUN") ;
    Parser.rule("VP", "VERB", "NP") ;
    Parser.rule("ART", "a") ;
    Parser.rule("ART", "the") ;
    Parser.rule("ADJ", "big") ;
    Parser.rule("ADJ", "red") ;
    Parser.rule("ADJ", "mean") ;
    Parser.rule("NOUN", "cat") ;
    Parser.rule("NOUN", "dog") ;
    Parser.rule("NOUN", "cow") ;
    Parser.rule("VERB", "saw") ;
    Parser.rule("VERB", "bit") ;
    System.out.println( Parser.parse("NP","the big mean dog") ) ;
    System.out.println( Parser.parse("S","the big red cat saw a dog") ) ;

    System.out.println("===== TEST BACKTRACKING =====") ;
    Parser.initialize() ;
    Parser.rule("S", "A") ;
    Parser.rule("A", "B", "c") ;
    Parser.rule("B", "a", "b") ;
    Parser.rule("S", "C") ;
    Parser.rule("C", "a", "D") ;
    Parser.rule("D", "b", "E") ;
    Parser.rule("E", "c", "d") ;
    System.out.println( Parser.parse("S","a b c d") ) ;

    System.out.println("===== TEST LEFT-RECURSIVE GRAMMAR (FAILURE) =====") ;
    Parser.initialize() ;
    Parser.rule("S", "A", "B") ;
    Parser.rule("A", "A", "A") ;
    Parser.rule("A", "A") ;
    Parser.rule("A", "a") ;
    Parser.rule("B", "b") ;
    // System.out.println( Parser.parse("S","a b") ) ;

  }

}

/// End-of-File
