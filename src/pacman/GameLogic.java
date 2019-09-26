package pacman;

import java.util.ArrayList;

import exception.InvalidPlayerPositionException;
import exception.CircularPathException;
import exception.IsWallException;
import exception.NoPathException;

public class GameLogic {
	
	
	/*
	 * Returns the validity of a players position
	 * @params cell
	 */
	public static boolean isValidPlayerPosition(Cell cell) throws InvalidPlayerPositionException {
		if(cell.getOccupant().getClass().getName() == "pacman.Wall" ) {
//			System.out.println("Row"+cell.getRow() + "col"+cell.getCol());
			throw new InvalidPlayerPositionException("Player moved to a wall");
		}else if(cell.getOccupant().getClass().getName() == "pacman.Player") {
			throw new InvalidPlayerPositionException("Player moved to another players position");
		}else if(cell.getOccupant().getClass().getName() == "pacman.Monster") {
			throw new InvalidPlayerPositionException("Player moved to monster position");
		}
		return true;
	}
	
	public static void isWall(Cell cell) throws IsWallException {
		if(cell.getOccupant().getClass().getName() == "pacman.Wall" )
			throw new IsWallException();
	}
	
	/*
	 * 
	 */
	public static Cell getNewPosition(Player player, String direction, Cell[][] grid) throws InvalidPlayerPositionException {
		
		int i = player.currentCell.getRow();
		int j = player.currentCell.getCol();
		try {
			switch (direction){
				case "U":{
					if(grid[i-1][j].getClass().getName() == "pacman.Portal") {
						return ((Portal) grid[i-1][j].getOccupant()).getTarget();
					}
					if(isValidPlayerPosition(grid[i-1][j])) {
						return grid[i-1][j];
					}
					break;
				}case "D":{
					if(grid[i+1][j].getClass().getName() == "pacman.Portal") {
						return ((Portal) grid[i+1][j].getOccupant()).getTarget();
					}
					if(isValidPlayerPosition(grid[i+1][j])) {
						return grid[i+1][j];
					}
					break;
				}case "L":{
					if(grid[i][j-1].getClass().getName() == "pacman.Portal") {
						return ((Portal) grid[i][j-1].getOccupant()).getTarget();
					}
					if(isValidPlayerPosition(grid[i][j-1])) {
						return grid[i][j-1];
					}
					break;
				}case "R":{
					if(grid[i][j+1].getClass().getName() == "pacman.Portal") {
						return ((Portal) grid[i][j+1].getOccupant()).getTarget();
					}
					if(isValidPlayerPosition(grid[i][j+1])) {
						return grid[i][j+1];
					}
					break;
				}
			}
		}catch(InvalidPlayerPositionException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return grid[i][j];
	}
	
	public static Cell getNewMonsterPosition(Monster monster, String direction, Cell[][] grid) throws IsWallException {
		
		int i = monster.currentCell.getRow();
		int j = monster.currentCell.getCol();
		switch (direction){
			case "U":isWall(grid[i-1][j]);
					return grid[i-1][j];
			case "D":isWall(grid[i+1][j]);
					return grid[i+1][j];
			case "L":isWall(grid[i][j-1]);
					return grid[i][j-1];
			case "R":isWall(grid[i][j+1]);
					return grid[i][j+1];
		}
		return grid[i][j];
	}

	public static String getBestDirection(Cell source, ArrayList<Cell> cells, String path, int terminateSize, Cell[][] grid) throws NoPathException {
		for( int i=0; i<cells.size() ; i++ ) {
			if(source.getCol()==cells.get(i).getCol()&&source.getRow()==cells.get(i).getRow()) {
				return path;
			}
		}
		if(path.length()>terminateSize) {
			throw new NoPathException();
		}
		Cell lCell = new Cell(source.getRow(),source.getCol()-1);
		Cell rCell = new Cell(source.getRow(),source.getCol()+1);
		Cell uCell = new Cell(source.getRow()-1,source.getCol());
		Cell dCell = new Cell(source.getRow()+1,source.getCol());
		String lPath, rPath, uPath, dPath;
		int lLength, rLength, uLength, dLength;
		try {
			circularPathMatch(path+"U");
			isWall(grid[uCell.getRow()][uCell.getCol()]);
			uPath=path+"U";
			try {
				uPath = getBestDirection(uCell, cells, uPath, terminateSize,grid);
				uLength = uPath.length();
			} catch(NoPathException ex) {
				uLength = terminateSize+1;
			}
		} catch(CircularPathException | IsWallException ex) {
			uPath = null;
			uLength = terminateSize+1;
		}
		try {
			circularPathMatch(path+"L");
			isWall(grid[lCell.getRow()][lCell.getCol()]);
			lPath=path+"L";
			try {
				lPath = getBestDirection(lCell, cells, lPath, terminateSize,grid);
				lLength = lPath.length();
			} catch(NoPathException ex) {
				lLength = terminateSize+1;
			}
		} catch(CircularPathException | IsWallException ex) {
			lPath = null;
			lLength = terminateSize+1;
		}
		try {
			circularPathMatch(path+"R");
			isWall(grid[rCell.getRow()][rCell.getCol()]);
			rPath=path+"R";
			try {
				rPath =  getBestDirection(rCell, cells, rPath, terminateSize,grid);
				rLength = rPath.length();
			} catch(NoPathException ex) {
				rLength = terminateSize+1;
			}
		} catch(CircularPathException | IsWallException ex) {
			rPath=null;
			rLength = terminateSize+1;
		}
		try {
			circularPathMatch(path+"D");
			isWall(grid[dCell.getRow()][dCell.getCol()]);
			dPath=path+"D";
			try {
				dPath = getBestDirection(dCell, cells, dPath, terminateSize,grid);
				dLength = dPath.length();
			} catch(NoPathException ex) {
				dLength = terminateSize+1;
			}
		} catch(CircularPathException | IsWallException ex) {
			dPath = null;
			dLength = terminateSize+1;
		}
		if(rLength==terminateSize+1&&lLength==terminateSize+1&&uLength==terminateSize+1&&dLength==terminateSize+1)
			throw new NoPathException();
		if(rLength<lLength&&rLength<uLength&&rLength<dLength)
			return rPath;
		else if(lLength<uLength&&lLength<dLength)
			return lPath;
		else if(uLength<dLength)
			return uPath;
		else return dPath;
	}
	
	public static String removeLastChar(String str) {
		if(str.length()>0)
			return  str.substring(0, str.length()-1);
		return null;
	}
	
	public static boolean checkOpposite(char ch1, char ch2) {
		if(ch1=='L' &&ch2=='R')
			return true;
		if(ch1=='R' &&ch2=='L')
			return true;
		if(ch1=='U' &&ch2=='D')
			return true;
		if(ch1=='D' &&ch2=='U')
			return true;
		return false;
	}
	
	public static void circularPathMatch(String path) throws CircularPathException {
		if(path.length()>2) {
			if(checkOpposite(path.charAt(path.length()-2), path.charAt(path.length()-1))) {
				throw new CircularPathException();
			}
		}
		int colChange=0, rowChange = 0; 
		for(int i=0;i<path.length();i++) {
			if(path.charAt(i)=='L')
				colChange--;
			else if(path.charAt(i)=='R')
				colChange++;
			else if(path.charAt(i)=='U')
				rowChange--;
			else if(path.charAt(i)=='D')
				rowChange++;
		}
		if(colChange==0&&rowChange==0) {
			throw new CircularPathException();
		}
	}
}
