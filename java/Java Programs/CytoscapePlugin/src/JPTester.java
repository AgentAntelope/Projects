

public class JPTester {
	public static void main (String [] args){
		ProteinFinder2 finder = new ProteinFinder2();
		finder.findEllements();
		for(int i = 0; i < finder.proteins.size(); i++)
		{
			if(finder.proteins.get(i).go_id.size()!= 0)
		System.out.println(finder.proteins.get(i).go_id.get(0));
		}

		}
	}

