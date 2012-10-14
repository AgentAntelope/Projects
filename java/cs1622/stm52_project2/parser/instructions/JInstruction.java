/**
* @author: Sean Myers 
*/
package parser.instructions;

import parser.Encoding;
import parser.Immediate;
import parser.InstructionAssembler;


public class JInstruction extends Instruction{
	private Immediate immediate;
	
	public JInstruction(String optCode, Immediate immediate) throws InvalidInstructionException{
		this.opt = translateOpName(optCode); //Opt holds funct info. shamt always is 0
		this.immediate = immediate;
	}

	public void resolveImmediate(InstructionAssembler ass, int place){
		// hehe, late night coding, sorry.
		ass.jump(immediate, place);
	}

	public int assembleInstruction(){
		int encoding = 0;
		encoding = encodeBits(Encoding.OPT.getStartPos(), Encoding.OPT.getSize(), opt.getOpCode(), encoding);
		encoding = encodeBits(Encoding.IMM_26.getStartPos(), Encoding.IMM_26.getSize(), immediate.value(), encoding);
		return encoding;
	}


}