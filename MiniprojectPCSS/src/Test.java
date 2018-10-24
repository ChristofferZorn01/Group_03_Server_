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

		outerloop: if (dice.getListRollSize() == numberOfPlayers) {
			for (int i = 0; i < numberOfPlayers; i++) {
				int com1 = dice.listWithRolls.get(i);
				for (int j = i + 1; j < numberOfPlayers; j++) {
					int com2 = dice.listWithRolls.get(j);
// THIS AREA - from here 
					if (com1 == com2) {
						if (com1 == Collections.max(dice.listWithRolls)
								|| com2 == Collections.max(dice.listWithRolls)) {
							System.out.println(game.listOfPlayers.get(i).getName() + " and "
									+ game.listOfPlayers.get(j).getName() + " got max. Subtract one point from each");
							break outerloop;
						}
					}
// to here - WORKS 
					// The problem is, that space 0 will be compared with 1 and 2, and then space 1 will be compared with 2. We need for if 2 is equals to or max. I'm going home now. Will be back around 19:00. 
					if (com1 != com2) {
						if (com1 == Collections.max(dice.listWithRolls)) {
							System.out
									.println(game.listOfPlayers.get(i).getName() + " rolled the highest. Gets 1 point");
							break outerloop;
						}
						if (com2 == Collections.max(dice.listWithRolls)) {
							System.out
									.println(game.listOfPlayers.get(j).getName() + " rolled the highest. Gets 1 point");
							break outerloop;
						}
						

					}

				}

			}
		}

	}
}
