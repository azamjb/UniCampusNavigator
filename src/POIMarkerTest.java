import org.junit.Test;
import javax.swing.Icon;
import org.junit.Before;
import javax.swing.JFrame;
import static org.junit.Assert.*;

/**
 * J Unit testing for POIMarker
 * @author Andrea Jackson
 */
public class POIMarkerTest {

    private POIMarker poiMarker;
    String desc = "Test description";
    String roomname = "Test name";
    String roomnum = "Test room number";

    /**
     * Sets up a POIMarker object
     * @param None
     * @return void
     */
    @Before
    public void setUp() {
        poiMarker = new POIMarker(desc, roomname, roomnum);
    }

    /**
     * Tests that the POIMarker object was created correctly
     * @param None
     * @return void
     */
    @Test
    public void testPOIMarker() {
        assertNotNull("POIMarker object should not be null", poiMarker);

        Icon icon = poiMarker.getIcon();
        assertNotNull("Icon should not be null", icon);
    }

    /**
     * Test that popup appears when poiMarker is clicked
     * @param None
     * @return void
     */
    @Test
    public void testShowPopup() {
        JFrame testFrame = new JFrame();
        testFrame.add(poiMarker);
        testFrame.pack();
        testFrame.setVisible(true);
        poiMarker.simulateClick(); // Simulates mouse click on poiMarker
        testFrame.dispose();
        }

}
