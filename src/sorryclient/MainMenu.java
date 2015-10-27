package sorryclient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import customUI.PaintedButton;
import customUI.PaintedPanel;
import library.ImageLibrary;

public class MainMenu extends PaintedPanel{
	private static final long serialVersionUID = 3609831945869059312L;
	
	private final JButton host, join;
	private final Dimension buttonSize = new Dimension (100, 35);
	private final JLabel space;
	
	public MainMenu(ActionListener hostAction,ActionListener joinAction, Image inImage){
		super(inImage,true);
		
		host = new PaintedButton(
				"Host",
				ImageLibrary.getImage("images/buttons/grey_button00.png"),
				ImageLibrary.getImage("images/buttons/grey_button01.png"),
				22
				);
		host.addActionListener(hostAction);
		host.setPreferredSize(buttonSize);
		
		join = new PaintedButton(
				"Join",
				ImageLibrary.getImage("images/buttons/grey_button00.png"),
				ImageLibrary.getImage("images/buttons/grey_button01.png"),
				22
				);
		join.addActionListener(joinAction);
		join.setPreferredSize(buttonSize);
		
		space = new JLabel("");
		space.setPreferredSize(buttonSize);
		
		Image titleImage = ImageLibrary.getImage("images/sorry.png");
		PaintedPanel titlePanel = new PaintedPanel(titleImage);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(host);
		buttonPanel.add(space);
		buttonPanel.add(join);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.ipadx = titleImage.getWidth(null);
		gbc.ipady = titleImage.getHeight(null);
		gbc.insets = new Insets(40,40,40,40);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(titlePanel,gbc);
		
		gbc.ipadx = 100;
		gbc.ipady = 25;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		add(buttonPanel, gbc);
	}
	
}
