package MediaDatabase;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import org.junit.Test;

/**
 * Project #4
 * CS 2334, Section 10
 * April 18, 2016
 * <P>
 * JUnit Test on Maker Credits search method
 * </P>
 * @author 
 * @version 1.0 
 */

public class MakerCreditsTest {

	@Test 
	public void testSearchMakerCredits() {
		// Constructing actors
		MediaMaker testMaker1 = new Actor("Angelina Jolie");
		MediaMaker testMaker2 = new Actor("Angelina Abdullaeva");
		// Constructing credits ArrayLists
		ArrayList<Media> testCredits1 = new ArrayList<Media>();
		testCredits1.add(new Media("A Fishified World (2005) (V)  [Herself]"));
		testCredits1.add(new Media("A Mighty Heart (2007)  [Mariane Pearl]  <2>"));
		testCredits1.add(new Media("A Place in Time (2007)  [Herself]"));
		ArrayList<Media> testCredits2 = new ArrayList<Media>();
		testCredits2.add(new Media("This Is Andromeda (2015)  [Andromeda]  <3>"));
		// Constructing LinkedHashMaps to set to MakerCredits for searching
		LinkedHashMap<MediaMaker,ArrayList<Media>> makerCreditsHash1 = new LinkedHashMap<MediaMaker,ArrayList<Media>>();
		LinkedHashMap<MediaMaker,ArrayList<Media>> makerCreditsHash2 = new LinkedHashMap<MediaMaker,ArrayList<Media>>();
		LinkedHashMap<MediaMaker,ArrayList<Media>> makerCreditsHash12 = new LinkedHashMap<MediaMaker,ArrayList<Media>>();
		makerCreditsHash12.put(testMaker2, testCredits2);		
		makerCreditsHash12.put(testMaker1, testCredits1);
		makerCreditsHash1.put(testMaker1, testCredits1);
		makerCreditsHash2.put(testMaker2, testCredits2);
		// Constructing and populating MakerCredits tests
		MakerCredits test1 = new MakerCredits();
		MakerCredits test2 = new MakerCredits();
		MakerCredits test12 = new MakerCredits();
		test1.setMakerCredits(makerCreditsHash1);
		test2.setMakerCredits(makerCreditsHash2);
		test12.setMakerCredits(makerCreditsHash12);

		assertEquals("Angelina Jolie",test1.searchMakerCredits("Angelina Jolie", true).getName());
		assertEquals(true,test2.searchMakerCredits("Angelina Jolie", true).isEmpty());
		assertEquals(2,test12.searchMakerCredits("angelina", false).size());
		assertEquals("Angelina Jolie",test12.searchMakerCredits("jolie", false).getName());
		
		Set<MediaMaker> makers = test12.getAll().keySet();
		MediaMaker maker = 	(MediaMaker)makers.toArray()[0];
		assertEquals(testCredits2,test12.searchMakerCredits("Angelina Abdullaeva", true).getAll().get(maker));
	}

}