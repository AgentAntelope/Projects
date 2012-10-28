import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import edu.ship.SimpleGraphics.*;

/**
 * @author Travis Fitzsimmons
 *
 */
public class Smiley
{
	SimpleGraphics g = new SimpleGraphics();;

	int X = 350;
	int Y = 350;

	/**
	 * 
	 */
	public Smiley()
	{
		
		
		

      
	}

	public void draw(int x,int y)
	{
		
      
		X = x;
		Y = y;
		
		g.clear();
		g.setColor(Color.YELLOW); 
		g.drawFilledOval(new Rectangle (X,Y,300,300)); 
		g.setColor(Color.BLACK); 
		g.drawFilledOval(new Rectangle (X+75,Y+75,60, 60)); 
		g.drawFilledOval(new Rectangle (X+175,Y+75,60, 60)); 
		g.drawArc(new Rectangle (X+55,Y+65,200,200), -180, 180); 
		g.paint();
	
	}
	
	

	

}
