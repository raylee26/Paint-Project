package controller.commands;

import java.util.Stack;

/**
 * @author Ray
 *
 */
public class History {
	
	private static Stack<Command> undo = new Stack<Command>();
	
	private static Stack<Command> redo = new Stack<Command>();
	
	/**
	 * Adds Commands to the undo stack so they can be undone
	 */
	public static void addCommand(Command command) {
		undo.add(command);
		//Erase redo stack after new commands inputed
		redo.clear();
		
	}
	
	public static void redo() {
		Command command = redo.pop();
		//executes last command from redo stack
		command.redo();
		//adds command back into undo stack
		undo.add(command);
	}
	
	
	/**
	 * Undoes last issued command
	 */
	public static void undo() {
		Command command = undo.pop();
		//Undo Command
		command.undo();
		//Move command to redo stack
		redo.add(command);
	}

}
