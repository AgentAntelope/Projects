package DrawingProject;
// CS 401 Fall 2009
/*
Name:Sean Myers   Assignment #:5

Lab Day and Time: Thursday 12:15+
Lab Instructor: Michael

Program Due Date:12/10/09

Handed in Date: 12/10/09 

Source code file name(s): 
Assign5.java, House.java, MyShape.java, Snowflake.java, Snowman.java, Tree.java

Compiled (.class) file name(s):
Assign5.class, House.class, MyShape.class, Snowflake.class, Snowman.class, Tree.class


Does your program compile without error?: Yes

Does your program run without error?: Yes

Other: Has a weird thing where the notepad text is all on one line, but if you copy and paste it, the \n does 

it's job..
*/
// For additional help, see Sections 14.5-14.6 in the text

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

// Create enum types that will be useful in the program
enum Figures {TREE,SNOWFLAKE,HOUSE,SNOWMAN};
enum Mode {NONE,DRAW,MOVE};

public class Assign5
{	
	private ShapePanel drawPanel;
	private JPanel buttonPanel;
	private JButton makeShape;
	private JRadioButton makeTree, makeFlake, makeHouse, makeSnowman;
	private ButtonGroup shapeGroup;
	private Figures currShape;
	private JLabel msg;
	private JMenuBar theBar;
	private JMenu fileMenu, editMenu;
	private JMenuItem endProgram,newOne ,open,saveAs,save,cut,copy,paste, resize, delete;
	private JPopupMenu popper;
	private JMenuItem moveIt;
	private JFrame theWindow;
	private JFileChooser FileChoose;
	private String saveName = "";
	private File saveFile;
	private int saveNeeded = 1;

