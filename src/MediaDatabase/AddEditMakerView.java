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

public class AddEditMakerView extends JFrame implements ActionListener{

	public AddEditMakerView(String makerTypeToAdd, MediaMaker mediaMaker){
		// Create New JFrame
		JFrame frame = new JFrame();

		// Set the size of the frame.
		frame.setSize(400, 300);

		// Set the title of the frame based on if sent a non null mediaMaker.
		if(mediaMaker == null)
			frame.setTitle("Add " + makerTypeToAdd + " Media Maker.");
		else
			frame.setTitle("Edit " + makerTypeToAdd);
		
		// Make the frame visible to the user
		frame.setVisible(true);

		//Create the text fields for name (first and last name) Populate if Edit
		
		JTextField firstName = new JTextField(mediaMaker.getMakerFirstName(),15);
		JLabel firstNameLabel = new JLabel(makerTypeToAdd + " First Name: ");
		JTextField lastName = new JTextField(mediaMaker.getMakerFamilyName(),15);
		JLabel lastNameLabel = new JLabel(makerTypeToAdd + " Last Name: ");
		
		
		//Create Scroll list for acceptable suffixes
		JLabel suffixLabel = new JLabel(makerTypeToAdd + " Suffix: ");
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
		JPanel mainPanel2 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
			
		
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30,5,5,5);
 		mainPanel2.add(firstNameLabel,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
		mainPanel2.add(firstName, c);
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5,5,5,5);
		mainPanel2.add(lastNameLabel,c);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		mainPanel2.add(lastName, c);
		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		mainPanel2.add(suffixLabel,c);
		c.fill = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridy = 2;
		mainPanel2.add(suffix, c);
		
		// Add Cancel and Submit buttons to the 
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		JButton submit = new JButton("Submit");
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
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
		
		mainPanel1.add(mainPanel2,BorderLayout.NORTH);
		mainPanel1.add(buttonPanel,BorderLayout.SOUTH);
		
		mainPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		frame.add(mainPanel1);
		
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
