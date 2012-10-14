/**
* @author: Sean Myers 
*/

package parser;
/*
	This won't change too much. Encodings are fairly constant since there are only 3 instruction types that
	don't deviate from this pattern.

	Encodings: size(bits to encode), starting position(shift amount in encoding)
*/
public enum Encoding{
	FUNCT(6, 0),
	SHAMT(5, 6),
	RD(5, 11),
	RT(5, 16),
	RS(5, 21),
	OPT(6, 26),
	IMM_26(26, 0),
	IMM_16(16, 0);

	int size;
	int startingPosition;
	private Encoding(int size, int startingPosition){
		this.size = size;
		this.startingPosition = startingPosition;
	}

	public int getStartPos(){
		return startingPosition;
	}

	public int getSize(){
		return size;
	}
}
