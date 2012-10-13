package Try;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class OpenStudent extends JPanel {

	public OpenStudent(Student guy){
		super();
		JPanel basicInfo, creditsInfo, notes;
		basicInfo = new JPanel();
		creditsInfo = new JPanel();
		notes = new JPanel();
		setLayout(new GridLayout(0,1));
		basicInfo.setLayout(new GridLayout(0,1));
		basicInfo.setPreferredSize(new Dimension(250,250));
		basicInfo.setBorder(BorderFactory.createTitledBorder("Student info"));
		Font mainFont = new Font("Calibri", Font.PLAIN, 15);
		basicInfo.setFont(mainFont);
		basicInfo.add(new JLabel(" Student:  " + guy.getFirst()+ " " + guy.getMiddle()+ " "+ guy.getLast()));
		basicInfo.add(new JLabel(" Address:  " + guy.getAddress()));
		basicInfo.add(new JLabel(" City:  " + guy.getCity() + "        State:  "+ guy.getState()));
		basicInfo.add(new JLabel(" Zip Code:  "+ guy.getZip()));
		basicInfo.add(new JLabel(" E-mail:  "+ guy.getEmail()));
		basicInfo.add(new JLabel(" Phone Number: "+ guy.getPhone()));
		basicInfo.add(new JLabel(" PeopleSoft Number:  "+ guy.getPeopleSoft()));
		basicInfo.add(new JLabel(" Degree:  "+ guy.getDegree()+ "         Program:  "+ guy.getProgram()));
		basicInfo.add(new JLabel(" School:  "+ guy.getSchool()));
		
		//Next few lines are to adjust for fonts
		Component[] fontisize = basicInfo.getComponents();
		for(Component i: fontisize){
			i.setFont(mainFont);
		}
		
		add(basicInfo);
		add(new NotePanel(guy.getNotebook()));
	}
}
