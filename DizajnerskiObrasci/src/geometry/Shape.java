package geometry;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import observer.Observable;
import observer.Observer;


public abstract class Shape implements Moveable, Comparable, Serializable {
	
	protected boolean selected;
	protected Color color;
	public Shape () {
	}
	
	public Shape (boolean selected) {
		this.selected=selected;
	}
	public Shape(Color color) {
		this.color=color;
	}
	public Shape(Color color, boolean selected) {
		this(color);
		this.selected=selected;
	}
	
	public abstract boolean contains (int x, int y);
	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public String getColorText() {
		
		return "("+ getColor().getRed()+","+ getColor().getGreen()+","+ getColor().getBlue()+")";
		//return "BorderColor("+ color.getRGB()+")";

	
		
	}

	

}


