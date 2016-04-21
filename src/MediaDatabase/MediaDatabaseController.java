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
import java.io.FileNotFoundException;
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

public class MediaDatabaseController extends JFrame implements Serializable {

	private static final long serialVersionUID = 4254743509402377695L;

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
		mainFrame.setTitle("MDb");
		mainFrame.pack();
		mainFrame.setVisible(true);
		//mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * @param mdbModel the mdbModel to set
	 */
	public void setMediaModel(MDbModel movies, MDbModel series) {
		this.movies = movies;
		this.series = series;
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
			JFileChooser jfile = new JFileChooser();
			jfile.setDialogTitle("Choose Binary File");
			int returnVal = jfile.showOpenDialog(mainFrame);
			File file = jfile.getSelectedFile();
			ArrayList<Object> databasesTemp = new ArrayList<Object>();

			try {
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream dbIn = new ObjectInputStream(fileIn);
				databasesTemp = (ArrayList<Object>) dbIn.readObject();
				dbIn.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			movies = (MDbModel) databasesTemp.get(0);
			series = (MDbModel) databasesTemp.get(1);
			actors = (MakerCreditsModel) databasesTemp.get(2);
			directors = (MakerCreditsModel) databasesTemp.get(3);
			producers = (MakerCreditsModel) databasesTemp.get(4);

			// TODO Change view 

		}
	}
	class ImportTextAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfile = new JFileChooser();
			jfile.setDialogType(JFileChooser.OPEN_DIALOG);
			JFileChooser jfile1 = new JFileChooser();
			jfile.setDialogType(JFileChooser.OPEN_DIALOG);
			JFileChooser jfile2 = new JFileChooser();
			jfile.setDialogType(JFileChooser.OPEN_DIALOG);
			JFileChooser jfile3 = new JFileChooser();
			jfile.setDialogType(JFileChooser.OPEN_DIALOG);
			JFileChooser jfile4 = new JFileChooser();
			jfile.setDialogType(JFileChooser.OPEN_DIALOG);

			jfile.setDialogTitle("Choose Movie File");
			int returnVal = jfile.showOpenDialog(mainFrame);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					movies.readMovieFileIn(jfile.getSelectedFile().getPath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			jfile1.setDialogTitle("Choose Series File");
			returnVal = jfile1.showOpenDialog(mainFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					series.readSeriesFileIn(jfile1.getSelectedFile().getPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			jfile2.setDialogTitle("Choose Actor File");
			returnVal = jfile2.showOpenDialog(mainFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					actors.readMakerFileIn('a', jfile2.getSelectedFile().getPath(), movies, series);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			jfile3.setDialogTitle("Choose Director File");
			returnVal = jfile3.showOpenDialog(mainFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					directors.readMakerFileIn('d', jfile3.getSelectedFile().getPath(), movies, series);
				} catch (IOException e1) {
					// TODO
					e1.printStackTrace();
				}
			}

			jfile4.setDialogTitle("Choose Producer File");
			returnVal = jfile4.showOpenDialog(mainFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					producers.readMakerFileIn('p', jfile4.getSelectedFile().getPath(), movies, series);
				} catch (IOException e1) {
					// TODO 
					e1.printStackTrace();
				}
			}

			//TODO
			//TODO
			//TODO
			//TODO
		}
	}
	class ImportTextMoviesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfile = new JFileChooser();
			jfile.setDialogTitle("Choose Movie File");
			int returnVal = jfile.showOpenDialog(mainFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {			
				File file = jfile.getSelectedFile();
				String fileName = file.getPath();
				try {
					movies.readMovieFileIn(fileName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			mediaList.setMasterMediaList(movies.getAllMedia(null, movies, series));
		}
	}
	class ImportTextSeriesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfile = new JFileChooser();
			jfile.setDialogTitle("Choose Series File");
			int returnVal = jfile.showOpenDialog(mainFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					series.readSeriesFileIn(jfile.getSelectedFile().getPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	class ImportTextActorsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfile = new JFileChooser();
			jfile.setDialogTitle("Choose Actor File");
			jfile.showOpenDialog(mainFrame);

			try {
				actors.readMakerFileIn('a', jfile.getSelectedFile().getPath(), movies, series);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class ImportTextDirectorsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfile = new JFileChooser();
			jfile.setDialogTitle("Choose Director File");
			jfile.showOpenDialog(mainFrame);

			try {
				directors.readMakerFileIn('d', jfile.getSelectedFile().getPath(), movies, series);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class ImportTextProducersListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfile = new JFileChooser();
			jfile.setDialogTitle("Choose Producer File");
			jfile.showOpenDialog(mainFrame);

			try {
				producers.readMakerFileIn('p', jfile.getSelectedFile().getPath(), movies, series);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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