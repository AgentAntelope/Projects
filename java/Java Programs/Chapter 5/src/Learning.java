import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

public class Learning {
private JFrame MainFrame;
private MyPanel Main;
int size;
Snowman Snowman;

public Learning(int size)
{
	this.size = size;
	MainFrame = new JFrame("Hello Frame");
	MainFrame.setSize(300, 300);
	MainFrame.setVisible(true);
	Main = new MyPanel();
	MainFrame.add(Main);
	MainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	Snowman = new Snowman(0,0,25);
	
}
	
public static void main(String[]Args)
{
	new Learning(10);
}
	
class MyPanel extends JPanel implements MouseMotionListener 

	{
int X= 0;
int Y = 0;
private Shape Face;

public MyPanel()
{
    addMouseMotionListener( this );
}
	public void paintComponent (Graphics g)    
		{
		super.paintComponent(g);         // don't forget this line!
		Graphics2D g2d = (Graphics2D) g;
		Snowman.draw(g2d);
		}
	 public void mouseDragged( MouseEvent e ) {
		   X = e.getX();
		   Y = e.getY();
		   Snowman.move(X, Y);
		    repaint();
		  }

		  public void mouseMoved( MouseEvent e ) {}

	}
}
