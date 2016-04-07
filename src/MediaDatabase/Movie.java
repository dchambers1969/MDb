package MediaDatabase;

/**
 * Project #2
 * CS 2334, Section 10
 * Feb 29, 2016
 * <P>
 * The Movie is an extension of the media class 
 *  creates an object with key information about one moviee
 * </P>
 * @author 
 * @version 1.0
 */
public class Movie extends Media  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** private class variable for Movie*/
	private String status;
	private Roman edition;//tracks edition of same name movies
	
	/** Constructor Overload for Movie */
	
	/**
	 * Empty constructor for Movie
	 *
	 */
	public Movie(){
		super();
		this.edition = null;
		this.status = null;
		
	}
	
	/**
	 * Movie constructor
	 * 
	 * @param mediaLine
	 * @param title
	 * @param releaseYear
	 * @param edition
	 */
	public Movie(String mediaLine, String title, Integer releaseYear, Roman edition){
		
		super(mediaLine, title, releaseYear);
		this.edition = edition;
		this.status = null;
	}
	/**
	 * Movie constructor
	 * 
	 * @param mediaLine
	 * @param title
	 * @param releaseYear
	 * @param releaseType
	 */
	public Movie (String mediaLine, String title, Integer releaseYear, 
			                                           ReleaseType releaseType){
		super(mediaLine, title, releaseYear, releaseType);
		this.edition = null;
		this.status = null;
	}
	
	/**
	 * Movie constructor
	 * 
	 * @param mediaLine
	 * @param title
	 * @param releaseYear
	 * @param releaseType
	 * @param edition
	 */
	public Movie(String mediaLine, String title, Integer releaseYear, 
			                          ReleaseType releaseType, Roman edition){
	
		super(mediaLine, title, releaseYear, releaseType);
		this.edition = edition;
		this.status = null;
	}
	
	
	/**
	 * Getter for Edition
	 * 
	 * @return Roman
	 */
	public Roman getEdition() {
		
		return edition;
	}
	
	/**
	 * Setter for Edition
	 * 
	 * @param edition
	 */
	public void setEdition(Roman edition) {
		this.edition = edition;
	}
	
	/**
	 * Boolean flag for whether a Movie has an edition
	 * 
	 * @return boolean
	 */
	public boolean hasEdition(){
		
		return (this.edition.getNumber()!=0);
	}
	
		
	/**
	 * Override for Movie to String
	 * @return String
	 */
	
	 @Override
	 public String toString(){
		
		 return super.getMediaLine();
	
}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
