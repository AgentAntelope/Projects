/**
* @author: Sean Myers 
*/
package parser.instructions;

import parser.Encoding;
import parser.Immediate;
public class IInstruction extends Instruction{
	private int rs, rt;
	private Immediate immediate;
	public IInstruction(String optCode, String rt, String rs, Immediate immediate) throws InvalidInstructionException{
		this.opt = translateOpName(optCode);
		this.rs = Integer.parseInt(rs.substring(2)); 
		this.rt = Integer.parseInt(rt.substring(2)); 
		this.immediate = immediate;
	}
	public IInstruction(String optCode, String rs, Immediate immediate) throws InvalidInstructionException{
		this.opt = translateOpName(optCode);
		this.rs = Integer.parseInt(rs.substring(2)); 
		this.immediate = immediate;
	}

	public int assembleInstruction(){
		int encoding = 0;
		encoding = encodeBits(Encoding.RS.getStartPos(), Encoding.RS.getSize(), rs, encoding);
		encoding = encodeBits(Encoding.RT.getStartPos(), Encoding.RT.getSize(), rt, encoding);
		encoding = encodeBits(Encoding.OPT.getStartPos(), Encoding.OPT.getSize(), opt.getOpCode(), encoding);
		// TODO: FIX 0 TO IMMEDIATE RESOLVER!
		encoding = encodeBits(Encoding.IMM_16.getStartPos(), Encoding.IMM_16.getSize(), immediate.value(), encoding);
		return encoding;
	}

}