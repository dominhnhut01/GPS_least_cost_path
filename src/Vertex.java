/**
 * @author Duc Vu, Tom Ho, Steve(Nhut) Do
 * Class Vertex stores the node's names, address and coordinates
 */

import java.awt.Color;
import java.awt.Graphics;

public class Vertex {
	private String name, address;
	public int x, y;
	public int state=0;
	public int width = 30, height = 30;
	private final int SIZE = 28;

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

	public Vertex(Vertex v) {
		this(v.getName(), v.getAddress(), v.getX(), v.getY());
	}

	public void setSize(int size) {
		width = size;
		height = size;
	}

	public void setState(int s) {
		this.state = s;
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

	public void draw(Graphics g) {
		if(state == 0) {
				g.setColor(Color.WHITE);
				g.fillOval(x, y, width, height);
				g.setColor(Color.BLACK);
				g.drawOval(x, y, width, height);

		}	else if(state == 1) {
				g.setColor(Color.CYAN);
				g.fillOval(x, y, width, height);
				g.setColor(Color.BLACK);
				g.drawOval(x, y, width, height);
				g.setColor(Color.RED);
		}
		g.drawString(name, x+SIZE/2-3, y+SIZE/2+3);
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
