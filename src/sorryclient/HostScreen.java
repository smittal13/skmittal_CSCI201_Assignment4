package sorryclient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import customUI.PaintedButton;
import customUI.PaintedPanel;
import library.FontLibrary;
import library.ImageLibrary;

public class HostScreen extends PaintedPanel {

	private JLabel port;
	private JTextField entry;
	private JButton start;
	private JPanel portPanel, startPanel;
	private final Dimension buttonSize = new Dimension (120, 35);
	private Font f;
	
	public HostScreen(ActionListener startAction, Image inImage) {
		super(inImage, true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Initialize Port
		portPanel = new JPanel();
		port = new JLabel("Port: ");
		port.setFont(FontLibrary.getFont("fonts/kenvector_future_thin.ttf", Font.PLAIN, 28));
		
		entry = new JTextField(4);
		entry.setFont(FontLibrary.getFont("fonts/kenvector_future_thin.ttf", Font.PLAIN, 28));
		entry.setBackground(Color.GRAY);
		
		portPanel.add(port);
		portPanel.add(entry);
		portPanel.setOpaque(false);
		
		//Initialize Start
		startPanel = new JPanel();
		start = new PaintedButton(
				"Start",
				ImageLibrary.getImage("images/buttons/grey_button00.png"),
				ImageLibrary.getImage("images/buttons/grey_button01.png"),
				22
				);
		start.addActionListener(startAction);
		start.setPreferredSize(buttonSize);
		startPanel.add(start);
		startPanel.setOpaque(false);
		
		add(Box.createGlue());
		add(portPanel);
		add(startPanel);
	}

}
