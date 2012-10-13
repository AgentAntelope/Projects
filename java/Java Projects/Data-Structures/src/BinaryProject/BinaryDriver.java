package BinaryProject;

import java.util.Scanner;

public class BinaryDriver {

	public static void main(String[]Args){

		System.out.print("input marked preorder listing: ");
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.nextLine();
		input.replaceAll("\\W","");
		char[] items = new char[input.length()/2];
		int[] marks = new int[input.length()/2];
		int index = 0;
		for(int x = 0; x < input.length()-1; x++){
		items[index] = input.charAt(x);
		marks[index] = Integer.parseInt("" + input.charAt(x+1));
		index++;
		x++;
		}
		int[] currNumber = new int[1];
		currNumber[0] = 0;
		
		BinaryNode<Character>  a= makeTree(marks, items, currNumber);
		printTree(a);
		//System.out.println(a.getRightChild().getRightChild().getRightChild().getData());
	}
	
	public static BinaryNode<Character> makeTree(int[] treeNodeInfo, char[] dataNodeInfo, int[] currNumber){
		BinaryNode<Character> root;  
		if(treeNodeInfo[currNumber[0]] == 1) {
		       root = new BinaryNode<Character>(dataNodeInfo[currNumber[0]]);
		       currNumber[0]+= 1;
		       root.setLeftChild(makeTree(treeNodeInfo, dataNodeInfo, currNumber));
		       root.setRightChild(makeTree(treeNodeInfo, dataNodeInfo, currNumber));
		    }
		   else{
		        root = new BinaryNode<Character>(dataNodeInfo[currNumber[0]]);
		        currNumber[0] += 1;
		       }
		   return root;
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


}
