package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SorryClient {
	
	static BufferedReader br;
	static PrintWriter pw;
	static MessageProcessor m;
	
	public SorryClient(String hostname, int port) {
		try {
			Socket s = new Socket (hostname, port);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
	public void run() {
		try {
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				if (line.startsWith("numPlayers")) {
					char a = line.charAt(line.length()-1);
					String s = Character.toString(a);
					m.ProcessMessage(s);
				}
			}
		} catch (IOException ioe) {
			System.out.println("ioe chat client: " + ioe.getMessage());
		}
	}
	
	public static void SetMessageProcessor(MessageProcessor mp) {
		m = mp;
	}
	
	public static void SendString(String line) {
		pw.println(line);
		pw.flush();
	}
	
}
