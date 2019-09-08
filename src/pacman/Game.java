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
		
		Cell monsterCell = map.getCell(5, 5);
		Monster monster = new Monster(1,monsterCell);
		
		map.addMovableOccupant(player1);
		map.addMovableOccupant(player2);
		map.addMovableOccupant(monster);
		
		Scanner sc =new Scanner(System.in);
		String input;
		map.displayGrid();
		while(true) {
			input = sc.next();
			switch (input) {
				case "w":{
					map.movePlayer(player1, "U");
					map.moveMonster(monster, player1);
					break;
				}case "a":{
					map.movePlayer(player1, "L");
					map.moveMonster(monster, player1);
					break;
				}case "s":{
					map.movePlayer(player1, "D");
					map.moveMonster(monster, player1);
					break;
				}case "d":{
					map.movePlayer(player1, "R");
					map.moveMonster(monster, player1);
					break;
				}
			}
			map.displayGrid();
		}

	}

}
	