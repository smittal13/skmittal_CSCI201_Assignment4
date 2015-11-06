package sorryclient;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import customUI.ClearPanel;
import customUI.PaintedButton;
import customUI.PaintedPanel;
import game.GameHelpers;
import library.FontLibrary;
import library.ImageLibrary;
import networking.MessageProcessor;
import networking.SorryClient;
import networking.SorryServer;

public class ColorSelector extends PaintedPanel implements MessageProcessor {
	
	private static final long serialVersionUID = 1900724217285760485L;
	
	private Color selection;	
	private final int numOptions = 4;
	private final PaintedButton[] optionButtons;
	
	private final PaintedButton confirmButton;
	
	private final static String selectColorString = "Select your color";
	
	private final static String[] colorNames = {"Red", "Blue", "Green", "Yellow"};
	private final static Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
	
	private final static Insets spacing = new Insets(60,80,60,80);
	
	public Color getPlayerColor() {
		return selection;
	}
	
	public ColorSelector(ActionListener confirmAction, Image inImage) {
		super(inImage,true);
		confirmButton = new PaintedButton(
				"Confirm",
				ImageLibrary.getImage("images/buttons/grey_button00.png"),
				ImageLibrary.getImage("images/buttons/grey_button01.png"),
				22
				);
		confirmButton.addActionListener(confirmAction);
		confirmButton.setEnabled(false);
		
		JLabel selectColorLabel = new JLabel(selectColorString);
		selectColorLabel.setFont(FontLibrary.getFont("fonts/kenvector_future_thin.ttf", Font.PLAIN, 28));
		
		ClearPanel topPanel = new ClearPanel();
		topPanel.setBorder(new EmptyBorder(spacing));
		topPanel.add(selectColorLabel);
		
		ClearPanel centerPanel = new ClearPanel();
		centerPanel.setLayout(new GridLayout(2,2,10,10));
		Font buttonFont = new Font("",Font.BOLD,22);
		optionButtons = new PaintedButton[numOptions];
		for(int i = 0; i < numOptions; ++i) {
			optionButtons[i] = new PaintedButton(
					colorNames[i],
					ImageLibrary.getImage("images/buttons/"+colorNames[i].toLowerCase()+"_button00.png"),
					ImageLibrary.getImage("images/buttons/"+colorNames[i].toLowerCase()+"_button01.png"),
					22
					);
			final int buttonSelection = i;
			optionButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					ButtonAction(buttonSelection);
				}
			});
			optionButtons[i].setFont(buttonFont);
			centerPanel.add(optionButtons[i]);
		}
		centerPanel.setBorder(new EmptyBorder(new Insets(0, 80, 0, 80)));
		
		ClearPanel bottomPanel = new ClearPanel();
		bottomPanel.setLayout(new GridLayout(1,3));
		bottomPanel.setBorder(new EmptyBorder(spacing));
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(confirmButton);
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		add(topPanel);
		add(centerPanel);
		add(bottomPanel);
	}

	public void ButtonAction (int buttonSelection) {
		selection = colors[buttonSelection];
		for(JButton button : optionButtons) button.setEnabled(true);
		optionButtons[buttonSelection].setEnabled(false);
		confirmButton.setEnabled(true);
	}
	
	public String getSelection() {
		return GameHelpers.getNameFromColor(selection);
	}
	
	@Override
	public void ProcessMessage(String message) {
		optionButtons[Integer.parseInt(message)].setEnabled(false);
	}

}
