import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Graph {
	private boolean useDistCost;
	private boolean returnAddress;
	private HashMap <Vertex, Set<Edge>> graphData = new HashMap<Vertex, Set<Edges>>();
	private HashMap<String, Vertex> vertices = new HashMap<String, Vertex>(); 
	
	public Graph() {
		useDistCost = false;
		returnAddress = false;
		graphData = null;
		vertices = null;
	}
	
	public Graph(String fileName) {
		this();
		Scanner in = null;
		try {
			in = new Scanner(new File(fileName));
			while (in.hasNextLine() && !in.nextLine().trim().equals("<Nodes>")) {
				in.nextLine(); 
			}
			in.nextLine(); 
			in.nextLine();
			while (in.hasNextLine()) {
				String[] inputVertices = in.nextLine().trim().split("\t");
				if (inputVertices[0].equals("</Nodes>")) {
					break;
				} else {
					String key = inputVertices[0];
					String address = inputVertices[1];
					int x = Integer.parseInt(inputVertices[2]);
					int y = Integer.parseInt(inputVertices[3]);
					Vertex vertexToAdd = new Vertex(key, address, x, y);
					vertices.put(key, vertexToAdd);
				}
			}
			
			Set<Edge> edges = new TreeSet<Edge>();
			
			while (in.hasNextLine() && !in.nextLine().trim().equals("<Edges>")) {
				in.nextLine(); 
			}
			
			in.nextLine(); 
			in.nextLine();
			
			String keyCheck = "";
			String[] firstKey = in.nextLine().trim().split("\t");
	        if (firstKey[0].equals("</Edges>")) return;
	        
	        Vertex start = vertices.get(firstKey[0]);
	        keyCheck = start.getName();
            Vertex end = vertices.get(firstKey[1]);
            int timeCost = Integer.parseInt(firstKey[2]);
            int distCost = Integer.parseInt(firstKey[3]);
            Edge edgeToAdd = new Edge(start, end, timeCost, distCost);
            edges.add(edgeToAdd);
            
			while (in.hasNextLine()) {
	            String inputEdges[] = in.nextLine().trim().split("\t");
	            if (inputEdges[0].equals("</Edges>")) {
	            	graphData.put(vertices.get(keyCheck), edges);
	            	break;
	            }
	            
	            Vertex start = vertices.get(inputEdges[0]);
	            Vertex end = vertices.get(inputEdges[1]);
	            int timeCost = Integer.parseInt(inputEdges[2]);
	            int distCost = Integer.parseInt(inputEdges[3]);
	            
	            if (!start.getName().equals(keyCheck)) {
				    graphData.put(vertices.get(keyCheck), edges);
	                edges = new TreeSet<Edge>();
	                keyCheck = start.getName();
	            }

	            edgeToAdd = new Edge(start, end, timeCost, distCost);
	            edges.add(edgeToAdd);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
    
    public HashMap<String, Vertex> getVertices (){
    	return vertices;
    }
    
    public HashMap <Vertex, Set<Edge>> getGraphData (){
    	return graphData;
    }
}
