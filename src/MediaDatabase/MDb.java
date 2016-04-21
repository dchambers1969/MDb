package MediaDatabase;

import java.io.FileReader; //import to read in file
import java.io.BufferedReader; //import Buffered Reader to read in file
import java.io.FileOutputStream; //import to write out search results
import java.io.ObjectOutputStream; //import to write out objects from search results
import java.io.Serializable; //import to be able to serialize objects to write
import java.io.IOException; //import to handle input output exceptions
import java.io.InputStreamReader;
import java.util.ArrayList; //import ArrayList to use as MDb private variable
import java.util.Collections; //import Collections to Sort ArrayList of media
import java.util.Comparator;// import Comparator to build comparator for Media
import java.util.HashSet;
//import java.util.Scanner; //import to allow user input through console


/**
 * Project #3
 * CS 2334, Section 10
 * Mar 28, 2016
 * <P>
 * This MDB class maintains an ArrayList of media objects specified by content
 * This MDB class also performs all the database maintenance functions (read in, parse, add to, search)
 * </P>
 * 
 * @version 1.0 
 */

public class MDb implements Serializable{

	/**
	 * Generated serialize id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * MDb ArrayList of Media
	 */
	private ArrayList<Media> media;
	private ArrayList<Media> favorites;

	/**
	 * Empty Constructor for MDb
	 */	
	public MDb(){

		media = new ArrayList<Media>();
	}

	/**
	 * Getter function for MDb on media array
	 * 
	 * @return ArrayList<Media>	MDb to return
	 */
	public ArrayList<Media> getMedia(){
		return this.media;
	}

	/**
	 * ArrayList add function for MDb 
	 * @param m	Media to add
	 */
	public void add(Media m){
		media.add(m);
	}

	/**
	 * ArrayList add function for MDb
	 * 
	 * @param arr  ArrayList of media to add
	 */
	public void add(ArrayList<Media> arr){
		media.addAll(arr);
	}

	/**
	 * Getter for favorites.
	 * @return the favorites
	 */
	public ArrayList<Media> getFavorites() {
		return favorites;
	}

	/**
	 * Setter for favorites.
	 * @param favorites the favorites to set
	 */
	public void setFavorites(ArrayList<Media> favorites) {
		this.favorites = favorites;
	}

	/**
	 * This method will add media to favorites.
	 * @param mediaItem
	 */
	public void addMedia2Favorites(Media m){
		this.favorites.add(m);
	}
	
	/**
	 * ArrayList sort function to use Collections sort for MDb based on media Comparator sent
	 * 
	 * @param c	comparator to send to collection sort
	 */
	public void sort(Comparator<Media> c){
		Collections.sort(this.media,c);
	}

	/**
	 * ArrayList size function for MDb Class
	 * 
	 * @return int	return size of media array list
	 */
	public int size(){
		return this.media.size();
	}

	/**
	 * ArrayList isEmpty function for MDb 
	 * 
	 * @return boolean  returns true if database empty
	 */
	public boolean isEmpty() {
		return this.media.isEmpty();
	}

	/**
	 * Method to read Movie fileName into Movie then add to Media array
	 * 
	 * @param fileName  String for file name of movie file
	 * @throws IOException 
	 */
	public void readMovieFileIn(String fileName) throws IOException{
		
		boolean fileNotFound = true;
		InputStreamReader scanIn = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(scanIn);
		int enterTwice = 0;
		if (fileName.isEmpty())
			++enterTwice;

		while(fileNotFound){
			try{
				FileReader fr = new FileReader(fileName); // Create FileReader variable on new fileName.
				BufferedReader br = new BufferedReader(fr); // Create BufferedReader on FileReader variable.
				String nextLine = ""; // String to put read line into for passing to parsing method.
				Movie newMovie = null; // Create Movie variable to load parsed line into.

				while(nextLine!=null){ // Buffered reader loads in null if no new lines - continue until null found.
					nextLine = (br.readLine());// Read next line used buffered reader.
					if(nextLine!=null){ // As long as last line read is not null. 
						newMovie = parseMovieLine(nextLine); // Parsed read line into newMovie.
						this.media.add(newMovie); // Add newMovie into MDb movies.
						//System.out.println(newMovie.getSearchResultOutput()); // Print out each movie to show user in console.
					}
				}
				fr.close(); // Close file reader.
				br.close(); // Close buffered reader.
				fileNotFound = false;
			}

			catch(IOException e){
				System.out.println("Failed to read file.");
				fileNotFound = true;
				System.out.println("Please enter valid Movie file name:");
				fileName = input.readLine();
				if (fileName.isEmpty())
					++enterTwice;
				else
					enterTwice = 0;
				if (enterTwice == 2)
					System.exit(0);
			}
		}
	}

