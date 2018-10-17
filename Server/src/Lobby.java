import java.io.*;
import java.net.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Lobby extends Server implements Runnable {

	public static int PLAYER1 = 1; // Indicate player 1
	public static int PLAYER2 = 2; // Indicate player 2
	public static int PLAYER3 = 3; // Indicate player 3
	public static int PLAYER4 = 4; // Indicate player 4

	private Socket player1;
	private Socket player2;
	private Socket player3;
	private Socket player4;
	
	private DataInputStream dataInputFromPlayer1;
	private DataOutputStream dataOutputToPlayer1;
	private DataInputStream dataInputFromPlayer2;
	private DataOutputStream dataOutputToPlayer2;
	private DataInputStream dataInputFromPlayer3;
	private DataOutputStream dataOutputToPlayer3;
	private DataInputStream dataInputFromPlayer4;
	private DataOutputStream dataOutputToPlayer4;
	
	// Lobby constructor
	public Lobby(Socket player1, Socket player2, Socket player3, Socket player4) {
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
		this.player4 = player4;
	}
	

	public void run() {
		
		try {
			DataInputStream dataInputFromPlayer1 = new DataInputStream(player1.getInputStream());
			DataInputStream dataInputFromPlayer2 = new DataInputStream(player2.getInputStream());
			DataInputStream dataInputFromPlayer3 = new DataInputStream(player3.getInputStream());
			DataInputStream dataInputFromPlayer4 = new DataInputStream(player4.getInputStream());
			
		} catch(IOException ex) {
			System.err.println(ex);
		}
	}
}