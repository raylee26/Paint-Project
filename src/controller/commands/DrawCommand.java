package controller.commands;

import controller.CommandManager;
import model.interfaces.IApplicationState;
import model.shapes.Shape;
import view.interfaces.PaintCanvasBase;

public class DrawCommand implements Command{
	
	private IApplicationState state;
	private PaintCanvasBase canvas;
	private Shape shape;

	public DrawCommand(Shape shape, IApplicationState state, PaintCanvasBase canvas) {
		this.state = state;
		this.shape = shape;
		this.canvas = canvas;
		
	}
	
	@Override
	public void undo() {
		//Remove last shape added to shape list
		state.getShapeList().remove(shape);
		
		//Redraw canvas
		CommandManager.redrawCanvas(canvas, state);
	}

	@Override
	public void redo() {
		run();
	}

	@Override
	public void run() {
		// Add Rectangle data to Application State
		state.getShapeList().add(shape);
				
		// Draws shape
		CommandManager.drawShape(canvas, shape);
		        
		System.out.println("Number of Shapes: " + state.getShapeList().size());
		
	}

}
