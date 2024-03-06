package drawing;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class PnlDrawing extends JPanel {
	private FrmDrawing frmDrawing; 
	private ArrayList<Shape> shapes=new ArrayList<Shape>();
	Shape clShape;
	Point startPoint;
	Point upperLeftPoint;
	Point center;
	private Color innerColorRectangle;
	private Color borderColorRectangle;
	private Color innercolorCircle;
	private Color borderColorCircle;
	private Color innerColorDonut;
	private Color borderColorDonut;
	private Color colorPoint;
	private Color colorLine;

	/**
	 * Create the panel.
	 */
	public PnlDrawing(FrmDrawing frmDrawing) {
		this.frmDrawing=frmDrawing;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onMouseClicked(e);
				
			}});

	}
	
//------------------Mouse----------------------------------------------------
	private void onMouseClicked(MouseEvent e) {
	    if(frmDrawing.getSclick()==1) {
	    	pointDraw(e);
	    }
	    else if(frmDrawing.getSclick()==2) {
	    	lineDraw(e);	 
	    }
	    else if(frmDrawing.getSclick()==3) {
	    	rectangleDraw(e);
	    }
	    else if(frmDrawing.getSclick()==4) {
	    	circleDraw(e);
	    }
	    else if(frmDrawing.getSclick()==5) {
	    	donutDraw(e);
	    }
	    else if(frmDrawing.getSclick()==6) {
	    	selectShape(e);
	    }
	    else
	    {
	    	//JOptionPane.showMessageDialog(null, "You have to pick sahpe first!");
	    }
	    repaint();

}


