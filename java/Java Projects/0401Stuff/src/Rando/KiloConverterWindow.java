package Rando;
import javax.swing.*;
import java.awt.event.*;

public class KiloConverterWindow extends JFrame
{
private JPanel panel;
private JLabel messageLabel;
private JLabel messageLabel2;
private JTextField kiloTextField;
private JButton calcButton;
private final int WindowWidth = 350;
private final int WindowHeight = 100;

public KiloConverterWindow()
{
	setTitle("Kilometer Converter");
	
	//Set the size of your window
	setSize(WindowWidth, WindowHeight);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	buildPanel();
	
	add(panel);
	
	setVisible(true);
}

private void buildPanel()
{
	//Create a label to display instructions.
	messageLabel = new JLabel("Enter a distance " + 
								"in kilometers");
	messageLabel2= new JLabel("Enter Something else");
	//Create a text field with 10 Characters wide
	kiloTextField = new JTextField(10);
	
	//Creates a button with the caption Calculate
	calcButton = new JButton("Calculate Slug Percent");
	
	//Add an action listener to the button.
	calcButton.addActionListener(new CalcButtonListener());
	
	panel = new JPanel();
	
	panel.add(messageLabel);
	panel.add(kiloTextField);
	panel.add(messageLabel2);
	panel.add(calcButton);
	
}
/**
 * CalcButtonListener is an action listener class for the calculate button
*/
private class CalcButtonListener implements ActionListener
 {
public void actionPerformed(ActionEvent e)
	{
String input;
double miles;

input = kiloTextField.getText();
miles= Double.parseDouble(input)*.6214;

JOptionPane.showMessageDialog(null, input + " kilometers is "+ miles + " miles");
	
	}
 }

}