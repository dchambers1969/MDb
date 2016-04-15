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
 * Project #3
 * CS 2334, Section 10
 * March 28, 2016
 * <P>
 * MediaDatabaseControler is the controller class for a media database MVC.
 * 
 * The class reads in movie, series, actor, producer, and 
 * director files into a media database or a binary file holding
 * a serialized database and a previous search.
 * 
 * The user is then able to search this database for movies, 
 * series, episodes, or media makers. These results will then be 
 * printed to standard output, with the additional option of 
 * pie chart and histogram graphical displays for media makers.
 * 
 * These results can then be optionally saved in a human readable
 * text file or in a binary file to be read by the program at a 
 * later time.
 * </P>
 * 
 * @version 1.0
 */

public class MediaDatabaseController implements Serializable {

	/** Generated UID for Serializable. */
	private static final long serialVersionUID = 314854348324198438L;
	private MDbModel mdbModel;
	private MakerCreditsModel makerCreditsModel;
	private SelectionView selectView;
	private MakerActionListView makerActionListView;
	private MediaActionListView mediaActionListView;
	private EditMediaView editMediaView;
	private AddEditMakerView addMakerView;
	private AddMediaView addMediaView;
	private AddCastToMediaView addCastToMediaView;
	
	public MediaDatabaseController(){
		
	}
	
