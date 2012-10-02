/**
 * Sean Myers -Clock Algorithm
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;


public class ClockAlgorithm {
	HashMap<String, Page> pageTable;
	LinkedList<Page> instructions;
	LinkedList<Page> clockQueue;
	int maxTable, pageFaults, writes, insCount, resetCounter, hand;
	
	public ClockAlgorithm(String fileName, int pageNum){
		instructions = new LinkedList<Page>();
		pageTable = new HashMap<String, Page>();
		maxTable = pageNum;
		clockQueue = new LinkedList<Page>();
		initInstructions(fileName);
		insCount = instructions.size();
		Clock();
		
	}
	private void initInstructions(String file){
		try{
			BufferedReader read = new BufferedReader(new FileReader(new File(file)));//File parser
			String currLine = read.readLine();
			int position = 0;
			while(currLine != null){
				String[] temp = currLine.split(" ");
				Page currNode = new Page(temp[0], temp[1]);
				instructions.add(currNode);
				position++;
				currLine = read.readLine();				
			}
			
			
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot find the file you input.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void Clock(){
		Page currentNode = null;
		if(instructions.size() == 0){
			return;
		}
		while(instructions.size() > 0){
			currentNode = instructions.remove();
			currentNode.setRead(true); //It has to be read when it is brought in.

			if(currentNode.op){
				currentNode.setModified(true);
			}		
			if(pageTable.containsKey(currentNode.getMemLocation())){
				pageTable.get(currentNode.getMemLocation()).setRead(true); //Just in case a read bit was set to 0, needs updated.
				System.out.println("Hit");
			}
			else if(pageTable.size() >= maxTable){
				pageFaults++;
				Page removeFromTable = swapClock(currentNode);
				pageTable.remove(removeFromTable.getMemLocation());
				pageTable.put(currentNode.getMemLocation(), currentNode);
			}
			else{
				pageFaults++;//Remove if compulsory miss doesn't count.
				System.out.println("Page Fault --Compulsory Miss");
				pageTable.put(currentNode.getMemLocation(), currentNode);
				clockQueue.add(currentNode);
			}
		}
		System.out.println("Number of frames:" + maxTable);
		System.out.println("Total Memory Access: "+ insCount);
		System.out.println("Total Page Faults: " + pageFaults);
		System.out.println("Total Writes To Disk: " + writes);
	}

	private Page swapClock(Page toBePlaced){
		Page currentNode = clockQueue.get(hand);
		while(currentNode.getRead()){
			currentNode.setRead(false);
			hand = (hand+1) % maxTable;
			currentNode = clockQueue.get(hand);
		}
		clockQueue.set(hand, toBePlaced);
		hand = (hand+1) % maxTable;
		declarePageFault(currentNode);
		return currentNode;
	}
	private void declarePageFault(Page node){
		if(node.getModified()){
			System.out.println("Page Fault--Evict Dirty");
			writes++;
		}
		else{
			System.out.println("Page Fault--Evict Clean");
		}		
	}
}
