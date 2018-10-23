	package main;

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
		public int clientNumber;
		public int playerNumberReady;
		public boolean allPlayersReady = false;
		public boolean OddurIsNice = false;
		
		public static void main(String[] args) throws IOException, ClassNotFoundException {
			Lobby s = new Lobby();
			s.runServer();
		}
		public void runServer() throws IOException, ClassNotFoundException {
			registerServer();
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server waiting for connections...");
			while (true) {
				Socket socket = serverSocket.accept();
				clientNumber++;
				new ServerThread(socket).start();
			
			}
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
			ServerThread(Socket socket) {
				this.socket = socket;
			}
			public void run() {
				try {		
					
					// This method is for when the client want's to connect to the lobby
					ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					
					BoardGameClient joined = (BoardGameClient) objectInputStream.readObject();
					System.out.println(joined.name + " is now connected.");
					
					objectOutputStream.writeObject("You joined the server.");
					objectOutputStream.writeObject("You are player Number " + clientNumber);
					
					objectOutputStream.writeObject("Press '1' if you are ready");
					
					if(objectInputStream.readObject().equals(1)) {
						playerNumberReady++;
					}
					
						if (playerNumberReady == 2) {
							allPlayersReady = true;
						}
					
					while (true) {
					
						if (allPlayersReady == false) {
							objectOutputStream.writeObject("Waiting...");
					} 
					
						if (allPlayersReady == true) {
						objectOutputStream.writeObject("Lets GO");
					}							
				
					
					while (true) {
						System.out.println(objectInputStream.readObject());
					}
				}} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
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
