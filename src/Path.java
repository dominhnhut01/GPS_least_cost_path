import java.util.*;

/**
 * @author Duc Vu
 * Class Path stores the path's source, destination, costs
 */
public class Path implements Comparable<Path> {
	private LinkedList<Edge> path;
	private ArrayList<Vertex> verticesVisited;
	private int totalDistCost, totalTimeCost, cost;
	private Vertex start, end;
	private boolean useDistCost;

	/**
	 * Workhorse Constructor
	 * @param Vertex start
	 * @param Vertex end
	 * @param int totalDistCost
	 * @param int totalTimeCost
	 * @param LinkedList<Vertex> path
	 * @param boolean useDistCost
	 */
	// public Path(Vertex start,
	// 			Vertex end,
	// 			int totalDistCost,
	// 			int totalTimeCost,
	// 			LinkedList<Edge> path,
	// 			boolean useDistCost) {
	//
	// 	this.start = start;
	// 	this.end = end;
	// 	setPath(path);
	//
	// 	this.totalDistCost = totalDistCost;
	// 	this.totalTimeCost = totalTimeCost;
	// 	this.useDistCost = useDistCost;
	//
	// 	this.setCost();
	// }

	/**
	 * Constructor
	 * @param useDistCost
	 */
	public Path(boolean useDistCost) {
		this.start = this.end = null;
		this.path = new LinkedList<Edge>();
		this.verticesVisited = new ArrayList<Vertex>();
		this.totalDistCost = this.totalTimeCost = 0;
		this.useDistCost = useDistCost;
		this.setCost();
	}

	/**
	 * Constructor
	 * @param useDistCost
	 */
	// public Path(Vertex start, boolean useDistCost) {
	// 	this.start = start;
	// 	this.end = start;
	// 	this.path.add(start);
	//
	// 	this.totalDistCost = this.totalTimeCost = 0;
	// 	this.useDistCost = useDistCost;
	// 	this.setCost();
	// }

	/**
	 * Clone Constructor
	 * @param p
	 */
	public Path(Path p) {
		this.start = new Vertex(p.getStart());
		this.end = new Vertex(p.getEnd());
		this.path = new LinkedList<Edge>(p.getPath());
		this.verticesVisited = new ArrayList<Vertex>(p.getVerticesVisited());
		this.totalDistCost = p.getTotalDistCost();
		this.totalTimeCost = p.getTotalTimeCost();
		this.useDistCost = p.isUseDistCost();
    }

	/**
	 * Add an Edge to current Path
	 * @param Edge e
	 * Add a new edge to current path. The current path's last vertex
	 * must equal to the staring vertex of added Egde
	 */
	public boolean addEdge(Edge e) {
		if (e.getStart().equals(e.getEnd())) {
			this.start = e.getStart();
			this.end = e.getEnd();
			verticesVisited.add(e.getEnd());
			return true;
		}
		if (start == null) {
			// Update initial start & end nodes
			path.add(e);
		}

		if (this.end.equals(e.getStart()) && !verticesVisited.contains(e.getEnd())) {
			this.end = e.getEnd();
			path.add(e);

			totalDistCost += e.getDistCost();
			totalTimeCost += e.getTimeCost();
			this.setCost();
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**
	 * Compare based on totalDistCost or totalTimeCost
	 * return -1 if fewer, 0 if equal and 1 if larger.
	 */
	public int compareTo(Path o) {
		return this.cost - o.getCost();
	}

	@Override
	public String toString() {
		String result = String.format("Source: %s  Destination: %s  Distance Cost: %d  Time Cost: %d\n",
				start.getName(), end.getName(), totalDistCost, totalTimeCost);
		System.out.println(path);
		for (Edge e : path) {
			result += e.getStart().getName();
			result += "-->";
		}
		
		result += this.end.getName();
		return result;
	}


	public LinkedList<Edge> getPath() {
		return path;
	}

	public void setPath(LinkedList<Edge> path) {
		this.path = path;
	}

	public int getTotalDistCost() {
		return totalDistCost;
	}

	public void setTotalDistCost(int totalDistCost) {
		this.totalDistCost = totalDistCost;
	}

	public int getTotalTimeCost() {
		return totalTimeCost;
	}

	public void setTotalTimeCost(int totalTimeCost) {
		this.totalTimeCost = totalTimeCost;
	}

	public Vertex getStart() {
		return start;
	}

	public void setStart(Vertex start) {
		this.start = start;
	}

	public Vertex getEnd() {
		return end;
	}

	public void setEnd(Vertex end) {
		this.end = end;
	}
	
	public ArrayList<Vertex> getVerticesVisited() {
		return verticesVisited;
	}

	public boolean isUseDistCost() {
		return useDistCost;
	}

	public void setUseDistCost(boolean useDistCost) {
		this.useDistCost = useDistCost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost() {
		if (this.useDistCost) {
			this.cost = this.totalDistCost;
		} else {
			this.cost = this.totalTimeCost;
		}
	}
}
