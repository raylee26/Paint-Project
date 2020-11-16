package model.shapes;

import model.PointCoordinate;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public abstract class Shape {
	protected PointCoordinate coor;
	protected int width;
	protected int height;
	protected ShapeColor priColor;
	protected ShapeColor secColor;
	protected ShapeShadingType shading;
	
	public Shape(PointCoordinate coor, int width, int height, ShapeColor priColor, ShapeColor secColor, ShapeShadingType shading) {
		this.setCoor(coor);
		this.setWidth(width);
		this.setHeight(height);
		this.setPriColor(priColor);
		this.setSecColor(secColor);
		this.setShading(shading);
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
	
	public ShapeType getType() {
		
		return ShapeType.RECTANGLE;
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
	
	public ShapeColor getPriColor() {
		return priColor;
	}

	public void setPriColor(ShapeColor priColor) {
		this.priColor = priColor;
	}

	public ShapeColor getSecColor() {
		return secColor;
	}

	public void setSecColor(ShapeColor secColor) {
		this.secColor = secColor;
	}

	public ShapeShadingType getShading() {
		return shading;
	}

	public void setShading(ShapeShadingType shading) {
		this.shading = shading;
	}

	
}

