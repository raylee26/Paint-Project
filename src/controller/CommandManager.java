package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import controller.commands.DrawCommand;
import controller.commands.History;
import controller.commands.MoveCommand;
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
		
		// Determine width and height
		int width = Math.abs(secondPoint.getxCoor() - firstPoint.getxCoor());
		int height = Math.abs(secondPoint.getyCoor() - firstPoint.getyCoor());
		// Determine location based on smallest x,y coordinate
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
		
		//Create Shape Command
		DrawCommand draw = new DrawCommand(shape, state, canvas);
		//Run Shape Command
		draw.run();
		//Save Shape Command into history
		History.addCommand(draw);
	}
	
	public static void moveShape(PointCoordinate firstPoint, PointCoordinate secondPoint,
			IApplicationState state, PaintCanvasBase canvas) {
		// Determine Direction and Distance of movement
		int moveX = secondPoint.getxCoor() - firstPoint.getxCoor();
		int moveY = secondPoint.getyCoor() - firstPoint.getyCoor();

		//Create Move Command
		MoveCommand move = new MoveCommand(state, canvas, moveX, moveY);
		//Run Move Command
		move.run();
		//Save Move Command into history
		History.addCommand(move);
	}
	
	public static void selectShape(PointCoordinate firstPoint, PointCoordinate secondPoint, IApplicationState state, PaintCanvasBase canvas) {
		
		
		// Determine width and height
		int width = Math.abs(secondPoint.getxCoor() - firstPoint.getxCoor());
		int height = Math.abs(secondPoint.getyCoor() - firstPoint.getyCoor());
		
		// Determine location based on smallest x,y coordinate
		int smallX = Math.min(firstPoint.getxCoor(), secondPoint.getxCoor());
		int smallY = Math.min(firstPoint.getyCoor(), secondPoint.getyCoor());
		
		// Create rectangle using width, height, and location data  Does not draw shape
		Shape shape = new Rectangle(new PointCoordinate(smallX, smallY), width, height, null, null, null);
		
		// Empty selected shape arrayList
		state.getSelectedShapeList().clear();
		
		// Check if shapes inside shapeList falls inside of rectangle
		for(Shape s: state.getShapeList()) {
			// Adds shape to a selected list if it does fall inside rectangle
			if(shape.contains(s)) {
				state.getSelectedShapeList().add(s);
			}
		}
		// clears and redraws canvas
		redrawCanvas(canvas, state);
		
		System.out.println(state.getSelectedShapeList().size() + " shapes selected");
		
	}
	
	/**drawShape
	 * draws provided shape with relevant color and outline
	 * @param canvas- window for application to draw on
	 * @param shape- provided shape to draw outline on
	 */
	public static void drawShape(PaintCanvasBase canvas, Shape shape) {
		
		// Sets Primary Color
		Graphics2D graphics2d = canvas.getGraphics2D();
		graphics2d.setColor(translateColor(shape.getPriColor()));
		
		//Draws filled in shape
		if(shape.getShading() != ShapeShadingType.OUTLINE) {
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
		
		//Sets secondary color
		graphics2d.setColor(translateColor(shape.getSecColor()));
		graphics2d.setStroke(new BasicStroke(5));
		
		//Draws outline
		if(shape.getShading() != ShapeShadingType.FILLED_IN) {
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
	
	/**drawSelectedShape 
	 * draws a dashed outline on shape
	 * @param canvas- window for application to draw on
	 * @param shape- provided shape to draw outline on
	 */
	public static void drawSelectedShape(PaintCanvasBase canvas, Shape shape) {
		Graphics2D graphics2d = canvas.getGraphics2D();
		
		//Set outline make black dashed line
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        
        //Draw dashed outline
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
				break; } 
		
	}
	
	/**redrawCanvas
	 * clears canvas and redraws new shapes and selection outlines
	 * @param canvas- window to draw on
	 * @param state- current state of application
	 */
	public static void redrawCanvas(PaintCanvasBase canvas, IApplicationState state) {
		// Draw New canvas
		//wipes current canvas
		canvas.paintImmediately(0, 0, canvas.getWidth(), canvas.getHeight());
		// Draws outline for selected shape
		for(Shape s: state.getSelectedShapeList()) {
			drawSelectedShape(canvas, s);
		}
		//redraw canvas
		for(Shape s: state.getShapeList()) {
			// Draw shape on screen
			drawShape(canvas, s);
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
