package model;

public abstract class Shape {
	protected PointCoordinate coor;
	protected int width;
	protected int height;
	
	public Shape(PointCoordinate coor, int width, int height) {
		this.setCoor(coor);
		this.setWidth(width);
		this.setHeight(height);
	}

	public PointCoordinate getCoor() {
		return coor;
	}

	public void setCoor(PointCoordinate coor) {
		this.coor = coor;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	public void moveShape(int x, int y) {
		// Add or subtract pixels moved
		int oldX = this.coor.getxCoor();
		int oldY = this.coor.getyCoor();
		
		int newX = oldX + x;
		int newY = oldY + y;
		
		this.coor.setxCoor(newX);
		this.coor.setyCoor(newY);
	}
	
	
}

