package Java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JFrame{
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private final static int WIDTH = 600;
        private final static int HEIGHT = 300;
        public static double total = 0.0;
        final static double z = 3.00;
        final static double y = 2.50;
        final static double x = 3.50;
        final static double w = 4.00;
        final static double v = 1.50;
        final static double u = 6.50;
        final static double t = 5.50;
        final static double s = 4.50;
        final static double r = 3.50;
        final static double q = 2.00;
        final static double p = 1.50;
        final static double o = 2.00;
        final static double n = 2.50;
        final static double m = 3.00;
        final static double l = 3.50; 
        
        JFrame menu = new JFrame("Kent's Pub");


          JPanel bkft = new JPanel();
          JPanel lunch = new JPanel();
          JPanel drink = new JPanel();
          JPanel ordered = new JPanel();
          JPanel cmds = new JPanel();
        
          JTextArea breakfastta, lunchta, drinkta;

          JButton clear, order;
          JCheckBox B1, B2, B3, B4, B5, L1, L2, L3, L4, L5, D1, D2, D3, D4, D5;
        
        public Menu(){
                
                order = new JButton("Order");
                clear = new JButton("Clear");
                lunch.setLayout(new GridLayout(0,1));
                bkft.setLayout(new GridLayout(0,1));
                bkft.setBorder(BorderFactory.createLineBorder(Color.gray));
                drink.setLayout(new GridLayout(0,1));
                drink.setBorder(BorderFactory.createLineBorder(Color.gray));
                lunch.setBorder(BorderFactory.createLineBorder(Color.gray));
                breakfastta = new JTextArea("Breakfast Items");
                lunchta = new JTextArea("Lunch Items");
                drinkta = new JTextArea("Drink Items");
                
                B1 = new JCheckBox("Scones -- 3.00");
                B2 = new JCheckBox("Crumpets -- 2.50");
                B3 = new JCheckBox("Poached Eggs -- 3.50");
                B4 = new JCheckBox("Liver Sausage -- 4.00");
                B5 = new JCheckBox("Black Pudding -- 1.50");
                
                L1 = new JCheckBox("Fish & Chips -- 6.50");
                L2 = new JCheckBox("Bangers and Mash -- 5.50");
                L3 = new JCheckBox("Ploughman's Lunch -- 4.50");
                L4 = new JCheckBox("Ham Sarny -- 3.50");
                L5 = new JCheckBox("Yorkshire Pudding -- 2.00");
                
                D1 = new JCheckBox("Tea -- 1.50");
                D2 = new JCheckBox("Coffee -- 2.00");
                D3 = new JCheckBox("Lager -- 2.50");
                D4 = new JCheckBox("Bitter -- 3.00");
                D5 = new JCheckBox("Best -- 3.50");
                
                
                bkft.add(B1);
                bkft.add(B2);
                bkft.add(B3);
                bkft.add(B4);
                bkft.add(B5);
                
                lunch.add(L1);
                lunch.add(L2);
                lunch.add(L3);
                lunch.add(L4);
                lunch.add(L5);
                
                drink.add(D1);
                drink.add(D2);
                drink.add(D3);
                drink.add(D4);
                drink.add(D5);

                bkft.add(breakfastta);
                lunch.add(lunchta);
                drink.add(drinkta);
              //  menu.add(bkft);
              //  menu.add(lunch);
              //  menu.add(drink);
               cmds.add(clear);
               cmds.add(order);
              //  menu.add(cmds);
              //  menu.add(ordered);

                //menu.add(breakfastta);
                //menu.add(lunchta);
                //menu.add(drinkta);
                Container c = menu.getContentPane();     
                c.add(lunch, BorderLayout.CENTER);
                c.add(bkft, BorderLayout.EAST);
                c.add(cmds, BorderLayout.SOUTH);
                c.add(drink, BorderLayout.WEST);
                menu.pack();
                menu.setLocation(200, 100);
                //bkft.setVisible(true);
                //lunch.setVisible(true);
                //drink.setVisible(true);
                //cmds.setVisible(true);
                menu.setVisible(true);
                menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                order.addActionListener(new ActionListener() {
                          public void actionPerformed(ActionEvent evt) {
                                  if (B1.isSelected( ))
                                         total = total + z; 
                                                
                                  if (B2.isSelected())
                                          total = total + y;
                                  if (B3.isSelected())
                                          total = total + x;
                                  if (B4.isSelected())
                                          total = total + w;
                                  if (B5.isSelected())
                                          total = total + v;
                                  if (L1.isSelected())
                                          total = total + u;
                                  if (L2.isSelected())
                                          total = total + t;
                                  if (L3.isSelected())
                                          total = total + s;
                                  if (L4.isSelected())
                                          total = total + r;
                                  if (L5.isSelected())
                                          total = total + q;
                                  if (D1.isSelected())
                                          total = total + p;
                                  if (D2.isSelected())
                                          total = total + o;
                                  if (D3.isSelected())
                                          total = total + n;
                                  if (D4.isSelected())
                                          total = total + m;
                                  if (D5.isSelected())
                                          total = total + l;
                                  
                                  
                                  JOptionPane.showMessageDialog(null, "Your Total is: " + total);
                                          
                          }
                });
                
                clear.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent evt){
                                B1.setSelected(false);
                                B2.setSelected(false);
                                B3.setSelected(false);
                                B4.setSelected(false);
                                B5.setSelected(false);
                                L1.setSelected(false);
                                L2.setSelected(false);
                                L3.setSelected(false);
                                L4.setSelected(false);
                                L5.setSelected(false);
                                D1.setSelected(false);
                                D2.setSelected(false);
                                D3.setSelected(false);
                                D4.setSelected(false);
                                D5.setSelected(false);
                                
                                total = 0.0;
                                
                                
                        }
                });
                
        }
        public static void main(String[]Args)
        {
        	new Menu();
        }
}
