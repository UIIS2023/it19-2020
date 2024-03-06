package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends ColorShape implements Cloneable{
	
	 private Hexagon hexagon;
	 
	 public HexagonAdapter() {
		
	}
	 
	 public HexagonAdapter(Hexagon hexagon) {
		 this.hexagon=hexagon;
	 }

	 
	 public HexagonAdapter(Hexagon hexagon, Color color, Color innerColor) {
		 this.hexagon=hexagon;
		//setColor(color);
		//setInnerColor(innerColor);
		this.hexagon.setBorderColor(color);
		this.hexagon.setAreaColor(innerColor);
		
	 }

	@Override
	public void moveTo(int x, int y) {
			
	}

	@Override
	public void moveBy(int x, int y) {	
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof HexagonAdapter)
		{
			return(int) (this.area()-((HexagonAdapter)o).area());
		}
		else 
			return 0;
	}
	public double area() {
		return (3 * Math. sqrt(3) * hexagon.getR() * hexagon.getR()) / 2;
	}



	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fill(Graphics g) {
		//hexagon.paint(g);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
		
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		//this.fill(g);
		
	}
	
	@Override
	public  void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}
	@Override
	public String toString() {
		Point center = new Point(hexagon.getX(), hexagon.getY());
		return "Hexagon: Center: x= "+center.getX()+" y="+center.getY()+ ", radius=" +hexagon.getR()+hexaColorText();
		
	}
	@Override
	public HexagonAdapter clone() {
		Hexagon hexagon = new Hexagon(this.getX(), this.getY(), this.getR());
		HexagonAdapter clonedHexa = new HexagonAdapter(hexagon);
		clonedHexa.setColor(this.getHexaColor());
		clonedHexa.setInnerColor(this.getHexaInnerColor());
		
		return clonedHexa;
			
	}
	public String hexaColorText() {
		return "("+hexagon.getBorderColor().getRed()+","+hexagon.getBorderColor().getGreen()+","+hexagon.getBorderColor().getBlue()+")"+"FillColor:("+hexagon.getAreaColor().getRed()+","+hexagon.getAreaColor().getGreen()+","+hexagon.getAreaColor().getBlue()+")";
	}

	public int getR() {
		return hexagon.getR();
	}

	public int getY() {
		return hexagon.getY();
	}

	public int getX() {
		return hexagon.getX();
	}
	public void setY(int y) {
	     hexagon.setY(y);
	}
	public void setX(int x) {
		hexagon.setX(x);
	}
	public void setR(int r) {
		hexagon.setR(r);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	public void setColor(Color color) {
		this.hexagon.setBorderColor(color);
		
	}
	public void setInnerColor(Color innerColor) {
		this.hexagon.setAreaColor(innerColor);
		
	}
	public Color getHexaColor() {
		return this.hexagon.getBorderColor();
	}
	public Color getHexaInnerColor() {
		return this.hexagon.getAreaColor();		
	}
	
}
