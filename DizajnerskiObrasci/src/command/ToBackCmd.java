package command;

import java.util.Collections;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {

	private DrawingModel model;
	private Shape shape;
	//private int position;
	//private int tempPosition;

	
	public ToBackCmd(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
		
	}
	@Override
	public void execute() {
		int position=model.getShapes().indexOf(shape);	
		//model.remove(shape);

		if(position==0) {
			JOptionPane.showInternalMessageDialog(null, "Already back");
			//model.remove(shape);
			//model.getShapes().add(0,shape);
	
		}else {
		
		Collections.swap(model.getShapes(), position, position-1);
			//model.getShapes().add(position-1, shape);

		}
	}

	@Override
	public void unexecute() {
		int position=model.getShapes().indexOf(shape);
		model.remove(shape);
		if(position==model.getShapes().size()) {
			JOptionPane.showMessageDialog(null, "Element is at the top!");
			model.getShapes().add(shape);
	
		}else {
		
			model.getShapes().add(position+1, shape);

		}
		//Collections.swap(model.getShapes(), position, position+1);
		
	}
	@Override
	public String toString() { 
		return "Backward one position:" + shape.toString();
	}
	

}
