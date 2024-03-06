package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import command.AddPointCmd;
import command.AddShapeCmd;
import command.BringToBack;
import command.BringToFront;
import command.Command;
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
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgCircle;
import drawing.DlgDelete;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import observer.ShapesObserver;
import strategy.SaveDrawing;
import strategy.SaveLog;
import strategy.SavingManager;

public class DrawingController implements PropertyChangeListener, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DrawingModel model;
	private DrawingFrame frame;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	Point startPoint;
	Point upperLeftPoint;
	Point center;
	Shape clShape;
	private Color innerColorRectangle;
	private Color borderColorRectangle;
	private Color innercolorCircle;
	private Color borderColorCircle;
	private Color innerColorDonut;
	private Color borderColorDonut;
	private Color borderColorHexa;
	private Color innerColorHexa;
	private Color colorPoint;
	private Color colorLine;
	private boolean isLoadedFile=false;
	
	private Stack<Command> undoRedoStack= new Stack<>();
	private Stack<Command> undoStack=new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	
	private List<Command> dlmCommand= new ArrayList(); 
	


	public void mouseClicked(MouseEvent e) {
		 
		   if(frame.tglBtnPoint.isSelected()) {
		    	Point p = new Point(e.getX(), e.getY(), frame.getBorderFill());
		    	AddShapeCmd addP= new AddShapeCmd(model, p);
		    	addP.execute();
		    	undoStack.push(addP);
		    	frame.getBtnUndo().setEnabled(true);
		    	redoStack.clear();
		    	frame.getBtnRedo().setEnabled(false);
		    	if(!isLoadedFile)
		    	frame.getDlmString().addElement(addP.toString()+"("+frame.getBorderFill().getRed()+","+frame.getBorderFill().getGreen()+","+frame.getBorderFill().getBlue()+")");
		    	dlmCommand.add(addP);;
		    	
		     
		    }
		   else if(frame.tglBtnLine.isSelected()) {
			   if(startPoint == null) {
					startPoint = new Point(e.getX(), e.getY(), frame.getBorderFill());
				}
				else {
					Line l=new Line(startPoint,new Point( e.getX(), e.getY()), frame.getBorderFill());
					AddShapeCmd addShapeCmd= new AddShapeCmd(model, l);
					addShapeCmd.execute();
					startPoint=null;
					undoStack.push(addShapeCmd);
					frame.getBtnUndo().setEnabled(true);
					redoStack.clear();
			    	frame.getBtnRedo().setEnabled(false);
					frame.getDlmString().addElement(addShapeCmd.toString());
			    	dlmCommand.add(addShapeCmd);

				
		    }
		   }
		    else if(frame.tglBtnRectangle.isSelected()) {
		    	upperLeftPoint= new Point(e.getX(), e.getY());
				DlgRectangle dlgRectangle=new DlgRectangle();
				dlgRectangle.getTextupX().setText(Integer.toString(upperLeftPoint.getX())); 
				dlgRectangle.getTxtupY().setText(Integer.toString(upperLeftPoint.getY()));
				dlgRectangle.setVisible(true);
				
				if(dlgRectangle.isOk()) {
					int x=Integer.parseInt(dlgRectangle.getTextupX().getText());
					int y=Integer.parseInt(dlgRectangle.getTxtupY().getText());
					int width=Integer.parseInt(dlgRectangle.getTextWidth().getText());
					int height=Integer.parseInt(dlgRectangle.getTxtHeight().getText());
					
					
					if(width !=0 && height!=0) {
						clShape=new Rectangle(new Point(x,y), width, height,false,frame.getBorderFill(), frame.getInnerFill());
						AddShapeCmd addRectangle= new AddShapeCmd(model, clShape);
						addRectangle.execute();
						undoStack.push(addRectangle);
						frame.getBtnUndo().setEnabled(true);
						redoStack.clear();
						if(!isLoadedFile)
						frame.getDlmString().addElement(addRectangle.toString());
				    	dlmCommand.add(addRectangle);

						
					}
					
					else {
						JOptionPane.showMessageDialog(null, "The width amd height must be greater than zero!");
					}
				}
		    }
		    else if(frame.tglBtnHexagon.isSelected()) {
		    	Point center=new Point(e.getX(), e.getY());
				DlgHexagon dlgHexagon=new DlgHexagon();
				dlgHexagon.getTextX().setText(Integer.toString(center.getX()));
				dlgHexagon.getTextY().setText(Integer.toString(center.getY()));
				dlgHexagon.setVisible(true);
				
				if(dlgHexagon.isOk()) {
					
					int x=Integer.parseInt(dlgHexagon.getTextX().getText());
					int y=Integer.parseInt(dlgHexagon.getTextY().getText());
					int r=Integer.parseInt(dlgHexagon.getTextRadius().getText());
					
					if(r>0) {
						Hexagon hexagon=new Hexagon(x, y, r);
						HexagonAdapter hexaAdapter=new HexagonAdapter(hexagon, frame.getBorderFill(), frame.getInnerFill());
						AddShapeCmd addHexagonCmd= new AddShapeCmd(model, hexaAdapter);
						addHexagonCmd.execute();
						undoStack.push(addHexagonCmd);
						frame.getBtnUndo().setEnabled(true);
						redoStack.clear();
				    	frame.getBtnRedo().setEnabled(false);
				    	if(!isLoadedFile)
				    	frame.getDlmString().addElement(addHexagonCmd.toString());
				    	dlmCommand.add(addHexagonCmd);

					}
				}
		    } else if(frame.tglBtnCircle.isSelected()) {
					    	Point center=new Point(e.getX(), e.getY());
							DlgCircle dlgCircle=new DlgCircle();
							dlgCircle.getTextCenterX().setText(Integer.toString(center.getX()));
							dlgCircle.getTextCenterY().setText(Integer.toString(center.getY()));
							dlgCircle.setVisible(true);
				
							
							if(dlgCircle.isOk()) {
								
								int x=Integer.parseInt(dlgCircle.getTextCenterX().getText());
								int y=Integer.parseInt(dlgCircle.getTextCenterY().getText());
								int r=Integer.parseInt(dlgCircle.getTextRadius().getText());
								
								
								
								if(r>0) {
									clShape=new Circle(new Point(x,y), r,false, frame.getBorderFill(), frame.getInnerFill());
									AddShapeCmd addCircle= new AddShapeCmd(model, clShape);
									addCircle.execute();
									undoStack.push(addCircle);
									frame.getBtnUndo().setEnabled(true);
									redoStack.clear();
							    	frame.getBtnRedo().setEnabled(false);
							    	if(!isLoadedFile)
							    	frame.getDlmString().addElement(addCircle.toString());
								}
					else
					{
						JOptionPane.showMessageDialog(null, "Radius must be grather than zero!");
					}
				}
		    }else if(frame.tglBtnDonut.isSelected()) {
		    	Point center=new Point(e.getX(), e.getY());
				DlgDonut dlgDonut=new DlgDonut();
				dlgDonut.getTextCenterX().setText(Integer.toString(center.getX()));
				dlgDonut.getTxtCenterY().setText(Integer.toString(center.getY()));
				dlgDonut.setVisible(true);
				
				if(dlgDonut.isOk()) {
					int x=Integer.parseInt(dlgDonut.getTextCenterX().getText());
					int y=Integer.parseInt(dlgDonut.getTxtCenterY().getText());
					int innerRadius=Integer.parseInt(dlgDonut.getTextinnerRadius().getText());
					int radius=Integer.parseInt(dlgDonut.getTextRadius().getText());
						
					if(innerRadius<radius) {
						clShape=new Donut(new Point(x,y),radius, innerRadius, false, frame.getBorderFill(), frame.getInnerFill());
						AddShapeCmd addDonut= new AddShapeCmd(model, clShape);
						addDonut.execute();
						undoStack.push(addDonut);
						frame.getBtnUndo().setEnabled(true);
						redoStack.clear();
				    	frame.getBtnRedo().setEnabled(false);
				    	if(!isLoadedFile)
				    	frame.getDlmString().addElement(addDonut.toString());
				    	dlmCommand.add(addDonut);

					
					}
					else {
						JOptionPane.showMessageDialog(null, "Radius must be greater then inner radius or radius must be greather than zero!");
						
					}
					
					}
			
		    }
		    else if(frame.btnSelect.isSelected()) {
		    	selectShape(e);
		    }
		    else
		    {
		    	JOptionPane.showMessageDialog(null, "You have to pick sahpe first!");
		    }
		    frame.getView().repaint();	    	 
	}
	
	
	//------------------Select----------------------------------------------------

	private void selectShape(MouseEvent e) {
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) 
		{
			Shape shape = it.next();
			if (shape.contains(e.getX(), e.getY())) 
			{
				if(shape.isSelected()) {
			
					DeselectShapeCmd deselectShapeCmd = new DeselectShapeCmd(shape);
					deselectShapeCmd.execute();
					undoStack.push(deselectShapeCmd);
					frame.getDlmString().addElement(deselectShapeCmd.toString());
			    	dlmCommand.add(deselectShapeCmd);
				    countSelected();
					//shape.setSelected(false);
				} else {
					//shape.setSelected(true);
					SelectShapeCmd selectShapeCmd= new SelectShapeCmd(shape);
					selectShapeCmd.execute();
					undoStack.push(selectShapeCmd);
					if(!isLoadedFile)
					frame.getDlmString().addElement(selectShapeCmd.toString());
			    	dlmCommand.add(selectShapeCmd);
				    countSelected();	
				}
				break;
			}
		
		}
		frame.getView().repaint();
	}
	
	

	//--------------------------------Modify-------------------------------------------------------------------------

	public  void modifyShape() {
		for(int i=0; i<model.getShapes().size(); i++) 
		{
			if (model.get(i) instanceof Point) 
			{
				if(model.get(i).isSelected()) 
				{
					DlgPoint dlgPoint=new DlgPoint();
					Point point=(Point) model.get(i);
					dlgPoint.getTxtX().setText(Integer.toString(point.getX()));
					dlgPoint.getTxtY().setText(Integer.toString(point.getY()));
					dlgPoint.getBtnColor().setBackground(point.getColor());
					dlgPoint.setVisible(true);
					
					if(dlgPoint.isOk) {
						int x=Integer.parseInt(dlgPoint.getTxtX().getText());
						int y=Integer.parseInt(dlgPoint.getTxtY().getText());
						colorPoint=dlgPoint.getBtnColor().getBackground();
						if(dlgPoint.isColorOk()) {
							colorPoint=dlgPoint.fill;
						}
						else {
							colorPoint=frame.borderFill;
						}
						Point newP=new Point(x,y,colorPoint);
						UpdatePointCmd updatePointCmd= new UpdatePointCmd(point, newP);
						updatePointCmd.execute();
						undoStack.push(updatePointCmd);
						if(!isLoadedFile)
						frame.getDlmString().addElement(updatePointCmd.toString()+"("+colorPoint.getRed()+","+colorPoint.getGreen()+","+colorPoint.getBlue()+")");
						frame.getView().repaint();
						
					}
										
				}
			}
					
			else if (model.get(i) instanceof Line) 
			{
				if (model.get(i).isSelected()) 
				{
					DlgLine dlgLine = new DlgLine();
					Line line = (Line) model.get(i);
					dlgLine.getTxtStartX().setText(Integer.toString(line.getStartPoint().getX()));
					dlgLine.getTxtStartY().setText(Integer.toString(line.getStartPoint().getY()));
					dlgLine.getTxtEndX().setText(Integer.toString(line.getEndPoint().getX()));
					dlgLine.getTxtEndY().setText(Integer.toString(line.getEndPoint().getY()));
					dlgLine.getBtnColor().setBackground(line.getColor());
					dlgLine.setVisible(true);
					
					if (dlgLine.isOk()) 
					{
						int startX = Integer.parseInt(dlgLine.getTxtStartX().getText());
						int startY = Integer.parseInt(dlgLine.getTxtStartY().getText());
						int endX = Integer.parseInt(dlgLine.getTxtEndX().getText());
						int endY = Integer.parseInt(dlgLine.getTxtEndY().getText());
						colorLine=dlgLine.getBtnColor().getBackground();
						if (dlgLine.isColorOk()) 
						{
							colorLine = dlgLine.borderColor;
						}
						
						Line newl = new Line(new Point(startX, startY), new Point(endX,endY), colorLine);
						UpdateLineCmd updateLine = new UpdateLineCmd(line,newl);
						updateLine.execute();
						undoStack.push(updateLine);
						if(!isLoadedFile)
						frame.getDlmString().addElement(updateLine.toString());
						frame.getView().repaint();
					}
				}
			}
			else if (model.get(i) instanceof Rectangle) 
			{
				if (model.get(i).isSelected()) 
				{
					Rectangle r = (Rectangle) model.get(i);
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.getBtnBorderColor().setVisible(true);
					dlgRectangle.getBtnInnerColor().setVisible(true);
					dlgRectangle.getTextupX().setEditable(true);
					dlgRectangle.getTxtupY().setEditable(true);
					dlgRectangle.getTextupX().setText(Integer.toString(r.getUpperLeftPoint().getX()));
					dlgRectangle.getTxtupY().setText(Integer.toString(r.getUpperLeftPoint().getY()));
					dlgRectangle.getTxtHeight().setText(Integer.toString(r.getHeight()));
					dlgRectangle.getTextWidth().setText(Integer.toString(r.getWidth()));
					dlgRectangle.getBtnBorderColor().setBackground(r.getColor());
					dlgRectangle.getBtnInnerColor().setBackground(r.getInnerColor());
					dlgRectangle.setVisible(true);
					
					if (dlgRectangle.isOk()) 
					{
						int x = Integer.parseInt(dlgRectangle.getTextupX().getText());
						int y = Integer.parseInt(dlgRectangle.getTxtupY().getText());
						int width = Integer.parseInt(dlgRectangle.getTextWidth().getText());
						int height = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
						borderColorRectangle=dlgRectangle.getBtnBorderColor().getBackground();
						innerColorRectangle=dlgRectangle.getBtnInnerColor().getBackground();
						if (dlgRectangle.isInnerColorOk()) 
						{
							innerColorRectangle=dlgRectangle.getInnerFill();
						} 
						
						if (dlgRectangle.isBorderColorOk()) 
						{
							borderColorRectangle = dlgRectangle.getBorderFill();
						}
						
						if (width > 0 && height > 0) 
						{
							Rectangle newR= new Rectangle(new Point(x, y), height, width, false, borderColorRectangle, innerColorRectangle);
							UpdateRectangleCmd updateRectCmd=new UpdateRectangleCmd(r, newR);
							updateRectCmd.execute();
							if(!isLoadedFile)
							frame.getDlmString().addElement(updateRectCmd.toString());
							undoStack.push(updateRectCmd);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "The width and height must be greather than zero!");
						}
						frame.getView().repaint();
					}
				}
			}
			else if (model.get(i) instanceof Donut) 
			{
				if (model.get(i).isSelected()) 
				{
					Donut donut = (Donut) model.get(i);
					DlgDonut dlgDonut= new DlgDonut();
					dlgDonut.getBtnBorderColor().setVisible(true);
					dlgDonut.getBtnInnerColor().setVisible(true);
					dlgDonut.getTextCenterX().setEditable(true);
					dlgDonut.getTxtCenterY().setEditable(true);
					dlgDonut.getTextCenterX().setText(Integer.toString(donut.getCenter().getX()));
					dlgDonut.getTxtCenterY().setText(Integer.toString(donut.getCenter().getY()));
					dlgDonut.getTextinnerRadius().setText(Integer.toString(donut.getInnerRadius()));
					dlgDonut.getTextRadius().setText(Integer.toString(donut.getRadius()));
					dlgDonut.getBtnBorderColor().setBackground(donut.getColor());
					dlgDonut.getBtnInnerColor().setBackground(donut.getInnerColor());
					dlgDonut.setVisible(true);

					if (dlgDonut.isOk()) 
					{
						int x = Integer.parseInt(dlgDonut.getTextCenterX().getText());
						int y = Integer.parseInt(dlgDonut.getTxtCenterY().getText());
						int innerR = Integer.parseInt(dlgDonut.getTextinnerRadius().getText());
						int r = Integer.parseInt(dlgDonut.getTextRadius().getText());
						borderColorDonut=dlgDonut.getBtnBorderColor().getBackground();
						innerColorDonut=dlgDonut.getBtnInnerColor().getBackground();
						
						if (dlgDonut.isInnerColorOk()) 
						{
							innerColorDonut=dlgDonut.getInnerFill();
						} 
						
						if (dlgDonut.isBorderColorOk()) 
						{
							borderColorDonut = dlgDonut.getBorderFill();
						} 
						
					
						if (innerR < r) 
						{
							Donut newD = new Donut(new Point(x,y), r, innerR, false, borderColorDonut, innerColorDonut);
							UpdateDonutCmd updateDonut=new UpdateDonutCmd(donut,newD);
							updateDonut.execute();
							undoStack.push(updateDonut);
							if(!isLoadedFile)
							frame.getDlmString().addElement(updateDonut.toString());
						
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "The radius mast be greather than inner radius or inner radius mast be greather than zero!");
						}
						frame.getView().repaint();
					}
				}
			}
			
			else if (model.get(i) instanceof Circle) 
			{
				if (model.get(i).isSelected()) 
				{
					Circle c = (Circle) model.get(i);
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.getBtnBorderColor().setVisible(true);
					dlgCircle.getBtnInnerColor().setVisible(true);
					dlgCircle.getTextCenterX().setEditable(true);
					dlgCircle.getTextCenterY().setEditable(true);
					dlgCircle.getTextCenterX().setText(Integer.toString(c.getCenter().getX()));
					dlgCircle.getTextCenterY().setText(Integer.toString(c.getCenter().getY()));
					dlgCircle.getTextRadius().setText(Integer.toString(c.getRadius()));
					dlgCircle.getBtnBorderColor().setBackground(c.getColor());
					dlgCircle.getBtnInnerColor().setBackground(c.getInnerColor());
					dlgCircle.setVisible(true);
					
					if (dlgCircle.isOk()) 
					{
						int x = Integer.parseInt(dlgCircle.getTextCenterX().getText());
						int y = Integer.parseInt(dlgCircle.getTextCenterY().getText());
						int r = Integer.parseInt(dlgCircle.getTextRadius().getText());
						borderColorCircle=dlgCircle.getBtnBorderColor().getBackground();
						innercolorCircle=dlgCircle.getBtnInnerColor().getBackground();
						if (dlgCircle.isInnerColorOk()) 
						{
							innercolorCircle = dlgCircle.getInnerFill();
						} 
					
						if(dlgCircle.isBorderColorOk()) {
							borderColorCircle=dlgCircle.borderColor;
						}
						if (r > 0) 
						{
							Circle newC= new Circle(new Point(x,y), r, false, borderColorCircle, innercolorCircle);
							UpdateCircleCmd updateCircleCmd=new UpdateCircleCmd(c, newC);
							updateCircleCmd.execute();
							undoStack.push(updateCircleCmd);
							if(!isLoadedFile)
							frame.getDlmString().addElement(updateCircleCmd.toString());
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "The radius must be greater than zero!");
						}
						frame.getView().repaint();
					}
				}
			}else if (model.get(i) instanceof HexagonAdapter) 
			{
				if (model.get(i).isSelected()) 
				{
					
					HexagonAdapter h = (HexagonAdapter) model.get(i);
					DlgHexagon dlgHexagon = new DlgHexagon();
					dlgHexagon.getBtnBorderColor().setVisible(true);
					dlgHexagon.getBtnInnerColor().setVisible(true);
					dlgHexagon.getTextX().setEditable(true);
					dlgHexagon.getTextY().setEditable(true);
					dlgHexagon.getTextX().setText(Integer.toString(h.getX()));
					dlgHexagon.getTextY().setText(Integer.toString(h.getY()));
					dlgHexagon.getTextRadius().setText(Integer.toString(h.getR()));
			
					innerColorHexa=h.getHexagon().getAreaColor();
					borderColorHexa=h.getHexagon().getBorderColor();
					dlgHexagon.getBtnBorderColor().setBackground(h.getHexagon().getBorderColor());
					dlgHexagon.getBtnInnerColor().setBackground(h.getHexagon().getAreaColor());
					dlgHexagon.setVisible(true);
					
					if (dlgHexagon.isOk()) 
					{
						int x = Integer.parseInt(dlgHexagon.getTextX().getText());
						int y = Integer.parseInt(dlgHexagon.getTextY().getText());
						int r = Integer.parseInt(dlgHexagon.getTextRadius().getText());
						
						if (dlgHexagon.isInnerColorOk()) 
						{
							innerColorHexa = dlgHexagon.getInnerFill();
						} 
						
					
						if(dlgHexagon.isBorderColorOk()) {
							borderColorHexa=dlgHexagon.getBorderFill();

						}
					
						if (r > 0) 
						{
							Hexagon hexagon= new Hexagon(x, y, r);
							HexagonAdapter hexaAdapter= new HexagonAdapter(hexagon, borderColorHexa, innerColorHexa);
							UpdateHexagonCmd updateHexagonCmd= new UpdateHexagonCmd(h, hexaAdapter);
							updateHexagonCmd.execute();
							undoStack.push(updateHexagonCmd);
							if(!isLoadedFile)
							frame.getDlmString().addElement(updateHexagonCmd.toString());
		
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "The radius must be greater than zero!");
						}
						frame.getView().repaint();
					}
				}
			}
			
				}
	
		}
	
