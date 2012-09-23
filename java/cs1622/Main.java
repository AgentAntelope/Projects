import java.io.*;


public class Main{
	public static void main(String[] args){
		try{
			FileInputStream fileStream = new FileInputStream(new File(args[0]));
			Lexer lex = new Lexer(fileStream);
			int blah = lex.yylex();
		}
		catch(Exception ex){

		}
	}
}
