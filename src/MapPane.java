import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

// https://stackoverflow.com/questions/14705684/placing-a-marker-within-the-image
public class MapPane extends JLayeredPane {
    
    private BufferedImage map;

    public MapPane(String floor) {
        try {
            map = ImageIO.read(getClass().getResource(floor));
        } catch (Exception e) {
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return map == null ? super.getPreferredSize() : new Dimension(map.getWidth(), map.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (map != null) {
            g.drawImage(map, 0, 0, this);
        }
    }
}
