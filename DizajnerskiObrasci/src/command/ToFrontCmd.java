package command;

import java.util.Collections;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command{
	private DrawingModel model;
	private Shape shape;
	private int position;
	
	public ToFrontCmd(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
	}

	@Override
	public void execute() {
		this.position=model.getShapes().indexOf(shape);
		if(position==model.getShapes().size()-1) {
			JOptionPane.showMessageDialog(null, "Shape is already in front!");
		
		}else {
		Collections.swap(model.getShapes(), position, position+1);
		}
	}

	@Override
	public void unexecute() {
		this.position=model.getShapes().indexOf(shape);
		if (position==0) {
			JOptionPane.showMessageDialog(null, "Shape is at the bottom");
		}
		else {
		Collections.swap(model.getShapes(), position, position-1);
		}
	}
	@Override
	public String toString() { //For log
		return "Forward one position:" + shape.toString();
	}
	

}
