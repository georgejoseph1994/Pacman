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

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setOccupant(Occupant occupant) {
		this.occupant = occupant;
	}

}
