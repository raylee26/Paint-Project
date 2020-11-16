package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import model.PointCoordinate;
import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import model.shapes.Ellipse;
import model.shapes.Rectangle;
import model.shapes.Shape;
import model.shapes.Triangle;
import view.interfaces.PaintCanvasBase;

public class CommandManager {
	
	public static void makeShape(PointCoordinate firstPoint, PointCoordinate secondPoint,
			IApplicationState state, PaintCanvasBase canvas) {
		
		// Do shape math
		int width = Math.abs(secondPoint.getxCoor() - firstPoint.getxCoor());
		int height = Math.abs(secondPoint.getyCoor() - firstPoint.getyCoor());
		int smallX = Math.min(firstPoint.getxCoor(), secondPoint.getxCoor());
		int smallY = Math.min(firstPoint.getyCoor(), secondPoint.getyCoor());
		System.out.println("Width: " + width + " Height: " + height);
		
		//get color and shading type
		ShapeColor priColor = state.getActivePrimaryColor();
		ShapeColor secColor = state.getActiveSecondaryColor();
		ShapeShadingType shading = state.getActiveShapeShadingType();
		
		// Create Shape data
		Shape shape;
		
		switch(state.getActiveShapeType()) {
		case ELLIPSE:
			shape = new Ellipse(new PointCoordinate(smallX, smallY), width, height, priColor, secColor, shading);
			break;
		case RECTANGLE:
			shape = new Rectangle(new PointCoordinate(smallX, smallY), width, height, priColor, secColor, shading);
			break;
		case TRIANGLE:
			shape = new Triangle(new PointCoordinate(smallX, smallY), width, height, priColor, secColor, shading);
			break;
		default:
			shape = new Rectangle(new PointCoordinate(smallX, smallY), width, height, priColor, secColor, shading);
			break;
		}
		
		// Add Rectangle data to Application State
		state.getShapeList().add(shape);
		
		// Draws shape
		drawShape(state, canvas, shape);
        
        System.out.println("Number of Shapes: " + state.getShapeList().size());
	}
	
	public static void moveShape(PointCoordinate firstPoint, PointCoordinate secondPoint,
			IApplicationState state, PaintCanvasBase canvas) {
		// Determine Direction and Distance of movement
		int moveX = secondPoint.getxCoor() - firstPoint.getxCoor();
		int moveY = secondPoint.getyCoor() - firstPoint.getyCoor();
		// Apply movement to shape
		for(Shape s: state.getShapeList()) {
			s.moveShape(moveX, moveY);
		}
		// Draw New canvas
			//wipes current canvas
		canvas.paintImmediately(0, 0, canvas.getWidth(), canvas.getHeight());
			//redraw canvas
		for(Shape s: state.getShapeList()) {
			// Draw rectangle on screen
			drawShape(state, canvas, s);
		}
	}
	
	public static void selectShape() {
		
	}
	
	private static void drawShape(IApplicationState state, PaintCanvasBase canvas, Shape shape) {
		
		// Sets Primary Color
		Color priColor = translateColor(shape.getPriColor());
		Graphics2D graphics2d = canvas.getGraphics2D();
		graphics2d.setColor(priColor);
		
		if(shape.getShading() == ShapeShadingType.FILLED_IN || shape.getShading() == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
			switch(shape.getType()) {
			case ELLIPSE:
		        graphics2d.fillOval(shape.getCoor().getxCoor(), shape.getCoor().getyCoor(), shape.getWidth(), shape.getHeight());
				break;
			case RECTANGLE:
		        graphics2d.fillRect(shape.getCoor().getxCoor(), shape.getCoor().getyCoor(), shape.getWidth(), shape.getHeight());
				break;
			case TRIANGLE:
				Triangle tri = (Triangle) shape;
				graphics2d.fillPolygon(tri.getxArray(), tri.getyArray(), 3);
				break;
			default:
				break;
			}
		}
		
		Color secColor = translateColor(shape.getSecColor());
		graphics2d.setColor(secColor);
		graphics2d.setStroke(new BasicStroke(5));
		
		if(shape.getShading() == ShapeShadingType.OUTLINE || shape.getShading() == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
			switch(shape.getType()) {
			case ELLIPSE:
		        graphics2d.drawOval(shape.getCoor().getxCoor(), shape.getCoor().getyCoor(), shape.getWidth(), shape.getHeight());
				break;
			case RECTANGLE:
		        graphics2d.drawRect(shape.getCoor().getxCoor(), shape.getCoor().getyCoor(), shape.getWidth(), shape.getHeight());
				break;
			case TRIANGLE:
				Triangle tri = (Triangle) shape;
				graphics2d.drawPolygon(tri.getxArray(), tri.getyArray(), 3);
				break;
			default:
				break;
			}
		}

	}
	
	private static Color translateColor(ShapeColor color) {
		switch(color) {
		case BLACK:
			return Color.black;
		case BLUE:
			return Color.blue;
		case CYAN:
			return Color.cyan;
		case DARK_GRAY:
			return Color.darkGray;
		case GRAY:
			return Color.gray;
		case GREEN:
			return Color.green;
		case LIGHT_GRAY:
			return Color.lightGray;
		case MAGENTA:
			return Color.magenta;
		case ORANGE:
			return Color.orange;
		case PINK:
			return Color.pink;
		case RED:
			return Color.red;
		case WHITE:
			return Color.white;
		case YELLOW:
			return Color.yellow;
		default:
			return Color.green;
		
		}
		
	}
		
}
