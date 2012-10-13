package Dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class SearchTool extends DeployDatabase{
	
	public SearchTool(){
		super();
	}
	
	public void readVocab(File file){
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);		
			ObjectInputStream in = new ObjectInputStream(fis);
			DeployDatabase t = (DeployDatabase)in.readObject();
			this.setHashtable(t.getHashtable());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void queryVocabulary(String englishWord){
		
	}

	public boolean addWordToVocabulary(String englishWord, String latinWord){
		TreeMap<String,LinkedList<String>> yar = this.getHashtable();
		
		if(yar.containsKey(englishWord)){
			yar.get(englishWord).add(latinWord);
			return true;
		}
		else if(!yar.containsKey(englishWord)){
			LinkedList<String> temp = new LinkedList<String>();
			temp.add(latinWord);
			yar.put(englishWord, temp);
			return true;
		}
		else
		  return false;
	}

	public boolean removeWordFromVocabulary(String englishWord, String latinWord){
		if(this.isEmpty())
		    return false;
		else if(!this.getHashtable().containsKey(englishWord))
			return false;
		else{
		     TreeMap<String, LinkedList<String>> pew = this.getHashtable();
			boolean returnStuff= pew.get(englishWord).remove(latinWord);
			 if(pew.get(englishWord).isEmpty())
				 pew.remove(englishWord);
			 return returnStuff;
		}
	}
	public static void main(String[]Args){
		SearchTool b = new SearchTool();
		b.displayVocabulary();
		
	}
}
