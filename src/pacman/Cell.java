package pacman;

public class Cell {

	int row;
	int col;
	Occupant occupant;

	public Cell(int row, int col, Occupant occupant) {
		this.row = row;
		this.col = col;
		this.occupant = occupant;
	}

	public Occupant getOccupant() {

		return this.occupant;

	}

}
