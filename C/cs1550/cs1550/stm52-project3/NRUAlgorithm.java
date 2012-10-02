/**
 * Sean Myers -NRU Algorithm
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;


public class NRUAlgorithm {
	HashMap<String, Page> pageTable;
	LinkedList<Page> instructions;
	int maxTable, pageFaults, writes, insCount, resetCounter;
	
	public NRUAlgorithm(String fileName, int pageNum){
		instructions = new LinkedList<Page>();
		pageTable = new HashMap<String, Page>();
		maxTable = pageNum;
		initInstructions(fileName);
		insCount = instructions.size();
		NRU();
	}
	
	/*Will read the instructions in from the file and then create an object for each line
	  and then add those to the linked list instructions. 
	*/
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
	public void NRU(){
		int position = 0;
		Page currentNode = null;
		if(instructions.size() == 0){
			return;
		}
		while(instructions.size() > 0){
			position++;
			currentNode = instructions.remove();
			currentNode.setRead(true); //It has to be read when it is brought in.
			
			//Every 10,000 memory references, reset R-bits.
			if(position %10000== 0){
				resetRead();
				resetCounter++;
			}
			if(currentNode.op){
				currentNode.setModified(true);
			}		
			if(pageTable.containsKey(currentNode.getMemLocation())){
				pageTable.get(currentNode.getMemLocation()).setRead(true); //Just in case a read bit was set to 0, needs updated.
				System.out.println("Hit");
			}
			else if(pageTable.size() >= maxTable){
				pageFaults++;
				removeNRU();
				pageTable.put(currentNode.getMemLocation(), currentNode);
				
			}
			else{
				pageFaults++;//Remove if compulsory miss doesn't count.
				System.out.println("Page Fault --Compulsory Miss");
				pageTable.put(currentNode.getMemLocation(), currentNode);
			}
		}
		System.out.println("Number of frames:" + maxTable);
		System.out.println("Total Memory Access: "+ insCount);
		System.out.println("Total Page Faults: " + pageFaults);
		System.out.println("Total Writes To Disk: " + writes);
	}
	/**
	 * This algorithm is based off the books definition of classes. The algorithm
	 * will attempt to choose the lowest class if it is available. If it is not,
	 * then it will go to the next class. There HAS to be at least 1 class since
	 * this is only called if the page table has greater than the size.
	 */
	private void removeNRU(){
		Page removeNode = null, class1 = null,class2 = null,class3 = null;
		for(Page currentPage: pageTable.values()){
			if(!currentPage.getRead() && !currentPage.getModified()){
				removeNode = currentPage;
				break;
			}
			else if(!currentPage.getRead() && currentPage.getModified()){
				class1 = currentPage;

			}
			else if(currentPage.getRead() && !currentPage.getModified()){
				class2 = currentPage;
			}
			else if(currentPage.getRead() && currentPage.getModified()){
				class3 = currentPage;
			}
		}		
		
		//If there is no class 0, choose the next highest class.
		if(removeNode == null){
			if(class1 != null){
				removeNode = class1;
			}
			else if(class2!=null){
				removeNode = class2;
			}
			else{
				removeNode = class3;
			}
		}
		pageTable.remove(removeNode.getMemLocation());
		declarePageFault(removeNode);
	}
	
	//called every 10,000 instructions to reset all page's read bit.
	private void resetRead(){
		for(String currentPage: pageTable.keySet()){
			pageTable.get(currentPage).setRead(false);
		}
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