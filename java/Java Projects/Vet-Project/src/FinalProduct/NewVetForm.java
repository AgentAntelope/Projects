package FinalProduct;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class NewVetForm extends JDialog{


	private JLabel peopleSoft, fName, mInitial, lName, address, zip, state, city, GI, email, telephone1, telephone2,telephone3;
	private JTextField PSBox, fNameBox, mNameBox, otherBox, lNameBox, zipBox, addressBox, GIBox, cityBox, stateBox, emailBox, telephoneBox, telephoneBox2,telephoneBox3;
	private JPanel nameArea, psArea, telephoneArea, addressPanel, studentPanel, emailPanel, submitPanel, radioPanel, gradPanel;
	private JButton submit;
	private SubmitListener submitAction;
	private JRadioButton UnderGrad, Grad, BS, BA, Other, Masters, phd;
	private ButtonGroup grad, degree;
	private RadioListener rad;
	private Student steve;
	private JDialog h = this;
	public void init(){
		
		//Initializing JLabel Fields
		Font italiFont = new Font("Serif", Font.ITALIC, 9);
		peopleSoft = new JLabel("Example 3245436");
		peopleSoft.setFont(italiFont);
		fName = new JLabel("First:");
		mInitial = new JLabel("M.I:");
		lName = new JLabel("Last:");
		address = new JLabel("Address:");
		GI = new JLabel("GI Chapter:");
		email = new JLabel("name@provider.com:");
		email.setFont(italiFont);
		telephone1 = new JLabel("(");
		telephone2 = new JLabel(")");
		telephone3 = new JLabel("-");
		city = new JLabel("City:");
		state = new JLabel("State:");
		zip = new JLabel("Zip Code:");

		//------------------------------------------

		
		//Initializing JTextFields
		PSBox = new JTextField(7);
		fNameBox = new JTextField(5);
		mNameBox = new JTextField(2);
		lNameBox = new JTextField(8);
		addressBox = new JTextField(20);
		telephoneBox = new JTextField(2);
		telephoneBox2 = new JTextField(2);
		telephoneBox3 = new JTextField(3);
		cityBox = new JTextField(4);
		stateBox = new JTextField(3);
		zipBox = new JTextField(5);
		emailBox = new JTextField(11);
		otherBox = new JTextField(5);
		otherBox.setEditable(false);
		//------------------------------------------
		
		submit = new JButton("Submit");
		submitAction = new SubmitListener();
		submit.addActionListener(submitAction);
		
		//Add Components to the Name Panel------------------
		nameArea = new JPanel();
		nameArea.add(fName);
		nameArea.add(fNameBox);
		nameArea.add(mInitial);
		nameArea.add(mNameBox);
		nameArea.add(lName);
		nameArea.add(lNameBox);
		nameArea.setPreferredSize(new Dimension(190, 80));
		nameArea.setBorder(BorderFactory.createTitledBorder("Name"));
      //-----------------------------------------
	
		psArea = new JPanel();
		psArea.add(peopleSoft);
		psArea.add(PSBox);
		psArea.setBorder(BorderFactory.createTitledBorder("PeopleSoft #"));
		
		//----------------------------------------------------------------
		
		
		
		telephoneArea = new JPanel();
		telephoneArea.add(telephone1);
		telephoneArea.add(telephoneBox);
		telephoneArea.add(telephone2);
		telephoneArea.add(telephoneBox2);
		telephoneArea.add(telephone3);
		telephoneArea.add(telephoneBox3);
		telephoneArea.setBorder(BorderFactory.createTitledBorder("Telephone #"));

		//-----------------------------------------------------------------
		
		addressPanel = new JPanel();
		addressPanel.setLayout(new FlowLayout());
		addressPanel.setPreferredSize(new Dimension(330,80));
		addressPanel.add(address);
		addressPanel.add(addressBox);
		addressPanel.add(zip);
		addressPanel.add(zipBox);
		addressPanel.add(city);
		addressPanel.add(cityBox);
		addressPanel.add(state);
		addressPanel.add(stateBox);
		addressPanel.setBorder(BorderFactory.createTitledBorder("Address"));
	    //----------------------------------------------------------------
		
		emailPanel = new JPanel();
		emailPanel.add(email);
		emailPanel.add(emailBox);
		emailPanel.setBorder(BorderFactory.createTitledBorder("Email"));
		emailPanel.setPreferredSize(new Dimension(140, 80));
		//----------------------------------------------------------------
		
		//	private JRadioButton UnderGrad, Grad, BS, BA, Other, Masters, phd;

		rad = new RadioListener();
		UnderGrad = new JRadioButton("Undergrad");
		Grad = new JRadioButton("Grad");
		BS = new JRadioButton("BS");
		BA = new JRadioButton("BA");
		phd = new JRadioButton("PHD");
		Masters = new JRadioButton("Masters");
		Other = new JRadioButton("Other:");
		
		Other.addItemListener(rad);
		Masters.addItemListener(rad);
		BA.addItemListener(rad);
		BS.addItemListener(rad);
		phd.addItemListener(rad);
		
		grad = new ButtonGroup();
		grad.add(UnderGrad);
		grad.add(Grad);
		
		degree = new ButtonGroup();
		degree.add(BS);
		degree.add(BA);		
		degree.add(phd);
		degree.add(Masters);
		degree.add(Other);
		
		radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(0,3));
		radioPanel.add(BS);
		radioPanel.add(BA);
		radioPanel.add(phd);
		radioPanel.add(Masters);
		radioPanel.add(Other);
		radioPanel.add(otherBox);

	
		radioPanel.setBorder(BorderFactory.createTitledBorder("Degree"));

		gradPanel = new JPanel();
		gradPanel.add(Grad);
		gradPanel.add(UnderGrad);
		gradPanel.setLayout(new GridLayout(0,1));
		gradPanel.setBorder(BorderFactory.createTitledBorder("Graduate"));

		//-------------------------------------------------------------
		studentPanel = new JPanel();
		studentPanel.setLayout(new FlowLayout());
		studentPanel.add(nameArea);
		studentPanel.add(emailPanel);
		studentPanel.add(addressPanel);
		studentPanel.add(psArea);
		studentPanel.add(telephoneArea);
		studentPanel.setBorder(BorderFactory.createTitledBorder("Student Info"));
		studentPanel.add(radioPanel);
		studentPanel.add(gradPanel);

		//-----------------------------------------------------------
		submitPanel = new JPanel();
		submitPanel.add(submit);
		this.setLayout(new BorderLayout());
		this.add(studentPanel, BorderLayout.CENTER);
		this.add(submitPanel, BorderLayout.SOUTH);
		this.pack();
		this.setSize(400,450);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);


	}
	public NewVetForm(JFrame main){
		super(main,true);
		init();
		setResizable(false);


	}
	public Student newVet(){
		return steve;
	}
	private class RadioListener implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			otherBox.setEditable(false);

			if(e.getSource() == Other){
				otherBox.setEditable(true);
			}


		}
		
	}
	private class SubmitListener implements ActionListener{
 
		@Override
		public void actionPerformed(ActionEvent e) {
			if(checkFields()){
				steve = new Student();
				steve.setFirst(fNameBox.getText());
				steve.setMiddle(mNameBox.getText());
				steve.setLast(lNameBox.getText());
				steve.setEmail(emailBox.getText());
				steve.setAddress(addressBox.getText());				
				steve.setZip(Integer.parseInt(zipBox.getText()));
				steve.setCity(cityBox.getText());
				steve.setState(stateBox.getText());
				steve.setPeopleSoft(Integer.parseInt(PSBox.getText()));
				steve.setPhone("("+telephoneBox.getText()+ ")"+ telephoneBox2.getText() + "-"+ telephoneBox3.getText());
				h.dispose();
				/*
				JRadioButton t = (JRadioButton) degree.getSelection();
				steve.setDegree(t.getName());
				JRadioButton s = (JRadioButton)grad.getSelection();
				if(s.getText().equals("Grad"))
					steve.setUndergrad(false); 
				else
					steve.setUndergrad(true);
*/
				System.out.println(steve.getAddress());

			}
			
		}
		
	}	
	private boolean checkFields(){
		try{
		Integer.parseInt(zipBox.getText());
		}
		catch(Exception e){
			zipBox.setBackground(Color.PINK);
			return false;
		}
		try{
			Integer.parseInt(PSBox.getText());
			}
		catch(Exception e){
				PSBox.setBackground(Color.PINK);
				return false;
			}
		try{
			Integer.parseInt(telephoneBox.getText());
			Integer.parseInt(telephoneBox2.getText());
			Integer.parseInt(telephoneBox3.getText());

			}
		catch(Exception e){
			telephoneBox.setBackground(Color.PINK);
			telephoneBox2.setBackground(Color.PINK);
			telephoneBox3.setBackground(Color.PINK);
			return false;
			}
		
		return true;
		
	}
	

}
