import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Dijkstra {
  //Properties
  private PriorityQueue<Path> pathQueue;
  private int totalCost;
  private Map<String, Edge> edgeList;
  private Map<String, Vertex> vertexList;
  Vertex start, goal, end;

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

  public void addEdge(Edge edge) {
    String edgeStr = edge.getStart().toString() + edge.getEnd().toString();
    edgeList.put(edgeStr, edge);
  }

  public void addVertex(Vertex vertex) {
    vertexList.put(vertex.toString(), vertex);
  }

  public void setStart(Vertex start) {
    this.start = start;
  }

  public void setEnd(Vertex end) {
    this.end = end;
  }

  public Path shortestPath(boolean useDistCost) {
    Vertex curStartPoint = start;
    pathQueue.add(new Path(start, useDistCost));
    Path curPath;
    Path clonePath;

    ArrayList<Edge> tempEdges;
    while (pathQueue.peek() != null && pathQueue.peek().getEnd().equals(goal)) {
      curPath = pathQueue.poll();  // Path class
      tempEdges = findEdge(curPath.getEnd());

      for (Edge e : tempEdges) {
        clonePath = new Path(curPath);
        clonePath.addEdge(e);
        pathQueue.add(clonePath);

      }
    }

    return pathQueue.peek();
  }

  /**
   * Return edge with the vertex argument as the start start
   * @param startPoint
   * @return
   */
  public ArrayList<Edge> findEdge(Vertex startPoint) {
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
