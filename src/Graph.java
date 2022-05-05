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

	public Graph() {
		useDistCost = false;
		returnAddress = false;
		graphData = new HashMap<Vertex, ArrayList<Edge>>();
		vertices = new HashMap<String, Vertex>();
	}

	public Graph(String fileName) {
		this();
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
      in.nextLine();

			String keyCheck = "";
			line = in.nextLine();
			String[] firstKey = line.trim().split("\t");

      start = vertices.get(firstKey[0]);

      keyCheck = start.getName();

      end = vertices.get(firstKey[1]);
      timeCost = Integer.parseInt(firstKey[2]);
      distCost = Integer.parseInt(firstKey[3]);
      edgeToAdd = new Edge(start, end, timeCost, distCost);
      edges.add(edgeToAdd);

			while (in.hasNextLine()) {
				line = in.nextLine();
	      String inputEdges[] = line.trim().split("\t");
	      if (inputEdges[0].equals("</Edges>")) {
	      	graphData.put(vertices.get(keyCheck), edges);
	      	break;
	      }

	      start = vertices.get(inputEdges[0]);
	      end = vertices.get(inputEdges[1]);
	      timeCost = Integer.parseInt(inputEdges[2]);
	      distCost = Integer.parseInt(inputEdges[3]);

	      edgeToAdd = new Edge(start, end, timeCost, distCost);
	      edges.add(edgeToAdd);


			}

			for (String s : vertices.keySet()) {
				System.out.println(vertices.get(s));
			}
			for (Vertex v : graphData.keySet()) {
				System.out.println(graphData.get(v));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Path findShortestPath(String start, String end) {
		Vertex startVertex = vertices.get(start);
		Vertex endVertex = vertices.get(end);

		return null;
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
