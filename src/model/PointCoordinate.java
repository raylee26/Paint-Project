package model;

public class PointCoordinate {
	private int xCoor;
	private int yCoor;
	
	// Constructor
	public PointCoordinate(int x, int y) {
		xCoor = x;
		yCoor = y;
		
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
	
	@Override
	public String toString() {
		String coor = "(" + xCoor + "," + yCoor + ")";
		return coor;
		
	}
	
	public PointCoordinate copy() {
		return new PointCoordinate(this.xCoor, this.yCoor); 
	}
}

