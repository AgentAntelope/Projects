package FinalProduct;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.event.*;
import javax.swing.*;

public class MainForm extends JFrame{

	private static final long serialVersionUID = 3617067236261985984L;
	private JLabel Name, Address, City, State, Zip, Email, PeopleSoft, Phone, Undergrad,
	        Degree, Program, School, Chapter, Dependant;
	private Action Print = new PrintAction();
	private Action Add = new AddAction();
	private Action Save = new SavetoCompAction();
	private JTabbedPane perStudent;
	private ArrayList<Student> StudentDatabase;
	JPanel pane;
	JFrame main = this;

	private class PrintAction extends AbstractAction{
		public PrintAction(){
			putValue(NAME, "Print PDF");
			putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_P) );
		    putValue(SHORT_DESCRIPTION, "Print the student to a pdf form") ;

		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class SavetoCompAction extends AbstractAction{

		public SavetoCompAction(){
			putValue(NAME, "Save");
			putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_S) );
		    putValue(SHORT_DESCRIPTION, "Save student Database to the computer") ;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			FileOutputStream a;
			try {
				a = new FileOutputStream("test.bin");
			ObjectOutputStream b = new ObjectOutputStream(a);
			b.writeObject(StudentDatabase);
			b.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	private class AddAction extends AbstractAction{

		public AddAction(){
			putValue(NAME, "Add Student");
			putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_T) );
		    putValue(SHORT_DESCRIPTION, "Add a student") ;
		}
		public void actionPerformed(ActionEvent e) {
			Student l;
			NewVetForm j = new NewVetForm(main);
				l = j.newVet();	
				if(l==null){
					
				}
				else
			System.out.println(l.getCity());
			//perStudent.addTab("Student 2", new JPanel() );

		}
		
	}
	public MainForm(){
		JMenuBar mb = new JMenuBar();
		StudentDatabase = new ArrayList<Student>();
		StudentDatabase.add(new Student("Joe"));
		JMenu file = new JMenu("File");
		file.add(new JMenuItem(Print));
		file.add(new JMenuItem(Save));
		//file.add(new JMenuItem(Add));
		mb.add(file);
		perStudent = new JTabbedPane();
		pane = new JPanel();
		this.setLayout(new BorderLayout());
		this.setJMenuBar(mb);
		this.add(perStudent, BorderLayout.CENTER);
		this.add(new JButton(Add), BorderLayout.SOUTH);
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[]Args){
		new MainForm();
	}
	public void addTab(){
		perStudent.addTab("Student 1", pane );
	}
}
