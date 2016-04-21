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
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	private MDbModel media;
	private MakerCreditsModel makers;

	// References to various views
	private SelectionView selectionView;
	private MenuView menuView = new MenuView();
	private MasterMakerListView makerList = new MasterMakerListView();
	private MasterMediaListView mediaList = new MasterMediaListView();
	
	
	// Main frame and panel to aggregate various components
	private static JFrame mainFrame = new JFrame();
	private static JPanel mainPanel = new JPanel();

	
	
	
	
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
		return media;
	}

	/**
	 * @param mdbModel the mdbModel to set
	 */
	public void setMediaModel(MDbModel mediaModel) {
		this.media = mediaModel;
	}

	/**
	 * @return the makerCreditsModel
	 */
	public MakerCreditsModel getMakerCreditsModel() {
		return makers;
	}

	/**
	 * @param makerCreditsModel the makerCreditsModel to set
	 */
	public void setMakerCreditsModel(MakerCreditsModel makerCreditsModel) {
		this.makers = makerCreditsModel;
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

		mainPanel.add(this.selectionView);
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
		mainPanel.add(this.menuView);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setMasterListViews(MasterMakerListView makerList, MasterMediaListView mediaList) {
		// TODO Auto-generated method stub
		this.makerList = makerList;
		this.mediaList = mediaList;
		
		JSplitPane splitMediaPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.makerList, this.mediaList);

		mainPanel.add(splitMediaPane);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}