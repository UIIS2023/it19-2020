package command;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	private DrawingModel model;
	private Hashtable<Integer,Shape> shapes; // storing key(index),value pairs.
	
	
	
	
	public RemoveShapeCmd(DrawingModel model, List<Shape> shapes) {
		this.model=model;
		this.shapes=new Hashtable<>();                         
		
		for(Shape shape : shapes) {
			this.shapes.put(model.getShapes().indexOf(shape), shape);
		}
		
	}

	@Override
	public void execute() {
		
		
		for(Shape shape : shapes.values()) {
			shape.setSelected(false);
			model.getShapes().remove(shape);
		}
				
	}
	
	

	@Override
	public void unexecute() { // returning shapes on same positions they  been before deleting
		for(int k : shapes.keySet()) {
			Shape shape = shapes.get(k);
			if(k <= model.getShapes().size()) {
				model.getShapes().add(k,shape);
				shape.setSelected(true);
			}else {
				model.add(shape);
				shape.setSelected(true);
			}
		} 
	}
	@Override
	public String toString() {
		
		return "Removed shape!";
	}
	


}
