package MediaDatabase;

import javax.swing.JFrame;

public class MediaDatabaseDriver {
	
	// Controller
	static MediaDatabaseController mediaDatabaseController;
		
	// Models
	static MDbModel mdbModel = new MDbModel();
	static MakerCreditsModel makerCreditsModel = new MakerCreditsModel();
	// TODO Other possible models for 
	// static MDbModel movies;
	// static MDbModel series;
	// static MakerCreditsModel actors;
	// static MakerCreditsModel directors;
	// static MakerCreditsModel producers;
	
	// Views
	static SelectionView selectionView;
	// static EditMediaView editMediaView;
	// static AddEditMakerView addMakerView;
	// static AddMediaView addMediaView;
	// static AddCastToMediaView addCastToMediaView;
	
	// ??? Maybe
	// static MakerActionListView makerActionListView;
	// static MediaActionListView mediaActionListView;
	
	public MediaDatabaseDriver() {
		// Purposefully left empty
		// Possibly not needed
		mediaDatabaseController = new MediaDatabaseController();
	}

	public static void main(String[] args) {
		// TODO Check for accuracy/functionality

		new MediaDatabaseDriver();
		
		// Linking models to controller
		mediaDatabaseController.setMediaModel(mdbModel);
		mediaDatabaseController.setMakerCreditsModel(makerCreditsModel);

		// Linking views to controller
		mediaDatabaseController.setSelectionView(selectionView);
		//mediaDatabaseController.setAddCastToMediaView(addCastToMediaView);
		//mediaDatabaseController.setAddMakerView(addMakerView);
		//mediaDatabaseController.setAddMediaView(addMediaView);
		//mediaDatabaseController.setEditMediaView(editMediaView);
		selectionView = new SelectionView();
	}

}
