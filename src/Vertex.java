/**
 * @author Duc Vu
 * Class Vertex stores the node's names, address and coordinates
 */
public class Vertex {
	private String name, address;
	private int x, y;
	
	/**
	 * Constructor
	 * @param name
	 * @param address
	 * @param x
	 * @param y
	 */
	public Vertex(String name, String address, int x, int y) {
		this.name = name;
		this.address = address;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Default Constructor
	 */
	public Vertex() {
		this.name = "";
		this.address = "";
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * toString() method
	 */
	@Override
	public String toString() {
		String result = String.format("%s\t%s\t%d\t%d", name, address, x, y);
		return result;
	}
	
	//===========================================================================================
	// Setters and Getters
	// ==========================================================================================
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