	/**
	 * Method to read Series fileName into Series/Episode than add to Media Array
	 *
	 * @param fileName  string containing file name of series file
	 * @throws IOException 
	 */
	public void readSeriesFileIn(String fileName) throws IOException{

		boolean fileNotFound = true;
		InputStreamReader scanIn = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(scanIn);
		int enterTwice = 0;
		if (fileName.isEmpty())
			++enterTwice;
		
		while(fileNotFound){
			try{ // Try read in and catch IOException.
				FileReader fr = new FileReader(fileName); // Create FileReader variable on new fileName.
				BufferedReader br = new BufferedReader(fr); // Create BufferedReader on FileReader variable.
				String nextLine = ""; // String to put read line into for passing to parsing method.
				Episode newEpisode; // Create new episode to parse Episode line into.
				TVSeries newTVSeries; // Create new TV Series to parse TV Series line into.

				while(nextLine!=null){ // Buffered reader loads in null if no new lines - continue until null found.
					nextLine = (br.readLine()); // Read next line used buffered reader.
					//System.out.println(nextLine);
					if(nextLine!=null && nextLine.contains("{")){
						newEpisode = parseEpisodeLine(nextLine); // Parsed read line into episode.
						// If line is episode find its matching TVSeries by going through media.
						// Once found add the newEpisode to TV series episode array list.
						for(Media m: this.media){
							if(m.getReleaseType().equals(ReleaseType.TV)){ // Only check media that are TVSeries.
								// Match unique titles of TVSeries and Episode (Series in quotes plus year in () ).
								if(newEpisode.getUniqueSeriesTitle().equalsIgnoreCase(((TVSeries) m).getUniqueTitle())){
									((TVSeries)m).add(newEpisode); // Add newEpisode into correct TVSeries arrayList of episodes.
									continue; // End for loop once correctly added.
								}
							}
						}
					}
					
					// Parse TVSeries if line not Episode and lines still available to read.
					else if(nextLine!=null && !nextLine.contains("{")){ // As long as last line read is not null. 
						newTVSeries = parseSeriesLine(nextLine); // Parsed read line into newSeries.
						this.media.add(newTVSeries); // Add newMovie into MDb movies.
					}
				}

				// Print out TVSeries information. 
				for(Media tv: media){
					if(tv.getReleaseType().equals(ReleaseType.TV)){
						//System.out.println(tv.getSearchResultOutput());
						for(Episode e: ((TVSeries)tv).getEpisodes()){
							//System.out.println(e.getSearchResultOutput());
						}
					}
				}
				fr.close(); // Close file reader.
				br.close(); // Close buffered reader.	
				fileNotFound = false;
			}
			catch(IOException e){
				System.out.println("Failed to read file.");
				fileNotFound = true;
				System.out.println("Please enter valid Series file name:");
				fileName = input.readLine();
				if (fileName.isEmpty())
					++enterTwice;
				else
					enterTwice = 0;
				if (enterTwice == 2)
					System.exit(0);
			}
		}
	}

