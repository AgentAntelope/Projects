/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Interpreter;

public class SLT extends Instruction{
	private int rs, rt, rd;
		
	public SLT(String rd, String rs, String rt){
		this.rs = Integer.parseInt(rs.substring(2));
		this.rt = Integer.parseInt(rt.substring(2));
		this.rd = Integer.parseInt(rd.substring(2));		   
	}

	public void runInstruction(Interpreter interpreter){
		boolean test = interpreter.getRegister(rs) < interpreter.getRegister(rt);
		char result =(char)((test)?(1):(0));

		interpreter.setRegister(rd, result);
		interpreter.nextInstruction();
	}
}
