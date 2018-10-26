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
		// connecting the lobby to the main server
		registerServer();
		// creating a new thread for the lobby 
		new Thread(() -> {
			try {
				// Creating the server on the stated port
				ServerSocket serverSocket = new ServerSocket(PORT);
				System.out.println("Waiting for connections...");
				// While the variable morePlayersCanJoin is true the server can accept clients
				while (morePlayersCanJoin) {
					// increases the number of clients that joined and accepting the connection
					clientNumber++;
					Socket socket1 = serverSocket.accept();
					// creating an output stream to the client telling the client how many clients are missing before the game can start
					DataOutputStream objectOutputStream = new DataOutputStream(socket1.getOutputStream());
					objectOutputStream.writeInt(MAX_USERS - clientNumber);

					System.out.println("User " + clientNumber + " is now connected");
					// increases the number of clients that joined and accepting the connection
					clientNumber++;
					Socket socket2 = serverSocket.accept();
					// creating an output stream to the client telling the client how many clients are missing before the game can start
					DataOutputStream objectOutputStream1 = new DataOutputStream(socket2.getOutputStream());
					objectOutputStream1.writeInt(MAX_USERS - clientNumber);

					System.out.println("User " + clientNumber + " is now connected");
					// increases the number of clients that joined and accepting the connection
					clientNumber++;
					Socket socket3 = serverSocket.accept();
					// creating an output stream to the client telling the client how many clients are missing before the game can start
					DataOutputStream objectOutputStream2 = new DataOutputStream(socket3.getOutputStream());
					objectOutputStream2.writeInt(MAX_USERS - clientNumber);

					System.out.println("User " + clientNumber + " is now connected");
					// increases the number of clients that joined and accepting the connection
					clientNumber++;
					Socket socket4 = serverSocket.accept();
					// creating an output stream to the client telling the client how many clients are missing before the game can start
					DataOutputStream objectOutputStream3 = new DataOutputStream(socket4.getOutputStream());
					System.out.println("User " + clientNumber + " is now connected");
					objectOutputStream3.writeInt(MAX_USERS - clientNumber);

					// if the number of clients reaches 4, no more clients are able to join
					if (clientNumber >= 4) {
						System.out.println("Limit Reached");
						morePlayersCanJoin = false;
					}

					
					// depending on the number of clients that have joined, the switch statement makes sure enough new threads are created
					switch (clientNumber) {
					// if the client number is 2: 2 new threads are created
					case 2:
						new Thread(new ServerThread(socket1, socket2)).start();
						break;
						// if the client number is 3: 3 new threads are created
					case 3:
						new Thread(new ServerThread(socket1, socket2, socket3)).start();
						break;
						// if the client number is 4: 4 new threads are created
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

	// Method for establishing a connection to the MainServer
	private void registerServer() throws UnknownHostException, IOException, ClassNotFoundException {
		// Connecting to the main server
		Socket socket = new Socket(MainServer.HOST, MainServer.PORT);
		// Input and output to the main server from the lobby
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		objectOutputStream.writeObject(this);

		System.out.println((String) objectInputStream.readObject());
	}

		// class to create new threads depending of the amount of clients connected
	public class ServerThread extends Thread {
		// declaring 4 sockets one for each client joined 
		public Socket socket1;
		public Socket socket2;
		public Socket socket3;
		public Socket socket4;

		// constructors that overrides each-other depending on how many client are connected
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

		// Declare the input and output streams of the 4 clients
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

				// The input and output to each client 
				// Client #1 
				inputClient1 = new DataInputStream(socket1.getInputStream());
				outputClient1 = new DataOutputStream(socket1.getOutputStream());
				System.out.println(socket1);
				System.out.println("User 1 is now connected");
				// sending messages to the client stating that they have joined, they are player X and if they are ready press X
				outputClient1.writeUTF(joinedServer);
				outputClient1.writeUTF(youArePlayerNumber + 1);
				outputClient1.writeUTF(pressIfReady);

				// Client #2
				inputClient2 = new DataInputStream(socket2.getInputStream());
				outputClient2 = new DataOutputStream(socket2.getOutputStream());
				System.out.println(socket2);
				System.out.println("User 2 is now connected");

				outputClient2.writeUTF(joinedServer);
				outputClient2.writeUTF(youArePlayerNumber + 2);
				outputClient2.writeUTF(pressIfReady);

				// Client #3 
				inputClient3 = new DataInputStream(socket3.getInputStream());
				outputClient3 = new DataOutputStream(socket3.getOutputStream());
				System.out.println(socket2);
				System.out.println("User 3 is now connected");

				outputClient3.writeUTF(joinedServer);
				outputClient3.writeUTF(youArePlayerNumber + 3);
				outputClient3.writeUTF(pressIfReady);

				// Client #4 
				inputClient4 = new DataInputStream(socket4.getInputStream());
				outputClient4 = new DataOutputStream(socket4.getOutputStream());
				System.out.println(socket2);
				System.out.println("User 4 is now connected");

				outputClient4.writeUTF(joinedServer);
				outputClient4.writeUTF(youArePlayerNumber + 4);
				outputClient4.writeUTF(pressIfReady);

				// If statements to check whether the client are ready to start the game
				
				
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

				while (allPlayersReady) {

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
			outputClient1.writeUTF("You rollled " + rand.nextInt(50));

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