package parser;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import parser.instructions.*;

public class InstructionAssembler{
	public HashMap<String, Integer> labels;
	public ArrayList<Instruction> instructions;

	public InstructionAssembler(){
		labels = new HashMap<String, Integer>();
		instructions = new ArrayList<Instruction>();
	}

	public void addInstruction(Instruction instruction){
		instructions.add(instruction);
	}

	public void addInstruction(String label, Instruction instruction){
		int currentInstruction = instructions.size();
		instructions.add(instruction);
		labels.put(label, new Integer(currentInstruction));
	}

	public void resolve(){
		for(Instruction ins: instructions){
			ins.resolve(labels);
		}
	}
	/* PRINT OUT THE ENTIRE ASSEMBLY! */
	public String toString(){
		StringBuilder ret = new StringBuilder();
		//Print out the assembled instruction.
		System.out.println(instructions.size());
	
		return ret.toString();
	}
}
