	
	// Class for testing Dice class
	 
	public class Test {
	 // 
	    public static int players = 3;
	    public static int scoreToWin = 10;
	 
	    public static void main(String[] args) {
	        // TODO Auto-generated method stub
	 
	        System.out.println(players + " players have entered the game");
	 
	        Game game = new Game(players, scoreToWin); // checked
	        Dice dice = new Dice(players); // checked
	 
	        for (int i = 0; i < players; i++) { // checked for roll()
	            dice.roll(); // checked
	            System.out.println("Player no. " + game.addPlayers(i) + " rolled a "
	                    + dice.getRollsFromList(i) + ". Player" + game.toString()); // checked
	 
	            // TODO: COMPARE RESULTS FROM listWithRolls - and then add score!
	 
	        }
	    }
	}