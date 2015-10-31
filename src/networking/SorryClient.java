package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SorryClient {
	
	BufferedReader br;
	PrintWriter pw;
	
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
				System.out.println(line);
			}
		} catch (IOException ioe) {
			System.out.println("ioe chat client: " + ioe.getMessage());
		}
	}
	
	public void SendString(String line) {
		pw.println("Sagar: " + line);
		pw.flush();
	}
}
