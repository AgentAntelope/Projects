/**
* @author: Sean Myers 
*/
package parser;
import java.util.*;
/* A proxy that is only useable if the assembler resolves them before an instruction tries to 
assemble itself. Holds a label or an immediate. By the resolution phase, should just hold an immediate. */
public class Immediate{
	public String label;
	private int immediateValue;
	public boolean isLabel;

	public Immediate(int immediate){
		isLabel = false;
		immediateValue = immediate;
	}
	public Immediate(String label){
		this.label = label;
		isLabel = true;
	}
	public void setImmediate(int immediate){
		immediateValue = immediate;
	}

	public void resolve(Map<String, Integer> labels){
		if(isLabel){
			immediateValue = labels.get(label);
		}
	}
	public int value(){
		return immediateValue;
	}

}
