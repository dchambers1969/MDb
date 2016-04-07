package MediaDatabase;
/**
 * Project #3
 * CS 2334, Section 10
 * Mar 28, 2016
 * <P>
 * The Episode is an extension of the media class 
 *  creates an episode object with key information about one TV Series episode
 * </P>
 * 
 * @version 1.0
 */
public class Episode extends Media {
	
	/**
	 * Serialize ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**  Episode unique variables in addition Media variables */
	private String uniqueSeriesTitle; //Info on Title and Year to search Episode for common series info
	private String broadcastInfo; // Info of the broadcast (#1.08)
	private String status; //status of series (UNSPECIFIED, SUSPENDED)
	private Integer seriesYear; // Integer representation of the 1 for Year (#1.08)
	private String episodeDetails;
	private Integer episodeNumber; //Integer representation of the 08 for Episode # (#1.08)
	private Date episodeDate;//Object for date episode aired
	
	/** Episode Constructor Overload - 5 constructors for various parameter inputs
	
	/**
	 * default Episode constructor that creates empty Episode
	 */
	public Episode(){
		super();
		this.uniqueSeriesTitle = null;
		this.broadcastInfo = null;
		this.setSeriesYear(null);
		this.setEpisodeNumber(null);
		this.setEpisodeDate(null);
		this.setEpisodeDetails(null);
	}
	
	/**
	 * Episode Constructor
	 * 
	 * @param mediaLine	Default String for Episode
	 * @param uniqueSeriesTitle	Episode String that matches Series String
	 */
	public Episode(String mediaLine, String uniqueSeriesTitle){
		super(mediaLine);
		this.uniqueSeriesTitle = uniqueSeriesTitle;
		this.setSeriesYear(null);
		this.setEpisodeNumber(null);
		this.setEpisodeDate(null);
		this.setEpisodeDetails(null);
	}
		
	/**
	 * Episode Constructor
	 * 
	 * @param mediaLine	Default String for Episode
	 * @param uniqueSeriesTitle	Episode String that matches Series String
	 * @param title	Episode title string
	 * @param releaseYear	release year for the Episode
	 * @param releaseType	Format the Episode was released in - TVSeries
	 * @param status	String to show status of episode if it was suspended or unspecified
	 * @param broadcastInfo	String to store broadcast year#.episode#
	 */
	public Episode(String mediaLine, String uniqueSeriesTitle, String title, 
			Integer releaseYear, ReleaseType releaseType, String status, String broadcastInfo){
		
		super(mediaLine, title, releaseYear, releaseType);
		this.uniqueSeriesTitle = uniqueSeriesTitle;
		this.status = status;
		this.broadcastInfo = broadcastInfo;
		this.setEpisodeNumber(translateEpisodeNumber(broadcastInfo));
		this.setSeriesYear(translateSeriesYear(broadcastInfo));
		this.setEpisodeDate(null);
		this.setEpisodeDetails(null);
	}
	
	/**
	 * Episode Constructor
	 * 
	 * @param mediaLine	Default String for Episode
	 * @param uniqueSeriesTitle	Episode String that matches Series String
	 * @param title	Episode title string
	 * @param releaseYear	release year for the Episode
	 * @param releaseType	Format the Episode was released in - TVSeries
	 */
	public Episode (String mediaLine, String uniqueSeriesTitle,
					String title, Integer releaseYear, ReleaseType releaseType){
		
		super(mediaLine, title, releaseYear, releaseType);
		this.uniqueSeriesTitle = uniqueSeriesTitle;
		this.broadcastInfo = null;
		this.setSeriesYear(null);
		this.setEpisodeNumber(null);
		this.setEpisodeDate(null);
		this.setEpisodeDetails(null);
	}
		
	/**
	 * Getter for unique Series Title for this episode
	 * 
	 * @return String	returns uniqueTitleString that matches its Series uniqueTitleString
	 */
	public String getUniqueSeriesTitle(){
		return uniqueSeriesTitle;
	}
	
	/**
	 * Setter for unique Series Title for this episode
	 * 
	 * @param uniqueSeriesTitle uniqueTitleString that matches its Series uniqueTitleString
	 */
	public void setUniqueSeriesTitle(String uniqueSeriesTitle){
		this.uniqueSeriesTitle = uniqueSeriesTitle;
	}
	
