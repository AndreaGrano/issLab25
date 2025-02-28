package conway;


/*
 * Il core di game of life
 * Non ha dipendenze da dispositivi di input/output
 * Non ha regole di controllo del gioco 
 */

public class Life {
    //La struttura
    private int rows=0;
    private int cols=0;
//    private static int[][] grid;
//    private static int[][] nextGrid;
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
//        grid     = new int[rows][cols];
//        nextGrid = new int[rows][cols];   
        //CommUtils.outyellow("Life | initializeGrids done");
    	
    	this.grid = new Grid(this.rows, this.cols);
    	this.nextGrid = new Grid(this.rows, this.cols);
    }

    protected void resetGrids() {
//         for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                grid[i][j]     = 0;
//                //setCellState(   i,   j, false );
//                //outdev.setCellColor(  i,  j, grid[i][j] );
//                nextGrid[i][j] = 0;
//            }
//        }
        //CommUtils.outyellow("Life | initGrids done");
    	
    	this.grid.reset();
    	this.nextGrid.reset();
    }


    protected int countNeighboursLive(int row, int col) {
        int count = 0;
        if (row-1 >= 0) {
//            if (grid[row-1][col] == 1) count++;
        	if(grid.getCellState(row - 1, col) == 1) count++;
        }
        if (row-1 >= 0 && col-1 >= 0) {
//            if (grid[row-1][col-1] == 1) count++;
        	if(grid.getCellState(row - 1, col - 1) == 1) count++;
        }
        if (row-1 >= 0 && col+1 < cols) {
//            if (grid[row-1][col+1] == 1) count++;
        	if(grid.getCellState(row - 1, col + 1)== 1) count++;
        }
        if (col-1 >= 0) {
//            if (grid[row][col-1] == 1) count++;
        	if(grid.getCellState(row, col - 1) == 1) count++;
        }
        if (col+1 < cols) {
//            if (grid[row][col+1] == 1) count++;
        	if(grid.getCellState(row, col + 1) == 1) count++;
        }
        if (row+1 < rows) {
//            if (grid[row+1][col] == 1) count++;
        	if(grid.getCellState(row + 1, col) == 1) count++;
        }
        if (row+1 < rows && col-1 >= 0) {
//            if (grid[row+1][col-1] == 1) count++;
        	if(grid.getCellState(row + 1, col - 1) == 1) count++;
        }
        if (row+1 < rows && col+1 < cols) {
//            if (grid[row+1][col+1] == 1) count++;
        	if(grid.getCellState(row + 1, col + 1) == 1) count++;
        }
        
        return count;
    }



    protected void computeNextGen( IOutDev outdev ) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int n = countNeighboursLive(i,j);
                applyRules(i, j, n);
//                outdev.displayCell( ""+grid[i][j] );
                outdev.displayCell("" + grid.getCellState(i, j));
            }
            outdev.displayCell("\n");  //Va tolta nel caso della GUI?
        }
        copyAndResetGrid( outdev );
        outdev.displayCell("\n");
    }

    protected void copyAndResetGrid( IOutDev outdev ) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
//                grid[i][j] = nextGrid[i][j];
            	grid.setCellState(i, j, nextGrid.getCellState(i, j));
                //outdev.displayCell( ""+grid[i][j] );
            }
        }
        nextGrid.reset();
    }

    protected void applyRules(int row, int col, int numNeighbours) {
        //int numNeighbors = countNeighborsLive(row, col);
        //CELLA VIVA
//        if (grid[row][col] == 1) {
//            if (numNeighbors < 2) { //muore per isolamento
//                nextGrid[row][col] = 0;
//            } else if (numNeighbors == 2 || numNeighbors == 3) { //sopravvive
//                nextGrid[row][col] = 1;
//            } else if (numNeighbors > 3) { //muore per sovrappopolazione
//                nextGrid[row][col] = 0;
//            }
//        }
        //CELLA MORTA
//        else if (grid[row][col] == 0) {
//            if (numNeighbors == 3) { //riproduzione
//                nextGrid[row][col] = 1;
//            }
//        }
        //CommUtils.outgreen("Life applyRules " + nextGrid   );
    	
    	if(grid.getCellState(row, col) == 1) {
    		if(numNeighbours == 2 || numNeighbours == 3) { //sopravvive
    			nextGrid.setCellState(row, col, 1);
    		} else { //muore
    			nextGrid.setCellState(row, col, 0);
    		}
    	} else if(grid.getCellState(row, col) == 0) {
    		if(numNeighbours == 3) { //riproduzione
    			nextGrid.setCellState(row, col, 1);
    		}
    	}
    }

    public void switchCellState(int i, int j){
//        if( grid[i][j] == 0) grid[i][j] = 1;       
//        else if( grid[i][j] == 1) grid[i][j] = 0; 
    	if(grid.getCellState(i, j) == 0) grid.setCellState(i, j, 1);
    	else if(grid.getCellState(i, j) == 1) grid.setCellState(i, j, 0);
    }

    public  int getCellState( int i, int j  ) {
//        return   grid[i][j];
    	return grid.getCellState(i, j);
    }
 


}
