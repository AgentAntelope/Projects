/**
* @author: Sean Myers
*/
import java_cup.runtime.Symbol;
import tools.*;
import java.io.*;

public class main {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("ERROR: Invalid number of command line arguments.");
			System.err.println("Usage: java main file.asm");
			System.exit(1);
		}
		try {
			Parser parser_obj = new Parser(new JavaLex(new FileInputStream(args[0])));

		} catch (IOException e) {
			System.err.println("ERROR: Unable to open file: " + args[0]);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
