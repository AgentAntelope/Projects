
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphicalTest {

    /**
     *   * @param args
     *       */
    public static void main(String[] args) {
            JFrame frame = new JFrame("Hello world!");
            JLabel label = new JLabel("Inner Text");
            JLabel label2 = new JLabel("Other text");
            JButton button = new JButton("Click Me!!!");
            JButton button2 = new JButton("Other Click me!!");
            ButtonHandler b = new ButtonHandler();
            button.addActionListener(b);
            button2.addActionListener(b);
            JPanel panel = new JPanel();
            JPanel panel2 = new JPanel();
            JTextArea t = new JTextArea(10, 10);
            GridLayout grid = new GridLayout(1,2);
            GridLayout innerGrid = new GridLayout(2, 1);
            panel.setLayout(grid);
            panel2.setLayout(innerGrid);
            panel2.add(button2);
            panel2.add(button);
            panel.add(panel2);
            panel.add(t);
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
}
class ButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.out.println("Performed action!");
    }
}


