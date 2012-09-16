
public class TransactionNode {
	String description, tx_ID;

	public TransactionNode(String desc, String tx_ID){
		description = desc;
		this.tx_ID =tx_ID;
	}
	
	public String toString(){
		String type;
	
		return "You " + description + "transaction ID: " + tx_ID; 
	}
}
