/**
 * @author Duc Vu, Tom Ho, Steve(Nhut) Do
 * Class Dijkstra finds the shortest path
 */
import java.util.HashMap;
import java.util.ArrayList;

public class Dijkstra {
  //Properties
  private SortedLinkedListPriorityQueue<Path> pathQueue;
  private int totalCost;
  private HashMap<String, Vertex> vertexList;
  private HashMap<Vertex, ArrayList<Edge>> edgeList;
  Vertex start, goal;

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

  public void setGoal(Vertex goal) {
    this.goal = goal;
  }

  public Path shortestPath(boolean useDistCost) {
    Vertex curStartPoint = start;

    //Create a first edge with the same start and end point
    // to initialize the Dijkstra
    Edge firstEdge = new Edge(curStartPoint, curStartPoint);
    Path firstPath = new Path(useDistCost);
    firstPath.addEdge(firstEdge);
    if (start.equals(goal)) {
      // In case start vertex is the same as goal vertex
      return firstPath;
    }
    pathQueue.add(firstPath);
    Path curPath;
    Path clonePath;

    ArrayList<Edge> tempEdges;
    while (pathQueue.peek() != null && !pathQueue.peek().getEnd().equals(goal)) {

      curPath = pathQueue.peek();  // Path class
      pathQueue.remove();

      tempEdges = edgeList.get(curPath.getEnd());

      if (tempEdges == null) {
    	  continue;
      }
      for (Edge e : tempEdges) {

        clonePath = new Path(curPath);
        if (clonePath.addEdge(e))
        	pathQueue.add(clonePath);
      }
    }

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
