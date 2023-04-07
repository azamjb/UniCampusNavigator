import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

/**
 * Graphical display of the floor plan
 * <p> inspired from https://stackoverflow.com/questions/14705684/placing-a-marker-within-the-image <p>
 * @author Nathan Michael Vos
 * @author Shadi Seaidoun
 */
public class MapPane extends JLayeredPane {
    
    private BufferedImage map;

    /**
     * Constructor, loads the image
     * @param floor, path to image file that will be displayed
     */
    public MapPane(String floor) {
        
        try {
            map = ImageIO.read(getClass().getResource(floor));
        } 
        catch (Exception e) {
        }
    }

    /**
     * Gets the prefferedSize of the image.
     * @return Dimension object containing the preferred size of the map
     */
    @Override
    public Dimension getPreferredSize() {
        
        return map == null ? super.getPreferredSize() : new Dimension(map.getWidth(), map.getHeight());
    }

    /**
     * Paints the map onto the MapPane object.
     * @param g, Graphics object of the map to be painted
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (map != null) {
            g.drawImage(map, 0, 0, this);
        }
    }
}
