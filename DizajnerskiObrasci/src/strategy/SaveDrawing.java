package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;


public class SaveDrawing implements Saving {
	private List<Shape> shapeList;
	
	private DrawingFrame frame;
	private DrawingModel model;
	
	public SaveDrawing(List<Shape> shapeList) {
		this.shapeList=shapeList;
		
	}
	public SaveDrawing(DrawingModel model, DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}

	@Override
	public void save() {
		JFileChooser jFileChooser = new JFileChooser("C:\\Users\\Dijana\\Desktop");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("ser file (.ser)", "ser"));
		jFileChooser.setDialogTitle("Please choose location to save!");
		
		if(jFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			
			File drawing = new File(jFileChooser.getSelectedFile().getAbsolutePath()+".ser");
			
			if(drawing.exists()) {
				JOptionPane.showMessageDialog(null, "File with that name already exists! Try sometnig unique!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				FileOutputStream fileOutputStream;
				ObjectOutputStream objectOutputStream;
				try {
					fileOutputStream = new FileOutputStream(drawing);
					objectOutputStream = new ObjectOutputStream(fileOutputStream);
					
					objectOutputStream.writeObject(shapeList);
					objectOutputStream.close();
					fileOutputStream.close();
					
					JOptionPane.showMessageDialog(null, "Nice! Successfully Saved!", "Saving Done :}",
							JOptionPane.INFORMATION_MESSAGE);
					
				} catch (Exception e) {
					e.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Error while saving the file. ", "Error!",JOptionPane.ERROR_MESSAGE);
				}	
				
			}
		}
		
	}

	@Override
	public void load() {
		JFileChooser jfc=new JFileChooser("C:\\Users\\Korisnik\\Desktop");
		jfc.setFileFilter(new FileNameExtensionFilter("ser file (.ser)", "ser"));
		jfc.setDialogTitle("Open Ser File!");
	        int choosen =jfc.showOpenDialog(null);
	        if (choosen == JFileChooser.APPROVE_OPTION) {
	        	File serFile=new File(jfc.getSelectedFile().getAbsolutePath());
	        	try 
	        	{
	        		FileInputStream fileInputStream = new FileInputStream(serFile);
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
					frame.getBtnUndo().setEnabled(false);
					//undoRedoStack.clear();
					//undoRedoStackPointer=-1;
					model.getShapes().clear();
					frame.getDlmString().clear();
					@SuppressWarnings("unchecked")
					List<Shape> list = (List<Shape>)objectInputStream.readObject();
					
					for (Shape s : list) {
						model.add(s);
						if(s.isSelected()) {
							s.setSelected(true);
						}
					}
					//frame.getBtnSelect().setEnabled(true);
					objectInputStream.close();
					fileInputStream.close();
	                JOptionPane.showMessageDialog(null, "Drawing loaded succesifuly", "Succesful!",JOptionPane.INFORMATION_MESSAGE);
	                frame.repaint();
	            } catch (Exception ex) 
	        	{
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Error while opening the file.", "Error!",JOptionPane.ERROR_MESSAGE);
	            }
	
	}
}
		
	}


