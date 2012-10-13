package Java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JPanel;
//A circle drawn.

public class CircleComponent extends JComponent
{
        private double x;
        private double y;
        private double rad;
        
        public CircleComponent ()
        {
                x = 0;
                y = 0;
                rad = 0;
        }
        public CircleComponent(double inputx, double inputy, double inputrad)
        {
                setx(inputx);
                sety(inputy);
                setrad(inputrad);
        }
        public void setrad(double inputrad)
        {
                rad = inputrad;
        }
        public void setx(double inputx)
        {
                x = inputx;
        }
        public void sety(double inputy)
        {
                y = inputy;
        }
        public void paintComponent(Graphics g)
        {
                Graphics2D g2 = (Graphics2D) g;
                

                Ellipse2D.Double circle = new Ellipse2D.Double(x,y,rad,rad);
                g2.draw(circle);
        }
}