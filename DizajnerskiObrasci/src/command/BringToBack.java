package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBack implements Command {
	DrawingModel model;
	Shape shape;
	int position;
	
	public BringToBack(DrawingModel model, Shape shape) {
			this.model=model;
			this.shape=shape;
	}

	@Override
	public void execute() {
		this.position=model.getShapes().indexOf(shape);
		if(position==0) {
			JOptionPane.showMessageDialog(null, "Shape is already to back!");
		}else {
		model.remove(shape);
		model.getShapes().add(0, shape);
		}
		
	}

	@Override
	public void unexecute() {
		this.position=model.getShapes().indexOf(shape);
		model.remove(shape);
		model.getShapes().add(shape);
		//model.getShapes().add(position, shape);
		
	}
	@Override
	public String toString() {
		return "Bringback:" + shape.toString();
	}

}
