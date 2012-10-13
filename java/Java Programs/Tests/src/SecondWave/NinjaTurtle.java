/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SecondWave;

/**
 *
 * @author Sean Myers
 */
public class NinjaTurtle implements Runnable {

       Thread myThread;
       String myWord;

       public NinjaTurtle(String givenWord)
       {
        myThread = new Thread(this);
        myWord = givenWord;
       // myThread.setDaemon(true);
        myThread.start();

       }
    public void run() {
    System.out.println("Hello World");
    }

    public static synchronized void runWords(String Word)
    {
        System.out.println(Word);
    }
    public static void main(String[]Args)
    {
        NinjaTurtle Blue =new NinjaTurtle("Sup");
        NinjaTurtle Red = new NinjaTurtle("Bro");
        boolean yar = true;
        while(yar)
        {
            Blue.runWords("Sup");
            Red.runWords("Not Muchh");
        }


    }
}
