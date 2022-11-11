package snake_and_ladder;

public class Cell {
	public int position;
	public Jump jump;
	public Cell(int position) {
		this.position = position;
		jump=new Jump(position,position);
	}

	public Jump getJump() {
		return jump;
	}

	public void setJump(Jump jump) {
		this.jump = jump;
	}
}
