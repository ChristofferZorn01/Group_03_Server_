/*import java.net.*;
import java.io.*;
import java.util.*;*/
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

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
	
					
					if(maxClients >= 4) { 
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
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
