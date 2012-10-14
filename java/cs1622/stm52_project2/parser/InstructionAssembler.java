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

	/**
	*	Resolves all label conflicts and builds the string labels in data.
	*/
	public void resolve(){
		resolveData();
		walkInstructions();
	}

	/* Resolves all data elements. */
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
				char currentChar = str.charAt(currentStrPos);
				if((currentStrPos+1) < str.length() && currentChar == '\\' && str.charAt(currentStrPos+1) == 'n'){
					currentChar = '\n';
					currentStrPos++;
				}				

				// Encode current position
				int placeholder = currentChar;
				placeholder = placeholder << (8 * (3 - encodingPosition));

				currentEncoding = currentEncoding | placeholder;
				encodingPosition++;
				position++;

				// If fully encoded, then save
				if( encodingPosition % 4 == 0 && encodingPosition != 0){
					Data d = new Data(position-4, currentEncoding);
					resolvedStrings.add(d);					
					currentEncoding = 0;				
					encodingPosition = 0;
				}
				// Check if it is \n

			}
			int placeholder = 0;
			placeholder = placeholder << (8 * (3 - encodingPosition));
			currentEncoding = currentEncoding | placeholder;
			encodingPosition++;
			position++;
			if(encodingPosition!= 0){
				position += (position % 4);
				Data d = new Data(position-4, currentEncoding);
				resolvedStrings.add(d);						
				currentEncoding = 0;
				encodingPosition = 0;
			}
		}
		if(encodingPosition != 0){
				Data d = new Data(position-4, currentEncoding);
				resolvedStrings.add(d);		
		}
	
	}

	/* Walks the instructions resolving all the labels in them. */
	public void walkInstructions(){
		int place = 0;
		for(Instruction ins: instructions){
			ins.resolveImmediate(this, place);
			place++;
		}
	}

	public void offset(Immediate imm, int place){
		if(!imm.isLabel){
			
		}
		else{
			int offsetPosition = labels.get(imm.label);
			offsetPosition = place - offsetPosition;
			//offsetPosition *= 4;
			imm.setImmediate(-offsetPosition);
		}
	}
	public void upper(Immediate imm, int place){
		if(!imm.isLabel){
			
		}
		else{
			int position = translateLabel(imm.label);
			position = position >> 16;

			imm.setImmediate(position);
		}
	}
	public void lower(Immediate imm, int place){
		if(!imm.isLabel){
			
		}
		else{
			int position = translateLabel(imm.label);
			position = position & 0x0000FFFF;
			imm.setImmediate(position);
		}
	}

	public int translateLabel(String label){
		int labelPosition = 0;
		if(labels.get(label) == null && resolvedLabels.get(label) == null){
			System.out.println("Warning: NO LABEL, SOMETHING HAS GONE WRONG!");
		}
		else if(labels.get(label) == null){
			labelPosition = resolvedLabels.get(label);
		}
		else{
			int textPosition = 0x00400000;
			labelPosition = labels.get(label);
			labelPosition *= 4;
			labelPosition += textPosition;
		}

		return labelPosition;
	}
	public void jump(Immediate imm, int place){
		if(!imm.isLabel){
		}
		else{
			int labelPosition = translateLabel(imm.label);
			// Calculate only the first 26 bits
			labelPosition = labelPosition & 0x0FFFFFFF;
			labelPosition = labelPosition >> 2;
			imm.setImmediate(labelPosition);
		}
	}

	/* PRINT OUT THE ENTIRE ASSEMBLY! */
	public String toString(){
		StringBuilder ret = new StringBuilder();
		//Print out the assembled instruction.
		for(Instruction i: instructions){
			ret.append(String.format("0x%08x\n", i.assembleInstruction()));
		}

		ret.append("DATA SEGMENT\n");
		for(Data i: resolvedStrings){
			ret.append(String.format("0x%08x 0x%08x\n", i.getHeapPosition(), i.getStrData()));

		}
	
		return ret.toString();
	}
}