package MediaDatabase;

public class MediaDatabaseDriver {
	
	// Controller
	static MediaDatabaseController mediaDatabaseController;
		
	// Models
	static MDbModel movie;
	static MDbModel series;
	static MakerCreditsModel actors;
	static MakerCreditsModel directors;
	static MakerCreditsModel producers;

	// Views
	static SelectionView selectionView = new SelectionView();
	static MenuView menuView = new MenuView();
	static MasterMakerListView makerList = new MasterMakerListView();
	static MasterMediaListView mediaList = new MasterMediaListView();
		
	public MediaDatabaseDriver() {
		// Purposefully left empty
		// Possibly not needed
		mediaDatabaseController = new MediaDatabaseController();
	}

	public static void main(String[] args) {
		// TODO Check for accuracy/functionality

		new MediaDatabaseDriver();
		
		// Linking models to controller
		mediaDatabaseController.setMediaModel(movie, series);
		mediaDatabaseController.setMakerCreditsModel(actors, directors, producers);

		// Linking views to controller		
		mediaDatabaseController.setMenuView(menuView);
		mediaDatabaseController.setSelectionView(selectionView);
		mediaDatabaseController.setMasterListViews(makerList, mediaList);
	}
}