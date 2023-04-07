/**
 * @author Andrea Jackson
 * J Unit testing for POIMarker
 */
import org.junit.Test;
import javax.swing.Icon;
import org.junit.Before;
import javax.swing.JFrame;
import static org.junit.Assert.*;

public class POIMarkerTest {

    private POIMarker poiMarker;
    String desc = "Test description";
    String roomname = "Test name";
    String roomnum = "Test room number";

    @Before
    public void setUp() {
        poiMarker = new POIMarker(desc, roomname, roomnum);
    }

    @Test
    public void testPOIMarker() {
        /**
         * Test POIMarker object is created and icon is set correctly
         */
        assertNotNull("POIMarker object should not be null", poiMarker);

        Icon icon = poiMarker.getIcon();
        assertNotNull("Icon should not be null", icon);
    }

    @Test
    public void testShowPopup() {
        /**
         * Test that popup appears when poiMarker is clicked
         */
        JFrame testFrame = new JFrame();
        testFrame.add(poiMarker);
        testFrame.pack();
        testFrame.setVisible(true);
        poiMarker.simulateClick(); // Simulates mouse click on poiMarker
        testFrame.dispose();
        }

}
