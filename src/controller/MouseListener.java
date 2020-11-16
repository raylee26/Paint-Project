package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.PointCoordinate;
import model.interfaces.IApplicationState;
import model.shapes.Ellipse;
import model.shapes.Rectangle;
import model.shapes.Shape;
import view.interfaces.PaintCanvasBase;
import controller.CommandManager;

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
		// Switch functions based on settings
		switch(state.getActiveStartAndEndPointMode()) {
		case SELECT:
			// Selects Shapes
			
			break;
		case DRAW:
			// Draws Shapes
			CommandManager.makeShape(firstPoint, secondPoint, state, canvas);
			break;
		case MOVE:
			// Moves Shapes
			CommandManager.moveShape(firstPoint, secondPoint, state, canvas);
			break;
		default:
			break;
		}
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
