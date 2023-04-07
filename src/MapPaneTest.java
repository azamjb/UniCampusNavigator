import org.junit.Test;
import org.junit.Assert;
import java.awt.Dimension;

/**
 * J Unit testing for the MapPane class
 * @author Azam Jawad Butt
 */
public class MapPaneTest {

    /**
     * Tests if constuctor successfully creates object
     * @param None
     * @return void
     */
    @Test
    public void testMapPaneConstructor() {
        MapPane mapPane = new MapPane("test_floor");
        Assert.assertNotNull(mapPane);
    }

    /**
     * Tests GetPreferredSize when valid image is presented
     * @param None
     * @return void
     */
    @Test
    public void testGetPreferredSizeWithMap() {
        MapPane mapPane = new MapPane("images/mc1.png");
        Dimension expected = new Dimension(2104, 1154); 
        Dimension actual = mapPane.getPreferredSize();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests GetPrefferedSize when no image is provided
     * @param None
     * @return void
     */
    @Test
    public void testGetPreferredSizeWithoutMap() {
        MapPane mapPane = new MapPane("non_existent_image");
        Dimension expected = new Dimension(0, 0);
        Dimension actual = mapPane.getPreferredSize();
        Assert.assertEquals(expected, actual);
    }
}