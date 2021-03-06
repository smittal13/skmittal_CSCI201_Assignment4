package sorryclient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;

import game.GameHelpers;
import game.GameManager;
import library.ImageLibrary;
import networking.*;

public class ClientPanel extends JPanel implements MessageProcessor {
	private static final long serialVersionUID = 6415716059554739910L;
	
	private MainMenu mainMenu;
	private NumPlayerSelector numPlayerSelect;
	private ColorSelector colorSelect;
	private GamePanel gamePanel;
	private HostScreen hostScreen;
	private JoinScreen joinScreen;
	private ChatPanel chatPanel;
	private JPanel mainGamePanel;
	
	private int numberOfPlayers;
	
	private GameManager gameManager;
	
	{
		mainMenu = new MainMenu(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(hostScreen);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		},new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(joinScreen);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		},ImageLibrary.getImage("images/panels/grey_panel.png"));
		
		refreshComponents();
		setLayout(new BorderLayout());
		add(mainMenu);
	}
	
	private void refreshComponents() {
		hostScreen = new HostScreen(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(numPlayerSelect);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		},ImageLibrary.getImage("images/panels/grey_panel.png"));
		joinScreen = new JoinScreen(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(colorSelect);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
				SorryClient c = new SorryClient(joinScreen.getHostString(), joinScreen.getPortInt());
				SorryClient.SetMessageProcessor(ClientPanel.this);
				SorryClient.SendString("numPlayers" + numPlayerSelect.getNumberOfPlayers());
			}
		},ImageLibrary.getImage("images/panels/grey_panel.png"));
		numPlayerSelect = new NumPlayerSelector(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(colorSelect);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
				SorryServer ss = new SorryServer(hostScreen.getPortNumber(), numPlayerSelect.getNumberOfPlayers());
				new Thread(ss).start();
				SorryClient c = new SorryClient("localhost", hostScreen.getPortNumber());
				SorryClient.SetMessageProcessor(ClientPanel.this);
				SorryClient.SendString("numPlayers" + numPlayerSelect.getNumberOfPlayers());
			}
		},ImageLibrary.getImage("images/panels/grey_panel.png"));
		colorSelect = new ColorSelector(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientPanel.this.removeAll();
				gameManager.setUp(
					colorSelect.getPlayerColor(), 
					numberOfPlayers
				);
				ClientPanel.this.add(mainGamePanel);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		},ImageLibrary.getImage("images/panels/grey_panel.png"));
		
		gameManager = new GameManager();
		mainGamePanel = new JPanel(new BorderLayout());
		chatPanel = new ChatPanel();
		gamePanel = new GamePanel(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(mainMenu);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
				refreshComponents();
			}
		}, gameManager, ImageLibrary.getImage("images/sorry.png"));
		
		mainGamePanel.add(chatPanel, BorderLayout.SOUTH);
		mainGamePanel.add(gamePanel, BorderLayout.CENTER);
	}

	public void ProcessMessage(String message) {
		int i = Integer.parseInt(message);
		numberOfPlayers = i;
	}
	
	public String[] ColorArray() {
		//You can't connect to something that hasn't been decided yet
		Vector<SorryThread> st = SorryServer.getClientColors();
		String[] colorArray = new String[st.size()];
		for (int i = 0; i < st.size(); i++) {
			//colorArray[i] = 
		}
		return colorArray;
	}

}
