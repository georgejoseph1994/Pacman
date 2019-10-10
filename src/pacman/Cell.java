package pacman;

public class Cell {

	protected int row;
	protected int col;
	protected Occupant occupant;

	public Cell(int row, int col, Occupant occupant) {
		this.row = row;
		this.col = col;
		this.occupant = occupant;
	}
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		this.occupant = null;
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
	
	public boolean equals(Cell cell) {
		if(this.col==cell.col&&this.row==cell.row)
			return true;
		return false;
	}

}
