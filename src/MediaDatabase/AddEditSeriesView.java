package MediaDatabase;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.text.NumberFormatter;

public class AddEditSeriesView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddEditSeriesView(TVSeries seriesToEdit){
				
		// Create New JFrame
		JFrame frame = new JFrame();

		// Set the size of the frame.
		frame.setSize(400, 300);

		// Set the title of the frame based on if sent a non null mediaMaker.
		if(seriesToEdit == null)
			frame.setTitle("Add Series");
		else
			frame.setTitle("Edit " + seriesToEdit.getTitle());
		
		// Make the frame visible to the user
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			
		
		//Create the text fields for series info (populate if an edit
	
		JTextField seriesTitleField = new JTextField(seriesToEdit.getTitle(),20);
		JLabel seriesTitleLabel = new JLabel("TV Series Title: ");
		
		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setGroupingUsed(false);
		NumberFormatter integerYearFormat = new NumberFormatter(format);
		integerYearFormat.setValueClass(Integer.class);
		integerYearFormat.setMinimum(1910);
		integerYearFormat.setMaximum(2018);
		integerYearFormat.setCommitsOnValidEdit(true);
		JFormattedTextField releaseYear = new JFormattedTextField(integerYearFormat);
		releaseYear.setValue(seriesToEdit.getReleaseYear());//seriesToEdit.getReleaseYear());
		
		releaseYear.setSize(10,10);
		JLabel releaseYearLabel1 = new JLabel("Release Year: ");
		JLabel releaseYearLabel2 = new JLabel("Valid release years are 1910 - 2018.");
		
		if(releaseYear.getValue()!=null){
			Integer value = Integer.parseInt(releaseYear.getValue().toString());
			integerYearFormat.setMinimum(value);
		}
			
		
		JFormattedTextField endingYear = new JFormattedTextField(integerYearFormat);
		JLabel endingYearLabel1 = new JLabel("Ending Year: ");
		endingYear.setValue(seriesToEdit.getEndingYear());
		System.out.println(seriesToEdit.getEndingYear());
		endingYear.setSize(10,10);
		JLabel endingYearLabel2 = new JLabel("Valid entries for ending year > release or blank.");
		
		
		JPanel mainPanel2 = new JPanel(new GridBagLayout());
		JPanel warningPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
			
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30,5,5,5);
 		mainPanel2.add(seriesTitleLabel,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
		mainPanel2.add(seriesTitleField, c);
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 35;
		c.insets = new Insets(5,5,5,5);
		mainPanel2.add(releaseYearLabel1,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		mainPanel2.add(releaseYear, c);
		
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		mainPanel2.add(endingYearLabel1,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		mainPanel2.add(endingYear, c);
		
		
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
		
		mainPanel1.add(mainPanel2,BorderLayout.NORTH);
		//mainPanel1.add(warningPanel,BorderLayout.CENTER);
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