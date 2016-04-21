package MediaDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
//import java.util.Scanner;
import java.util.Set;

/**
 * Project #4
 * CS 2334, Section 10
 * April 18, 2016
 * <P>
 * The MakerCredits is a linkedHashMap for mediaMakers 
 * used by MDb in a similar way to an ArrayList of media
 * </P>
 * 
 * @version 1.0 
 */

public class MakerCredits implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6426634447386362588L;
	
	/**
	 * LinkedHashMap for mediaMakers linking mediaMaker with its corresponding ArrayList of Media Credits
	 */
	private LinkedHashMap<MediaMaker, ArrayList<Media>> makerCredits; 

	// quick hack to test if populating is working, returns private field makerCredits
	public LinkedHashMap<MediaMaker, ArrayList<Media>> getAll() {
		return this.makerCredits;
	}
	
	/**
	 * Default constructor for MakerCredits
	 */
	public MakerCredits() {
		setMakerCredits(new LinkedHashMap<MediaMaker, ArrayList<Media>>());
	}

	/**
	 * Setter method for makerCredits
	 * 
	 * @param makerCredits	the credits for the media makers
	 */
	public void setMakerCredits(LinkedHashMap<MediaMaker, ArrayList<Media>> makerCredits) {
		this.makerCredits = makerCredits;
	}

	/**
	 * Add media credits to makerCredits
	 * 
	 * @param mediaMaker	mediaMaker object used as key for linkedhashmap
	 * @param media	ArrayList of Media objects the mediaMaker has credits in
	 */
	public void addCredits(MediaMaker mediaMaker, ArrayList<Media> media){
		makerCredits.put(mediaMaker, media);
	}

	/**
	 * Is empty check on this makerCredits
	 * 
	 * @return boolean returns true if makerCredits empty
	 */
	public void setCreditForMaker(MediaMaker mediaMaker, Media m){
		ArrayList<Media> updatedValue = this.makerCredits.get(mediaMaker);
		updatedValue.add(m);
		makerCredits.put(mediaMaker, updatedValue);
	}

	/**
	 * Getter for Credits for Maker. 
	 * 
	 * @param mediaMaker media maker that media is needed
	 * @return ArrayList<Media> media for maker to return
	 */
	public ArrayList<Media> getCreditsForMaker(MediaMaker mediaMaker){
		ArrayList<Media> returnMedia = this.makerCredits.get(mediaMaker);
		return returnMedia;
	}
	
	/**
	 * Is empty boolean.
	 * 	
	 * @return boolean true if object is empty
	 */
	public boolean isEmpty(){
		if (this.size() == 0)
			return true;
		return false;
	}
	
	/**
	 * This Reads in the Media Maker File.
	 * 
	 * @param fileName
	 * @throws IOException 
	 */
	public void readMakerFileIn(char flag, String fileName, MDb movies, MDb series) throws IOException{

		InputStreamReader scanIn = new InputStreamReader(System.in);//initialize scanner to send to methods for user input;
		BufferedReader input = new BufferedReader(scanIn); // Switched scanner over to buffered reader to read in for enterTwice
		int enterTwice = 0;

		boolean fileNotFound = true;
		boolean firstLine = true;
		String nextLine = ""; //String to put read line into for passing to parsing method
		String testString = "";
		Character testCharacter = null;

		boolean actorFile = false;
		boolean producerFile = false;
		boolean directorFile = false;

		String makerName = "";
		String makerLastName = "";
		String makerFirstName = "";
		String makerSuffix = "";
		boolean getSuffix = true;

		String[] makerCastingDetails = new String[5];
		int indexOfLessThan;
		String actorOrder = null;
		int indexOfOpenBracket;
		String actorRole = null;
		boolean stillParsingCreditDetails = true;
		int detailsIndex;
		int indexLastCloseParen;
		int indexLastOpenParen;
		int indexOfFirstParen;
		String duplicateYear;

		Movie newMovie;
		boolean movieExistsInDB;
		Episode newEpisode;
		boolean seriesExistsInDB;
		TVSeries newSeries;
		boolean episodeExistsInSeries;
		ArrayList<Episode> episodes;

		ArrayList<Media> newMakerCredits = null;

		MediaMaker newMaker = null;

		String suspendedString = null;
		int indexEndOfFirstName;
		int indexEndOfLastName;


		if(flag=='a'){
			actorFile = true;
		}
		else if(flag=='p'){
			producerFile = true;		
		}
		else{
			directorFile = true;
		}

		while(fileNotFound)
			try{ //Try read in and catch IOException
				FileReader fr = new FileReader(fileName); //create FileReader variable on new fileName
				BufferedReader br = new BufferedReader(fr); // create BufferedReader on FileReader variable
				nextLine = (br.readLine());//read next line used buffered reader
				while(nextLine!=null){ //Buffered reader loads in null if no new lines - continue until null found
					nextLine = nextLine.trim();

					if(firstLine){//Grab actor name at beginning and trim nextline to just media info

						if(actorFile)
							newMaker = new Actor();
						else if(producerFile)
							newMaker = new Producer();
						else
							newMaker = new Director();

						firstLine = false;
						makerName = nextLine.substring(0,nextLine.indexOf("\t"));

						newMakerCredits = new ArrayList<Media>();
						firstLine = false;
						indexEndOfLastName = makerName.indexOf(',');
						makerLastName = makerName.substring(0,indexEndOfLastName);
						if(!makerName.contains("(")){
							indexEndOfFirstName = makerName.length();
							getSuffix = false;
						}
						else{
							getSuffix = true;
							indexEndOfFirstName = makerName.indexOf("(")-2;
						}
						makerFirstName = makerName.substring(indexEndOfLastName+2,indexEndOfFirstName);


						if(getSuffix){
							makerSuffix = makerName.substring(makerName.indexOf("(")+1,makerName.indexOf(")"));
							newMaker.setMakerSuffixInfo(makerSuffix);
						}
						nextLine = nextLine.substring(nextLine.indexOf("\t"),nextLine.length()).trim();

						newMaker.setMakerFamilyName(makerLastName);
						newMaker.setMakerFirstName(makerFirstName);
						newMaker.setMakerName(makerName);
					}
					else if(nextLine.isEmpty()){// if nextLine blank then prepare for new actor
						firstLine = true;
						this.addCredits(newMaker,newMakerCredits);

						/* System.out.println("Something should be added here");
						*  System.out.println(newMaker.getMakerName());
						*  for (Media i: newMakerCredits)
						*  System.out.println(i.getTitle());
						*/

						nextLine = br.readLine();//read next line used buffered reader
						continue;
					}

					indexOfFirstParen = nextLine.indexOf("(");

					if(Character.isDigit(nextLine.charAt(indexOfFirstParen+1))
							||nextLine.charAt(indexOfFirstParen+1)=='?' ){
						duplicateYear = " " + nextLine.substring(indexOfFirstParen+1,indexOfFirstParen+5);
					}
					else
						duplicateYear = " ????";

					if(actorFile){
						testCharacter = nextLine.charAt(nextLine.length()-1);
						//System.out.println(testCharacter); TEST LINE.
						if(testCharacter == '>'){//Grab actor order in credits info
							indexOfLessThan = nextLine.indexOf('<',nextLine.length()/2);
							actorOrder = nextLine.substring(indexOfLessThan+1,nextLine.indexOf('>')).trim();
							nextLine = nextLine.substring(0,indexOfLessThan).trim();
							makerCastingDetails[1] = actorOrder;
							//System.out.println(nextLine); TEST LINE.
						}
						if(nextLine.contains("[")){
							indexOfOpenBracket = nextLine.indexOf('[');
							actorRole = nextLine.substring(indexOfOpenBracket+1,nextLine.lastIndexOf(']')).trim();
							makerCastingDetails[0] = actorRole;
							nextLine = nextLine.substring(0,indexOfOpenBracket).trim();
						}
						else
							makerCastingDetails[0] = "N/A";
						//System.out.println(nextLine); TEST LINE.
					}

					if(nextLine.charAt(0)!='\"' && nextLine.contains("{{")){
						suspendedString = nextLine.substring(nextLine.indexOf("{{")+2,nextLine.indexOf("}}")).trim();
						nextLine = 	nextLine.substring(0,nextLine.indexOf("{{")).trim() + " " 
								+ nextLine.substring(nextLine.indexOf("}}")+2,nextLine.length());
					}

					detailsIndex = 1;
					stillParsingCreditDetails = true;
					while(stillParsingCreditDetails){	

						indexLastCloseParen = nextLine.lastIndexOf(')');
						indexLastOpenParen = nextLine.lastIndexOf('(');

						testString = nextLine.substring(indexLastOpenParen+1,indexLastCloseParen);
						testCharacter = testString.charAt(0);
						//System.out.println(testString);
						//System.out.println(testCharacter + " 		" + Character.isDigit(testCharacter));

						if(!(testString.startsWith("TV") ||
								testString.equals("V") ||
								Character.isDigit(testCharacter) ||
								testString.startsWith("?") ||
								testString.contains("#"))) {
							detailsIndex++;

							makerCastingDetails[detailsIndex] = nextLine.substring(indexLastOpenParen+1,indexLastCloseParen);
							//System.out.println("makerDetails : " + detailsIndex + " " + makerCastingDetails[detailsIndex]+ " "); TEST LINE.
							nextLine = nextLine.substring(0,indexLastOpenParen).trim();
							stillParsingCreditDetails = true;

							//System.out.println(nextLine); TEST LINE
						}
						else
							stillParsingCreditDetails = false;
					}
					if(nextLine.startsWith("\"")){
						nextLine += duplicateYear;
						newEpisode = MDb.parseEpisodeLine(nextLine);
						//TODO Add maker to episode cast
						newMakerCredits.add(newEpisode);

						seriesExistsInDB = false;
						newSeries = new TVSeries();
						for(Media s: movies.getMedia()){
							if(s.getClass().equals(newSeries.getClass())){
								if(((TVSeries)s).getUniqueTitle().equals(newEpisode.getUniqueSeriesTitle())){
									seriesExistsInDB = true;
									episodes = ((TVSeries)s).getEpisodes();
									for(Episode e: episodes){
										if(newEpisode.equals(e)){
											episodeExistsInSeries = true;
											continue;
										}
										else
											newSeries.add(newEpisode);
									}
									continue;
								}
								else{//Add Episode to Series and Populate Series Info and add Series to DB
									newSeries = MDb.parseSeriesLine(newEpisode.getUniqueSeriesTitle() 
											+ " " + duplicateYear + " - ????");
									newSeries.add(newEpisode);
									series.add(newSeries);
								}
							}	
						}
					}
					else { //Parse movie information
						nextLine += duplicateYear;
						newMovie = MDb.parseMovieLine(nextLine);
						newMovie.setStatus(suspendedString);
						//TODO add maker into newMovie cast
						newMakerCredits.add(newMovie);
						movieExistsInDB = false;
						for(Media m: movies.getMedia()){
							if(m.getClass().equals(newMovie.getClass())){
								if(newMovie.equals(m)){
									movieExistsInDB = true;
									continue;
								}
							}
						}
						if(!movieExistsInDB){
							movies.add(newMovie);
						}
					}
					nextLine = (br.readLine());
				}
				fileNotFound = false;
				br.close();
				fr.close();
				//System.out.println("end of Actor File");
			}
		catch(IOException e){
			if (fileName.isEmpty())
				++enterTwice;
			System.out.println("Failed to read file.");
			fileNotFound = true;
			if(actorFile)
				System.out.println("Please enter valid Actor file name:");
			else if(producerFile)
				System.out.println("Please enter valid Producer file name: ");
			else
				System.out.println("Please enter valid Director file name: ");
			fileName = input.readLine();
			++enterTwice;
			if (!fileName.isEmpty())
				enterTwice = 0;
			if (enterTwice == 2)
				System.exit(0);
		}
	}

	/**
	 * Iterates through LinkedHashMap to search for media makers and to record their media
	 * 
	 * @param searchName	Name to search for
	 * @param exactResult	Boolean flag for exact or partial results
	 * @return MakerCredits  makercredits hashmap
	 */
	public MakerCredits searchMakerCredits(String searchName, boolean exactResult) {
		LinkedHashMap<MediaMaker, ArrayList<Media>> results = new LinkedHashMap<MediaMaker, ArrayList<Media>>();
		Set<MediaMaker> keySet = this.makerCredits.keySet();

		if (exactResult) {
			for (MediaMaker maker: keySet) {
				if (maker.getMakerName().equalsIgnoreCase(searchName)) 
					results.put(maker, this.makerCredits.get(maker));
			}
		} else {
			for (MediaMaker maker: keySet) {
				if (maker.getMakerName().toLowerCase().contains(searchName))
					results.put(maker, this.makerCredits.get(maker));
			}
		}
		MakerCredits credits = new MakerCredits();
		credits.setMakerCredits(results);
		return credits;
	}

	/**
	 * Method to determine the size of a maker credits object by measuring the key set
	 * 
	 * @return int size of keySet for hashmap
	 */
	public int size() {
		int size = this.makerCredits.keySet().size();
		return size;
	}

	/**
	 *  Method to print correctly formatted credits
	 * @param actors  actor media makers
	 * @param producers producer media makers
	 * @param directors director media makers
	 * @param singleMaker boolean to determine if result of search is single maker key
	 */
	public static void printCredits(MakerCredits actors, MakerCredits producers, MakerCredits directors, boolean singleMaker) {
		if (singleMaker) {
			if (actors.isEmpty() && producers.isEmpty() && directors.isEmpty()) {
				System.out.println("No results found"); 
				return;
			}
			LinkedHashMap<MediaMaker, ArrayList<Media>> actorTemp = actors.getAll();
			LinkedHashMap<MediaMaker, ArrayList<Media>> directorTemp = directors.getAll();
			LinkedHashMap<MediaMaker, ArrayList<Media>> producerTemp = producers.getAll();

			if (!actorTemp.isEmpty()) {
				System.out.println("ACTING");
				for (MediaMaker maker: actorTemp.keySet()) {
					for (Media media: actorTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
			}

			if (!directorTemp.isEmpty()) {
				System.out.println("DIRECTING");
				for (MediaMaker maker: directorTemp.keySet()) {
					for (Media media: directorTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
			}
			
			if (!producerTemp.isEmpty()) {
				System.out.println("PRODUCING");
				for (MediaMaker maker: producerTemp.keySet()) {
					for (Media media: producerTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
			}
		} else {
			LinkedHashMap<MediaMaker, ArrayList<Media>> actorTemp = actors.getAll();
			LinkedHashMap<MediaMaker, ArrayList<Media>> directorTemp = directors.getAll();
			LinkedHashMap<MediaMaker, ArrayList<Media>> producerTemp = producers.getAll();
			Set<MediaMaker> directorKeySet = directorTemp.keySet();
			Set<MediaMaker> producerKeySet = producerTemp.keySet();

			for (MediaMaker maker: actorTemp.keySet()) {
				System.out.println(maker.getMakerName());

				if (actorTemp.keySet().contains(maker)) {
					System.out.println("ACTING");
					for (Media media: actorTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}

				if (directorTemp.keySet().contains(maker)) {
					System.out.println("DIRECTING");
					for (Media media: directorTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
				
				if (producerTemp.keySet().contains(maker)) {
					System.out.println("PRODUCING");
					for (Media media: producerTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
				directorKeySet.remove(maker);
				producerKeySet.remove(maker);
			}

			for (MediaMaker maker: directorKeySet) {
				System.out.println(maker.getMakerName());
				
				if (actorTemp.keySet().contains(maker)) {
					System.out.println("ACTING");
					for (Media media: actorTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
				
				if (directorTemp.keySet().contains(maker)) {
					System.out.println("DIRECTING");
					for (Media media: directorTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
				
				if (producerTemp.keySet().contains(maker)) {
					System.out.println("PRODUCING");
					for (Media media: producerTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
				producerKeySet.remove(maker);
			}
			
			for (MediaMaker maker: producerKeySet) {
				System.out.println(maker.getMakerName());
				
				if (actorTemp.keySet().contains(maker)) {
					System.out.println("ACTING");
					for (Media media: actorTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
				
				if (directorTemp.keySet().contains(maker)) {
					System.out.println("DIRECTING");
					for (Media media: directorTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
				
				if (producerTemp.keySet().contains(maker)) {
					System.out.println("PRODUCING");
					for (Media media: producerTemp.get(maker)) {
						System.out.println(media.toString());
					}
				}
			}
		}
	}
	
	/**
	 *  Method to create string of correctly formatted credits
	 * @param actors  actor media makers
	 * @param producers producer media makers
	 * @param directors director media makers
	 * @param singleMaker boolean to determine if result of search is single maker key
	 * @return String 
	 */
	public static String saveCreditsToString(MakerCredits actors, MakerCredits producers, MakerCredits directors, boolean singleMaker) {
		String results = "";
		if (singleMaker) {
			if (actors.isEmpty() && producers.isEmpty() && directors.isEmpty()) {
				results += ("\nNo results found"); 
				return results;
			}
			LinkedHashMap<MediaMaker, ArrayList<Media>> actorTemp = actors.getAll();
			LinkedHashMap<MediaMaker, ArrayList<Media>> directorTemp = directors.getAll();
			LinkedHashMap<MediaMaker, ArrayList<Media>> producerTemp = producers.getAll();

			if (!actorTemp.isEmpty()) {
				results += ("\nACTING");
				for (MediaMaker maker: actorTemp.keySet()) {
					for (Media media: actorTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
			}

			if (!directorTemp.isEmpty()) {
				results += ("\nDIRECTING");
				for (MediaMaker maker: directorTemp.keySet()) {
					for (Media media: directorTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
			}
			
			if (!producerTemp.isEmpty()) {
				results += ("\nPRODUCING");
				for (MediaMaker maker: producerTemp.keySet()) {
					for (Media media: producerTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
			}
		} else {
			LinkedHashMap<MediaMaker, ArrayList<Media>> actorTemp = actors.getAll();
			LinkedHashMap<MediaMaker, ArrayList<Media>> directorTemp = directors.getAll();
			LinkedHashMap<MediaMaker, ArrayList<Media>> producerTemp = producers.getAll();
			Set<MediaMaker> directorKeySet = directorTemp.keySet();
			Set<MediaMaker> producerKeySet = producerTemp.keySet();

			for (MediaMaker maker: actorTemp.keySet()) {
				results += ("\n\n" + maker.getMakerName());

				if (actorTemp.keySet().contains(maker)) {
					results += ("\nACTING");
					for (Media media: actorTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}

				if (directorTemp.keySet().contains(maker)) {
					results += ("\nDIRECTING");
					for (Media media: directorTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
				
				if (producerTemp.keySet().contains(maker)) {
					results += ("\nPRODUCING");
					for (Media media: producerTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
				directorKeySet.remove(maker);
				producerKeySet.remove(maker);
			}

			for (MediaMaker maker: directorKeySet) {
				results += ("\n\n" + maker.getMakerName());
				
				if (actorTemp.keySet().contains(maker)) {
					results += ("\nACTING");
					for (Media media: actorTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
				
				if (directorTemp.keySet().contains(maker)) {
					results += ("\nDIRECTING");
					for (Media media: directorTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
				
				if (producerTemp.keySet().contains(maker)) {
					results += ("\nPRODUCING");
					for (Media media: producerTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
				producerKeySet.remove(maker);
			}
			
			for (MediaMaker maker: producerKeySet) {
				results += ("\n\n" + maker.getMakerName());
				
				if (actorTemp.keySet().contains(maker)) {
					results += ("\nACTING");
					for (Media media: actorTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
				
				if (directorTemp.keySet().contains(maker)) {
					results += ("\nDIRECTING");
					for (Media media: directorTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
				
				if (producerTemp.keySet().contains(maker)) {
					results += ("\nPRODUCING");
					for (Media media: producerTemp.get(maker)) {
						results += ("\n" + media.toString());
					}
				}
			}
		}
		return results;
	}
	
	/**
	 * Method to allow for determining the name of a maker when there is only one
	 * @return String that is first makerName
	 */
	public String getName() {
		Set<MediaMaker> makers = this.makerCredits.keySet();
		for (MediaMaker maker: makers)
			return maker.getMakerName();
		return null;
	}
	
	/**
	 * 
	 * @param maker
	 * @param actorCredits
	 * @param directorCredits
	 * @param producerCredits
	 * @return HashSet<Media>  masterCreditsList
	 */
	public HashSet<Media> getAllCredits(MediaMaker maker, LinkedHashMap<Actor,ArrayList<Media>> actorCredits,
			LinkedHashMap<Director,ArrayList<Media>> directorCredits, LinkedHashMap<Producer,ArrayList<Media>> producerCredits){
		
		LinkedHashMap<MediaMaker, ArrayList<Media>> monsterHash = new LinkedHashMap<MediaMaker, ArrayList<Media>>();
	
		if(maker.getClass()==(new Actor().getClass())){
			monsterHash.putAll(actorCredits);
		}
		else if(maker.getClass()==(new Director().getClass())){
			monsterHash.putAll(directorCredits);
		}
		else if(maker.getClass()==(new Director().getClass())){
			monsterHash.putAll(producerCredits);
		}
		else{
			monsterHash.putAll(actorCredits);
			monsterHash.putAll(directorCredits);
			monsterHash.putAll(producerCredits);
		}
		
		HashSet<Media> masterCreditsList = new HashSet<Media>();
		Set<MediaMaker> makerList = monsterHash.keySet();
		MediaMaker[] makerArray = new MediaMaker[makerList.size()];
		makerList.toArray(makerArray);
		
		for(MediaMaker m: makerArray){
			masterCreditsList.addAll(monsterHash.get(m));
		}
	
		return masterCreditsList;
	}
}