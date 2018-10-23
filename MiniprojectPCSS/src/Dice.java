	
	// DO NOT TOUCH
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Random;
	 
	public class Dice {
	    // Defining...
	    private int sides = 6; // Number of sides on the die
	    public int faceValue;
	 
	    // Creating new random object, to create a random roll
	    Random random = new Random();
	   
	    public List<Dice> listWithRolls = new ArrayList<Dice>();
	 
	    // Constructor, that takes in the number of players
	    public Dice(int players) {
	        // There will always be one more side on the die than there are players
	        sides = players + 1;
	    }
	 
	    // Rolling the die
	    public int roll() {
	        // Value for face. Take in sides as argument, which sets the max. 1 is min.
	    	this.faceValue = faceValue;
	        faceValue = random.nextInt(sides) + 1;
	        System.out.println("You rolled " + faceValue);
	        return faceValue;
	    }
	 
	    // String to print
	    public String toString() {
	        return roll();
	    }
	}
