import java.util.Map;
import java.util.HashMap;

public class Dijkstra {
  //Properties
  private PriorityQueue<Path> pathQueue;
  private int totalCost;
  private Map<String, Edge> edgeList;
  private Map<String, Vertex> vertexList;
  Vertex start, goal;

  //Method
  public Dijkstra(Vertex start, Vertex goal) {
    /*
    Constructor: Construct a Dijkstra object with
    the start and goal vertex to find the shortest
    path between them
    @params: Vertex start: the Vertex object of the
                start point
             Vertex end: the Vertex object of the
                end point
    */
    this.start = start;
    this.goal = goal;
    this.totalCost = 0;
    this.edgeList = new HashMap<String, Edge>();
    this.pathQueue = new PathQueue<Path>();
  }

  public void addEdge(Path edge) {

    edgeStr = edge.getStart() + edge.getEnd();
    edgeList.put(edgeStr, edge);
  }

  public void addVertex(Vertex vertex) {
    edgeList.put(vertex.toString(), vertex);
  }

  public void setStart(Vertex start) {
    this.start = start
  }

  public void setEnd(Vertex end) {
    this.end = end;
  }

  public Path shortestPath() {
    Vertex curStartPoint = start;
    pathQueue.add(new Path(start, start));
    Path curPath;
    Path clonePath;
    ArrayList tempEdges;
    while (pathQueue.peek() != null && pathQueue.peek().getEndPoint().compareTo(goal) == 0) {
      curPath = pathQueue.poll();
      tempEdges = findEdge(curPath.getEndPoint());
      for (Edge e : tempEdges) {
        clonePath = new Path(curPath);
        clonePath.addEdge(e);
        pathQueue.add(clonePath);
      }
    }

    return pathQueue.peek();
  }

  public ArrayList<Edge> findEdge(Vertex startPoint) {
    //Return edge with the vertex argument as the start start
    String curEdgeStr;
    Edge curEdge;
    ArrayList<Edge> validEdges = new ArrayList<Edge>();
    for (String v : vertexList.keySet()) {
      curEdgeStr = startPoint.toString() + v;
      curEdge = edgeList.get(curEdgeStr);
      if (curEdge != null) {
        validEdges.add(curEdge);
      }
    }

    return validEdges;
  }
}
