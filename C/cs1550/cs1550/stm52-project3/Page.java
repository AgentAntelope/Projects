/**
 * 
 * @author Sean
 *
 * This class is used in the linked list for Pages that are read from that large file.
 */

public class Page {
	private String memLocation; //Memory name
	public boolean memRead, memWrite; //If it has been reference or modified respectively
	public boolean op; //0(false) is a read, 1(true) is a write.
	
	public Page(String mem, String Operation){
		memLocation = mem;
		memRead = false;
		memWrite = false;
		if(Operation.equalsIgnoreCase("W")){
			op = true;
		}
		else{
			op = false;
		}
	}
	
	public String getMemLocation(){
		return memLocation;
	}
	public void setRead(boolean read){
		memRead = read;
	}
	public void setModified(boolean write){
		memWrite = write;
	}
	public boolean getModified(){
		return memWrite;
	}
	public boolean getRead(){
		return memRead;
	}
}