	public Assign5()
	{
		drawPanel = new ShapePanel(500, 300);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 2));

		makeShape = new JButton("Make Shape");

		ButtonHandler bhandler = new ButtonHandler();
		makeShape.addActionListener(bhandler);

		buttonPanel.add(makeShape);
		msg = new JLabel("");
		buttonPanel.add(msg);

		makeTree = new JRadioButton("Tree", false);
		makeSnowman = new JRadioButton("Snowman", false);
		makeHouse = new JRadioButton("House", false);
		makeFlake = new JRadioButton("Snowflake", true);

		RadioHandler rhandler = new RadioHandler();
		makeTree.addItemListener(rhandler);
		makeFlake.addItemListener(rhandler);
		makeSnowman.addItemListener(rhandler);
		makeHouse.addItemListener(rhandler);

		
		buttonPanel.add(makeFlake);
		buttonPanel.add(makeTree);
		buttonPanel.add(makeSnowman);
		buttonPanel.add(makeHouse);


		// A ButtonGroup allows a set of JRadioButtons to be associated
		// together such that only one can be selected at a time
		shapeGroup = new ButtonGroup();
		shapeGroup.add(makeFlake);
		shapeGroup.add(makeTree);
		shapeGroup.add(makeSnowman);
		shapeGroup.add(makeHouse);

		
		currShape = Figures.SNOWFLAKE;
		drawPanel.setMode(Mode.NONE);

		theWindow = new JFrame("Assignment 5");
		Container c = theWindow.getContentPane();
		drawPanel.setBackground(Color.lightGray);
		c.add(drawPanel, BorderLayout.NORTH);
		c.add(buttonPanel, BorderLayout.SOUTH);

		// Note how the menu is created.  First we make a JMenuBar, then
		// we put a JMenu in it, then we put JMenuItems in the JMenu.  We
		// can have multiple JMenus if we like.  JMenuItems generate
		// ActionEvents, just like JButtons, so we just have to link an
		// ActionListener to them.
		theBar = new JMenuBar();
		theWindow.setJMenuBar(theBar);
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		theBar.add(fileMenu);
		theBar.add(editMenu);
		endProgram = new JMenuItem("Exit");
		newOne = new JMenuItem("New");
		saveAs = new JMenuItem("Save As..");
		save = new JMenuItem("Save");
		open = new JMenuItem("Open");
		fileMenu.add(newOne);
		fileMenu.add(open);		
		fileMenu.add(saveAs);
		fileMenu.add(save);
		fileMenu.add(endProgram);
		copy = new JMenuItem("Copy");
		cut = new JMenuItem("Cut");
		paste = new JMenuItem("Paste");
		cut.setEnabled(false);
		copy.setEnabled(false);
		resize = new JMenuItem("Resize");
		delete = new JMenuItem("Delete");
		editMenu.add(cut);
		editMenu.add(copy);
		editMenu.add(paste);
		paste.setEnabled(false);
		endProgram.addActionListener(bhandler);
		
		

		popper = new JPopupMenu();
		popper.add(resize);
		popper.add(delete);
		moveIt = new JMenuItem("Move");
		newOne.addActionListener(bhandler);
		save.addActionListener(bhandler);
		saveAs.addActionListener(bhandler);
		delete.addActionListener(bhandler);
		moveIt.addActionListener(bhandler);
		cut.addActionListener(bhandler);
		paste.addActionListener(bhandler);
		copy.addActionListener(bhandler);
		open.addActionListener(bhandler);
		resize.addActionListener(bhandler);

		popper.add(moveIt);

		
		theWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		theWindow.pack();
		theWindow.setVisible(true);
	}

	public static void main(String [] args)
	{
		new Assign5();
	}

	// See Section 7.5 for information on JRadioButtons.  Note that the
	// text uses ActionListeners to handle JRadioButtons.  Clicking on
	// a JRadioButton actually generates both an ActionEvent and an
	// ItemEvent.  I am using the ItemEvent here.  To handle the event,
	// all I am doing is changing a state variable that will affect the
	// MouseListener in the ShapePanel.
	private class RadioHandler implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getSource() == makeTree)
				currShape = Figures.TREE;
			else if (e.getSource() == makeFlake)
				currShape = Figures.SNOWFLAKE;
			else if (e.getSource() == makeHouse)
				currShape = Figures.HOUSE;
			else if (e.getSource() == makeSnowman)
				currShape = Figures.SNOWMAN;
		}
	}

	// Note how the makeShape button and moveIt menu item are handled 
	// -- we again simply set the state in the panel so that the mouse will 
	// actually do the work.  The state needs to be set back in the mouse
	// listener.
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == makeShape)
			{
				saveNeeded = 0;
				drawPanel.setMode(Mode.DRAW);
				msg.setText("Position new shape with mouse");
				makeShape.setEnabled(false);
			}
			else if (e.getSource() == moveIt)
			{
				saveNeeded = 0;
				drawPanel.setMode(Mode.MOVE);
				msg.setText("Move shape with mouse");
				makeShape.setEnabled(false);
			}
			else if (e.getSource() == endProgram)
			{
				if(saveNeeded == 0)
				{
				    int reply = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Yes or no", JOptionPane.YES_NO_OPTION);
				    if (reply == JOptionPane.YES_OPTION)
				    {
				     NormSave();
				    }

				}
				System.exit(0);
			}
			else if(e.getSource()== newOne)
			{
				if(saveNeeded == 0)
				{
				    int reply = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Yes or no", JOptionPane.YES_NO_OPTION);
				    if (reply == JOptionPane.YES_OPTION)
				    {
				     NormSave();
				    }
				}
				
				saveNeeded = 0;
				drawPanel.newScreen();
			}
			else if(e.getSource()== cut)
			{
				saveNeeded = 0;
				drawPanel.cutAction();
				paste.setEnabled(true);

			}
			else if(e.getSource()== paste)
			{
				saveNeeded = 0;
				drawPanel.pasteAction();
			}
			else if(e.getSource()== copy)
			{
				drawPanel.copyAction();
				paste.setEnabled(true);
			}
			else if(e.getSource()== resize)
			{
				saveNeeded = 0;
				drawPanel.resizeAction();
			}
			else if(e.getSource()== delete)
			{
				saveNeeded = 0;
				drawPanel.deleteAction();
			}
			else if(e.getSource()==save)
			{
				NormSave();
			}
			else if(e.getSource()== saveAs)
			{
			saveSystem();         
			}
			else if(e.getSource()==open)
			{
			FileChoose = new JFileChooser();
		    int result = FileChoose.showSaveDialog(theWindow);
		 
		    if(result==0)
		    {
		    	File file = FileChoose.getSelectedFile();
		    	drawPanel.openData(file);

		    }
			}
		}
		public void NormSave()
		{
			if(!saveName.equals(""))
			{
		try{
		PrintWriter P = new PrintWriter(saveFile);
		P.write(drawPanel.saveObjects());
		System.out.println(drawPanel.saveObjects());
		P.close();
		saveNeeded = 1;
		}
		catch(Exception e2){}

			}
	if(saveName.equals(""))
	{
		saveSystem();
	}
		}
		public void saveSystem()
		{
			FileChoose = new JFileChooser();
		    int result = FileChoose.showSaveDialog(theWindow);
		    if(result==0)
		    {
		    	File file = FileChoose.getSelectedFile();
		    	saveFile = file;
		    	   try {
		    	         PrintWriter bw = new PrintWriter(file+".txt");
		    	         bw.write(drawPanel.saveObjects());
		    	         bw.close();
		    				saveName = file.getName()+ ".txt";
		    	      }
		    	      catch (Exception e1) {
		    	         JOptionPane.showMessageDialog(
		    	            theWindow,
		    	            "Error with file you tried to save, please try again" );
		    	      }
		    }	
		}
	}

	// Here we are extending JPanel.  This way we can use all of the
	// properties of JPanel (including generating MouseEvents) and also
	// add new instance data and methods, as shown below.  Since this is
	// an inner class, it can access instance variables from the A5Help
	// class if necessary.
	private class ShapePanel extends JPanel
	{
		// This ArrayList is used to store the shapes in the program.
		// It is specified to be of type MyShape, so objects of any class
		// that implements the MyShape interface can be stored in here.
		// See Section 8.13 in your text for more info on ArrayList.
		private ArrayList<MyShape> shapeList;
		
		private MyShape newShape;
		private MyShape CopyShape;
		
		// These instance variables are used to store the desired size
		// of the panel
		private int prefwid, prefht;

		// Store index of the selected MyShape.  This allows the Shape
		// to be moved and updated.
		private int selindex;

		// Keep track of positions where mouse is moved on the display.
		// This is used by mouse event handlers when moving the shapes.
		private int x1, y1, x2, y2; 

		private Mode mode;   // Keep track of the current Mode

		public ShapePanel (int pwid, int pht)
		{
			shapeList = new ArrayList<MyShape>(); // create empty ArrayList
			selindex = -1;
		
			prefwid = pwid;	// values used by getPreferredSize method below
			prefht = pht;   // (which is called implicitly).  This enables
			// the JPanel to request the room that it needs.
			// However, the JFrame is not required to honor
			// that request.

			setOpaque(true);// Paint all pixels here (See API)

			setBackground(Color.lightGray);

			addMouseListener(new MyMouseListener());
			addMouseMotionListener(new MyMover());
		}  // end of constructor

		private class EditListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent e) {

			}
		}
		// This class is extending MouseAdapter.  MouseAdapter is a predefined
		// class that implements MouseListener in a trivial way (i.e. none of
		// the methods actually do anything).  Extending MouseAdapter allows
		// a programmer to implement only the MouseListener methods that
		// he/she needs but still satisfy the interface (recall that to
		// implement an interface one must implement ALL of the methods in the
		// interface -- in this case I do not need 3 of the 5 MouseListener
		// methods)
		private class MyMouseListener extends MouseAdapter
		{
			int x1;
			int y1;
			public void mousePressed(MouseEvent e)
			{
				x1 = e.getX();  // store where mouse is when clicked
				y1 = e.getY();


				 if (e.isPopupTrigger() && selindex >= 0)  // if button is
				{								               // the popup menu
					popper.show(ShapePanel.this, x1, y1);      // trigger, show
				}											   // the popup menu
			}
			public void mouseClicked(MouseEvent e)
			{

				if (!e.isPopupTrigger() && mode == Mode.NONE) // left click and
				{									   // not in a special mode
					if (selindex >= 0)
						unSelect();
					selindex = getSelected(x1, y1);  // find shape mouse is
					repaint();						 // clicked on
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if (mode == Mode.DRAW) // in DRAW mode, create the new Shape
				{					   // and add it to the list of Shapes
					if (currShape == Figures.TREE)
					{
						newShape = new Tree(x1,y1,50);
					}
					else if (currShape == Figures.SNOWFLAKE)
					{
						newShape = new Snowflake(x1,y1,10);
					}
					else if (currShape == Figures.SNOWMAN)
					{
						newShape = new Snowman(x1,y1,20);
					}
					else if (currShape == Figures.HOUSE)
					{
						newShape = new House(x1,y1,20);
					}
					addShape(newShape);
					makeShape.setEnabled(true);  // Set interface back to
					mode = Mode.NONE;			 // "base" state
					msg.setText("");
				}
				else if (mode == Mode.MOVE) // in MOVE mode, set mode back to
				{							// to NONE and unselect shape (since
					mode = Mode.NONE;		// the move is now finished)
					unSelect();
					selindex = -1;     
					makeShape.setEnabled(true);
					msg.setText("");
					repaint();
				}
				else if (e.isPopupTrigger() && selindex >= 0) // if button is
				{							// the popup menu trigger, show the
					popper.show(ShapePanel.this, x1, y1); // popup menu
				}
			}
		}

		// the MouseMotionAdapter has the same idea as the MouseAdapter
		// above, but with only 2 methods.  The method not implemented
		// here is mouseMoved
		private class MyMover extends MouseMotionAdapter
		{
			public void mouseDragged(MouseEvent e)
			{
				x2 = e.getX();   // store where mouse is now
				y2 = e.getY();

				// Note how easy moving the shapes is, since the "work"
				// is done within the various shape classes.  All we do
				// here is call the appropriate method.
				if (mode == Mode.MOVE)
				{
					MyShape s = shapeList.get(selindex);
					s.move(x2, y2);
				}
				repaint();	// Repaint screen to show updates
			}
		}

		// Check to see if point (x,y) is within any of the shapes.  If
		// so, select that shape and highlight it so user can see.
		// This version of getSelected() always considers the shapes from
		// beginning to end of the ArrayList.  Thus, if a shape is "under"
		// or "within" a shape that was previously created, it will not
		// be possible to select the "inner" shape.  In your assignment you
		// must redo this method so that it allows all shapes to be selected.
		// Think about how you would do this.
		private int getSelected(double x, double y)
		{                          
			if(selindex >= 0 && selindex < shapeList.size())
			{
				for (int i = selindex+1; i < shapeList.size(); i++)
				{
				if (shapeList.get(i).contains(x, y))
					{
					shapeList.get(i).highlight(true); 
					cut.setEnabled(true);
					copy.setEnabled(true);
					return i;
					}
				}
			}
			else
			{
				for (int i = 0; i < shapeList.size(); i++)
				{
				if (shapeList.get(i).contains(x, y))
					{
					shapeList.get(i).highlight(true); 
					cut.setEnabled(true);
					copy.setEnabled(true);
					return i;
					}
				}
			}
			return -1;
		}

		public void unSelect()
		{
			if (selindex >= 0)
			{
				shapeList.get(selindex).highlight(false);
				cut.setEnabled(false);
				copy.setEnabled(false);
			}
		}

		public void setMode(Mode newMode)            // set Mode
		{
			mode = newMode;
		}
		public void cutAction()
		{
			if(selindex >= 0)
			{
			CopyShape = shapeList.get(selindex);
		    CopyShape=(MyShape)CopyShape.copyData();
			shapeList.remove(selindex);
			selindex = -1;
			cut.setEnabled(false);
			copy.setEnabled(false);
			repaint();
			}
		}
		public void copyAction()
		{
			if(selindex >= 0)
			{
			CopyShape = shapeList.get(selindex);
			CopyShape.highlight(false);
		    CopyShape=(MyShape)CopyShape.copyData();
			selindex = -1;
			cut.setEnabled(false);
			copy.setEnabled(false);
			repaint();
			}
		}
		public void pasteAction()
		{
			addShape((MyShape) CopyShape.copyData());
		}
		public void deleteAction()
		{
			if(selindex >= 0)
			shapeList.remove(selindex);			
			selindex = -1;
			repaint();
		}
		public void resizeAction()
		{
			if(selindex >= 0)
			{
			int newsize = Integer.parseInt(JOptionPane.showInputDialog("Please enter a new size"));
			shapeList.get(selindex).resize(newsize);
			repaint();
			}
		}
		public String saveObjects()
		{
			StringBuffer saveInfo = new StringBuffer();
			int	NumItems = shapeList.size();
			saveInfo.append(NumItems+ "\n");
			for(int i = 0; i < shapeList.size(); i++)
			{

			saveInfo.append(shapeList.get(i).saveData()+ "\n");
			}
			return saveInfo.toString();
		}
		public void openData(File file)
		{
			saveFile = file;
			try{
		    Scanner S = new Scanner(new FileInputStream(file));
		 int itItems =Integer.parseInt(S.nextLine());
		 shapeList.removeAll(shapeList);
		    for(int i = 0; i < itItems; i++)
		    	{
		    String ObjectShape=	S.nextLine();
		    String[] ObjectShapeSplit =ObjectShape.split(":");
		    if(ObjectShapeSplit[0].equals("Tree"))
		    		{
		    		int X = Integer.parseInt(ObjectShapeSplit[1]);
		    		int Y = Integer.parseInt(ObjectShapeSplit[2]);
		    		int Size = Integer.parseInt(ObjectShapeSplit[3]);
		    		newShape = new Tree(X,Y,Size);
		    		shapeList.add(newShape);
		    		repaint();
		    		}
		    else if(ObjectShapeSplit[0].equals("Snowman"))
    				{
		    		int X = Integer.parseInt(ObjectShapeSplit[1]);
		    		int Y = Integer.parseInt(ObjectShapeSplit[2]);
		    		int Size = Integer.parseInt(ObjectShapeSplit[3]);
		    		newShape = new Snowman(X,Y,Size);
		    		shapeList.add(newShape);
		    		repaint();
    				}
		    else if(ObjectShapeSplit[0].equals("Snowflake"))
    				{
		    		int X = Integer.parseInt(ObjectShapeSplit[1]);
		    		int Y = Integer.parseInt(ObjectShapeSplit[2]);
		    		int Size = Integer.parseInt(ObjectShapeSplit[3]);
		    		newShape = new Snowflake(X,Y,Size);
		    		shapeList.add(newShape);
		    		repaint();
    				}
		    else if(ObjectShapeSplit[0].equals("House"))
			{
    		int X = Integer.parseInt(ObjectShapeSplit[1]);
    		int Y = Integer.parseInt(ObjectShapeSplit[2]);
    		int Size = Integer.parseInt(ObjectShapeSplit[3]);
    		newShape = new House(X,Y,Size);
    		shapeList.add(newShape);
    		repaint();
			}
		    	}
			saveName = file.getName();

			}
			catch(Exception e1)
			{
				 JOptionPane.showMessageDialog(theWindow, "Error with file you tried to"
						 + " open, please try again" );
			}

		}
		private void addShape(MyShape newshape)      // Add shape
		{
			shapeList.add(newshape);
			repaint();	// repaint so we can see new shape
		}
		private void newScreen()
		{
			drawPanel.shapeList.removeAll(shapeList);
			repaint();
		}

		// Method called implicitly by the JFrame to determine how much
		// space this JPanel wants
		public Dimension getPreferredSize()
		{
			return new Dimension(prefwid, prefht);
		}

		// This method enables the shapes to be seen.  Note the parameter,
		// which is implicitly passed.  To draw the shapes, we in turn
		// call the draw() method for each shape.
		public void paintComponent (Graphics g)    
		{
			super.paintComponent(g);         // don't forget this line!
			Graphics2D g2d = (Graphics2D) g;
			for (int i = 0; i < shapeList.size(); i++)
			{
				shapeList.get(i).draw(g2d);
			}
		}
	} // end of ShapePanel
}