package MediaDatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Project #4
 * CS 2334, Section 10
 * Mar 28, 2016
 * <P>
 * This MDbModel class extends the MDb as a model for the MVC of MediaDatabase
 * </P>
 * 
 * @version 1.0 
 */
public class MDbModel extends MDb{
	
	/**
	 * Generated serialize id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ArrayList of ActionListener
	 */
	private ArrayList<ActionListener> actionListenerList;

	public MDbModel() {
		super();
	}
	
	/**
	 * Getter function for MDb on media array
	 * 
	 * @return ArrayList<Media>	MDb to return
	 */
	public ArrayList<Media> getMedia(){
		return super.getMedia();
	}

	/**
	 * ArrayList add function for MDb 
	 * @param m	Media to add
	 */
	public void add(Media m){
		super.add(m);
		processEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED, "add media"));		
	}

	/**
	 * ArrayList add function for MDb
	 * 
	 * @param arr  ArrayList of media to add
	 */
	public void add(ArrayList<Media> arr){
		super.add(arr);
	}

	/**
	 * Getter for favorites.
	 * @return the favorites
	 */
	public ArrayList<Media> getFavorites() {
		return super.getFavorites();
	}

	/**
	 * Setter for favorites.
	 * @param favorites the favorites to set
	 */
	public void setFavorites(ArrayList<Media> favorites) {
		super.setFavorites(favorites);
	}

	/**
	 * This method will add media to favorites.
	 * @param mediaItem
	 */
	public void addMedia2Favorites(Media m){
		super.addMedia2Favorites(m);
	}

	/**
	 * ArrayList sort function to use Collections sort for MDb based on media Comparator sent
	 * 
	 * @param c	comparator to send to collection sort
	 */
	public void sort(Comparator<Media> c){
		super.sort(c);
	}

	/**
	 * ArrayList size function for MDb Class
	 * 
	 * @return int	return size of media array list
	 */
	public int size(){
		return super.size();
	}

	/**
	 * ArrayList isEmpty function for MDb 
	 * 
	 * @return boolean  returns true if database empty
	 */
	public boolean isEmpty() {
		return super.isEmpty();
	}

	/**
	 * Method to read Movie fileName into Movie then add to Media array
	 * 
	 * @param fileName  String for file name of movie file
	 */
	public void readMovieFileIn(String fileName) throws IOException{
	
			super.readMovieFileIn(fileName);
		
		}		
	

	/**
	 * Method to read Series fileName into Series/Episode than add to Media Array
	 *
	 * @param fileName  string containing file name of series file
	 * 
	 */
	public void readSeriesFileIn(String fileName) throws IOException{
		super.readSeriesFileIn(fileName);
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
		return super.searchTextInMedia(searchString, episodesIn, exact);
	}

	/**
	 * Method to search year(s) in Media array
	 * 
	 * @param yearSearch
	 * @param episodesIn
	 * @return ArrayList<Media>
	 */
	public ArrayList<Media> searchYearInMedia(String yearString, boolean episodesIn){
		return super.searchYearInMedia(yearString, episodesIn);
	}

	/**
	 * To String Method to convert to a readable string.
	 */
	public String toString(){
		return super.toString();
	}	// TODO Auto-generated constructor stub
	
	/**
	 * 
	 * @param media
	 * @param movies
	 * @param series
	 * @return
	 */
	public ArrayList<Media> getAllMedia(Media media, MDb movies, MDb series){
		return super.getAllMedia(media, movies, series);
	}
		
	private void processEvent(ActionEvent actionEvent) {
	// TODO Auto-generated method stub
	}
}