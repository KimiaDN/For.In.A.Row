package FourInARow;

public class Game {
	
	public int[][] grid ;
	public int width = 6;
	public int height = 7;
	
	
	public Game(int width, int height) {
		
		this.width = width;
		this.height = height;
		grid = new int[width][height];
		for(int i = 0 ; i < width ; i++) {
			for(int j = 0; j < height ; j++) {
				grid[i][j] = 0;
			}
		}
	}
	
	public void printBoard() {
		
		for(int i = 0 ; i < width ; i++) {
			for(int j = 0; j < height ; j++) {
				
				if(grid[i][j] == 0) {
					System.out.print("*" + " ");
				}
				else if(grid[i][j] == 1) {
					System.out.print("X" + " ");
				}
				else if(grid[i][j] == 2) {
					System.out.print("O" + " ");
				}
			}
			System.out.println();
		}
	}
	
	

}
