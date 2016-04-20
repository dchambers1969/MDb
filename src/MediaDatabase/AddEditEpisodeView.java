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

public class AddEditEpisodeView extends JFrame implements ActionListener {

	public AddEditEpisodeView(TVSeries seriesToUpdate, Episode episodeToEdit){
		
		boolean newSeriesFlag = false;

		
		// Create New JFrame
		JFrame frame = new JFrame();

		// Set the size of the frame.
		frame.setSize(400, 300);

		// Set the title of the frame based on if sent a non null mediaMaker.
		if(episodeToEdit.getTitle() == null){
			if(seriesToUpdate.getTitle() == null){
				frame.setTitle("Add New Series and New Episode");
				newSeriesFlag = true;
			}
			else {
				frame.setTitle("Add New Episode to " + seriesToUpdate.getTitle() + " series");
				newSeriesFlag = false;
			}
		}
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create the text fields for episode info (populate if an edit)
	
		JTextField seriesTitleField = new JTextField(seriesToUpdate.getTitle(),20);
		JLabel seriesTitleLabel = new JLabel("TV Series Title: ");
		
		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setGroupingUsed(false);
		NumberFormatter integerYearFormat = new NumberFormatter(format);
		integerYearFormat.setValueClass(Integer.class);
		integerYearFormat.setMinimum(1910);
		integerYearFormat.setMaximum(2018);
		integerYearFormat.setCommitsOnValidEdit(true);
		JFormattedTextField releaseYear = new JFormattedTextField(integerYearFormat);
		releaseYear.setValue(seriesToUpdate.getReleaseYear());//seriesToEdit.getReleaseYear());
		
		releaseYear.setSize(10,10);
		JLabel releaseYearLabel1 = new JLabel("Release Year: ");
		JLabel releaseYearLabel2 = new JLabel("Valid release years are 1910 - 2018.");
		
		if(releaseYear.getValue()!=null){
			Integer value = Integer.parseInt(releaseYear.getValue().toString());
			integerYearFormat.setMinimum(value);
		}
			
		
		JFormattedTextField endingYear = new JFormattedTextField(integerYearFormat);
		JLabel endingYearLabel1 = new JLabel("Ending Year: ");
		endingYear.setValue(seriesToUpdate.getEndingYear());
		System.out.println(seriesToUpdate.getEndingYear());
		endingYear.setSize(10,10);
		JLabel endingYearLabel2 = new JLabel("Valid entries for ending year > release or blank.");
		
		//allow editing if new series is to be created for new episode
		seriesTitleField.setEditable(newSeriesFlag);
		releaseYear.setEditable(newSeriesFlag);
		endingYear.setEditable(newSeriesFlag);
			
		JPanel seriesPanel = new JPanel(new GridBagLayout());
		JPanel warningPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
			
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30,5,5,5);
 		seriesPanel.add(seriesTitleLabel,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
		seriesPanel.add(seriesTitleField, c);
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 35;
		c.insets = new Insets(5,5,5,5);
		seriesPanel.add(releaseYearLabel1,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		seriesPanel.add(releaseYear, c);
		
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		seriesPanel.add(endingYearLabel1,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		seriesPanel.add(endingYear, c);
		
		
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 0;
		c.anchor = GridBagConstraints.WEST;
		warningPanel.add(releaseYearLabel2,c);
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		warningPanel.add(endingYearLabel2,c);
		
		//Episode Title
		JTextField episodeTitle = new JTextField(episodeToEdit.getTitle(),20);
		JLabel episodeTitleLabel = new JLabel("Episode Title: ");
		
		//Episode Release Year
		JFormattedTextField episodeReleaseYear = new JFormattedTextField(integerYearFormat);
		JLabel episodeYearLabel1 = new JLabel("Episode Release Year: ");
		episodeReleaseYear.setValue(episodeToEdit.getReleaseYear());
		
		episodeReleaseYear.setSize(10,10);
		JLabel episodeReleaseYearLabel2 = new JLabel("Valid entries for episode year are to be within series release/ending window or blank.");
		
		
		//Episode Year Run
		NumberFormatter integerSeriesYearFormat = new NumberFormatter(format);
		integerSeriesYearFormat.setValueClass(Integer.class);
		integerSeriesYearFormat.setMinimum(1);
		integerSeriesYearFormat.setMaximum(50);
		integerSeriesYearFormat.setCommitsOnValidEdit(true);
		JFormattedTextField seriesYear = new JFormattedTextField(integerSeriesYearFormat);
		seriesYear.setValue(episodeToEdit.getSeriesYear());
		seriesYear.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel broadcastInfoLabel = new JLabel("                          Episode Broadcast Details");
		JLabel seriesYearEpisodeLabel = new JLabel("                        Series Year (1-50)                     Episode # (1-365)");
		
		//Episode # for Given Year
		NumberFormatter integerEpisodeNumberFormat = new NumberFormatter(format);
		integerSeriesYearFormat.setValueClass(Integer.class);
		integerSeriesYearFormat.setMinimum(1);
		integerSeriesYearFormat.setMaximum(365);
		integerSeriesYearFormat.setCommitsOnValidEdit(true);
		JFormattedTextField episodeNumber = new JFormattedTextField(integerSeriesYearFormat);
		episodeNumber.setValue(episodeToEdit.getEpisodeNumber());
		episodeNumber.setHorizontalAlignment(JTextField.CENTER);
		
		
		
		//Episode Airing Date
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		JFormattedTextField episodeDate = new JFormattedTextField(dateFormat);
		episodeDate.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel airDateLabel = new JLabel("Episode Air Date (yyyy-mm-dd): ");
		
		String dateString = "";
		
		if(episodeToEdit.getEpisodeDate()!=null){
			dateString += episodeToEdit.getEpisodeDate().getMonth();
			dateString += "-";
			dateString += episodeToEdit.getEpisodeDate().getDay();
			dateString += "-";
			dateString += episodeToEdit.getEpisodeDate().getYear();
			;
			try {
				episodeDate.setValue(new SimpleDateFormat("MM-dd-yyyy").parse(dateString));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String[] statusChoices = {"UNSPECIFIED", "SUSPENDED", "NO STATUS"};
		JList statusJList = new JList(statusChoices);
		JScrollPane statusPane = new JScrollPane(statusJList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JLabel statusLabel = new JLabel("Special Status :");
		statusJList.setPreferredSize(new Dimension(20,3));
		if(episodeToEdit.getStatus()!=null){
			if(episodeToEdit.getStatus().equalsIgnoreCase(statusChoices[0]))
				statusJList.setSelectedIndex(0);
			else if(episodeToEdit.getStatus().equalsIgnoreCase(statusChoices[1]))
				statusJList.setSelectedIndex(1);
			else
				statusJList.setSelectedIndex(2);
		}
		else
			statusJList.setSelectedIndex(2);
		
		JPanel episodeInfoPanel = new JPanel(new GridBagLayout());
		JPanel episodePanel = new JPanel(new BorderLayout());
		JPanel episodeSubPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30,5,5,5);
 		episodeSubPanel.add(episodeTitleLabel,c);
 		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
 		episodeSubPanel.add(episodeTitle,c);
 		
 		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 35;
		c.insets = new Insets(5,5,5,5);
 		episodeSubPanel.add(episodeYearLabel1,c);
 		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
 		episodeSubPanel.add(episodeReleaseYear,c);
 		
 		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 30;
		c.ipadx = 0;
		c.insets = new Insets(5,5,5,5);
 		episodeInfoPanel.add(episodeReleaseYearLabel2,c);
 		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 0;
		c.ipadx = 30;
		c.insets = new Insets(5,5,5,5);
 		episodeInfoPanel.add(broadcastInfoLabel,c);
 		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 0;
		c.insets = new Insets(5,5,5,5);
 		episodeInfoPanel.add(seriesYearEpisodeLabel,c);
 		
 		JPanel episodeDetails = new JPanel(new GridBagLayout());
 		c = new GridBagConstraints();
 		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 20;
		c.insets = new Insets(5,5,5,5);
 		episodeDetails.add(seriesYear,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
		episodeDetails.add(episodeNumber, c);
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 80;
		c.insets = new Insets(5,5,5,5);
		episodeDetails.add(airDateLabel,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		episodeDetails.add(episodeDate, c);
		
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		episodeDetails.add(statusLabel,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		c.ipadx = 60;
		c.ipady = -90;
		episodeDetails.add(statusPane, c);
 		
		episodePanel.add(episodeSubPanel, BorderLayout.NORTH);
		episodePanel.add(episodeInfoPanel, BorderLayout.CENTER);
		episodePanel.add(episodeDetails, BorderLayout.SOUTH);
		/* Integer seriesYear; // Integer representation of the 1 for Year (#1.08)
		private String episodeDetails;
		private Integer episodeNumber; //Integer representation of the 08 for Episode # (#1.08)
		private Date episodeDate;//Object for date episode aired
		*/
		
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
		
		mainPanel1.add(seriesPanel,BorderLayout.NORTH);
		//mainPanel1.add(warningPanel,BorderLayout.CENTER);
		mainPanel1.add(episodePanel, BorderLayout.CENTER);
		if(newSeriesFlag)
			mainPanel1.add(warningPanel,BorderLayout.CENTER);
		mainPanel1.add(buttonPanel,BorderLayout.SOUTH);
		
		mainPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		frame.add(mainPanel1);
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
