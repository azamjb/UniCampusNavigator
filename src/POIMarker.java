import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;;

// https://stackoverflow.com/questions/14705684/placing-a-marker-within-the-image
public class POIMarker extends JLabel {

    boolean favourite; // keep track if poi should be in favourites
    String type; // keep track of poi for layers

    public POIMarker(String desc, String name, String num) {
        favourite = false;
        try {
            setIcon(new ImageIcon(ImageIO.read(getClass().getResource("images/poi.png"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPopup(e, name, desc, num);
            }
        });
    }

    private void showPopup(MouseEvent e, String name, String desc, String num) {
        JFrame popupFrame = new JFrame(name);
    
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupFrame.setSize(575, 350);
        popupFrame.setLocationRelativeTo(null);
        popupFrame.setVisible(true);
    
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Create a panel for the title and room number labels
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel titleLabel = new JLabel("Name: " + name);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 18));
        topPanel.add(titleLabel);
    
        JLabel roomnumLabel = new JLabel("Room number: " + num);
        roomnumLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        roomnumLabel.setFont(roomnumLabel.getFont().deriveFont(18));
        topPanel.add(roomnumLabel);
    
        // Create a panel for the description JTextArea
        JPanel descPanel = new JPanel(new BorderLayout());
        JTextArea description = new JTextArea(desc);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        descPanel.add(description, BorderLayout.CENTER);
    
        // Create a panel for the favorite button
        JPanel favPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton favBtn = new JButton("Add to Favorites");
        JButton unfavBtn = new JButton("Remove from Favorites");
        favBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFavourite();
                JOptionPane.showMessageDialog(popupFrame, "Added to favorites.");
            }
        });
        unfavBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unsetFavourite();
                JOptionPane.showMessageDialog(popupFrame, "Removed from favorites.");
            }
        });
        favPanel.add(favBtn);
        favPanel.add(unfavBtn);
    
        // Add the panels to the parent panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(descPanel, BorderLayout.CENTER);
        panel.add(favPanel, BorderLayout.SOUTH);
    
        popupFrame.add(panel);
    }

    // set favourite as true
    public void setFavourite(){
        this.favourite = true;
    }

    // set favourite as false
    public void unsetFavourite(){
        this.favourite = false;
    }

    // check whether or not poi is a favourite
    public boolean isFavourite(){
        return this.favourite;
    }

    // set poi type
    public void setType(String newType){
        this.type = newType;
    }

    // get poi type
    public String getType(){
        return this.type;
    }

}