	/**
	 * Parser for Movie string in movie file - once parsed adds to newMovie.
	 * 
	 * @param fileLine line from file that contains movie details.
	 * @return Movie  returns new movie populated from parsed line from file.
	 */
	public static Movie parseMovieLine(String fileLine){

		Integer releaseYear = null; // Int.eger to store year info into.
		int year = 0;// Used to check values for year are good.
		String mediaLine = fileLine.trim(); // Trim fileLine and store string as original mediaLine.
		String yearString = ""; // Variable for string value of year information.
		String title = null; // String to store title info into.
		String searchResultOutput = ""; // String to store printable line for media object search result.
		Roman edition = new Roman(); // Create new Roman variable for edition information.
		ReleaseType releaseType = null; // Create ReleaseType enum variable - set to null.
		String testString; // Establish testString to use during parsing.
		char testCharacter; // Establish testCharacter to use for testing.
		boolean releaseTypeSet = false; // Flag on if releaseType is set.
		boolean checkSecondYear = false; // Flag to check second year if first year bad data.

		fileLine = fileLine.trim(); // Trim beginning and ending white space from fileLine.

		// Collect year information in String contained at end of each fileLine.
		yearString = fileLine.substring(fileLine.length()-4,fileLine.length());

		if(!yearString.equals("????")){ // If movie has released date check for valid year.
			releaseYear =  Integer.valueOf(yearString); // Create int from year data to check valid.
			year = (int) releaseYear;

			if(year <1895 || year > 2030) {
				releaseYear = null; // If year information is bad <1895 or >2030 then use default for unreleased.
				checkSecondYear = true;
			}
		}
		// Else if yearString unknown designated by ????.
		else {

			releaseYear = null; // If ???? set to null.
			checkSecondYear = false;
		}
		// Trim off the year and then any whitespace on end of fileLine.
		fileLine = fileLine.substring(0,fileLine.length()-5).trim();
		// Set a test string of 3 characters which could be ??), DD), /I), II), (V), TV).
		// D-stands for Digit, I - stands for Roman Numeral.
		testString = fileLine.substring(fileLine.length()-3,fileLine.length());
		// Set testCharacter to first character in testString which could be {?,D,/,I,(,T}.
		testCharacter = testString.charAt(0);
		// If Else to check if Video or TV releaseType ( or T is next grouping in fileLine.
		if(testCharacter == '('){
			// Set ReleaseType to video. 
			releaseType = ReleaseType.VIDEO;
			releaseTypeSet = true; // ReleaseType set.
			// Trim fileLine of releaseType and white space.
			fileLine = fileLine.substring(0,fileLine.lastIndexOf("(")).trim();
			// Set new testString and testCharacter.
			testString = fileLine.substring(fileLine.length()-3,fileLine.length());
			testCharacter = testString.charAt(0);
		}
		else if(testCharacter =='T'){
			// Set ReleaseType to TV.
			releaseType = ReleaseType.TVMOVIE;
			releaseTypeSet = true;//releaseType set
			// Trim fileLIne of releaseType and whitespace. 
			fileLine = fileLine.substring(0,fileLine.lastIndexOf("(")).trim();
			// Set new testString and testCharacter.
			testString = fileLine.substring(fileLine.length()-3,fileLine.length());
			testCharacter = testString.charAt(0);
		}
		// Note: year in parenthesis is ignored in favor of last year. 
		// Check if testCharacter is a digit or ? meaning parse it as year.
		if(Character.isDigit(testCharacter)||testCharacter == '?'){
			// Grab title from beginning and remove white space between title and (year).
			title = fileLine.substring(0,fileLine.length()-6).trim();
			if(!releaseTypeSet)
				releaseType = ReleaseType.SCREEN; //if releaseType isn't set then set to Screen

			//If year on end of fileLine was bad then possibly take year in () after title
			if(checkSecondYear){
				testString = fileLine.substring(fileLine.indexOf("(")+1,fileLine.lastIndexOf(")"));
				if(!testString.equals("????")){
					releaseYear = Integer.valueOf(yearString);
					year = (int) releaseYear;
				}
				if(!testString.equals(yearString)&& (year >1895 && year < 2030)){
					releaseYear = Integer.valueOf(testString);
				}
			}
		}
		else { // Get edition information from FileLine.
			// Grab title from beginning and remove white space between title and (year).
			title = fileLine.substring(0,fileLine.lastIndexOf("/")-5).trim();
			// Set Roman edition information based on information after /.
			edition.setRomanNumeral(fileLine.substring(fileLine.lastIndexOf("/")+1,
					fileLine.lastIndexOf(")")));
			if(!releaseTypeSet)
				releaseType = ReleaseType.SCREEN; //
			// If year on end of fileLine was bad then possibly take year in () after title.
			if(checkSecondYear){
				testString = fileLine.substring(fileLine.indexOf("(")+1,fileLine.lastIndexOf("/"));
				if(!testString.equals("????"))
					year = (int) Integer.valueOf(yearString);
				if(!testString.equals(yearString) && year >1895 && year < 2030)
					yearString = testString;
			}
		}		
		// Build printable line for search results output for particular Movie object.
		searchResultOutput = "MOVIE ";
		if(releaseType.equals(ReleaseType.TVMOVIE))
			searchResultOutput += "(TV) : " + title + " " + "(";
		else if (releaseType.equals(ReleaseType.VIDEO))
			searchResultOutput += "(straight to video): " + title + "(";
		else
			searchResultOutput += ": " + title + "(";
		if(releaseYear==null)
			searchResultOutput += "UNSPECIFIED)";
		else
			searchResultOutput += releaseYear + ")";
		// Set newMovie parameters to parsed information and then return newMovie.
		Movie newMovie = new Movie();
		newMovie.setTitle(title);
		newMovie.setSearchResultOutput(searchResultOutput);
		newMovie.setReleaseType(releaseType);
		newMovie.setReleaseYear(releaseYear);
		newMovie.setEdition(edition);
		newMovie.setMediaLine(mediaLine);
		return newMovie;
	}

