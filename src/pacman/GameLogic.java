package pacman;

public class GameLogic {
	
	
	/*
	 * Returns the validity of a players position
	 * @params cell
	 */
	public static boolean isValidPlayerPossition(Cell cell) throws InvalidPlayerPositionException {
		if(cell.getOccupant().getClass().getName() == "pacman.Wall" ) {
			System.out.println("Row"+cell.getRow() + "col"+cell.getCol());
			throw new InvalidPlayerPositionException("Player moved to a wadll");
		}else if(cell.getOccupant().getClass().getName() == "pacman.Player") {
			throw new InvalidPlayerPositionException("Player moved to another players position");
		}
		return true;
	}
	
	/*
	 * 
	 */
	public static Cell getNewPosition(Player player, String direction, Cell[][] grid) {
		
		int i = player.currentCell.getRow();
		int j = player.currentCell.getCol();
		try {
			switch (direction){
				case "U":{
					if(isValidPlayerPossition(grid[i-1][j])) {
						return grid[i-1][j];
					}
					break;
				}case "D":{
					if(isValidPlayerPossition(grid[i+1][j])) {
						return grid[i+1][j];
					}
					break;
				}case "L":{
					if(isValidPlayerPossition(grid[i][j-1])) {
						return grid[i][j-1];
					}
					break;
				}case "R":{
					if(isValidPlayerPossition(grid[i][j+1])) {
						return grid[i][j+1];
					}
					break;
				}
			}
		}catch(InvalidPlayerPositionException e) {
			System.out.println(e.getMessage());
		}
		return grid[i][j];
	}
}
