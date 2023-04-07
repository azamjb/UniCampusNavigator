import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

/**
 * @author Azam Jawad Butt
 * J Unit testing for Layers
 */
public class LayersTest {

    /**
     * Tests the constructor for the Layers class
     */
    @Test
    public void testLayersConstructor() {
        // Tests that ArrayList<POIMarker> can be initialized correctly, not null, with correct size
        ArrayList<POIMarker> poiMarkers = new ArrayList<>();
        poiMarkers.add(new POIMarker("desc1", "name1", "num1"));
        poiMarkers.add(new POIMarker("desc2", "name2", "num2"));

        Layers layers = new Layers(poiMarkers);
        assertNotNull("ArrayList<POIMarker> should not be null", layers.list);
        assertEquals("ArrayList<POIMarker> should have the correct size", 2, layers.list.size());
    }

    /**
     * Tests the changeLayer() method for the Layers class
     */
    @Test
    public void testChangeLayer() {
        // Tests that visibility of POIMarker objects are updated based on selected layer, uses robot to simulate interactions
        ArrayList<POIMarker> poiMarkers = new ArrayList<>();
        POIMarker marker1 = new POIMarker("desc1", "name1", "num1");
        marker1.setType("Food");
        POIMarker marker2 = new POIMarker("desc2", "name2", "num2");
        marker2.setType("Classroom");
        poiMarkers.add(marker1);
        poiMarkers.add(marker2);

        Layers layers = new Layers(poiMarkers);
        try {
            layers.changeLayer(); // Open the JFrame and wait for user input

            // Create a Robot to simulate user interactions
            Robot robot = new Robot();
            robot.setAutoDelay(500); // Delay between events

            // Move cursor to JComboBox
            robot.mouseMove(500, 500);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // Move cursor to select "Food" layer
            robot.mouseMove(500, 540); 
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // Move cursor to "OK" button and click
            robot.mouseMove(500, 600); 
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // Check if the visibility of POIMarkers has been updated correctly
            assertTrue("Food POIMarker should be visible", marker1.isVisible());

        } catch (Exception e) {
            fail("changeLayer method should not throw an exception");
        }
    }
}