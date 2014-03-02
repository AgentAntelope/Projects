/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Interpreter;

public class LB extends Instruction{
	private int rs, rt, imm;
		
	public LB(String rt, String rs, int imm){
		this.rs = Integer.parseInt(rs.substring(2));
		this.rt = Integer.parseInt(rt.substring(2));
		this.imm = imm;		   
	}

	public void runInstruction(Interpreter interpreter){
		interpreter.setRegister(rt, interpreter.getFromMemory(rs + imm));
	}
}
