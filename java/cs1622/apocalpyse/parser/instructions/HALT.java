/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Immediate;
import parser.Interpreter;

public class HALT extends Instruction{
		
	public HALT(){
	}
	
	public void runInstruction(Interpreter interpreter){
		System.exit(0);
	}
}
