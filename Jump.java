package snake_and_ladder;

public class Jump {
	public int start;
	public int end;
	public JumpType jumpType;
	public Jump(int start, int end){
		this.start=start;
		this.end=end;
	}

	public JumpType getJumpType() {
		return jumpType;
	}

	public void setJumpType(JumpType jumpType) {
		this.jumpType = jumpType;
	}
}
