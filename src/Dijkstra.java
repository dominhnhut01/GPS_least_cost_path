/**
 * @author Nhut Do, Duc Vu, Tom Ho
 * Class Dijkstra finds the shortest path
 */
import java.util.HashMap;
import java.util.ArrayList;

public class Dijkstra {
  //Properties
  private SortedLinkedListPriorityQueue<Path> pathQueue;
  private int totalCost;
  
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
    this.pathQueue = new SortedLinkedListPriorityQueue<Path>();
  }

  /**
   * This method initialize the vertext list
   * @param vertexList
   */

  /**
   * This method initialize the edge list
   * @param edgeList
   */
  public void addEdgeList(HashMap <Vertex, ArrayList<Edge>> edgeList) {
    this.edgeList = edgeList;
  }

  /**
   * Setter for start vertex
   * @param start
   */
  public void setStart(Vertex start) {
    this.start = start;
  }

  /**
   * Setter for end vertex
   * @param goal
   */
  public void setGoal(Vertex goal) {
    this.goal = goal;
  }

  /**
   * This method return the shortest path based on distant cost
   * @param useDistCost
   * @return Path of shortest path
   */
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

}