	public void contollerBegin() throws IOException{
		/** Driver variables. */
		/** New Media databases. */
		MDb movies = new MDb();
		MDb series = new MDb();
		MakerCredits actors = new MakerCredits();
		MakerCredits producers = new MakerCredits();
		MakerCredits directors = new MakerCredits();
		/** New Array list to store Media IDs from search results. */
		ArrayList<Media> movieSearchResults = new ArrayList<Media>();
		ArrayList<Media> seriesSearchResults = new ArrayList<Media>();
		MakerCredits actorSearchResults = new MakerCredits();
		MakerCredits producerSearchResults = new MakerCredits();
		MakerCredits directorSearchResults = new MakerCredits();
		/** String to retain and output search parameters from user. */
		String searchOutline = "";
		/** Scanner input to allow for user dialog via keyboard and console. */
		InputStreamReader scanner = new InputStreamReader(System.in);
		BufferedReader scan = new BufferedReader(scanner);
		LinkedHashMap<String,Integer> pieCredits;
		LinkedHashMap<Integer,ArrayList<Integer>> histogramYearlyCredits;
		Set<Integer> setOfHistoYears;
		Integer[] countHisto;


		/** Flags for recording search options. */
		char mediaOrPeople = ' ';
		char moviesOrSeries = ' ';
		char titleOrYear = ' ';
		boolean exactResult = false;
		boolean includeEpisodeTitles = false;
		String searchYear = "";
		String searchTitle = "";
		String searchName = "";
		boolean sortByTitle = false;
		boolean singleMaker = false; // Number of makers found in search.

		/** Variables for temporarily holding user input. */
		String userTemp = ""; // String for UI that can be reused/doesn't need saving.
		char userChar = ' ';
		int enterTwice = 0; // Int to record if user hits enter twice.

		/** Initial branches in option tree, text or binary input. */
		while(!(userChar == 't' || userChar == 'b')) {
			System.out.println("Read (t)ext or (b)inary data?");
			userTemp = scan.readLine();
			if (!userTemp.isEmpty()) {
				userChar = userTemp.toLowerCase().charAt(0);
				enterTwice = 0;
			}
			else 
				++enterTwice;
			if (enterTwice == 2)
				System.exit(0);
		}


		/** Binary input file. */
		// Add functionality for displaying saved searches.
		if (userChar == 'b') {

			enterTwice = 0;
			ArrayList<Object> databases = new ArrayList<Object>();
			System.out.println("Enter the database file name: ");
			userTemp = scan.readLine();
			if (userTemp.isEmpty())
				++enterTwice;

			while (databases.size() == 0) {
				try {
					FileInputStream fileIn = new FileInputStream(userTemp);
					ObjectInputStream objectIn = new ObjectInputStream(fileIn);
					databases = (ArrayList<Object>) objectIn.readObject();
					objectIn.close();
				} catch (ClassNotFoundException i) {
					System.out.println("Please try another file: ");
					userTemp = scan.readLine();
					if (userTemp.isEmpty())
						++enterTwice;
					else 
						enterTwice = 0;
					if (enterTwice == 2 && userTemp.isEmpty())
						System.exit(0);
				} catch (IOException e) {
					System.out.println("Please try another file: ");
					userTemp = scan.readLine();
					if (userTemp.isEmpty())
						++enterTwice;
					else 
						enterTwice = 0;
					if (enterTwice == 2 && userTemp.isEmpty())
						System.exit(0);
				}
			}
			// Read in database objects from ArrayList.
			movies = (MDb) databases.get(0);
			series = (MDb) databases.get(1);
			actors = (MakerCredits) databases.get(2);
			producers = (MakerCredits) databases.get(3);
			directors = (MakerCredits) databases.get(4);
			movieSearchResults = (ArrayList<Media>) databases.get(5);
			seriesSearchResults = (ArrayList<Media>) databases.get(6);
			actorSearchResults = (MakerCredits) databases.get(7);
			producerSearchResults = (MakerCredits) databases.get(8);
			directorSearchResults = (MakerCredits) databases.get(9);
			searchOutline = (String) databases.get(10);
			singleMaker = (boolean) databases.get(11);
			// Display previous searches.
			if (!searchOutline.isEmpty()) {
				userChar = ' ';
				while (userChar != 'y' && userChar != 'n') {
					System.out.println("Display saved search? (y/n)");
					userTemp = scan.readLine();
					if (!userTemp.isEmpty())
						userChar = userTemp.toLowerCase().charAt(0);
				}
				if (userChar == 'y') {
					// Prints media
					if (searchOutline.endsWith("m")) {
						searchOutline = searchOutline.substring(0, searchOutline.length()-2);
						System.out.println(searchOutline);
					} else if (searchOutline.endsWith("p")) {
						// Prints people with option for graphs
						if(!singleMaker) {
							searchOutline = searchOutline.substring(0, searchOutline.length()-2);
							System.out.println("\n\n" + searchOutline);
							MakerCredits.printCredits(actorSearchResults, producerSearchResults, directorSearchResults, singleMaker);
						} else if ((actorSearchResults.size() + directorSearchResults.size() + producerSearchResults.size() != 0)) {
							// If only one maker saved
							userChar = ' ';
							while (userChar != 't' && userChar != 'g') {
								System.out.println("Display (t)ext or (g)raph?");
								userTemp = scan.readLine();
								if (!userTemp.isEmpty())
									userChar = userTemp.toLowerCase().charAt(0);
							}
							if (userChar == 't') {
								searchOutline = searchOutline.substring(0, searchOutline.length()-2);
								System.out.println("\n\n" + searchOutline);
								MakerCredits.printCredits(actorSearchResults, producerSearchResults, directorSearchResults, singleMaker);
							} else if (userChar == 'g') {
								int actorEpisode = 0;
								int actorMovie = 0;
								int producerEpisode = 0;
								int producerMovie = 0;
								int directorEpisode = 0;
								int directorMovie = 0;
								String makerName = "";

								setOfHistoYears = new HashSet<Integer>();

								pieCredits = new LinkedHashMap<String,Integer>();
								if(!actorSearchResults.isEmpty()){
									for (MediaMaker maker: actorSearchResults.getAll().keySet()) {
										makerName = maker.getMakerFirstName() + " " + maker.getMakerFamilyName();
										if(maker.getMakerSuffixInfo()!=null)
											makerName += " " + maker.getMakerSuffixInfo();
										for (Media media: actorSearchResults.getCreditsForMaker(maker)){
											if(media.getReleaseYear()!=null)
												setOfHistoYears.add(media.getReleaseYear());
											if (media.isEpisode())
												actorEpisode++;
											else if (media.isScreen()||media.isTVMovie()||media.isVideo())
												actorMovie++;
										}
									}
								}

								if(!producerSearchResults.isEmpty()){
									for (MediaMaker maker: producerSearchResults.getAll().keySet()) {
										makerName = maker.getMakerFirstName() + " " + maker.getMakerFamilyName();
										if(maker.getMakerSuffixInfo()!=null)
											makerName += " " + maker.getMakerSuffixInfo();
										for (Media media: producerSearchResults.getCreditsForMaker(maker)){
											if(media.getReleaseYear()!=null)
												setOfHistoYears.add(media.getReleaseYear());
											if (media.isEpisode())
												producerEpisode++;
											else if (media.isScreen()||media.isTVMovie()||media.isVideo())
												producerMovie++;		
										}
									}	
								}

								if(!directorSearchResults.isEmpty()){
									for (MediaMaker maker: directorSearchResults.getAll().keySet()) {
										makerName = maker.getMakerFirstName() + " " + maker.getMakerFamilyName();
										if(maker.getMakerSuffixInfo()!=null)
											makerName += " " + maker.getMakerSuffixInfo();
										for (Media media: directorSearchResults.getCreditsForMaker(maker)){
											if(media.getReleaseYear()!=null)
												setOfHistoYears.add(media.getReleaseYear());
											if (media.isEpisode())
												directorEpisode++;
											else if (media.isScreen()||media.isTVMovie()||media.isVideo())
												directorMovie++;	
										}
									}		
								}

								pieCredits.put("Actor Episode", actorEpisode);
								pieCredits.put("Actor Movie", actorMovie);
								pieCredits.put("Producer Episode", producerEpisode);
								pieCredits.put("Producer Movie", producerMovie);
								pieCredits.put("Director Episode", directorEpisode);
								pieCredits.put("Director Movie", directorMovie);

								histogramYearlyCredits = new LinkedHashMap<Integer,ArrayList<Integer>>();

								for(Integer year: setOfHistoYears){
									countHisto = new Integer[6];
									for(int index = 0; index<6; index++)
										countHisto[index]=0;
									if(!actorSearchResults.isEmpty()){
										for (MediaMaker maker: actorSearchResults.getAll().keySet()) {
											for (Media media: actorSearchResults.getCreditsForMaker(maker)){
												if(media.getReleaseYear()!=null && media.getReleaseYear().equals(year)){
													if (media.isEpisode())
														countHisto[1]++;
													else if (media.isScreen()||media.isTVMovie()||media.isVideo())
														countHisto[0]++;
												}
											}
										}
									}

									if(!producerSearchResults.isEmpty()){
										for (MediaMaker maker: producerSearchResults.getAll().keySet()) {
											for (Media media: producerSearchResults.getCreditsForMaker(maker)){
												if(media.getReleaseYear()!=null && media.getReleaseYear().equals(year)){
													if (media.isEpisode())
														countHisto[2]++;
													else if (media.isScreen()||media.isTVMovie()||media.isVideo())
														countHisto[3]++;		
												}
											}
										}	
									}

									if(!directorSearchResults.isEmpty()){
										for (MediaMaker maker: directorSearchResults.getAll().keySet()) {
											for (Media media: directorSearchResults.getCreditsForMaker(maker)){
												if(media.getReleaseYear()!=null && media.getReleaseYear().equals(year)){
													if (media.isEpisode())
														countHisto[5]++;
													else if (media.isScreen()||media.isTVMovie()||media.isVideo())
														countHisto[4]++;	
												}
											}
										}		
									}
									ArrayList<Integer> countHistoArrayList = new ArrayList<Integer>(Arrays.asList(countHisto));
									histogramYearlyCredits.put(year, countHistoArrayList);
								}
								// Test printout of histogram data.
								//for(Integer year: setOfHistoYears){
									//ArrayList<Integer> temp = histogramYearlyCredits.get(year);
									//System.out.println("Year: " + year + " " + temp.toString());
								//}

								// Histogram or pie chart.
								while (userChar != 'p' && userChar != 'h') {
									System.out.println("(P)ie chart or (h)istogram?");
									userTemp = scan.readLine();
									if (!userTemp.isEmpty())
										userChar = userTemp.toLowerCase().charAt(0);
								}

								if (userChar == 'p') {
									PieChartView makerCreditPie = new PieChartView(pieCredits, makerName);
									makerCreditPie.buildPieChart();
								} else {
									HistogramView yearlyInfo = new HistogramView(histogramYearlyCredits ,makerName);
									yearlyInfo.buildHistogram();
								}
							}
						} else {
							searchOutline = searchOutline.substring(0, searchOutline.length()-2);
							System.out.println("\n\n" + searchOutline);
							MakerCredits.printCredits(actorSearchResults, producerSearchResults, directorSearchResults, singleMaker);
						}
					} else {
						System.out.println("Sorry, something went wrong.");
					}
				}			
				// Clear searches and continue or exit
				userChar = ' ';
				while (userChar != 'c' && userChar != 'e') {
					System.out.println("(C)ontinue or (e)xit?");
					userTemp = scan.readLine();
					if (!userTemp.isEmpty())
						userChar = userTemp.toLowerCase().charAt(0);
				}
				if (userChar == 'e') {
					System.exit(0);
				} else {
					searchOutline = "";
					movieSearchResults = new ArrayList<Media>();
					seriesSearchResults = new ArrayList<Media>();
					actorSearchResults = new MakerCredits();
					producerSearchResults = new MakerCredits();
					directorSearchResults = new MakerCredits();
					singleMaker = false;
				}
			}
		}
		/** Text input files. */
		// If user hits enter instead of a filename twice, program exits.
		if (userChar == 't') {			
			// Read in movie file.
			System.out.println("Enter movie file name: ");
			userTemp = scan.readLine();
			movies.readMovieFileIn(userTemp);
			//movies.readMovieFileIn("movies.txt");

			// Read in series file.
			System.out.println("Enter series file name: ");
			userTemp = scan.readLine();
			series.readSeriesFileIn(userTemp);
			//series.readSeriesFileIn("series.txt");

			// Read in actor file.
			System.out.println("Enter actor file name: ");
			userTemp = scan.readLine();
			actors.readMakerFileIn('a', userTemp, movies, series);
			//actors.readMakerFileIn('a', "SomeActors.txt", movies, series);

			// Read in producer file.
			System.out.println("Enter producer file name: ");
			userTemp = scan.readLine();
			producers.readMakerFileIn('p', userTemp, movies, series);
			//producers.readMakerFileIn('p', "SomeProducers.txt", movies, series);

			// Read in director file.
			System.out.println("Enter director file name: ");
			userTemp = scan.readLine();
			directors.readMakerFileIn('d', userTemp, movies, series);
			//directors.readMakerFileIn('d', "SomeDirectors.txt", movies, series);

		}

		userChar = ' ';
		// While loop to loop entire program.
		while (userChar != 'n') {
			while (userChar != 'm' && userChar != 'p') {
				System.out.println("\nSearch (m)edia or (p)eople?");
				userTemp = scan.readLine();
				userTemp.toLowerCase();
				if (!userTemp.isEmpty())
					userChar = userTemp.charAt(0);
			}
			mediaOrPeople = userChar;

			/** Media (Same as project 2). */	
			if (mediaOrPeople == 'm') {
				// Reset all variables to looping doens't cause issues.
				userTemp = "";
				userChar = ' ';

				moviesOrSeries = ' ';
				titleOrYear = ' ';

				searchYear = "";
				searchTitle = "";

				includeEpisodeTitles = false;
				exactResult = false;
				sortByTitle = false;
				singleMaker = false;

				movieSearchResults = new ArrayList<Media>();
				seriesSearchResults = new ArrayList<Media>();
				actorSearchResults = new MakerCredits();
				producerSearchResults = new MakerCredits();
				directorSearchResults = new MakerCredits();
				searchOutline = "";

				// Loops until user picks movies, series, or both.
				while (moviesOrSeries != 'm' && moviesOrSeries != 's' && moviesOrSeries != 'b') {
					System.out.println("\nSearch (m)ovies, (s)eries, or (b)oth? ");
					userTemp = scan.readLine();
					if (!userTemp.isEmpty())
						moviesOrSeries = userTemp.toLowerCase().charAt(0);
				}

				// Loops until user picks to search by title, year, or both.
				while (titleOrYear != 't' && titleOrYear != 'y' && titleOrYear != 'b') {
					System.out.println("\nSearch (t)itle, (y)ear, or (b)oth? ");
					userTemp = scan.readLine();
					if (!userTemp.isEmpty())
						titleOrYear = userTemp.toLowerCase().charAt(0);
				}

				// If searching series, loops until user chooses to include episodes.
				if (moviesOrSeries == 's' || moviesOrSeries == 'b') {
					userChar = ' ';
					while (userChar != 'y' && userChar != 'n') {
						System.out.println("Include episode titles in search and output? (y/n)");
						userTemp = scan.readLine();
						if (!userTemp.isEmpty())
							userChar = userTemp.toLowerCase().charAt(0);
						if (userChar == 'y')
							includeEpisodeTitles = true;
						if (userChar == 'n')
							includeEpisodeTitles = false;
					}
				}

				// If searching by title (t || b), loops until user chooses exact or partial.
				if (titleOrYear == 't' || titleOrYear == 'b') {
					userChar = ' ';

					while (userChar != 'e' && userChar != 'p') {
						System.out.println("Search for (e)xact or (p)artial matches?");
						userTemp = scan.readLine();
						if (!userTemp.isEmpty())
							userChar = userTemp.toLowerCase().charAt(0);
						if(userChar == 'e')
							exactResult = true;
						if(userChar == 'p')
							exactResult = false;
					}


					// Search title.
					System.out.println("Title?");
					searchTitle = scan.readLine();
				} 

				// Year or years for search.
				if (titleOrYear == 'y'|| titleOrYear =='b'){
					System.out.println("Year(s)?");
					searchYear = scan.readLine();
				}

				// Loops until user decides to sort by title or year.
				while (userChar != 't' && userChar != 'y') {
					System.out.println("Sort by (t)itle or (y)ear?");
					userTemp = scan.readLine();
					if (!userTemp.isEmpty())
						userChar = userTemp.toLowerCase().charAt(0);
					if (userChar == 't')
						sortByTitle = true;
					if (userChar == 'y')
						sortByTitle = false;
				}
			}

			/** Searching people. */
			if (mediaOrPeople == 'p') {
				userChar = ' ';
				searchName = "";

				// Loops until user picks exact or partial. 
				while (userChar != 'e' && userChar != 'p') {
					System.out.println("Search for (e)xact or (p)artial matches?");
					userTemp = scan.readLine();
					if (!userTemp.isEmpty())
						userChar = userTemp.toLowerCase().charAt(0);
					if(userChar == 'e')
						exactResult = true;
					if(userChar == 'p')
						exactResult = false;
				}

				System.out.println("Name?");
				searchName = scan.readLine();
				searchName = searchName.toLowerCase();
			}

			/** Searching and retrieving results. */
			if (mediaOrPeople == 'm') {
				// Search media.
				if (moviesOrSeries == 'm' || moviesOrSeries == 'b') {
					// Searches movies.
					if (titleOrYear == 't') {
						movieSearchResults = movies.searchTextInMedia(searchTitle, includeEpisodeTitles, exactResult);
					} else if (titleOrYear == 'y') {
						movieSearchResults = movies.searchYearInMedia(searchYear, includeEpisodeTitles);
					} else {
						// Both.
						movieSearchResults = movies.searchTextInMedia(searchTitle, includeEpisodeTitles, exactResult);
						movieSearchResults = movies.searchYearInMedia(searchYear, includeEpisodeTitles);
					}
				} 
				if (moviesOrSeries == 's' || moviesOrSeries == 'b') {
					// Searches series.
					if (titleOrYear == 't') {
						seriesSearchResults = series.searchTextInMedia(searchTitle, includeEpisodeTitles, exactResult);
					} else if (titleOrYear == 'y') {
						seriesSearchResults = series.searchYearInMedia(searchYear, includeEpisodeTitles);
					} else {
						// Both.
						seriesSearchResults = series.searchTextInMedia(searchTitle, includeEpisodeTitles, exactResult);
						seriesSearchResults = series.searchYearInMedia(searchYear, includeEpisodeTitles);
					}
				}
			} else {
				// Search people.
				actorSearchResults = actors.searchMakerCredits(searchName, exactResult);
				producerSearchResults = producers.searchMakerCredits(searchName, exactResult);
				directorSearchResults = directors.searchMakerCredits(searchName, exactResult);

				// Determining if more than one maker found.
				// All contain 1 or 0 results.
				if (	(actorSearchResults.size() == 1 || actorSearchResults.size() == 0) &&
						(producerSearchResults.size() == 1 || producerSearchResults.size() == 0) &&
						(directorSearchResults.size() == 1 || directorSearchResults.size() == 0)) {
					// Actor contains 1 result
					String actorName = actorSearchResults.getName();
					String producerName = producerSearchResults.getName();
					String directorName = directorSearchResults.getName();

					if (actorSearchResults.size() == 1) {
						if (producerSearchResults.size() == 1) {
							if (directorSearchResults.size() == 1) {
								// If a/d/p all contain 1 result, they must be equal.
								if (actorName.equals(directorName) && actorName.equals(producerName)) {
									singleMaker = true;
								} else {
									singleMaker = false;
								}
							} else {
								// If a/p contain 1 result and d is 0, a/p must be equal.
								if (actorName.equals(producerName)) {
									singleMaker = true;
								} else {
									singleMaker = false;
								}
							}
						} else {
							// If p is empty, d must have 1 and be equal.
							if (directorSearchResults.size() == 1) {
								if (actorName.equals(directorName)) {
									singleMaker = true;
								} else {
									singleMaker = false;
								}
							} else {
								singleMaker = true;
							}
						} 
					} else {
						// Actor is empty, must test p/d.
						if (producerSearchResults.size() == 1){
							if (directorSearchResults.size() == 1) {
								// If d/p all contain 1 result, they must be equal.
								if (producerName.equals(directorName)) {
									singleMaker = true;
								} else {
									singleMaker = false;
								}
							} else {
								singleMaker = true;
							}
						} else {
							// If only director has one if is all are empty, true.
							singleMaker = true;
						}
					}
				}	
			}

			/** Printing out, displaying, and saving search results. */
			if (mediaOrPeople == 'm') {
				searchOutline = "SEARCHED ";
				switch(moviesOrSeries){
				case 'm': searchOutline += "MOVIES\n";
				break;
				case 's': searchOutline += (includeEpisodeTitles? "TV SERIES AND EPISODES\n" : "TV SERIES\n");
				break;
				case 'b': searchOutline += (includeEpisodeTitles? "MOVIES , TV SERIES, AND EPISODES\n" : "MOVIES AND TV SERIES\n");
				break;
				}

				searchOutline += (exactResult? "EXACT " : "PARTIAL ");
				searchOutline += "TITLE: " + (searchTitle.isEmpty()? "Any" : searchTitle) + "\n";
				searchOutline += "YEARS: " + (searchYear.isEmpty()? "Any" : searchYear) + "\n";
				searchOutline += "SORTED BY " + (sortByTitle? "TITLE\n" : "YEAR\n");
				searchOutline += "===============================================================================";

				// Print all media as outlined in project 2
				// Check comparisons for sorting
				System.out.println("\n\n\n" + searchOutline);

				if (sortByTitle) {
					movieSearchResults.sort(Media.TITLE_FIRST_COMPARATOR);
					seriesSearchResults.sort(Media.TITLE_FIRST_COMPARATOR);
				} else {
					movieSearchResults.sort(Media.YEAR_FIRST_COMPARATOR);
					seriesSearchResults.sort(Media.YEAR_FIRST_COMPARATOR);
				}

				for (Media media: movieSearchResults) {
					System.out.println("MOVIE: " + media.getTitle()); 
					searchOutline += ("\nMOVIE: " + media.getTitle());
				}
				for (Media media: seriesSearchResults) {
					if (media.isEpisode()) {
						System.out.println("EPISODE: " + media.toString());
						searchOutline += ("\nEPISODE: " + media.toString());
					} else { 
						System.out.println("SERIES: " + media.getTitle());					
						searchOutline += ("\nSERIES: " + media.getTitle());
					}
				}
			} else {
				// Covers case when only one media maker is found.

				// Tests to see if only one maker.
				if (singleMaker && (actorSearchResults.size() + directorSearchResults.size() + producerSearchResults.size() != 0)) {
					userChar = ' ';
					while (userChar != 't' && userChar != 'g') {
						System.out.println("Display (t)ext or (g)raph?");
						userTemp = scan.readLine();
						if (!userTemp.isEmpty())
							userChar = userTemp.toLowerCase().charAt(0);
					}
					// Setting searchOutline to store search info
					searchOutline = "SEARCHED PEOPLE\n";
					if (exactResult)
						searchOutline += "EXACT NAME: " + searchName + "\n";
					else 
						searchOutline += "PARTIAL NAME: " + searchName + "\n";
					searchOutline += "===============================================================================\n";

					// Printing maker.
					if (userChar == 't') {
						System.out.println("\n\n" + searchOutline);
						MakerCredits.printCredits(actorSearchResults, producerSearchResults, directorSearchResults, singleMaker);
					} 
					else {
						int actorEpisode = 0;
						int actorMovie = 0;
						int producerEpisode = 0;
						int producerMovie = 0;
						int directorEpisode = 0;
						int directorMovie = 0;
						String makerName = "";

						setOfHistoYears = new HashSet<Integer>();

						pieCredits = new LinkedHashMap<String,Integer>();
						if(!actorSearchResults.isEmpty()){
							for (MediaMaker maker: actorSearchResults.getAll().keySet()) {
								makerName = maker.getMakerFirstName() + " " + maker.getMakerFamilyName();
								if(maker.getMakerSuffixInfo()!=null)
									makerName += " " + maker.getMakerSuffixInfo();
								for (Media media: actorSearchResults.getCreditsForMaker(maker)){
									if(media.getReleaseYear()!=null)
										setOfHistoYears.add(media.getReleaseYear());
									if (media.isEpisode())
										actorEpisode++;
									else if (media.isScreen()||media.isTVMovie()||media.isVideo())
										actorMovie++;
								}
							}
						}

						if(!producerSearchResults.isEmpty()){
							for (MediaMaker maker: producerSearchResults.getAll().keySet()) {
								makerName = maker.getMakerFirstName() + " " + maker.getMakerFamilyName();
								if(maker.getMakerSuffixInfo()!=null)
									makerName += " " + maker.getMakerSuffixInfo();
								for (Media media: producerSearchResults.getCreditsForMaker(maker)){
									if(media.getReleaseYear()!=null)
										setOfHistoYears.add(media.getReleaseYear());
									if (media.isEpisode())
										producerEpisode++;
									else if (media.isScreen()||media.isTVMovie()||media.isVideo())
										producerMovie++;		
								}
							}	
						}

						if(!directorSearchResults.isEmpty()){
							for (MediaMaker maker: directorSearchResults.getAll().keySet()) {
								makerName = maker.getMakerFirstName() + " " + maker.getMakerFamilyName();
								if(maker.getMakerSuffixInfo()!=null)
									makerName += " " + maker.getMakerSuffixInfo();
								for (Media media: directorSearchResults.getCreditsForMaker(maker)){
									if(media.getReleaseYear()!=null)
										setOfHistoYears.add(media.getReleaseYear());
									if (media.isEpisode())
										directorEpisode++;
									else if (media.isScreen()||media.isTVMovie()||media.isVideo())
										directorMovie++;	
								}
							}		
						}

						pieCredits.put("Actor Episode", actorEpisode);
						pieCredits.put("Actor Movie", actorMovie);
						pieCredits.put("Producer Episode", producerEpisode);
						pieCredits.put("Producer Movie", producerMovie);
						pieCredits.put("Director Episode", directorEpisode);
						pieCredits.put("Director Movie", directorMovie);

						histogramYearlyCredits = new LinkedHashMap<Integer,ArrayList<Integer>>();

						for(Integer year: setOfHistoYears){
							countHisto = new Integer[6];
							for(int index = 0; index<6; index++)
								countHisto[index]=0;
							if(!actorSearchResults.isEmpty()){
								for (MediaMaker maker: actorSearchResults.getAll().keySet()) {
									for (Media media: actorSearchResults.getCreditsForMaker(maker)){
										if(media.getReleaseYear()!=null && media.getReleaseYear().equals(year)){
											if (media.isEpisode())
												countHisto[1]++;
											else if (media.isScreen()||media.isTVMovie()||media.isVideo())
												countHisto[0]++;
										}
									}
								}
							}

							if(!producerSearchResults.isEmpty()){
								for (MediaMaker maker: producerSearchResults.getAll().keySet()) {
									for (Media media: producerSearchResults.getCreditsForMaker(maker)){
										if(media.getReleaseYear()!=null && media.getReleaseYear().equals(year)){
											if (media.isEpisode())
												countHisto[2]++;
											else if (media.isScreen()||media.isTVMovie()||media.isVideo())
												countHisto[3]++;		
										}
									}
								}	
							}

							if(!directorSearchResults.isEmpty()){
								for (MediaMaker maker: directorSearchResults.getAll().keySet()) {
									for (Media media: directorSearchResults.getCreditsForMaker(maker)){
										if(media.getReleaseYear()!=null && media.getReleaseYear().equals(year)){
											if (media.isEpisode())
												countHisto[5]++;
											else if (media.isScreen()||media.isTVMovie()||media.isVideo())
												countHisto[4]++;	
										}
									}
								}		
							}
							ArrayList<Integer> countHistoArrayList = new ArrayList<Integer>(Arrays.asList(countHisto));
							histogramYearlyCredits.put(year, countHistoArrayList);
						}
						// Test printout of histogram data.
						//for(Integer year: setOfHistoYears){
							//ArrayList<Integer> temp = histogramYearlyCredits.get(year);
							//System.out.println("Year: " + year + " " + temp.toString());
						//}

						// Histogram or pie chart.
						while (userChar != 'p' && userChar != 'h') {
							System.out.println("(P)ie chart or (h)istogram?");
							userTemp = scan.readLine();
							if (!userTemp.isEmpty())
								userChar = userTemp.toLowerCase().charAt(0);
						}

						if (userChar == 'p') {
							PieChartView makerCreditPie = new PieChartView(pieCredits, makerName);
							makerCreditPie.buildPieChart();
						} else {
							HistogramView yearlyInfo = new HistogramView(histogramYearlyCredits ,makerName);
							yearlyInfo.buildHistogram();
						}
					}

				} else {
					// Covers all other cases.						
					searchOutline = "SEARCHED PEOPLE\n";
					if (exactResult)
						searchOutline += "EXACT NAME: " + searchName + "\n";
					else 
						searchOutline += "PARTIAL NAME: " + searchName + "\n";
					searchOutline += "===============================================================================\n";
					System.out.println("\n\n" + searchOutline);
					MakerCredits.printCredits(actorSearchResults, producerSearchResults, directorSearchResults, singleMaker);
				}
			}

			userChar = ' ';
			while (userChar != 'y' && userChar != 'n') {
				System.out.println("Would you like to save? (y/n)");
				userTemp = scan.readLine();
				if (!userTemp.isEmpty())
					userChar = userTemp.toLowerCase().charAt(0);
			}
			if (userChar == 'y') {
				while (userChar != 't' && userChar != 'b') {
					System.out.println("Write (t)ext or (b)inary data?");
					userTemp = scan.readLine();
					if(!userTemp.isEmpty())
						userChar = userTemp.toLowerCase().charAt(0);
				}
			}
			if (userChar == 'b') {
				ArrayList<Object> toBeSaved = new ArrayList<Object>();
				toBeSaved.add(movies);
				toBeSaved.add(series);
				toBeSaved.add(actors);
				toBeSaved.add(producers);
				toBeSaved.add(directors);
				toBeSaved.add(movieSearchResults);
				toBeSaved.add(seriesSearchResults);
				toBeSaved.add(actorSearchResults);
				toBeSaved.add(producerSearchResults);
				toBeSaved.add(directorSearchResults);
				searchOutline += "\n" + mediaOrPeople;
				toBeSaved.add(searchOutline);
				toBeSaved.add(singleMaker);

				System.out.println("Filename?");
				userTemp = scan.readLine();
				FileOutputStream fileOut = new FileOutputStream(userTemp);
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

				objectOut.writeObject(toBeSaved);
				objectOut.close();
				fileOut.close();
			} else if (userChar == 't') {
				System.out.println("Filename?");
				FileWriter fw = new FileWriter(scan.readLine());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(searchOutline);					
				if (mediaOrPeople == 'p')
					bw.write(MakerCredits.saveCreditsToString(actorSearchResults, producerSearchResults, directorSearchResults, singleMaker));
				bw.close();
			}

			userChar = ' ';
			while(userChar != 'y' && userChar != 'n') {
				System.out.println("Continue? (y/n)");
				userTemp = scan.readLine();
				if (!userTemp.isEmpty())
					userChar = userTemp.toLowerCase().charAt(0);
			}
		}
		System.exit(0);
	}

	/**
	 * @return the mdbModel
	 */
	public MDbModel getMdbModel() {
		return mdbModel;
	}

	/**
	 * @param mdbModel the mdbModel to set
	 */
	public void setMdbModel(MDbModel mdbModel) {
		this.mdbModel = mdbModel;
	}

	/**
	 * @return the makerCreditsModel
	 */
	public MakerCreditsModel getMakerCreditsModel() {
		return makerCreditsModel;
	}

	/**
	 * @param makerCreditsModel the makerCreditsModel to set
	 */
	public void setMakerCreditsModel(MakerCreditsModel makerCreditsModel) {
		this.makerCreditsModel = makerCreditsModel;
	}

	/**
	 * @return the selectView
	 */
	public SelectionView getSelectView() {
		return selectView;
	}

	/**
	 * @param selectView the selectView to set
	 */
	public void setSelectView(SelectionView selectView) {
		this.selectView = selectView;
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
	}
}
