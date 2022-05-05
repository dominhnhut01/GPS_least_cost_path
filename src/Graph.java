import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;

public class Graph {
	private boolean useDistCost;
	private boolean returnAddress;
	private HashMap <Vertex, ArrayList<Edge>> graphData;
	private HashMap <String, Vertex> vertices;
	private Dijkstra Dijkstra_search;

	public Graph() {
		useDistCost = false;
		returnAddress = false;
		graphData = new HashMap<Vertex, ArrayList<Edge>>();
		vertices = new HashMap<String, Vertex>();
	}

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
			System.out.println("--------------------------");

			// Skips lines until Edges are reached
      while (!line.equals("<Edges>")) { line = in.nextLine(); }

      line = in.nextLine();
      String[] s = line.split("\t");
      Edge tempEdge;
      ArrayList<Edge> tempList;
      while (!line.equals("</Edges>")) {
	      line = in.nextLine();
	      System.out.println(line);
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

	public Path findShortestPath(String start, String goal) {
		Vertex startVertex = vertices.get(start);
		Vertex goalVertex = vertices.get(goal);

		Dijkstra Dijkstra_search = new Dijkstra(startVertex, goalVertex);
		Dijkstra_search.addEdgeList(graphData);
		Dijkstra_search.addVertexList(vertices);
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
	}
