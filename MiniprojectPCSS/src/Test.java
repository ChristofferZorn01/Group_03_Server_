	import java.util.ArrayList;
	import java.util.List;
	 
	// Class for testing Dice class
	 
	public class Test {
	 
	    public static int players = 3;
	    public static int scoreToWin = 10;
	 
	    public static void main(String[] args) {
	        // TODO Auto-generated method stub
	 
	        System.out.println("Note from Test class: " + players + " players have entered the game");
	 
	        // For -loop: Making multiple dice. Will make as many dice as there are players.
	        Game game = new Game(scoreToWin);
	        Dice dice = new Dice(players);
	 
	        for (int i = 1; i <= players; i++) {
	            System.out.println(game.addPlayers(i) + " rolled a " + dice.toString());
	        }
	        System.out.println(game.toString());
	    }
	}
	 
	/*
	 * for(i = 1; i < game.getList().size(); i++) {
	 *
	 * // Note the initial value of j for(int j = i + 1; j < game.getList().size();
	 * j++) { if (i != j) { System.out.println("Linnea"); } }
	 */