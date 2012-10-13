import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class ProteinFinder2 {
	
	public ArrayList<String> edgeleft;
	public ArrayList<String> edgeright;
	private JFileChooser Opener;
	public ArrayList<Protein> proteins;
	ArrayList<String> idholder;
	public ProteinFinder2(){
		edgeleft = new ArrayList();
		edgeright = new ArrayList();
	}
	
	public void findEllements(){

		//Create variables
		proteins = new ArrayList();
		idholder = new ArrayList();
		Scanner oboIn = null;
		Scanner gofeatIn = null;
		Scanner classIn = null;
		
		

		//Read in all files
		Opener = new JFileChooser();
		Opener.showOpenDialog(null);
		File inFile =  Opener.getSelectedFile();
		try {
			oboIn = new Scanner(inFile);
		} catch (FileNotFoundException e1) {
			System.out.println("OBOv1.3 not found");
		}
		Opener = new JFileChooser();
		Opener.showOpenDialog(null);
		inFile =  Opener.getSelectedFile();
		try {
			gofeatIn = new Scanner(inFile);
		} catch (FileNotFoundException e1) {
			System.out.println("goFeature not found");
		}
		Opener = new JFileChooser();
		Opener.showOpenDialog(null);
		inFile =  Opener.getSelectedFile();
		try {
			classIn = new Scanner(inFile);
		} catch (FileNotFoundException e1) {
			System.out.println("CellPartClassification not found");
		}
		
		String lineTerm = null;
		
		
		
		lineTerm = classIn.nextLine();
		ArrayList<String> partarray = new ArrayList();
		String location = new String();
		while(classIn.hasNextLine()){
			String str = new String();
			str = lineTerm;
			lineTerm = classIn.nextLine();
			while(!lineTerm.equals("") && lineTerm.charAt(0) == '0'){
				str = str+" "+lineTerm.substring(0,lineTerm.indexOf(":"));
				lineTerm = classIn.nextLine();
			}	
			partarray.add(str);
		}
		
		

		ArrayList<String> cellIDs = new ArrayList();
		for(lineTerm = oboIn.next(); oboIn.hasNext(); lineTerm = oboIn.next()){
			if(lineTerm.equals("id:")){
				String tempID = oboIn.next().substring(3);
				oboIn.nextLine();
				oboIn.nextLine();
				oboIn.next();
				String loc = oboIn.next();
				if(loc.equals("cellular_component")){
					cellIDs.add(tempID);
				}
			}
		}
	
		//sorts the proteins by ID
		Collections.sort(cellIDs);
		
		
		
		lineTerm = gofeatIn.next();
		int count = 0;			
		String Numbers= JOptionPane.showInputDialog("How many nodes do you want?");
		int nodeNumber = Integer.parseInt(Numbers);
		
		for(int i = 0; i < nodeNumber; i++){
		//while (gofeatIn.hasNext()){
			Protein p = new Protein(lineTerm);
			lineTerm = gofeatIn.next();
			ArrayList<String> ids = new ArrayList();

			while(lineTerm.charAt(0) == '0'&& gofeatIn.hasNext()){
				//checks if its in the cell
				//if(binarySearch(cellIDs, cellIDs.size(), lineTerm)>=0){
					ids.add(lineTerm);
				//}
				lineTerm = gofeatIn.next();
			}
			if(gofeatIn.hasNext() == false){
				ids.add(lineTerm);
			}
			p.setGoID(ids, partarray);
			proteins.add(p);
		}
		
		
	}
	
	public void setEdges(){
		Scanner humanIn = null;

		Opener = new JFileChooser();
		Opener.showOpenDialog(null);
		File inFile = Opener.getSelectedFile();
		try {
			humanIn = new Scanner(inFile);
		} catch (FileNotFoundException e1) {
			System.out.println("Human.pos.go not found");
		}


		for(String lineTerm = humanIn.next(); humanIn.hasNext(); lineTerm = humanIn.next()){
			edgeleft.add(lineTerm);
			System.out.println(lineTerm);
			lineTerm = humanIn.next();
			edgeright.add(lineTerm);
			humanIn.next();
			if(!humanIn.hasNext())break;
		}
	}
	
	
	
	
	
	public static int binarySearch(ArrayList<String> list, int listLength, String searchItem) {
	    int first=0;
	    int last = listLength - 1;
	    int mid;
	    
	    boolean found = false;
	    while(first <= last &&!found) {
	        mid = (first + last) /2;
	        
	        if(Integer.parseInt(list.get(mid))==Integer.parseInt(searchItem)) found = true;
	        else { 
	            if( Integer.parseInt(list.get(mid)) > Integer.parseInt(searchItem)) last = mid -1;
	            else first = mid + 1;
	        }
		    if(found) return mid;
	    }
	    return(-1);
	}

}