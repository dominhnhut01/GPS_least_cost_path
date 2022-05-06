/**
* @author: Nhut Do, Duc Vu, Tom Ho
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Graphics;

public class Graph {
	private boolean useDistCost;
	private boolean returnAddress;
	private HashMap <Vertex, ArrayList<Edge>> graphData;
	private HashMap <String, Vertex> vertices;
	private Dijkstra Dijkstra_search;

	/**
	 * Default Constructor
	 */
	public Graph() {
		useDistCost = false;
		returnAddress = false;
		graphData = new HashMap<Vertex, ArrayList<Edge>>();
		vertices = new HashMap<String, Vertex>();
	}

	/**
	 * Workhorse constructor
	 * @param fileName
	 * @param useDistCost
	 */
	public Graph(String fileName, boolean useDistCost) {
		this();
		this.useDistCost = useDistCost;
		Scanner in = null;
		Vertex vertexToAdd, start, end;
		Edge edgeToAdd;
		int distCost, timeCost;

		try {
			in = new Scanner(new File(fileName));
			String line;
			while (in.hasNextLine() && !in.nextLine().trim().equals("<Nodes>")) {
				in.nextLine();
			}
			line = in.nextLine();

			String key, address;
			int x, y;
			while (in.hasNextLine()) {
				line = in.nextLine();
				String[] inputVertices = line.trim().split("\t");
				if (inputVertices[0].equals("</Nodes>")) {
					break;
				} else {
					key = inputVertices[0];
					address = inputVertices[1];
					x = Integer.parseInt(inputVertices[2]);
					y = Integer.parseInt(inputVertices[3]);
					vertexToAdd = new Vertex(key, address, x, y);
					vertices.put(key, vertexToAdd);
				}
			}

			ArrayList<Edge> edges = new ArrayList<Edge>();

			// Skips lines until Edges are reached
      while (!line.equals("<Edges>")) { line = in.nextLine(); }

      line = in.nextLine();
      String[] s = line.split("\t");
      Edge tempEdge;
      ArrayList<Edge> tempList;
      while (!line.equals("</Edges>")) {
    	  //Read each line and store them into Edge object, then store inside hashmap
	      line = in.nextLine();
	      s = line.split("\t");
	      if (s[0].equals("</Edges>")) {
		      break;
		  }

	      start = vertices.get(s[0]);
	      end = vertices.get(s[1]);
	      timeCost = Integer.parseInt(s[2]);
	      distCost = Integer.parseInt(s[3]);
	      tempEdge = new Edge(start, end, timeCost, distCost);

	      //Add new edges to the hash map graphData when it's empty and non empty
	      if (graphData.get(start) == null) {
	    	  tempList= new ArrayList<Edge>();
	    	  tempList.add(tempEdge);
	    	  graphData.put(start, tempList);
	      }	else {
	    	  tempList = graphData.get(start);
	    	  tempList.add(tempEdge);
	    	  graphData.put(start, tempList);
	      }
      }

	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	  }
	}

	/**
	 * This method find the shortest path using Dijkstra
	 * @param start
	 * @param goal
	 * @return
	 */
	public Path findShortestPath(String start, String goal) {
		Vertex startVertex = vertices.get(start);
		Vertex goalVertex = vertices.get(goal);

		Dijkstra Dijkstra_search = new Dijkstra(startVertex, goalVertex);
		Dijkstra_search.addEdgeList(graphData);
		Path shortest = Dijkstra_search.shortestPath(this.useDistCost);

		return shortest;
	}

//	public Path testShortestPath() {
//		graphData.clear();
//		Vertex nodeA = vertices.get("A");
//		Vertex nodeB = vertices.get("B");
//		Vertex nodeD = vertices.get("D");
//		Vertex nodeC = vertices.get("C");
//		Vertex nodeE = vertices.get("E");
//		Vertex nodeK = vertices.get("K");
//
//		Edge edgeAB = new Edge(nodeA, nodeB);
//		Edge edgeDA = new Edge(nodeD, nodeA);
//		Edge edgeBC = new Edge(nodeB, nodeC);
//		Edge edgeCK = new Edge(nodeC, nodeK);
//		Edge edgeDK = new Edge(nodeD, nodeK);
//		Edge edgeDE = new Edge(nodeD, nodeE);
//
//		graphData
//	}

	public Map<String, Vertex> getVertices() {
		return vertices;
	}

	public Map<Vertex, ArrayList<Edge>> getGraphData() {
		return graphData;
	}


	/**
	 * Getter for useDistCost
	 * @return boolean of useDistCost
	 */
	public boolean getUseDistCost() {
  	return useDistCost;
  }

	/**
	 * Getter for returnAddress
	 * @return boolean of returnAddress
	 */
 	 public boolean getReturnAddress() {
    	return returnAddress;
 	 }


	/**
	 * Setter for useDistCost
	 * @param useDistCost boolean indicator
	 */
  	public void setUseDistCost(boolean useDistCost) {
    	this.useDistCost = useDistCost;
  	}

	/**
	* Setter for returnAddress
	* @param returnAddress boolean indicator
	*/
  	public void setReturnAddress(boolean returnAddress) {
    	this.returnAddress = returnAddress;
  	}

  	/**
  	 * Call the draw method in edge class
  	 * @param g
  	 */
	public void draw(Graphics g) {
		for (Map.Entry<Vertex, ArrayList<Edge>> set :graphData.entrySet()) {
			ArrayList<Edge> edges = set.getValue();
			for(Edge e : edges)
				e.draw(g);
        }
	}
	/**
	 * Call draw method in edge class
	 * @param g
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean drawPath(Graphics g, Vertex start, Vertex end) {
		for (Map.Entry<Vertex, ArrayList<Edge>> set :graphData.entrySet()) {
			ArrayList<Edge> edges = set.getValue();
			for(Edge e : edges)
				e.draw(g);
        }

		Path resultEdges = this.findShortestPath(start.getName(), end.getName());
		for(Edge e : resultEdges.getPath())
			e.drawPath(g);
		return false; // Revise later
	}
}