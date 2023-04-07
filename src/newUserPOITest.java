import org.junit.Test;

/**
 * Testing for the NewUserPOI class
 * @author Azam Jawad Butt
 */
public class newUserPOITest {

    /**
     * Tests that a new POI can be written to a JSON file with no errors
     * @param None
     * @return void
     */
    @Test
    public void testWritePOI() {
        newUserPOI poi = new newUserPOI();
        // Call writePOI with sample data
        poi.writePOI("image.jpg", "10", "20", "Science Building", "2", "Restroom", "Mens Washroom", "A restroom for men", "SB 203");
    }
}
