

// Class for testing Dice class

	class TestDice {
	
		// Setting the number of players
		public static int players = 10;
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	
			System.out.println(players + " players have entered the game");
	
			// For -loop: Making multiple dice. Will make as many dice as there are players.
			for (int i = 1; i <= players; i++) {
				Dice dice = new Dice(players);
				System.out.println("Player number " + i + " rolled a " + dice.toString());
			}
	
		}
	}