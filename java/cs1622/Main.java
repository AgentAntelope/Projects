import java.io.*;
import java_cup.runtime.*;


public class Main{
	public static void main(String[] args){
		try{
			FileInputStream fileStream = new FileInputStream(new File(args[0]));
			Lexer lex = new Lexer(fileStream);
			int blah = lex.yylex();
			while(blah != lex.YYEOF)
				blah = lex.yylex();
		}
		catch(Exception ex){

		}
	}
}