	/**
	 * Getter for Episode Status
	 * 
	 * @return String	returns status of Episode
	 */
	public String getStatus(){
		return status;
	}
	
	/**
	 * Setter for Episode Status
	 * 
	 * @param status	status of Episode
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	/**
	 * Getter for broadcastInfo
	 * 
	 * @return String	returns broadcast info for Episode (year#.episode#)
	 */
	public String getBroadcastInfo() {
		return broadcastInfo;
	}

	/**
	 * Setter for broadcastInfo with translation of string to Year & Episode # of Series
	 * 
	 * @param broadcastInfo	broadcast info for Episode (year#.episode#)
	 */
	public void setBroadcastInfo(String broadcastInfo) {
		this.broadcastInfo = broadcastInfo;
		if(broadcastInfo!=null){
			this.setEpisodeNumber(translateEpisodeNumber(broadcastInfo));
			this.setSeriesYear(translateSeriesYear(broadcastInfo));
		}
	}
	
	/**
	 * Translate method to take broadcastInfo and set Episode # 
	 * 
	 * @param broadcastInfo	broadcast info for Episode (year#.episode#)
	 * @return Integer returns integer for the # of Episode in a particular season
	 */
	public Integer translateEpisodeNumber(String broadcastInfo){
		
		try{ //Try and catch to ensure broadcast info to translate is formatted correctly otherwise return null
			return Integer.valueOf(broadcastInfo.substring(broadcastInfo.indexOf('.')+1,broadcastInfo.length()));
		}
		catch(NullPointerException e){
			System.out.println("There is no period!");return null;
		}
		catch(NumberFormatException e){
			System.out.println("That isn't a number!");return null;
		}
	}
	
	/**
	 * Translate method to take broadcastInfo and set Series Year 
	 * 
	 * @param broadcastInfo	broadcast info for Episode (year#.episode#)
	 * @return Integer	integer for the season number for an episode
	 */
	public Integer translateSeriesYear(String broadcastInfo){
		
		try{//Try and catch to ensure broadcast info to translate is formatted correctly otherwise return null
			return Integer.valueOf(broadcastInfo.trim().substring(0,broadcastInfo.indexOf('.')));
		}
		catch(NullPointerException e){
			System.out.println("There is no period!");return null;
		}
	}
	
	/**
	 * Getter for Series season number
	 * 
	 * @return Integer	returns integer for season number of episodes
	 */
	public Integer getSeriesYear() {
		return seriesYear;
	}
	
	/**
	 * Setter for Series season number
	 * 
	 * @param seriesYear	integer for season number of episodes
	 */
	public void setSeriesYear(Integer seriesYear) {
		this.seriesYear = seriesYear;
	}

	/**
	 * Getter for Episode #
	 * 
	 * @return Integer	returns episode number for a particular season
	 */
	public Integer getEpisodeNumber() {
		return episodeNumber;
	}

	/**
	 * Setter for Episode #
	 * 
	 * @param episodeNumber	episode number for a particular season
	 */
	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	/**
	 * To String override for Episode
	 * 
	 * @return String	returns string to print for Episode
	 */
	@Override
	 public String toString(){
		
		 return super.getMediaLine();
	}
	
	/**
	 * Boolean flag on if Episode has a status
	 * 
	 * @return boolean	returns true if episode has a status
	 */
	public boolean hasStatus(){
		return this.status!=null;
	}

	/**
	 * getter for the episode Date
	 * 
	 * @return Date the episodeDate
	 */
	public Date getEpisodeDate() {
		return episodeDate;
	}

	/**
	 * setter for the episode date
	 * 
	 * @param episodeDate the episodeDate to set
	 */
	public void setEpisodeDate(Date episodeDate) {
		this.episodeDate = episodeDate;
	}

	/**
	 * getter for episode details
	 * 
	 * @return the EpisodeDetails
	 */
	public String getEpisodeDetails() {
		return episodeDetails;
	}

	/**
	 * setter for episode details
	 * 
	 * @param EpisodeDetails the EpisodeDetails to set
	 */
	public void setEpisodeDetails(String episodeDetails) {
		this.episodeDetails = episodeDetails;
	}

}