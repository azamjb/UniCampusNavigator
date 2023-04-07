/**
 * @author Andrea Jackson
 * J Unit testing for Maps
 */


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Component;
import java.awt.image.BufferedImage;


public class MapsTest {

    private Maps maps;

    @Before
    public void setUp() {
        maps = new Maps();
    }

    @After
    public void tearDown() {
    maps = null;
    }
    @Test
    public void testBuildingSelect() throws AWTException {
        /**
         * Tests that building is selected after button is clicked
         */
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
