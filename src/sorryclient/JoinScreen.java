package sorryclient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import customUI.PaintedButton;
import customUI.PaintedPanel;
import library.FontLibrary;
import library.ImageLibrary;

public class JoinScreen extends PaintedPanel {

	private JPanel topPanel, ipPanel, portPanel, connectPanel;
	private JLabel ipAddress, port;
	private JTextField ipEntry, portEntry;
	private JButton connect;
	private final Dimension buttonSize = new Dimension (150, 35);
	private final Insets spacing = new Insets(60,80,60,80);
	
	public JoinScreen(ActionListener connectAction, Image inImage) {
		super(inImage, true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(spacing));
		
		//Top panel initialization
		topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		//Initialize IP Fields
		ipPanel = new JPanel();
		ipAddress = new JLabel("IP: ");
		ipAddress.setFont(FontLibrary.getFont("fonts/kenvector_future_thin.ttf", Font.PLAIN, 28));
		ipEntry = new JTextField(15);
		ipEntry.setFont(FontLibrary.getFont("fonts/kenvector_future_thin.ttf", Font.PLAIN, 28));
		ipEntry.setBackground(Color.GRAY);
		ipPanel.add(ipAddress);
		ipPanel.add(ipEntry);
		topPanel.add(ipPanel, gbc);
		
		//Initialize Port Fields
		portPanel = new JPanel();
		port = new JLabel("Port: ");
		port.setFont(FontLibrary.getFont("fonts/kenvector_future_thin.ttf", Font.PLAIN, 28));
		portEntry = new JTextField(4);
		portEntry.setFont(FontLibrary.getFont("fonts/kenvector_future_thin.ttf", Font.PLAIN, 28));
		portEntry.setBackground(Color.GRAY);
		portPanel.add(port);
		portPanel.add(portEntry);
		gbc.gridy = 2;
		topPanel.add(portPanel, gbc);
		
		//Initialize Connect fields
		connect = new PaintedButton(
				"Connect",
				ImageLibrary.getImage("images/buttons/grey_button00.png"),
				ImageLibrary.getImage("images/buttons/grey_button01.png"),
				22
				);
		connect.setPreferredSize(buttonSize);
		connect.addActionListener(connectAction);
		connectPanel = new JPanel();
		connectPanel.add(connect);
		connectPanel.setOpaque(false);
		
		//Add panels to screen
		add(topPanel);
		add(connectPanel);
	}

}