	/**
	 * Parser for TVSeries string in series file - once parsed adds to newSeries
	 * 
	 * @param fileLine  line from file that contains TV Series information
	 * 
	 * @return TVSeries  returns populated new TVSeries from parsed line from file
	 */
	public static TVSeries parseSeriesLine(String fileLine){

		String mediaLine = fileLine.trim(); // Trim fileLine and store string as original mediaLine.
		String title = null; // String to store title info into. 
		String searchResultOutput = null; // String to store printable line for media object search result.
		String stringYear = null; // Variable for string value of year information.
		Integer releaseYear = null; // Integer to store year info into.
		String uniqueTitle = null; // Unique title that matches episode with its Series.
		Integer endingYear = null; // Year end of series run - if no end run year then null.
		ArrayList<Episode> episodes = new ArrayList<Episode>(); // Array of episodes for that TV Series.

		// Parse out title out of media line between quotes
		title = mediaLine.substring(1, mediaLine.lastIndexOf('\"'));
		// Parse out unique title for series that includes "Title" (Year)
		uniqueTitle = mediaLine.substring(0,mediaLine.indexOf(')')+1);
		// Remove title out of media line for later parsing
		String noTitle = mediaLine.substring(mediaLine.lastIndexOf('\"')).trim();
		// Parse out string format of year information for series
		stringYear = noTitle.substring(noTitle.indexOf('(')+1,noTitle.indexOf(')'));

		try{// Try and catch to see if valid integer in string year read in.
			releaseYear = Integer.valueOf(stringYear);
		}
		catch(NumberFormatException e){// If invalid set releaseYear to null.
			releaseYear = null;
		}
		// Begin to build string for results of search output.		
		searchResultOutput = "SERIES: " + title + " (" + releaseYear + "-";
		// Parse ending string year from end of mediaLine. 
		stringYear = mediaLine.substring(mediaLine.length()-4,mediaLine.length());
		if(stringYear.equals("????")){// If ???? for year then set ending to null and add Unspecified to output string.
			endingYear = null;
			searchResultOutput += "UNSPECIFIED)";
		}
		else{// Else put valid year into ending year and into search result output.
			endingYear = Integer.valueOf(stringYear);
			searchResultOutput += endingYear + ")";
		}
		// Create new TVSeries object and fill with variables from parse.
		TVSeries newSeries = new TVSeries();
		newSeries.setMediaLine(mediaLine);
		newSeries.setTitle(title);
		newSeries.setSearchResultOutput(searchResultOutput);
		newSeries.setReleaseYear(releaseYear);
		newSeries.setUniqueTitle(uniqueTitle);
		newSeries.setEpisodes(episodes);
		newSeries.setEndingYear(endingYear);
		newSeries.setReleaseType(ReleaseType.TV);
		// Return the new series.
		return newSeries;
	}

