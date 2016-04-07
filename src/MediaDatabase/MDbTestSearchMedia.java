package MediaDatabase;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Project #4
 * CS 2334, Section 10
 * Mar 28, 2016
 * <P>
 * JUnit Test on MDb media search method
 * </P>
 * @author 
 * @version 1.0 
 */

public class MDbTestSearchMedia {

	static MDb testMDb = new MDb();

	@BeforeClass
	public static void fillMDb() throws IOException {
		// Hard coded for testing
		testMDb.readMovieFileIn("movies.txt");
		testMDb.readSeriesFileIn("series.txt");
	}
	
	@Test
	public void testSearchTextInMedia() {
		// Searches MDb for exact title Star Trek, not including episodes
		ArrayList<Media> titleSearch1 = testMDb.searchTextInMedia("Star Trek", false, true);
		assertEquals("Star Trek",titleSearch1.get(0).getTitle());
		assertEquals((Integer) 2009,titleSearch1.get(0).getReleaseYear());
	}
	
	@Test
	public void testSearchYearInMedia() {
		// Initially searches through MDb for everything from 2009
		MDb results1 = new MDb();
		results1.add(testMDb.searchYearInMedia("2009", false));
		// Searches results for one specific media that should be present
		// Depended on first test passing
		ArrayList<Media> yearSearch1 = results1.searchTextInMedia("Star Trek", false, true);

		assertEquals("Star Trek",yearSearch1.get(0).getTitle());
		assertEquals((Integer) 2009,yearSearch1.get(0).getReleaseYear());
	}
}
