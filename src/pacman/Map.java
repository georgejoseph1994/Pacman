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
	
	public void addPlayer(Player player) throws InvalidPlayerPositionException {
		int i = player.currentCell.getRow();
		int j = player.currentCell.getCol();
		
		if(GameLogic.isValidPlayerPossition(player.currentCell)) {
			this.grid[i][j].setOccupant(player);
		}
	}

	public void addMonster(Monster monster) throws InvalidPlayerPositionException {
		int i = monster.currentCell.getRow();
		int j = monster.currentCell.getCol();
		
		if(GameLogic.isValidPlayerPossition(monster.currentCell)) {
			this.grid[i][j].setOccupant(monster);
		}
	}

	public void movePlayer(Player player, String direction) {
		
		int i = player.currentCell.getRow();
		int j = player.currentCell.getCol();
		Path path = new Path();
		
		
		Cell newPos = GameLogic.getNewPosition( player,  direction, grid);
		
		int newI = newPos.getRow();
		int newJ = newPos.getCol();
		
//		System.out.println(newI+" "+newJ);
		player.currentCell = newPos;

		grid[i][j].occupant = path;
		grid[newI][newJ].occupant = player;
	}
	
	public void moveMonster(Monster monster, Player player) {
		
		String bestPath = GameLogic.getBestDirection(monster.currentCell, player.currentCell, "", 10,grid);
//		System.out.println(bestPath);
		if(bestPath==null)
			return;
		else {
			int i = monster.currentCell.getRow();
			int j = monster.currentCell.getCol();

			Path path = new Path();
			grid[i][j].occupant = path;
			
			Cell newPos = GameLogic.getNewMonsterPosition( monster,  String.valueOf(bestPath.charAt(0)), grid);
			int newI = newPos.getRow();
			int newJ = newPos.getCol();

			monster.currentCell = newPos;
			grid[newI][newJ].occupant = monster;
		}
	}
	
	
	/*
	 * Method to display a test graph
	 */
	public void displayGrid() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				Occupant cellOccupant = this.getCell(i, j).getOccupant();
				System.out.print(cellOccupant.getRepresentation());
			}
			System.out.println();
		}
	}
	

}
