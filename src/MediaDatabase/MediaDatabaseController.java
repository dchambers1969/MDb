// Selection and other views need addListener methods













package MediaDatabase;

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

public class MediaDatabaseController {

	// TODO Maybe other five models instead of the current two?
	// TODO Will need new setters
	// private static MDbModel movies;
	// private static MDbModel series;
	// private static MakerCreditsModel actors;
	// private static MakerCreditsModel directors;
	// private static MakerCreditsModel producers;

	// References to Media and Maker models
	private MDbModel mediaModel;
	private MakerCreditsModel makerModel;

	// References to various views
	private SelectionView selectionView;

	private EditMediaView editMediaView;
	private AddEditMakerView addMakerView;
	private AddMediaView addMediaView;
	private AddCastToMediaView addCastToMediaView;

	private MakerActionListView makerActionListView;
	private MediaActionListView mediaActionListView;

	public MediaDatabaseController(){
		// Purposefully left empty
		// Not needed, using setters instead
	}


	/**
	 * @return the mdbModel
	 */
	public MDbModel getMediaModel() {
		return mediaModel;
	}

	/**
	 * @param mdbModel the mdbModel to set
	 */
	public void setMediaModel(MDbModel mediaModel) {
		this.mediaModel = mediaModel;
	}

	/**
	 * @return the makerCreditsModel
	 */
	public MakerCreditsModel getMakerCreditsModel() {
		return makerModel;
	}

	/**
	 * @param makerCreditsModel the makerCreditsModel to set
	 */
	public void setMakerCreditsModel(MakerCreditsModel makerCreditsModel) {
		this.makerModel = makerCreditsModel;
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
		// TODO 
		// add ActionListeners
	}

	/**
	 * @return the makerActionListView
	 */
	public MakerActionListView getMakerActionListView() {
		return makerActionListView;
	}

	/**
	 * @param makerActionListView the makerActionListView to set
	 */
	public void setMakerActionListView(MakerActionListView makerActionListView) {
		this.makerActionListView = makerActionListView;
		// TODO
		// add ActionListeners
	}

	/**
	 * @return the mediaActionListView
	 */
	public MediaActionListView getMediaActionListView() {
		return mediaActionListView;
	}

	/**
	 * @param mediaActionListView the mediaActionListView to set
	 */
	public void setMediaActionListView(MediaActionListView mediaActionListView) {
		this.mediaActionListView = mediaActionListView;
		// TODO
		// add ActionListeners
	}

	/**
	 * @return the editMediaView
	 */
	public EditMediaView getEditMediaView() {
		return editMediaView;
	}

	/**
	 * @param editMediaView the editMediaView to set
	 */
	public void setEditMediaView(EditMediaView editMediaView) {
		this.editMediaView = editMediaView;
		// TODO
		// add ActionListeners
	}

	/**
	 * @return the addMakerView
	 */
	public AddEditMakerView getAddMakerView() {
		return addMakerView;
	}

	/**
	 * @param addMakerView the addMakerView to set
	 */
	public void setAddMakerView(AddEditMakerView addMakerView) {
		this.addMakerView = addMakerView;
		// TODO
		// add ActionListeners
	}

	/**
	 * @return the addMediaView
	 */
	public AddMediaView getAddMediaView() {
		return addMediaView;
	}

	/**
	 * @param addMediaView the addMediaView to set
	 */
	public void setAddMediaView(AddMediaView addMediaView) {
		this.addMediaView = addMediaView;
		// TODO
		// add ActionListeners
	}

	/**
	 * @return the addCastToMediaView
	 */
	public AddCastToMediaView getAddCastToMediaView() {
		return addCastToMediaView;
	}

	/**
	 * @param addCastToMediaView the addCastToMediaView to set
	 */
	public void setAddCastToMediaView(AddCastToMediaView addCastToMediaView) {
		this.addCastToMediaView = addCastToMediaView;
		// TODO
		// add ActionListeners
	}
}
