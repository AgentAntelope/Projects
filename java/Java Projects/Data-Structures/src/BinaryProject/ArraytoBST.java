package BinaryProject;

public class ArraytoBST <T extends Comparable<? super T>>{
	public  BinaryNode<T> FruitLoop(T[] a, int low, int high){
		int mid = (low+high)/2;
		if(high-low > 1){
			BinaryNode<T> tucanSam = new BinaryNode<T>(a[mid]);
			tucanSam.setLeftChild(FruitLoop(a, low, mid));
			tucanSam.setRightChild(FruitLoop(a, mid+1, high));
			return tucanSam;
		}
		else if(high-low == 1){
			return new BinaryNode<T>(a[mid]);
		}
		else 
			return null;
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
		String[] a = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
		ArraytoBST<String> b = new ArraytoBST<String>();
		BinaryNode<String> yar = b.FruitLoop(a, 0, a.length);
		printTree(yar);
		//System.out.println(yar.getData());
		//System.out.println(yar.getLeftChild().getData()+ " " +yar.getRightChild().getData());
	}
}
