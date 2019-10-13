package pacman.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.InvalidPlayerPositionException;
import pacman.Cell;
import pacman.GameLogic;
import pacman.Monster;
import pacman.PacmanMap;
import pacman.Player;

public class GameLogicPlayerTest {
	PacmanMap map;
	ArrayList<Cell> cells;
	Monster monster;
	Player player1;
	Player player2;
	Cell startingCell;
	Cell startingCell2;
	Cell monsterCell;
	
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
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void GetNewPosition_LegalUpMovement_True() throws InvalidPlayerPositionException {
		startingCell = map.getCell(2, 1);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		Cell newPos = GameLogic.getNewPosition(player1, "U", map.getGrid());
		
		assertEquals(1, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
	
	@Test
	public void GetNewPosition_LegalDownMovement_True() throws InvalidPlayerPositionException {
		startingCell = map.getCell(1, 1);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		Cell newPos = GameLogic.getNewPosition(player1, "D", map.getGrid());
		
		assertEquals(1, newPos.getCol());
		assertEquals(2, newPos.getRow());
	}
	
	@Test
	public void GetNewPosition_LegalRightMovement_True() throws InvalidPlayerPositionException {
		startingCell = map.getCell(1, 1);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		Cell newPos = GameLogic.getNewPosition(player1, "R", map.getGrid());
		
		assertEquals(2, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
	
	@Test
	public void GetNewPosition_LegalLeftMovement_Exception() throws InvalidPlayerPositionException {
		startingCell = map.getCell(1, 2);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		Cell newPos = GameLogic.getNewPosition(player1, "L", map.getGrid());
		
		assertEquals(1, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
	
	@Test(expected = InvalidPlayerPositionException.class)
	public void GetNewPosition_IllegalWallMovement_Exception() throws InvalidPlayerPositionException {
		startingCell = map.getCell(1, 1);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		Cell newPos = GameLogic.getNewPosition(player1, "L", map.getGrid());
	}
	
	@Test(expected = InvalidPlayerPositionException.class)
	public void GetNewPosition_IllegalPlayerToPlayerMovement_Exception() throws InvalidPlayerPositionException {
		startingCell = map.getCell(1, 1);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		
		startingCell2 = map.getCell(1, 2);
		player2 = new Player(2,startingCell2);
		map.addPlayer(player2);
		
		Cell newPos = GameLogic.getNewPosition(player2, "L", map.getGrid());
	}
	
	@Test(expected = InvalidPlayerPositionException.class)
	public void GetNewPosition_IllegalPlayerToMonsterMovement_Exception() throws InvalidPlayerPositionException {
		startingCell = map.getCell(1, 1);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		
		monsterCell = map.getCell(1, 2);
		monster = new Monster(1,monsterCell);
		map.addMovableOccupant(monster);
		
		Cell newPos = GameLogic.getNewPosition(player1, "R", map.getGrid());
	}
	@Test
	public void GetNewPosition_LegalLeftPortalMovement_True() throws InvalidPlayerPositionException {
		startingCell = map.getCell(5, 1);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		Cell newPos = GameLogic.getNewPosition(player1, "L", map.getGrid());
		
		assertEquals(10, newPos.getCol());
		assertEquals(5, newPos.getRow());
	}
	
	@Test
	public void GetNewPosition_LegalRightPortalMovement_True() throws InvalidPlayerPositionException {
		startingCell = map.getCell(5, 9);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		Cell newPos = GameLogic.getNewPosition(player1, "R", map.getGrid());
		
		assertEquals(1, newPos.getCol());
		assertEquals(5, newPos.getRow());
	}
	
	@Test
	public void GetNewPosition_LegalUpPortalMovement_True() throws InvalidPlayerPositionException {
		startingCell = map.getCell(1, 5);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		Cell newPos = GameLogic.getNewPosition(player1, "U", map.getGrid());
		
		assertEquals(5, newPos.getCol());
		assertEquals(10, newPos.getRow());
	}
	
	@Test
	public void GetNewPosition_LegalDownPortalMovement_True() throws InvalidPlayerPositionException {
		startingCell = map.getCell(9, 5);
		player1 = new Player(1,startingCell);
		map.addPlayer(player1);
		Cell newPos = GameLogic.getNewPosition(player1, "U", map.getGrid());
		
		assertEquals(5, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
}
