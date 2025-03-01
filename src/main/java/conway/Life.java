package conway;

/*
 * Il core di game of life
 * Non ha dipendenze da dispositivi di input/output
 * Non ha regole di controllo del gioco 
 */

public class Life {
    //La struttura
    private int rows = 0;
    private int cols = 0;
    private Grid grid;
    private Grid nextGrid;
 
    public Life( int rowsNum, int colsNum ) {
        this.rows   = rowsNum;
        this.cols   = colsNum;
        createGrids();   //crea la struttura a griglia
    }

    public int getRowsNum(){
        return rows;
    }
    public int getColsNum(){
        return cols;
    }

    protected void  createGrids() {
    	this.grid = new Grid(this.rows, this.cols);
    	this.nextGrid = new Grid(this.rows, this.cols);
    }

    protected void resetGrids() {    	
    	this.grid.reset();
    	this.nextGrid.reset();
    }


    protected int countNeighboursLive(int row, int col) {
        int count = 0;
        if (row - 1 >= 0) {
        	if(grid.getCellState(row - 1, col) == 1) count++;
        }
        if (row - 1 >= 0 && col - 1 >= 0) {
        	if(grid.getCellState(row - 1, col - 1) == 1) count++;
        }
        if (row - 1 >= 0 && col + 1 < cols) {
        	if(grid.getCellState(row - 1, col + 1) == 1) count++;
        }
        if (col - 1 >= 0) {
        	if(grid.getCellState(row, col - 1) == 1) count++;
        }
        if (col + 1 < cols) {
        	if(grid.getCellState(row, col + 1) == 1) count++;
        }
        if (row + 1 < rows) {
        	if(grid.getCellState(row + 1, col) == 1) count++;
        }
        if (row + 1 < rows && col-1 >= 0) {
        	if(grid.getCellState(row + 1, col - 1) == 1) count++;
        }
        if (row + 1 < rows && col + 1 < cols) {
        	if(grid.getCellState(row + 1, col + 1) == 1) count++;
        }
        
        return count;
    }

    protected void computeNextGen() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int n = countNeighboursLive(i,j);
                applyRules(i, j, n);
            }
        }
        copyAndResetGrid();
    }

    protected void copyAndResetGrid() {
    	grid.reset();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
            	if(nextGrid.getCellState(i, j) == 1)
            		grid.switchCellState(i, j);
            }
        }
        nextGrid.reset();
    }

    protected void applyRules(int row, int col, int numNeighbours) {
    	//nextGrid è stata resettata, si modifica lo stato delle sole celle vive
        if(grid.getCellState(row, col) == 1) {
    		if(numNeighbours == 2 || numNeighbours == 3) { //sopravvive
    			nextGrid.switchCellState(row, col);
    		}
    	} else if(grid.getCellState(row, col) == 0) {
    		if(numNeighbours == 3) { //riproduzione
    			nextGrid.switchCellState(row, col);
    		}
    	}
    }

    public void switchCellState(int i, int j){
    	grid.switchCellState(i, j);
    }

    public  int getCellState( int i, int j  ) {
    	return grid.getCellState(i, j);
    }
}
