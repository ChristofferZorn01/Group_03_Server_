
public class Player { 

	private int playerNumber;
	private int totalScore = 0;

	Player(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getName() {
		return "Player no. " + playerNumber;
	}

	void addToScore() {
		totalScore += 1;
	}

	void subFromScore() {
		totalScore -= 1;
	}

	int getTotalScore() {
		return totalScore;
	}

	void resetScore() {
		totalScore = 0;
	}

	
	
}