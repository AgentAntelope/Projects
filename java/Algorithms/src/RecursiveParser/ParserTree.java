package RecursiveParser;
/**
 * @author Sean Myers
 * email: seanmyers0608@gmail.com
 *
 */
public class ParserTree {
	private ParserTree left, right;
	String currentRule;
	int words;
	/**
	 * @param toBeAdded- Rule to be checked.
	 */
	public ParserTree(String toBeAdded){
		currentRule = toBeAdded;
		words = 1;
	}
	public ParserTree(String root, ParserTree left){
		currentRule = root;
		this.left = left;
		words = left.words;
		right = null;
	}
	public ParserTree(String root, ParserTree left, ParserTree right){
		currentRule = root;
		this.left = left;
		this.right = right;
		words = left.words + right.words;
	}
	/**
	 * @param rightTree- Adds the right side of the current Parser tree
	 */
	public void addRightChild(ParserTree rightTree){
		right = rightTree;
	}
	
	/**
	 * @param leftTree- adds teh left side of the current Parser Tree.
	 */
	public void addLeftChild(ParserTree leftTree){
		left = leftTree;
	}
	public void makeString(ParserTree root, StringBuilder b){
		System.out.print("( "+root.currentRule+" ");
		if(root.left!=null){
			makeString(root.left, b);
		}
		if(root.right!= null){
			makeString(root.right, b);
		}
		System.out.print(")");
	}

	public String toString(){
		StringBuilder b = new StringBuilder();
		makeString(this, b);
		System.out.println("\n");
		return b.toString();
	}
}
