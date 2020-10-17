package controller;

import java.awt.Color;
import java.awt.Graphics2D;

import model.PointCoordinate;
import model.Rectangle;
import model.Shape;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class CommandManager {
	
	public static void drawShape(PointCoordinate firstPoint, PointCoordinate secondPoint,
			IApplicationState state, PaintCanvasBase canvas) {
		
		// Do Rectangle math
		int width = Math.abs(secondPoint.getxCoor() - firstPoint.getxCoor());
		int height = Math.abs(secondPoint.getyCoor() - firstPoint.getyCoor());
		int smallX = Math.min(firstPoint.getxCoor(), secondPoint.getxCoor());
		int smallY = Math.min(firstPoint.getyCoor(), secondPoint.getyCoor());
		System.out.println("Width: " + width + " Height: " + height);
		
		// Create Rectangle data
		Shape shape = new Rectangle(new PointCoordinate(smallX, smallY), width, height);
		
		// Add Rectangle data to Application State
		state.getShapeList().add(shape);
		
		// Draw rectangle on screen
		Graphics2D graphics2d = canvas.getGraphics2D();
		graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(shape.getCoor().getxCoor(), shape.getCoor().getyCoor(), shape.getWidth(), shape.getHeight());
        
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
			Graphics2D graphics2d = canvas.getGraphics2D();
			graphics2d.setColor(Color.GREEN);
	        graphics2d.fillRect(s.getCoor().getxCoor(), s.getCoor().getyCoor(), s.getWidth(), s.getHeight());
		}
	}
	
	public static void selectShape() {
		
	}

		
}
