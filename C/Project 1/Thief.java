import java.util.ArrayList;
import java.util.Hashtable;


public class Thief {
	int count;
	ArrayList<KnapItem> items;
	Hashtable<Integer,KnapSack> memo;
	KnapSack[] table;
	
	public Thief(){
		count = 0;
		items = new ArrayList<KnapItem>();
		memo = new Hashtable<Integer, KnapSack>();
	}
	
	public void addItem(int value, int weight, String name){
		items.add(new KnapItem(weight, value, name));
	}
		
	public KnapSack memSolution(int capacity){
		count++;
		KnapSack a = new KnapSack(capacity);
		if(memo.containsKey(capacity)){
			return memo.get(capacity);
		}
		else if(a.capacity == 0){
			memo.put(capacity, a);
			return a;
		}

		else{
			for(KnapItem n : items){
				int checkNum = a.capacity - n.getWeight();
				if(checkNum < 0)
					continue;
				KnapSack temp = memSolution(checkNum);
				 if(temp.value > a.value){
					a = temp;
				}
			}
			memo.put(capacity, a);
			return a;
		}
	
	}
	public KnapSack tabSolution(int capacity){
		count = 0;
		table = new KnapSack[capacity+1];
		
		for(int i = 0; i < capacity; i++){
			KnapSack a = new KnapSack(i);
			for(KnapItem k: items){
				count++;
				int newCap =a.capacity-k.getWeight();
				if(newCap < 0)
					continue;
				else if(newCap == 0){
					a.value = 0;
				}
				else{
					
				}
			}
			table[i] = a;
		}
		return table[10];
	}
	
	public KnapSack recSolution(int capacity){
		count++;
		KnapSack a = new KnapSack(capacity);
		if(a.capacity == 0){
			a.value = 0;
			return a;
		}
		else{
			for(KnapItem n : items){
				int newCap = a.capacity - n.getWeight();
				if(newCap < 0)
					continue;
				KnapSack temp = recSolution(newCap);
				temp.value += n.getValue();
				temp.knapValues.add(n);

				if(temp.value > a.value){
					a = temp;
				}
			}

			return a;
		}
	}
	
	public static void main(String[]Args){
		Thief a = new Thief();
		a.addItem(4, 5, "B");
		a.addItem(7, 10, "C");
		a.addItem(3, 4, "A");

		KnapSack b = a.recSolution(1);
		
		System.out.println(b);
		a.count=0;
		b = a.memSolution(30);
		System.out.println(a.count);
	}
}
