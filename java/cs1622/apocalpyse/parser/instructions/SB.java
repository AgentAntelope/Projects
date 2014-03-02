/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Interpreter;

public class SB extends Instruction{
	private int rs, rt, imm;
		
	public SB(String rt, String rs, int imm){
		this.rs = Integer.parseInt(rs.substring(2));
		this.rt = Integer.parseInt(rt.substring(2));
		this.imm = imm;		   
	}
	
	public void runInstruction(Interpreter interpreter){
		interpreter.putInMemory(interpreter.getRegister(rs)+ imm, interpreter.getRegister(rt));
	}
}
