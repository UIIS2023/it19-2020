package command;

import geometry.Shape;

public class DeselectShapeCmd implements Command {
	private Shape s;
	
	public DeselectShapeCmd(Shape s) {
		this.s=s;
	}

	@Override
	public void execute() {
		s.setSelected(false);
		
	}

	@Override
	public void unexecute() {
	     s.setSelected(true);
		
	}
	@Override
	public String toString() {
		return "Deselected:" + s.toString();
	}

}
