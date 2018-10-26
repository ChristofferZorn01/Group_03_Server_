package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// 10:51 Linnea is doing (2) 
// (1) TODO: What happens if three players get the same - can Random in Dice class be made, so only two of the same numbers can be rolled?
// (2) TODO: Go through all variables and methods - are they used? Can they be optimized? 
// Will write // checked at variables and methods, that I have checked. 


// This is the 'game', that controls the scores for each  player, at rolls the die. 
public class Test {

	public static void main(String[] args) throws InterruptedException {

		// Defining (and initializing)...
		Scanner sc = new Scanner(System.in); // checked
		boolean playing = false; // checked
		boolean creating = true;
		Player player;
		List<Player> listOfPlayers = new ArrayList<Player>(); // The list, which holds the objects Player
		Dice dice;
		int numberOfPlayers; // How many players are playing?
		int scoreToWin; // The score, that a player has to reach to win

		// This is for testing the program. Using the scanner to read how many players.
		System.out.println("Put in number of players:");
		numberOfPlayers = sc.nextInt();

		while (creating) {
			// Adding player to a list with name "Player no. 1" and so on...
			if (numberOfPlayers > 2) {

				for (int i = 0; i < numberOfPlayers; i++) {
					player = new Player((i + 1)); // Giving player a number, which starts at 1. Starting with 0 points.
					listOfPlayers.add(player);// Adding the new player to the list listOfPlayers
				}
				break;
			} else if (numberOfPlayers <= 2) {
				System.out.println("You have to be at least 3 players.\nPut in the number of players:");
			}
			numberOfPlayers = sc.nextInt();
		}

		playing = true;
		creating =! true;

		System.out.println(numberOfPlayers
				+ " players has entered the game.\nEnter amount of points to win (when testing choose 1)");
		scoreToWin = sc.nextInt();
		System.out.println("Press '0' to roll.");
		int num = sc.nextInt();

		// while-loop, so scanner doesn't close
		while (playing) {

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

						}
						// If it's the last player, who has max:
						/*
						 * TODO: CAN BE OPTIMIZED, if: for (int i = 0; i < numberOfPlayers; i++) ==> for
						 * (int i = numberOfPlayers; i >=0; i--). And the same for j. Try it out!
						 */
						if (i == numberOfPlayers - 1) {
							listOfPlayers.get(i).addScore();
							System.out.println((i + 1) + " rolled the highest value: +1 point.");

							break outerloop;
						}
					}

				}
				Thread.sleep(1000);
				// Printing out the scoring sheet:
				System.out.println("\n - Scoring list:");
				for (int i = 0; i < numberOfPlayers; i++) {
					System.out.println(
							listOfPlayers.get(i).getName() + " - total score: " + listOfPlayers.get(i).getTotalScore());
					if (listOfPlayers.get(i).getTotalScore() == scoreToWin) {
						System.out
								.println("\n   CONGRATS TO\n  " + listOfPlayers.get(i).getName() + "\n    *YOU WIN*\n");
						playing = false;
						break;
					}
				}

			}
			// If Scanner sc read something else than '0':
			else if (num != 0) {
				System.out.println("Invalid input.");
			}
			if (playing) {
				// Roll again by pressing 0
				System.out.println("\n\nPress '0' to roll");
				num = sc.nextInt();
			}
		}

		// TODO write code, where max score has been reached, and that boolean connected
		// = false; to close Scanner sc

		System.out.print("\nThank you for playing DiceRace\n");
		sc.close();

	}

}
