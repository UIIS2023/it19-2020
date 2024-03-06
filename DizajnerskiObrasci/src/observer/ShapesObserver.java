package observer;


import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;



public class ShapesObserver implements Observer {
	private DrawingModel model;
	private DrawingFrame frame;
	
	public ShapesObserver(DrawingModel model, DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}

	@Override
	public void update() {
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
