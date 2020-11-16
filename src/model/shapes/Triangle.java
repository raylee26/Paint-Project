package model.shapes;

import model.PointCoordinate;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public class Triangle extends Shape{
	
	private int[] xArray = new int[3];
	private int[] yArray = new int[3];

	public Triangle(PointCoordinate coor, int width, int height, ShapeColor priColor, ShapeColor secColor, ShapeShadingType shading) {
		super(coor, width, height, priColor, secColor, shading);
		// TODO Auto-generated constructor stub
		
		xArray[0] = coor.getxCoor();
		yArray[0] = coor.getyCoor();
		
		xArray[1] = coor.getxCoor();
		yArray[1] = coor.getyCoor() + height;
		
		xArray[2] = coor.getxCoor() + width;
		yArray[2] = coor.getyCoor() + height;
	}

	@Override
	public ShapeType getType() {
		return ShapeType.TRIANGLE;
	}

	public int[] getxArray() {
		return xArray;
	}

	public int[] getyArray() {
		return yArray;
	}
	
	@Override
	public void moveShape(int x, int y) {
		super.moveShape(x, y);
		
		xArray[0] += x;
		yArray[0] += y;
		
		xArray[1] += x;
		yArray[1] += y;
		
		xArray[2] += x;
		yArray[2] += y;
	}
}
