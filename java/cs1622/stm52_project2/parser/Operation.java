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
	BNE		(0x5, 0x0),
	SYSCALL	(0x0, 0xC),
	LW		(0x23, 0x0),
	SW		(0x2b, 0x0),
	LB		(0x20, 0x0),
	J		(0x2, 0x0),
	SLL 	(0x0, 0x0),
	LUI		(0xF, 0x0),
	AND		(0x0, 0x24),
	ORI		(0xD, 0x0),
	NOR		(0x0, 0x27);

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