/**
* @author: Sean Myers 
*/
package parser.instructions;

public class RAND extends Instruction{
	private int rs, rt, rd;
		
	public RAND(String rs, String rt){
		this.rs = Integer.parseInt(rs.substring(2));
		this.rt = Integer.parseInt(rt.substring(2));
	}

}
