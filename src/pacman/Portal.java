package pacman;

public class Portal extends Occupant{
	Cell target;
	public Portal( Cell target) {
		this.representation = "  0 ";
		this.target = target;
		
		identity = "0";
	}
}
