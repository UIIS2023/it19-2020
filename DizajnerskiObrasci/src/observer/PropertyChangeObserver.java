package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class PropertyChangeObserver implements PropertyChangeListener {
	private DrawingModel model;
	private DrawingFrame frame;
	

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		int cnt=0;
		for(Shape shape :model.getShapes()) {
			if (shape.isSelected()) {
				cnt++;
			}
		}
		int numOfSelectedShapes=cnt;
		
		if(numOfSelectedShapes==0) {
			frame.getBtnDelete().setVisible(false);
			frame.getBtnModify().setVisible(false);
		}else if (numOfSelectedShapes==1) {
			frame.getBtnModify().setVisible(true);
			frame.getBtnDelete().setVisible(true);
		}else {
			frame.getBtnDelete().setVisible(false);
		}
		
	}
		
	}


