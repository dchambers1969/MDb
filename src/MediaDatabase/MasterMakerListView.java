package MediaDatabase;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MasterMakerListView extends JScrollPane implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8687420756511535047L;
	
	private ArrayList<MediaMaker> makersList = new ArrayList<MediaMaker>();

	public MasterMakerListView() {

		HashSet<String> makerSet = new HashSet<String>();
		for(MediaMaker m: makersList){
			makerSet.add(m.getMakerName());
		}

		String[] makers = new String[makerSet.size()];
		makerSet.toArray(makers);
		JList<String> uniqueMakers = new JList<String>(makers);


		// This panel is a container for the two scroll panes necessary to show
		// the selection return data.
		JScrollPane seriesSelectPane = new JScrollPane(uniqueMakers,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		seriesSelectPane.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/6, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		seriesSelectPane.setVisible(true);
		add(seriesSelectPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
	}

}