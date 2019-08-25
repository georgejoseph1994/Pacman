package pacman;

import java.util.Scanner;

public class Game {

	public static void main(String args[]) throws InvalidPlayerPositionException {

		Map map = new Map();
		map.initialiseTestMap();
		
		Cell startingCell = map.getCell(1, 1);
		Player player1 = new Player(1,startingCell);
		
		Cell startingCell2 = map.getCell(9, 9);
		Player player2 = new Player(2,startingCell2);
		
		map.addPlayer(player1);
		map.addPlayer(player2);
		
		Scanner sc =new Scanner(System.in);
		String input;
		map.displayGrid();
		while(true) {
			input = sc.next();
			switch (input) {
				case "w":{
					map.movePlayer(player1, "U");
					break;
				}case "a":{
					map.movePlayer(player1, "L");
					break;
				}case "s":{
					map.movePlayer(player1, "D");
					break;
				}case "d":{
					map.movePlayer(player1, "R");
					break;
				}
			}
			map.displayGrid();
		}

	}

}
	