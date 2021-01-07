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
	
	public Shape(Shape oldShape) {
		this.setCoor(oldShape.getCoor());
		this.setWidth(oldShape.getWidth());
		this.setHeight(oldShape.getHeight());
		this.setPriColor(oldShape.getPriColor());
		this.setSecColor(oldShape.getSecColor());
		this.setShading(oldShape.getShading());
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
	
	/**checks if given shape is inside this shape
	 * @param shape
	 * @return boolean
	 */
	public boolean contains(Shape shape) {
		
		//Grab shape data
		int a = this.coor.getxCoor();
		int b = this.coor.getyCoor();
		int x = shape.getCoor().getxCoor();
		int y = shape.getCoor().getyCoor();
		int width = shape.getWidth();
		int height = shape.getHeight();
		//Compare first point
		if(!(a < x && a + this.width > x))
			return false;
		if(!(b < y && b + this.height > y))
			return false;
		//Compare second point
		x = x + width;
		if(!(a < x && a + this.width > x))
			return false;
		if(!(b < y && b + this.height > y))
			return false;
		//Compare third point
		y = y + height;
		if(!(a < x && a + this.width > x))
			return false;
		if(!(b < y && b + this.height > y))
			return false;

		return true;
	}

	/**takes old shape and returns a new copy of the old shape
	 * @return
	 */
	public abstract Shape copy();
	
}

