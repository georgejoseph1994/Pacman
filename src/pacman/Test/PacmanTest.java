package pacman.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.CircularPathException;
import exception.IsWallException;
import exception.NoPathException;
import pacman.Cell;
import pacman.GameLogic;
import pacman.Monster;
import pacman.PacmanMap;
import pacman.Player;

public class PacmanTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBestDirection() throws Exception {
		PacmanMap map = new PacmanMap();
		map.initialiseTestMap();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		Cell startingCell = map.getCell(5, 1);
		Player player1 = new Player(1,startingCell);
		
		Cell startingCell2 = map.getCell(9, 9);
		Player player2 = new Player(2,startingCell2);
		
		Cell monsterCell = map.getCell(5, 5);
		Monster monster = new Monster(1,monsterCell);
		
		map.addPlayer(player1);
		map.addPlayer(player2);
		map.addMonster(monster);
		cells.add(player1.getCurrentCell());
		cells.add(player2.getCurrentCell());
		
		
		String path = GameLogic.getBestDirection(monster.getCurrentCell() , cells, "", 10, map.getGrid());
		assertEquals('L', path.charAt(0));
	}
	
	
	@Test (expected=NoPathException.class)
	public void testBestDirection1() throws Exception {
		PacmanMap map = new PacmanMap();
		map.initialiseTestMap();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		Cell startingCell = map.getCell(1, 5);
		Player player1 = new Player(1,startingCell);
		
		Cell startingCell2 = map.getCell(9, 9);
		Player player2 = new Player(2,startingCell2);
		
		Cell monsterCell = map.getCell(5, 5);
		Monster monster = new Monster(1,monsterCell);
		
		map.addPlayer(player1);
		map.addPlayer(player2);
		map.addMonster(monster);
		cells.add(player1.getCurrentCell());
		cells.add(player2.getCurrentCell());
		
		GameLogic.getBestDirection(monster.getCurrentCell() , cells, "", 2, map.getGrid());
	}

	@Test
	public void testBestDirection2() throws Exception {
		PacmanMap map = new PacmanMap();
		map.initialiseTestMap();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		Cell startingCell = map.getCell(1, 1);
		Player player1 = new Player(1,startingCell);
		
		Cell startingCell2 = map.getCell(5, 9);
		Player player2 = new Player(2,startingCell2);
		
		Cell monsterCell = map.getCell(5, 5);
		Monster monster = new Monster(1,monsterCell);
		
		map.addPlayer(player1);
		map.addPlayer(player2);
		map.addMonster(monster);
		cells.add(player1.getCurrentCell());
		cells.add(player2.getCurrentCell());
		
		
		String path = GameLogic.getBestDirection(monster.getCurrentCell() , cells, "", 10, map.getGrid());
		assertEquals('R', path.charAt(0));
	}
	
	/**
	 * If both players are equidistant from the monster position, it can choose any of the paths identified
	 * @throws Exception
	 */
	@Test
	public void testBestDirectionEqual() throws Exception {

		PacmanMap map = new PacmanMap();
		map.initialiseTestMap();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		Cell startingCell = map.getCell(1, 2);
		Player player1 = new Player(1,startingCell);
		
		Cell startingCell2 = map.getCell(8, 9);
		Player player2 = new Player(2,startingCell2);
		
		Cell monsterCell = map.getCell(5, 5);
		Monster monster = new Monster(1,monsterCell);
		
		map.addPlayer(player1);
		map.addPlayer(player2);
		map.addMonster(monster);
		cells.add(player2.getCurrentCell());
		cells.add(player1.getCurrentCell());

		String path = GameLogic.getBestDirection(monster.getCurrentCell() , cells, "", 10, map.getGrid());
        List<Character> expected = Arrays.asList('U','R');
		assertThat(expected, hasItems(path.charAt(0)));
	}
	

	@Test (expected=CircularPathException.class)
	public void testCheckCircularPath() throws Exception {
		
		GameLogic.circularPathMatch("LUDR");
	}

	@Test (expected=IsWallException.class)
	public void testGetNewMonsterPositionGoingToWall() throws Exception {
		
		PacmanMap map = new PacmanMap();
		map.initialiseTestMap();
		
		Cell monsterCell = map.getCell(4, 5);
		Monster monster = new Monster(1,monsterCell);
		
		map.addMonster(monster);
		
		GameLogic.getNewMonsterPosition(monster, "L", map.getGrid());
	}
	
	@Test
	public void testGetNewMonsterPositionMoving() throws Exception {
		
		PacmanMap map = new PacmanMap();
		map.initialiseTestMap();
		
		Cell monsterCell = map.getCell(4, 5);
		Monster monster = new Monster(1,monsterCell);
		
		map.addMonster(monster);
		
		Cell expected = map.getCell(3, 5);
		Cell actual = GameLogic.getNewMonsterPosition(monster, "U", map.getGrid());
		assertThat(actual, is(expected));
	}
	
	@Test
	public void testIsWall() throws Exception {
		
		PacmanMap map = new PacmanMap();
		map.initialiseTestMap();
		
		GameLogic.isWall(map.getCell(1, 2));
	}
	
	@Test (expected=IsWallException.class)
	public void testIsWallException () throws Exception {
		
		PacmanMap map = new PacmanMap();
		map.initialiseTestMap();
		
		GameLogic.isWall(map.getCell(0, 0));
	}
	

}
