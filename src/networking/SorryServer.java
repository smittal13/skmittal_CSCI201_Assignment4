package networking;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class SorryServer implements Runnable {

	static Vector<SorryThread> stVector;
	int p, limit;
	static Color colorSelection;
	static boolean readyToStart = false;
	
	public SorryServer(int port, int limit) {
		stVector = new Vector<SorryThread>();
		p = port;
		this.limit = limit;
	}
	
	public void sendMessageToAllClients(String message, SorryThread sendingThread) {
		System.out.println(message);
		for (SorryThread st : stVector) {
			if (sendingThread != st) {
				st.sendMessage(message);
			}
		}
	}
	
	public void removeChatThread(SorryThread st) {
		System.out.println("Client Disconected: " + st.getSocket().getInetAddress());
		stVector.remove(st);
	}	
	
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(p);
			while (true) {
				while (stVector.size() <= limit) {
					System.out.println("waiting for connection...");
					Socket s = ss.accept();
					SorryThread st = new SorryThread(s, this);
					stVector.add(st);
					sendMessageToAllClients("Connection from: " + s.getInetAddress(), null);
					st.start();
				}
			}
		} catch (IOException ioe) {
			System.out.println("Chat server ioe: " + ioe.getMessage());
		}
	}
	
	public static void setColorSelection(Color c) {
		colorSelection = c;
	}
	
	public static Vector<SorryThread> getClientColors() {
		return stVector;
	}
	
}
