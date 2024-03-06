package command;

import geometry.Point;
import mvc.DrawingModel;

public class AddPointCmd implements Command{
	
	private DrawingModel model;
	private Point point;
	
	public AddPointCmd (DrawingModel model, Point point) {
		this.model= model;
		this.point = point;
	}

	@Override
	public void execute() {
		model.add(point);
		
	}

	@Override
	public void unexecute() {
		model.remove(point);
	}
	

}
