/**
 * @author Azam Jawad Butt
 * Testing for Search
 */
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchTest {

    @Test
    public void testSearchFilter() {
        // Tests functionality of SearchFileter, creates arraylist of POI data and initializes search instance
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