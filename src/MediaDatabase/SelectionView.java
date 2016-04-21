package MediaDatabase;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is the selection view class that will display an interface for the user
 * to make selections to receive a desired out put.
 *
 */

public class SelectionView extends JPanel implements ActionListener {
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -251377283773919734L;

	//
	
	/**
	 * This method builds the frame and panels for the selection view.
	 */
	public SelectionView() {
		
		JPanel radioPanel = new JPanel(new GridLayout(8, 1));
		
		// Create the radio buttons
		JRadioButton movies = new JRadioButton("Movies");
		JRadioButton series = new JRadioButton("Series");
		JRadioButton episode = new JRadioButton("Episode");
		JRadioButton person = new JRadioButton("Person");
		JRadioButton actor = new JRadioButton("Actor");
		JRadioButton director = new JRadioButton("Director");
		JRadioButton producer = new JRadioButton("Producer");
		JRadioButton radioSpace = new JRadioButton("");
		radioSpace.setVisible(false);
		
		// Create the radio button group Media that will contain the radio
		// buttons and their labels.
		ButtonGroup radioButtonGroupMedia = new ButtonGroup();

		// Add the radio buttons to the Media radio button group.
		radioButtonGroupMedia.add(movies);
		radioButtonGroupMedia.add(series);
		radioButtonGroupMedia.add(episode);

		// Create the radio button group Maker that will contain the radio
		// buttons and their labels.
		ButtonGroup radioButtonGroupMaker = new ButtonGroup();

		// Add the radio buttons to the Maker radio button group.
		radioButtonGroupMaker.add(person);
		radioButtonGroupMaker.add(actor);
		radioButtonGroupMaker.add(director);
		radioButtonGroupMaker.add(producer);

		// Add buttons to the panel vertically.
		radioPanel.add(movies);
		radioPanel.add(series);
		radioPanel.add(episode);
		radioPanel.add(radioSpace);
		radioPanel.add(person);
		radioPanel.add(actor);
		radioPanel.add(director);
		radioPanel.add(producer);
		// Set border.
		radioPanel.setBorder(BorderFactory.createEtchedBorder());
		radioPanel.setVisible(true);
		
		add(radioPanel);
	}
	
		public void actionPerformed(ActionEvent e){
			
	}
}