//---------------------------Draw--------------------------------------------------------------------------------------
	
	public void pointDraw(MouseEvent e) {
		clShape=new Point(e.getX(), e.getY(), false,Color.BLACK);
		shapes.add(clShape);
		frmDrawing.getDlm().addElement(clShape);
	
		
		
	}
	
	public void lineDraw(MouseEvent e) {
		if(startPoint == null) {
			startPoint = new Point(e.getX(), e.getY(), false);
		}
		else {
			clShape=new Line(startPoint,new Point( e.getX(), e.getY()), false, Color.BLACK);
			shapes.add(clShape);
			startPoint=null;
			frmDrawing.getDlm().addElement(clShape);
		}
		}
	public void rectangleDraw(MouseEvent e) {
		upperLeftPoint= new Point(e.getX(), e.getY());
		DlgRectangle dlgRectangle=new DlgRectangle();
		dlgRectangle.getTextupX().setText(Integer.toString(upperLeftPoint.getX())); //setuju se vrijednosti u dijalog
		dlgRectangle.getTxtupY().setText(Integer.toString(upperLeftPoint.getY()));
		dlgRectangle.setVisible(true);
		
		if(dlgRectangle.isOk()) {
			int x=Integer.parseInt(dlgRectangle.getTextupX().getText());
			int y=Integer.parseInt(dlgRectangle.getTxtupY().getText());
			int width=Integer.parseInt(dlgRectangle.getTextWidth().getText());
			int height=Integer.parseInt(dlgRectangle.getTxtHeight().getText());
			
			if(dlgRectangle.isInnerColorOk()) {
				
				innerColorRectangle=dlgRectangle.getInnerFill();
			}
			else {
				innerColorRectangle=Color.WHITE;
			}
              if (dlgRectangle.isBorderColorOk()) {
				
				borderColorRectangle = dlgRectangle.getBorderFill();
			} 
			else 
			{
				borderColorRectangle = Color.BLACK;
			}
			
			if(width !=0 && height!=0) {
				clShape=new Rectangle(new Point(x,y), width, height,false,borderColorRectangle, innerColorRectangle);
				shapes.add(clShape);
				frmDrawing.getDlm().addElement(clShape);
			}
			
			else {
				JOptionPane.showMessageDialog(null, "The width amd height must be greater than zero!");
			}
		}
	}
	private void circleDraw(MouseEvent e) {
		Point center=new Point(e.getX(), e.getY());
		DlgCircle dlgCircle=new DlgCircle();
		dlgCircle.getTextCenterX().setText(Integer.toString(center.getX()));
		dlgCircle.getTextCenterY().setText(Integer.toString(center.getY()));
		dlgCircle.setVisible(true);
		
		if(dlgCircle.isOk()) {
			
			int x=Integer.parseInt(dlgCircle.getTextCenterX().getText());
			int y=Integer.parseInt(dlgCircle.getTextCenterY().getText());
			int r=Integer.parseInt(dlgCircle.getTextRadius().getText());
			
			if(dlgCircle.isInnerColorOk()) {
				innercolorCircle=dlgCircle.getInnerFill();
			}
			else {
				innercolorCircle=Color.WHITE;
			}
              if (dlgCircle.isBorderColorOk()) {
				
				borderColorCircle = dlgCircle.getBorderFill();
			} 
			else 
			{
				borderColorCircle = Color.BLACK;
			}
			
			
			if(r>0) {
				clShape=new Circle(new Point(x,y), r,false, borderColorCircle, innercolorCircle);
				shapes.add(clShape);
				frmDrawing.getDlm().addElement(clShape);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "RAdius must be grather than zero!");
			}
		}
		
		
	}
	private void donutDraw(MouseEvent e) {
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
			
			
			if(dlgDonut.isInnerColorOk()) {
				innerColorDonut=dlgDonut.getInnerFill();
			}
			else {
				innerColorDonut=Color.WHITE;
			}
			if (dlgDonut.isBorderColorOk()) 
			{
				borderColorDonut = dlgDonut.getBorderFill();
			}
			else 
			{
				borderColorDonut = Color.BLACK;
			}
			
			if(innerRadius<radius) {
				clShape=new Donut(new Point(x,y),radius, innerRadius, false, borderColorDonut, innerColorDonut);
				shapes.add(clShape);
				frmDrawing.getDlm().addElement(clShape);
			}
			else {
				JOptionPane.showMessageDialog(null, "Radius must be greater then inner radius or radius must be greather than zero!");
				
			}
			
			}
	}
		
	//---------Selected-------------------------------------------------------------------------------------------
		private void selectShape(MouseEvent e) {
			Shape selected = null;
			Iterator<Shape> it = shapes.iterator();
			while (it.hasNext()) 
			{
				Shape shape = it.next();
				shape.setSelected(false);
				if (shape.contains(e.getX(), e.getY())) 
				{
					selected = shape;
				}
			}
			if (selected != null) 
			{
				selected.setSelected(true);
			}
			repaint();
		}
		
		
		
		//--------------------------------Modify-------------------------------------------------------------------------

	public  void modifyShape() {
		for(int i=0; i<shapes.size(); i++) 
		{
			if (shapes.get(i) instanceof Point) 
			{
				if(shapes.get(i).isSelected()) 
				{
					DlgPoint dlgPoint=new DlgPoint();
					Point point=(Point) shapes.get(i);
					dlgPoint.getTxtX().setText(Integer.toString(point.getX()));
					dlgPoint.getTxtY().setText(Integer.toString(point.getY()));
					dlgPoint.fill=colorPoint;
					dlgPoint.setVisible(true);
					
					if(dlgPoint.isOk) {
						int x=Integer.parseInt(dlgPoint.getTxtX().getText());
						int y=Integer.parseInt(dlgPoint.getTxtX().getText());
						if(dlgPoint.isColorOk()) {
							colorPoint=dlgPoint.getFill();
						}
						else {
							colorPoint=dlgPoint.fill;
						}
						clShape=new Point(x,y,false,colorPoint);
						shapes.add(clShape);
						shapes.remove(shapes.get(i));
						frmDrawing.getDlm().remove(i);
						frmDrawing.getDlm().addElement(clShape);
						repaint();
						
					}
					
					
				}
			}
			
			else if (shapes.get(i) instanceof Line) 
			{
				if (shapes.get(i).isSelected()) 
				{
					DlgLine dlgLine = new DlgLine();
					Line line = (Line) shapes.get(i);
					dlgLine.getTxtStartX().setText(Integer.toString(line.getStartPoint().getX()));
					dlgLine.getTxtStartY().setText(Integer.toString(line.getStartPoint().getY()));
					dlgLine.getTxtEndX().setText(Integer.toString(line.getEndPoint().getX()));
					dlgLine.getTxtEndY().setText(Integer.toString(line.getEndPoint().getY()));
					dlgLine.borderColor=colorLine;
					
					dlgLine.setVisible(true);
					
					if (dlgLine.isOk()) 
					{
						int startX = Integer.parseInt(dlgLine.getTxtStartX().getText());
						int startY = Integer.parseInt(dlgLine.getTxtStartY().getText());
						int endX = Integer.parseInt(dlgLine.getTxtEndX().getText());
						int endY = Integer.parseInt(dlgLine.getTxtEndY().getText());
						if (dlgLine.isColorOk()) 
						{
							colorLine = dlgLine.getFill();
						}
						else 
						{
							colorLine = dlgLine.borderColor;
						}
						clShape = new Line(new Point(startX, startY), new Point(endX,endY), false, colorLine);
						shapes.add(clShape);
						shapes.remove(shapes.get(i));
						frmDrawing.getDlm().remove(i);
						frmDrawing.getDlm().addElement(clShape);
						repaint();
					}
				}
			}
			else if (shapes.get(i) instanceof Rectangle) 
			{
				if (shapes.get(i).isSelected()) 
				{
					Rectangle r = (Rectangle) shapes.get(i);
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.getTextupX().setText(Integer.toString(r.getUpperLeftPoint().getX()));
					dlgRectangle.getTxtupY().setText(Integer.toString(r.getUpperLeftPoint().getY()));
					dlgRectangle.getTxtHeight().setText(Integer.toString(r.getHeight()));
					dlgRectangle.getTextWidth().setText(Integer.toString(r.getWidth()));
					dlgRectangle.innerFill=innerColorRectangle;
					dlgRectangle.borderFill=borderColorRectangle;
					dlgRectangle.setVisible(true);
					
					if (dlgRectangle.isOk()) 
					{
						int x = Integer.parseInt(dlgRectangle.getTextupX().getText());
						int y = Integer.parseInt(dlgRectangle.getTxtupY().getText());
						int width = Integer.parseInt(dlgRectangle.getTextWidth().getText());
						int height = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
						if (dlgRectangle.isInnerColorOk()) 
						{
							innerColorRectangle=dlgRectangle.getInnerFill();
						} 
						else 
						{
							innerColorRectangle=dlgRectangle.innerFill;
						}
						if (dlgRectangle.isBorderColorOk()) 
						{
							borderColorRectangle = dlgRectangle.getBorderFill();
						}
						else
						{
							borderColorRectangle = dlgRectangle.borderFill;
						}
						
						if (width > 0 && height > 0) 
						{
							clShape = new Rectangle(new Point(x,y), width, height, false, borderColorRectangle, innerColorRectangle);
							shapes.add(clShape);
							shapes.remove(shapes.get(i));
							frmDrawing.getDlm().removeElementAt(i);
							frmDrawing.getDlm().addElement(clShape);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "The width and height must be greather than zero!");
						}
						repaint();
					}
				}
			}
			else if (shapes.get(i) instanceof Donut) 
			{
				if (shapes.get(i).isSelected()) 
				{
					Donut donut = (Donut) shapes.get(i);
					DlgDonut dlgDonut= new DlgDonut();
					dlgDonut.getTextCenterX().setText(Integer.toString(donut.getCenter().getX()));
					dlgDonut.getTxtCenterY().setText(Integer.toString(donut.getCenter().getY()));
					dlgDonut.getTextinnerRadius().setText(Integer.toString(donut.getInnerRadius()));
					dlgDonut.getTextRadius().setText(Integer.toString(donut.getRadius()));
					dlgDonut.innerFill=innerColorDonut;
					dlgDonut.borderFill=borderColorDonut;
					dlgDonut.setVisible(true);

					if (dlgDonut.isOk()) 
					{
						int x = Integer.parseInt(dlgDonut.getTextCenterX().getText());
						int y = Integer.parseInt(dlgDonut.getTxtCenterY().getText());
						int innerR = Integer.parseInt(dlgDonut.getTextinnerRadius().getText());
						int r = Integer.parseInt(dlgDonut.getTextRadius().getText());
						
						if (dlgDonut.isInnerColorOk()) 
						{
							innerColorDonut=dlgDonut.getInnerFill();
						} 
						else 
						{
							innerColorDonut=dlgDonut.innerFill;
						}
						if (dlgDonut.isBorderColorOk()) 
						{
							borderColorDonut = dlgDonut.getBorderFill();
						} 
						else 
						{
							borderColorDonut = dlgDonut.borderFill;
						}
					
						if (innerR < r) 
						{
							clShape = new Donut(new Point(x,y), r, innerR,  false, borderColorDonut ,innerColorDonut);
							shapes.add(clShape);
							shapes.remove(shapes.get(i));
							frmDrawing.getDlm().remove(i);
							frmDrawing.getDlm().addElement(clShape);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "The radius mast be greather than inner radius or inner radius mast be greather than zero!");
						}
						repaint();
					}
				}
			}
			
			else if (shapes.get(i) instanceof Circle) 
			{
				if (shapes.get(i).isSelected()) 
				{
					Circle c = (Circle) shapes.get(i);
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.getTextCenterX().setText(Integer.toString(c.getCenter().getX()));
					dlgCircle.getTextCenterY().setText(Integer.toString(c.getCenter().getY()));
					dlgCircle.getTextRadius().setText(Integer.toString(c.getRadius()));
					dlgCircle.innerColor=innercolorCircle;
					dlgCircle.borderColor=borderColorCircle;
					dlgCircle.setVisible(true);
					
					if (dlgCircle.isOk()) 
					{
						int x = Integer.parseInt(dlgCircle.getTextCenterX().getText());
						int y = Integer.parseInt(dlgCircle.getTextCenterY().getText());
						int r = Integer.parseInt(dlgCircle.getTextRadius().getText());
						if (dlgCircle.isInnerColorOk()) 
						{
							innercolorCircle = dlgCircle.getInnerFill();
						} 
						else 
						{
							innercolorCircle= dlgCircle.innerColor;
						}
					
						if(dlgCircle.isBorderColorOk()) {
							borderColorCircle=dlgCircle.borderColor;
						}
						else {
							borderColorCircle=dlgCircle.borderColor;
						}
						if (r > 0) 
						{
							clShape = new Circle(new Point(x,y), r, false, borderColorCircle, innercolorCircle);
							shapes.add(clShape);
							shapes.remove(shapes.get(i));
							frmDrawing.getDlm().remove(i);
							frmDrawing.getDlm().addElement(clShape);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "The radius must be greater than zero!");
						}
						repaint();
					}
				}
			}
			
		
			
				}
			
			
			
		}
	
	
//----------------------------------------------Delete------------------------------------------------------------------------------
	public void delete() 
	{
		for (int i = 0; i < shapes.size(); i++) 
		{
			if (shapes.get(i).isSelected()) 
			{
				DlgDelete dlgDelete = new DlgDelete();
				dlgDelete.setVisible(true);
				if (dlgDelete.isOk()) 
				{
					shapes.remove(shapes.get(i));
					frmDrawing.getDlm().removeElementAt(i);
					repaint();
				}
			}
		}
	}
	
		
	//------------------------------------------Paint---------------------------------------------------------------------------------------------------
	public void paint(Graphics g) 
	{
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) 
		{
			clShape = it.next();
			clShape.draw(g);
		}
	}
}
	


	

