package parser;

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