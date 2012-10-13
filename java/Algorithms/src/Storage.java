/**  @Author: Sean Myers (Iph)
 *  Email: Seanmyers0608@gmail.com
 *  Project: NetworkFlow
 *  
 */
import java.util.ArrayList;

public class Storage{
	private ArrayList<Node> a;
	public Storage(){
		a = new ArrayList<Node>();
	}
	public Node retrieve(String b){
		for(Node c: a){
			if(c.name.equalsIgnoreCase(b)){
				return c;
			}
		}
		 return null;
	}
	public void store(Node b){
		a.add(b);
	}
}
