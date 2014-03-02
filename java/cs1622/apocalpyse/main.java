/**
* @author: Sean Myers 
*/
import parser.instructions.*;
import parser.Immediate;
import parser.InstructionAssembler;
import java_cup.runtime.Symbol;
import java.io.*;
import parser.Interpreter;

public class main {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("ERROR: Invalid number of command line arguments.");
			System.err.println("Usage: java main file.asm");
			System.exit(1);
		}
		Symbol parse_tree = null;
		try {
			parser parser_obj = new parser(new MipsLex(new FileInputStream(args[0])));
			parse_tree = parser_obj.parse();
			InstructionAssembler assembler = parser_obj.getAssembler();
			assembler.resolve();
			Interpreter terpy = new Interpreter(assembler.instructions);
			terpy.run();
		} catch (IOException e) {
			System.err.println("ERROR: Unable to open file: " + args[0]);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
