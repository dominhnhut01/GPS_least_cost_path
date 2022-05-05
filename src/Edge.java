/**
 * @author Duc Vu
 * Class Edge stores the graph's edges
 */
 import java.awt.Graphics;
 import java.awt.Color;

public class Edge {
	private Vertex start, end;
	private int timeCost, distCost;

	/**
	 * Workhorse Constructor
	 * @param Vertex start
	 * @param Vertex end
	 * @param int timeCost
	 * @param int distCost
	 */
  public Edge(Vertex start, Vertex end) {
 		this.start = start;
 		this.end = end;
    if (start.equals(end)) {
      this.timeCost = 0;
      this.distCost = 0;
    }
  }
	public Edge(Vertex start, Vertex end, int timeCost, int distCost) {
		this.start = start;
		this.end = end;
		this.timeCost = timeCost;
		this.distCost = distCost;
	}

	@Override
	public String toString() {
		String result = String.format("%s\t%s\t%d\t%d", start.getName(), end.getName(), timeCost, distCost);
		return result;
	}

	/**
	 * equals() method
	 */
	@Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Edge)) {
        	return false;
        }
        Edge c = (Edge) o;
        return this.start.equals(c.getStart()) &&
			this.end.equals(c.getEnd()) &&
			timeCost == c.getTimeCost() &&
			distCost == c.getDistCost();
    }

	//===========================================================================================
	// Setters and Getters
	// ==========================================================================================
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
