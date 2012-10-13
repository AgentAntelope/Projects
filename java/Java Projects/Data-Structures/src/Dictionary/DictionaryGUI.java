package Dictionary;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DictionaryGUI {
	private JFrame theWindow;	// Window
	private JPanel controlPanel, infoPanel;	// Panels for the
	private JButton listWords, addWord, findWord, removeWord;
	private JTextArea info;	// See JTextArea in Java API
	private Container thePane;
	private Controlistener theListener;
	private JScrollPane ta;
	private SearchTool dictionary;
	private JFileChooser open;
	private listAction listOpen;
	
	public DictionaryGUI(){

		dictionary = new SearchTool();
		
		controlPanel = new JPanel();
		infoPanel = new JPanel();
		
		listWords = new JButton("List");
		addWord = new JButton("Add a Word");
		findWord = new JButton("Find a Word");
		removeWord = new JButton("Remove a Word");
		info = new JTextArea();
		info.setRows(20);
		info.setColumns(40);
		info.setWrapStyleWord(true);
		ta = new JScrollPane(info);
		infoPanel.add(ta);
		listOpen = new listAction();
		listWords.addActionListener(listOpen);
		
		controlPanel.setLayout(new GridLayout(0,1, 0, 12));
		controlPanel.setBorder(BorderFactory. createEmptyBorder(6, 16, 240, 16) ) ;
		controlPanel.add(listWords);
		controlPanel.add(addWord);
		controlPanel.add(findWord);
		controlPanel.add(removeWord);
		
		theWindow = new JFrame("Dictionary");

		theWindow.setSize(500,400);
		thePane = theWindow.getContentPane();
		thePane.add(controlPanel, BorderLayout.EAST);
		thePane.add(infoPanel, BorderLayout.CENTER);
		   try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(thePane);
			} catch(Exception e){}
		// pack() to size JFrame as needed
		theWindow.pack();
		theWindow.setSize(theWindow.getWidth(),theWindow.getHeight());
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.setResizable(false);
		theWindow.setVisible(true);
		
	}
	public static void main(String[]Args){
		new DictionaryGUI();
	}
	class listAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser open = new JFileChooser();
			int fileInt = open.showOpenDialog(theWindow);
		    File file = open.getSelectedFile();
			
			if(file.getName().contains(".txt"))
				dictionary.readText(file);
			else
				dictionary.readVocab(file);
			String displayText = dictionary.displayVocabulary();
			info.setText(displayText);
			
			
		}
		
	}
	
	class Controlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}


	}

