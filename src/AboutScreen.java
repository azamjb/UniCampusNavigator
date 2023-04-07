/**
 * @author Riley Sgro
 * GUI for about screen
 * Displays information about the program, creators, purpose, etc
 */

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AboutScreen extends JDialog {

    private static final long serialVersionUID = 1L;
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18); // Create font type, to be used for text in project

    public AboutScreen() {
        setTitle("About My Application");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel contentPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("UWO campus Building Navigation");
        titleLabel.setFont(mainFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        infoPanel.add(new JLabel("Version:    1.0"));
        infoPanel.add(new JLabel("Release Date:    April 6, 2023"));
        infoPanel.add(new JLabel("Team Members: "));
        infoPanel.add(new JLabel("Andrea Jackson:    ajacks94@uwo.ca"));
        infoPanel.add(new JLabel("Riley Sgro:    rsgro2@uwo.ca"));
        infoPanel.add(new JLabel("Azam Jawad Butt:    ajawadbu@uwo.ca"));
        infoPanel.add(new JLabel("Shadi Seaidoun:    sseaidou@uwo.ca"));
        infoPanel.add(new JLabel("Nathan Michael Vos:    nvos2@uwo.ca"));

        contentPanel.add(infoPanel, BorderLayout.CENTER);


        add(contentPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AboutScreen();
    }


}