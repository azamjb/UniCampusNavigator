/**
 * @author Azam Jawad Butt
 * J Unit testing for Map Pane
 */

import org.junit.Test;
import org.junit.Assert;
import java.awt.Dimension;

public class MapPaneTest {

    @Test
    public void testMapPaneConstructor() {
        /**
         * Tests if constuctor successfully creates object
         */
        MapPane mapPane = new MapPane("test_floor");
        Assert.assertNotNull(mapPane);
    }

    @Test
    public void testGetPreferredSizeWithMap() {
        /**
         * Tests GetPreferredSize when valid image is presented
         */
        MapPane mapPane = new MapPane("images/mc1.png");
        Dimension expected = new Dimension(2104, 1154); 
        Dimension actual = mapPane.getPreferredSize();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPreferredSizeWithoutMap() {
        /**
         * Tests GetPrefferedSize when no image is provided
         */
        MapPane mapPane = new MapPane("non_existent_image");
        Dimension expected = new Dimension(0, 0);
        Dimension actual = mapPane.getPreferredSize();
        Assert.assertEquals(expected, actual);
    }
}