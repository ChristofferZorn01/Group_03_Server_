
public class Player { // checked

	private int playerNumber;
	private int totalScore = 1;

	Player(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public int getName() {
		return playerNumber;
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