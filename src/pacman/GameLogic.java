package pacman;

public class GameLogic {
	
	
	/*
	 * Returns the validity of a players position
	 * @params cell
	 */
	public static boolean isValidPlayerPossition(Cell cell) throws InvalidPlayerPositionException {
		if(cell.getOccupant().getClass().getName() == "pacman.Wall" ) {
			System.out.println("Row"+cell.getRow() + "col"+cell.getCol());
			throw new InvalidPlayerPositionException("Player moved to a wadll");
		}else if(cell.getOccupant().getClass().getName() == "pacman.Player") {
			throw new InvalidPlayerPositionException("Player moved to another players position");
		}
		return true;
	}
	
	public static boolean isWall(Cell cell) {
		if(cell.getOccupant().getClass().getName() == "pacman.Wall" )
			return true;
		return false;
	}
	
	/*
	 * 
	 */
	public static Cell getNewPosition(Player player, String direction, Cell[][] grid) {
		
		int i = player.currentCell.getRow();
		int j = player.currentCell.getCol();
		try {
			switch (direction){
				case "U":{
					if(isValidPlayerPossition(grid[i-1][j])) {
						return grid[i-1][j];
					}
					break;
				}case "D":{
					if(isValidPlayerPossition(grid[i+1][j])) {
						return grid[i+1][j];
					}
					break;
				}case "L":{
					if(isValidPlayerPossition(grid[i][j-1])) {
						return grid[i][j-1];
					}
					break;
				}case "R":{
					if(isValidPlayerPossition(grid[i][j+1])) {
						return grid[i][j+1];
					}
					break;
				}
			}
		}catch(InvalidPlayerPositionException e) {
			System.out.println(e.getMessage());
		}
		return grid[i][j];
	}
	
	public static Cell getNewMonsterPosition(Monster monster, String direction, Cell[][] grid) {
		
		int i = monster.currentCell.getRow();
		int j = monster.currentCell.getCol();
		switch (direction){
			case "U":{
				if(!isWall(grid[i-1][j])) {
					return grid[i-1][j];
				}
				break;
			}case "D":{
				if(!isWall(grid[i+1][j])) {
					return grid[i+1][j];
				}
				break;
			}case "L":{
				if(!isWall(grid[i][j-1])) {
					return grid[i][j-1];
				}
				break;
			}case "R":{
				if(!isWall(grid[i][j+1])) {
					return grid[i][j+1];
				}
				break;
			}
		}
		return grid[i][j];
	}

	public static String getBestDirection(Cell source, Cell dest, String path, int terminateSize, Cell[][] grid) {
//		System.out.println("Current Path:" +path);
		if(source.getCol()==dest.getCol()&&source.getRow()==dest.getRow()) {
//			System.out.println("Path Found:"+path);
			return path;
		}
		if(path.length()>terminateSize) {
//			System.out.println("Limit Path:"+path);
			return null;
		}
		Cell lCell = new Cell(source.getRow(),source.getCol()-1);
		Cell rCell = new Cell(source.getRow(),source.getCol()+1);
		Cell uCell = new Cell(source.getRow()-1,source.getCol());
		Cell dCell = new Cell(source.getRow()+1,source.getCol());
		String lPath, rPath, uPath, dPath;
		int lLength, rLength, uLength, dLength;
		if(isWall(grid[uCell.getRow()][uCell.getCol()])||circularPathMatch(path+"U")) {
//			System.out.println("UWall Path:"+path);
			uPath = null;
		}
		else{
			path+="U";
			uPath = getBestDirection(uCell, dest, path, terminateSize,grid);
			if(uPath==null)
				path = removeLastChar(path);
		}
		if(uPath==null){
			uLength = terminateSize+1;
		}
		else
			uLength = uPath.length();
		if(isWall(grid[lCell.getRow()][lCell.getCol()])||circularPathMatch(path+"L")) {
//			System.out.println("LWall Path:"+path);
			lPath = null;
		}
		else{
			path+="L";
			lPath = getBestDirection(lCell, dest, path, terminateSize,grid);
			if(lPath==null)
				path = removeLastChar(path);
		}
		if(lPath==null){
			lLength = terminateSize+1;
		}
		else
			lLength = lPath.length();
		if(isWall(grid[rCell.getRow()][rCell.getCol()])||circularPathMatch(path+"R")) {
//			System.out.println("RWall Path:"+path);
			rPath=null;
		}
		else{
			path+="R";
			rPath =  getBestDirection(rCell, dest, path, terminateSize,grid);
			if(rPath==null)
				path = removeLastChar(path);
		}
		if(rPath==null) {
			rLength = terminateSize+1;
		}
		else
			rLength = rPath.length();
		if(isWall(grid[dCell.getRow()][dCell.getCol()])||circularPathMatch(path+"D")){
//			System.out.println("DWall Path:"+path);
			dPath = null;
		}
		else{
			path+="D";
			dPath = getBestDirection(dCell, dest, path, terminateSize,grid);
			if(dPath==null)
				path = removeLastChar(path);
		}
		if(dPath==null){
			dLength = terminateSize+1;
		}
		else
			dLength = dPath.length();
		if(rLength==terminateSize+1&&lLength==terminateSize+1&&uLength==terminateSize+1&&dLength==terminateSize+1)
			return null;
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
	
	public static boolean circularPathMatch(String path) {
		if(path.length()>2) {
			if(checkOpposite(path.charAt(path.length()-2), path.charAt(path.length()-1))) {
				return true;
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
//			System.out.println("Circular Path:"+path);
			return true;
		}
		return false;
	}
}
