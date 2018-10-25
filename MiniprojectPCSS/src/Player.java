public class Player {

	private int playerNumber;
	public int totalScore;

	Player(int playerNumber) {
		this.playerNumber = playerNumber;
		totalScore= 0;
	}

	public String getName() {
		return "Player no. " + playerNumber;
	}

	public void addScore() {
		totalScore = totalScore+1;
	}

	void subScore() {
		totalScore = totalScore - 1;
		if(totalScore < 0) totalScore = 0;
	}

	public int getTotalScore() {
		return totalScore;
	}

}