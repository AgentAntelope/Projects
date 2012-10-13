package JListJCombo;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class SimpleList extends JPanel{
	String[] Label = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight"};
	JList list;
	
	public SimpleList(){
		this.setLayout(new BorderLayout());
		list = new JList(Label);
		JScrollPane pane = new JScrollPane(list);
		JButton Button = new JButton("Print");
		Button.addActionListener(new PrintListener());
		
		this.add(pane, BorderLayout.CENTER);
		this.add(Button, BorderLayout.SOUTH);
	}
	
	class PrintListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			int selected[] = list.getSelectedIndices();
			
			System.out.println("The selected:");
			
			for(int i: selected){
				System.out.print((String)list.getModel().getElementAt(i) + ", ");
			}
		}	
	}
	
	public static void main(String[]Args)
	{
		JFrame main = new JFrame("List Example");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setContentPane(new SimpleList());
		main.setSize(250, 200);
		main.setVisible(true);
	}
}
