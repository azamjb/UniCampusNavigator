import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * GUI for new POI creation, provides option for user to create a new POI
 * @author Nathan Michael Vos
 * @author Andrea Jackson
 */
public class newUserPOI {
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18); // Create font type, to be used for text in
                                                                          // project
    String userName;

    String JpgName;
    String xValStr;
    String yValStr;
    String buildingName;
    String floorNumStr;
    String poiType;
    String poiName;
    String description;
    String roomNumStr;

    JTextField buildingNameInput, floorNumInput, poiTypeInput, poiNameInput, descriptionInput, roomNumInput; // Declare
                                                                                                             // variables
    /**
     * Empty constructor for the newUserPOI class
     * @param None
     */
    public newUserPOI() {
    }

    /**
     * Initializes the newUserPOI screen and takes input for a new POI to be written
     * @param XVal, String with the x coordinates of the POI
     * @param YVAL, String with the y coordinates of the POI
     * @param jpgName, String with the corresponding file name for the map of the POI
     * @return void
     */
    public void initialize(String XVal, String YVal, String jpgName) {
        setXVal(XVal);
        setYVal(YVal);
        setJpgName(jpgName);

        // Json file name from username initialization
        LoginFrame temp = new LoginFrame(); // create new login frame object to call the static
        // username object
        this.userName = temp.getUserStr();

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
        JButton btnCreate = new JButton("Create"); // Create new JButton instance for login button
        btnCreate.setFont(mainFont);
        btnCreate.setPreferredSize(new Dimension(150, 40)); // Set button size
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buildingName = buildingNameInput.getText();
                String floorNum = floorNumInput.getText();
                String poiType = poiTypeInput.getText();
                String poiName = poiNameInput.getText();
                String desc = descriptionInput.getText();
                String roomNum = roomNumInput.getText();

                setBuildingName(buildingName);
                setFloorNum(floorNum);
                setPOItype(poiType);
                setPOIName(poiName);
                setDescription(desc);
                setRoomNum(roomNum);

                writePOI(getJpgName(), getXVal(), getYVal(), getBuildingName(), getFloorNum(), getPOItype(),
                        getPOIName(),
                        getDescription(),
                        getRoomNum());

                mainFrame.dispose(); // When new POI is created, dispose current frame and return to Maps menu
                new Maps(getJpgName(), userName);
            }
        });
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 0, 0, 0); // Add slight padding to the top
        formPanel.add(btnCreate, c);

        // Main Panel
        JPanel mainPanel = new JPanel(); // Create new JPanel instance for main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.add(formPanel, BorderLayout.CENTER); // Add form panel to main panel, in center region

        // Logo Panel
        ImageIcon logoIcon = new ImageIcon("westernlogo.jpg"); // Load image from relative path as ImageIcon
        Image logoImage = logoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); // Resize image
        logoIcon = new ImageIcon(logoImage);
        JLabel lbLogo = new JLabel(logoIcon); // Create new JLABEL object, sets its icon to LogoIcon object
        GridBagConstraints logoConstraints = new GridBagConstraints();
        logoConstraints.anchor = GridBagConstraints.CENTER; // Specifies how image icon should be positioned within its
                                                            // cell
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new GridBagLayout());
        logoPanel.setBackground(new Color(79, 38, 130)); // Set panel background colour to Western University purple
        logoPanel.add(lbLogo, logoConstraints);
        mainPanel.add(logoPanel, BorderLayout.WEST);

        // Add all components to frame
        mainFrame.add(mainPanel);
        mainFrame.setTitle("Enter the POI details!");
        mainFrame.setSize(800, 400); // Set dimensions for screen
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Exit application when frame is closed
        mainFrame.setLocationRelativeTo(null); // Center the window on the screen
        mainFrame.setVisible(true);

    }

    /**
     * Private setter for the name of the image
     * @param String JpgName, the name of the image
     * @return void
     */
    private void setJpgName(String JpgName) {
        this.JpgName = JpgName;
    }
    /**
     * Private setter for the x coordinate of the POI
     * @param String xVal, the value of the x coordinate
     * @return void
     */
    private void setXVal(String xVal) {
        this.xValStr = xVal;
    }
    /**
     * Private setter for the y coordinate of the POI
     * @param String yVal, the value of the y coordinate
     * @return void
     */
    private void setYVal(String yVal) {
        this.yValStr = yVal;
    }
    /**
     * Private setter for the building name of the POI
     * @param String bldName, the building's name
     * @return void
     */
    private void setBuildingName(String bldName) {
        this.buildingName = bldName;
    }
    /**
     * Private setter for the floor of the POI
     * @param String floorNum, what floor the POI is on
     * @return void
     */
    private void setFloorNum(String floorNum) {
        this.floorNumStr = floorNum;
    }
    /**
     * Private setter for the type of the POI
     * @param String poiType, the type of the POI
     * @return void
     */
    private void setPOItype(String poiType) {
        this.poiType = poiType;
    }
    /**
     * Private setter for the name of the POI
     * @param String poiName, the name of the POI
     * @return void
     */
    private void setPOIName(String poiName) {
        this.poiName = poiName;
    }
    /**
     * Private setter for the description of the POI
     * @param String desc, the POI's description
     * @return void
     */
    private void setDescription(String desc) {
        this.description = desc;
    }
    /**
     * Private setter for the room number of the POI
     * @param String roomNum, the POI's room number
     * @return void
     */
    private void setRoomNum(String roomNum) {
        this.roomNumStr = roomNum;
    }

    /**
     * Private getter for the image of the POI's map
     * @param None
     * @return String JpgName, the POI's corresponding jpg
     */
    private String getJpgName() {
        return this.JpgName;
    }
    /**
     * Private getter for the x coordinate of the POI
     * @param None
     * @return String xValStr, the POI's x coordinate
     */
    private String getXVal() {
        return this.xValStr;
    }
    /**
     * Private getter for the y coordinate of the POI
     * @param None
     * @return String yValStr, the POI's y coordinate
     */
    private String getYVal() {
        return this.yValStr;
    }
    /**
     * Private getter for the building name for the POI
     * @param None
     * @return String buildingName, the name of the building for the POI
     */
    private String getBuildingName() {
        return this.buildingName;
    }
    /**
     * Private getter for the floor number of the POI
     * @param None
     * @return String floorumStr, the number of the floor the POI is on
     */
    private String getFloorNum() {
        return this.floorNumStr;
    }
    /**
     * Private getter for the POI's type
     * @param None
     * @return String poiType, the type of the POI
     */
    private String getPOItype() {
        return this.poiType;
    }
    /**
     * Private getter for the POI's name
     * @param None
     * @return String poiName, the name of the POI
     */
    private String getPOIName() {
        return this.poiName;
    }
    /**
     * Private getter for the POI's description
     * @param None
     * @return String description, the POI's description
     */
    private String getDescription() {
        return this.description;
    }
    /**
     * Private getter for the POI's room number
     * @param None
     * @return String roomNumStr, the room number of the POI
     */
    private String getRoomNum() {
        return this.roomNumStr;
    }

    /**
     * Writes the created POI to the designated json file of the user
     * @param jpgName, String with the corresponding file name for the map of the POI
     * @param xVal, String with the x coordinates of the POI
     * @param yVAL, String with the y coordinates of the POI
     * @param bldName, String with the name of the building the POI is in
     * @param floorNum, String with the corresponding floor of the building the POI is in
     * @param POIType, String with the type of POI to be written
     * @param POIName, String with the name of the POI
     * @param description, String with the description of the POI
     * @param roomNumString, String with the room number of the POI
     * @return void
     */
    public void writePOI(String jpgName, String xVal, String yVal, String bldName, String floorNum, String POIType,
            String POIName,
            String description,
            String roomNumString) {

        String filename = this.userName + ".json";
        try {
            // Read existing data from file
            String jsonString = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonString);

            // Add new data to JSONObject
            int keysCount = jsonObject.length();
            String key = Integer.toString(keysCount);
            JSONArray POIarr = new JSONArray();
            POIarr.put(getJpgName());
            POIarr.put(getXVal());
            POIarr.put(getYVal());
            POIarr.put(getBuildingName());
            POIarr.put(getFloorNum());
            POIarr.put(getPOItype());
            POIarr.put(getPOIName());
            POIarr.put(getDescription());
            POIarr.put(getRoomNum());

            jsonObject.put(key, POIarr);

            // Write updated data back to file
            Files.write(Paths.get(filename), jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
