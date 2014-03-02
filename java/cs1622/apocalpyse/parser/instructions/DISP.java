/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Interpreter;

public class DISP extends Instruction{
	int rs;

	public DISP(String rs){
		this.rs = Integer.parseInt(rs.substring(2));		   
	}

	public void runInstruction(Interpreter interpreter){
		char result = interpreter.getRegister(rs);
		System.out.println("Result: " + (int)result);
		interpreter.nextInstruction();
	}
}
