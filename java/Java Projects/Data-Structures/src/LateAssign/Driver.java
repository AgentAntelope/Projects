package LateAssign;

public class Driver{
   public static void main(String[] args){
      FavoritesList<String> list = new FavoritesList();

      list.access("Bob");
      list.access("Carol");
      list.access("Anne");
      list.access("Tim");
      list.access("Cal");
      list.access("Art");

      if(!list.isEmpty()) list.display();

      System.out.println("\nsize of list = " + list.size());
      
      System.out.println("\nadd: Tim");

      list.access("Tim");
      list.display();
      
      
      System.out.println("\nadd: Tim");

      list.access("Tim");
      list.display();
      System.out.println("\nadd: Cal");

      list.access("Cal");
      list.display();
      System.out.println("\nadd: Art");

      list.access("Art");
      list.display();
      System.out.println("\nadd: Art");

      list.access("Art");
      list.display();
      System.out.println("\nadd: Cal");


      list.access("Cal");
      list.display();
      System.out.println("\nadd: Cal");

      list.access("Cal");
      list.display();        
      
      System.out.print("\nTop 4 items: ");

      list.topElements(4).display();
      System.out.println("\nremove: Tim");

      list.remove("Tim");
      list.display();
      
      System.out.println("\nremove: Tim");

     list.topElements(4).display();
     System.out.println();
 
   }
}
