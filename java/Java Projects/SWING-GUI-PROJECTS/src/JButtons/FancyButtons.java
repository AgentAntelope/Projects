package JButtons;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class FancyButtons extends JButton {

	public FancyButtons(Icon icon, Icon pressed, Icon Rollover)
	{
		super(icon);
		setFocusPainted(false);
		setRolloverEnabled(true);
		setRolloverIcon(Rollover);
		setPressedIcon(pressed);
		setBorderPainted(false);
		setContentAreaFilled(false);
	}
	
	public static void main(String[]Args)
	{
		FancyButtons b1 = new FancyButtons(
				  new ImageIcon("images/redcube.gif") ,
			      new ImageIcon("images/redpaw.gif") ,
			      new ImageIcon("images/reddiamond.gif") ) ;
	    FancyButtons b2 = new FancyButtons(
		      new ImageIcon("images/bluecube.gif") ,
		      new ImageIcon("images/bluepaw.gif") ,
		      new ImageIcon("images/bluediamond.gif") ) ;
		    JFrame f = new JFrame( );
		    f. setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE) ;
		    Container c = f. getContentPane( );
		    c. setLayout(new FlowLayout( ));
		    c. add(b1) ;
		    c. add(b2) ;
		    f. pack( );
		    f. setVisible(true) ;
	}
}
