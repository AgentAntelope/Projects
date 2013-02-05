import java.util.*;
import javax.swing.*;
import java.awt.*;
public class GraphicalRectangle{
	private JFrame frame;
	private PaintPanel pane;

	public GraphicalRectangle(){
		frame = new JFrame("These are your Rectangles!");
		pane = new PaintPanel(500, 500);
        JButton a = new JButton("HI!");
        frame.add(a);
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.repaint();

	}

	public void addRectangle(MyRectangle a){
		pane.addRectangle(a);
		frame.repaint();
	}

	public void addRectangleWithColor(MyRectangle rect, Color color){
		pane.addRectangleWithColor(rect, color);
		frame.repaint();
	}


	private class PaintPanel extends JPanel{
		public static final long serialVersionUID = 1;
		private int xSize, ySize;
		private Color background = Color.white;
		private ArrayList<MyRectangle> rects;

		public PaintPanel(int xSize, int ySize) {
			this.xSize = xSize;
			this.ySize = ySize;
			rects = new ArrayList<MyRectangle>();
			setPreferredSize( new Dimension(xSize,ySize) ) ;
		}

		public void addRectangle(MyRectangle a){
			rects.add(a);
		}
		public void addRectangleWithColor(MyRectangle a, Color color){
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g) ;
			g.setColor(background) ;
			g.fillRect(0,0,xSize,ySize);
			g.setColor(new Color(100, 160, 0));
			for(MyRectangle i: rects){
				g.fillRect(i.getX(), i.getY(), i.getWidth(), i.getHeight());
			}
		}
	}
}
