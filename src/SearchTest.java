import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Testing for the Search class
 * @author Azam Jawad Butt
 */
public class SearchTest {

    /**
     * Tests the overall functionality of the searchFilter() method
     * @param None
     * @return void
     */
    @Test
    public void testSearchFilter() {
        // Tests functionality of SearchFilter, creates arraylist of POI data and initializes search instance
        ArrayList<String[]> POIs = new ArrayList<>();
        POIs.add(new String[] { "1", "100", "100", "0", "0", "0", "Library", "A place to read books", "L1" });
        POIs.add(new String[] { "1", "200", "200", "0", "0", "0", "Cafeteria", "A place to eat", "C1" });
        POIs.add(new String[] { "2", "100", "100", "0", "0", "0", "Gym", "A place to exercise", "G1" });

        Search search = new Search(POIs, "1", "user");
        search.searchFilter("Library", "1");

        assertTrue(search.defaultListModel.contains("Library"));
        assertFalse(search.defaultListModel.contains("Cafeteria"));
        assertFalse(search.defaultListModel.contains("Gym"));
    }

    /**
     * Tests the functionality of the searchFilter() method when there are no matching results
     * @param None
     * @return void
     */
    @Test
    public void testSearchFilterNoResults() {
        // Tests funcitonality of SearchFilter when no results match the search
        ArrayList<String[]> POIs = new ArrayList<>();
        POIs.add(new String[] { "1", "100", "100", "0", "0", "0", "Library", "A place to read books", "L1" });
        POIs.add(new String[] { "1", "200", "200", "0", "0", "0", "Cafeteria", "A place to eat", "C1" });
        POIs.add(new String[] { "2", "100", "100", "0", "0", "0", "Gym", "A place to exercise", "G1" });

        Search search = new Search(POIs, "1", "user");
        search.searchFilter("Nonexistent", "1");

        assertEquals(0, search.defaultListModel.size());
    }
}