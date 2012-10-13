package Try;

import java.io.Serializable;
import java.util.LinkedList;


public class Notebook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5757964184561921262L;
	private LinkedList<Note> n; 
	private int size;
	
	public Notebook(){
		n = new LinkedList<Note>();
		size = 0;
	}
	//@param firstNote The string of the first note in the Notebook.
	public Notebook(String firstNote, boolean firstAlert){
		n = new LinkedList<Note>();
		n.add(new Note(firstNote, firstAlert));
		size = 1;
	}
	
    //Each individual Note will consist of an alert and the text describing it.	
	
	 class Note implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7732190831256191219L;
		private String note;
		private boolean alert;
		
		public Note(String x, boolean y){
			note = x;
			alert = y;
		}
		public void changeAlert(boolean alertChange){
			alert = alertChange;
		}
		@Override
		public String toString(){
			return note;
		}
	}
	//----------End of Note class----------------------------------------
	
	public void addNote(String newNote){
		n.add(new Note(newNote, false));
		size++;
	}
	
	public void addNote(String newNote, boolean alertStatus){
		n.add(new Note(newNote, alertStatus));
		size++;
	}
	Note retrieveNote(int x){
		return n.get(x);
	}
	public LinkedList<Note> returnNotebook(){
		return n;
	}
	@Override
	public String toString(){
		StringBuilder t = new StringBuilder();
		for(Note l : n){
			t.append(l);
			t.append("\n");
		}
		return t.toString();
	}

}

	

