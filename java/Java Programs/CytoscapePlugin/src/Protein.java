import java.util.ArrayList;


public class Protein implements Comparable{
	public String protnum;
	public ArrayList<String> go_id;
	public String location;
	
	public Protein(String id){
		protnum = id;
		location = "none";
		go_id = new ArrayList();
	}
	
	public void setGoID(ArrayList<String> IDs, ArrayList<String> partarray){		
		for(int i = 0; i<IDs.size(); i++){
			for(int j = 0; j < partarray.size(); j++){
				if(partarray.get(j).contains(IDs.get(i))){
					go_id.add(partarray.get(j).substring(0,partarray.get(j).indexOf(" ")));
				}
			}
		}
		
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getId(){
		return protnum;
	}
	
	public String toString(){
		String str = new String("\n\nProtID: "+protnum+"\nLocations: "+go_id);
		return str;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