//----------------------------------------------Delete------------------------------------------------------------------------------
	public void delete() 
	{
		ArrayList<Shape> shapes= new ArrayList<Shape>();
				
		for (Shape shape : model.getShapes()) 
		{
			//if (model.getShapes().get(i).isSelected()) 
			if(shape.isSelected())
			{
			    shapes.add(shape);
			}
		}
				//model.getShapes().add(shape);
				DlgDelete dlgDelete = new DlgDelete();
				dlgDelete.setVisible(true);
				if (dlgDelete.isOk()) 
				{
					//model.remove(model.get(i));
					RemoveShapeCmd removeShapeCmd= new RemoveShapeCmd(model, shapes);
					removeShapeCmd.execute();
					undoStack.push(removeShapeCmd);
					frame.getView().repaint();
					frame.btnDelete.setEnabled(false);
						if(!isLoadedFile)
					frame.getDlmString().addElement(removeShapeCmd.toString());
					countSelected();
				}
			}


	public void toBack() {
		Iterator<Shape> it=model.getShapes().iterator();
		while (it.hasNext()) {
			Shape shape=it.next();
			if(shape.isSelected()) {
				ToBackCmd toBackCmd=new ToBackCmd(model, shape);
				toBackCmd.execute();
				undoStack.push(toBackCmd);
				//redoStack.clear();

				if(!isLoadedFile)
					frame.getDlmString().addElement(toBackCmd.toString());
			}
			frame.getView().repaint();
		}
			
		
		
	}


	public void toFront() {
		Iterator<Shape> it=model.getShapes().iterator();
		while(it.hasNext()) {
			Shape shape=it.next();
			if(shape.isSelected()) {
				ToFrontCmd toFrontCmd = new ToFrontCmd(model, shape);
				toFrontCmd.execute();
				undoStack.push(toFrontCmd);
				//redoStack.clear();
					if(!isLoadedFile)
						frame.getDlmString().addElement(toFrontCmd.toString());
			frame.getView().repaint();
			break;
			}
		}
		
	}


	public void bringToBack() {
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			Shape shape=it.next();
			if (shape.isSelected()) {
				BringToBack brToBackCmd=new BringToBack(model, shape);
				brToBackCmd.execute();
				undoStack.push(brToBackCmd);

					if(!isLoadedFile)
						frame.getDlmString().addElement(brToBackCmd.toString());
				//redoStack.clear();
				frame.getView().repaint();
				break;
			}
		}
		
	}


	public void bringToFront() {
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			Shape shape=it.next();
			if (shape.isSelected()) {
				BringToFront brToFrontCmd=new BringToFront(model, shape);
				brToFrontCmd.execute();
				undoStack.push(brToFrontCmd);
				//redoStack.clear();

					if(!isLoadedFile)
						frame.getDlmString().addElement(brToFrontCmd.toString());
				frame.getView().repaint();
				break;
			}
		}
		
	}



	public void redo() {
		if(!redoStack.isEmpty()) {
		RedoCmd redoCmd=new RedoCmd(frame, undoStack, redoStack);
		redoCmd.execute();
		//undoStack.push(redoCmd);
			if(!isLoadedFile)
				frame.getDlmString().addElement(redoCmd.toString());
		}else {
			frame.getView().repaint();
			JOptionPane.showMessageDialog(null, "There is no action to be redone!");
			//redoStack.clear();
		}
		countSelected();


	}

	public void undo() {
		if(!undoStack.isEmpty()) {
		UndoCmd undoCmd= new UndoCmd(frame, undoStack, redoStack);
		undoCmd.execute();
		//redoStack.push(undoCmd);
				if(!isLoadedFile)
					frame.getDlmString().addElement(undoCmd.toString());
		}else {
			JOptionPane.showMessageDialog(null, "There is no action to be undone!");

		}
		countSelected();
		
					
	}
	public void countSelected() {
		int cnt=0;
		for(Shape shape :model.getShapes()) {
			if (shape.isSelected()) {
				cnt++;
			}
		}
		model.numOfSelectedShapes(cnt);
	
	}



	public Stack<Command> getUndoStack() {
		return undoStack;
	}



	public void setUndoStack(Stack<Command> undoStack) {
		this.undoStack = undoStack;
	}



	public Stack<Command> getRedoStack() {
		return redoStack;
	}



	public boolean isLoadedFile() {
		return isLoadedFile;
	}


	public void setLoadedFile(boolean isLoadedFile) {
		this.isLoadedFile = isLoadedFile;
	}


	public void setRedoStack(Stack<Command> redoStack) {
		this.redoStack = redoStack;
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("selectedShapes")) {
			if((int)evt.getNewValue()<1) {
				frame.getBtnDelete().setEnabled(false);
				frame.getBtnModify().setEnabled(false);
				
				frame.getBtnBringToBack().setEnabled(false);
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
				
				
			}else if((int)evt.getNewValue()>1) {
				
				frame.getBtnDelete().setEnabled(true);
				frame.getBtnModify().setEnabled(false);
				
				frame.getBtnBringToBack().setEnabled(false);
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
				
			

			}else {
				
				frame.getBtnDelete().setEnabled(true);
				frame.getBtnModify().setEnabled(true);
				
				frame.getBtnBringToBack().setEnabled(true);
				frame.getBtnBringToFront().setEnabled(true);
				frame.getBtnToBack().setEnabled(true);
				frame.getBtnToFront().setEnabled(true);
				
			}
		}
		
	}



	public List<Command> getDlmCommand() {
		return dlmCommand;
	}



	public void setDlmCommand(List<Command> dlmCommand) {
		this.dlmCommand = dlmCommand;
	}


	public void save(int chosen) {
		if(chosen == JOptionPane.YES_OPTION) {
			SaveLog saveLog=new SaveLog(frame.getDlmString());
			SavingManager savingManager=new SavingManager(saveLog);
			savingManager.save();
		}
		else if(chosen == JOptionPane.NO_OPTION) {
			SaveDrawing saveDrawing = new SaveDrawing(model.getShapes());
			SavingManager savingManager2= new SavingManager(saveDrawing);
			savingManager2.save();
		
		}	
		
	}

	public void load(int chosen) {
		if(chosen == JOptionPane.YES_OPTION) {
			SaveLog loadLog=new SaveLog(model,frame);
			SavingManager loadingManager=new SavingManager(loadLog);
			loadingManager.load();
			isLoadedFile=true;
			frame.getBtnLoadStep().setEnabled(true);
			undoStack.clear();
			redoStack.clear();
			
		}
		else if(chosen == JOptionPane.NO_OPTION) {
			SaveDrawing loadDrawing = new SaveDrawing(model, frame);
			SavingManager loadingManager2= new SavingManager(loadDrawing);
			loadingManager2.load();
		
		}
		
	}

	public void loadStep() {
		
		
		 String selectedLogEntry = (String) frame.getListString().getSelectedValue();
		 //System.out.println(selectedLogEntry);
	        if (selectedLogEntry != null) {
	             extractCommandFromLogEntry(selectedLogEntry);
	            /*if (command != null && !command.isEmpty()) {
	                executeCommand(command);
	            }*/
	        }
	        }
	/*	
	private void executeCommand(String command) {
		 try {
	            Process process = Runtime.getRuntime().exec(command);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}*/
	@SuppressWarnings("unchecked")
	private void extractCommandFromLogEntry(String selectedLogEntry) {
		
			String[] command=selectedLogEntry.split("\\W+");
			   /* for(int i=0; i<command.length; i++) {
			    	System.out.println(command[i]);
			    	
			    }*/

			    if (command[1].contentEquals("Point")) {
			    	String action=command[0];
		    		Point point = new Point(Integer.parseInt(command[3]),Integer.parseInt(command[5]));
		    		//Point pointColor = new Point(Integer.parseInt(command[3]),Integer.parseInt(command[5]),new Color(Integer.parseInt(command[6]), Integer.parseInt(command[7]),Integer.parseInt(command[8])));

			    	switch(action) {
			    	case "Added":
			    		AddShapeCmd addShape= new AddShapeCmd(model,point);
			    		addShape.execute();
			    		undoStack.push(addShape);
						frame.getBtnUndo().setEnabled(true);
						redoStack.clear();
			    		frame.getView().repaint();
			    	break;
			    	
			   default:
			    		break;
			   case "Selected":
					for(Shape s : model.getShapes()) {
						if(s instanceof Point) {
							if(point.compareTo((Shape)s)==0) {
								System.out.println("Say if its true");
								SelectShapeCmd select = new SelectShapeCmd(s);
								select.execute();
								undoStack.push(select);
							}
						}
					}
					frame.getView().repaint();
					break;
		    	case "Deselected":
					for(Shape s : model.getShapes()) {
						if(s instanceof Point) {
							if(point.compareTo((Shape)s)==0) {
								DeselectShapeCmd deselect = new DeselectShapeCmd(s);
								deselect.execute();
								undoStack.push(deselect);
							}
						}
					}
					frame.getView().repaint();
					break;
		   
		    	case "Updated":
				Point newPoint = new Point((Integer.parseInt(command[8])),Integer.parseInt(command[10]),new Color(Integer.parseInt(command[11]), Integer.parseInt(command[12]),Integer.parseInt(command[13])));
					for(Shape shape : model.getShapes()) {
						if(shape instanceof Point) {
							if(point.compareTo((Shape)shape)==0) {
								UpdatePointCmd update=new UpdatePointCmd((Point)shape, newPoint);
								update.execute();
								undoStack.push(update);
							}
						}
					}
					frame.getView().repaint();
					break;	
			    }
			    }
			    else if(command[1].contentEquals("Line")) {
			    	String action=command[0];
		    		Line line = new Line(new Point(Integer.parseInt(command[4]),Integer.parseInt(command[6])),new Point(Integer.parseInt(command[9]),Integer.parseInt(command[11])),new Color(Integer.parseInt(command[12]),Integer.parseInt(command[13]),Integer.parseInt(command[14])));			    		

			    	switch(action) {
			    	case "Added":
			    		AddShapeCmd addShape= new AddShapeCmd(model,line);
			    		addShape.execute();

			    		undoStack.push(addShape);
						frame.getBtnUndo().setEnabled(true);
						redoStack.clear();
			    		frame.getView().repaint();
			    	break;
			    	case "Selected":
						for(Shape s : model.getShapes()) {
							if(s instanceof Line) {
								if(line.compareTo((Shape)s)==0) {
									SelectShapeCmd select = new SelectShapeCmd(s);
									select.execute();
									undoStack.push(select);
								}
							}
						}
						frame.getView().repaint();
						break;
			    	case "Deselected":
						for(Shape s : model.getShapes()) {
							if(s instanceof Line) {
								if(line.compareTo((Shape)s)==0) {
									DeselectShapeCmd deselect = new DeselectShapeCmd(s);
									deselect.execute();
									undoStack.push(deselect);
								}
							}
						}
						frame.getView().repaint();
						break;
			
			   case "Updated":
					Line newLine = new Line(new Point(Integer.parseInt(command[18]),Integer.parseInt(command[20])),new Point(Integer.parseInt(command[23]),Integer.parseInt(command[25])),new Color(Integer.parseInt(command[26]),Integer.parseInt(command[27]),Integer.parseInt(command[28])));
						for(Shape shape : model.getShapes()) {
							if(shape instanceof Line) {
								if(line.compareTo((Shape)shape)==0) {
									UpdateLineCmd updateLine=new UpdateLineCmd((Line)shape, newLine);
									updateLine.execute();
									undoStack.push(updateLine);
								}
							}
						}
						frame.getView().repaint();
						break;	
			    }
			    }else if(command[1].contentEquals("Rectangle")) {
			    	String action=command[0];
		    		Rectangle rec = new Rectangle(new Point(Integer.parseInt(command[11]),Integer.parseInt(command[13])),Integer.parseInt(command[5]),Integer.parseInt(command[3]),new Color(Integer.parseInt(command[14]),Integer.parseInt(command[15]),Integer.parseInt(command[16])), new Color(Integer.parseInt(command[18]),Integer.parseInt(command[19]),Integer.parseInt(command[20])));			    		

			    	switch(action) {
			    	case "Added":
			    		AddShapeCmd addShape= new AddShapeCmd(model,rec);
			    		addShape.execute();
			    		undoStack.push(addShape);
						frame.getBtnUndo().setEnabled(true);
						redoStack.clear();
			    		frame.getView().repaint();
			    	break;
			    	case "Selected":
						for(Shape s : model.getShapes()) {
							if(s instanceof Rectangle) {
								if(rec.compareTo((Shape)s)==0) {
									SelectShapeCmd select = new SelectShapeCmd(s);
									select.execute();
									undoStack.push(select);
								}
							}
						}
						frame.getView().repaint();
						break;
			    	case "Deselected":
						for(Shape s : model.getShapes()) {
							if(s instanceof Rectangle) {
								if(rec.compareTo((Shape)s)==0) {
									DeselectShapeCmd deselect = new DeselectShapeCmd(s);
									deselect.execute();
									undoStack.push(deselect);
								}
							}
						}
						frame.getView().repaint();
						break;
			   default:
			    		break;
			   case "Updated":
					Rectangle newRec = new Rectangle(new Point(Integer.parseInt(command[31]),Integer.parseInt(command[33])),Integer.parseInt(command[25]),Integer.parseInt(command[23]),new Color(Integer.parseInt(command[34]),Integer.parseInt(command[35]),Integer.parseInt(command[36])),new Color(Integer.parseInt(command[38]),Integer.parseInt(command[39]),Integer.parseInt(command[40])));
						for(Shape shape : model.getShapes()) {
							if(shape instanceof Rectangle) {
								if(rec.compareTo((Shape)shape)==0) {
									UpdateRectangleCmd update=new UpdateRectangleCmd((Rectangle)shape, newRec);
									update.execute();
									undoStack.push(update);
								}
							}
						}
						frame.getView().repaint();
						break;	
			    }
			    }else if(command[1].contentEquals("Circle")) {
			    	String action=command[0];
		    		Circle circle = new Circle(new Point(Integer.parseInt(command[5]),Integer.parseInt(command[7])),Integer.parseInt(command[9]),new Color(Integer.parseInt(command[10]),Integer.parseInt(command[11]),Integer.parseInt(command[12])), new Color(Integer.parseInt(command[14]),Integer.parseInt(command[15]),Integer.parseInt(command[16])));			    		

			    	switch(action) {
			    	case "Added":
			    		AddShapeCmd addShape= new AddShapeCmd(model,circle);
			    		addShape.execute();
			    		undoStack.push(addShape);
						frame.getBtnUndo().setEnabled(true);
						redoStack.clear();
			    		frame.getView().repaint();
			    	break;
			    	case "Selected":
						for(Shape s : model.getShapes()) {
							if(s instanceof Circle) {
								if(circle.compareTo((Shape)s)==0) {
									SelectShapeCmd select = new SelectShapeCmd(s);
									select.execute();
									undoStack.push(select);
								}
							}
						}
						frame.getView().repaint();
						break;
			    	case "Deselected":
						for(Shape s : model.getShapes()) {
							if(s instanceof Circle) {
								if(circle.compareTo((Shape)s)==0) {
									DeselectShapeCmd deselect = new DeselectShapeCmd(s);
									deselect.execute();
									undoStack.push(deselect);
								}
							}
						}
						frame.getView().repaint();
						break;
			   default:
			    		break;
			   case "Updated":
					Circle newCircle = new Circle(new Point(Integer.parseInt(command[21]),Integer.parseInt(command[23])),Integer.parseInt(command[25]),new Color(Integer.parseInt(command[26]),Integer.parseInt(command[27]),Integer.parseInt(command[28])),new Color(Integer.parseInt(command[30]),Integer.parseInt(command[31]),Integer.parseInt(command[32])));
						for(Shape shape : model.getShapes()) {
							if(shape instanceof Circle) {
								if(circle.compareTo((Shape)shape)==0) {
									UpdateCircleCmd updateCircle=new UpdateCircleCmd((Circle)shape, newCircle);
									updateCircle.execute();
									undoStack.push(updateCircle);
								}
							}
						}
						frame.getView().repaint();
						break;	
			    }
			    }else if(command[1].contentEquals("Donut")) {
			    	String action=command[0];
		    		Donut donut = new Donut(new Point(Integer.parseInt(command[5]),Integer.parseInt(command[7])),Integer.parseInt(command[9]),Integer.parseInt(command[12]),new Color(Integer.parseInt(command[13]),Integer.parseInt(command[14]),Integer.parseInt(command[15])), new Color(Integer.parseInt(command[17]),Integer.parseInt(command[18]),Integer.parseInt(command[19])));			    		

			    	switch(action) {
			    	case "Added":
			    		AddShapeCmd addShape= new AddShapeCmd(model,donut);
			    		addShape.execute();
			    		undoStack.push(addShape);
						frame.getBtnUndo().setEnabled(true);
						redoStack.clear();
			    		frame.getView().repaint();
			    	break;
			    	case "Selected":
						for(Shape s : model.getShapes()) {
							if(s instanceof Circle) {
								if(donut.compareTo((Shape)s)==0) {
									SelectShapeCmd select = new SelectShapeCmd(s);
									select.execute();
									undoStack.push(select);
								}
							}
						}
						frame.getView().repaint();
						break;
			    	case "Deselected":
						for(Shape s : model.getShapes()) {
							if(s instanceof Donut) {
								if(donut.compareTo((Shape)s)==0) {
									DeselectShapeCmd deselect = new DeselectShapeCmd(s);
									deselect.execute();
									undoStack.push(deselect);
								}
							}
						}
						frame.getView().repaint();
						break;
			   default:
			    		break;
			   case "Updated":
		    		Donut newDonut = new Donut(new Point(Integer.parseInt(command[24]),Integer.parseInt(command[26])),Integer.parseInt(command[28]),Integer.parseInt(command[31]),new Color(Integer.parseInt(command[32]),Integer.parseInt(command[33]),Integer.parseInt(command[34])), new Color(Integer.parseInt(command[36]),Integer.parseInt(command[37]),Integer.parseInt(command[38])));			    		
						for(Shape shape : model.getShapes()) {
							if(shape instanceof Donut) {
								if(newDonut.compareTo((Shape)shape)==0) {
									UpdateDonutCmd updateDonut=new UpdateDonutCmd((Donut)shape, newDonut);
									updateDonut.execute();
									undoStack.push(updateDonut);
								}
							}
						}
						frame.getView().repaint();
						break;	
			    }
			    }else if(command[1].contentEquals("Hexagon")) {
			    	String action=command[0];
		    		HexagonAdapter hexagon = new HexagonAdapter(new Hexagon(Integer.parseInt(command[4]),Integer.parseInt(command[6]),Integer.parseInt(command[8])),new Color(Integer.parseInt(command[9]),Integer.parseInt(command[10]),Integer.parseInt(command[11])), new Color(Integer.parseInt(command[13]),Integer.parseInt(command[14]),Integer.parseInt(command[15])));			    		

			    	switch(action) {
			    	case "Added":
			    		AddShapeCmd addShape= new AddShapeCmd(model,hexagon);
			    		addShape.execute();
			    		undoStack.push(addShape);
						frame.getBtnUndo().setEnabled(true);
						redoStack.clear();
			    		frame.getView().repaint();
			    	break;
			    	case "Selected":
						for(Shape s : model.getShapes()) {
							if(s instanceof HexagonAdapter) {
								if(hexagon.compareTo((Shape)s)==0) {
									SelectShapeCmd select = new SelectShapeCmd(s);
									select.execute();
									undoStack.push(select);
								}
							}
						}
						frame.getView().repaint();
						break;
			    	case "Deselected":
						for(Shape s : model.getShapes()) {
							if(s instanceof HexagonAdapter) {
								if(hexagon.compareTo((Shape)s)==0) {
									DeselectShapeCmd deselect = new DeselectShapeCmd(s);
									deselect.execute();
									undoStack.push(deselect);
								}
							}
						}
						frame.getView().repaint();
						break;
			   default:
			    		break;
			   case "Updated":
		    		HexagonAdapter newHexagon = new HexagonAdapter(new Hexagon(Integer.parseInt(command[19]),Integer.parseInt(command[21]),Integer.parseInt(command[23])),new Color(Integer.parseInt(command[24]),Integer.parseInt(command[25]),Integer.parseInt(command[26])), new Color(Integer.parseInt(command[28]),Integer.parseInt(command[29]),Integer.parseInt(command[30])));			    		
						for(Shape shape : model.getShapes()) {
							if(shape instanceof HexagonAdapter) {
								if(hexagon.compareTo((Shape)shape)==0) {
									UpdateHexagonCmd update=new UpdateHexagonCmd((HexagonAdapter)shape, newHexagon);
									update.execute();
									undoStack.push(update);
								}
							}
						}
						frame.getView().repaint();
						break;	
			    }
			    }
			    String action1=command[0];
			    switch(action1) {
			    case "Bringback":
			    	bringToBack();
			    	break;
			    case "Front":
			    	bringToFront();
			    	break;
			    case "Removed":
			    	ArrayList<Shape> shapes = new ArrayList<>();
					for(Shape shape : model.getShapes()) {
						if(shape.isSelected()) {
							shapes.add(shape);
						}
					}
					RemoveShapeCmd remove=new RemoveShapeCmd(model, shapes);
					remove.execute();
					undoStack.push(remove);
					frame.getView().repaint();
				break;
				case "Undone":
					undo();
					break;
				case "Redone":
					redo();				
				break;
					
				case "Forward":
					toFront();
					break;
				case "Backward":
					toBack();
					break;
				
				}
			    }
				
					
					
 }

			 
			    
	

			
			
		

