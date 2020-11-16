package model.shapes;

import model.PointCoordinate;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public class Ellipse extends Shape {

	public Ellipse(PointCoordinate coor, int width, int height, ShapeColor priColor, ShapeColor secColor, ShapeShadingType shading) {
		super(coor, width, height, priColor, secColor, shading);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ShapeType getType() {
		return ShapeType.ELLIPSE;
	}
}
