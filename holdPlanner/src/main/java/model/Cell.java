package main.java.model;

public class Cell {
	private CellType type;
	
	public Cell(CellType type) {
		this.type = type;
	}
	
	public Cell(String code) {
		this.type = CellType.fromCode(code);
	}
	
	public Cell() {
		
	}

	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type.toString();
	}
}
