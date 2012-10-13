import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.TreeSet;
import javax.imageio.ImageIO;
import javax.swing.*;
     
public final class KeyManager implements KeyListener {
   private static Object keyLock = new Object();
   private static KeyManager km = new KeyManager();
   private static JFrame frame;

   // queue of typed key characters
   private static LinkedList<Character> keysTyped = new LinkedList<Character>();

    // set of key codes currently pressed down
    private static TreeSet<Integer> keysDown = new TreeSet<Integer>();

    // not instantiable
    private KeyManager() { }


    // static initializer
    static { init(); }

    private static void init() {
        if (frame != null) frame.setVisible(false);
        frame = new JFrame();

        JLabel draw = new JLabel();

        frame.setContentPane(draw);
        frame.addKeyListener(km); 
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            // closes all windows
        // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      // closes only current window
        frame.setTitle("Key Manager");
        //frame.setJMenuBar(createMenuBar());
        frame.pack();
        frame.requestFocusInWindow();
        frame.setVisible(true);
    }




    /**
     * Has the user typed a key?
     * @return true if the user has typed a key, false otherwise
     */
    public static boolean hasNextKeyTyped() {
        synchronized (keyLock) {
            return !keysTyped.isEmpty();
        }
    }

    /**
     * What is the next key that was typed by the user? This method returns
     * a Unicode character corresponding to the key typed (such as 'a' or 'A').
     * It cannot identify action keys (such as F1
     * and arrow keys) or modifier keys (such as control).
     * @return the next Unicode key typed
     */
    public static char nextKeyTyped() {
        synchronized (keyLock) {
            return keysTyped.removeLast();
        }
    }

    /**
     * Is the keycode currently being pressed? This method takes as an argument
     * the keycode (corresponding to a physical key). It can handle action keys
     * (such as F1 and arrow keys) and modifier keys (such as shift and control).
     * See <a href = "http://download.oracle.com/javase/6/docs/api/java/awt/event/KeyEvent.html">KeyEvent.java</a>
     * for a description of key codes.
     * @return true if keycode is currently being pressed, false otherwise
     */
    public static boolean isKeyPressed(int keycode) {
        return keysDown.contains(keycode);
    }

    /**
     * This method cannot be called directly.
     */
    public void keyTyped(KeyEvent e) {
        synchronized (keyLock) {
            keysTyped.addFirst(e.getKeyChar());
        }
    }

    /**
     * This method cannot be called directly.
     */
    public void keyPressed(KeyEvent e) {
        keysDown.add(e.getKeyCode());
    }

    /**
     * This method cannot be called directly.
     */
    public void keyReleased(KeyEvent e) {
        keysDown.remove(e.getKeyCode());
    }


    public static void main(String[] args){
       KeyManager km = new KeyManager();
       while(true){
       if (km.hasNextKeyTyped()) {
            char key = km.nextKeyTyped();
            System.out.println(key);
       }
       }
    }
}
