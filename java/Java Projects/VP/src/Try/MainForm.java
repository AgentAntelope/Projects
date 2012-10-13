package Try;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

public class MainForm extends JFrame{

	private static final long serialVersionUID = 3617067236261985984L;
	private Action Print = new PrintAction();
	private Action Add = new AddAction();
	private Action Save = new SavetoCompAction();
	private Action Open = new OpentoComp();
	private JTabbedPane perStudent;
	private ArrayList<Student> StudentDatabase;
	JPanel pane;
	JFrame main = this;

	
	//For Kristy's PDF Maker Class, put here:
	private class PrintAction extends AbstractAction{
		public PrintAction(){
			putValue(NAME, "Print PDF");
			putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_P) );
		    putValue(SHORT_DESCRIPTION, "Print the student to a pdf form") ;

		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	/*
	 * *******************************************************************
	 * Open Database Class:
	 * -Still needs components to connect to MySQL or whatever we are using.
	 * -Currently using a .bin file saved locally on the computer, kind of fail.
	 * 
	 * *******************************************************************
	 */
	
	private class OpentoComp extends AbstractAction{

		public OpentoComp(){
			putValue(NAME, "Open Database");
			putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_O) );
		    putValue(SHORT_DESCRIPTION, "Open database from local file") ;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			FileInputStream a;
			try {
				a = new FileInputStream("test.bin");
			ObjectInputStream b = new ObjectInputStream(a);
			try {
				StudentDatabase =(ArrayList<Student>) b.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			System.out.println(StudentDatabase.get(1).getNotebook() + "blank?");
			b.close();
			addTab(StudentDatabase.get(1));
			} catch (FileNotFoundException e1) {

			} catch (IOException e1) {

			}

		}
	}
	
	/*
	 * *******************************************************************
	 * Save to Comp:
	 * -Shouldn't be needed in the final class, used for testing purposes only.
	 * 
	 * *******************************************************************
	 */
	private class SavetoCompAction extends AbstractAction{

		public SavetoCompAction(){
			putValue(NAME, "Save");
			putValue(MNEMONIC_KEY,new Integer(KeyEvent.VK_S) );
		    putValue(SHORT_DESCRIPTION, "Save student Database to the computer") ;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			save();
		}
	}
	/*
	 * *******************************************************************
	 * Add Action Class:
	 * -Needs further expansion, do they want to insert a new term? or create
	 * a new student entirely? Must specify before further action, currently is just
	 * a new student button.
	 * 
	 * *******************************************************************
	 */
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

				if(l==null){}
				else
					StudentDatabase.add(l);
			addTab(l);
				
		}
		
	}
	public MainForm(){
  
		JMenuBar mb = new JMenuBar();
		StudentDatabase = new ArrayList<Student>();
		StudentDatabase.add(new Student("Joe"));
		JMenu file = new JMenu("File");
		file.add(new JMenuItem(Print));
		file.add(new JMenuItem(Save));
		file.add(new JMenuItem(Open));
		file.add(new JMenuItem(Add));
		mb.add(file);
		perStudent = new JTabbedPane();
		
		this.setLayout(new BorderLayout());
		this.setJMenuBar(mb);
		this.add(perStudent, BorderLayout.CENTER);
		this.setSize(550,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[]Args){
		new MainForm();
	}
	public void addTab(Student studentPane){
		perStudent.addTab(studentPane.getLast(), new OpenStudent(studentPane));
	}
	public void save(){
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
