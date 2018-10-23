	
	import java.util.List;
	import java.util.ArrayList;
	 
	public class Game {
	    Player newPlayer;
	    public List<Player> listOfPlayers = new ArrayList<Player>();
	 
	    int scoreToWin;
	 
	    public Game(int scoreToWin) {
	        this.scoreToWin = scoreToWin;
	    } // checked
	 
	    public String addPlayers(int i) {
	        newPlayer = new Player((i + 1));
	        listOfPlayers.add(newPlayer);
	        return "" + newPlayer.getName();
	    } // checked
	 
	    public String toString() {
	        return "" + newPlayer.getName() + "'s total score: " + newPlayer.getTotalScore(); // checked
	 
	    } // checked
	}