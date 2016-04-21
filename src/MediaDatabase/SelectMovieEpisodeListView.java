package MediaDatabase;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class SelectMovieEpisodeListView extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Media> masterMediaList;
	
	public SelectMovieEpisodeListView(ArrayList<Media> masterMediaList) {
		this.masterMediaList = masterMediaList;
		ArrayList<String> titles = new ArrayList<String>();
		
		ArrayList<Episode> episodes;
		for(Media m: masterMediaList){
			if(m.getClass()==(new TVSeries().getClass())){
				episodes = ((TVSeries)m).getEpisodes();
				if(((TVSeries) m).getEpisodes()!=null){
					for(Episode e: episodes){
						titles.add(e.getTitle() + "  <Episode>");
					}
				}
			}
			else if (m.getClass()==(new Movie().getClass())){
				titles.add(m.getTitle() + "  <Movie>");
			}
		}
		
		String [] titleArray = new String[titles.size()];
		titles.toArray(titleArray);
		JList<String> titleJList = new JList<String>(titleArray);

		// This panel is a container for the two scroll panes necessary to show
		// the selection return data.
		JScrollPane mediaSelectPane = new JScrollPane(titleJList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		JSplitPane splitMediaPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mediaSelectPane, descriptionPanel);
		splitMediaPane.setResizeWeight(.9d);
		splitMediaPane.setLeftComponent(mediaSelectPane); // set media components
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