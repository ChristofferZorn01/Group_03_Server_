
public class Lobby extends Server {
	
	private static int boardScore;
	static int diceRoll;
	static int diceSize;
	private static boolean playerReady;
	private static int playerScore;
	private static int numberOfPlayers;
	private static String clientName;
	
//	numberOfPlayers = Server.getNumberOfClients();
	
	newDiceTest dice = new newDiceTest(numberOfPlayers);
	
	private boolean checkIfReady() {
		if(playerReady == false) {
			return false;
		} else {
			return true;
		}
	}
	
//	public Lobby() {
//		
//	}
//	
//	public Lobby(String clientName, int playerScore) {
//		this.clientName = clientName;
//		this.playerScore = playerScore;
//	}

	
	// !!!!!!!!!! The following functions probably belong in a Board class, not this class !!!!!!!!!!!! //
	public void setDiceSize() {
		diceSize = dice.getDiceSize();
	}
	
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
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

