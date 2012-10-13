package BinaryProject;

import java.util.*;
import java.io.*;

public class PartB{
private static boolean dups = false;
private static File file;
private static Scanner input;
private static String strings;
private static String[] objects;
public static void main(String[] args){
if(args.length>0){
if(args[0].contains("dups")){
dups = true;
file = new File(args[1]);
}
else{
file = new File(args[0]);
}
try {
input = new Scanner(file);
} catch (FileNotFoundException e) {
e.printStackTrace();
}
while(input.hasNext()){
strings = input.nextLine();
}
objects = strings.split(" ");
BinarySearchTree<String> tree = new BinarySearchTree<String>(dups);
for(String x : objects){
tree.add(new BinaryNode<String>(x));
}
printTree(tree.root);
}
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