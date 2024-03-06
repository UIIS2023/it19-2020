package command;

import geometry.Line;

public class UpdateLineCmd implements Command {
	private Line oldLine;
	private Line newLine;
	private Line originalLine=new Line();
	
	 public UpdateLineCmd(Line oldLine, Line newLine) {
		 	this.oldLine=oldLine;
		 	this.newLine=newLine;
	} 
		
	

	@Override
	public void execute() {
		
		originalLine=oldLine.clone();
		/*
		originalLine.getStartPoint().setX(oldLine.getStartPoint().getX());
		originalLine.getStartPoint().setY(oldLine.getStartPoint().getY());
		originalLine.getEndPoint().setX(oldLine.getEndPoint().getX());
		originalLine.getEndPoint().setY(oldLine.getEndPoint().getY());
		originalLine.setColor(oldLine.getColor());
*/
		oldLine.getStartPoint().setX(newLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(newLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(newLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(newLine.getEndPoint().getY());
		oldLine.setColor(newLine.getColor());
		
	}

	@Override
	public void unexecute() {
		oldLine.getStartPoint().setX(originalLine.getStartPoint().getX());
		oldLine.getStartPoint().setY(originalLine.getStartPoint().getY());
		oldLine.getEndPoint().setX(originalLine.getEndPoint().getX());
		oldLine.getEndPoint().setY(originalLine.getEndPoint().getY());
		oldLine.setColor(originalLine.getColor());
		
	}
	@Override
	public String toString() {
		return "Updated:"+ originalLine.toString()+"-->"+newLine.toString();
	}

}
