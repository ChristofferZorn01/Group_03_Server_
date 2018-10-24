package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

import master.MainServer;

/**
 * The Class Server.
 */
public class Lobby implements Serializable {
	private static final long serialVersionUID = -21654L;
	public static final int PORT = 4445;
	public static final int MAX_USERS = 4;
	public static final String HOST = "localhost";
	public String name = "Lobby Server";
	static DataInputStream in;
	static DataOutputStream out;
	public int clientNumber = 0;
	public int playerNumberReady = 0;
	public boolean allPlayersReady = false;
	public boolean OddurIsNice = false;
	public String joinedServer = "You joined the Lobby.";
	public String waiting = "Waiting...";
	public String letsGo = "LETS GO";
	public String youArePlayerNumber = "You are player number: ";
	public String pressIfReady = "Press '1' if you are ready";
	public boolean morePlayersCanJoin = true;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Lobby s = new Lobby();
		s.runServer();
	}

	public void runServer() throws IOException, ClassNotFoundException {
		registerServer();
		new Thread(() -> {
			try {
				ServerSocket serverSocket = new ServerSocket(PORT);
				System.out.println("Server waiting for connections...");
				while (morePlayersCanJoin) {
					clientNumber++;
					Socket socket1 = serverSocket.accept();
					
					System.out.println("User " + clientNumber + " is now connected");
					clientNumber++;
					Socket socket2 = serverSocket.accept();
					
					System.out.println("User " + clientNumber + " is now connected");
					clientNumber++;
					Socket socket3 = serverSocket.accept();
				
					System.out.println("User " + clientNumber + " is now connected");
					clientNumber++;
					Socket socket4 = serverSocket.accept();
					
					System.out.println("User " + clientNumber + " is now connected");
					
					if (clientNumber >= 4) {
						System.out.println("Limit Reached");
						morePlayersCanJoin = false;
				}
				//	new ServerThread(socket, socket2).start();

					
					switch (clientNumber) {
					case 2: 
						new Thread(new ServerThread(socket1, socket2)).start();
						break;
					case 3:
						new Thread(new ServerThread(socket1, socket2, socket3)).start();
						break;
					case 4: 
						new Thread(new ServerThread(socket1, socket2, socket3, socket4)).start();
						break;
					default: 
						break;
					}
					
					
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}).start();

	}

	private void registerServer() throws UnknownHostException, IOException, ClassNotFoundException {
		// Method for establishing a connection to the MainServer
		Socket socket = new Socket(MainServer.HOST, MainServer.PORT);

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		objectOutputStream.writeObject(this);

		System.out.println((String) objectInputStream.readObject());
	}
	
	

	public class ServerThread extends Thread {
		public Socket socket1;
		public Socket socket2;
		public Socket socket3;
		public Socket socket4;

		
		
		ServerThread(Socket socket) {
			this.socket1 = socket;
		}

		ServerThread(Socket socket, Socket socket2) {
			this.socket1 = socket;
			this.socket2 = socket2;
		}

		ServerThread(Socket socket1, Socket socket2, Socket socket3) {
			this.socket1 = socket1;
			this.socket2 = socket2;
			this.socket3 = socket3;
		}

		ServerThread(Socket socket1, Socket socket2, Socket socket3, Socket socket4) {
			this.socket1 = socket1;
			this.socket2 = socket2;
			this.socket3 = socket3;
			this.socket4 = socket4;
		}

		public void run() {
			try {

				// This method is for when the client want's to connect to the lobby
				DataInputStream objectInputStream = new DataInputStream(socket1.getInputStream());
				DataOutputStream objectOutputStream = new DataOutputStream(socket1.getOutputStream());
				System.out.println(socket1);
				System.out.println("User 1 is now connected");

				objectOutputStream.writeUTF(joinedServer);
				objectOutputStream.writeUTF(youArePlayerNumber + 1);

				objectOutputStream.writeUTF(pressIfReady);

				DataInputStream objectInputStream2 = new DataInputStream(socket2.getInputStream());
				DataOutputStream objectOutputStream2 = new DataOutputStream(socket2.getOutputStream());
				System.out.println(socket2);
				System.out.println("User 2 is now connected");

				objectOutputStream2.writeUTF(joinedServer);
				objectOutputStream2.writeUTF(youArePlayerNumber + 2);

				objectOutputStream2.writeUTF(pressIfReady);

				DataInputStream objectInputStream3 = new DataInputStream(socket3.getInputStream());
				DataOutputStream objectOutputStream3 = new DataOutputStream(socket3.getOutputStream());
				System.out.println(socket2);
				System.out.println("User 3 is now connected");

				objectOutputStream3.writeUTF(joinedServer);
				objectOutputStream3.writeUTF(youArePlayerNumber + 3);

				objectOutputStream3.writeUTF(pressIfReady);

				DataInputStream objectInputStream4 = new DataInputStream(socket4.getInputStream());
				DataOutputStream objectOutputStream4 = new DataOutputStream(socket4.getOutputStream());
				System.out.println(socket2);
				System.out.println("User 4 is now connected");

				objectOutputStream4.writeUTF(joinedServer);
				objectOutputStream4.writeUTF(youArePlayerNumber + 4);

				objectOutputStream4.writeUTF(pressIfReady);

				if (objectInputStream.readInt() == 1) {
					System.out.println("Player number before increment from user 1: " + playerNumberReady);
					playerNumberReady++;
					System.out.println("Player number after increment from user 1: " + playerNumberReady);
					allPlayersReady = checkIfAllPlayersReady();
					if (allPlayersReady == false) {
						objectOutputStream.writeUTF(waiting);
					}
				}

				if (objectInputStream2.readInt() == 1) {
					System.out.println("Player number before increment from user 2: " + playerNumberReady);
					playerNumberReady++;
					System.out.println("Player number after increment from user 2: " + playerNumberReady);
					allPlayersReady = checkIfAllPlayersReady();
					if (allPlayersReady == true) {
						objectOutputStream.writeUTF(letsGo);
						objectOutputStream2.writeUTF(letsGo);
					}
				}

				if (objectInputStream3.readInt() == 1) {
					System.out.println("Player number before increment from user 2: " + playerNumberReady);
					playerNumberReady++;
					System.out.println("Player number after increment from user 2: " + playerNumberReady);
					allPlayersReady = checkIfAllPlayersReady();
					if (allPlayersReady == true) {
						objectOutputStream.writeUTF(letsGo);
						objectOutputStream2.writeUTF(letsGo);
					}
				}

				if (objectInputStream4.readInt() == 1) {
					System.out.println("Player number before increment from user 2: " + playerNumberReady);
					playerNumberReady++;
					System.out.println("Player number after increment from user 2: " + playerNumberReady);
					allPlayersReady = checkIfAllPlayersReady();
					if (allPlayersReady == true) {
						objectOutputStream.writeUTF(letsGo);
						objectOutputStream2.writeUTF(letsGo);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public boolean checkIfAllPlayersReady() {

			if (playerNumberReady != MAX_USERS) {
				return false;
			} else {
				return true;
			}

		}
	}
}

/*
 * // numberOfPlayers = Server.getNumberOfClients();
 * 
 * newDiceTest dice = new newDiceTest(numberOfPlayers);
 * 
 * private boolean checkIfReady() { if(playerReady == false) { return false; }
 * else { return true; } }
 * 
 * // public Lobby() { // // } // // public Lobby(String clientName, int
 * playerScore) { // this.clientName = clientName; // this.playerScore =
 * playerScore; // }
 * 
 * // !!!!!!!!!! The following functions probably belong in a Board class, not
 * this class !!!!!!!!!!!! // public void setDiceSize() { diceSize =
 * dice.getDiceSize(); }
 * 
 * // This should return client's name, followed by their individual score
 * public int getBoardScore() { return this.playerScore; }
 * 
 * // Not sure what this one should do, it's taken from the class diagram public
 * int getDiceRoll() { return diceRoll; }
 * 
 * // This function should add the dice value to player's score
 * 
 * // Need help to get access to the Dice class private void addDiceToScore(int
 * diceToScore) { // int diceValue = Dice.roll(); this.playerScore +=
 * diceToScore; }
 * 
 * public static void main(String[] args) { // TODO Auto-generated method stub }
 * }
 */