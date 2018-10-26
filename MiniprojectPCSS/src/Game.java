package src;


import java.util.List;
import java.util.ArrayList;

public class Game {
	int scoreToWin;
	int numberOfPlayers;
	
	Player newPlayer;
	public List<Player> listOfPlayers = new ArrayList<Player>();

	public Game(int numberOfPlayers, int scoreToWin) { // checked
		this.scoreToWin = scoreToWin; //
		this.numberOfPlayers = numberOfPlayers; //
		addPlayers(); //
	}

	public void addPlayers() { // checked
		for (int i = 0; i < numberOfPlayers; i++) { //
			newPlayer = new Player((i + 1)); // Giving player a number, which starts at 1. 
			listOfPlayers.add(newPlayer);//  Adding the new player to the list listOfPlayers
		}
	}

}