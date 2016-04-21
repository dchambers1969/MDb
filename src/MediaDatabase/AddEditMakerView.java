package MediaDatabase;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

/**
 * This class defines the Add Maker View. 
 */

public class AddEditMakerView extends JPanel implements ActionListener{

	public AddEditMakerView(MediaMaker mediaMaker, Actor actor, Director director, Producer producer){
		
		JPanel mainRadio = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel subRadio = new JPanel();
		JLabel radioLabel = new JLabel("Select what type of Media Maker(s) for this person.");
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
				
		radioButtons.add(actorButton,BorderLayout.WEST);
		radioButtons.add(directorButton,BorderLayout.CENTER);
		radioButtons.add(producerButton,BorderLayout.EAST);
		subRadio.add(space1);
		space1.setVisible(false);
		subRadio.add(radioButtons);
		if(mediaMaker.getMakerFirstName()!=null){
			if(actor.getMakerFirstName()!=null)
				actorButton.setSelected(true);
			if(director.getMakerFirstName()!= null)
				directorButton.setSelected(true);
			if(producer.getMakerFirstName()!=null)
				producerButton.setSelected(true);
		}
		
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
		
		//Create the text fields for name (first and last name) Populate if Edit
		JTextField firstName = new JTextField(mediaMaker.getMakerFirstName(),15);
		firstName.setHorizontalAlignment(JTextField.CENTER);
		JLabel firstNameLabel = new JLabel("First Name: ");
		JTextField lastName = new JTextField(mediaMaker.getMakerFamilyName(),15);
		lastName.setHorizontalAlignment(JTextField.CENTER);
		JLabel lastNameLabel = new JLabel("Last Name: ");
				
		//Create Scroll list for acceptable suffixes
		JLabel suffixLabel = new JLabel("Suffix: ");
		String [] suffixOptions = {"Jr", "I" , "II", "III", "IV", "V"};
		JList<String> suffixJList = new JList<String>(suffixOptions);
		suffixJList.setVisibleRowCount(3);
		suffixJList.setFixedCellWidth(22);
				
		int suffixIndex = 0;
		
		//Show selection if edit 
		if(mediaMaker.getMakerSuffixInfo()!=null){
			for(int i = 0; i<suffixOptions.length; ++i){
				if(mediaMaker.getMakerSuffixInfo().equals(suffixOptions[i]))
						suffixIndex = i;
			}
			suffixJList.setSelectedIndex(suffixIndex);
		}
		JScrollPane suffix = new JScrollPane(suffixJList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				
		// This is the Input fields panel container.
		JPanel namePanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
				
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 45;
		c.ipady = 0;
		c.insets = new Insets(5,5,5,5);
 		namePanel.add(firstNameLabel,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 50;
		namePanel.add(firstName, c);
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		namePanel.add(lastNameLabel,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 75;
		namePanel.add(lastName, c);
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 2;
		namePanel.add(suffixLabel,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 2;
		c.ipadx = 20;
		c.ipady = 40;
		namePanel.add(suffix, c);
		
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
		mainPanel1.add(namePanel,BorderLayout.CENTER);
		mainPanel1.add(buttonPanel,BorderLayout.SOUTH);
		
		mainPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
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