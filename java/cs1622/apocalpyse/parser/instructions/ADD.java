/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Interpreter;

public class ADD extends Instruction{
	private int rs, rt, rd;
		
	public ADD(String rd, String rs, String rt){
		this.rs = Integer.parseInt(rs.substring(2));
		this.rt = Integer.parseInt(rt.substring(2));
		this.rd = Integer.parseInt(rd.substring(2));		   
	}

	public void runInstruction(Interpreter interpreter){
		char result =(char) (interpreter.getRegister(rs) + (char)interpreter.getRegister(rt));
		interpreter.setRegister(rd, result);
		interpreter.nextInstruction();
	}

}
