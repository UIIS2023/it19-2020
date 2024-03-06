package command;

import geometry.HexagonAdapter;

public class UpdateHexagonCmd implements Command{
	private HexagonAdapter oldHexa;
	private HexagonAdapter newHexa;
	private HexagonAdapter original= new HexagonAdapter();
	
	public UpdateHexagonCmd(HexagonAdapter oldHexa, HexagonAdapter newHexa) {
		this.oldHexa=oldHexa;
		this.newHexa=newHexa;
	}
	
	@Override
	public void execute() {
		original=oldHexa.clone();
		
		oldHexa.setX(newHexa.getX());
		oldHexa.setY(newHexa.getY());
		oldHexa.setR(newHexa.getR());
		oldHexa.setColor(newHexa.getHexagon().getBorderColor());
		oldHexa.setInnerColor(newHexa.getHexagon().getAreaColor());
		
	}
	@Override
	public void unexecute() {
		oldHexa.setX(original.getX());
		oldHexa.setY(original.getY());
		oldHexa.setR(original.getR());
		oldHexa.setColor(original.getHexaColor());
		oldHexa.setInnerColor(original.getHexaInnerColor());
		
	}
	
	@Override
	public String toString() {
		return "Updated:"+ original.toString()+"-->"+newHexa.toString();
	}

}
