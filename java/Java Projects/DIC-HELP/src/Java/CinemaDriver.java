package Java;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


 public class CinemaDriver
{
   public static void main(String[]args)throws IOException
   {
      Film f = null;
      LinkedList<Film>  f1 = new LinkedList<Film>();
     // Scanner inputStream = null;

      
      
       Scanner  inputStream = new Scanner(new File("Movie.txt"));
         String title = inputStream.nextLine();
         String genre = inputStream.nextLine();
         int screenings = inputStream.nextInt();
         int attendance = inputStream.nextInt();
         f = new Film(title,genre,screenings,attendance);
         f1.add(f);
      System.out.println(f1.get(0).getTitle());
   }
}