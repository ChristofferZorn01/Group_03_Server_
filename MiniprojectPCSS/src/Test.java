
// Class for testing Dice class

public class Test {
	//
	public static int numberOfPlayers = 3;
	public static int scoreToWin = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(numberOfPlayers + " players have entered the game"); //

		Game game = new Game(numberOfPlayers, scoreToWin); //
		Dice dice = new Dice(numberOfPlayers); //

		for (int i = 0; i < numberOfPlayers; i++) {
			dice.roll();
			System.out.println(game.listOfPlayers.get(i).getName() + " rolled a " + dice.getRollsFromList(i)
					+ ". Total score: " + game.listOfPlayers.get(i).getTotalScore());

		}

		
		
		// TODO: Compare and set scores
		if (game.listOfPlayers.size() == numberOfPlayers) {
			for (int i = numberOfPlayers - 1; i >= 0; i--) {
				for (int j = i - 1; j >= 0; j--) {
					if (dice.listWithRolls.get(i) != dice.listWithRolls.get(j)) {
						System.out.println("same");

						if (dice.listWithRolls.get(i) < dice.listWithRolls.get(j)) {
							System.out.println("break?");
						}
						if (dice.listWithRolls.get(i) > dice.listWithRolls.get(j)) {
							System.out.println("den anden vej");
						}
					} 
					else if (dice.listWithRolls.get(i) == dice.listWithRolls.get(j)) {
						System.out.println("same");

					}

				}
			}

			// TODO: COMPARE RESULTS FROM listWithRolls - and then add score!

		}
	}
}