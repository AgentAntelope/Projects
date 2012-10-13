package Rando;
import javax.swing.*;
public class ShowWindow 
{

	public static void main(String[]args)
	{
		final int WindowWidth = 350;
		final int WindowHeight= 250;
		
		//Create a window
		JFrame window = new JFrame();
		
		//Sets the title
		window.setTitle("A Simple Window");
		
		//Set the size of your window
		window.setSize(WindowWidth, WindowHeight);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setVisible(true);
	}
}
