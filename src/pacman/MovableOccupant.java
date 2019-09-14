package pacman;

public class MovableOccupant extends Occupant{
	protected int id;
	protected char direction;
	protected Cell currentCell;

	
	public MovableOccupant(int id,Cell startingCell) {
		this.id = id;
		
		this.currentCell = startingCell;
	}
}
