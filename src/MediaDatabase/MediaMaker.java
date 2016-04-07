
package MediaDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 
 * This MediaMaker class is an abstract class that acts as the parent for all types of mediaMakers.
 * Their corresponding media credits are accessed using MediaMaker subclasses as the key in a hashmap.
 * The abstract class will contain all of the class features for each of its subclasses 
 * (Actor, Director, Producer).  
 * This class can be expanded (additional variables) for additional information about each media maker.
 * @author
 */
public abstract class MediaMaker implements Serializable{

	/**
	 * Generated serialized ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * MediaMaker Name String.
	 */
	private String makerName;
	
	/** MediaMaker Family Name. */
	private String makerFamilyName;
	
	/** MediaMaker First Name. */
	private String makerFirstName;
	
	/** MediaMaker Suffix Info */
	private String makerSuffixInfo;

	
	/**
	 * Default constructor for MediaMaker.
	 */
	public MediaMaker(){
		this.makerName = null;
		this.makerFamilyName = null;
		this.makerFirstName = null;
		this.makerSuffixInfo = null;
	}
	
	/**
	 * Constructor for MediaMaker.
	 * 
	 * @param makerName
	 */
	
	public MediaMaker(String makerName){
		this.makerName = makerName;
		this.makerFamilyName = null;
		this.makerFirstName = null;
		this.makerSuffixInfo = null;
	}
			
	/**
	 * Getter for MakerName.
	 * @return String  media maker Name
	 */
	public String getMakerName() {
		return makerName;
	}

	/**
	 * Setter for Media Maker Name.
	 * @param makerName  the media maker name to set
	 */
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}

	/**
	 * Getter for Maker Family Name.
	 * @return String the makerFamilyName
	 */
	public String getMakerFamilyName() {
		return makerFamilyName;
	}

	/**
	 * Setter for Maker Family Name.
	 * @param makerFamilyName the makerFamilyName to set
	 */
	public void setMakerFamilyName(String makerFamilyName) {
		this.makerFamilyName = makerFamilyName;
	}

	/**
	 * getter for Maker First Name.
	 * @return String the makerFirstName
	 */
	public String getMakerFirstName() {
		return makerFirstName;
	}

	/**
	 * Setter for Maker First Name.
	 * @param makerFirstName the makerFirstName to set
	 */
	public void setMakerFirstName(String makerFirstName) {
		this.makerFirstName = makerFirstName;
	}

	/**
	 * Getter for Maker Suffix Info.
	 * @return String the makerSuffixInfo
	 */
	public String getMakerSuffixInfo() {
		return makerSuffixInfo;
	}

	/**
	 * Setter for Maker Suffix Info.
	 * @param makerSuffixInfo the makerSuffixInfo to set
	 */
	public void setMakerSuffixInfo(String makerSuffixInfo) {
		this.makerSuffixInfo = makerSuffixInfo;
	}
}