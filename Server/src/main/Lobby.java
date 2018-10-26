package main;

import java.io.DataInputStream;
import java.util.Random;
import java.io.DataOutputStream;
import java.io.EOFException;
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
	public String joinedServer = "Game Session started!";
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
					DataOutputStream objectOutputStream = new DataOutputStream(socket1.getOutputStream());
					objectOutputStream.writeInt(MAX_USERS - clientNumber);
					
					System.out.println("User " + clientNumber + " is now connected");
					clientNumber++;
					Socket socket2 = serverSocket.accept();
					DataOutputStream objectOutputStream1 = new DataOutputStream(socket2.getOutputStream());
					objectOutputStream1.writeInt(MAX_USERS - clientNumber);
					
					System.out.println("User " + clientNumber + " is now connected");
					clientNumber++;
					Socket socket3 = serverSocket.accept();
					DataOutputStream objectOutputStream2 = new DataOutputStream(socket3.getOutputStream());
					objectOutputStream2.writeInt(MAX_USERS - clientNumber);
					
					System.out.println("User " + clientNumber + " is now connected");
					clientNumber++;
					Socket socket4 = serverSocket.accept();
					DataOutputStream objectOutputStream3 = new DataOutputStream(socket4.getOutputStream());
					System.out.println("User " + clientNumber + " is now connected");
					objectOutputStream3.writeInt(MAX_USERS - clientNumber);
					
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
		
		// Declare the input and output streams
		DataInputStream inputClient1;
		DataOutputStream outputClient1;
		DataInputStream inputClient2;
		DataOutputStream outputClient2;
		DataInputStream inputClient3;
		DataOutputStream outputClient3;
		DataInputStream inputClient4;
		DataOutputStream outputClient4;
		
		public void run() {
			try {

				// This method is for when the client want's to connect to the lobby
				inputClient1 = new DataInputStream(socket1.getInputStream());
				outputClient1 = new DataOutputStream(socket1.getOutputStream());
				System.out.println(socket1);
				System.out.println("User 1 is now connected");

				outputClient1.writeUTF(joinedServer);
				outputClient1.writeUTF(youArePlayerNumber + 1);

				outputClient1.writeUTF(pressIfReady);

				inputClient2 = new DataInputStream(socket2.getInputStream());
				outputClient2 = new DataOutputStream(socket2.getOutputStream());
				System.out.println(socket2);
				System.out.println("User 2 is now connected");

				outputClient2.writeUTF(joinedServer);
				outputClient2.writeUTF(youArePlayerNumber + 2);

				outputClient2.writeUTF(pressIfReady);

				inputClient3 = new DataInputStream(socket3.getInputStream());
				outputClient3 = new DataOutputStream(socket3.getOutputStream());
				System.out.println(socket2);
				System.out.println("User 3 is now connected");

				outputClient3.writeUTF(joinedServer);
				outputClient3.writeUTF(youArePlayerNumber + 3);

				outputClient3.writeUTF(pressIfReady);

				inputClient4 = new DataInputStream(socket4.getInputStream());
				outputClient4 = new DataOutputStream(socket4.getOutputStream());
				System.out.println(socket2);
				System.out.println("User 4 is now connected");

				outputClient4.writeUTF(joinedServer);
				outputClient4.writeUTF(youArePlayerNumber + 4);

				outputClient4.writeUTF(pressIfReady);

				if (inputClient1.readInt() == 1) {
					System.out.println("Player number before increment from user 1: " + playerNumberReady);
					playerNumberReady++;
					System.out.println("Player number after increment from user 1: " + playerNumberReady);
					allPlayersReady = checkIfAllPlayersReady();
					if (allPlayersReady == false) {
						outputClient1.writeUTF(waiting);
					}
				}

				if (inputClient2.readInt() == 1) {
					System.out.println("Player number before increment from user 2: " + playerNumberReady);
					playerNumberReady++;
					System.out.println("Player number after increment from user 2: " + playerNumberReady);
					allPlayersReady = checkIfAllPlayersReady();
					if (allPlayersReady != true) {
//						objectOutputStream.writeUTF(waiting);
						outputClient2.writeUTF(waiting);
					} else {
						outputClient1.writeUTF(letsGo);
						outputClient2.writeUTF(letsGo);
					}
				}

				if (inputClient3.readInt() == 1) {
					System.out.println("Player number before increment from user 2: " + playerNumberReady);
					playerNumberReady++;
					System.out.println("Player number after increment from user 2: " + playerNumberReady);
					allPlayersReady = checkIfAllPlayersReady();
					if (allPlayersReady != true) {
//						objectOutputStream.writeUTF(waiting);
//						objectOutputStream2.writeUTF(waiting);
						outputClient3.writeUTF(waiting);
					} else {
						outputClient1.writeUTF(letsGo);
						outputClient2.writeUTF(letsGo);
						outputClient3.writeUTF(letsGo);
					}
				}

				if (inputClient4.readInt() == 1) {
					System.out.println("Player number before increment from user 2: " + playerNumberReady);
					playerNumberReady++;
					System.out.println("Player number after increment from user 2: " + playerNumberReady);
					allPlayersReady = checkIfAllPlayersReady();
					if (allPlayersReady != true) {
						outputClient1.writeUTF(waiting);
					} else { 
						outputClient1.writeUTF(letsGo);
						outputClient2.writeUTF(letsGo);
						outputClient3.writeUTF(letsGo);
						outputClient4.writeUTF(waiting);
						outputClient4.writeUTF(letsGo);
					}
				}

				// Send a bool to the client so that they stay inside a Game while loop
				outputClient1.writeBoolean(true);
				outputClient2.writeBoolean(true);
				outputClient3.writeBoolean(true);
				outputClient4.writeBoolean(true);
				
			while(allPlayersReady) {
				
				rollTheDice();

				
			}
			} catch (EOFException e) {
				e.printStackTrace();
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
		public void rollTheDice() throws IOException {
			outputClient1.writeUTF("Press 'r' to roll the dice");
			outputClient2.writeUTF("Press 'r' to roll the dice");
			outputClient3.writeUTF("Press 'r' to roll the dice");
			outputClient4.writeUTF("Press 'r' to roll the dice");
			
			Random rand = new Random();
			char rollValue = inputClient1.readChar();
			System.out.println(rollValue);
			outputClient1.writeUTF("You rollled " + rand.nextInt(50) );
			
			rollValue = inputClient2.readChar();
			System.out.println(rollValue);
			outputClient2.writeUTF("You rollled " + rand.nextInt(50));
			
			rollValue = inputClient3.readChar();
			System.out.println(rollValue);
			outputClient3.writeUTF("You rollled " + rand.nextInt(50));
			
			rollValue = inputClient4.readChar();
			System.out.println(rollValue);
			outputClient4.writeUTF("You rollled " + rand.nextInt(50));
		}
	}
}
		
	/*	// Method needed for when players win 
	    boolean PlayerWon(int positionState) {
	        // Print message if game-over
	        if (positionState == socket1) {
	            System.out.println(T"'Player1' won!");
	        } else if (positionState == socket2) {
	            System.out.println("'Player2' won!");
	        } else if (positionState == socket3) {
	            System.out.println("'Player3' won!");
	        } else if (positionState == socket4) {
	            System.out.println("'Player4' won!");
	        } else {
	            return false;
	            }
	        }
	    }
}*/

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