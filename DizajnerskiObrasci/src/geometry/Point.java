package geometry;
import java.awt.Color;
import java.awt.Graphics;

import mvc.DrawingFrame;

public class Point extends Shape {
	private int x;
	private int y;
	
	private DrawingFrame frame;

	
	
	public Point() {
		
	}
	
	public Point(int x, int y)
	{
		this.x=x;
		this.y=y;
		
	}
	
	public Point(int x, int y, boolean selected) {
		this(x,y);
		setSelected(selected);
		//this.selected=selected;
	}
	public Point(int x, int y, Color color) {
		this(x,y);
		this.setColor(color);
		
	}
	public Point(int x, int y, boolean selected, Color color) {
		this(x,y,selected);
		this.setColor(color);
	}
	
	public boolean equals(Object obj) {
	
		if (obj instanceof Point) {
			Point pomocna=(Point) obj;
			if(this.x==pomocna.x && this.y==pomocna.y)
				return true;
			else
				return false;
		}
	
		else
			return false;
}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y)<=2;
	}
	
	
	public double distance(int x2, int y2) {
		double dx=this.x-x2;
		double dy=this.y-y2;
		double d=Math.sqrt(dx*dx+dy*dy);
		return d;
	}
	
	
		
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);
		
		if(selected) {
			g.setColor(Color.BLUE);
			g.drawRect(x-2, y-2, 4, 4);
		}
	}
	
	public void setX(int x) {
		this.x=x;
	}
	public int getX() {
		return x;
	}
	

	
	@Override
	public void moveTo(int x, int y) {
		this.x=x;
		this.y=y;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int x, int y) {
		this.x=this.x+x;
		this.y+=y;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Point) {
		
		return (int) (this.distance(0, 0)-((Point)o).distance(0, 0));
	}
		return 0;
	}
	

	public void setY(int y) {
		this.y=y;
	}
	public int getY() {
		return this.y;
	}
	
	
	public String toString() {
		return "Point: x= "+x+ ", y= "+y;
	}

}
