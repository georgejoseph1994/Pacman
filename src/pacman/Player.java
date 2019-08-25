package pacman;

public class Player extends Occupant{
	
	int id;
	char direction;
	
	Cell currentCell;
	
	public Player(int id,Cell startingCell) {
		this.id = id;
		this.representation = " P"+this.id+" ";
		this.currentCell = startingCell;
	}
	

}
