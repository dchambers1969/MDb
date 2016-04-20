package MediaDatabase;
import java.awt.BorderLayout;
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

public class NewOrExistingSeriesView extends JFrame implements ActionListener{

	public NewOrExistingSeriesView() {
		// Create the radio buttons
		
		JFrame frame = new JFrame();

		// Set the size of the frame.
		frame.setSize(400, 300);
		frame.setTitle("Select New or Existing Series.");
		frame.setVisible(true);
		JLabel pickSeriesSelection = new JLabel("  Select where you want the new Episode:      ");
		JRadioButton existingSeries = new JRadioButton("Choose Existing Series");
		JRadioButton newSeries = new JRadioButton("Create New Series");
		
		ButtonGroup radioButtonSeriesType = new ButtonGroup();
		radioButtonSeriesType.add(existingSeries);
		radioButtonSeriesType.add(newSeries);
	
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel radioPanel = new JPanel(new BorderLayout());
		mainPanel.add(pickSeriesSelection, BorderLayout.NORTH);
		radioPanel.add(existingSeries, BorderLayout.NORTH);
		radioPanel.add(newSeries,BorderLayout.SOUTH);
		mainPanel.add(radioPanel, BorderLayout.CENTER);
		
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
		
		mainPanel.add(buttonPanel,BorderLayout.SOUTH);
		frame.add(mainPanel);
		
		frame.pack();

		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
