package snake_and_ladder;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {
	private Board board;
	private Queue<Player> players;
	private Dice dice;
	private Player winner;

	public Game(){
		initilizeGame();
		initializePlayers();
		initializeDice();
	}

	private void initializeDice() {
		dice=new Dice(1);
	}

	private void initializePlayers() {
		players=new LinkedList<Player>();
		System.out.println("enter number of players : ");
		Scanner sc=new Scanner(System.in, StandardCharsets.UTF_8);
		int n=sc.nextInt();
		if(n==0){
			n=2;
		}
		for(int i=1;i<=n;i++){
			players.offer(new Player(i));
		}
	}

	private void initilizeGame() {
		board=new Board();
	}

	public void start(){
		while(winner==null){
			var player=players.poll();
			System.out.println("Player "+player.getId()+"'s turn. Press 1 to roll the dice ");
			System.out.println("Player "+player.getId()+"'s turn. Press 2 to exit from game");
			Scanner sc=new Scanner(System.in);
			if(sc.nextInt()==1) {
				int result=0;
				do {
					result = dice.rollDice();
					System.out.println("Dice result is "+result);
					int moveTo = movePlayer(player, result);
					if (moveTo == (board.rowSize * board.colSize)) {
						winner = player;
						System.out.println("The winner is player " + player.getId());
						System.out.println("Thanks");
						return;
					}
				} while (result == 6);

				players.offer(player);
			}
			else{
				System.exit(0);
			}
		}
	}

	private int movePlayer(Player player, int result) {
		if((player.getCurrentPosition() == -1) && (result != 6) && (result != 1)){
			System.out.println("your haven't open yet");
			return -1;
		}
		if(player.getCurrentPosition()==-1){
			System.out.println("your moved to "+ 1);
			player.setCurrentPosition(1);
			return 1;
		}
		int currPosition=player.getCurrentPosition();
		int nextPosition=currPosition;
		if((currPosition + result) <= (board.rowSize * board.colSize)){
			nextPosition=currPosition+result;
		}
		var cell=board.getCellByPosition(nextPosition);
		int finalPosition=cell.getJump().end;
		if(finalPosition>nextPosition){
			System.out.println("Jumped up by ladder");
		}
		if(finalPosition<nextPosition){
			System.out.println("oops ! Jumped down by snake");
		}
		player.setCurrentPosition(finalPosition);
		System.out.println("your moved to "+ finalPosition);
		return finalPosition;
	}

	public Player getWinner() {
		return winner;
	}
}
