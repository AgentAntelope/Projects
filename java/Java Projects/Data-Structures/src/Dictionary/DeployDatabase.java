package Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.*;

public class DeployDatabase implements Serializable{
	   /**
	 * 
	 */
	private TreeMap<String,LinkedList<String>> hashtable;  

	   //constructor
	   public DeployDatabase(){
		   hashtable = new TreeMap<String, LinkedList<String>>();
	   }

	   //setter
	   @SuppressWarnings("unchecked")
	public void setHashtable(TreeMap h){
		   hashtable = h;
	   }

	   //getter
	   public TreeMap getHashtable(){
		   return hashtable;
	   }

	   public boolean isEmpty(){
		 return hashtable.isEmpty();
	   }

	   //create database from text file
	   public void readText(File file){
		   
		   try {
			Scanner in = new Scanner(file);
			while(in.hasNext()){
				String yar = in.nextLine();
				if(!yar.equalsIgnoreCase("")){
					if(!yar.substring(0, 1).equals("%")){
						String[] LatinWord = yar.split(": ");
						String[] EnglishWords = LatinWord[1].split(", ");
						for(String English : EnglishWords){
							if(hashtable.containsKey(English)){
								LinkedList<String> i =hashtable.get(English);
								i.add(LatinWord[0]);
							}
							else{
								LinkedList<String> temp =new LinkedList<String>();
								temp.add(LatinWord[0]);
								hashtable.put(English, temp);
							}
							
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }

	   //display database of English words and their Latin equivalents
	   public String displayVocabulary(){
		   StringBuilder a= new StringBuilder();
		  Set<String> hey= hashtable.keySet();
		  for(String key: hey){
			 LinkedList<String> latin= hashtable.get(key);
			 a.append(key + ": ");
			 
			 for(String latino : latin){
				 a.append(latino + ", ");
			 }
			 a.append("\n");
		  }
		  return a.toString();
	   }

	   //write database to a binry file
	   public void writeVocabulary(){
		   try {
			FileOutputStream a = new FileOutputStream("latino.txt");
			ObjectOutputStream b = new ObjectOutputStream(a);
			b.writeObject(this);
			b.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      }
	   public void setFile(File file){
		   
	   }
	   
	   public static void main(String[]Args){
		DeployDatabase yar=new DeployDatabase(); 
		//yar.readText();
		yar.displayVocabulary();
		yar.writeVocabulary();
	   }
	}


