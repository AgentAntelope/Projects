package BinaryProject;

public class BinarySearchTree<T extends Comparable<? super T>> {
	BinaryNode<T> root;
	boolean dup;
	public BinarySearchTree(){
		root = null;
	}
	public BinarySearchTree(boolean dupl){
		dup = dupl;
		root = null;
	}
	
	public void add(BinaryNode<T> child){
		root = add(child, root);
	}
	
	public BinaryNode<T> add(BinaryNode<T> newNode, BinaryNode<T> tempRoot){
		
		if(tempRoot == null){
			return newNode;
		}
		else if(tempRoot.getData().compareTo(newNode.getData()) < 0){
			tempRoot.setRightChild(add(newNode, (BinaryNode<T>)tempRoot.getRightChild()));
		}
		else if(tempRoot.getData().compareTo(newNode.getData()) > 0){
			tempRoot.setLeftChild(add(newNode, (BinaryNode<T>)tempRoot.getLeftChild()));
		}
		else if((tempRoot.getData().compareTo(newNode.getData()) == 0)){
			if(dup)
				return tempRoot;
			else
				tempRoot.setLeftChild(add(newNode, (BinaryNode<T>)tempRoot.getLeftChild()));

		}
		return tempRoot;

//	return tempRoot;
	}

	 public static <T> void printTree(BinaryNodeInterface<T> p){
		    printTree(p,0);
		 }


		 public static <T> void printTree(BinaryNodeInterface<T> p, int level){
			 if(p!=null){
			    printTree(p.getRightChild(),level+1);
			    for(int j=1; j<=level; j++) System.out.printf("     ");
				System.out.printf("%6s%n%n",p.getData());
			      printTree(p.getLeftChild(),level+1);
			    }
		   }
	
	public static void main(String[]Args){
		BinarySearchTree<String> a = new BinarySearchTree<String>();
		a.add(new BinaryNode<String>("c"));
		a.add(new BinaryNode<String>("b"));
		a.add(new BinaryNode<String>("d"));
		a.add(new BinaryNode<String>("c"));

		printTree(a.root);
	}
}
