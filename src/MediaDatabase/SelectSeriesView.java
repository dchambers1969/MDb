package MediaDatabase;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class SelectSeriesView extends JFrame implements ActionListener {

	public SelectSeriesView(ArrayList<TVSeries> seriesList) {
		String[] titles = new String[(seriesList.size())];
		int i = 0;
		for(TVSeries series: seriesList){
			titles[i]=series.getTitle();
			i++;
		}
		JList<String> titleJList = new JList<String>(titles);
		

		// This panel is a container for the two scroll panes necessary to show
		// the selection return data.
		JScrollPane seriesSelectPane = new JScrollPane(titleJList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel descriptionPanel = new JPanel(new BorderLayout());
		JTextArea seriesDescription = new JTextArea(4,5);
		seriesDescription.setMargin(new Insets(10,10,10,10));
		seriesDescription.setLineWrap(true);
		seriesDescription.setWrapStyleWord(true);
		seriesDescription.setText("Select series to add new Episode to from the list on the left.");
		seriesDescription.setEditable(false);
		descriptionPanel.add(seriesDescription, BorderLayout.NORTH);
		JPanel spacePanel = new JPanel();
		descriptionPanel.add(spacePanel,BorderLayout.CENTER);
		JSplitPane splitMediaPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, seriesSelectPane, descriptionPanel);
		splitMediaPane.setResizeWeight(.9d);
		splitMediaPane.setLeftComponent(seriesSelectPane); // set media components
		splitMediaPane.setRightComponent(descriptionPanel); // set maker credits
		// Create New JFrame
		JFrame frame = new JFrame();

		// Set the size of the frame.
		frame.setSize(400, 300);

		// Set the title of the frame.
		frame.setTitle("Selection Series View");

		

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
		
		
		// Surround the MainPanle2 with a raised etched border.
		
		
		//mainPanel1.add(warningPanel,BorderLayout.CENTER);
		descriptionPanel.add(buttonPanel,BorderLayout.SOUTH);
		
		// Make the frame visible to the user
		frame.add(splitMediaPane);
		frame.setVisible(true);
		}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
