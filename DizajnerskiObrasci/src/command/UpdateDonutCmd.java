package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command {
	private Donut oldDonat;
	private Donut newDonat;
	private Donut original=new Donut();
	
	public UpdateDonutCmd(Donut oldDonat, Donut newDonat) {
		this.oldDonat=oldDonat;
		this.newDonat=newDonat;
	}
	@Override
	public void execute() {
		original=oldDonat.clone();
		
		oldDonat.getCenter().setX(newDonat.getCenter().getX());
		oldDonat.getCenter().setY(newDonat.getCenter().getY());
		try {
			oldDonat.setRadius(newDonat.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldDonat.setInnerRadius(newDonat.getInnerRadius());
		oldDonat.setColor(newDonat.getColor());
		oldDonat.setInnerColor(newDonat.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		oldDonat.getCenter().setX(original.getCenter().getX());
		oldDonat.getCenter().setY(original.getCenter().getY());
		try {
			oldDonat.setRadius(original.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldDonat.setInnerColor(original.getInnerColor());
		oldDonat.setColor(original.getColor());
		oldDonat.setInnerColor(original.getInnerColor());
		
	}
	@Override
	public String toString() {
		return "Updated:"+ original.toString()+"-->"+newDonat.toString();
	}

}
