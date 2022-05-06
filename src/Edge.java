/**
 * @author Duc Vu, Tom Ho, Steve(Nhut) Do
 * Class Edge stores the graph's edges (Start, Destination, Costs)
 */
import java.awt.Graphics;
import java.awt.Color;

public class Edge {
   private Vertex start, end;
   private int timeCost, distCost;

   /**
    * Default Constructor
    * @param start
    * @param end
    */
   public Edge(Vertex start, Vertex end) {
		this.start = start;
		this.end = end;
	   if (start.equals(end)) {
		 this.timeCost = 0;
		 this.distCost = 0;
	   }
   }
   
   /**
	* Workhorse Constructor
	* @param Vertex start
	* @param Vertex end
	* @param int timeCost
	* @param int distCost
	*/
   public Edge(Vertex start, Vertex end, int timeCost, int distCost) {
	   this.start = start;
	   this.end = end;
	   this.timeCost = timeCost;
	   this.distCost = distCost;
   }
   
   /**
    * Copy Constructor
    * @param e
    */
   public Edge(Edge e) {
	   this(e.getStart(), e.getEnd(), e.getTimeCost(), e.getDistCost());
   }

   @Override
   /**
    * toString method
    */
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

   /**
    * Draw initial edge of graph
    * @param g
    */
   public void draw(Graphics g) {
	   g.setColor(Color.BLACK);
	   int coef = start.width / 2;
	   g.drawLine(start.x + coef, start.y + coef, end.x + coef, end.y + coef);
	   start.draw(g);
	   end.draw(g);
   }

   /**
    * Draw the viable path
    * @param g
    */
   public void drawPath(Graphics g) {
	   g.setColor(Color.green);
	   int coef = start.width / 2;
	   g.drawLine(start.x + coef, start.y + coef, end.x + coef, end.y + coef);
	   start.draw(g);
	   end.draw(g);
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