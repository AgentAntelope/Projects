class Edge<L> implements Comparable{
  L v;
  L w;
  double weight;
  double distance;
  
  Edge(this.v, this.w, this.weight){
    distance = -1.0;
  }
  L other(L vert)=>(vert==v)?w:v;
  String toString()=> "$v-$w: $weight, $distance";
  
  int hashCode(){
    return 1;
  }
  
  int compareTo(Edge other){
    if(this.distance == -1 && other.distance > 0){
      return 1;
    }
    if(this.distance == -1 && other.distance == -1){
      return 0;
    }
    if(this.distance < other.distance){
      return -1;
    }
    else if(this.distance > other.distance){
      return 1;
    }
    else{
      return 0;
    }
  }
}
class Graph<L>{
  int vertices;
  int edges;
 Map<L,Set<Edge>> adjacent;
  Graph(){
    vertices= 0;
    edges = 0;
    adjacent = new Map<L,Set<Edge>>();
  }
  addEdge(L v, L w, double weight){
    if(!adjacent.containsKey(v)  || !adjacent.containsKey(w)){
      throw "Vertex doesn't exist";
    }
    Edge temp = new Edge(v,w,weight);
    adjacent[v].add(temp);
    adjacent[w].add(temp);
    edges++;
  }
  addVertex(L vertex){
    adjacent[vertex] = new Set();
    vertices++;
  }
  Set<Edge> adj(L vertex){
    return adjacent[vertex];
  }

}

/////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////
class PriorityQueue<E extends Comparable>{
  Heap<E> heap;
  PriorityQueue(){
    heap = new Heap<E>();
  }
  push(E ele){
    heap.insert(ele);
  }
  E pop(){
    if(heap.heap.length == 0){
      return null;
    }
    else{
      return heap.remove();
    }
  }
  String toString(){
    StringBuffer a = new StringBuffer();
    for(final e in heap.heap){
      a.add("$e, ");
    }
    return a.toString();
  }
}

class Heap<E extends Comparable> {
  List<E> heap;
  
  Heap(){
     heap = new List<E>();
   }
  insert(E node){
    heap.add(node);
    percolate(heap.length-1);
  }
  E remove(){
    if(heap.length== 0){
      throw "RAAAAAWWWWRRRRR";
    }
    swap(0, heap.length-1);
    E retItem = heap.removeLast();
    simmer(0);
    return retItem;
  }
  simmer(int node){
    if(node > heap.length){
      return;
    }
    else{
      int left = left(node);
      int right = right(node);
      int swapper;
      if(left >= heap.length && right >= heap.length){
        return;
      }
      else if(left >= heap.length){
         swapper = right;
      }
      else if(right >= heap.length){
        swapper = left;
      }
      else{
        swapper = (heap[left].compareTo(heap[right]) < 0)? left:right;
    } 
      if(heap[node].compareTo(heap[swapper]) > 0){
          swap(node, swapper);
          simmer(swapper);
      }
  
    }
  }
  percolate(int node){
    if( node <= 0 || node >= heap.length){
        return;
     }
    else{
       int parent = parent(node);

       if(heap[parent].compareTo(heap[node]) > 0){
          swap(parent, node);
          percolate(parent);
        }
    }
    
  }
  swap(int node1, int node2){
    E temp = heap[node1];
    heap[node1]= heap[node2];
    heap[node2]= temp;
  }
  int parent(int node)=>(node-1)>>1;
  int right(int node)=>2*node+1;
  int left(int node)=>2*node+2;
}