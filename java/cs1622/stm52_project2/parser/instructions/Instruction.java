/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Operation;
import parser.Immediate;
import parser.InstructionAssembler;
import java.util.ArrayList;

public abstract class Instruction{
	protected Operation opt;


	/* Tries to translate the string name into an Operation enum. Throws InvalidInstructionException if
	it can't find the enum. */
	protected Operation translateOpName(String opt) throws InvalidInstructionException{
		for(Operation i: Operation.values()){
			if (opt.equalsIgnoreCase(i.toString())){
				return i;
			}
		}
		throw new InvalidInstructionException(opt);
	}


	/**
	*	@param shiftAmount: The amount to shift the encoding to. e.g: If you want to start at bit 26 in an int, then set this to 26.
	*	@param bitAmount: The amount of bits this encoding will fill. A register takes 5 bits, so someone would put 5 here.
	*	@param toEncode: The actual value you want to shift and put into the encoding.
	*	@param currentEncoding: This is the int you want to encode into. 
	*	@return return the updated currentEncoding that now has the toEncode in it as well.
	*/
	public static int encodeBits(int shiftAmount, int bitAmount, int toEncode, int currentEncoding){
		int mask = 0;
		int bitShifted = 1;

		// Create a bit mask for toEncode to make sure it doesn't delete anything
		for (int i = 0; i < bitAmount; i++) {
			mask += bitShifted;
			bitShifted = bitShifted << 1;
		}
		//Make sure toEncode isn't too big here.
		int maskedBits = toEncode & mask;

		// Clear out the currentEncoding if anything is in there.
		mask = mask << shiftAmount;
		mask = ~mask;
		currentEncoding = currentEncoding & mask;

		//Insert bits into the cleared space.
		maskedBits = maskedBits << shiftAmount;
		currentEncoding = currentEncoding | maskedBits;
		return currentEncoding;
	}

	/* Helpful little function for printing all the bits of an integer. */
	public static void printBits(int num){
		String temp = "";
		String temp2 = "";
		int currentBit = 1;
		for(int i = 0; i < 32; i++){
			if((num & currentBit) != 0){
				if(i > 8){
					temp = "|" +  1 + " |" + temp;

				}
				else{
					temp = "|" +  1 + "|" + temp;
				}

			}
			else{
				if(i > 8){
					temp = "|" +  0 + " |" + temp;

				}
				else{
					temp = "|" +  0 + "|" + temp;
				}			}

			temp2 = "|" + (i + 1) + "|" + temp2;
			currentBit = currentBit << 1;
		}
		System.out.println(temp);
		System.out.println(temp2);
	}
	
	/* Implemented to stop annoying the fuck out out of me trying to implement this in RInstruction and PseudoInstruction */
	public void resolveImmediate(InstructionAssembler ins, int place){}

	/* Have your sub classes implement this since each is so vastly different. */
	abstract public int assembleInstruction();

}

/* Just for the lolz, and for people trying to bs the instructions! */
class InvalidInstructionException extends Exception{
	public InvalidInstructionException(String code){
		super("Tried to use " + code + " but that isn't allowed.");
	}
}