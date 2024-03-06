package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFront implements Command {
	private DrawingModel model;
	private Shape shape;
	private int position;
	
	public BringToFront(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
	}

	@Override
	public void execute() {
		this.position=model.getShapes().indexOf(shape);
		if(position==model.getShapes().size()-1) {
			JOptionPane.showMessageDialog(null, "Shape is already in front!");
		
		}else {

		model.remove(shape);
		model.add(shape);
		}
		
	}

	@Override
	public void unexecute() {
		this.position=model.getShapes().indexOf(shape);
		model.remove(shape);
		model.getShapes().add(0, shape);
		
	}
	@Override
	public String toString() { 
		return "Front:" + shape.toString();
	}

}
