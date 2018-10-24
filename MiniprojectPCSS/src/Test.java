import java.util.Collections;
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

		Object obj = Collections.max(dice.listWithRolls);
		System.out.println(obj);

		
		
		
		// TODO: Compare and set scores
		if (game.listOfPlayers.size() == numberOfPlayers) {
			for (int i = numberOfPlayers - 1; i >= 0; i--) {
				int com1 = dice.listWithRolls.get(i);
				for (int j = i - 1; j >= 0; j--) {
					int com2 = dice.listWithRolls.get(j);
					
					if (com1 == com2 && com1 == Collections.max(dice.listWithRolls)) {
						System.out.println(game.listOfPlayers.get(i).getName() + " got max");
						break;
					} else if (com1 == com2 && com2 == Collections.max(dice.listWithRolls)) {
						System.out.println(game.listOfPlayers.get(i).getName() + " got max");
						break;
					}
					break;

				}
				break;
				// }
			}
		}
	}
}
