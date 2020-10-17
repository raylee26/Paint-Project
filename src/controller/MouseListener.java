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
		
		// Draws Shapes
		CommandManager.drawShape(firstPoint, secondPoint, state, canvas);
		// Moves Shapes
		
		// Selects Shapes
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
