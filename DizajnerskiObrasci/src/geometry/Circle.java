package geometry;
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends ColorShape implements Cloneable{
	protected Point center;
	protected int radius;
	
	
	
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveBy(int x, int y) {
		center.moveBy(x, y);
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Circle) {
			return(int) (this.area()-((Circle)o).area());
		}
		return 0;
	}
	
	public Circle() {
		
	}
	public Circle(Point center, int radius) {
		this.center=center;
		this.radius=radius;
	}
	
	public Circle(Point center, int radius, boolean selected)
	{
		this(center, radius);
		setSelected(selected);
		//this.selected=selected;
	}
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		this.setColor(color);
	}
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		this.setInnerColor(innerColor);
	}
	
	public Circle(Point center, int radius, Color color, Color innerColor) {
		this(center, radius);
		this.setColor(color);
		this.setInnerColor(innerColor);
	}
	public boolean equals(Object obj) {
		
		if (obj instanceof Circle) {
			Circle pomocna=(Circle) obj;
			if(this.center.equals(pomocna.center) && this.radius==pomocna.radius)
				return true;
			else
				return false;
		}
	
		else
			return false;
}
	public boolean contains(int x, int y) {
		return center.distance(x, y)<=radius;
	}
	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY())<=radius;
	}
	public double area() {
		return this.radius*this.radius*Math.PI;
	}
	public double circumference()
	{
		return 2*this.radius*Math.PI;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX()-radius,center.getY()-radius, radius*2, radius*2);
		this.fill(g);
		
		
		if(selected) {
		g.drawOval(center.getX()-2,center.getY()-2, 4, 4);
		g.drawOval(center.getX()-radius-2,center.getY()-2, 4, 4);
		g.drawOval(center.getX()+radius-2,center.getY()-2, 4, 4);
		g.drawOval(center.getX()-2,center.getY()-radius-2, 4, 4);
		g.drawOval(center.getX()-2,center.getY()+radius-2, 4, 4);}
	
	
	
	}

	
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1, this.radius * 2 - 2, this.radius * 2 - 2);
		
	}
	
	@Override
	public Circle clone() {
		Circle clonedCircle = new Circle();
		clonedCircle.setCenter(getCenter());
		try {
			clonedCircle.setRadius(getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clonedCircle.setColor(getColor());
		clonedCircle.setInnerColor(getInnerColor());
		
		return clonedCircle;
	}
		
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception {
		if(radius<0) {
			throw new Exception("Vrijednost poluprecnika mora biti veca od 0");
			
		}
		//System.out.println("Provjera da li se ova naredba izvrsava ako dodje do izuzetka");
		this.radius = radius;
	}
	
	public String toString() {
		return "Circle: Center: " +center+ ", radius= "+radius+getColorText()+getInnerColorText();
	}


}
