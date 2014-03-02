/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Immediate;
import parser.Interpreter;

public class BEQ extends Instruction{
	private int rs, rt;
	private Immediate val;
		
	public BEQ(String rs, String rt, String label){
		this.rs = Integer.parseInt(rs.substring(2));
		this.rt = Integer.parseInt(rt.substring(2));
		try{
			int v = Integer.parseInt(label);
			val = new Immediate(v);
		}
		catch(Exception e){
			val = new Immediate(label);
		}
	}
	
	public void runInstruction(Interpreter interpreter){
		if(interpreter.getRegister(rs) == interpreter.getRegister(rt))
			interpreter.setInstructionPosition(val.value());
	}

}
