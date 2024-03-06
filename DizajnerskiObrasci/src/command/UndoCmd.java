package command;

import java.util.Stack;

import mvc.DrawingController;
import mvc.DrawingFrame;

public class UndoCmd implements Command {
	private DrawingFrame frame;
	private Stack undoStack;
	private Stack redoStack;
	private Command currentCommand=(Command) undoStack;

	public UndoCmd(DrawingFrame frame, Stack undoStack, Stack redoStack) {
		this.frame=frame;
		this.undoStack=undoStack;
		this.redoStack=redoStack;
		
	}
	@Override
	public void execute() {
	    currentCommand=(Command) undoStack.peek();
		currentCommand.unexecute();
		undoStack.pop();
		redoStack.push(currentCommand);
		frame.getBtnRedo().setEnabled(true);
		frame.getView().repaint();

		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "Undone-> "+currentCommand.toString();
	}

}
