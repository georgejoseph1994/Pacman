package pacman;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static void main(String args[]) throws InvalidPlayerPositionException {

		PacmanMap pacmanMap = new PacmanMap();
		pacmanMap.initialiseTestMap();
		ArrayList<Player> players = new ArrayList<Player>();
		Cell startingCell = pacmanMap.getCell(1, 1);
		Player player1 = new Player(1,startingCell);
		players.add( player1 );

		Cell startingCell2 = pacmanMap.getCell(9, 9);
		Player player2 = new Player(2,startingCell2);
		players.add( player2 );

		Cell monsterCell = pacmanMap.getCell(5, 5);
		Monster monster = new Monster(1,monsterCell);
		
		pacmanMap.addPlayer(player1);
		pacmanMap.addPlayer(player2);
		pacmanMap.addMonster(monster);
		
		Scanner sc =new Scanner(System.in);
		String input;
		pacmanMap.displayGrid();
		while(true) {
			input = sc.next();
			switch (input) {
				case "w":{
					pacmanMap.movePlayer(player1, "U");
					pacmanMap.moveMonster(monster, players);
					break;
				}case "a":{
					pacmanMap.movePlayer(player1, "L");
					pacmanMap.moveMonster(monster, players);
					break;
				}case "s":{
					pacmanMap.movePlayer(player1, "D");
					pacmanMap.moveMonster(monster, players);
					break;
				}case "d":{
					pacmanMap.movePlayer(player1, "R");
					pacmanMap.moveMonster(monster, players);
					break;
				}
			}
			pacmanMap.displayGrid();
		}

	}

}
	