
	import java.io.*;
	import java.lang.Thread;
	import java.util.List;
	import java.util.Scanner;
	import java.util.ArrayList;
	import java.util.InputMismatchException;
	 
	public class Game {
	 
	    public List<Player> listOfPlayers = new ArrayList<Player>();
	   
	    // public boolean connected = true;
	    private int scoreToWin = 10;
	    public Dice dice;
	 
	    public Game(int scoreToWin) {
	        this.scoreToWin = scoreToWin;
	    }
	 
	    String addPlayers(int i) {
	        Player newPlayer = new Player("Player no " + i);
	        listOfPlayers.add(newPlayer);
	        return "" + newPlayer.getName() + ". Their total score: " + newPlayer.getTotalScore();
	    }
	   
	    public List<Player> getList() {
	        return listOfPlayers;
	    }
	 
	    public String toString() {
	        return "Note from Game class: There are " + listOfPlayers.size() + " players. ";   
	    }
	}
	
