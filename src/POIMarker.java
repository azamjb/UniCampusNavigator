import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// https://stackoverflow.com/questions/14705684/placing-a-marker-within-the-image
public class POIMarker extends JLabel {
    public POIMarker() {
        try {
            setIcon(new ImageIcon(ImageIO.read(getClass().getResource("images/poi.png"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPopup(e);
            }
        });
    }

    private void showPopup(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "Information about this POI", "POI Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
