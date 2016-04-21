package MediaDatabase;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuView extends JMenuBar {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1465386031787574064L;

	public MenuView() {
		// Create the Menu bar at the top of the panel.
		JMenuBar selectionMenuBar = this;
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
		display.setEnabled(false);
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
		JMenuItem clearDisplay = new JMenuItem("Clear");

		// Add to the Display submenu.
		display.add(pie);
		display.add(chart);
		display.add(clearDisplay);

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

		// Create the Bulk Edit sub menu items.
		JMenuItem addEditEpisode = new JMenuItem("Add/Edit Episode");
		JMenuItem addEditSeries = new JMenuItem("Add/Edit Series");
		JMenuItem addEditMovie = new JMenuItem("Add/Edit Movie");
		JMenuItem addEditMaker = new JMenuItem("Add/Edit Maker");
		JMenuItem addCredits = new JMenuItem("Add Credits to Maker");
		
		
		// Add the Bulk Edit sub menu items.
		bulkEdit.add(addEditEpisode);
		bulkEdit.add(addEditSeries);
		bulkEdit.add(addEditMovie);
		bulkEdit.add(addEditMaker);
		bulkEdit.add(addCredits);
	}
}