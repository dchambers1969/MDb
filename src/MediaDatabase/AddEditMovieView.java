package MediaDatabase;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.text.NumberFormatter;

public class AddEditMovieView extends JFrame implements ActionListener {

	public AddEditMovieView(Movie movieToEdit){
		
		// Create New JFrame
		JFrame frame = new JFrame();

		// Set the size of the frame.
		frame.setSize(400, 300);

		// Set the title of the frame based on if sent a non null mediaMaker.
		if(movieToEdit == null)
			frame.setTitle("Add Movie");
		else
			frame.setTitle("Edit " + movieToEdit.getTitle());
		
		// Make the frame visible to the user
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			
		
		// Create the text fields for series info (populate if an edit).
		JTextField movieTitleField = new JTextField(movieToEdit.getTitle(),20);
		movieTitleField.setHorizontalAlignment(JTextField.CENTER);
		// Create label for the title field.
		JLabel movieTitleLabel = new JLabel("Movie Title: ");
		
		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setGroupingUsed(false);
		NumberFormatter integerYearFormat = new NumberFormatter(format);
		integerYearFormat.setValueClass(Integer.class);
		integerYearFormat.setMinimum(1910);
		integerYearFormat.setMaximum(2018);
		integerYearFormat.setCommitsOnValidEdit(true);
		JFormattedTextField releaseYear = new JFormattedTextField(integerYearFormat);
		// Set the value of the release year.
		releaseYear.setValue(movieToEdit.getReleaseYear());
		releaseYear.setHorizontalAlignment(JTextField.CENTER);
		
		releaseYear.setSize(10,10);
		JLabel releaseYearLabel1 = new JLabel("Release Year: ");
		JLabel releaseYearLabel2 = new JLabel("Valid years are 1910 - 2018 or leave blank for unknown.");
		// Set the label for the radio buttons
		JLabel radioSelectionLabel = new JLabel("Please select one of the following: ");
		
		if(releaseYear.getValue()!=null){
			Integer value = Integer.parseInt(releaseYear.getValue().toString());
			integerYearFormat.setMinimum(value);
		}
		
		JPanel mainRadio = new JPanel();
		// Set border layout of mainRadio.
		//mainRadio.setLayout(new BorderLayout());
		// Create radio button panel.
		JPanel radioButtons = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Set the Radio button panel layout.
		radioButtons.setLayout(new GridLayout(1, 4));
		// Create the radio buttons.	
		JRadioButton space1 = new JRadioButton(" ");
		JRadioButton theater = new JRadioButton("Theater        ");
		JRadioButton television = new JRadioButton("TV  ");
		JRadioButton video = new JRadioButton("Video");
		
		// Create the button group for the radio buttons.
		ButtonGroup movieFormatGroup = new ButtonGroup();
		
		// Add the radio buttons to the button group.
		//movieFormatGroup.add(space1);
		movieFormatGroup.add(theater);
		movieFormatGroup.add(television);
		movieFormatGroup.add(video);
		if(movieToEdit.getReleaseType()!=null){
			if(movieToEdit.getReleaseType() ==ReleaseType.SCREEN)
				movieFormatGroup.setSelected(theater.getModel(), true);
			else if(movieToEdit.getReleaseType() == ReleaseType.VIDEO)
				movieFormatGroup.setSelected(video.getModel(), true);
			else
				movieFormatGroup.setSelected(television.getModel(), true);
		
		}
		// Add the radio buttons to the panel
		c.fill = GridBagConstraints.LINE_START;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		radioButtons.add(theater,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
		radioButtons.add(television,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.gridy = 0;
		radioButtons.add(video,c);
		// Add the radio buttons to the main radio button panel.
		mainRadio.add(radioButtons);
		// Set the visibility of the panel.
		radioButtons.setVisible(true);
		
		// Create main Panel 2.
		JPanel mainPanel2 = new JPanel(new GridBagLayout());
		// Create warning panel.
		JPanel warningPanel = new JPanel(new GridBagLayout());
		// Create the Grid bag constraints variable;
		c = new GridBagConstraints();
		// Set the grid bag main panel 2 layout.
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30,5,5,5);
 		mainPanel2.add(movieTitleLabel,c);
 		
 		// Set the grid bag main panel 2 layout.
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
		mainPanel2.add(movieTitleField, c);
		
		
		// Set the grid bag main panel 2 parameters.
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 35;
		c.insets = new Insets(5,5,5,5);
		mainPanel2.add(releaseYearLabel1,c);
		
		// Set the grid bag main panel parameters.
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		mainPanel2.add(releaseYear, c);
		
		// Set the grid bag warning panel parameters.
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 0;
		c.anchor = GridBagConstraints.WEST;
		warningPanel.add(releaseYearLabel2,c);
		
		 // Set the grid bag warning panel parameters.
		
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 0;
		c.anchor = GridBagConstraints.CENTER;
		warningPanel.add(radioSelectionLabel,c);
 
		// Add Cancel and Submit buttons to the 
		JButton cancel = new JButton("Cancel");
		// Set an action listener to the cancel button
		cancel.addActionListener(this);
		// Set action command of cancel.
		cancel.setActionCommand("cancel");
		// Create new button called submit.
		JButton submit = new JButton("Submit");
		// Create the main button panel.
		JPanel buttonPanel = new JPanel(new BorderLayout());
		// Create a new sub panel for button.
		JPanel buttonSubPanel = new JPanel();
		
		// Add the cancel button to the sub panel.
		buttonSubPanel.add(cancel);
		// Add the submit button to the sub panel.
		buttonSubPanel.add(submit);
		// Set the visibility of the button sub panel.
		buttonSubPanel.setVisible(true);
		// Add the button sub panel to the button panel.
		buttonPanel.add(buttonSubPanel, BorderLayout.EAST);
		// Set the visibility of the buttonPanel
		buttonPanel.setVisible(true);
		// Add action listener.
		submit.addActionListener(this);
		// Set the action command.
		submit.setActionCommand("submit");
		// This is the backup panel that contains all other elements of this
		// graphical user interface.
		JPanel mainPanel1 = new JPanel(new BorderLayout());
		JPanel subPanel = new JPanel(new BorderLayout());
		// Add mainPanel1.add(warningPanel,BorderLayout.CENTER);
		subPanel.add(warningPanel,BorderLayout.NORTH);
		// Add the radio buttons to the main panel.
		subPanel.add(mainRadio, BorderLayout.CENTER);
		// Add Cancel and Submit buttons.
		subPanel.add(buttonPanel,BorderLayout.SOUTH);
		// Add the mainPanel2 to the mainPanel 1.
		mainPanel1.add(mainPanel2, BorderLayout.NORTH);
		mainPanel1.add(subPanel,BorderLayout.SOUTH);
	
		// Surround the MainPanle2 with a raised etched border.
		mainPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		// Add the main panel to the frame.
		frame.add(mainPanel1);
		// Useless pack statement.
		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("submit")){
			System.out.println("submit");
			System.exit(0);
		}
		else {
			System.out.println("cancel");
			System.exit(0);
		}
	}	
}