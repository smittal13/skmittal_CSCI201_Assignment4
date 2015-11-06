package sorryclient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import game.GameManager;
import library.ImageLibrary;

public class ClientPanel extends JPanel {
	private static final long serialVersionUID = 6415716059554739910L;
	
	private MainMenu mainMenu;
	private NumPlayerSelector numPlayerSelect;
	private ColorSelector colorSelect;
	private GamePanel gamePanel;
	
	private GameManager gameManager;
	
	{
		mainMenu = new MainMenu(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(numPlayerSelect);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		},ImageLibrary.getImage("images/panels/grey_panel.png"));
		
		refreshComponents();
		setLayout(new BorderLayout());
		add(mainMenu);
	}
	
	private void refreshComponents() {
		numPlayerSelect = new NumPlayerSelector(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(colorSelect);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		},ImageLibrary.getImage("images/panels/grey_panel.png"));
		colorSelect = new ColorSelector(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientPanel.this.removeAll();
				gameManager.setUp(
					colorSelect.getPlayerColor(), 
					numPlayerSelect.getNumberOfPlayers()
				);
				ClientPanel.this.add(gamePanel);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		},ImageLibrary.getImage("images/panels/grey_panel.png"));
		
		gameManager = new GameManager();
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
	}

}
