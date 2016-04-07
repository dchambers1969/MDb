package MediaDatabase;

import java.io.Serializable;

/**
 * Project #2
 * CS 2334, Section 10
 * Feb 29, 2016
 * <P>
 * The ReleaseType depicts which venue release the single media item is
 * </P>
 * @author Darin Chambers
 * @version 1.0
 */

public enum ReleaseType implements Serializable {
	
	VIDEO, TVMOVIE, SCREEN, EPISODE, TV;
	
}
