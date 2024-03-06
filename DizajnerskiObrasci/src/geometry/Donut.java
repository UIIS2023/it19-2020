package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Donut extends Circle implements Cloneable{
	
	private int innerRadius;
	
	
	public Donut(){
		
	}
	public Donut(Point center, int radius, int innerRadius) {
		//this.setCenter(center);
		//this.center=center;
		super(center, radius);
		this.innerRadius=innerRadius;
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		this.setColor(color);
		
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		this.setInnerColor(innerColor);
	}
	
	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
		this(center, radius, innerRadius);
		this.setColor(color);
		this.setInnerColor(innerColor);
	}
	public double area() {
		return super.area()-innerRadius*innerRadius*Math.PI;
	}
	public boolean contains(int x, int y) {
		return center.distance(x, y)>=innerRadius && super.contains(x,y);
	}
	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY())>=innerRadius && super.contains(p.getX(), p.getY());
	}
	

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			if (this.center.equals(pomocni.center) &&
					this.getRadius() == pomocni.getRadius() && innerRadius == pomocni.innerRadius) {
				return true;
			} else {
				return false;
			}
		} else 
			return false;
		}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Donut) {
			return(int) (this.area()-((Donut)o).area());
		}
		return 0;
	}
	
	
	public void fill(Graphics g) {
		super.fill(g);
		g.setColor(getInnerColor());
		g.setColor(Color.WHITE);
		g.fillOval(this.getCenter().getX() - this.innerRadius, this.getCenter().getY() - this.innerRadius, this.innerRadius * 2,  this.innerRadius * 2);

	}
	@Override
	public void draw(Graphics g) {
		/*super.draw(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.setColor(this.getColor());
		
		//g.setColor(Color.BLACK);
		//g.setColor(this.getColor());
		g2d.drawOval(center.getX()-innerRadius,center.getY()-innerRadius, innerRadius*2, innerRadius*2);
		//g.drawOval(center.getX()-innerRadius,center.getY()-innerRadius, innerRadius*2, innerRadius*2);
	
		if(selected) {
			g.drawOval(center.getX()-2,center.getY()-2, 4, 4);
			g.drawOval(center.getX()-innerRadius-2,center.getY()-2, 4, 4);
			g.drawOval(center.getX()+innerRadius-2,center.getY()-2, 4, 4);
			g.drawOval(center.getX()-2,center.getY()-innerRadius-2, 4, 4);
			g.drawOval(center.getX()-2,center.getY()+innerRadius-2, 4, 4);}
			}*/
		Graphics2D g2d = (Graphics2D)g.create();
		
		
        Area donut= createDonut();
		
        g2d.setColor(getInnerColor());
        g2d.fill(donut);
        g2d.setColor(getColor());
        
        g2d.draw(donut);
        
        if(selected) {
			g.drawOval(center.getX()-2,center.getY()-2, 4, 4);
			g.drawOval(center.getX()-innerRadius-2,center.getY()-2, 4, 4);
			g.drawOval(center.getX()+innerRadius-2,center.getY()-2, 4, 4);
			g.drawOval(center.getX()-2,center.getY()-innerRadius-2, 4, 4);
			g.drawOval(center.getX()-2,center.getY()+innerRadius-2, 4, 4);}
			}
        
       // g2d.dispose();
		
	

	
	public Area createDonut() { //Method for creating donut with transparent middle
		
		Double outer=new Ellipse2D.Double(
	            getCenter().getX() - getRadius(), 
	            getCenter().getY() - getRadius(),
	            getRadius()*2, 
	            getRadius()*2);
		
		Double inner=new Ellipse2D.Double(
				getCenter().getX() - innerRadius, 
				getCenter().getY() - innerRadius,
	            innerRadius*2, 
	            innerRadius*2);
		
		Area area = new Area(outer);
        area.subtract(new Area(inner)); //subtracting the inner shape from the outer shape
        return area;
	}
	
	@Override
	public Donut clone()  {
		Donut clonedDonat = new Donut();
		clonedDonat.setCenter(new Point(getCenter().getX(), getCenter().getY()));
		try {
			clonedDonat.setRadius(getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clonedDonat.setInnerRadius(getInnerRadius());
		clonedDonat.setColor(getColor());
		clonedDonat.setInnerColor(getInnerColor());
		
		return clonedDonat;
	}
	



	
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	public String toString() {
		//return super.toString()+", Inner Radius="+ innerRadius;
		return "Donut: Center: " +center+ ", radius= "+radius+", Inner Radius="+ innerRadius+getColorText()+getInnerColorText();
	}


}
