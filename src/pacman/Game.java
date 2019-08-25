package pacman;

public class Game {
	final static int PLAYER1 = 1;
	final static int PLAYER2 = 2;
	final static int PLAYER3 = 3;
	final static int PLAYER4 = 4;
	
	final static int WALL = 7;
	
	final int MONSTER = 5;
	final int PORTAL = 6;
	
	public static void main(String args[]) {
		int[][] testMap = new int[11][11];
		
		//Creating a test map
		for(int i=0;i<11;i++) {
			for(int j=0;j<11;j++) {
				if(i == 0 || i==10 ||j==0 ||j==10) {
					testMap[i][j] = WALL;
				}
				if(i==1 || i==5 || i==9 || j==1 || j==5 || j==9) {
					
				}else {
					testMap[i][j] = WALL;
				}
			}
		}
		
		Map map= new Map(testMap);
		
		map.displayGrid();
		
	}

}
