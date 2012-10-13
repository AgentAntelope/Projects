package Tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Form extends JFrame{

	JButton test;
	ActionPow sup;
	Form yar;
	public Form(){
		super("Test");
		yar = this;
		test = new JButton("Yar!");
		sup = new ActionPow();
		test.addActionListener(sup);
		this.add(test);
		setSize(400,400);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible(true);
	}
	class ActionPow implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		    JFrame frame = new JFrame("Frame in Java Swing");
		    frame.setSize(400, 400);
	//	    yar.setEnabled(false);
		    frame.setVisible(true);
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		}
		
	}
	
	public static void main(String[]Args)
	{
		new Form();
	}
}
