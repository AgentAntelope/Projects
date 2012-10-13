package SecondWave;

import java.util.ArrayList;

public class Turtle extends Animal
{
   public static void main(String[]Args)
   {
       ArrayList<Integer> Test = new ArrayList();
       Test.add(30);
       Test.add(40);
       Test.add(20);
       Test.add(50);

       for(int index: Test)
       {
           System.out.println(index);
       }
       System.out.println(SIZE);
   }
}