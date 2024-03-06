package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {
	private Rectangle oldRec;
	private Rectangle newRec;
	
	private Rectangle original=new Rectangle();
	
	public UpdateRectangleCmd(Rectangle oldRec, Rectangle newRec) {
		this.oldRec=oldRec;
		this.newRec=newRec;
	}
	@Override
	public void execute() {
		original=oldRec.clone();
		
		oldRec.getUpperLeftPoint().setX(newRec.getUpperLeftPoint().getX());
		oldRec.getUpperLeftPoint().setY(newRec.getUpperLeftPoint().getY());	
		oldRec.setHeight(newRec.getHeight());
		oldRec.setWidth(newRec.getWidth());
		oldRec.setColor(newRec.getColor());
		oldRec.setInnerColor(newRec.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		oldRec.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		oldRec.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		oldRec.setHeight(original.getHeight());
		oldRec.setWidth(original.getWidth());
		oldRec.setColor(original.getColor());
		oldRec.setInnerColor(original.getInnerColor());
		
	}
	@Override
	public String toString() {
		return "Updated:"+ original.toString()+"-->"+newRec.toString();
	}
	

}
