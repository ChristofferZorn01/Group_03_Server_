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
		public int playerNumberReady = 0;
		public boolean allPlayersReady = false;
		public boolean OddurIsNice = false;
		
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
						Socket socket = serverSocket.accept();
						System.out.println("User 1 is now connected");
						clientNumber++;
//						new ObjectOutputStream(socket.getOutputStream()).writeObject("You are connected man");
						Socket socket2 = serverSocket.accept();
						System.out.println("User 2 is now connected");
						clientNumber++;
//						ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());
//						objectOutputStream2.writeObject("You are player number " + clientNumber + ". Waiting for other players to join");
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
					
					// This method is for when the client want's to connect to the lobby
					ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					System.out.println("User 1 is now connected");
					
					ObjectInputStream objectInputStream2 = new ObjectInputStream(socket2.getInputStream());
					ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket2.getOutputStream());
					System.out.println("User 2 is now connected");
					BoardGameClient joined = (BoardGameClient) objectInputStream.readObject();
					System.out.println(joined.name + " is now connected.");
					while(true) {
					objectOutputStream.writeObject("You joined the server.");
					objectOutputStream.writeObject("You are player Number " + 1);
					
					objectOutputStream.writeObject("Press '1' if you are ready");
					
					objectOutputStream2.writeObject("You joined the server.");
					objectOutputStream2.writeObject("You are player Number " + 2);
					
					objectOutputStream2.writeObject("Press '1' if you are ready");
					
					if(objectInputStream.readObject().equals(1)) {
						playerNumberReady++;
					}
					
					if(objectInputStream2.readObject().equals(1)) {
						playerNumberReady++;
					}
					
						if(playerNumberReady != 2) {
							allPlayersReady = false;
						} else {
							allPlayersReady = true;
						}
					
					
					
						if (allPlayersReady == false) {
							objectOutputStream.writeObject("Waiting...");
							objectOutputStream2.writeObject("Waiting...");
					} 
					
						if (allPlayersReady == true) {
						objectOutputStream.writeObject("Lets GO");
						objectOutputStream2.writeObject("Lets GO");
					}							
				
					
//					while (true) {
//						System.out.println(objectInputStream.readObject());
//					}
					}
					} catch (ClassNotFoundException e) {
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