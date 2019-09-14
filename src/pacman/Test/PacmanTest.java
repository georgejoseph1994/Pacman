package pacman.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pacman.Cell;
import pacman.GameLogic;
import pacman.Map;
import pacman.Monster;
import pacman.Player;

public class PacmanTest {

	Map map;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {

		map = new Map();
		map.initialiseTestMap();
		
		Cell startingCell = map.getCell(1, 1);
		Player player1 = new Player(1,startingCell);
		
		Cell startingCell2 = map.getCell(9, 9);
		Player player2 = new Player(2,startingCell2);
		
		Cell monsterCell = map.getCell(5, 5);
		Monster monster = new Monster(1,monsterCell);
		
		map.addPlayer(player1);
		map.addPlayer(player2);
		map.addMonster(monster);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsWall() {
		
	}

}
