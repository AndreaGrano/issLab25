package main.java.model;

import java.util.ArrayList;

import main.java.model.Exceptions.CellNotFoundException;

public class HoldMap {
	private static HoldMap singleton;
	
	public static HoldMap getHoldMap() {
		if(singleton == null)
			singleton = new HoldMap();
		
		return singleton;
	}
	
	private ArrayList<ArrayList<Cell>> holdMap;
	
	private HoldMap() {
		super();
		
		holdMap = new ArrayList<ArrayList<Cell>>(); 
	}
	
	public void putCell(int x, int y, Cell cell) {
		try {
			holdMap.get(x);
		} catch(IndexOutOfBoundsException iobe) {
			for(int i = holdMap.size(); i < x; i++) {
				holdMap.add(new ArrayList<Cell>());
			}
		}
		holdMap.add(y, new ArrayList<Cell>());
		
		try {
			holdMap.get(x).get(y);
			holdMap.get(x).remove(y);
		} catch(IndexOutOfBoundsException iobe) {
			for(int i = holdMap.get(x).size(); i < y; i++) {
				holdMap.get(x).add(new Cell(CellType.NONE));
			}
		}
		holdMap.get(x).add(y, cell);
	}
	
	public Cell getCell(int x, int y) throws CellNotFoundException {
		Cell cell;
		
		try {
			cell = holdMap.get(x).get(y);
		} catch(IndexOutOfBoundsException iobe) {
			throw new CellNotFoundException();
		}
		
		return cell;
	}
	
	public CellType getCellType(int x, int y) throws CellNotFoundException {
		CellType cellType;
		
		try {
			cellType = holdMap.get(x).get(y).getType();
		} catch(IndexOutOfBoundsException iobe) {
			throw new CellNotFoundException();
		}
		
		return cellType;
	}
	
	public int getRowsDim() {
		return holdMap.size();
	}
	
	public int getColsDim() {
		int size = 0;
		
		for(ArrayList<Cell> row : holdMap) {
			if(size < row.size()) {
				size = row.size();
			}
		}
		
		return size;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(ArrayList<Cell> row : holdMap) {
			sb.append("|");
			for(Cell cell : row) {
				sb.append(cell.toString());
				
				sb.append(", ");
			}
			sb.replace(sb.length() -1, sb.length(), "|");
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
