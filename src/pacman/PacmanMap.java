package pacman;

import java.util.ArrayList;

import exception.IsWallException;
import exception.InvalidPlayerPositionException;
import exception.NoPathException;

public class PacmanMap {

	final static int PLAYER1 = 1;
	final static int PLAYER2 = 2;
	final static int PLAYER3 = 3;
	final static int PLAYER4 = 4;

	final static int MONSTER = 5;
	final static int PORTAL = 6;

	final static int WALL = 7;
	final static int PATH = 8;

	protected Cell grid[][];

	public PacmanMap() {

	}

	public PacmanMap(Cell[][] grid) {
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
		
//		Portal portal = new Portal(new Cell(10,0));
		
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
//		testMap[5][0] = new Cell(5,0,new Portal(new Cell(5,9)));
//		testMap[5][10] = new Cell(5,10,new Portal(new Cell(5,1)));
//		testMap[0][5] = new Cell(0,5,new Portal(new Cell(9,5)));
//		testMap[10][5] = new Cell(10,5,new Portal(new Cell(1,5)));
		this.grid = testMap;
	}
	

	public void addMovableOccupant(MovableOccupant movableOccupant) throws InvalidPlayerPositionException {
		int i = movableOccupant.currentCell.getRow();
		int j = movableOccupant.currentCell.getCol();

		if(GameLogic.isValidPlayerPosition(movableOccupant.currentCell)) {
			this.grid[i][j].setOccupant(movableOccupant);
		}
	}

	public void addPlayer(Player player) throws InvalidPlayerPositionException {
		int i = player.currentCell.getRow();
		int j = player.currentCell.getCol();
		
		if(GameLogic.isValidPlayerPosition(player.currentCell)) {
			this.grid[i][j].setOccupant(player);
		}
	}

	public void addMonster(Monster monster) throws InvalidPlayerPositionException {
		int i = monster.currentCell.getRow();
		int j = monster.currentCell.getCol();
		
		if(GameLogic.isValidPlayerPosition(monster.currentCell)) {
			this.grid[i][j].setOccupant(monster);
		}
	}

	public void movePlayer(Player player, String direction) {
		if(direction!=null) {
			int i = player.currentCell.getRow();
			int j = player.currentCell.getCol();
			Path path = new Path();
			Cell newPos;
			
			try {
				newPos = GameLogic.getNewPosition( player,  direction, grid);
			}catch(InvalidPlayerPositionException e)  {
				newPos = grid[i][j];
			}
			
			int newI = newPos.getRow();
			int newJ = newPos.getCol();
			
			//System.out.println(newI+" "+newJ);
			player.currentCell = newPos;
	
			grid[i][j].occupant = path;
			grid[newI][newJ].occupant = player;
		}
	}
	
	public Player moveMonster(Monster monster, ArrayList<Player> players) {
		Player playerFailed = null;
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for( int i=0; i<players.size(); i++)
			cells.add(players.get(i).currentCell);
		String bestPath;
		try {
			bestPath = GameLogic.getBestDirection(monster.currentCell, cells, "", 10,grid);
		} catch (NoPathException e) {
			bestPath=null;
		}
		if(bestPath==null||bestPath=="")
			return playerFailed;
		else {
			int i = monster.currentCell.getRow();
			int j = monster.currentCell.getCol();

			Path path = new Path();
			grid[i][j].occupant = path;
			
			Cell newPos;
			try {
				newPos = GameLogic.getNewMonsterPosition( monster,  String.valueOf(bestPath.charAt(0)), grid);
			} catch (IsWallException e) {
				newPos = monster.currentCell;
			}

			int newI = newPos.getRow();
			int newJ = newPos.getCol();
			monster.currentCell = newPos;
			if(grid[newI][newJ].occupant.getClass().getName() == "pacman.Player") {
				for( int k=0; k<players.size(); k++) {
					if(players.get(k).currentCell.equals(monster.currentCell)) {
						playerFailed = players.get(k);
					}
						
				}
			}
			grid[newI][newJ].occupant = monster;
			return playerFailed;
		}
	}
	
	
	public Cell[][] getGrid() {
		return grid;
	}

	/*
	 * Method to display a test graph
	 */
	public String[][] displayGrid() {
		String[][] mapMatrix = new String[11][11];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				Occupant cellOccupant = this.getCell(i, j).getOccupant();
				mapMatrix[i][j] = cellOccupant.getIdentity();
				System.out.print(cellOccupant.getRepresentation());
			}
			System.out.println();
		}
		return mapMatrix;
	}
	

}
