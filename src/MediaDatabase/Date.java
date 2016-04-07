package MediaDatabase;

public class Date {
	
	/**
	 *        
	 *	This class sets up the Airing Date for Episode media 
	 *        
	 */      
	private Integer year;
	private Integer month;
	private Integer day;
	
	/**
	 * Default constructor for date
	 */
	public Date(){
		this.year = null;
		this.month = null;
		this.day = null;
	}
	
	/**
	 * Date constructor that also parses the date string param
	 * @param dateString text string that contains YYYY-MM-DD type field from Episode Details
	 */
	public Date(String dateString) {
		
		// Initialize Variables
		String tempYear;
		String tempMonth;
		String tempDay;
		
		// Trimming white space. 
		dateString = dateString.trim();

		// Read 4 digit number up to the first dash character for year.
		tempYear = dateString.substring(0, dateString.indexOf('-')).trim();
		setYear(tempYear);
		
		// Read from previous dash to next / last dash which should be the month value.
		tempMonth = dateString.substring(dateString.indexOf('-') + 1, dateString.lastIndexOf('-')).trim();
		setMonth(tempMonth);
		
		// Read from last dash to end which should be the day value.
		tempDay = dateString.substring(dateString.lastIndexOf('-') + 1 ,dateString.length());
		setDay(tempDay);
	}

	/**
	 * This will get and return a Year.
	 * 
	 * @return Integer the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * This will allow for setting of the year.
	 * 
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * This will allow for the setting of the Year.
	 * 
	 * @param year the year to set in String
	 */
	public void setYear(String year) {
		this.year = Integer.valueOf(year);
	}
	
	/**
	 * This will get and return Month.
	 * 
	 * @return Integer the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * This will allow for the setting of the MOnth.
	 * 
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * This allows for the setting of the Month.
	 * 
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = Integer.valueOf(month);
	}
	
	/**
	 * This will get the Day as an Integer. 
	 * 
	 * @return Integer the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * This will allow the setting of the Day.
	 * 
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	
	/**
	 * This will allow the setting of the Day.
	 * 
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = Integer.valueOf(day);
	}
}