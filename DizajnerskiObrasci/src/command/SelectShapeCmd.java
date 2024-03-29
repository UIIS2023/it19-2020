package command;

import geometry.Shape;

public class SelectShapeCmd implements Command {
	private Shape s;
	
	public SelectShapeCmd(Shape s) {
		this.s=s;
	}

	@Override
	public void execute() {
		s.setSelected(true);
		
	}

	@Override
	public void unexecute() {
		s.setSelected(false);
		
	}
	@Override
	public String toString() { // for log
		return "Selected:" + s.toString();
	}
	

}
