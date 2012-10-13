package Try;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Try.Notebook.Note;

public class NotePanel extends JPanel {
	DefaultListModel baseModel;
	JCheckBox alert;
	JButton addnewNote;
	JList notesPage;
	JTextField newNote;
	Notebook joomla;
	public NotePanel(Notebook Joomla){
		super();
		setBorder(BorderFactory.createTitledBorder("Notes"));
		baseModel = new DefaultListModel();
		setPreferredSize(new Dimension(460,150));
		joomla = Joomla;
		newNote = new JTextField(30);
		for(Note t : joomla.returnNotebook()){
			baseModel.addElement(t);
		}
		addnewNote = new JButton("Add Note") ;
		alert = new JCheckBox("Alert");
		String[] pew = new String[5];
		notesPage= new JList(baseModel);

		JScrollPane holdNotePage = new JScrollPane(notesPage);
		holdNotePage.setPreferredSize(new Dimension(480,180));
		addnewNote.addActionListener(new noteListener());
		add(newNote);
		add(alert);
		add(addnewNote);
		add(holdNotePage);
	}
	private class noteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String noteStuff = newNote.getText();
			newNote.setText("");
			joomla.addNote(newNote.getText(), alert.isSelected());
			alert.setSelected(false);
			baseModel.addElement(noteStuff);
		}
		
	}

	
}
