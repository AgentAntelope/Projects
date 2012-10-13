/**
* @author: Sean Myers 
*/
package parser.instructions;

import parser.Immediate;

public class JInstruction extends Instruction{
	private Immediate immediate;
	
	public RInstruction(String optCode, Immediate immediate) throws InvalidInstructionException{
		this.opt = translateOpName(optCode); //Opt holds funct info. shamt always is 0
		this.immediate = immediate;
	}

	public int assembleInstruction(){
		int encoding = 0;
		encoding = encodeBits(Encoding.OPT.getStartPos(), Encoding.OPT.getSize(), opt.getOpCode(), encoding);
		// TODO: FIX TO NOT 0
		encoding = encodeBits(Encoding.IMM_24.getStartPos(), Encoding.IMM_24.getSize(), 0, encoding);
		return encoding;
	}


}