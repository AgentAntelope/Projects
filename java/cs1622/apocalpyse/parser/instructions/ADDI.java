/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Interpreter;

public class ADDI extends Instruction{
	private int rs, rt, imm;
		
	public ADDI(String rt, String rs, int imm){
		this.rs = Integer.parseInt(rs.substring(2));
		this.rt = Integer.parseInt(rt.substring(2));
		this.imm = imm;
	}
	
	public void runInstruction(Interpreter interpreter){
		char result =(char) (interpreter.getRegister(rs) + (char)imm);
		interpreter.setRegister(rt, result);
		interpreter.nextInstruction();
	}
}
