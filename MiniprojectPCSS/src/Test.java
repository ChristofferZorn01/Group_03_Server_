import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// This is the 'game', that controls the scores for each  player, at rolls the die. 
public class Test {

	public static void main(String[] args) throws InterruptedException {

		// Defining (and initializing)...
		Scanner sc = new Scanner(System.in);
		boolean connected = true;
		Player player;
		List<Player> listOfPlayers = new ArrayList<Player>(); // The list, which holds the objects Player
		Dice dice;
		int numberOfPlayers; // How many players are playing?

		// This is for testing the program. Using the scanner to read how many players.
		System.out.println("Put in number of players:");
		numberOfPlayers = sc.nextInt();
		System.out.println("There are " + numberOfPlayers + " players. Press '0' to roll.");
		int num = sc.nextInt();

		// Adding player to a list with name "Player no. 1" and so on...
		for (int i = 0; i < numberOfPlayers; i++) {
			player = new Player((i + 1)); // Giving player a number, which starts at 1. Starting with 0 points.
			listOfPlayers.add(player);// Adding the new player to the list listOfPlayers
		}

		// while-loop, so scanner doesn't close
		while (connected) {

			// When pressing 0, you roll. In bottom - else: "Invalid" msg.
			if (num == 0) {
				dice = new Dice(numberOfPlayers); // Creating a new dice for getting different rolls.

				// Roll once for each player
				for (int i = 0; i < numberOfPlayers; i++) {
					dice.roll();
					System.out.println(listOfPlayers.get(i).getName() + " rolled a " + dice.getRollsFromList(i));
					Thread.sleep(100);
				}

				int max = Collections.max(dice.listWithRolls); // Finding the max of the rolls.

				Thread.sleep(100);
				// This is where the scoring happens
				outerloop: for (int i = 0; i < numberOfPlayers; i++) {

					// if not player i has max, go back and search for next player.
					if (dice.getRollsFromList(i) != max) {
						// System.out.println(listOfPlayers.get(i).getName() + "did not roll the
						// highest");
						Thread.sleep(100);
					}
					// Is roll i max?
					if (dice.getRollsFromList(i) == max) {
						// Checking if others also have max
						for (int j = i + 1; j < numberOfPlayers; j++) {

							// If no one else has max, and will (should) only give point if every player has
							// been checked, hence the j == num...
							if (dice.getRollsFromList(i) != dice.getRollsFromList(j) && j == (numberOfPlayers - 1)) {
								listOfPlayers.get(i).addScore();
								System.out.println((i + 1) + " rolled the highest value: +1 point.");

								break outerloop;
							}

							// If another player also has rolled the max, a point will be subtracted.
							else if (dice.getRollsFromList(i) == dice.getRollsFromList(j)) {
								System.out.println(listOfPlayers.get(i).getName() + " and "
										+ listOfPlayers.get(j).getName() + " both rolled the highest value:" + max
										+ "\n Both lose a point (if not already on 0).");
								listOfPlayers.get(i).subScore();
								listOfPlayers.get(j).subScore();
								break outerloop;
							}

						} // If it's the last player, who has max: 
						if (i == numberOfPlayers - 1) {
							listOfPlayers.get(i).addScore();
							System.out.println((i + 1) + " rolled the highest value: +1 point.");

							break outerloop;
						}
					}

				}
				// Printing out the scoring sheet:
				System.out.print("\nScoring list:\n");
				for (int i = 0; i < numberOfPlayers; i++) {
					System.out.print(listOfPlayers.get(i).getName() + " - total score: "
							+ listOfPlayers.get(i).getTotalScore() + "\n");
				}

			}
			// If Scanner sc read something elses than '0':
			else if (num != 0) {
				System.out.println("Invalid input.");
			}
			// Roll again by pressing 0
			System.out.println("Press '0' to roll");
			num = sc.nextInt();

		}

		sc.close();

		/*
		 * if (dice.getRollsFromList(i) == obj) {
		 * 
		 * for (int j = i + 1; j < numberOfPlayers; j++) { if (dice.getRollsFromList(i)
		 * != dice.getRollsFromList(j) && j == (numberOfPlayers - 1)) {
		 * System.out.println(listOfPlayers.get(i).getName() +
		 * " rullede max som den eneste. Får 1 point.");
		 * listOfPlayers.get(i).addScore(); break outerloop; }
		 * 
		 * else if (dice.getRollsFromList(i) == dice.getRollsFromList(j)) {
		 * System.out.println("Max fundet: " + obj + ". \nMinuspoint til " +
		 * listOfPlayers.get(i).getName() + " og " + listOfPlayers.get(j).getName() +
		 * "."); break outerloop; } } }
		 */
	}

}
