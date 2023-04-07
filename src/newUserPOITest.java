/**
 * @author Azam Jawad Butt
 * Testing for NewUserPOI
 */

import org.junit.Test;

public class newUserPOITest {

    @Test
    public void testWritePOI() {
         /**
          * Tests that a new POI can be written to a JSON file with no errors
          */
        newUserPOI poi = new newUserPOI();
        // Call writePOI with sample data
        poi.writePOI("image.jpg", "10", "20", "Science Building", "2", "Restroom", "Mens Washroom", "A restroom for men", "SB 203");

        
    }
}
