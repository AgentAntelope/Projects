import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

public class Snowman {
	private Shape Face;
	private Shape Body;
	private int size;
	
public Snowman(int X, int Y, int Size)
{

	size = Size;
	Face = new Ellipse2D.Double(X*size, Y*size, size, size);
	Body = new Ellipse2D.Double(X*size, Y*size, size*2, size*2);

}
public void draw(Graphics2D g)
{
	g.draw(Face);
	g.draw(Body);
}
public void move(int X, int Y)
{
	Face = new Ellipse2D.Double(X-size, Y-size, size, size);
	Body = new Ellipse2D.Double(X, Y, size*2, size*2);

}
}
