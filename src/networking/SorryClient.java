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
		//	this.start();
			Scanner scan = new Scanner(System.in);
			while (true) {
				String line = scan.nextLine();
				pw.println("Sagar: " + line);
				pw.flush();
			}
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
}
