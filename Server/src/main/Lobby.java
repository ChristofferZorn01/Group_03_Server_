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
		public static final int MAX_USERS = 5000;
		public static final String HOST = "localhost";
		public String name = "Lobby Server";
		static DataInputStream in;
		static DataOutputStream out;
		public int clientNumber;
		public int playerNumberReady = 0;
		public boolean allPlayersReady = false;
		public boolean OddurIsNice = false;
		public String joinedServer = "You joined the server.";
		public String waiting = "Waiting...";
		public String letsGo = "LETS GO";
		public String youArePlayerNumber = "You are player number: ";
		public String pressIfReady = "Press '1' if you are ready";
		public static void main(String[] args) throws IOException, ClassNotFoundException {
			Lobby s = new Lobby();
			s.runServer();
		}
		public void runServer() throws IOException, ClassNotFoundException {
			registerServer();
			new Thread( () -> {
				try {
					ServerSocket serverSocket = new ServerSocket(PORT);
					System.out.println("Server waiting for connections...");
					while (true) {
						
						// Client 1 is assigned to socket
						Socket socket = serverSocket.accept();
						System.out.println("User 1 is now connected");
						clientNumber++;  // not really used now
						
//						new ObjectOutputStream(socket.getOutputStream()).writeObject("You are connected man");
						
						// Client 1 is assigned to socket2
						Socket socket2 = serverSocket.accept();
						System.out.println("User 2 is now connected");
						clientNumber++; // not really used now
						
//						ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());
//						objectOutputStream2.writeObject("You are player number " + clientNumber + ". Waiting for other players to join");
						
						// Start the threads with both the users
						new ServerThread(socket, socket2).start();
					
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
			public Socket socket = null;
			public Socket socket2 = null;
			ServerThread(Socket socket, Socket socket2) {
				this.socket = socket;
				this.socket2 = socket2;
			}
			public void run() {
				try {		
					
					// This method is for when the client wants to connect to the lobby
					DataInputStream objectInputStream = new DataInputStream(socket.getInputStream());
					DataOutputStream objectOutputStream = new DataOutputStream(socket.getOutputStream());
					System.out.println(socket);
					System.out.println("User 1 is now connected");

					// This method is for when the second client wants to connect to the lobby
					DataInputStream objectInputStream2 = new DataInputStream(socket2.getInputStream());
					DataOutputStream objectOutputStream2 = new DataOutputStream(socket2.getOutputStream());
					System.out.println(socket2);
					System.out.println("User 2 is now connected");
					
//					BoardGameClient joined = (BoardGameClient) objectInputStream.readObject();
//					System.out.println(joined.name + " is now connected.");
//					while(true) {
					
					// Send info to the client 1
					objectOutputStream.writeUTF(joinedServer);
					objectOutputStream.writeUTF(youArePlayerNumber + 1);
					objectOutputStream.writeUTF(pressIfReady);
					
					// Send info to the client 1
					objectOutputStream2.writeUTF(joinedServer);
					objectOutputStream2.writeUTF(youArePlayerNumber + 2);
					objectOutputStream2.writeUTF(pressIfReady);
					
					// Check if the input received from client 1 is 1 (player is ready)
					if(objectInputStream.readInt() == 1) {
						System.out.println("Player number before increment from user 1: " + playerNumberReady);
						playerNumberReady++;
						System.out.println("Player number after increment from user 1: " + playerNumberReady);
						allPlayersReady = checkIfAllPlayersReady();
						if(allPlayersReady == false) {
							objectOutputStream.writeUTF(waiting); // Send to client 1 that not all users are ready
						}
					}
					
					// Check if the input received from client 2 is 1 (player is ready)
					if(objectInputStream2.readInt() == 1) {
						System.out.println("Player number before increment from user 2: " + playerNumberReady);
						playerNumberReady++;
						System.out.println("Player number after increment from user 2: " + playerNumberReady);
						allPlayersReady = checkIfAllPlayersReady();
						if (allPlayersReady == true) {
							objectOutputStream.writeUTF(letsGo); // Send to both clients that all users are ready
							objectOutputStream2.writeUTF(letsGo);
					}					
					}
					
//					if(allPlayersReady == false) {
//						objectOutputStream.writeObject("Waiting...");
//						objectOutputStream2.writeObject("Waiting...");
//					}
//
//					
//					if (allPlayersReady == true) {
//							objectOutputStream.writeObject("Lets GO");
//							objectOutputStream2.writeObject("Lets GO");
//					}							
				
					
//					while (true) {
//						System.out.println(objectInputStream.readObject());
//					}
//					}
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			public boolean checkIfAllPlayersReady() {
				
				if(playerNumberReady != 2) {
					return false;
				}  else {
					return true;
				}
			
			}
		}
	}
	
	
	
	
	
	
	
/*	
//	numberOfPlayers = Server.getNumberOfClients();
	
	newDiceTest dice = new newDiceTest(numberOfPlayers);
	
	private boolean checkIfReady() {
		if(playerReady == false) {
			return false;
		} else {
			return true;
		}
	}
	
//	public Lobby() {
//		
//	}
//	
//	public Lobby(String clientName, int playerScore) {
//		this.clientName = clientName;
//		this.playerScore = playerScore;
//	}
	
	// !!!!!!!!!! The following functions probably belong in a Board class, not this class !!!!!!!!!!!! //
	public void setDiceSize() {
		diceSize = dice.getDiceSize();
	}
	
	// This should return client's name, followed by their individual score
	public int getBoardScore() {
		return this.playerScore;
	}
	
	// Not sure what this one should do, it's taken from the class diagram
	public int getDiceRoll() {
		return diceRoll;
	}
	
	// This function should add the dice value to player's score
	
	// Need help to get access to the Dice class
	private void addDiceToScore(int diceToScore) {
//		int diceValue = Dice.roll();
		this.playerScore += diceToScore;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
*/