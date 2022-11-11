package snake_and_ladder;

public class Player {
	private int id;
	private int currentPosition;

	public Player(int id){
		this.id=id;
		currentPosition=-1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
}
