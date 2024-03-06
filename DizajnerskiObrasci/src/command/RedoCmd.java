package command;

import java.util.Stack;

import mvc.DrawingFrame;

public class RedoCmd implements Command{
	private DrawingFrame frame;
	private Stack undoStack;
	private Stack redoStack;
	private Command curRedoCmd=(Command) redoStack;


	public RedoCmd(DrawingFrame frame, Stack undoStack, Stack redoStack) {
		this.frame=frame;
		this.undoStack=undoStack;
		this.redoStack=redoStack;
	}

	@Override
	public void execute() {
	    curRedoCmd=(Command)redoStack.peek();
		curRedoCmd.execute();
		frame.getView().repaint();
		redoStack.pop();
		undoStack.push(curRedoCmd);
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "Redone-> "+curRedoCmd.toString();
	}

}
