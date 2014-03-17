package parser;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import parser.instructions.*;


public class Interpreter{
	private ArrayList<Instruction> instructions;
	private char[] registers;
	private char[] memory;
	public static final int REGISTERS = 4;
	public static final int MEMORY_AMOUNT = 256;
	int position;


	public Interpreter(ArrayList<Instruction> instructions){
		this.instructions = instructions;
		registers = new char[REGISTERS];
		memory = new char[MEMORY_AMOUNT];
	}

	public void putInMemory(int position, char mem){
		memory[position] = mem;
	}

	public char getFromMemory(int position){
		return memory[position];
	}

	public int getInstructionPosition(){
		return position;
	}

	public void setInstructionPosition(int newPosition){
		position = newPosition;
	}

	public void setRegister(int registerNum, char value){
		registers[registerNum] = value;
	}

	public char getRegister(int registerNum){
		return registers[registerNum];
	}

	public void nextInstruction(){
		position++;
	}

	public void run(){
		while(true){
			if(position < 0 || position >= instructions.size()){
				System.exit(0);
			}
			Instruction ins = instructions.get(position);
			ins.runInstruction(this);
		}
	}
}
