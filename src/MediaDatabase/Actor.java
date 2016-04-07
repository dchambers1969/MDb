package MediaDatabase;

/**
 * Project #4 CS 2334, Section 10 April 18, 2016
 * <P>
 * This class will contain the name for the Actor and will act as a
 * key in the Credits hashmap to find corresponding media credits.
 * </P>
 *
 * @version 1.0
 */

public class Actor extends MediaMaker {

	/**
	 * Generated Serialized id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for Actor class.
	 */
	public Actor() {
		super();
		//System.out.println("Actor call");
	}

	/**
	 * Constructor for Actor class
	 * 
	 * @param actorName name of actor
	 */
	public Actor(String actorName) {
		super(actorName);
	}
}
