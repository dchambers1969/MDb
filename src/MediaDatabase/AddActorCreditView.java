package MediaDatabase;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;

public class AddActorCreditView extends JPanel implements ActionListener {
	
	private Media media;
	private String makerName;
	
	public AddActorCreditView(){
		boolean movieFlag;
		String typeMedia;
		
		if(media.getClass() == (new Movie().getClass())){
			movieFlag = true;
			typeMedia = "movie";
			System.out.println(typeMedia + "Crazy");
		}
		else{
			movieFlag = false;
			typeMedia = "episode.";
		}		
		
		JLabel panelLabel1 = new JLabel("Add Actor Credits for " + makerName);
		JLabel panelLabel2 = new JLabel("to the following " + typeMedia);
		
		JPanel titlePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15,5,5,5);
		titlePanel.add(panelLabel1,c);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,5,15,5);
		titlePanel.add(panelLabel2,c);
		JPanel mediaPanel = new JPanel(new BorderLayout());
		
		//Create the text fields for series info (populate if an edit
		if(movieFlag){//Show movie title and release year
			System.out.println(((Movie)media).getTitle());
			// Create the text fields for series info (populate if an edit).
			JTextField movieTitleField = new JTextField(((Movie)media).getTitle(),15);
			movieTitleField.setHorizontalAlignment(JTextField.CENTER);
			// Create label for the title field.
			JLabel movieTitleLabel = new JLabel("Movie Title: ");
			JTextField releaseYear = new JTextField(((Movie)media).getReleaseYear().toString());
			releaseYear.setHorizontalAlignment(JTextField.CENTER);
			
			releaseYear.setSize(10,10);
			JLabel releaseYearLabel1 = new JLabel("Release Year: ");
			
			movieTitleField.setEditable(false);
			releaseYear.setEditable(false);
			JPanel movieSubPanel = new JPanel(new GridBagLayout());
			
			// Create the Grid bag constraints variable;
			c = new GridBagConstraints();
			// Set the grid bag main panel 2 layout.
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.FIRST_LINE_START;
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(30,5,5,5);
	 		movieSubPanel.add(movieTitleLabel,c);
	 		
	 		// Set the grid bag main panel 2 layout.
			c.fill = GridBagConstraints.RELATIVE;
			c.gridx = 1;
			c.gridy = 0;
			movieSubPanel.add(movieTitleField, c);
			
			// Set the grid bag main panel 2 parameters.
			c.fill = GridBagConstraints.FIRST_LINE_START;
			c.gridx = 0;
			c.gridy = 1;
			c.insets = new Insets(5,5,5,5);
			movieSubPanel.add(releaseYearLabel1,c);
			
			// Set the grid bag main panel parameters.
			c.fill = GridBagConstraints.RELATIVE;
			c.gridx = 1;
			c.gridy = 1;
			movieSubPanel.add(releaseYear, c);
			
			mediaPanel.add(titlePanel,BorderLayout.NORTH);
			mediaPanel.add(movieSubPanel,BorderLayout.SOUTH);
		}
		else{
			String seriesName = ((Episode)media).getUniqueSeriesTitle().trim();
			seriesName.replaceAll("\"", "");	
			JTextField seriesTitleField = new JTextField(seriesName,20);
		
			seriesTitleField.setHorizontalAlignment(JTextField.CENTER);
			JLabel seriesTitleLabel = new JLabel("TV Series Title: ");
	
			seriesTitleField.setEditable(false);
			
			JPanel seriesPanel = new JPanel(new GridBagLayout());
					
			c = new GridBagConstraints();
				
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.FIRST_LINE_START;
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(5,5,5,5);
			seriesPanel.add(seriesTitleLabel,c);
			c.fill = GridBagConstraints.CENTER;
			c.gridx = 1;
			c.gridy = 0;
			seriesPanel.add(seriesTitleField, c);
			
			//Episode Title
			System.out.println(((Episode)media).getTitle());
			JTextField episodeTitle = new JTextField(((Episode)media).getTitle(),20);
			episodeTitle.setHorizontalAlignment(JTextField.CENTER);
			JLabel episodeTitleLabel = new JLabel("Episode Title: ");
			episodeTitle.setEditable(false);
			
			//Episode Release Year
			JTextField episodeReleaseYear = new JTextField(((Episode)media).getReleaseYear().toString(),20);
			episodeReleaseYear.setHorizontalAlignment(JTextField.CENTER);
			JLabel episodeYearLabel1 = new JLabel("Episode Release Year: ");
			episodeReleaseYear.setSize(10,10);
			episodeReleaseYear.setEditable(false);
			
			//Episode Year Run
			JTextField seriesYear = new JTextField(((Episode)media).getSeriesYear().toString());
			JLabel broadcastInfoLabel = new JLabel("Episode Broadcast Details");
			JLabel seriesYearEpisodeLabel = new JLabel("Series Year     Episode #");
			seriesYear.setEditable(false);
			
			//Episode # for Given Year
			System.out.println(((Episode)media).getEpisodeNumber());
			JTextField episodeNumber = new JTextField(((Episode)media).getEpisodeNumber().toString());
			episodeNumber.setHorizontalAlignment(JTextField.CENTER);
			episodeNumber.setEditable(false);
						
			//Episode Airing Date
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			JFormattedTextField episodeDate = new JFormattedTextField(dateFormat);
			episodeDate.setHorizontalAlignment(JTextField.CENTER);
			
			JLabel airDateLabel = new JLabel("Episode Air Date: ");

			String dateString = "";
			
			if(((Episode)media).getEpisodeDate()!=null){//show 
				dateString += ((Episode)media).getEpisodeDate().getMonth();
				dateString += "-";
				dateString += ((Episode)media).getEpisodeDate().getDay();
				dateString += "-";
				dateString += ((Episode)media).getEpisodeDate().getYear();
				;
				try {
					episodeDate.setValue(new SimpleDateFormat("MM-dd-yyyy").parse(dateString));
				} catch (ParseException e) {
					episodeDate.setValue(null);
				}
			}
			episodeDate.setEditable(false);
			
			JPanel episodeInfoPanel = new JPanel(new GridBagLayout());
			JPanel episodePanel = new JPanel(new BorderLayout());
			JPanel episodeSubPanel = new JPanel(new GridBagLayout());
			c = new GridBagConstraints();
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.FIRST_LINE_START;
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(5,5,5,5);
			episodeSubPanel.add(episodeTitleLabel,c);
			c.gridx = 1;
			c.gridy = 0;
			episodeSubPanel.add(episodeTitle,c);
			c.gridx = 0;
			c.gridy = 1;
			episodeSubPanel.add(episodeYearLabel1,c);
			c.gridx = 1;
			c.gridy = 1;
			episodeSubPanel.add(episodeReleaseYear,c);

			c.fill = GridBagConstraints.CENTER;
			c.gridx = 0;
			c.gridy = 0;
			episodeInfoPanel.add(broadcastInfoLabel,c);
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.CENTER;
			c.gridx = 0;
			c.gridy = 1;
			c.insets = new Insets(5,5,5,5);
			episodeInfoPanel.add(seriesYearEpisodeLabel,c);
			
			JPanel episodeDetails = new JPanel(new GridBagLayout());
			c = new GridBagConstraints();
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.CENTER;
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(5,5,5,5);
			episodeDetails.add(seriesYear,c);
			c.fill = GridBagConstraints.RELATIVE;
			c.gridx = 1;
			c.gridy = 0;
			episodeDetails.add(episodeNumber, c);
			c.fill = GridBagConstraints.WEST;
			c.gridx = 0;
			c.gridy = 1;
			episodeDetails.add(airDateLabel,c);
			c.fill = GridBagConstraints.RELATIVE;
			c.gridx = 1;
			c.gridy = 1;
			episodeDetails.add(episodeDate, c);
			
			episodePanel.add(episodeSubPanel, BorderLayout.NORTH);
			episodePanel.add(episodeInfoPanel, BorderLayout.CENTER);
			episodePanel.add(episodeDetails, BorderLayout.SOUTH);
		
			mediaPanel.add(titlePanel,BorderLayout.NORTH);
			mediaPanel.add(seriesPanel,BorderLayout.CENTER);
			mediaPanel.add(episodePanel,BorderLayout.SOUTH);
		}
			
		JPanel actorPanel = new JPanel(new BorderLayout());
		JLabel roleLabel = new JLabel("Actor Role: ");
		JTextField role = new JTextField("",15);
		role.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel listingLabel = new JLabel("Listing Order #: ");
		NumberFormat format = NumberFormat.getIntegerInstance();
		NumberFormatter integerSeriesYearFormat = new NumberFormatter(format);
		integerSeriesYearFormat.setValueClass(Integer.class);
		integerSeriesYearFormat.setMinimum(1);
		integerSeriesYearFormat.setMaximum(15);
		integerSeriesYearFormat.setCommitsOnValidEdit(true);
		JFormattedTextField listing = new JFormattedTextField(integerSeriesYearFormat);
		listing.setSize(10,10);
		listing.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel detailsLabel1 = new JLabel("Additional Details for Credits");
		JLabel detailsLabel2 = new JLabel("Select all that apply.  If selecting 'other', fill in field: ");
		JRadioButton voice = new JRadioButton("Voice");
		JRadioButton archivedFootage = new JRadioButton("Archived Footage");
		JRadioButton rumored = new JRadioButton("Rumored");
		JRadioButton uncredited = new JRadioButton("Uncredited");
		JRadioButton other = new JRadioButton("Other");
		JTextField otherDetails = new JTextField("",15);
		otherDetails.setHorizontalAlignment(JTextField.CENTER);
		
		JPanel instructionPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(30,5,5,5);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		instructionPanel.add(detailsLabel1,c);
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 1;
		instructionPanel.add(detailsLabel2,c);
		
		
		JPanel actorSubPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		actorSubPanel.add(roleLabel,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		actorSubPanel.add(role,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 1;
		actorSubPanel.add(listingLabel,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 20;
		actorSubPanel.add(listing,c);
		c.fill = GridBagConstraints.WEST;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 2;
		actorSubPanel.add(voice,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 2;
		actorSubPanel.add(archivedFootage,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 3;
		actorSubPanel.add(rumored,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 3;
		actorSubPanel.add(uncredited,c);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 4;
		actorSubPanel.add(other,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 4;
		actorSubPanel.add(otherDetails,c);
		
		actorPanel.add(instructionPanel,BorderLayout.NORTH);
		actorPanel.add(actorSubPanel,BorderLayout.SOUTH);
		// Add Cancel and Submit buttons to the 
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		JButton submit = new JButton("Submit");
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JPanel buttonSubPanel = new JPanel();
		
		buttonSubPanel.add(cancel);
		buttonSubPanel.add(submit);
		buttonPanel.add(buttonSubPanel, BorderLayout.EAST);
		
		submit.addActionListener(this);
		submit.setActionCommand("submit");
		// This is the backup panel that contains all other elements of this
		// graphical user interface.
		JPanel mainPanel1 = new JPanel(new BorderLayout());
		// Surround the MainPanle2 with a raised etched border.
		
		mainPanel1.add(mediaPanel,BorderLayout.NORTH);
		//mainPanel1.add(warningPanel,BorderLayout.CENTER);
		mainPanel1.add(actorPanel, BorderLayout.CENTER);
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