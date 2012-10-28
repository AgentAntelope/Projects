import java. awt. *;
import java. awt. event. *;
import javax. swing. *;

public class JavaSequel {
	
		  public static void main( String[ ] args ) {
		    JFrame frame = new JFrame( "HelloJava2" ) ;
		    frame. getContentPane( ).add( new JSequel("Hello, Java! ") );
		    frame. setDefaultCloseOperation( JFrame. EXIT_ON_CLOSE ) ;
		    frame. setSize( 300, 300 ) ;
		    frame. setVisible( true ) ;
		  }
		}

		class JSequel extends JComponent implements MouseMotionListener
		{
		  String theMessage;
		  int messageX = 125, messageY = 95; // Coordinates of the message
		  public JSequel( String message ) 
		  {
		    theMessage = message;
		    addMouseMotionListener(this) ;
		  }
		  
		  public void paintComponent( Graphics g )
		  {
		    g. drawString( theMessage, messageX, messageY ) ;
		  }
		  public void mouseDragged(MouseEvent e) 
		  {
		    // Save the mouse coordinates 
	messageX = e.getX( );
    messageY = e.getY( );
 
    repaint( );
  }
  public void mouseMoved(MouseEvent e) { }
}