/**
 * @author Duc Vu
 * Class Vertex stores the node's names, address and coordinates
 */

import java.awt.Graphics;
import java.awt.Color;

public class Vertex {
	private String name, address;
	public int x, y;
	public int width = 10, height = 10;
	private final int SIZE = 25;

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
		String result = String.format("%s", name);
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
        if (!(o instanceof Vertex)) {
        	return false;
        }
        Vertex c = (Vertex) o;
        return this.name.equals(c.getName());
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
