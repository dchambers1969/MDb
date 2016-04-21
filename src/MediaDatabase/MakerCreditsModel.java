package MediaDatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class MakerCreditsModel extends MakerCredits {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6426634447386362588L;
	
	/**
	 * Default constructor for MakerCredits
	 */
	public MakerCreditsModel() {
		super();
	}
	
	// quick hack to test if populating is working, returns private field makerCredits
	public LinkedHashMap<MediaMaker, ArrayList<Media>> getAll() {
		return super.getAll();
	}
	
	/**
	 * Setter method for makerCredits
	 * 
	 * @param makerCredits	the credits for the media makers
	 */
	public void setMakerCredits(LinkedHashMap<MediaMaker, ArrayList<Media>> makerCredits) {
		super.setMakerCredits(makerCredits);
	}

	/**
	 * Add media credits to makerCredits
	 * 
	 * @param mediaMaker	mediaMaker object used as key for linkedhashmap
	 * @param media	ArrayList of Media objects the mediaMaker has credits in
	 */
	public void addCredits(MediaMaker mediaMaker, ArrayList<Media> media){
		super.addCredits(mediaMaker, media);
	}

	/**
	 * Sets credit for Maker
	 * 
	 * @param mediaMaker MediaMaker - maker to add credit to
	 * @param m   Media - credit to add
	 */
	public void setCreditForMaker(MediaMaker mediaMaker, Media m){
		super.setCreditForMaker(mediaMaker, m);
	}

	/**
	 * Getter for Credits for Maker. 
	 * 
	 * @param mediaMaker media maker that media is needed
	 * @return ArrayList<Media> media for maker to return
	 */
	public ArrayList<Media> getCreditsForMaker(MediaMaker mediaMaker){
		return super.getCreditsForMaker(mediaMaker);
	}
	
	/**
	 * Is empty boolean.
	 * 	
	 * @return boolean true if object is empty
	 */
	public boolean isEmpty(){
		return super.isEmpty();
	}
	
	/**
	 * This Reads in the Media Maker File.
	 * 
	 * @param fileName
	 * @throws IOException 
	 */
	public void readMakerFileIn(char flag, String fileName, MDb movies, MDb series) throws IOException{
		super.readMakerFileIn(flag, fileName, movies, series);
	}

	/**
	 * Iterates through LinkedHashMap to search for media makers and to record their media
	 * 
	 * @param searchName	Name to search for
	 * @param exactResult	Boolean flag for exact or partial results
	 * @return MakerCredits  makercredits hashmap
	 */
	public MakerCredits searchMakerCredits(String searchName, boolean exactResult) {
		return super.searchMakerCredits(searchName, exactResult);
	}

	/**
	 * Method to determine the size of a maker credits object by measuring the key set
	 * 
	 * @return int size of keySet for hashmap
	 */
	public int size() {
		return super.size();
	}

	/**
	 * Method to allow for determining the name of a maker when there is only one
	 * @return String that is first makerName
	 */
	public String getName() {
		return super.getName();
	}
	
	public HashSet<Media> getAllCredits(MediaMaker maker, LinkedHashMap<Actor,ArrayList<Media>> actorCredits,
			LinkedHashMap<Director,ArrayList<Media>> directorCredits, 
			LinkedHashMap<Producer,ArrayList<Media>> producerCredits){
		return super.getAllCredits(maker, actorCredits, directorCredits, producerCredits);
	}
}