package DrawingProject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.*;


public class House implements MyShape
{
	int x,y,size;
	private Boolean isHighlighted = false;
	private Polygon Roof;
	private Shape Door;
	private Shape Siding;

	public House(int X, int Y, int sizez)
	{
		size = sizez;
		x = X;
		y = Y;
		Roof = new Polygon();
		Roof.addPoint(x,y);
		Roof.addPoint(x+size*3,y);
		Roof.addPoint(x+9*size/6,y-5*size/3);
		Siding = new Rectangle2D.Double(x, y, size*3, size*5/2);		
		Door = new Rectangle2D.Double(x+size,y+size*9/6,size,size);
		
	}

	public boolean contains(double x, double y) {
	if(Roof.contains(x,y)|| Siding.contains(x,y)|| Door.contains(x,y))
		return true;
	else
		return false;
	}

	
	public void draw(Graphics2D g) {
		if(!isHighlighted)
		{
		g.setColor(Color.RED);
		g.fill(Siding);
		g.setColor(Color.BLACK);
		g.fill(Door);
		g.fill(Roof);
		}
		if(isHighlighted)
		{
		g.setColor(Color.RED);
		g.draw(Siding);
		g.setColor(Color.BLACK);
		g.draw(Door);
		g.draw(Roof);
		}
	}

	
	public void highlight(boolean b) {
		isHighlighted = b;
		
	}

	
	public void move(int x, int y) {
		this.x = x;
		this.y = x;
		Roof = new Polygon();
		Roof.addPoint(x,y);
		Roof.addPoint(x+size*3,y);
		Roof.addPoint(x+9*size/6,y-5*size/3);
		Siding = new Rectangle2D.Double(x, y, size*3, size*5/2);		
		Door = new Rectangle2D.Double(x+size,y+size*9/6,size,size);		
	}

	
	public void resize(int newsize) {
		size = newsize;
		Roof = new Polygon();
		Roof.addPoint(x,y);
		Roof.addPoint(x+size*3,y);
		Roof.addPoint(x+9*size/6,y-5*size/3);
		Siding = new Rectangle2D.Double(x, y, size*3, size*5/2);		
		Door = new Rectangle2D.Double(x+size,y+size*9/6,size,size);	
		
	}

	
	public String saveData() {
		return ("House:" + x + ":" + y + ":" + size);
	}


	public Object copyData() {
		return new House(x,y,size);
		
	}

}
