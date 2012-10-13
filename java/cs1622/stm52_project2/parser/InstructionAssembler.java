package parser;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import parser.instructions.*;
public class InstructionAssembler{
	HashMap<String, Integer> resolvedLabels, labels;
	ArrayList<String> declaredStrings;
	ArrayList<String> associativeLabel;
	ArrayList<Data> resolvedStrings;
	ArrayList<Instruction> instructions;

	public InstructionAssembler(){
		resolvedLabels = new HashMap<String, Integer>();
		labels = new HashMap<String, Integer>();
		resolvedStrings = new ArrayList<Data>();
		instructions = new ArrayList<Instruction>();
		declaredStrings = new ArrayList<String>();
		associativeLabel = new ArrayList<String>();
	}

	public void addInstruction(Instruction instruction){
		instructions.add(instruction);
	}

	public void addInstruction(String label, Instruction instruction){
		int currentInstruction = instructions.size();
		instructions.add(instruction);
		labels.put(label, new Integer(currentInstruction));
	}
	public void addData(String label, String val){
		associativeLabel.add(label);
		declaredStrings.add(val);
	}
	public void resolve(){
		resolveData();
		System.out.println("DATA BITCHES");
		for(Data i: resolvedStrings){
			System.out.printf("0x%08x  0x%08x\n", i.getHeapPosition(), i.getStrData());

		}
		//walkInstructions();
	}

	public void resolveData(){
		int position = 0x10010000;
		int currentEncoding = 0;
		int encodingPosition = 0;
		ArrayList<Integer> encodings = new ArrayList<Integer>();
		for(int i = 0; i < declaredStrings.size(); i++){
			String str = declaredStrings.get(i);
			String label = associativeLabel.get(i);
			if(label != null){
				resolvedLabels.put(label, position);
			}
			str = str.substring(1, str.length()-1);
			//////////Encode string
			for(int currentStrPos = 0; currentStrPos < str.length(); currentStrPos++){
				if( encodingPosition % 4 == 0 && encodingPosition != 0){
					encodings.add(currentEncoding);
					currentEncoding = 0;				
					encodingPosition = 0;
				}

				char currentChar = str.charAt(currentStrPos);
				if((currentStrPos+1) < str.length() && currentChar == '\\' && str.charAt(currentStrPos+1) == 'n'){
					currentChar = '\n';
					currentStrPos++;
				}				

				int placeholder = currentChar;
				placeholder = placeholder << (8 * encodingPosition);
				currentEncoding = currentEncoding | placeholder;
				encodingPosition++;
			}
			if( encodingPosition % 4 == 0 && encodingPosition!= 0){
				encodings.add(currentEncoding);
				currentEncoding = 0;
				encodingPosition = 0;
			}
			int placeholder = 0;
			placeholder = placeholder << (8 * encodingPosition);
			currentEncoding = currentEncoding | placeholder;
			encodingPosition++;
		}
		if(encodingPosition % 4 != 0){
			encodings.add(currentEncoding);
		}

		for(int encoding: encodings){
			Data d = new Data(position, encoding);
			resolvedStrings.add(d);
			position += 4;
		}	
	}

	public int offset(Immediate imm, Instruction current){
		if(!imm.isLabel){
			return imm.value();
		}
		else{
			/* TODO: Return the offset of the instruction.  */
			return 0;
		}
	}

	public int jump(Immediate imm){
		if(!imm.isLabel){
			return imm.value();
		}
		else{
			/* TODO: Return the jump of the instruction.  */
			return 0;
		}
	}

	public String toString(){
		String ret = "";
		for(Instruction i: instructions){
			System.out.printf("0x%08x\n", i.assembleInstruction());
		}
		return ret;
	}
}