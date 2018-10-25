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