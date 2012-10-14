package parser;

/* Just an encapsulation of Data from the string section. can possibly make this more dynamic in the future
	by adding more types, and possibly enum / spacing attributes. */
public class Data{
	private int strValue, dataPosition;
	public Data(int position, int value){
		dataPosition = position;
		strValue = value;
	}

	public int getStrData(){
		return strValue;
	}

	public int getHeapPosition(){
		return dataPosition;
	}

}