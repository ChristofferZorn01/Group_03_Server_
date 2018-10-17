
public class Lobby extends Server{
	
	private static int boardScore;
	static int diceRoll;
	private static boolean playerReady;
	private static int playerScore;
	private static String clientName;
	public Lobby() {
		
	}
	
//	public Lobby(String clientName, int playerScore) {
//		this.clientName = clientName;
//		this.playerScore = playerScore;
//	}


	// This should return client's name, followed by their individual score
	public int getBoardScore() {
		return this.playerScore;
	}
	
	
	// Not sure what this one should do, it's taken from the class diagram
	public int getDiceRoll() {
		return diceRoll;
	}
	
	// This function should add the dice value to player's score
	
	// Need help to get access to the Dice class
	private void addDiceToScore(int diceToScore) {
//		int diceValue = Dice.roll();
		this.playerScore += diceToScore;
	}
	
	private boolean checkIfReady() {
		if(playerReady == false) {
			return false;
		} else {
			return true;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

