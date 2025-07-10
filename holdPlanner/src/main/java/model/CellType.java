package main.java.model;

public enum CellType {
	HOME("H"), IOPORT("I"), SLOT1("A"), SLOT2("B"), SLOT3("C"), SLOT4("D"), NONE("-");
	
	private String code;
	private CellType(String code) {
		this.code = code;
	}
	
	public static CellType fromCode(String code) {
		return CellType.valueOf(code);
	}
	
	@Override
	public String toString() {
		return code;
	}
}