	/**
	 * Parser for Episode string in series file - once parsed adds to newSeries
	 * 
	 * @param fileLine	String representation of Episode to parse
	 * @return Episode	new Episode with parsed information loaded
	 */
	public static Episode parseEpisodeLine(String fileLine){

		String mediaLine = fileLine.trim(); // Trim fileLine and store string as original mediaLine.
		String episodeTitle = null; // String to store title info into. 
		String searchResultOutput = null; // String to store printable line for media object search result.
		String stringYear = null; // Variable for string value of year information.
		Integer releaseYear = null; // Integer to store year info into.
		String uniqueSeriesTitle = null; // Unique title that matches episode with its Series.
		String seriesTitle = null; // Title of series that this episode will be added to.
		String status = null; // Status of the Series/Episode.
		String broadcastInfo = null; // The broadcast information for this episode.
		String testString = null ;
		char testCharacter; 
		boolean statusIncluded = false; // Whether or not the status is included in the data.
		boolean episodeIncluded = false; // Whether or not an episode is included in the data.
		boolean detailsIncluded = true; // Whether or not details are included in the data.
		String episodeDetails = ""; // Episode Details variable.
		Date episodeDate = null; //  Episode Air Date variable.
		String dateString = ""; // Date String variable.

		int statusIndexStart = 0;
		int statusIndexEnd = 0;
		int episodeIndexStart = 0;
		int episodeIndexEnd = 0;
		int titleIndexStart = 0;
		int titleIndexEnd = 0;

		uniqueSeriesTitle = mediaLine.substring(0,mediaLine.indexOf(')')+1);
		stringYear = mediaLine.substring(mediaLine.length()-4,mediaLine.length());

		if(stringYear.equals("????"))
			releaseYear = null; // Set Release Year to null if data string = ????.
		else
			releaseYear = Integer.valueOf(stringYear); // Set the value of the release year to the data provided.

		if(mediaLine.contains("{")) {
			episodeDetails = mediaLine.substring(mediaLine.indexOf('{')+1,mediaLine.indexOf('}')); // If details are present then set them to episode details variable.
		} else {
			detailsIncluded = false; // Set details included to false if details are missing.
		}
		
		seriesTitle = mediaLine.substring(mediaLine.indexOf('\"')+1,mediaLine.lastIndexOf('\"')).trim() + ":";

		if (detailsIncluded) {
			statusIncluded = mediaLine.contains("{{"); // Parse status based on {{.
			if(statusIncluded){
				statusIndexStart = mediaLine.indexOf("{{")+2; // Parse status based on {{.
				statusIndexEnd = mediaLine.indexOf("}}"); // Parse status based on ending }}.
				status = mediaLine.substring(statusIndexStart,statusIndexEnd).trim();
				if(episodeDetails.contains("{")){
					episodeDetails = null;
				}
			}
			
			if(episodeDetails!=null){
			}
			episodeIncluded = mediaLine.contains("(#"); // Parse episode based on (#.

			if(episodeIncluded){
				episodeIndexStart = mediaLine.indexOf("(#")+2; // Parse episode based on (#.	
				episodeIndexEnd = mediaLine.indexOf(')',episodeIndexStart);
				broadcastInfo = mediaLine.substring(episodeIndexStart,episodeIndexEnd).trim();
			}
			titleIndexStart = mediaLine.indexOf('{')+1; // Parse the start of the title based on {.
			titleIndexEnd = mediaLine.indexOf('}'); // Parse the end of the title based on }.
			episodeTitle = mediaLine.substring(titleIndexStart,titleIndexEnd+1).trim();
		} else {
			episodeTitle = seriesTitle;
		}

		if(statusIncluded)
			searchResultOutput += " (" + status + ")";
		
		if(releaseYear==null)
			searchResultOutput += " ????"; // If the release year is empty or null output ????.
		else
			searchResultOutput += " " + releaseYear; // If the Release year is not empty then output the year.

		//System.out.println(episodeTitle); // Print out the episode title.
		testString = episodeTitle.substring(0,2); 

		testCharacter = testString.charAt(0);

		if(Character.isLetterOrDigit(testCharacter)||testCharacter=='\''||testCharacter=='.'){
			episodeTitle = seriesTitle + " " + mediaLine.substring(titleIndexStart,titleIndexEnd).trim();
			if(episodeTitle.contains("(#")&&episodeTitle.contains(")")){

				titleIndexEnd = episodeTitle.indexOf("(#");
				episodeTitle = episodeTitle.substring(0,titleIndexEnd).trim();
			}
		}
		else if(testCharacter=='('){
			episodeTitle = seriesTitle + " #" + broadcastInfo;

		}
		searchResultOutput = "EPISODE: " + episodeTitle + " (";
		if(statusIncluded)
			searchResultOutput += status + ")"; // If the status is included return search result output plus the status.
		else
			searchResultOutput += releaseYear + ")"; // If the status is not included return search result output plus the year of release.

		Episode newEpisode = new Episode(); // New Episode Construction.

		newEpisode.setMediaLine(mediaLine); // Set the Media Line Info.
		newEpisode.setTitle(episodeTitle); // set the Episode Title.
		newEpisode.setReleaseYear(releaseYear); // Set the Release Year.
		newEpisode.setReleaseType(ReleaseType.EPISODE); // Set the Release Type to Episode.
		newEpisode.setUniqueSeriesTitle(uniqueSeriesTitle); // Set the Unique Series Title.
		newEpisode.setStatus(status); // Set the Status of the Series/Episode.
		newEpisode.setSearchResultOutput(searchResultOutput); // Search results output.
		newEpisode.setBroadcastInfo(broadcastInfo); // Set the broadcast information.
		newEpisode.setEpisodeDate(episodeDate); // set the Episode air date.
		newEpisode.setEpisodeDetails(episodeDetails); // set the episode details.

		return newEpisode;
	}

