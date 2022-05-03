import java.util.*;

/**
 * @author Duc Vu
 * Class Path stores the path's source, destination, costs
 */
public class Path implements Comparable<Path> {
	private LinkedList<Vertex> path;
	private int totalDistCost, totalTimeCost, cost;
	private Vertex src, dest;
	private boolean useDistCost;
	
	/**
	 * Workhorse Constructor
	 * @param Vertex src
	 * @param Vertex dest
	 * @param int totalDistCost
	 * @param int totalTimeCost
	 * @param LinkedList<Vertex> path
	 * @param boolean useDistCost
	 */
	public Path(Vertex src,
				Vertex dest,
				int totalDistCost,
				int totalTimeCost,
				LinkedList<Vertex> path,
				boolean useDistCost) {

		this.src = src;
		this.dest = dest;
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
		this.src = this.dest = null;
		this.path = null;
		this.totalDistCost = this.totalTimeCost = 0;
		this.useDistCost = useDistCost;
		this.setCost();
	}

	/**
	 * Add an Edge to current Path
	 * @param Edge e
	 * Add a new edge to current path. The current path's last vertex 
	 * must equal to the staring vertex of added Egde
	 */
	public void addPath(Edge e) {
		if (src == null && dest == null) {
			src = e.getSrc();
			path.add(src);
			dest = e.getDest();
			path.add(dest);

			totalDistCost += e.getDistCost();
			totalTimeCost += e.getTimeCost();
			this.setCost();
			
		} else if (dest.getName().equals(e.getSrc().getName())) {
			dest = e.getDest();
			path.add(dest);
			
			totalDistCost += e.getDistCost();
			totalTimeCost += e.getTimeCost();
			
			this.setCost();
		} else {
			System.out.println("End vertex not match to new path's starting Vertex");
			this.setCost();
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
				src.getName(), dest.getName(), totalDistCost, totalDistCost);
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

	public Vertex getSrc() {
		return src;
	}

	public void setSrc(Vertex src) {
		this.src = src;
	}

	public Vertex getDest() {
		return dest;
	}

	public void setDest(Vertex dest) {
		this.dest = dest;
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
