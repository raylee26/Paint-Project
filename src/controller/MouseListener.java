package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.Ellipse;
import model.PointCoordinate;
import model.Rectangle;
import model.Shape;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class MouseListener implements MouseInputListener {

	PointCoordinate firstPoint;
	PointCoordinate secondPoint;
	PaintCanvasBase canvas;
	IApplicationState state;
	
	public MouseListener(PaintCanvasBase canvas, IApplicationState state) {
		this.canvas = canvas;
		this.state = state;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Mouse pressed");
		int x = arg0.getX();
		int y = arg0.getY();
		firstPoint = new PointCoordinate(x,y);
		System.out.println(firstPoint);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Record release point
		System.out.println("Mouse released");
		int x = arg0.getX();
		int y = arg0.getY();
		secondPoint = new PointCoordinate(x,y);
		System.out.println(secondPoint);
		
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

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
