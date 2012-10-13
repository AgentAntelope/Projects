/**
* @author: Sean Myers 
*/
package parser.instructions;
import parser.Operation;
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

		// Create a bit mask fo
		for (int i = 0; i < bitAmount; i++) {
			mask += bitShifted;
			bitShifted = bitShifted << 1;
		}
		int maskedBits = toEncode & mask;
		mask = mask << shiftAmount;
		mask = ~mask;
		currentEncoding = currentEncoding & mask;
		maskedBits = maskedBits << shiftAmount;
		currentEncoding = currentEncoding | maskedBits;
		return currentEncoding;
	}


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
	

	/* Have your sub classes implement this! */
	abstract public int assembleInstruction();
	public ArrayList<Instruction> resolveInstructions(){
		ArrayList<Instruction> temp = new ArrayList<Instruction>();
		temp.add(this);
		return temp;
	}
}


class InvalidInstructionException extends Exception{
	public InvalidInstructionException(String code){
		super("Tried to use " + code + " but that isn't allowed.");
	}
}