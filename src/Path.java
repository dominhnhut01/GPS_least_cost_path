import java.util.*;

/**
 * @author Duc Vu
 * Class Path stores the path's source, destination, costs
 */
public class Path implements Comparable<Path> {
	private LinkedList<Vertex> path;
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
	public Path(Vertex start,
				Vertex end,
				int totalDistCost,
				int totalTimeCost,
				LinkedList<Vertex> path,
				boolean useDistCost) {

		this.start = start;
		this.end = end;
		setPath(path);

		this.totalDistCost = totalDistCost;
		this.totalTimeCost = totalTimeCost;
		this.useDistCost = useDistCost;

		this.setCost();
	}

	/**
	 * Constructor
	 * @param useDistCost
	 */
	public Path(boolean useDistCost) {
		this.start = this.end = null;
		this.path = new LinkedList<Vertex>();
		this.totalDistCost = this.totalTimeCost = 0;
		this.useDistCost = useDistCost;
		this.setCost();
	}

	/**
	 * Constructor
	 * @param useDistCost
	 */
	public Path(Vertex start, boolean useDistCost) {
		this.start = start;
		this.end = start;
		this.path.add(start);

		this.totalDistCost = this.totalTimeCost = 0;
		this.useDistCost = useDistCost;
		this.setCost();
	}

	public Path(Path p) {
		this.start = p.getStart();
		this.end = p.getEnd();
		setPath(p.getPath());
		this.totalDistCost = p.getTotalDistCost();
		this.totalTimeCost = p.getTotalTimeCost();
		this.useDistCost = p.isUseDistCost();
		this.setCost();
    }

	/**
	 * Add an Edge to current Path
	 * @param Edge e
	 * Add a new edge to current path. The current path's last vertex
	 * must equal to the staring vertex of added Egde
	 */
	public void addEdge(Edge e) {
		if (start == null) {
			// Update initial start & end nodes
			start = e.getStart();
			path.add(start);
			end = e.getEnd();
			path.add(end);
		}

		if (end.equals(e.getStart())) {
			end = e.getEnd();
			path.add(end);

			totalDistCost += e.getDistCost();
			totalTimeCost += e.getTimeCost();
			this.setCost();
		} else {
			System.out.println("End vertex != new path's starting Vertex");
			return;
		}
	}

	@Override
	/**
	 * Compare based on totalDistCost or totalTimeCost
	 * return -1 if fewer, 0 if equal and 1 if larger.
	 */
	public int compareTo(Path o) {
		if (this.cost < o.getCost()) {
			return -1;
		} else if (this.cost == o.getCost()) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		String result = String.format("Source: %s  Destination: %s  Distance Cost: %d  Time Cost: %d",
				start.getName(), end.getName(), totalDistCost, totalDistCost);
		return result;
	}


	public LinkedList<Vertex> getPath() {
		return path;
	}

	public void setPath(LinkedList<Vertex> path) {
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
