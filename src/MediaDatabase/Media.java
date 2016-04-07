package MediaDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

/**
 * Project #3 CS 2334, Section 10 March 28, 2016
 * <P>
 * The Media class creates an object with key information about a media type
 * </P>
 * 
 * @version 1.0
 */

public class Media implements Comparable<Media>, Serializable  {

	/** Media class variables */

	/**
	 * Generated Serialize id
	 */
	private static final long serialVersionUID = 1L;

	/** Comparator variables used for comparator of Media objects */
	public static final Comparator<Media> TITLE_FIRST_COMPARATOR = new TitleFirstComparator();
	public static final Comparator<Media> YEAR_FIRST_COMPARATOR = new YearFirstComparator();
	public static final Comparator<Media> FORMAT_COMPARATOR = new FormatComparator();
	
	/** retain original string of media from file to use during output */
	private String mediaLine;

	/** Title stores the title of the media object */
	private String title; // Inherited Title for Episode is the actual Episode
							// title not Series title
	
	/** releaseYear stores the year the media item was released */
	private Integer releaseYear;// Inherited releaseYear for Episode is actual
								// episode run not Series start
	/** String used to print out search results when this media found*/
	private String searchResultOutput;
	
	/**
	 * releaseType stores the release type of the media saved in enum
	 * ReleaseType - SCREEN, VIDEO, TVMOVIE, EPISODE, TV
	 */
	private ReleaseType releaseType;
	
	/** 
	 * Linked hash map to establish cast and details about cast (actor/role) for media
	 * index 	0 has role
	 * 		 	1 has cast order (-1 for Producer, 0 for Director)
	 * 			2 - 4 additional details (voice, archive footage, uncredited, etc)
	 */
	private LinkedHashMap<Actor,String[]> cast;
	
	/**    */
	private LinkedHashMap<Director, String[]> directors;
	
	/**    */
	private LinkedHashMap<Producer,String[]> producers;
	
	/** Media Constructor Overload */
	
	/**
	 * Empty constructor for Media
	 */
	public Media() {
		this.mediaLine = null;
		this.title = null;
		this.releaseYear = null;
		this.releaseType = null;
		this.searchResultOutput = null;
		this.cast = null;
		this.directors = null;
		this.producers = null;
	}

	/**
	 * Media constructor
	 * 
	 * @param mediaLine
	 */
	public Media(String mediaLine) {
		this.mediaLine = mediaLine;
		this.title = null;
		this.releaseYear = null;
		this.releaseType = null;
		this.searchResultOutput = null;
		this.cast = null;
		this.directors = null;
		this.producers = null;
	}

	/**
	 * Media constructor
	 * 
	 * @param mediaLine
	 * @param title
	 */
	public Media(String mediaLine, String title) {
		this.mediaLine = mediaLine;
		this.title = title;
		this.releaseYear = null;
		this.releaseType = null;
		this.searchResultOutput = null;
		this.cast = null;
		this.directors = null;
		this.producers = null;
	}

	/**
	 * Media Constructor
	 * 
	 * @param mediaLine
	 * @param title
	 * @param releaseYear
	 */
	public Media(String mediaLine, String title, Integer releaseYear) {
		this.mediaLine = mediaLine;
		this.title = title;
		this.releaseYear = releaseYear;
		this.releaseType = null;
		this.searchResultOutput = null;
		this.cast = null;
		this.directors = null;
		this.producers = null;
	}

	/**
	 * Media constructor
	 * 
	 * @param mediaLine
	 * @param title
	 * @param releaseYear
	 * @param releaseType
	 */
	public Media(String mediaLine, String title, Integer releaseYear, ReleaseType releaseType) {
		this.mediaLine = mediaLine;
		this.title = title;
		this.releaseYear = releaseYear;
		this.releaseType = releaseType;
		this.searchResultOutput = null;
		this.cast = null;
		this.directors = null;
		this.producers = null;
	}

	/**
	 * Getter for MediaLine
	 * 
	 * @return String returns string of original file line for media 
	 */
	public String getMediaLine() {

		return this.mediaLine;
	}

	/**
	 * Setter for MediaLine
	 * 
	 * @param mediaLine
	 */
	public void setMediaLine(String mediaLine) {
		this.mediaLine = mediaLine;
	}

	/**
	 * Getter for Media Title
	 * 
	 * @return String  string of media title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for Title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
		
	/**
	 * Set for searchResultOutput
	 * 
	 * @param searchResultOutput
	 */
	public void setSearchResultOutput(String searchResultOutput){
		this.searchResultOutput = searchResultOutput;
	}
	
	/**
	 * getter for Search Result Output
	 * 
	 * @return String  string of search result to print
	 */
	public String getSearchResultOutput(){
		return this.searchResultOutput;
	}
		
	/**
	 * Getter for media releaseYear
	 * 
	 * @return Integer value of releaseYear
	 */
	public Integer getReleaseYear() {
		return releaseYear;
	}

	/**
	 * Setter for ReleaseYear
	 * 
	 * @param releaseYear
	 */
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	/**
	 * Getter for ReleaseType
	 * 
	 * @return ReleaseType  returns releaseType of media Object
	 */
	public ReleaseType getReleaseType() {
		return releaseType;
	}

	/**
	 * Setter for ReleaseType
	 * 
	 * @param releaseType  
	 */
	public void setReleaseType(ReleaseType releaseType) {
		this.releaseType = releaseType;
	}

