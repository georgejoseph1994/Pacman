package pacman;

public class Monster extends MovableOccupant{

	public Monster(int id,Cell startingCell) {
		super(id,startingCell);
		this.representation = "  M ";
		this.identity = "M"+this.id;
	}
}
