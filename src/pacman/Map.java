package pacman;

public class Map {
	
	final int WALL = 0;
	final int PLAYER1 = 1;
	final int PLAYER2 = 2;
	final int PLAYER3 = 3;
	final int PLAYER4 = 4;
	
	final int MONSTER = 5;
	final int PORTAL = 6;
	
	int grid[][];
	
	public Map(int[][] grid) {
		this.grid = grid;
	}
	
	public void displayGrid() {
		for(int i=0;i<11;i++) {
			for(int j=0;j<11;j++) {
				System.out.print(this.grid[i][j]+" ");
			}
			System.out.println();
		}
	}

}
