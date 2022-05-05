import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Dijkstra {
  //Properties
  private SortedLinkedListPriorityQueue<Path> pathQueue;
  private int totalCost;
  private HashMap<String, Vertex> vertexList;
  private HashMap<Vertex, ArrayList<Edge>> edgeList;
  Vertex start, goal, end;

  //Method
  public Dijkstra(Vertex start, Vertex goal) {
    /*
    Constructor: Construct a Dijkstra object with
    the start and goal vertex to find the shortest
    path between them
    @params: Vertex start: the Vertex object of the
                start point
             Vertex goal: the Vertex object of the
                end point
    */
    this.start = start;
    this.goal = goal;
    this.totalCost = 0;
    this.edgeList = new HashMap<Vertex, ArrayList<Edge>>();
    this.vertexList = new HashMap<String, Vertex>();
    this.pathQueue = new SortedLinkedListPriorityQueue<Path>();
  }

  public void addVertexList(HashMap<String, Vertex> vertexList) {
    this.vertexList = vertexList;
  }

  public void addEdgeList(HashMap <Vertex, ArrayList<Edge>> edgeList) {
    this.edgeList = edgeList;
  }

  public void setStart(Vertex start) {
    this.start = start;
  }

  public void setEnd(Vertex end) {
    this.end = end;
  }

  public Path shortestPath(boolean useDistCost) {
    Vertex curStartPoint = start;

    //Create a first edge with the same start and end point
    // to initialize the Dijkstra
    Edge firstEdge = new Edge(curStartPoint, curStartPoint);
    Path firstPath = new Path(useDistCost);
    firstPath.addEdge(firstEdge);
    pathQueue.add(firstPath);
    Path curPath;
    Path clonePath;

    ArrayList<Edge> tempEdges;
    while (pathQueue.peek() != null && pathQueue.peek().getEnd().equals(goal)) {
      curPath = pathQueue.poll();  // Path class
      tempEdges = vertexList.get(curPath.getEnd());

      for (Edge e : tempEdges) {
        clonePath = new Path(curPath);
        clonePath.addEdge(e);
        pathQueue.add(clonePath);

      }
    }

    totalCost = pathQueue.peek().getCost();

    return pathQueue.peek();
  }

  /**
   * Return edge with the vertex argument as the start
   * @param startPoint
   * @return
   */
  // public ArrayList<Edge> findEdge(Vertex startPoint) {
  //   String curEdgeStr;
  //   Edge curEdge;
  //   ArrayList<Edge> validEdges = new ArrayList<Edge>();
  //   for (String v : vertexList.keySet()) {
  //     curEdgeStr = startPoint.toString() + v;
  //     curEdge = edgeList.get(curEdgeStr);
  //     if (curEdge != null) {
  //       validEdges.add(curEdge);
  //     }
  //   }
  //
  //   return validEdges;
  // }
}
