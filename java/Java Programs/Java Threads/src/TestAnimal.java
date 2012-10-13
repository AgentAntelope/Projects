
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.ldap.ManageReferralControl;



public class TestAnimal implements Runnable{

    /**
     * You need a Thread not in the constructor (but will be assigned in the
     * constructor) so you can call certain functions like sleep and wait.
     * I am not sure if your teacher explained about Scopes of variables,
     * but if you ever need to reference a variable in more than one method,
     * you always have to it defined outside the methods or else the Garbage
     * collection collects them after the method executes. The same applies to
     * constructors. I also put timer in so that the Run Method could use it too.
     *
     */
    Thread MainThread;
    int timer;

    public TestAnimal(int TimeSleep)
    {
        // Threads always need to reference the object they are working with, and
        // if they are defined within the object, just use "this" like so.
        MainThread = new Thread(this);

        //The start method of a Thread just tries to find the run() method and run it.
        MainThread.start();
        timer = TimeSleep;
    }

    //If you implement Runnable, which to use threads you have to, you need this method.
   public void run()
   {
       //I just set up an infinite while loop to show you what is going on...Repeatedly.
       boolean truealways = true;
       while(truealways)
       {

           /** So here is what is going on, I didn't name the individual threads,
            * but since the time is different on each one, I just name them based on
            *  the time that I assigned them..
            * What happens is the thread will go to sleep for the alloted amount of time
            * (in milli-seconds), and then wake up and keep running the program.
            * When it is asleep, it uses NO CPU and wastes no real memory until it
            * wakes up, talk about efficient.
            *
            * On a side note, you have to have a try - catch block around the sleep
            * method since there are certain methods in the Thread class that can
            * wake up the sleeping thread and you need to catch the Interrupted
            * Exception or else the program freaks out. We won't use those methods,
            * but we still need the try-catch block.
            */
           System.out.println("The Thread with timer: " + timer + " just activated and then went to sleep");
            try {
                MainThread.sleep(timer);
            } catch (InterruptedException ex) { }
       }
   }
}