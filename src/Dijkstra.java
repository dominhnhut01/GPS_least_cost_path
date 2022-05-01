import java.util.Map;
import java.util.HaspMap;

public class Dijkstra {
  //Properties
  private PriorityQueue<Path> pathQueue;
  private int totalCost;
  private Map<String, Edge> edgeList;
  Vertex start, goal;

  //Method
  public Dijkstra(Vertex start, Vertex goal) {
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

  public void setStart(Vertex start) {
    this.start = start
  }

  public void setEnd(Vertex end) {
    this.end = end;
  }

  public Path shortestPath() {
    
  }
}
