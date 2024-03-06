package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import command.DeselectShapeCmd;
import command.RedoCmd;
import command.RemoveShapeCmd;
import command.SelectShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UndoCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.AddShapeCmd;
import command.BringToBack;
import command.BringToFront;
import command.Command;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;


public class SaveLog implements Saving {
	private DefaultListModel<String> dlmString;
	private DrawingFrame frame;
	private DrawingModel model;
	
	private BufferedReader bufferReader;

	
	public SaveLog(DefaultListModel<String> dlmString) {
		this.dlmString=dlmString;
	}

	public SaveLog(DrawingModel model, DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}


	@Override
	public void save() {
		JFileChooser jFileChooser=new JFileChooser("C:\\Users\\Dijana\\Desktop");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		jFileChooser.setDialogTitle("Please choose location to save!");
		
		if(jFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			File log = new File(jFileChooser.getSelectedFile().getAbsolutePath()+ ".log");
			
			if(log.exists()) {
				JOptionPane.showMessageDialog(null, "File with that name already exists! Try something unique!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				try {
					PrintWriter printWriter = new PrintWriter(log);
					
					for(int i=0; i<dlmString.size();i++) {
						printWriter.println(dlmString.get(i));
					}
					
					printWriter.close();
					JOptionPane.showMessageDialog(null, "Successfully Saved!", "Saving Done :}",
							JOptionPane.INFORMATION_MESSAGE);
					
					
					//saveCommands(commands);
				}catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error while saving the file. Please contact a developer :)", "Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	@Override
	public void load() {
		JFileChooser jfc=new JFileChooser("C:\\Users\\Dijana\\Desktop");
		jfc.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		jfc.setDialogTitle("Open Log File!");
	        int chosen =jfc.showOpenDialog(null);
	        if (chosen == JFileChooser.APPROVE_OPTION) {
	       File logFile=new File(jfc.getSelectedFile().getAbsolutePath());
	       try {
			bufferReader = new BufferedReader(new FileReader(logFile));
			frame.getDlmString().clear();
			 String line;
	            try {
					while ((line = bufferReader.readLine()) != null) {
					    //System.out.println(line);
					    frame.getDlmString().addElement(line);
					  
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//System.out.println(line);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        	
	  
	}
	}
	
	
	
}



