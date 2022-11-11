package snake_and_ladder;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Board {
	private Cell[][] cells;
	private List<Jump> jumps;
	public final int rowSize=10;
	public final int colSize=10;
	private final int snakeCount=5;
	private final int ladderCount=5;

	public Board(){
		jumps=new ArrayList<Jump>();
		initializeBoard();
	}

	private void initializeBoard() {
		initializeCells();
	}
	private void initializeCells(){
		cells =new Cell[rowSize][colSize];
		for(int i=0;i<rowSize;i++){
			for(int j=0;j<colSize;j++){
				int position=getPositionByIndexes(i,j);
				cells[i][j]=new Cell(position);
			}
		}
		setJumps(snakeCount, ladderCount);
	}

	private void setJumps(int snakeCount, int ladderCount) {
		//setSnakes
		var set=new HashSet<Integer>();
		for(int i=1;i<=snakeCount;i++){
			var jump=getJump(JumpType.SNAKE,set);
			var cell= getCellByPosition(jump.start);
			cell.setJump(jump);
			jumps.add(jump);
		}

		//set ladders
		for(int i=1;i<=ladderCount;i++){
			Jump jump=getJump(JumpType.LADDER,set);
			var cell= getCellByPosition(jump.start);
			cell.setJump(jump);
			jumps.add(jump);
		}
	}

	private Jump getJump(JumpType snack, HashSet<Integer> set) {
		if(snack == JumpType.SNAKE){
			Random random= new SecureRandom();
			int start=-1, end=-1;
			while(true){
				int i1=random.nextInt(rowSize);
				int j1=random.nextInt(colSize);
				start=getPositionByIndexes(i1,j1);
				int i2=random.nextInt(rowSize);
				int j2=random.nextInt(colSize);
				end=getPositionByIndexes(i2,j2);
				if(!isFeasibleSnake(set, start, end)){
					continue;
				}
				break;
			}
			set.add(start);
			set.add(end);
			return new Jump(start,end);
		}

		Random random= new SecureRandom();
		int start=-1, end=-1;
		while(true){
			int i1=random.nextInt(rowSize);
			int j1=random.nextInt(colSize);
			start=getPositionByIndexes(i1,j1);
			int i2=random.nextInt(rowSize);
			int j2=random.nextInt(colSize);
			end=getPositionByIndexes(i2,j2);
			if(!isFeasibleLadder(set, start, end)){
				continue;
			}
			break;
		}
		set.add(start);
		set.add(end);
		return new Jump(start,end);

	}

	private static boolean isFeasibleLadder(Set<Integer> set, int start, int end) {
		return !((start >end) || (start == 0) || set.contains(start) || set.contains(end));
	}

	private boolean isFeasibleSnake(HashSet<Integer> set, int start, int end) {
		return !((start < end) || (start == (colSize * rowSize)) || set.contains(start) || set.contains(end));
	}

	private int getPositionByIndexes(int i, int j){
		if((i % 2) == 0){
			return (i * colSize) + j + 1;
		}
		return (i * colSize) + (colSize - j);
	}
	public Cell getCellByPosition(int position){
		int i=(position-1)/colSize;
		if((i % 2) == 0){
			int j=(position-1)%colSize;
			return cells[i][j];
		}
		int j=colSize-((position-1)%colSize)-1;
		return cells[i][j];
	}

	public Cell[][] getCells() {
		return cells;
	}

	public List<Jump> getJumps() {
		return jumps;
	}
}
