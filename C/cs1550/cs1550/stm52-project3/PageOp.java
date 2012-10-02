
public class PageOp extends Page{

	private int position; //Hopefully there is less than 2.1 billion instructions.
	
	public PageOp(String mem, String Operation, int pos){
		super(mem, Operation);
		position = pos;
	}
	public int getPosition(){
		return position;
	}
}
