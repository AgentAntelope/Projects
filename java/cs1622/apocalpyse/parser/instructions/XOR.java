/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Interpreter;

public class XOR extends Instruction{
	private int rs, rt, rd;
		
	public XOR(String rs, String rt, String rd){
		this.rs = Integer.parseInt(rs.substring(2));
		this.rt = Integer.parseInt(rt.substring(2));
		this.rd = Integer.parseInt(rd.substring(2));		   
	}

	public void runInstruction(Interpreter interpreter){
		interpreter.setRegister(rs, (char)(interpreter.getRegister(rt) ^ interpreter.getRegister(rd)));
	}

}
