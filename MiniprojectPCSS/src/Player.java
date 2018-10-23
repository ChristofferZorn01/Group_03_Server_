	
	public class Player { // checked
	 
	    private int playerNumber;
	    private int totalScore = 100;
	 
	    Player(int playerNumber) {
	        this.playerNumber = playerNumber;
	    }
	 
	    public int getName() {
	        return playerNumber;
	    }
	 
	    void changeScore(int roundScore) {
	        totalScore += roundScore;
	    }
	 
	    int getTotalScore() {
	        return totalScore;
	    }
	 
	}