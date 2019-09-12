package pacman;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static void main(String args[]) throws InvalidPlayerPositionException {

		Map map = new Map();
		map.initialiseTestMap();
		ArrayList<Player> players = new ArrayList<Player>();
		Cell startingCell = map.getCell(1, 1);
		Player player1 = new Player(1,startingCell);
		players.add( player1 );
		
		Cell startingCell2 = map.getCell(9, 9);
		Player player2 = new Player(2,startingCell2);
		players.add( player2 );
		
		Cell monsterCell = map.getCell(5, 5);
		Monster monster = new Monster(1,monsterCell);
		
		map.addPlayer(player1);
		map.addPlayer(player2);
		map.addMonster(monster);
		
		Scanner sc =new Scanner(System.in);
		String input;
		map.displayGrid();
		while(true) {
			input = sc.next();
			switch (input) {
				case "w":{
					map.movePlayer(player1, "U");
					map.moveMonster(monster, players);
					break;
				}case "a":{
					map.movePlayer(player1, "L");
					map.moveMonster(monster, players);
					break;
				}case "s":{
					map.movePlayer(player1, "D");
					map.moveMonster(monster, players);
					break;
				}case "d":{
					map.movePlayer(player1, "R");
					map.moveMonster(monster, players);
					break;
				}
			}
			map.displayGrid();
		}

	}

}
	