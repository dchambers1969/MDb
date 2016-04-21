package MediaDatabase;

public class MediaDatabaseDriver {
	
	// Controller
	static MediaDatabaseController mediaDatabaseController;
		
	// Models
	static MDbModel media;
	static MakerCreditsModel makers;
	
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
		mediaDatabaseController.setMediaModel(media);
		mediaDatabaseController.setMakerCreditsModel(makers);

		// Linking views to controller		
		mediaDatabaseController.setMenuView(menuView);
		mediaDatabaseController.setSelectionView(selectionView);
		mediaDatabaseController.setMasterListViews(makerList, mediaList);
	}
}