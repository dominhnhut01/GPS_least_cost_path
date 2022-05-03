/**
 * @author Duc Vu
 * Class Edge stores the graph's edges
 */
public class Edge {
	private Vertex src, dest;
	private int timeCost, distCost;
	
	/**
	 * Workhorse Constructor
	 * @param Vertex src
	 * @param Vertex dest
	 * @param int timeCost
	 * @param int distCost
	 */
	public Edge(Vertex src, Vertex dest, int timeCost, int distCost) {
		this.src = src;
		this.dest = dest;
		this.timeCost = timeCost;
		this.distCost = distCost;
	}
	
	@Override
	public String toString() {
		String result = String.format("%s\t%s\t%d\t%d", src.getName(), dest.getName(), timeCost, distCost);
		return result;
	}
	
	//===========================================================================================
	// Setters and Getters
	// ==========================================================================================
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
	public int getTimeCost() {
		return timeCost;
	}
	public void setTimeCost(int timeCost) {
		this.timeCost = timeCost;
	}
	public int getDistCost() {
		return distCost;
	}
	public void setDistCost(int distCost) {
		this.distCost = distCost;
	}
	
	
}
