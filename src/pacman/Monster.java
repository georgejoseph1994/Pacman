package pacman;

public class Monster extends Occupant{
	int id;
	char direction;
	
	Cell currentCell;
	
	public Monster(int id,Cell startingCell) {
		this.id = id;
		this.representation = "  M ";
		this.currentCell = startingCell;
	}
}
