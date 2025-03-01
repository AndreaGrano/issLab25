package conway;

public class Grid {
	private int x;
	private int y;
	private Cell[][] grid;
	
	public Grid(int x, int y) {
		this.x = x;
		this.y = y;
		this.grid = new Cell[x][y];
		
		int i,j;
		for(i = 0; i < x; i++) {
			for(j = 0; j < y; j++) {
				this.grid[i][j] = new Cell();
			}
		}
	}
	
	public int getCellState(int x, int y) {
		return this.grid[x][y].getState();
	}
	
	public void switchCellState(int x, int y) {
		this.grid[x][y].switchState();
	}
	
	public void reset() {
		int i, j;
		
		for(i = 0; i < x; i++) {
			for(j = 0; j < y; j++) {
				grid[i][j] = new Cell();
			}
		}
	}
}
