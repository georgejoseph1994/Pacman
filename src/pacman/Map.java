package pacman;

public class Map {

	final static int PLAYER1 = 1;
	final static int PLAYER2 = 2;
	final static int PLAYER3 = 3;
	final static int PLAYER4 = 4;

	final static int MONSTER = 5;
	final static int PORTAL = 6;

	final static int WALL = 7;
	final static int PATH = 8;

	Cell grid[][];

	public Map() {

	}

	public Map(Cell[][] grid) {
		this.grid = grid;
	}

	/*
	 * Returns a particular cell in a grid
	 */
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}

	/*
	 * Initialise a test Map
	 */
	public void initialiseTestMap() {

		Cell[][] testMap = new Cell[11][11];
		Wall wall = new Wall();
		Path path = new Path();
		
		
		// Creating a test map
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (i == 0 || i == 10 || j == 0 || j == 10) {
					testMap[i][j] = new Cell(i, j, wall);
				} else if (i == 1 || i == 5 || i == 9 || j == 1 || j == 5 || j == 9) {
					testMap[i][j] = new Cell(i, j, path);
				} else {
					testMap[i][j] = new Cell(i, j, wall);
				}
			}
		}
		this.grid = testMap;
	}
	
	public void updatePlayerPosition() {
		
	}

	/*
	 * Method to display a test graph
	 */
	public void displayGrid() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				Occupant cellOccupant = this.getCell(i, j).getOccupant();
//				if(cellOccupant.getClass() == Path.class) {
//					System.out.print(" ");
//				}else {
//					System.out.print(cellOccupant.getRepresentation());
//				}
				System.out.print(cellOccupant.getRepresentation());
//				System.out.print(" ");
			}
			System.out.println();
		}
	}

}
