package pacman;

public class Player extends MovableOccupant{
	
	public Player(int id,Cell startingCell) {
		super(id,startingCell);
		this.representation = " P"+this.id+" ";
	}
	

}
