/**
 * @author Azam Jawad Butt
 * Testing for NewUserPOI
 */


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.*;
import org.junit.internal.*;
import org.junit.Assert;


import javax.swing.JFrame;

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
