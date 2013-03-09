package graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Graphical1 {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Hello world!");
		JLabel label = new JLabel("Hello world!");
		frame.add(label);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
class ButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		e.
		// TODO Auto-generated method stub
		
	}
	
}
}