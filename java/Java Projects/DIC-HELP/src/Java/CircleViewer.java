package Java;

import javax.swing.JFrame;
import java.util.Scanner;

public class CircleViewer
{
        public static void main(String [] args)
        {
                double radius;
                double xin;
                double yin;
                JFrame frame = new JFrame();
                Scanner in = new Scanner (System.in);
                System.out.println("Welcome to Kate's Circle Graphics Program! Please enter a radius for the circle:");
                radius = in.nextDouble();
                
                System.out.println("Please enter a x coordinate:");
                xin = in.nextDouble();
                
                System.out.println("Please enter a y coordinate:");
                yin = in.nextDouble();
                
                frame.setSize(500,500);
                frame.setTitle("Circle Drawer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                CircleComponent component = new CircleComponent(xin, yin, radius);
                frame.add(component);
                
                frame.setVisible(true);
        }
}
