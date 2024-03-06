package mvc;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class DrawingApp {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				try {
					DrawingModel model = new DrawingModel();
					DrawingFrame frame = new DrawingFrame();
					
					frame.getView().setModel(model);
					DrawingController controller = new DrawingController(model, frame);
					frame.setDrawingController(controller);
					model.addPCListener(controller);
					frame.setSize(600, 400);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
					
				}catch(Exception e){
					e.printStackTrace();
					
				}
			
				
			}
		});
		
	}

}