package MediaDatabase;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuView extends JMenuBar {

	private static final long serialVersionUID = -1465386031787574064L;

	// Main Menu Bar
	JMenuBar selectionMenuBar = this;
	JMenu file = new JMenu("File");
	JMenu selectionEdit = new JMenu("Selection Edits");
	JMenu bulkEdit = new JMenu("Database Edits");
	JMenu display = new JMenu("Display");
	JMenu space1 = new JMenu("    ");
	JMenu space2 = new JMenu("    ");
	JMenu space3 = new JMenu("    ");

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
	
	// Create the Display sub menu items
	JMenuItem pie = new JMenuItem("Pie Chart");
	JMenuItem chart = new JMenuItem("Histogram");
	JMenuItem clearDisplay = new JMenuItem("Clear");
	
	// Create the Edit sub menu items.
	JMenuItem clearAll = new JMenuItem("Clear All");
	JMenuItem delete = new JMenuItem("Delete");
	JMenuItem editEntry = new JMenuItem("Edit");
	
	// Create the Bulk Edit sub menu items.
	JMenuItem addEditEpisode = new JMenuItem("Add/Edit Episode");
	JMenuItem addEditSeries = new JMenuItem("Add/Edit Series");
	JMenuItem addEditMovie = new JMenuItem("Add/Edit Movie");
	JMenuItem addEditMaker = new JMenuItem("Add/Edit Maker");
	JMenuItem addCredits = new JMenuItem("Add Credits to Maker");
	
	public MenuView() {	
		//JMenu space1 = new JMenu("    "); // set selectable or visible to false
		space1.setEnabled(false);
		//JMenu space2 = new JMenu("    ");
		space2.setEnabled(false);
		//JMenu space3 = new JMenu("    ");
		space3.setEnabled(false);

		// Add the menu categories to the menu bar.
		selectionMenuBar.add(file);
		selectionMenuBar.add(space1);
		selectionMenuBar.add(selectionEdit);
		selectionMenuBar.add(space2);
		selectionMenuBar.add(bulkEdit);
		selectionMenuBar.add(space3);
		selectionMenuBar.add(display);

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

		// Add to the Display submenu.
		display.add(pie);
		display.add(chart);
		display.add(clearDisplay);

		// Add the Selection Edit sub menu items.
		selectionEdit.add(clearAll);
		selectionEdit.add(delete);
		selectionEdit.add(editEntry);
		
		// Add the Bulk Edit sub menu items.
		bulkEdit.add(addEditEpisode);
		bulkEdit.add(addEditSeries);
		bulkEdit.add(addEditMovie);
		bulkEdit.add(addEditMaker);
		bulkEdit.add(addCredits);
	}

	// Methods to enable/disable menu buttons
	public void setSelectionEdits(boolean tf) {
		selectionEdit.setEnabled(tf);
	}

	public void setDatabaseEdits(boolean tf) {
		bulkEdit.setEnabled(tf);
	}
	
	public void setDisplay(boolean tf) {
		display.setEnabled(tf);
	}
	
	public void setSaveToFavorites(boolean tf) {
		saveFavorites.setEnabled(tf);
	}
	
	public void setExport(boolean tf) {
		upload.setEnabled(tf);
	}
	
	public void setDisplayClear(boolean tf) {
		clearDisplay.setEnabled(tf);
	}
	
	// ActionListeners
	public void addFileImportBinaryActionListener(ActionListener importBinaryListener) {
		binary.addActionListener(importBinaryListener);}
	public void addFileImportTextAllActionListener(ActionListener importTextAllListener) {
		textImportAll.addActionListener(importTextAllListener);}
	public void addFileImportTextMoviesActionListener(ActionListener importTextMoviesListener) {
		textImportMovie.addActionListener(importTextMoviesListener);}
	public void addFileImportTextSeriesActionListener(ActionListener importTextSeriesListener) {
		textImportSeries.addActionListener(importTextSeriesListener);}
	public void addFileImportTextActorsActionListener(ActionListener importTextActorsListener) {
		textImportActor.addActionListener(importTextActorsListener);}
	public void addFileImportTextDirectorsActionListener(ActionListener importTextDirectorsListener) {
		textImportDirector.addActionListener(importTextDirectorsListener);}
	public void addFileImportTextProducersActionListener(ActionListener importTextProducersListener) {
		textImportProducer.addActionListener(importTextProducersListener);}
	public void addFileExportBinaryActionListener(ActionListener exportBinaryListener) {
		binary1.addActionListener(exportBinaryListener);}
	public void addFileExportTextAllActionListener(ActionListener exportTextAllListener) {
		textExportAll.addActionListener(exportTextAllListener);}
	public void addFileExportTextMoviesActionListener(ActionListener exportTextMoviesListener) {
		textExportMovie.addActionListener(exportTextMoviesListener);}
	public void addFileExportTextSeriesActionListener(ActionListener exportTextSeriesListener) {
		textExportSeries.addActionListener(exportTextSeriesListener);}
	public void addFileExportTextActorsActionListener(ActionListener exportTextActorsListener) {
		textExportActor.addActionListener(exportTextActorsListener);}
	public void addFileExportTextDirectorsActionListener(ActionListener exportTextDirectorsListener) {
		textExportDirector.addActionListener(exportTextDirectorsListener);}
	public void addFileExportTextProducersActionListener(ActionListener exportTextProducersListener) {
		textExportProducer.addActionListener(exportTextProducersListener);}
	public void addFileLoadFavoritesActionListener(ActionListener loadFavListener) {
		loadFavorites.addActionListener(loadFavListener);}
	public void addFileSaveToFavoritesActionListener(ActionListener saveFavListener) {
		saveFavorites.addActionListener(saveFavListener);}
	public void addSelectionEditsClearAllActionListener(ActionListener editsClearAllListener) {
		clearAll.addActionListener(editsClearAllListener);}
	public void addSelectionEditsDeleteActionListener(ActionListener editsDeleteListener) {
		delete.addActionListener(editsDeleteListener);}
	public void addSelectionEditsEditActionListener(ActionListener editsEditListener) {
		editEntry.addActionListener(editsEditListener);}
	public void addDatabaseEditsAddEditEpisodeActionListener(ActionListener addEditEpisodeListener) {
		addEditEpisode.addActionListener(addEditEpisodeListener);}
	public void addDatabaseEditsAddEditSeriesActionListener(ActionListener addEditSeriesListener) {
		addEditSeries.addActionListener(addEditSeriesListener);}
	public void addDatabaseEditsAddEditMakerActionListener(ActionListener addEditMakerListener) {
		addEditMaker.addActionListener(addEditMakerListener);}
	public void addDatabaseEditsAddEditMovieActionListener(ActionListener addEditMovieListener) {
		addEditMovie.addActionListener(addEditMovieListener);}
	public void addDatabaseEditsAddCreditsToMakerActionListener(ActionListener addCreditsListener) {
		addCredits.addActionListener(addCreditsListener);}
	public void addDisplayPieChartActionListener(ActionListener displayPieChartListener) {
		pie.addActionListener(displayPieChartListener);}
	public void addDisplayHistogramActionListener(ActionListener displayHistogramListener) {
		chart.addActionListener(displayHistogramListener);}
	public void addDisplayClearDisplayActionListener(ActionListener displayClearListener) {
		clearDisplay.addActionListener(displayClearListener);}
}