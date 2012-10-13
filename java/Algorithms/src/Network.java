/**  @Author: Sean Myers (Iph)
 *  Email: Seanmyers0608@gmail.com
 *  Project: NetworkFlow
 *  
 *  Note to anyone grading: I hadn't used enums in a long time so I
 *  decided to implement them into this project. Just letting you know
 *  since they are a rarity in most code.
 */
import java.util.*;

public class Network {
	String nName;
	enum flowType{forward, phantom, emptiness};
	Storage n; 
	String[] nNames;
	flowType[][] adjMatrix; 
	int[][] flow;
	
	public Network(String a){
		nName = a;
		Node t;
		
		n = new Storage();
		String[] nodeNames = a.split(" ");
		nNames = nodeNames;
		for(int i = 0; i < nodeNames.length; i++){
			t = new Node(nodeNames[i]);
			t.index = i;
			n.store(t);
		}
		adjMatrix = new flowType[nodeNames.length][nodeNames.length];
		zeroMatrix();
		flow = new int[nodeNames.length][nodeNames.length];
	}

	public void setCap(String a, String b, int initCap){
		Node startNode = n.retrieve(a);
		Node endNode = n.retrieve(b);
		adjMatrix[startNode.index][endNode.index] = flowType.forward;	
		adjMatrix[endNode.index][startNode.index] = flowType.phantom;
		
		flow[startNode.index][endNode.index] = 0;		
		flow[endNode.index][startNode.index] = initCap;	
	}
	public void zeroMatrix(){
		for (int i = 0; i < adjMatrix.length; i++){
			for(int j = 0; j < adjMatrix.length; j++){
				adjMatrix[i][j] = flowType.emptiness;
			}
		}
	}
	public void print(){
		Node t;
		int index;
		for(int i=0; i<nNames.length; i++){
			t = n.retrieve(nNames[i]);
			System.out.print(t.name + "  -->  ");
			index = t.index;
			for(int j=0; j<adjMatrix.length; j++){
				if(adjMatrix[index][j]==flowType.forward){
					System.out.print(nNames[j] + ":" + flow[index][j] + "/" 
					+ (flow[index][j]+flow[j][index]) + "  ");
				}
			}
			System.out.println();
		}
	}
	
	public void maximizeFlow(String a, String b){
		Node sink = n.retrieve(b);
		Node t;
		int r;
		while(findBestPath(n.retrieve(a), n.retrieve(b))){
			t = sink;
			r = minResidual(n.retrieve(b));
			while(t.root!=null){
				flow[t.root.index][t.index] += r;
				flow[t.index][t.root.index] -= r;
				t = t.root;
			}		
			
		}
	}

	private boolean findBestPath(Node source, Node sink){
		netQueue<Node> q = new netQueue<Node>();
		Node curr,children, t;
		for(int i =0; i<nNames.length; i++){
			t = n.retrieve(nNames[i]);
			t.root = null;
			t.visited = false;
		}
		q.push(source);
		
		while(!q.isEmpty()){
			curr = q.pop();
			if(curr==sink){
				return true;
			}
			if(curr.visited)
				continue;
			curr.visited = true;

			for(int i=0; i<adjMatrix.length; i++){
				children = n.retrieve(nNames[i]);
				if(adjMatrix[curr.index][i]!=flowType.forward){
					continue;
				}
				else if(children.visited){
					continue;
				}
				else if(flow[children.index][curr.index]>0){
					children.root = curr;
					q.push(children);
				}
				
			}

		}
		return false;
	}
	private int minResidual(Node sink){
		int r = flow[sink.index][sink.root.index];
		while(sink.root!=null){
			if(flow[sink.index][sink.root.index]<r)
				r = flow[sink.index][sink.root.index];
			sink = sink.root;
		}
		return r;
	}
	
	

	
	
}