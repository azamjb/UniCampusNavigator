/**
 * @author Shadi Seaidoun
 * Design and function for a POI
 * Once clicked,popup appears and displays informaiton about poi
 * poi information is stored in json data
 * poi's are placed on the map based on coordinates, displayed as an image
 *
 */


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.event.MouseListener;

import org.json.JSONObject;
import org.json.simple.JSONArray;
// import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// https://stackoverflow.com/questions/14705684/placing-a-marker-within-the-image
public class POIMarker extends JLabel {

    boolean favourite; // keep track if poi should be in favourites
    String type; // keep track of poi for layers
    String userType; // keep track of user type to help with editing

    JTextField buildingNameInput, floorNumInput, poiTypeInput, poiNameInput, descriptionInput, roomNumInput; // Declare
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18); // Create font type, to be used for text in project

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
        /**
         * display information about poi in a popup when it is clicked
         * @params are the information to be displayed when poi is clicked
         */
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
        JButton editBtn = new JButton("Edit POI");
        
        // If user wants to add poi to favourites
        favBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFavourite();
                JOptionPane.showMessageDialog(popupFrame, "Added to favorites.");
            }
        });

        // If user wants to remove POI from favourites
        unfavBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unsetFavourite();
                JOptionPane.showMessageDialog(popupFrame, "Removed from favorites.");
            }
        });

        // If user wants to edit POI --> 2 cases
        // User is admin --> should have the ability to edit both built in and user created POIS
        // User is a regualr --> should have the ability to edit user created pois only
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                // Initialize frame to display
                JFrame mainFrame = new JFrame();

                // Form Panel
                JPanel formPanel = new JPanel(); // Create new instance of JPanel class
                formPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                formPanel.setBackground(new Color(79, 38, 130)); // Set background colour to Western University purple
                c.weightx = 1;

                JLabel lbbuildingName = new JLabel("Building Name: "); // Create new instance of JLabel class, for username
                                                                    // label
                lbbuildingName.setFont(mainFont);
                lbbuildingName.setForeground(Color.WHITE);
                c.gridx = 0;
                c.gridy = 0;
                c.anchor = GridBagConstraints.EAST; // Set component to right side of its cell
                c.insets = new Insets(0, 0, 0, 10); // Add slight padding to the right
                formPanel.add(lbbuildingName, c);

                buildingNameInput = new JTextField(15); // Create instance of JTextField class, for username input
                buildingNameInput.setFont(mainFont);
                c.gridx = 1;
                c.gridy = 0;
                c.anchor = GridBagConstraints.WEST; // Set component to left side of its cell
                formPanel.add(buildingNameInput, c);

                JLabel lbFloorNum = new JLabel("Floor Number: "); // Create new instance of JLabel class, for username
                                                                // label
                lbFloorNum.setFont(mainFont);
                lbFloorNum.setForeground(Color.WHITE);
                c.gridx = 0;
                c.gridy = 1;
                c.anchor = GridBagConstraints.EAST; // Set component to right side of its cell
                c.insets = new Insets(0, 0, 0, 10); // Add slight padding to the right
                formPanel.add(lbFloorNum, c);

                floorNumInput = new JTextField(15); // Create instance of JTextField class, for username input
                floorNumInput.setFont(mainFont);
                c.gridx = 1;
                c.gridy = 1;
                c.anchor = GridBagConstraints.WEST; // Set component to left side of its cell
                formPanel.add(floorNumInput, c);

                JLabel lbPOItype = new JLabel("POI Type: "); // Create new instance of JLabel class, for username
                                                            // label
                lbPOItype.setFont(mainFont);
                lbPOItype.setForeground(Color.WHITE);
                c.gridx = 0;
                c.gridy = 2;
                c.anchor = GridBagConstraints.EAST; // Set component to right side of its cell
                c.insets = new Insets(0, 0, 0, 10); // Add slight padding to the right
                formPanel.add(lbPOItype, c);

                poiTypeInput = new JTextField(15); // Create instance of JTextField class, for username input
                poiTypeInput.setFont(mainFont);
                c.gridx = 1;
                c.gridy = 2;
                c.anchor = GridBagConstraints.WEST; // Set component to left side of its cell
                formPanel.add(poiTypeInput, c);

                JLabel lbPOIName = new JLabel("POI Name: "); // Create new instance of JLabel class, for username
                // label
                lbPOIName.setFont(mainFont);
                lbPOIName.setForeground(Color.WHITE);
                c.gridx = 0;
                c.gridy = 3;
                c.anchor = GridBagConstraints.EAST; // Set component to right side of its cell
                c.insets = new Insets(0, 0, 0, 10); // Add slight padding to the right
                formPanel.add(lbPOIName, c);

                poiNameInput = new JTextField(15); // Create instance of JTextField class, for username input
                poiNameInput.setFont(mainFont);
                c.gridx = 1;
                c.gridy = 3;
                c.anchor = GridBagConstraints.WEST; // Set component to left side of its cell
                formPanel.add(poiNameInput, c);

                JLabel lbDescInput = new JLabel("Description: "); // Create new instance of JLabel class, for username
                // label
                lbDescInput.setFont(mainFont);
                lbDescInput.setForeground(Color.WHITE);
                c.gridx = 0;
                c.gridy = 4;
                c.anchor = GridBagConstraints.EAST; // Set component to right side of its cell
                c.insets = new Insets(0, 0, 0, 10); // Add slight padding to the right
                formPanel.add(lbDescInput, c);

                descriptionInput = new JTextField(15); // Create instance of JTextField class, for username input
                descriptionInput.setFont(mainFont);
                c.gridx = 1;
                c.gridy = 4;
                c.anchor = GridBagConstraints.WEST; // Set component to left side of its cell
                formPanel.add(descriptionInput, c);

                JLabel lbRoomNum = new JLabel("Room Number: "); // Create new instance of JLabel class, for username
                // label
                lbRoomNum.setFont(mainFont);
                lbRoomNum.setForeground(Color.WHITE);
                c.gridx = 0;
                c.gridy = 5;
                c.anchor = GridBagConstraints.EAST; // Set component to right side of its cell
                c.insets = new Insets(0, 0, 0, 10); // Add slight padding to the right
                formPanel.add(lbRoomNum, c);

                roomNumInput = new JTextField(15); // Create instance of JTextField class, for username input
                roomNumInput.setFont(mainFont);
                c.gridx = 1;
                c.gridy = 5;
                c.anchor = GridBagConstraints.WEST; // Set component to left side of its cell
                formPanel.add(roomNumInput, c);

                // Create Button
                JButton doneBtn = new JButton("Done"); // Create new JButton instance for login button
                doneBtn.setFont(mainFont);
                doneBtn.setPreferredSize(new Dimension(150, 40)); // Set button size

                if(userType.equals("admin")){
                    doneBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            // get user input
                            String buildingName = buildingNameInput.getText();
                            String floorNum = floorNumInput.getText();
                            String poiType = poiTypeInput.getText();
                            String poiName = poiNameInput.getText();
                            String desc = descriptionInput.getText();
                            String roomNum = roomNumInput.getText();

                            // variables to get desired poi 
                            String JpgName;
                            String xValStr;
                            String yValStr;

                            // get metadata from admin 
                            for (int i = 0; i < getAdminPOIHashMap().size(); i++) {
                                Map<String, String[]> adminMap = getAdminPOIHashMap();
                                String intStr = String.valueOf(i);
                                String[] valArr = adminMap.get(intStr);
                                
                                for(int j=0; j < valArr.length; j++){
                                    
                                    // find poi to edit
                                    if (valArr[3].equals(buildingName) && valArr[4].equals(floorNum) && valArr[8].equals(roomNum)){
                                        JpgName = valArr[0];
                                        xValStr = valArr[1];
                                        yValStr = valArr[2];

                                        // overwite poi metadata in json file
                                        String key = Integer.toString(i);
                                        writePOI(JpgName, xValStr, yValStr, buildingName, floorNum, poiType, poiName, desc, roomNum, key);
                                        break;
                                    }
                                    
                                }
                            }

                        }
                    });
                }

                // user is not an admin --> loop through user hashmap
                else{
                    doneBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            // get user input
                            String buildingName = buildingNameInput.getText();
                            String floorNum = floorNumInput.getText();
                            String poiType = poiTypeInput.getText();
                            String poiName = poiNameInput.getText();
                            String desc = descriptionInput.getText();
                            String roomNum = roomNumInput.getText();

                            // variables to get desired poi 
                            String JpgName;
                            String xValStr;
                            String yValStr;
                            boolean found = false;

                            // get metadata from admin 
                            for (int i = 0; i < getUserPOIHashMap().size(); i++) {
                                Map<String, String[]> userMap = getUserPOIHashMap();
                                String intStr = String.valueOf(i);
                                String[] valArr = userMap.get(intStr);
                                
                                
                                for(int j=0; j < valArr.length; j++){
                                    
                                    // find poi to edit
                                    if (valArr[3].equals(buildingName) && valArr[4].equals(floorNum) && valArr[8].equals(roomNum)){
                                        JpgName = valArr[0];
                                        xValStr = valArr[1];
                                        yValStr = valArr[2];

                                        // overwite poi metadata in json file
                                        String key = Integer.toString(i);
                                        writePOI(JpgName, xValStr, yValStr, buildingName, floorNum, poiType, poiName, desc, roomNum, key);
                                        found = true;
                                        break;
                                    }
                                }
                            }

                            // user trying to change built in poi
                            if (!found){
                                JFrame layerFrame = new JFrame();
                                layerFrame.setTitle("Error");
                                layerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                layerFrame.setSize(new Dimension(250, 150));
                                layerFrame.setLocationRelativeTo(null); // Center popup frame on screen
                                
                                JPanel layerPanel = new JPanel();
                                layerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                                JLabel layerLabel = new JLabel("Cannot edit built in POI's");
                                JButton layerButton = new JButton("OK");
                                layerButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                    
                                        layerFrame.dispose();
                                        
                                    }
                                });

                                layerPanel.add(layerLabel);
                                layerPanel.add(layerButton);
                                layerFrame.add(layerPanel);
                                layerFrame.setVisible(true);
                            }

                        }
                    });
                }

                c.gridx = 1;
                c.gridy = 6;
                c.anchor = GridBagConstraints.WEST;
                c.insets = new Insets(10, 0, 0, 0); // Add slight padding to the top
                formPanel.add(doneBtn, c);

                // Main Panel
                JPanel mainPanel = new JPanel(); // Create new JPanel instance for main panel
                mainPanel.setLayout(new BorderLayout());
                mainPanel.setBackground(new Color(255, 255, 255));
                mainPanel.add(formPanel, BorderLayout.CENTER); // Add form panel to main panel, in center region

                // Add all components to frame
                mainFrame.add(mainPanel);
                mainFrame.setTitle("Enter the POI details!");
                mainFrame.setSize(800, 400); // Set dimensions for screen
                mainFrame.setLocationRelativeTo(null); // Center the window on the screen
                mainFrame.setVisible(true);

                    
            }
        });


        favPanel.add(favBtn);
        favPanel.add(unfavBtn);
        favPanel.add(editBtn);
    
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

    // set the user type to for poi editing
    public void setUserType(String type){
        this.userType = type;
    }

    // Get admin pois for editing
    private Map<String, String[]> getAdminPOIHashMap() {
        /**
         * turning json into a dictionary
         */
        String filename = "admin.json";
        try {
            // file contents into a byte array
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            // byte array to string with UTF-8 encoding
            String jsonString = new String(bytes, "UTF-8");
            // JSON string to Map with org.json library
            JSONObject jsonObject = new JSONObject(jsonString);
            Map<String, String[]> dictionary = new HashMap<>();
            for (String key : jsonObject.keySet()) {
                org.json.JSONArray jsonArray = jsonObject.getJSONArray(key);
                String[] strArr = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    strArr[i] = jsonArray.getString(i);
                }
                dictionary.put(key, strArr);
            }
            return dictionary;
        } catch (IOException error) {
            System.out.println("Error reading file: " + error.getMessage());
        }
        return null;
    }

    // get user pois for editing
    private Map<String, String[]> getUserPOIHashMap() {
        /**
         * turning json into a dictionary
         */
        String filename = this.userType + ".json";
        try {
            // file contents into a byte array
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            // byte array to string with UTF-8 encoding
            String jsonString = new String(bytes, "UTF-8");
            // JSON string to Map with org.json library
            JSONObject jsonObject = new JSONObject(jsonString);
            Map<String, String[]> dictionary = new HashMap<>();
            for (String key : jsonObject.keySet()) {
                org.json.JSONArray jsonArray = jsonObject.getJSONArray(key);
                String[] strArr = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    strArr[i] = jsonArray.getString(i);
                }
                dictionary.put(key, strArr);
            }
            return dictionary;
        } catch (IOException error) {
            System.out.println("Error reading file: " + error.getMessage());
        }
        return null;
    }

    public void simulateClick() {
        /**
         * For use in JUnit testing
         */
        MouseEvent me = new MouseEvent(this, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
        for (MouseListener listener : this.getMouseListeners()) {
            listener.mouseClicked(me);
        }
    }


    public void writePOI(String jpgName, String xVal, String yVal, String bldName, String floorNum, String POIType,
            String POIName,
            String description,
            String roomNumString, String key) {
                /**
                 * create new poi based on given @params
                 */

        String filename = this.userType + ".json";
        try {
            // Read existing data from file
            String jsonString = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonString);

            // Add new data to JSONObject
            int keysCount = jsonObject.length();
            //String key = Integer.toString(keysCount);
            JSONArray POIarr = new JSONArray();
            POIarr.add(jpgName);
            POIarr.add(xVal);
            POIarr.add(yVal);
            POIarr.add(bldName);
            POIarr.add(floorNum);
            POIarr.add(POIType);
            POIarr.add(POIName);
            POIarr.add(description);
            POIarr.add(roomNumString);

            jsonObject.put(key, POIarr);

            // Write updated data back to file
            Files.write(Paths.get(filename), jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

}