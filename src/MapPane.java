/**
 * @author Shadi Seaidoun
 * Displays floor plan
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

// https://stackoverflow.com/questions/14705684/placing-a-marker-within-the-image
public class MapPane extends JLayeredPane {
    
    private BufferedImage map;

    public MapPane(String floor) {
        /**
        * Constructor, loads the image
        * @param floor, path to image file that will be displayed
        */
        try {
            map = ImageIO.read(getClass().getResource(floor));
        } catch (Exception e) {
        }
    }

    @Override
    public Dimension getPreferredSize() {
        /**
         * determines preffered size of component
         */
        return map == null ? super.getPreferredSize() : new Dimension(map.getWidth(), map.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        /**
         * allows component to display the map image on its surface
         */
        super.paintComponent(g);
        if (map != null) {
            g.drawImage(map, 0, 0, this);
        }
    }
}
