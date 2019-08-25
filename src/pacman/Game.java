package pacman;

public class Game {

	public static void main(String args[]) {

		Map map = new Map();
		map.initialiseTestMap();
		
		Cell startingCell = map.getCell(1, 1);
		Player player1 = new Player(1,startingCell);
		
		
		map.displayGrid();

	}

}
