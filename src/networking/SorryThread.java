package networking;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SorryThread extends Thread {
	private BufferedReader br;
	private PrintWriter pw;
	private SorryServer ss;
	private Socket s;
	
	String line = null;
	public SorryThread(Socket s, SorryServer ss) {
		this.s = s;
		this.ss = ss;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
		} catch (IOException ioe) {
			System.out.println("IOE message chat thread: " + ioe.getMessage());
		}
	}
	public void sendMessage(String message) {
		if (pw != null) {
			pw.println(message);
			pw.flush();
		}
	}
	public Socket getSocket() {
		return this.s;
	}
	public void run() {
		PrintChatMessage();
	}
	
	private void PrintChatMessage() {
		try {
			while (true) {
				line = br.readLine();
				System.out.println(line);
				if (line != null) {
					ss.sendMessageToAllClients(line, this);
					line = null;
				}
				else {
					break;
				}
			}
		}catch (IOException ioe) {
			System.out.println("IOE message run chat thread: " + ioe.getMessage());
		}finally {
			ss.removeChatThread(this);
		}
	}
}
