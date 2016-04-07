package MediaDatabase;

/**
 * Project #3 CS 2334, Section 10 Mar 28, 2016
 * <P>
 * This director class will contain the name for the Director and will act ask a
 * key in the Credits hashmap to find corresponding media credits.
 * </P>
 * 
 * @version 1.0
 */

public class Director extends MediaMaker {

	/**
	 * Generated Serialize id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Empty constructor for Director class
	 */
	public Director() {
		super();
	}

	/**
	 * Constructor for Director
	 * 
	 * @param directorName name of Director 
	 */
	public Director(String directorName) {
		super(directorName);
	}
}