package main.java.Parser;

import main.java.HoldMap.CellType;
import main.java.HoldMap.HoldMap;
import main.java.Exceptions.CellNotFoundException;

public class HoldMapParser {
	public static int[][] toObstacleGrid(HoldMap holdMap) {
		int rowsDim = holdMap.getRowsDim();
		int colsDim = holdMap.getColsDim();
		
		int[][] grid = new int[rowsDim][colsDim];
		
		for(int i = 0; i < rowsDim; i++) {
			try {
				for(int j = 0; j < colsDim; i++) {
					if((holdMap.getCellType(i, j) == CellType.OBSTACLE) || (holdMap.getCellType(i, j) == CellType.SLOT1) || 
							(holdMap.getCellType(i, j) == CellType.SLOT2) || (holdMap.getCellType(i, j) == CellType.SLOT3) ||
							(holdMap.getCellType(i, j) == CellType.SLOT4) || (holdMap.getCellType(i, j) == CellType.SLOT5)) {
						grid[i][j] = 1;
					} else {
						grid[i][j] = 0;
					}
				}
			} catch(CellNotFoundException cnfe) {
				continue;
			}
		}
		
		return grid;
	}
}
