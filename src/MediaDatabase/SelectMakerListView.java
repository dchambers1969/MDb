package MediaDatabase;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class SelectMakerListView extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<MediaMaker> makersList;
	
	public SelectMakerListView(ArrayList<MediaMaker> makersList) {
		
		HashSet<String> makerSet = new HashSet<String>();
		for(MediaMaker m: makersList){
			makerSet.add(m.getMakerName());
		}
		
		String[] makers = new String[makerSet.size()];
        makerSet.toArray(makers);
		JList<String> uniqueMakers = new JList<String>(makers);
		
		// This panel is a container for the two scroll panes necessary to show
		// the selection return data.
		JScrollPane makersSelectPane = new JScrollPane(uniqueMakers,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		makersSelectPane.setVisible(true);
		
		JPanel descriptionPanel = new JPanel(new BorderLayout());
		JTextArea seriesDescription = new JTextArea(4,5);
		seriesDescription.setMargin(new Insets(10,10,10,10));
		seriesDescription.setLineWrap(true);
		seriesDescription.setWrapStyleWord(true);
		seriesDescription.setText("Select Maker to which a credit will be added.");
		seriesDescription.setEditable(false);
		descriptionPanel.add(seriesDescription, BorderLayout.NORTH);
		JPanel spacePanel = new JPanel();
		descriptionPanel.add(spacePanel,BorderLayout.CENTER);
		JSplitPane splitMediaPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, makersSelectPane, descriptionPanel);
		splitMediaPane.setResizeWeight(.9d);
		splitMediaPane.setLeftComponent(makersSelectPane); // set media components
		splitMediaPane.setRightComponent(descriptionPanel); // set maker credits
		
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

		descriptionPanel.add(buttonPanel,BorderLayout.SOUTH);
		add(splitMediaPane);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("submit")){
			
		}
		else {
	
		}
	}
}