package MediaDatabase;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

public class MakerTypeView extends JPanel implements ActionListener {

	public MakerTypeView(String makerName) {
		
		JPanel mainRadio = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel subRadio = new JPanel();
		JLabel radioLabel = new JLabel("Select for which role you want to add credits for " + makerName);
		// Set border layout of mainRadio.
		//mainRadio.setLayout(new BorderLayout());
		// Create radio button panel.
		JPanel radioButtons = new JPanel(new BorderLayout());
		// Set the Radio button panel layout.
		radioButtons.setLayout(new GridLayout(1, 4));
		// Create the radio buttons.	
		JRadioButton space1 = new JRadioButton(" ");
		JRadioButton actorButton = new JRadioButton("Actor       ");
		JRadioButton directorButton = new JRadioButton("Director  ");
		JRadioButton producerButton = new JRadioButton("Producer");
		ButtonGroup makerButtons = new ButtonGroup();
		
		makerButtons.add(actorButton);
		makerButtons.add(directorButton);
		makerButtons.add(producerButton);
		
		radioButtons.add(actorButton,BorderLayout.WEST);
		radioButtons.add(directorButton,BorderLayout.CENTER);
		radioButtons.add(producerButton,BorderLayout.EAST);
		subRadio.add(space1);
		space1.setVisible(false);
		subRadio.add(radioButtons);
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20,5,5,5);
		mainRadio.add(radioLabel,c);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5,5,0,5);
		mainRadio.add(subRadio,c);
		
		
		// Add Cancel and Submit buttons to the 
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		JButton submit = new JButton("Submit");
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(5,200,5,5);
		buttonPanel.add(cancel,c);
		
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.insets = new Insets(5,5,5,5);
		buttonPanel.add(submit, c);
		submit.addActionListener(this);
		submit.setActionCommand("submit");
		// This is the backup panel that contains all other elements of this
		// graphical user interface.
		JPanel mainPanel1 = new JPanel(new BorderLayout());
		// Surround the MainPanle2 with a raised etched border.
		
		mainPanel1.add(mainRadio,BorderLayout.NORTH);
		mainPanel1.add(buttonPanel,BorderLayout.SOUTH);

		add(mainPanel1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("submit")){
			
		}
		else {
			
		}
				
	}	
}
