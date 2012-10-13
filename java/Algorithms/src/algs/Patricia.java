package algs;
/**
 * 
 * @author sean
 *	Date Dec 5th 2010
 *	Patricia.
 *	Problems: None.
 *	Class: Algorithm implementation
 */
public class Patricia {
	Node head;
	private class Node{
		Boolean isLeaf;
		Object value;
		String key;
		Node left, right;
		int bit;
		public Node(String key, Object value){
			this.key = key;
			this.value = value;
			isLeaf = true;
		}
		public Node(){
			isLeaf = false;
			bit = 0;
		}
		public Node(int n){
			isLeaf = false;
			bit = n;
		}
		public String toString(){
			String toReturn;
			if(this.isLeaf){
				toReturn = "I'm a leaf that holds:"+ value;
			}
			else{
				toReturn = "Im a link with discr at: " + bit;
			}
			return toReturn;
		}
	}
	public Patricia(){
		head = new Node();
	}
	public void put(String key, Object value){
		insert(key, value);
	}
	/*
	 * Searches the tree, and gets a child (or null)
	 */
	public Node search(Node current, String key){
		if(current == null)
			return null;
		else if(current.isLeaf){
			return current;
		}
		else{
			if(bit(key, current.bit) == 0){
				return search(current.left, key);
			}
			else{
				return search(current.right, key);
			}
		}
	}
	public Object get(String key){
		Node foo = search(head, key);
		if(foo!=null)
			return foo.value;
		return null;
	}
	public void insert(String key, Object value){
		Node test = search(head, key);
		if(test == null){
			head = insertR(key, head,value);
		} 
		else{
			int where = discrBit(key, test.key);
			head = insertSplit(key, head, where,value, null);
		}
	}
	public Node insertR(String key, Node current, Object value){
		if(current == null){
			return new Node(key,value);
		}
		else{
			if(bit(key, current.bit) == 0){
				current.left = insertR(key, current.left, value);
			}
			else{
				current.right = insertR(key, current.right, value);
			}
		}
		return current;
	}
	public Node insertSplit(String key, Node current, int bit,Object value, Node previous){

			
			if(current.isLeaf){
				Node rawr = new Node(bit);
				if(bit(key, bit) == 0){
					rawr.left = new Node(key, value);
					rawr.right = current;
					return rawr;
				}
				else{
					rawr.right = new Node(key, value);
					rawr.left = current;
					return rawr;
				}
			}
			else if(bit < current.bit){
				Node temp = new Node(bit);
				if(previous.left == current){
					previous.left = temp;
				}
				else{
					previous.right = temp; 
				}
				if(bit(key, bit) == 0){
					temp.left = new Node(key, value);
					temp.right = current;
					return temp;
				}
				else{
					temp.right = new Node(key, value);
					temp.left = current;	
					return temp;
				}
			}
			else{
				if(bit(key, current.bit) == 0){
						current.left = insertSplit(key, current.left, bit, value, current);
						return current;
					}
				else{
						current.right = insertSplit(key, current.right, bit, value, current);
						return current;
					}
		
			}
	}
	private int discrBit(String one, String two){
			int bitToGo = 0;
			while(true){
				if(bit(one, bitToGo) != bit(two, bitToGo))
					break;
				
				bitToGo++;
			}
			return bitToGo;
		}
    private int bit(String s, int n) {
	    if ( s.equals("ANCHOR") ) { return 0 ; }
	    if ( n >= s.length()*16 ) { return 1 ; }
	    char c = s.charAt( n / 16 ) ;
	    n = n%16 ;
	    return (c >> ((16-1)-n) & 1) ;
	  }


}
