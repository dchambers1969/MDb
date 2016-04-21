// old selection view
package MediaDatabase;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is the selection view class that will display an interface for the user
 * to make selections to receive a desired out put.
 *
 */

public class SelectionView extends JPanel {
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -251377283773919734L;

	// Copied from old Selection View
	private MediaActionListView mediaActionListView;
	private MakerActionListView makerActionListView;
	
	/**
	 * This method builds the frame and panels for the selection view.
	 */
	public SelectionView() {
		JScrollPane makersList = new JScrollPane();
		JScrollPane mediaList = new JScrollPane();

		// Create New JFrame
		//JPanel frame = new JPanel();
		JPanel frame = this;
		
		// Set the size of the frame.
		//frame.setSize(500, 500);

		// Set the title of the frame.
		//frame.setTitle("Main Frame for Selection View from the SelectionView");

		// Make the frame visible to the user
		frame.setVisible(true);

		// Create the radio buttons
		JRadioButton movies = new JRadioButton("Movies");
		JRadioButton series = new JRadioButton("Series");
		JRadioButton episode = new JRadioButton("Episode");
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
		radioButtonGroupMaker.add(actor);
		radioButtonGroupMaker.add(director);
		radioButtonGroupMaker.add(producer);

		// This is the main panel container.
		JPanel mainPanel1 = new JPanel(new BorderLayout());

		// This is the backup panel that contains all other elements of this
		// graphical user interface.
		JPanel mainPanel2 = new JPanel(new BorderLayout());
		// Surround the MainPanle2 with a raised etched border.
		mainPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		// Create the Menu bar at the top of the panel.
		JMenuBar selectionMenuBar = new JMenuBar();
		//selectionMenuBar.setLayout(new GridLayout(1, 4));

		// Create combo boxes to the menu bar
		JMenu file = new JMenu("File");
		JMenu selectionEdit = new JMenu("Selection Edits");
		// Set the selection edit as disabled.
		selectionEdit.setEnabled(false);
		JMenu bulkEdit = new JMenu("Database Edits");
		// Set the selection edit as disabled.
		bulkEdit.setEnabled(false);
		JMenu display = new JMenu("Display");
		JMenu space1 = new JMenu("    "); // set selectable or visible to false
		space1.setEnabled(false);
		JMenu space2 = new JMenu("    ");
		space2.setEnabled(false);
		JMenu space3 = new JMenu("    ");
		space3.setEnabled(false);
		
		// Add the menu categories to the menu bar.
		selectionMenuBar.add(file);
		selectionMenuBar.add(space1);
		selectionMenuBar.add(selectionEdit);
		selectionMenuBar.add(space2);
		selectionMenuBar.add(bulkEdit);
		selectionMenuBar.add(space3);
		selectionMenuBar.add(display);

		// Create sub menu items for the File category
		JMenu download = new JMenu("Import");
		JMenu upload = new JMenu("Export");
		JMenuItem loadFavorites = new JMenuItem("Load Favorites");
		JMenuItem saveFavorites = new JMenuItem("Save To Favorites");

		// The download and upload sub menu items for the file category.
		JMenuItem binary = new JMenuItem("Binary");
		JMenu text = new JMenu("Text");

		JMenuItem binary1 = new JMenuItem("Binary");
		JMenu text1 = new JMenu("Text");

		// The sub menu items for text for the import for the file category.
		JMenuItem textImportMovie = new JMenuItem("Movie");
		JMenuItem textImportSeries = new JMenuItem("Series");
		JMenuItem textImportActor = new JMenuItem("Actor");
		JMenuItem textImportProducer = new JMenuItem("Producer");
		JMenuItem textImportDirector = new JMenuItem("Director");
		JMenuItem textImportAll = new JMenuItem("All");

		// Sub menu items for the text for the export file category.
		JMenuItem textExportMovie = new JMenuItem("Movie");
		JMenuItem textExportSeries = new JMenuItem("Series");
		JMenuItem textExportActor = new JMenuItem("Actor");
		JMenuItem textExportProducer = new JMenuItem("Producer");
		JMenuItem textExportDirector = new JMenuItem("Director");
		JMenuItem textExportAll = new JMenuItem("All");

		// Add upload sub menu items.
		upload.add(binary1);
		upload.add(text1);

		// Add download sub menu items.
		download.add(binary);
		download.add(text);

		// Add to the text menu of the import sub menu.
		text.add(textImportMovie);
		text.add(textImportSeries);
		text.add(textImportActor);
		text.add(textImportProducer);
		text.add(textImportDirector);
		text.add(textImportAll);

		// Add to the text menu of the export sub menu.
		text1.add(textExportMovie);
		text1.add(textExportSeries);
		text1.add(textExportActor);
		text1.add(textExportProducer);
		text1.add(textExportDirector);
		text1.add(textExportAll);

		// Add the submenu items to the file category
		file.add(download);
		file.add(upload);
		file.add(loadFavorites);
		file.add(saveFavorites);

		// Create the Display sub menu items
		JMenuItem pie = new JMenuItem("Pie Chart");
		JMenuItem chart = new JMenuItem("Histogram");

		// Add to the Display submenu.
		display.add(pie);
		display.add(chart);

		// Create the Edit sub menu items.
		JMenuItem clearSelection = new JMenuItem("Clear Selection");
		JMenuItem clearAll = new JMenuItem("Clear All");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem add = new JMenuItem("Add");
		JMenuItem editEntry = new JMenuItem("Edit");

		// Add the Selection Edit sub menu items.
		selectionEdit.add(clearSelection);
		selectionEdit.add(clearAll);
		selectionEdit.add(delete);
		selectionEdit.add(add);
		selectionEdit.add(editEntry);

		// Add the Bulk Edit sub menu items.
		selectionEdit.add(clearSelection);
		selectionEdit.add(clearAll);
		selectionEdit.add(delete);
		selectionEdit.add(add);
		selectionEdit.add(editEntry);

		// Add the menu bar to the panel.
		mainPanel2.add(selectionMenuBar, BorderLayout.NORTH);

		// This panel implements all of the radio buttons and their labels
		// relevant to the selections.
		JPanel radioPanel = new JPanel(new GridLayout(7, 1));

		// Add buttons to the panel vertically.
		radioPanel.add(movies);
		radioPanel.add(series);
		radioPanel.add(episode);
		radioPanel.add(radioSpace);
		radioPanel.add(actor);
		radioPanel.add(director);
		radioPanel.add(producer);
		// Set border.
		radioPanel.setBorder(BorderFactory.createEtchedBorder());

		// Temporary Data List. This list is to be populated by the database.
		String[] dataList1 = { "This", "is", "Temp", "Data", "List", "1", "This", "is", "Temp", "Data", "List", "1",
				"This", "is", "Temp", "Data", "List", "1", "This", "is", "Temp", "Data", "List", "1", "This", "is",
				"Temp", "Data", "List", "1", "This", "is", "Temp", "Data", "List", "1", "This", "is", "Temp", "Data",
				"List", "1", "This", "is", "Temp", "Data", "List", "1" };
		String[] dataList2 = { "This", "is", "Temp", "Data", "List", "2", "This", "is", "Temp", "Data", "List", "2",
				"This", "is", "Temp", "Data", "List", "2", "This", "is", "Temp", "Data", "List", "2", "This", "is",
				"Temp", "Data", "List", "2", "This", "is", "Temp", "Data", "List", "2", "This", "is", "Temp", "Data",
				"List", "2", "This", "is", "Temp", "Data", "List", "2" };

		JList<String> tempDataJList1 = new JList<String>(dataList1);
		JList<String> tempDataJList2 = new JList<String>(dataList2);

		// This panel is a container for the two scroll panes necessary to show
		// the selection return data.
		JScrollPane leftPane = new JScrollPane(tempDataJList1);
		JScrollPane makerCreditsPane = new JScrollPane(tempDataJList2);
		JSplitPane splitMediaPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, makersList, mediaList);
		splitMediaPane.setResizeWeight(.5d);
		splitMediaPane.setLeftComponent(leftPane); // set media components
		splitMediaPane.setRightComponent(makerCreditsPane); // set maker credits

		// Add the main panel to the frame.
		frame.add(mainPanel1);
		// Add the main panel 2 to main panel 1.
		mainPanel1.add(mainPanel2, BorderLayout.CENTER);
		// Add the radio panel to main panel 2.
		mainPanel2.add(radioPanel, BorderLayout.WEST);
		// Add the Split pane to main panel 2.
		mainPanel2.add(splitMediaPane, BorderLayout.CENTER);

		//frame.pack();
		frame.setVisible(true);

		/**frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});*/
	}
	
		public void actionPerformed(){
		// TODO
	}
}


/**
// This is the Driver class
import javax.swing.SwingUtilities;

public class Driver {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SelectionView();
			}
		});
	}
}
*/
