package snake_and_ladder;

import java.security.SecureRandom;
import java.util.Random;

public class Dice {
	private final int diceCount;
	Dice(int diceCount){
		this.diceCount=diceCount;
	}

	public int rollDice(){
		Random random=new SecureRandom();
		int result= random.nextInt(diceCount*6)+1;
		return result;
	}
}
