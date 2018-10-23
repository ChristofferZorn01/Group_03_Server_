	
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Random;
	 
	public class Dice {
	    // Defining...
	    private int sides; // Number of sides on the die
	    public int faceValue;
	    List<Integer> listWithRolls = new ArrayList<>();
	 
	    // Creating new random object, to create a random roll
	    Random random = new Random();
	 
	    // Constructor, that takes in the number of players
	    public Dice(int players) {
	        // There will always be one more side on the die than there are players
	        sides = players + 1;
	    } // checked
	 
	    public int getDiceSize() {
	        return this.sides;
	    }
	    // Rolling the die
	    public void roll() {
	        // Value for face. Take in sides as argument, which sets the max. 1 is min.
	        faceValue = random.nextInt(sides) + 1;
	        listWithRolls.add(faceValue);
	    } // checked
	 
	    public int getRollsFromList(int i) {
	        return listWithRolls.get(i);
	    } // checked
	 
	    public int getListRollSize() {
	        return listWithRolls.size();
	    }
	}