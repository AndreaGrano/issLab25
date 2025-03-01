package conway;

public class Cell {
	private int state;
	
	public Cell() {
		this.state = 0;
	}

	public int getState() {
		return state;
	}

	public void switchState() {
		if(state == 0)
			state = 1;
		else
			state = 0;
	}
}
