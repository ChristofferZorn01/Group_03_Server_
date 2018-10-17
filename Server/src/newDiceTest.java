// This class was created since Lobby was not able to access the other Dice Class, as it was in another folder.
// This one seems to be working.

import java.util.Random;
public class newDiceTest extends Lobby {


	// Defining...
	private int sides; // Number of sides on the die

	// Creating new random object, to create a random roll
	Random random = new Random();

	// Constructor, that takes in the number of players
	public newDiceTest(int players) {
		// There will always be one more side on the die than there are players
		sides = players + 1;
	}

	public int getDiceSize() {
		return this.sides;
	}
	// Rolling the die
	public int roll() {

		// Value for face. Take in sides as argument, which sets the max. 1 is min.
		int faceValue = random.nextInt(sides) + 1;
		return faceValue;
	}

	// String to print
	public String toString() {
		return "" + roll();

	}

}
