/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Operation;
import parser.Immediate;
import parser.InstructionAssembler;
import java.util.ArrayList;

public abstract class Instruction{

	/* Implemented to stop annoying the fuck out out of me trying to implement this in RInstruction and PseudoInstruction */
	public void resolveImmediate(InstructionAssembler ins, int place){}

	/* Have your sub classes implement this since each is so vastly different. */
	abstract public int runInstruction(Interpreter interpreter);

}

/* Just for the lolz, and for people trying to bs the instructions! */
class InvalidInstructionException extends Exception{
	public InvalidInstructionException(String code){
		super("Tried to use " + code + " but that isn't allowed.");
	}
}
