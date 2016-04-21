package MediaDatabase;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MasterMediaListView extends JScrollPane implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 760461308455101663L;
	private ArrayList<Media> masterMediaList = new ArrayList<Media>();

	public MasterMediaListView() {

		ArrayList<String> titles = new ArrayList<String>();
		String [] titleArray = new String[titles.size()];
		JList<String> titleJList = new JList<String>();

		ArrayList<Episode> episodes;
		if (!masterMediaList.isEmpty()) {
			for(Media m: masterMediaList){
				if(m.getClass()==(new TVSeries().getClass())){
					titles.add(m.getTitle() + "  <TV Series>");
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

			titles.toArray(titleArray);
			titleJList = new JList<String>(titleArray);

			// This panel is a container for the two scroll panes necessary to show
			// the selection return data.
			JScrollPane mediaSelectPane = new JScrollPane(titleJList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			add(mediaSelectPane);
		} else {
			JScrollPane pane1 = new JScrollPane(new JList<String>(),ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			pane1.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/6, Toolkit.getDefaultToolkit().getScreenSize().height/2);
			pane1.setVisible(true);
			pane1.repaint();
			add(pane1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("submit")){
			
		}
		else {
			
		}
				
	}	

}