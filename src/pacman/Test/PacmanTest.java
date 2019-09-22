package pacman.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.NoPathException;
import pacman.Cell;
import pacman.GameLogic;
import pacman.Monster;
import pacman.PacmanMap;
import pacman.Player;

public class PacmanTest {

	PacmanMap map;
	Monster monster;
	ArrayList<Cell> cells;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {

		map = new PacmanMap();
		map.initialiseTestMap();
		cells = new ArrayList<Cell>();
		
		Cell startingCell = map.getCell(5, 1);
		Player player1 = new Player(1,startingCell);
		
		Cell startingCell2 = map.getCell(9, 9);
		Player player2 = new Player(2,startingCell2);
		
		Cell monsterCell = map.getCell(5, 5);
		monster = new Monster(1,monsterCell);
		
		map.addPlayer(player1);
		map.addPlayer(player2);
		map.addMonster(monster);
		cells.add(player1.getCurrentCell());
		cells.add(player2.getCurrentCell());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBestDirection() throws NoPathException {
		String path = GameLogic.getBestDirection(monster.getCurrentCell() , cells, "", 10, map.getGrid());
		assertEquals('L', path.charAt(0));
	}
	
	@Test (expected=NoPathException.class)
	public void testBestDirection1() throws NoPathException {
		String path = GameLogic.getBestDirection(monster.getCurrentCell() , cells, "", 2, map.getGrid());
	}

}
