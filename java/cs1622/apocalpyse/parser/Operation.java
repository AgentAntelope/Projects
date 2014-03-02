/**
* @author: Sean Myers 
*/
package parser;

/* If you want to add a real (no pseudo) instruction, add it to this list.*/
public enum Operation{
	ADDI 	(0x8, 0x0),
	ADD  	(0x0, 0x20),
	SUB  	(0x0, 0x22),
	SLT		(0x0, 0x2A),
	BEQ		(0x4, 0x0),
	DISP	(0x0, 0xC),
	SB		(0x2b, 0x0),
	LB		(0x20, 0x0),
	J		(0x2, 0x0),
	RAND	(0xD, 0x0),
	XOR		(0x0, 0x27),
	HALT    (0x0, 0xA);

	int funct;
	int opCode;

	private Operation(int opCode, int funct){
		this.opCode = opCode;
		this.funct = funct;
	}

	public int getFunct(){
		return this.funct;
	}

	public int getOpCode(){
		return this.opCode;
	}
}
