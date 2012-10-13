package LateAssign;
import java.util.Iterator;

public class FavoritesList<T>{
	private static final Exception IllegalArgumentException = null;
	private AList<Entry<T>> list;
	private Exception IllegalArgumentExpcetion;

   public FavoritesList(){
	   list = new AList<Entry<T>>();
   }
   
   public int size(){
	   return list.getLength();
   }
   
   public boolean isEmpty(){
	   return list.isEmpty();
   }
   
   public void display(){
	   list.display();
   }
   
   public void access(T item){
	  if(findPosition(item) > -1){
		  list.getEntry(findPosition(item)).incrementCount();
		  moveUp(findPosition(item));
	  }
	  else{
		  System.out.println(item + " not found");
		  list.add(new Entry<T>(item));
	  }
   }
   
   public T remove(T item){
	   Iterator<Entry<T>> iterator = list.getIteratorForAList();
	   Entry<T> entry;
	   while(iterator.hasNext()){
		   entry = iterator.next();
		   if(item.equals(entry.data())){
			   list.remove(findPosition(item));
		   }
	   }
	   return item;
   }
   
   public AList<Entry<T>> topElements(int x){
	   AList<Entry<T>> topElements = new AList<Entry<T>>(x);
	   for(int index=1; index<=x; index++){
		   topElements.add(list.getEntry(index));
	   }
	   //System.out.print("Top " + x + " items: ");
	   return topElements;
   }
   
   public T getData(int position){
	   try{
		   return entry(position).data();
	   }
	   catch(Exception e){
		   System.out.println(e.getMessage());
		   return null;
	   }
   }
   
   ///////////////////////private//////////////////////////////////
  
   private int findPosition(T item){
	 return findPosition(item , 1, list.getIteratorForAList());
	  
   }
  private int findPosition(T item, int position,Iterator<Entry<T>> it){
	if(it.hasNext()){
	  if(item.equals(it.next().data))
		  return position;
	  else
		  return findPosition(item, position+1, it);
	}
	else
		return -1;
	    }
   
   private void moveUp(int position){
	   try{
		   if(position>1){
			   if(entry(position).count() > entry(position-1).count()){
				   list.add((position-1), list.remove(position));
			   }
			   moveUp(position-1);
		   }
	   }
	   catch(Exception e){
		   System.out.println(e.getMessage());
	   }
   }
   
   private Entry<T> entry(int position) throws Exception{
	   if(position >= 0 && position <= list.getLength()){
		   return list.getEntry(position);
	   }
	   else throw IllegalArgumentExpcetion;
   }
   private Entry<T> entry(int position, int max)throws Exception{
	  if(position < list.getLength() &&  position > 0){
	   if(position == max){
		   return list.getEntry(position);
	   	}
	   else
		   return entry(position+1, max);
	  }
	  else
		  throw IllegalArgumentExpcetion;

   }
   
   private static class Entry<T>{
	      private int count;
	      private T data;

	      public Entry(){
	      }

	      public Entry(T item){
	         count = 1;
	         data = item;
	      }

	      public int incrementCount(){
	         return   ++count;
	      }

	      public int count(){
	         return count;
	      }

	      public T data(){
	         return data;
	      }

	      public String toString(){
	         return "(" + data + "," + count + ")";
	      }
   }
}
