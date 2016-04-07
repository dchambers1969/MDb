package MediaDatabase;

/**
 * This producer class will contain the name for the producer and will act ask a
 * key in the Credits hashmap to find corresponding media credits
 * 
 */
public class Producer extends MediaMaker {

	/**
	 * Generated Serialize id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for Producer class.
	 */
	public Producer() {
		super();
	}

	/**
	 * Constructor for Producer.
	 * 
	 * @param producerName
	 */
	public Producer(String producerName) {
		super(producerName);
	}
}