	/**
	 * Boolean Flag to see if Media is Video
	 * 
	 * @return boolean  returns true if media object is TVMOVIE
	 */
	public boolean isVideo() {
		return this.releaseType.equals(ReleaseType.TVMOVIE);
	}

	/**
	 * Boolean Flag to see if Media is Screen
	 * 
	 * @return boolean  returns true if media object is Screen
	 */
	public boolean isScreen() {
		return this.releaseType.equals(ReleaseType.SCREEN);
	}

	/**
	 * Boolean Flag to see if Media is TVMovie
	 * 
	 * @return boolean  returns true if media object is TVMovie
	 */
	public boolean isTVMovie() {
		return this.releaseType.equals(ReleaseType.TVMOVIE);
	}

	/**
	 * Boolean Flag to see if Media is TVSeries
	 * 
	 * @return boolean  returns true if media object is TVSeries
	 */
	public boolean isTVSeries() {
		return this.releaseType.equals(ReleaseType.TV);
	}

	/**
	 * Boolean Flag to see if Media is Episode
	 * 
	 * @return boolean  returns true if media object is episode
	 */
	public boolean isEpisode() {
		return this.releaseType.equals(ReleaseType.EPISODE);
	}

	/**
	 * Method to add cast member to media object
	 * 
	 * @param actor	Actor object that was in this media cast
	 * @param part	string for part actor played in media
	 */
	public void addCastMember(Actor actor, String part){
		//TODO add cast member body
	}
	
	/**
	 * Method to add media maker to credits to this media
	 * 
	 * @param maker	MediaMaker object that had a key 
	 * @param role	String for role the mediaMaker assumed in this media
	 */
	public void addCredits(MediaMaker maker, String role){
		//TODO create add credits method body
	}
	
	/**
	 * Getter method for getting the cast for a particular media
	 * 
	 * @return LinkedHashMap<Actor, String> a hash map for the media's cast
	 */
	public LinkedHashMap<Actor, String[]> getCast() {
		//TODO build get credits array
		return null;
	}

	/**
	 * setter method for media cast
	 * 
	 * @return LinkedHashMap<MediaMaker, String[]>  the credits for the media
	 */
	public void setCast(LinkedHashMap<Actor, String[]> cast){
		this.cast = cast;
	}
	
	/**
	 * Getter for media credits
	 * 
	 * @return LinkedHashMap<MediaMaker, String> hash map of mediamaker and their credited role
	 */
	public LinkedHashMap<MediaMaker, String[]> getCredits(){
		//TODO create get for Credits Hash
		return null;
	}
	
		
	/**
	 * Comparator class for Media to allow sort by Title then Year of search
	 * results
	 */
	private static class TitleFirstComparator implements Comparator<Media>, Serializable{
		
		/**
		 * Serialized ID for comparator
		 */
		private static final long serialVersionUID = 1617968996836647098L;

		/**
		 * Compare method for two media items
		 * To reverse the sorted order use: return title2.compareTo(title1).
		 * 
		 * @return int value of title compare
		 */
		public int compare(Media m1, Media m2) {
			String title1 = m1.getTitle();
			String title2 = m2.getTitle();
			return title1.compareTo(title2);
		}
	};

	/**
	 * Comparator class for Media to allow sort by Year or by the Title  or by Release Type of search
	 * results.
	 */
	private static class YearFirstComparator implements Comparator<Media>, Serializable {

		/**
		 * Serialized Id for comparator
		 */
		private static final long serialVersionUID = 2349032600859464201L;

		/**
		 * Compare method for two media items *
		 * To reverse sorted order use return year2 - year1;
		 * 
		 * @return int  value of year compare 
		 */
		public int compare(Media m1, Media m2) {
			
			//If else statements to handle null pointer for when year is unspecified for media object
			if(m1.getReleaseYear()==null || m2.getReleaseYear()==null){
				if(m1.getReleaseYear()==null && m2.getReleaseYear()==null)
					return 0;
				else if(m2.getReleaseYear()==null && m1.getReleaseYear()!=null)
					return -1;
				else if(m2.getReleaseYear()!=null && m1.getReleaseYear()==null)
					return 1;
			}
			else { // if both years are good then return difference of years for compare
				int year1 = m1.getReleaseYear();
				int year2 = m2.getReleaseYear();
				return year1 - year2;
			}
			return 0;
		}
	};

	private static class FormatComparator implements Comparator<Media>, Serializable{
		
		/**
		 * Serialized ID for comparator
		 */
		private static final long serialVersionUID = 1640371209832389304L;

		/*
		 * Compare method for two media items return int.
		 * To reverse the sorted order, use return format2.compareTo(format1);
		 */
		public int compare(Media m1, Media m2) {
			ReleaseType format1 = m1.getReleaseType();
			ReleaseType format2 = m2.getReleaseType();
			return format1.compareTo(format2);
		}
	};

	/**
	 * Override of toString for Media
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return this.mediaLine;
	}

	@Override
	public int compareTo(Media arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the director
	 */
	public LinkedHashMap<Director, String[]> getDirectors() {
		return directors;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirectors(Director director, String[] details) {
		directors.put(director, details);
	}

	/**
	 * @return the producer
	 */
	public LinkedHashMap<Producer, String[]> getProducers() {
		return producers;
	}

	/**
	 * @param producer the producer to set
	 */
	public void setProducers(Producer producer, String[] details) {
		producers.put(producer, details);
	}
}