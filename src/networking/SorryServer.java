package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class SorryServer {

	private Vector<SorryThread> stVector;
	public SorryServer(int port) {
		stVector = new Vector<SorryThread>();
		try {
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				System.out.println("waiting for connection...");
				Socket s = ss.accept();
				System.out.println("Connection from: " + s.getInetAddress());
				SorryThread st = new SorryThread(s, this);
				stVector.add(st);
		//		st.start();
			}
		} catch (IOException ioe) {
			System.out.println("Chat server ioe: " + ioe.getMessage());
		}
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
}
