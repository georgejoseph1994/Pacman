package pacman;

public class Portal extends Occupant{
	Cell target;
	public Portal( Cell target) {
		this.representation = "  0 ";
		this.target = target;
		
		identity = "0";
	}
	public Cell getTarget() {
		return target;
	}
	public void setTarget(Cell target) {
		this.target = target;
	}
	
}
