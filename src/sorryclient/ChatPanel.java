package sorryclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatPanel extends JPanel {
	
	JTextField typingField;
	JTextArea displayArea;
	JButton send;
	JPanel bottomPanel;
	String currentChatText;
	
	public ChatPanel() {
		setLayout(new BorderLayout());
		bottomPanel = new JPanel();
		
		displayArea = new JTextArea();
		displayArea.setBackground(Color.BLACK);
		displayArea.setPreferredSize(new Dimension (640, 100));
		add(displayArea, BorderLayout.CENTER);
		
		typingField = new JTextField(45);
		typingField.setPreferredSize(new Dimension (640, 25));
		typingField.setBackground(Color.BLACK);
		send = new JButton("Send");
		
		bottomPanel.add(typingField);
		bottomPanel.add(send);
		add(bottomPanel, BorderLayout.SOUTH);
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				currentChatText = getChatText();
				displayArea.setText(currentChatText += getUserText() + "\n");
				typingField.setText("");
			}
		});
	}
	
	private String getUserText() {
		return typingField.getText();
	}
	
	public String getChatText() {
		return displayArea.getText();
	}
}
