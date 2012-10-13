package DrawingProject;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

public class Snowman implements MyShape{
	private Shape Face;
	private Shape Body;
	private Shape Hat;
	private Shape Hat2;
	private int size;
	private int x,y;
	private Boolean isHighlighted= false;
	
public Snowman(int X, int Y, int size)
{
	this.size = size;
	x = X;
	y = Y;
	Face = new Ellipse2D.Double(X, Y, size, size);
	Body = new Ellipse2D.Double(X-size/2, Y+size, size*2, size*2);
	Hat = new Rectangle2D.Double(X, Y-size/8, size, size/6);
	Hat2 = new Rectangle2D.Double(X+size/4, Y-size/2, size/2, size/2);


}
public Snowman(Snowman X)
{
	size =X.size;
	Face = new Ellipse2D.Double(200, 150, size, size);
	Body = new Ellipse2D.Double(200-size/2, 150+size, size*2, size*2);
	Hat = new Rectangle2D.Double(200, 150-size/8, size, size/6);
	Hat2 = new Rectangle2D.Double(200+size/4, 150-size/2, size/2, size/2);

}
public void resize(int newsize)
{
	this.size = newsize;
	Face = new Ellipse2D.Double(x, y, size, size);
	Body = new Ellipse2D.Double(x-size/2, y+size, size*2, size*2);
	Hat = new Rectangle2D.Double(x, y-size/8, size, size/6);
	Hat2 = new Rectangle2D.Double(x+size/4, y-size/2, size/2, size/2);

	
}
public void draw(Graphics2D g)
{
	if(!isHighlighted)
	{
	g.setColor(Color.WHITE);
	g.fill(Face);
	g.fill(Body);
	g.setColor(Color.BLACK);
	g.fill(Hat);
	g.fill(Hat2);

	}
	else
	{
	g.setColor(Color.RED);
	g.draw(Face);
	g.draw(Body);
	g.draw(Hat);
	g.draw(Hat2);

	}
}
public void move(int X, int Y)
{
	x = X;
	y = Y;
	Face = new Ellipse2D.Double(X, Y, size, size);
	Body = new Ellipse2D.Double(X-size/2, Y+size, size*2, size*2);
	Hat = new Rectangle2D.Double(X, Y-size/8, size, size/6);
	Hat2 = new Rectangle2D.Double(X+size/4, Y-size/2, size/2, size/2);

}

public boolean contains(double x, double y) {
	if(Face.contains(x,y)||Body.contains(x,y)||Hat.contains(x,y)||Hat2.contains(x,y))
		return true;
	else
	return false;
}

public void highlight(boolean b) {
	isHighlighted = b;
	
}
public String saveData() {
	return ("Snowman:" + x + ":" + y + ":" + size);
}
public Object copyData() {
	return new Snowman(275,150,size);

}
}
