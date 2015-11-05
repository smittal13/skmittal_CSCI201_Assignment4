package sorryclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import customUI.PaintedButton;
import library.ImageLibrary;
import networking.SorryClient;

public class ChatPanel extends JPanel {
	
	JTextField typingField;
	JScrollPane jsp;
	JTextArea displayArea;
	JButton send;
	JPanel bottomPanel;
	String currentChatText, textToSend;
	SorryClient sc;
	
	public ChatPanel() {
		setLayout(new BorderLayout());
		bottomPanel = new JPanel();
		
		displayArea = new JTextArea();
		displayArea.setEditable(false);
		displayArea.setBackground(Color.BLACK);
		displayArea.setForeground(Color.WHITE);
		displayArea.setPreferredSize(new Dimension (640,100));
		displayArea.setLineWrap(true);
		displayArea.setWrapStyleWord(true);
		
		jsp = new JScrollPane(displayArea);
		jsp.setPreferredSize(new Dimension (640, 80));
		add(jsp);
		
		typingField = new JTextField(this.WIDTH);
		typingField.setPreferredSize(new Dimension (640, 25));
		typingField.setBackground(Color.BLACK);
		typingField.setForeground(Color.WHITE);
		send = new PaintedButton(
				"Send",
				ImageLibrary.getImage("images/buttons/grey_button00.png"),
				ImageLibrary.getImage("images/buttons/grey_button01.png"),
				22
				);
		
		bottomPanel.add(typingField);
		bottomPanel.add(send);
		add(bottomPanel, BorderLayout.SOUTH);
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				textToSend = getChatText() + getUserText() + "\n";
				SorryClient.SendString("chat - " + textToSend);
				PrintMessage();
			}
		});
	}
	
	public String getUserText() {
		return typingField.getText();
	}
	
	public String getChatText() {
		return displayArea.getText();
	}
	
	public void PrintMessage() {
		displayArea.setText(textToSend);
		typingField.setText("");
	}
}
