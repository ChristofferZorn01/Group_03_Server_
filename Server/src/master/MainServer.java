package master;

import java.net.*;
import java.io.*;
import java.util.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import main.Lobby;

public class MainServer {

	public static final int PORT = 4444;
	public static final String HOST = "localhost";
	public ArrayList<Lobby> serverList = new ArrayList<>();

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		new MainServer().runServer();
	}

	public void runServer() throws IOException, ClassNotFoundException {
		
		// Creating the server

		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Main Server initiated.");

		while (true) {

			Socket socket = serverSocket.accept();

			try {
				
				// Establishing the connection to the Lobby server and then adding it to its list
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectOutputStream.writeObject("Server created successfully.");
				Lobby s = (Lobby) objectInputStream.readObject();
				this.serverList.add(s);
				System.out.println("Server \"" + s.name + "\" added to game list.");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}

/*
public class Server {
	static java.util.Date serverCreate;
	private static int maxClients = 0;
	private static boolean morePlayersCanJoin = true;
	private static String[] playerNames;
	private static String client = "Client ";

	public int getNumberOfClients() {
		return maxClients;
	}

	public static void main(String[] args) {

		// Creating a thread for the server
		new Thread(() -> {
			try {
				ServerSocket server = new ServerSocket(7845);
				serverCreate = new java.util.Date();
				System.out.println("The Server Was Created: " + serverCreate);

				while (morePlayersCanJoin) {
					// Listen to clients
					// Based on Tic tac shit from the book, this should assign each new client to its own socket, named from player1 to player 4
					Socket player1 = server.accept();
					maxClients++;
					InetAddress inetAddress = server.getInetAddress();
					System.out.println("Client " + maxClients + "'s host name is " + inetAddress.getHostName());
					System.out.println("Client " + maxClients + "'s IP Address is " + inetAddress.getHostAddress());

					
					Socket player2 = server.accept();
					maxClients++;
					InetAddress inetAddress2 = server.getInetAddress();
					System.out.println("Client " + maxClients + "'s host name is " + inetAddress2.getHostName());
					System.out.println("Client " + maxClients + "'s IP Address is " + inetAddress2.getHostAddress());
					
					Socket player3 = server.accept();
					maxClients++;
					InetAddress inetAddress3 = server.getInetAddress();
					System.out.println("Client " + maxClients + "'s host name is " + inetAddress3.getHostName());
					System.out.println("Client " + maxClients + "'s IP Address is " + inetAddress3.getHostAddress());
					
					Socket player4 = server.accept();
					maxClients++;
					InetAddress inetAddress4 = server.getInetAddress();
					System.out.println("Client " + maxClients + "'s host name is " + inetAddress4.getHostName());
					System.out.println("Client " + maxClients + "'s IP Address is " + inetAddress4.getHostAddress());
					// adding to the number of clients connected

					// Creating a new thread for each client connecting

					// Using a Switch statement to determine how many threads need to be started, based on player count
					switch (maxClients) {
					case 1: 
						new Thread(new HandleAClient(player1)).start();
						break;
					case 2: 
						new Thread(new HandleAClient(player1, player2)).start();
						break;
					case 3:
						new Thread(new HandleAClient(player1, player2, player3)).start();
						break;
					case 4: 
						new Thread(new HandleAClient(player1, player2, player3, player4)).start();
						break;
					default: 
						break;
					}
	
					
//					if(maxClients >= 4) { 
////					new Thread(new HandleAClient(socket)).start();

					if (maxClients >= 4) {
						morePlayersCanJoin = false;
				}
			}		
			} catch (IOException ex) {
				System.err.println(ex);
			}
		
		}).start();

}
}
class HandleAClient implements Runnable {
	private Socket socket1;
	private Socket socket2;
	private Socket socket3;
	private Socket socket4;

	HandleAClient(Socket socket) {
		this.socket1 = socket;
	}
	
	HandleAClient(Socket socket1, Socket socket2) {
		this.socket1 = socket1;
		this.socket2 = socket2;
	}
	HandleAClient(Socket socket1, Socket socket2, Socket socket3) {
		this.socket1 = socket1;
		this.socket2 = socket2;
		this.socket3 = socket3;
	}

	HandleAClient(Socket socket1, Socket socket2, Socket socket3, Socket socket4) {
		this.socket1 = socket1;
		this.socket2 = socket2;
		this.socket3 = socket3;
		this.socket4 = socket4;
	}
	
	private DataOutputStream toPlayer1;
	private DataInputStream fromPlayer1;
	
	private DataOutputStream toPlayer2;
	private DataInputStream fromPlayer2;
	
	private DataOutputStream toPlayer3;
	private DataInputStream fromPlayer3;
	
	private DataOutputStream toPlayer4;
	private DataInputStream fromPlayer4;
	
	public void run() {
		try {
			// data recieved and sent for each individual client
			DataInputStream fromPLayer1 = new DataInputStream(socket1.getInputStream());
			DataOutputStream toPlayer1 = new DataOutputStream(socket1.getOutputStream());
			
			toPlayer1.writeInt(1);
			
			// data recieved and send
			DataInputStream fromPlayer2 = new DataInputStream(socket2.getInputStream());
			DataOutputStream toPlayer2 = new DataOutputStream(socket2.getOutputStream());
			
			toPlayer2.writeInt(2);
			
			DataInputStream fromPlayer3 = new DataInputStream(socket3.getInputStream());
			DataOutputStream toPlayer3 = new DataOutputStream(socket3.getOutputStream());
			
			toPlayer3.writeInt(3);
			
			DataInputStream fromPlayer4 = new DataInputStream(socket4.getInputStream());
			DataOutputStream toPlayer4 = new DataOutputStream(socket4.getOutputStream());

			toPlayer4.writeInt(4);
			
			while (true) {

			
//				//recive answer from the client - what did it roll?
//				int diceRoll = in.readInt();
//				
//				//Add the diceRoll to the clients existing score
//				int clientScore = 0 + diceRoll;
//				
//				//Send the clientScore back to the client
//				out.writeInt(clientScore);


				// recive answer from the client - what did it roll?
//				int diceRoll = in.readInt();
//
//				// Add the diceRoll to the clients existing score
//				int clientScore = 0 + diceRoll;
//
//				// Send the clientScore back to the client
//				out.writeInt(clientScore);

			}
		}

		catch (IOException e) {
			System.err.println(e);
		}
	}
}

*/

//=========================================================================================
//recive answer from the client - what did it roll?
//int diceRoll = in.readInt();
//
////Add the diceRoll to the clients existing score
//int clientScore = 0 + diceRoll;
//
////Send the clientScore back to the client
//out.writeInt(clientScore);


// recive answer from the client - what did it roll?
//int diceRoll = in.readInt();

//
//This is different than the whole 'Roll highest, move one space ahead' plan
//// Add the diceRoll to the clients existing score
//int clientScore = 0 + diceRoll;
//
//// Send the clientScore back to the client
//out.writeInt(clientScore);

/*Theorectical
 * For Checking after each round
 * int playersRolled = 0;//Number of players that have rolled this round.
 * int allPlayersRolled = number of players; // this int is for checking that all players have have rolled
 * 
 * [When an awnser is recieved] 
 * playersRolled++;//1 is added to the int
 * 
 * [When all players have rolled in 1 round]
 *if (playersRolled == allPlayersRolled)
 *{
 *	diceCheck();//We run the comparison Part of the code
 *	playersRolled = 0; and reset the counter 
 *} 
 *
 *diceCheck()
 *{
 *	for(i = diceSize; i>=1; i--)//We count down from the maxium dice roll possible in the game
 *	{
 *		int count = 0;//to count how many players have this roll
 *		[Go through each players dice roll]//Maybe a second 'if' statement? One for going through the diceRoll, second for going through the players? 
 *		if([players diceRoll] == i)//said player have the current roll checked for
 *			{
 *				count++;//we add to the count
 *			}
 *		//Afterwards, we check if there is any player with this dieRool, and if so, how many
 *		if(count == 1)//Only one with the maximum diceRoll
 *		{
 *			[Said player gets +1 point]
 *			[All clients gets their diceReady Boolean set to 'true']
 *			break;//we break completely out of the diceCheck class
 *		}
 *		else if(count < 1)
 *		{
 *			[Said players gets -1 point]
 *			[All clients gets their diceReady Boolean set to 'true']
 *			break;//we break completely out of the diceCheck class
 *		}
 *
 *	}
 *}
 * THoughts: we need a counter of the player's diceroll, so that when they all have rolled in one round, the server 
 * 	if(clientScore [OF ANY USER] >= maxScore)//When any one of the players
 * 	{
 * 		[Winning Player gets sent 'winCondition = true;']
 * 		[All Players gets sent 'gameEnd = true;']
 * 	}
 * */