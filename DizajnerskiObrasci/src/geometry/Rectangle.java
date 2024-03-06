package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends ColorShape implements Cloneable {
	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);
		// TODO Auto-generated method stub
		
	}
	private Point upperLeftPoint;
	private int width;
	private int height;
	
	
	
	public Rectangle() {
		
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			return this.area()-((Rectangle)o).area();
		}
		return 0;
	}
	public Rectangle(Point uppperLeftPoint, int width, int height) {
		this.upperLeftPoint=uppperLeftPoint;
		this.height=height;
		this.width=width;
		
	}
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this(upperLeftPoint,height, width);
		setSelected(selected);
		//this.selected=selected;
	}

	
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color) {
		this(upperLeftPoint, width, height, selected);
		this.setColor(color);
	}
	public Rectangle(Point upperLeftPoint, int width, int height, Color color) {
		this(upperLeftPoint, width, height);
		this.setColor(color);
		
	}
	public Rectangle(Point upperLeftPoint, int width, int height, Color color, Color innerColor) {
		this(upperLeftPoint, width, height,color);
		this.setInnerColor(innerColor);
		
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, width, height, selected, color);
		this.setInnerColor(innerColor);
	}
	

	public int area() {
		return this.width*this.height;
	}
	
	
	public int circumference() {
		return 2*this.width+2*this.height;
	}
	
	public boolean equals(Object obj) {
		
		if (obj instanceof Rectangle) {
			Rectangle pomocna=(Rectangle) obj;
			if(this.upperLeftPoint.equals(pomocna.upperLeftPoint) && (this.width==pomocna.width) && (this.height==pomocna.height))
				return true;
			else
				return false;
		}
	
		else
			return false;
}
	
	public boolean contains(int x, int y) {
		if((x>=upperLeftPoint.getX() && x<=upperLeftPoint.getX()+width) && (y>=upperLeftPoint.getY()&& y<upperLeftPoint.getY()+ height))
		return true;
		return false;
	}
	
	public boolean contains(Point p) {
		if((p.getX()>=upperLeftPoint.getX() && p.getX()<=upperLeftPoint.getX()+width) && (p.getY()>=upperLeftPoint.getY()&& p.getY()<upperLeftPoint.getY()+ height))
		return true;
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		this.fill(g);
		
		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeftPoint.getX()-2, upperLeftPoint.getY()-2, 4, 4);
			g.drawRect(upperLeftPoint.getX()+width-2, upperLeftPoint.getY()-2, 4, 4);
			g.drawRect(upperLeftPoint.getX()-2, upperLeftPoint.getY()+ height-2, 4, 4);
			g.drawRect(upperLeftPoint.getX()+width-2, upperLeftPoint.getY()+ height-2, 4, 4);
		}
	
	
	}
	
	
	public  void fill(Graphics g) {
		//g.setColor(getColor());
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1 , this.upperLeftPoint.getY() + 1, this.width - 1, this.height - 1);
		
		
	}
	
	@Override
	public Rectangle clone() {
		Rectangle clonedRec= new Rectangle();
		clonedRec.setUpperLeftPoint(new Point(getUpperLeftPoint().getX(), getUpperLeftPoint().getY()));
		clonedRec.setHeight(getHeight());
		clonedRec.setWidth(getWidth());
		clonedRec.setColor(getColor());
		clonedRec.setInnerColor(getInnerColor());
		
		return clonedRec;
	}
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height)
	{
		this.height=height;
	}
	public int getHeight()
	{
		return this.height;
	}
	
	public String toString() {
		return "Rectangle: Height="+height+ ", Width= "+width+", "+"Upper-left Point: "+upperLeftPoint+getColorText()+getInnerColorText();
	}

}
