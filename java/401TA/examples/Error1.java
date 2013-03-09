import java.util.*;
import java.io.*;
public class Error1{

    public static void main(String[] args){

        try{
            Scanner a = new Scanner(new File("Error1.javafdsfds"));
            Integer.parseInt("Hello");

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(Exception e){
            System.out.println("An error occured");
        }

     }
}
