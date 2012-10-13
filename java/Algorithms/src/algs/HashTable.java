package algs;
/**
 * @author sean
 *	Date Dec 5th 2010
 *	HashTable
 *	Problems: None.
 *	Class: Algorithm implementation
 */
public class HashTable {
	private int tableSize;
	private int probe;
	private Object[] table;
	private String[] keyTable;
	int tableFilled = 0;
	public HashTable(int size, int probeInterval){
		tableSize= findPrime(size);
		probe = probeInterval;
		table = new Object[tableSize];
		keyTable = new String[tableSize];
		instKeyTable();
	}
	public void instKeyTable(){
		for(int i = 0; i < keyTable.length; i++){
		}
	}
	public int hashCode(String tobe) {
		int hash = 0;
		int len = tobe.length();
		for ( int i=0; i<len; i++) {
		      hash = (31 * hash + tobe.charAt(i)) % tableSize;
		}
		return hash;
	}
	
	public void put(String key, Object value){
		int hashnum =  hashCode(key);
		if(table[hashnum] == null){
			keyTable[hashnum]= key;
			table[hashnum] = value;
			tableFilled++;
			return;
		}
		else if(tableSize-1 == tableFilled){
			System.out.println("Too many positions");
		}
		else{
			doubleHash(key, value, hashnum);
		}	
	}
	
	public Object get(String key){
		int hashnum = hashCode(key);
		int stepSize = doubleHashCode(key);
		while(keyTable[hashnum]!=null){
			if(keyTable[hashnum].equals(key)){
				return table[hashnum];
			}
					hashnum=(hashnum+stepSize)%tableSize;
		}
		return null;

	}
	public int doubleHashCode(String key){
		int sizeStr = 3721;
		sizeStr *=key.charAt(key.length()-1);
		sizeStr = (sizeStr % probe)+1;
		return sizeStr;
		
	}
	public void doubleHash(String key, Object value, int currentPos){
		if(table[currentPos] == null){
			table[currentPos]= value;
			keyTable[currentPos]= key;

		}
		else{
			int hashJump=(currentPos+doubleHashCode(key))%tableSize;
			doubleHash(key, value, hashJump);
		}
	}
	public int findPrime(int size){
		int prime = size;
		Boolean check = true;;
		while(check){
			check =checkPrime(prime);
			if(check)
			prime++;
		}
		return prime;
	}
	public Boolean checkPrime(int size){
		int i = size;
		int iter = 2;
		while(iter < i){
			if(i%iter == 0){
				return true;
			}
			iter++;
		}
		return false;
	}
	public static void main(String[]Args){
		HashTable rawr =new HashTable(80,10);
		rawr.put("pew", "argh");
		String pew = (String) rawr.get("pew");
		System.out.println(pew);
	}
}


