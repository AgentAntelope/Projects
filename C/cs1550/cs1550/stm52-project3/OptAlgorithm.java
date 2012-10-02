/**
 * Sean Myers -Opt Algorithm
 */
import java.io.*;
import java.util.*;
public class OptAlgorithm {
	HashMap<String, LinkedList<PageOp>> opMoves;
	HashMap<String, PageOp> pageTable;
	LinkedList<PageOp> instructions;
	int maxTable, pageFaults, writes, insCount;
	
	public OptAlgorithm(String fileName, int pageNum){
		instructions = new LinkedList<PageOp>();
		pageTable = new HashMap<String, PageOp>();
		opMoves = new HashMap<String, LinkedList<PageOp>>();
		maxTable = pageNum;
		initInstructions(fileName);
		insCount = instructions.size();		
		Opt(); //Actual algorithm.
		
	}
	/**
	 * 
	 * @param file
	 * 
	 * Takes the file and parses it into a linked list with the instructions.
	 * will also place the correct hashmap opmoves
	 */
	
	private void initInstructions(String file){
		try{
			BufferedReader read = new BufferedReader(new FileReader(new File(file)));//File parser
			String currLine = read.readLine();
			int position = 0;
			while(currLine != null){
				String[] temp = currLine.split(" ");
				PageOp currNode = new PageOp(temp[0], temp[1], position);
				instructions.add(currNode);
				addOp(temp[0], currNode);
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
	/*
	 * Puts in the correct linked list.
	 * Since the node is being put on the end, it will be in
	 * time order.
	 */
	private void addOp(String memName, PageOp currNode){
		if(opMoves.containsKey(memName)){
			if(opMoves.get(memName) != null){
				opMoves.get(memName).add(currNode);
			}
			else{
				LinkedList<PageOp> temp = new LinkedList<PageOp>();
				temp.add(currNode);
				opMoves.put(memName, temp);
			}
		}
		else{
			LinkedList<PageOp> temp = new LinkedList<PageOp>();
			temp.add(currNode);
			opMoves.put(memName, temp);
		}
	}
	/**************************Start of the main algorithm*************************************************/
	
	public void Opt(){
		PageOp currentNode = null;
		if(instructions.size() == 0){
			return;
		}
		while(instructions.size() > 0){
			/*Will remove the corrent node, get the linked list and remove that node as well.
			This way, when we look to remove the optimum node, the first node in the linked list
			is the node that will be used next.*/
			currentNode = instructions.remove();
			LinkedList<PageOp> list = opMoves.get(currentNode.getMemLocation());
			list.remove();
			if(currentNode.op){
				currentNode.setModified(true); //Only if it is a read operation.
			}		
			if(pageTable.containsKey(currentNode.getMemLocation())){
				System.out.println("Hit");
			}
			else if(pageTable.size() >= maxTable){
				pageFaults++;
				removeOpt();//main part of the opt algorithm.
				pageTable.put(currentNode.getMemLocation(), currentNode);//Now that we removed the opt node, put in new node.
				
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
	 * Iterates through the "Page Table".
	 * if one of the linked lists is empty, that means the node will not be
	 * used again (infinite time to be used again), remove that node and leave.
	 * Otherwise, store top node and remove it at the end.
	 */
	private void removeOpt(){
		PageOp nodeToRemove = null;
		for(String currLocation: pageTable.keySet()){
			if(opMoves.get(currLocation).isEmpty()){
				nodeToRemove = pageTable.remove(currLocation);
				declarePageFault(nodeToRemove);
				return;
			}
			//Gets first node since that will be the next one to be called(see comment in main Opt algorithm)
			else if(nodeToRemove == null){
				nodeToRemove = opMoves.get(currLocation).get(0);
			}
			//If the next node of this current memory location access is greater than the current one, swap them.
			else if(nodeToRemove.getPosition() < opMoves.get(currLocation).get(0).getPosition()){
				nodeToRemove = opMoves.get(currLocation).get(0);
			}
		}
		nodeToRemove = pageTable.remove(nodeToRemove.getMemLocation());//This is done to get the modified attribute of the evicted.
		declarePageFault(nodeToRemove);
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
