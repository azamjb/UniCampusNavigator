import static org.junit.Assert.assertTrue;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * J Unit testing for the Maps class
 * @author Andrea Jackson
 */
public class MapsTest {

    // create a Maps object for testing
    private Maps maps;

    /**
     * Initializes the Map object
     */
    @Before
    public void setUp() {
        maps = new Maps();
    }

    /**
     * Tears down the Map object
     */
    @After
    public void tearDown() {
    maps = null;
    }

    /**
     * Tests the buildingSelect() function
     */
    @Test
    public void testBuildingSelect() throws AWTException {
    JFrame frame = new JFrame();
    frame.setVisible(true);
    Robot robot = new Robot();
    robot.setAutoDelay(1000);
    robot.mouseMove(frame.getLocationOnScreen().x + 50, frame.getLocationOnScreen().y + 50);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    robot.delay(2000);
    assertTrue(maps.currentBuilding != null);
    }
}
