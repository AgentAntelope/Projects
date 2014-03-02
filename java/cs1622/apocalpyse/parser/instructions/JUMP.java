/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Immediate;
import parser.Interpreter;

public class JUMP extends Instruction{
	private Immediate val;
		
	public JUMP(String label){
		try{
			int v = Integer.parseInt(label);
			val = new Immediate(v);
		}
		catch(Exception e){
			val = new Immediate(label);
		}

	}
	
	public void runInstruction(Interpreter interpreter){
		interpreter.setInstructionPosition(val.value());
	}
}
