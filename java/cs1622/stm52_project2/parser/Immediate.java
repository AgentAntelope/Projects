/**
* @author: Sean Myers 
*/
package parser;

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

	public int value(){
		return immediateValue;
	}

}