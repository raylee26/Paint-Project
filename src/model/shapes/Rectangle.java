/**
 * 
 */
package model.shapes;

import model.PointCoordinate;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

/**
 * @author Ray
 *
 */
public class Rectangle extends Shape {

	public Rectangle(PointCoordinate coor, int width, int height, ShapeColor priColor, ShapeColor secColor, ShapeShadingType shading) {
		super(coor, width, height, priColor, secColor, shading);
		
		
	}
	
	public Rectangle(Rectangle oldRectangle) {
		super(oldRectangle);
	}
	
	@Override
	public ShapeType getType() {
		return ShapeType.RECTANGLE;
	}

	@Override
	public Shape copy() {
		return new Rectangle(this);
	}
	
}
