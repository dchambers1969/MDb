/**
	Modal/temp views to be called in methods

	static EditMediaView editMediaView;
	static AddEditMakerView addMakerView;
	static AddMediaView addMediaView;
	static AddCastToMediaView addCastToMediaView;
	static AddEditEpisodeView;
	static AddEditSeriesView;
	static MakerTypeView;
	static NewOrExistingSeriesView;
	static SelectSeriesView;
	static SelectMakerView;// Selection and other views need addListener methods

 */


package MediaDatabase;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * Project #4
 * CS 2334, Section 12
 * April 21, 2016
 * <P>
 * MediaDatabaseControler is the controller class for a media database MVC.
 * 
 * It controls the logic of the program and dictates how the model and the 
 * view interact with each other. It may be important to note that this
 * implementation of MVC does not follow the form introduced in class,
 * instead opting to have the controller manage all the interaction between
 * the model and the view.
 * </P>
 * 
 * @version 1.0
 */

public class MediaDatabaseController extends JFrame {

	// References to models	
	private MDbModel movies;
	private MDbModel series;
	private MakerCreditsModel actors;
	private MakerCreditsModel directors;
	private MakerCreditsModel producers;

	// References to various views
	private SelectionView selectionView;
	private MenuView menuView = new MenuView();
	private MasterMakerListView makerList = new MasterMakerListView();
	private MasterMediaListView mediaList = new MasterMediaListView();


	// Main frame and panel to aggregate various components
	private static JFrame mainFrame = new JFrame();
	private static JPanel mainPanel = new JPanel(new BorderLayout());





	public MediaDatabaseController(){
		// Purposefully left empty
		// Not needed, using setters insteadmainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
		//mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	/**
	 * @return the mdbModel
	 */
	public MDbModel getMediaModel() {
		// TODO
		return movies;
	}

	/**
	 * @param mdbModel the mdbModel to set
	 */
	public void setMediaModel(MDbModel movies, MDbModel series) {
		this.movies = movies;
		this.series = series;
	}

	/**
	 * @return the makerCreditsModel
	 */
	public MakerCreditsModel getMakerCreditsModel() {
		// TODO
		return actors;
	}

	/**
	 * @param makerCreditsModel the makerCreditsModel to set
	 */
	public void setMakerCreditsModel(MakerCreditsModel actors, MakerCreditsModel directors, MakerCreditsModel producers) {
		this.actors = actors;
		this.directors = directors;
		this.producers = producers;
	}

	/**
	 * @return the selectView
	 */
	public SelectionView getSelectionView() {
		return selectionView;
	}

	/**
	 * @param selectView the selectView to set
	 */
	public void setSelectionView(SelectionView selectionView) {
		this.selectionView = selectionView;

		mainPanel.add(this.selectionView,BorderLayout.WEST);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// TODO 
		// add ActionListeners
	}

	public void setMenuView(MenuView menuView) {
		// TODO Auto-generated method stub
		this.menuView = menuView;

		this.menuView.addFileImportBinaryActionListener(new ImportBinaryListener());
		this.menuView.addFileImportTextAllActionListener(new ImportTextAllListener());
		this.menuView.addFileImportTextMoviesActionListener(new ImportTextMoviesListener());
		this.menuView.addFileImportTextSeriesActionListener(new ImportTextSeriesListener());
		this.menuView.addFileImportTextActorsActionListener(new ImportTextActorsListener());
		this.menuView.addFileImportTextDirectorsActionListener(new ImportTextDirectorsListener());
		this.menuView.addFileImportTextProducersActionListener(new ImportTextProducersListener());

		this.menuView.addFileExportBinaryActionListener(new ExportBinaryListener());
		this.menuView.addFileExportTextAllActionListener(new ExportTextAllListener());
		this.menuView.addFileExportTextMoviesActionListener(new ExportTextMoviesListener());
		this.menuView.addFileExportTextSeriesActionListener(new ExportTextSeriesListener());
		this.menuView.addFileExportTextActorsActionListener(new ExportTextActorsListener());
		this.menuView.addFileExportTextDirectorsActionListener(new ExportTextDirectorsListener());
		this.menuView.addFileExportTextProducersActionListener(new ExportTextProducersListener());

		this.menuView.addFileLoadFavoritesActionListener(new LoadFavListener());
		this.menuView.addFileSaveToFavoritesActionListener(new SaveFavListener());

		this.menuView.addSelectionEditsClearAllActionListener(new EditsClearAllListener());
		this.menuView.addSelectionEditsDeleteActionListener(new EditsDeleteListener());
		this.menuView.addSelectionEditsEditActionListener(new EditsEditListener());

		this.menuView.addDatabaseEditsAddEditEpisodeActionListener(new AddEditEpisodeListener());
		this.menuView.addDatabaseEditsAddEditSeriesActionListener(new AddEditSeriesListener());
		this.menuView.addDatabaseEditsAddEditMovieActionListener(new AddEditMovieListener());
		this.menuView.addDatabaseEditsAddEditMakerActionListener(new AddEditMakerListener());
		this.menuView.addDatabaseEditsAddCreditsToMakerActionListener(new AddCreditsListener());

		this.menuView.addDisplayPieChartActionListener(new DisplayPieChartListener());
		this.menuView.addDisplayHistogramActionListener(new DisplayHistogramListener());
		this.menuView.addDisplayClearDisplayActionListener(new DisplayClearListener());

		this.menuView.setSaveToFavorites(false);
		this.menuView.setExport(false);
		this.menuView.setSelectionEdits(false);
		this.menuView.setDatabaseEdits(false);
		this.menuView.setDisplayClear(false);
		this.menuView.setDisplay(false);
		
		mainPanel.add(this.menuView,BorderLayout.NORTH);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class ImportBinaryListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Import Binary");
			JFileChooser jfile = new JFileChooser();
			jfile.showOpenDialog(mainFrame);
			File file = jfile.getSelectedFile();
		}
	}
	class ImportTextAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ImportTextMoviesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ImportTextSeriesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ImportTextActorsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ImportTextDirectorsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ImportTextProducersListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ExportBinaryListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ExportTextAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ExportTextMoviesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ExportTextSeriesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ExportTextActorsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ExportTextDirectorsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class ExportTextProducersListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class LoadFavListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class SaveFavListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class EditsClearAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class EditsDeleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class EditsEditListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class AddEditEpisodeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class AddEditSeriesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class AddEditMovieListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class AddEditMakerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class AddCreditsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class DisplayPieChartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class DisplayHistogramListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class DisplayClearListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}

	public void setMasterListViews(MasterMakerListView makerList, MasterMediaListView mediaList) {
		// TODO Auto-generated method stub
		this.makerList = makerList;
		this.mediaList = mediaList;

		JSplitPane splitMediaPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitMediaPane.setRightComponent(this.makerList);
		splitMediaPane.setLeftComponent(this.mediaList);
		splitMediaPane.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/3, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		splitMediaPane.setVisible(true);

		mainPanel.add(splitMediaPane,BorderLayout.CENTER);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}