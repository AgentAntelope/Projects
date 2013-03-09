public class TVClass{
	public static String STAN = "Standard";
	public static String ED = "EDTV";
	public static String HD = "HDTV";

	public String type, brand;
	public String res;
	public int size;
	public TVClass(String b){
		brand = b;
	}
	public void setSize(int s){
		size = s;
	}

	public void setResolution(String resolution){
		res = resolution;
	}
}