#import('dart:dom');
#source('Lib.dart');
#resource('Graph.css');
CanvasRenderingContext2D ctx;
HTMLCanvasElement can;
Map<int, List<int>> verts;
Map<int, Map<int,int>> links;
Graph<int> G;

int pastHighlight;
main() {
  verts = new Map<int, List<int>>();
  can = document.getElementById("draw");
  links = new Map<int, Map<int,int>>();
  ctx = can.getContext("2d");
  pastHighlight = -1;
  G = new Graph<int>();
  G.addVertex(1);
  G.addVertex(2);
  G.addVertex(3);
  G.addVertex(4);
  G.addVertex(5);
  G.addVertex(6);
  
  G.addEdge(1,2,4.0);
  G.addEdge(2,3,1.0);
  G.addEdge(1,3,2.0);
  G.addEdge(3,4,8.0);
  G.addEdge(2,4,5.0);
  G.addEdge(4,5,2.0);
  G.addEdge(3,5,10.0);
  G.addEdge(5,6,2.0);
  G.addEdge(4,6,6.0);
  generateVertices(G);
  drawGraph(G);
  for(final int i in G.adjacent.getKeys()){
    links[i] = LinkState(G, i);
  }
  can.addEventListener("mousedown", (MouseEvent e){
    int intersect = intersection(e.offsetX, e.offsetY);
    if(intersect == -1){
      return;
    }
    if(pastHighlight != -1){
      drawVertex(verts[pastHighlight][0], verts[pastHighlight][1],pastHighlight,"rgb(180,180,180)");
    }
    pastHighlight = intersect;
    drawVertex(verts[pastHighlight][0], verts[pastHighlight][1],pastHighlight,"rgb(30,30,30)");
    generateTable(links[intersect], intersect);
  });
}
generateTable(Map<int, int> map, int intersect){
  HTMLTableElement table = document.getElementById("table");
  table.innerHTML = '''
         <tr>
             <th>Node</th>
             <th>Output Hop</th>
         </tr>
   ''';
  
  for(final int i in map.getKeys()){
    HTMLElement tr = table.insertRow(table.childElementCount);
    tr.addEventListener("mouseover", (MouseEvent e){
      highlightPath(intersect, i);
    });
    tr.addEventListener("mouseout", (MouseEvent e){
      drawGraph(G);
    });
    tr.innerHTML='''
            <td>$i</td>
            <td>${map[i]}</td>
        ''';
  }
}
highlightPath(int sourceVert, int destVert){
  int newSource = sourceVert;

  while(sourceVert != destVert){
    Edge e = findEdge(sourceVert, links[sourceVert][destVert]);
    drawEdge(verts[e.v][0], verts[e.v][1], verts[e.w][0], verts[e.w][1], e.weight, "rgb(153,163,255)","rgb(24,61,201)");
    drawVertex(verts[sourceVert][0], verts[sourceVert][1],sourceVert,"rgb(130,130,130)","rgb(153,163,255)");
    sourceVert = e.other(sourceVert);
    drawVertex(verts[sourceVert][0], verts[sourceVert][1],sourceVert,"rgb(130,130,130)","rgb(153,163,255)");

  }
  drawVertex(verts[sourceVert][0], verts[sourceVert][1],sourceVert,"rgb(130,130,130)","rgb(70,252,188)");
  drawVertex(verts[newSource][0], verts[newSource][1],newSource,"rgb(130,130,130)","rgb(70,252,188)");

    
}
Edge findEdge(int v, int w){
  for(Edge e in G.adj(v)){
    if(e.other(v) == w){
      return e;
    }
  }
}
Map LinkState(Graph g, int vertex){
  //Graph<int> N = new Graph<int>();
  Map<int, int> parent = new Map<int, int>();

  //Used for priority queue so that if the set already contains this, don't re-add to priority queue
  Set<Edge> currentEdges = new Set<Edge>();
  
  //Sets determining what is in the working set, and what is not.
  Set<int> other = new Set<int>();
  Set<int> djik = new Set<int>();
  PriorityQueue<Edge> q = new PriorityQueue<Edge>();
  
  other.addAll(g.adjacent.getKeys());
  other.remove(vertex);
  djik.add(vertex);
 
  //Initialization
  for(final Edge e in g.adj(vertex) ){
    currentEdges.add(e);
    e.distance = e.weight;
    parent[e.other(vertex)] = vertex;
    q.push(e);
  }
  
  
  //Loop
  while(other.length > 0){
    Edge e = q.pop();
    //If v is the node in our set and w is the minimum vertex...
    if(djik.contains(e.v) && djik.contains(e.w)){
      continue;
    }

    if(djik.contains(e.v)){
      djik.add(e.w);
      other.remove(e.w);
      parent[e.w] = e.v;
      for(final edgeNotN in g.adj(e.w)){
        if(!currentEdges.contains(edgeNotN)){
          print(edgeNotN);
          edgeNotN.distance = min(edgeNotN.distance,(e.distance + edgeNotN.weight));
          print(edgeNotN);
          currentEdges.add(edgeNotN);
          q.push(edgeNotN);
        }
      }
    }
    else if(djik.contains(e.w)){
      djik.add(e.v);
      other.remove(e.v);
      parent[e.v] = e.w;
      for(final edgeNotN in g.adj(e.v)){
        if(!currentEdges.contains(edgeNotN)){
          currentEdges.add(edgeNotN);
          print(edgeNotN);
          edgeNotN.distance = min(edgeNotN.distance,e.distance + edgeNotN.weight);
          print(edgeNotN);

          q.push(edgeNotN);
        }
      }
    }
    //print("$e");
  }
  Map<int,int> ret = new Map<int,int>();
  for(final int i in parent.getKeys()){
    ret[i] = ancestor(vertex, i, parent); 
      print("($i : ${parent[i]})");
      
  }
  return ret;
}
double min(double num1, double num2){
  if(num1 == -1){
    return num2;
  }
  else if(num1 < num2){
    return num1;
  }
  else{
    return num2;
  }
}
int ancestor(int sourceNode, int curr, Map<int, int>mapping){
  int parent = mapping[curr];
   while(parent != sourceNode){
     curr = parent;
     parent = mapping[parent];
   }
   return curr;
}
drawEdge(int x1, int y1, int x2, int y2, double weight, String color, [String color2]){
  if(color2 == null){
    color2 = "rgb(80,80,80)";
  }
  ctx.beginPath();
  ctx.setLineWidth(2);
  //ctx.setFillColor("rgb(230,230,230)");
  ctx.setStrokeColor(color);
  ctx.moveTo(x1,y1);
  ctx.lineTo(x2,y2);
  ctx.fill();
  ctx.closePath();
  ctx.stroke();    
  ctx.setStrokeColor(color2);
  ctx.font = '15px sans-serif';
  ctx.strokeText("$weight", (x1+x2)/2, (y1+y2)/2);
}
drawVertex(int x, int y,int num, String color,[String color2]){
  if(color2 == null){
    color2 ="rgb(230,230,230)";
  }
  ctx.beginPath();
  ctx.setLineWidth(2);
  ctx.setFillColor("rgb(255,255,255)");
  ctx.setStrokeColor("rgb(255,255,255)");
  ctx.arc(x, y, 20.0, 0,2*3.141592654 , false);
  ctx.fill();
  ctx.closePath();
  ctx.stroke(); 
  ctx.beginPath();
  ctx.setLineWidth(1);
  ctx.setFillColor(color2);
  ctx.setStrokeColor(color);
  ctx.arc(x, y, 20.0, 0,2*3.141592654 , false);
  ctx.fill();
  ctx.closePath();
  ctx.stroke();   
  
     
  ctx.setStrokeColor("rgb(80,80,80)");
  ctx.font = '15px sans-serif';
  ctx.strokeText("$num", x-5, y+3);

}
drawGraph(Graph g){
  ctx.clearRect(0,0,can.width, can.height);
  for(final int i in g.adjacent.getKeys()){
    for(final Edge e in g.adjacent[i]){
     drawEdge(verts[e.v][0], verts[e.v][1], verts[e.w][0], verts[e.w][1], e.weight, "rgb(180,180,180)");
    }
   
  }
  for(final int i in g.adjacent.getKeys()){
    drawVertex(verts[i][0], verts[i][1], i, "rgb(180,180,180)");
  }
}
generateVertices(Graph g){
  int incr = ((can.width-40)/(g.adjacent.getKeys().length+1)).toInt();
  int count = 0 + incr;
  for(final int i in g.adjacent.getKeys()){
    int x = (count).toInt()+20;
    int y = (Math.random()*460).toInt()+20;
    verts[i] = [x,y];
    count += incr;
  }
}
gVertices(int vertex, List<int> xSector, List<int> ySector, Set<int> usedVerts, Graph g){
  if(usedVerts.contains(vertex)){
    return;
  }
  usedVerts.add(vertex);
  
  for(final Edge e in g.adj(vertex)){
   
  }
  
}
int intersection(x,y){
  for(final i in verts.getKeys()){
    if(((x-verts[i][0])*(x-verts[i][0]) + (y-verts[i][1])*(y-verts[i][1])-20*20) <= 0){
      return i;
    }  
  }
  return -1;
}