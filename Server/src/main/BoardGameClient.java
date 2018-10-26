package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import master.MainServer;

public class BoardGameClient implements Serializable {

	private int playerCount;
	private static final long serialVersionUID = -6224L;
	public String name = "User";
	private transient Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	public transient Scanner input = new Scanner(System.in);
	public boolean gameStarted;

	public static void main(String[] args) {

		BoardGameClient c = new BoardGameClient();

		try {

			c.joinServer();

		} catch (ClassNotFoundException | IOException e) {

			System.out.println("Failed to join server.");
			e.printStackTrace();
		}
	}

	public void joinServer() throws UnknownHostException, IOException, ClassNotFoundException {

		try {
			socket = new Socket(Lobby.HOST, Lobby.PORT);
			DataOutputStream outputToServer = new DataOutputStream(socket.getOutputStream());
			DataInputStream inputFromServer = new DataInputStream(socket.getInputStream());
			System.out.println("Trying to establish connection...");
			while (socket.isConnected()) {
				// objectOutputStream.writeObject(this);
				System.out.println("Please wait for more players... Missing " + inputFromServer.readInt() + " players");
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

				System.out.println(inputFromServer.readUTF());
				System.out.println(inputFromServer.readUTF());
				System.out.println(inputFromServer.readUTF());

				int ready = input.nextInt();
				outputToServer.writeInt(ready);

				System.out.println(inputFromServer.readUTF());
				System.out.println(inputFromServer.readUTF());

				// Receive a boolean so the client stays inside the game while loop
				gameStarted = inputFromServer.readBoolean();

				while (gameStarted) {
					// Receive instructions to roll the dice
					System.out.println(inputFromServer.readUTF());

					String roll = input.next();
					outputToServer.writeChars(roll);

					System.out.println(inputFromServer.readUTF());

				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
