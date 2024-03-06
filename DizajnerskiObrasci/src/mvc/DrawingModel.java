package mvc;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class DrawingModel {
	private List<Shape> shapes=new ArrayList<>();
	
	private PropertyChangeSupport pcSupport;
	private int numOfSelectedShapes;
	
	public DrawingModel() {
		pcSupport=new PropertyChangeSupport(this);
	}
	
	public void add(Shape p) {
		shapes.add(p);
	}
	
	public void remove(Shape p) {
		shapes.remove(p);
	}

	public List<Shape> getShapes() {
		return shapes;
	}
	
	public Shape get (int i) {
		return shapes.get(i);
		
	}
	

	//PropertyChangeListener
	public void addPCListener(PropertyChangeListener pcListener) {
		pcSupport.addPropertyChangeListener(pcListener);
	}
	
	public void removePCListener(PropertyChangeListener pcListener) {
		pcSupport.removePropertyChangeListener(pcListener);
	}
	
	public void numOfSelectedShapes(int number) {
		pcSupport.firePropertyChange("selectedShapes",this.numOfSelectedShapes, number);
		this.numOfSelectedShapes=number;
	}

}
