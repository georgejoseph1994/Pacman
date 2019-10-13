package pacman.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.InvalidPlayerPositionException;
import exception.IsWallException;
import pacman.Cell;
import pacman.GameLogic;
import pacman.Monster;
import pacman.PacmanMap;
import pacman.Player;

public class GameLogicMonsterTest {
	PacmanMap map;
	ArrayList<Cell> cells;
	
	Monster monster;
	Cell monsterCell;
	
	Player player1;
	Cell startingCell;
	
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
	public void getNewMonsterPosition_LegalUpMovement_True() throws InvalidPlayerPositionException, IsWallException {
		monsterCell = map.getCell(2, 1);
		monster = new Monster(1,monsterCell);
		map.addMonster(monster);
		Cell newPos = GameLogic.getNewMonsterPosition(monster, "U", map.getGrid());
		
		assertEquals(1, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
	
	@Test
	public void getNewMonsterPosition_LegalDownMovement_True() throws InvalidPlayerPositionException, IsWallException {
		monsterCell = map.getCell(1, 1);
		monster = new Monster(1,monsterCell);
		map.addMonster(monster);
		Cell newPos = GameLogic.getNewMonsterPosition(monster, "D", map.getGrid());
		
		assertEquals(1, newPos.getCol());
		assertEquals(2, newPos.getRow());
	}
	
	@Test
	public void getNewMonsterPosition_LegalRightMovement_True() throws InvalidPlayerPositionException, IsWallException {
		monsterCell = map.getCell(1, 1);
		monster = new Monster(1,monsterCell);
		map.addMonster(monster);
		Cell newPos = GameLogic.getNewMonsterPosition(monster, "R", map.getGrid());
		
		assertEquals(2, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
	
	@Test
	public void getNewMonsterPosition_LegalLeftMovement_Exception() throws InvalidPlayerPositionException, IsWallException {
		monsterCell = map.getCell(1,2);
		monster = new Monster(1,monsterCell);
		map.addMonster(monster);
		Cell newPos = GameLogic.getNewMonsterPosition(monster, "L", map.getGrid());
		
		assertEquals(1, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
	
	@Test(expected = IsWallException.class)
	public void getNewMonsterPosition_IllegalWallMovement_Exception() throws InvalidPlayerPositionException, IsWallException {
		monsterCell = map.getCell(1,1);
		monster = new Monster(1,monsterCell);
		map.addMonster(monster);
		Cell newPos = GameLogic.getNewMonsterPosition(monster, "L", map.getGrid());
		assertEquals(1, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
	
	@Test
	public void GetNewMonsterPosition_LegalLeftPortalMovement_True() throws IsWallException, InvalidPlayerPositionException {
		monsterCell = map.getCell(5,1);
		monster = new Monster(1,monsterCell);
		map.addMonster(monster);
		Cell newPos = GameLogic.getNewMonsterPosition(monster, "L", map.getGrid());
		assertEquals(10, newPos.getCol());
		assertEquals(5, newPos.getRow());
	}
	
	@Test
	public void GetNewMonsterPosition_LegalRightPortalMovement_True() throws InvalidPlayerPositionException, IsWallException {
		monsterCell = map.getCell(5,10);
		monster = new Monster(1,monsterCell);
		map.addMonster(monster);
		Cell newPos = GameLogic.getNewMonsterPosition(monster, "L", map.getGrid());
		assertEquals(5, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
	
	@Test
	public void GetNewPosition_LegalUpPortalMovement_True() throws InvalidPlayerPositionException, IsWallException {
		monsterCell = map.getCell(1,5);
		monster = new Monster(1,monsterCell);
		map.addMonster(monster);
		Cell newPos = GameLogic.getNewMonsterPosition(monster, "L", map.getGrid());
		assertEquals(5, newPos.getCol());
		assertEquals(10, newPos.getRow());
	}
	
	@Test
	public void GetNewPosition_LegalDownPortalMovement_True() throws InvalidPlayerPositionException, IsWallException {
		monsterCell = map.getCell(5,10);
		monster = new Monster(1,monsterCell);
		map.addMonster(monster);
		Cell newPos = GameLogic.getNewMonsterPosition(monster, "L", map.getGrid());
		assertEquals(5, newPos.getCol());
		assertEquals(1, newPos.getRow());
	}
}
