import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		boolean connected = true;
		Player player;
		List<Player> listOfPlayers = new ArrayList<Player>();
		Dice dice;
		int numberOfPlayers;

		System.out.println("Put in number of players:");
		numberOfPlayers = sc.nextInt();
		System.out.println("There are " + numberOfPlayers + " players. Press '0' to roll.");
		int num = sc.nextInt();

		while (connected) {

			// Adding player to a list with name "Player no. 1" and so on...
			for (int i = 0; i < numberOfPlayers; i++) {
				player = new Player((i + 1)); // Giving player a number, which starts at 1. Starting with 0 points.
				listOfPlayers.add(player);// Adding the new player to the list listOfPlayers
			}

			if (num == 0) {
				dice = new Dice(numberOfPlayers);
				for (int i = 0; i < numberOfPlayers; i++) {
					dice.roll();
					System.out.println(listOfPlayers.get(i).getName() + " rolled a " + dice.getRollsFromList(i));
				}

				
				
				
				outerloop: for (int i = 0; i < numberOfPlayers; i++) {
					int obj = Collections.max(dice.listWithRolls);
					if (dice.getRollsFromList(i) == obj) {

						for (int j = i + 1; j < numberOfPlayers; j++) {
							if (dice.getRollsFromList(i) != dice.getRollsFromList(j) && j == (numberOfPlayers - 1)) {
								System.out.println((i + 1) + " rullede max som den eneste. Får 1 point.");
								listOfPlayers.get(i).addScore();
							}

							else if (dice.getRollsFromList(i) == dice.getRollsFromList(j)) {
								System.out.println(
										"Max fundet: " + obj + ". \nMinuspoint til " + listOfPlayers.get(i).getName()
												+ " og " + listOfPlayers.get(j).getName() + " (hvis ikke total = 0.");
								listOfPlayers.get(i).subScore();
								listOfPlayers.get(j).subScore();
								break outerloop;
							}
						}
					}
				}
				System.out.print("\nScoring list:\n");
				for (int i = 0; i < numberOfPlayers; i++) {
					System.out.print(listOfPlayers.get(i).getName() + " - total score: "
							+ listOfPlayers.get(i).getTotalScore() + "\n");
			}

			
			} else if(num!=0) {
				System.out.println("Invalid input.");
			}
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
