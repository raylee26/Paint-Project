package controller.commands;

import java.util.ArrayList;

import controller.CommandManager;
import model.interfaces.IApplicationState;
import model.shapes.Shape;
import view.interfaces.PaintCanvasBase;

public class MoveCommand implements Command{
	
	private IApplicationState state;
	private PaintCanvasBase canvas;
	private int moveX;
	private int moveY;
	private ArrayList<Shape> moveTargets = new ArrayList<Shape>();
	
	public MoveCommand(IApplicationState state, PaintCanvasBase canvas, int moveX, int moveY) {
		this.state = state;
		this.canvas = canvas;
		this.moveX = moveX;
		this.moveY = moveY;
		//For every shape in the selected list 
		for(Shape s : state.getSelectedShapeList()) {
			//We move it into the moveTargets list
			moveTargets.add(s);
		}
		
	}
	
	@Override
	public void undo() {
		//Subtract movement from shape
		for(Shape s: moveTargets) {
			s.moveShape(-moveX, -moveY);
		}
		//clears and redraws canvas
		CommandManager.redrawCanvas(canvas, state);
	}

	@Override
	public void redo() {
		run();
	}

	@Override
	public void run() {
		// Apply movement to shape
		for(Shape s: moveTargets) {
			s.moveShape(moveX, moveY);
		}
		// clears and redraws canvas
		CommandManager.redrawCanvas(canvas, state);
	}

}
