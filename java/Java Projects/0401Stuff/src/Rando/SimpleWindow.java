package Rando;
import javax.swing.*;

public class SimpleWindow extends JFrame
{
	public SimpleWindow()
	{
	final int WindowWidth = 350;
	final int WindowHeight= 250;
	
	
	//Sets the title
	setTitle("A Simple Window");
	
	//Set the size of your window
	setSize(WindowWidth, WindowHeight);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	setVisible(true);
	}
}