	/**
	 * Method to search text in Media array
	 * 
	 * @param searchString
	 * @param episodesIn
	 * @param exact
	 * @return ArrayList<Media>
	 */
	public ArrayList<Media> searchTextInMedia(String searchString, boolean episodesIn, boolean exact){
		ArrayList<Media> filteredList = new ArrayList<Media>();
		if(exact){
			int resultIndex = Collections.binarySearch(media, new Media("", searchString), Media.TITLE_FIRST_COMPARATOR);
			if(resultIndex > 0){
				filteredList.add(media.get(resultIndex));
			}
			else if (episodesIn){
				for(Media m : media)
					if(m instanceof TVSeries)
						filteredList.addAll(((TVSeries) m).searchEpisodes(searchString, true));
			}
		}else{
			for(Media m : media){
				if(m.getTitle().toLowerCase().contains(searchString.toLowerCase()))
					filteredList.add(m);
				if (episodesIn && m instanceof TVSeries)
					filteredList.addAll(((TVSeries)m).searchEpisodes(searchString, false));
			}
		}
		return filteredList;
	}

	/**
	 * Method to search year(s) in Media array
	 * 
	 * @param yearSearch
	 * @param episodesIn
	 * @return ArrayList<Media>
	 */
	public ArrayList<Media> searchYearInMedia(String yearString, boolean episodesIn){
		ArrayList<String> years = parseYear(yearString);
		ArrayList<Media> filteredList = new ArrayList<Media>();
		for(Media m : media){
			for(String year: years){
				if(m.getReleaseYear() != null && m.getReleaseYear().equals(Integer.valueOf(year))){
					filteredList.add(m);
					if(m.isTVSeries() && episodesIn){
						for(Episode e: ((TVSeries) m).getEpisodes()){
							if(e.getReleaseYear()!=null && e.getReleaseYear().equals(Integer.valueOf(year)))
								filteredList.add(e);
						}
					}
				}
			}
		}
		return filteredList;
	}

	public static ArrayList<String> parseYear(String yearStr){
		ArrayList<String> years = new ArrayList<String>();
		String currentYear = "";
		for(int i = 0; i < yearStr.length(); i++){
			char thisChar = yearStr.charAt(i);
			if (thisChar != ',' && !Character.isWhitespace(thisChar) && thisChar != '-'){
				currentYear += thisChar;
				if(i == yearStr.length() - 1)
					years.add(currentYear);
			}
			else if(thisChar == ','){
				years.add(currentYear);
				currentYear = "";
			}
			else if(thisChar == '-'){
				String secondYear = "";
				while(i < yearStr.length() - 1){
					i++;
					thisChar = yearStr.charAt(i);
					if(!Character.isDigit(thisChar)){
						break;
					}
					secondYear += thisChar;
				}
				for(int j = Integer.valueOf(currentYear); j <= Integer.valueOf(secondYear); j++){
					years.add(Integer.toString(j));
				}
				currentYear = "";
			}
		}
		return years;
	}

	/**
	 * To String Method to convert to a readable string.
	 */
	public String toString(){
		String str = "";
		for(Media m: media)
			str += m.getTitle() + ": " + m.getReleaseYear() + "\n";
		return str;
	}

	/**
	 * 
	 * @param media
	 * @param movies
	 * @param series
	 * @return
	 */
	public ArrayList<Media> getAllMedia(Media media, MDb movies, MDb series){
		
		ArrayList<Media> masterMediaList = new ArrayList<Media>();
		ArrayList<Episode> tempMedia = new ArrayList<Episode>();
		
		if(media.getClass()==(new Movie().getClass())){
			masterMediaList = movies.getMedia();
		}
		else if(media.getClass()==(new TVSeries().getClass())){
			masterMediaList = series.getMedia();
		}
		else if(media.getClass()==(new Episode().getClass())){
			for(Media m: series.getMedia()){
				tempMedia = ((TVSeries)m).getEpisodes();
				for(Episode e: tempMedia){
					masterMediaList.add(e);
				}
			}
		}
		else{
			masterMediaList.addAll(movies.getMedia());
			masterMediaList.addAll(series.getMedia());
			for(Media m: series.getMedia()){
				for(Episode e: ((TVSeries)m).getEpisodes())
					masterMediaList.add(e);				
			}
		}
		return masterMediaList;
	}
}