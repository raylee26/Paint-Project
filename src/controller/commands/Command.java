package controller.commands;

public interface Command {
	
	public void undo();
	
	public void redo();
	
	public void run();

}
