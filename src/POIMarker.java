import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

// https://stackoverflow.com/questions/14705684/placing-a-marker-within-the-image
public class POIMarker extends JLabel {
    public POIMarker() {
        try {
            setIcon(new ImageIcon(ImageIO.read(getClass().getResource("images/poi.png"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
