import java.awt.*;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

/**
 * Displays maps of the buildings, with lots of features
 * Includes building selection screen, which then loads map
 * Options to change floors, panel on top of screen providing many features
 * @author Andrea Jackson
 * @author Azam Jawad Butt
 * @author Shadi Seaidoun
 * @author Riley Sgro
 * @author Nathan Michael Vos
 */
public class Maps {
    // Json file name from username
    LoginFrame temp;
    String userName;
    int POIcreated = 0;
    ArrayList<POIMarker> poiObjectList = new ArrayList<>();
    JScrollPane scrollPane;

    // File names for all the floor
    Map<String, String> mcDict = Map.of("Ground Floor", "images/mc1.png", "1st Floor", "images/mc1.png", "2nd Floor",
            "images/mc2.png", "3rd Floor", "images/mc3.png", "4th Floor", "images/mc4.png");
    Map<String, String> wscDict = Map.of("Ground Floor", "images/wscgf.png", "1st Floor", "images/wsc1.png",
            "2nd Floor", "images/wsc2.png", "3rd Floor", "images/wsc3.png");
    Map<String, String> tcDict = Map.of("1st Floor", "images/tc1.png", "2nd Floor", "images/tc2.png", "3rd Floor",
            "images/tc3.png", "4th Floor", "images/tc4.png");
    String currentBuilding = "";

    /**
     * Default constructor of the Maps class, initializes the building select screen
     */
    public Maps() {
        buildingSelect();

        // Json file name from username initialization
        temp = new LoginFrame(); // create new login frame object to call the static username object
        userName = temp.getUserStr();
    }

    /**
     * Modified constructor to skip the building selection after creating a User POI
     * @param displayMap, the string of the map to display
     * @param userName, the user name of the current user
     */
    public Maps(String displayMap, String userName) {
        this.userName = userName;
        if (displayMap.charAt(0) == 'm') {
            displayMC("images/" + displayMap);
        }
        else if (displayMap.charAt(0) == 'w') {
            displayWSC("images/" + displayMap);
        }
        else {
            displayTC("images/" + displayMap);
        }
    }

    /**
     * Modified constructor to skip building select and warp to POI after searching and warping to a POI
     * @param displayMap, the string of the map to display
     * @param userName, the user name of the current user
     * @param xVal, the x coordinate to scroll to
     * @param yVal, the y coordinate to scroll to
     */
    public Maps(String displayMap, String userName, int xVal, int yVal) {
        this.userName = userName;
        if (displayMap.charAt(0) == 'm') {
            displayMC("images/" + displayMap);
            scrollPane.getHorizontalScrollBar().setValue(xVal - 650);
            scrollPane.getVerticalScrollBar().setValue(yVal - 300);
            scrollPane.requestFocus();
        }
        else if (displayMap.charAt(0) == 'w') {
            displayWSC("images/" + displayMap);
            scrollPane.getHorizontalScrollBar().setValue(xVal - 650);
            scrollPane.getVerticalScrollBar().setValue(yVal - 300);
            scrollPane.requestFocus();
        }
        else {
            displayTC("images/" + displayMap);
            scrollPane.getHorizontalScrollBar().setValue(xVal - 650);
            scrollPane.getVerticalScrollBar().setValue(yVal - 300);
            scrollPane.requestFocus();
        }
    }

   
    /**
     * Screen that prompts the user to select between three buildings to navigate through floor
     */
    public void buildingSelect() {
        Font mainFont = new Font("Segoe print", Font.BOLD, 25);
        Dimension buttonSize = new Dimension(170, 75);
        JFrame mainFrame = new JFrame();

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());
        formPanel.setBackground(new Color(79, 38, 130));

        // Create the welcome label
        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(mainFont);
        welcomeLabel.setForeground(new Color(255, 255, 255));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 0));
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        welcomePanel.setBackground(new Color(79, 38, 130));
        welcomePanel.add(welcomeLabel);

        // Create message label
        JLabel messageLabel = new JLabel("Please select the building you would like to browse.");
        messageLabel.setFont(new Font("Segoe print", Font.PLAIN, 16));
        messageLabel.setForeground(new Color(255, 255, 255));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(-3, 20, 0, 0));
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        messagePanel.setBackground(new Color(79, 38, 130));
        messagePanel.add(messageLabel);

        // Add the welcome and message panel to the topPanel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(79, 38, 130));
        topPanel.add(welcomePanel);
        topPanel.add(messagePanel);

        // Add campus picture for completion of frame
        BufferedImage campusImage;
        try {
            campusImage = ImageIO.read(getClass().getResource("images/westerncampus.jpg"));

            int newWidth = 490;
            int newHeight = 210;
            Image scaledImage = campusImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            JLabel campusLabel = new JLabel(imageIcon);
            campusLabel.setHorizontalAlignment(JLabel.CENTER);
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.setBackground(new Color(79, 38, 130));
            imagePanel.add(campusLabel, BorderLayout.CENTER);
            imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 60, 5, 0));
            formPanel.add(imagePanel, BorderLayout.EAST);
        } catch (IOException e) {
            System.out.println("Image not found.");
        }

        // Add the welcome and message panel to the north of the formPanel
        formPanel.add(topPanel, BorderLayout.NORTH);

        // Create three buttons for building selection
        // Align them down the middle
        JButton button1 = new JButton("Middlesex College");
        button1.setPreferredSize(buttonSize);
        button1.setMaximumSize(buttonSize);
        button1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton button2 = new JButton("Western Science Centre");
        button2.setPreferredSize(buttonSize);
        button2.setMaximumSize(buttonSize);
        button2.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton button3 = new JButton("Talbot College");
        button3.setPreferredSize(buttonSize);
        button3.setMaximumSize(buttonSize);
        button3.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add ActionListener to each button
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if Middlesex button clicked, display the map
                currentBuilding = "Middlesex College";
                displayMC("images/mcgf.png");
                mainFrame.dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if Middlesex button clicked, display the map
                currentBuilding = "Western Science Centre";
                displayWSC("images/wscgf.png");
                mainFrame.dispose();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentBuilding = "Talbot College";
                displayTC("images/tc1.png");
                mainFrame.dispose();
            }
        });

        // Create a panel for the buttons with a GridLayout that has one column
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(79, 38, 130));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button1);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(button2);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(button3);
        buttonPanel.add(Box.createVerticalGlue());

        // Add the button panel to the center of the formPanel
        formPanel.add(buttonPanel, BorderLayout.CENTER);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(79, 38, 130));
        mainPanel.add(formPanel, BorderLayout.LINE_START);

        // Set up the JFrame
        mainFrame.add(mainPanel);
        mainFrame.setTitle("Building Selection");
        mainFrame.setSize(800, 400);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    /**
     * Display Middlesex College floor plan and call from any functionality methods outside of the Maps class when needed
     * @param floor, string of the floor to be displayed
     */
    public void displayMC(String floor) {
        Font mainFont = new Font("Segoe print", Font.PLAIN, 12);
        Dimension buttonSize = new Dimension(100, 25);

        JFrame mainFrame = new JFrame("Middlesex College");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose current window instead of closing entire
                                                                     // program

        String[] floorName = floor.split("/");

        try {
            MapPane mapPane = new MapPane(floor);

            // loop for poi hashmaps
            // 1: admin hash
            for (int i = 0; i < getAdminPOIHashMap().size(); i++) {
                Map<String, String[]> adminMap = getAdminPOIHashMap();
                String intStr = String.valueOf(i);
                String[] valArr = adminMap.get(intStr);

                if (!valArr[0].equals(floorName[1])) {
                    continue;
                } else {
                    String name = valArr[6];
                    String description = valArr[7];
                    String num = valArr[8];
                    int xVal = Integer.parseInt(valArr[1]);
                    int yVal = Integer.parseInt(valArr[2]);
                    POIMarker poi = new POIMarker(description, name, num);
                    poi.setSize(poi.getPreferredSize());
                    poi.setLocation(xVal, yVal);
                    poi.setType(valArr[5]);
                    poi.setUserType(userName);
                    poiObjectList.add(poi);
                    mapPane.add(poi);
                }
            }

            //add the user POI's
            if (!userName.equals("admin")) {
                for (int i = 0; i < getUserPOIHashMap().size(); i++) {
                    Map<String, String[]> userMap = getUserPOIHashMap();
                    String intStr = String.valueOf(i);
                    String[] valArr = userMap.get(intStr);
    
                    if (!valArr[0].equals(floorName[1])) {
                        continue;
                    } 
                    else {
                        String name = valArr[6];
                        String description = valArr[7];
                        String num = valArr[8];
                        int xVal = Integer.parseInt(valArr[1]);
                        int yVal = Integer.parseInt(valArr[2]);
                        POIMarker userPOI = new POIMarker(description, name, num);
                        userPOI.setSize(userPOI.getPreferredSize());
                        userPOI.setLocation(xVal, yVal);
                        userPOI.setType(valArr[5]);
                        userPOI.setUserType(userName);
                        poiObjectList.add(userPOI);
                        mapPane.add(userPOI);
                    }
                }   
            }

            mainFrame.add(mapPane);
            scrollPane = new JScrollPane(mapPane);
            scrollPane.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX() + scrollPane.getHorizontalScrollBar().getValue();
                    int y = e.getY() + scrollPane.getVerticalScrollBar().getValue();
                    System.out.println("Clicked at (" + x + ", " + y + ")");
                    int result = JOptionPane.showConfirmDialog(scrollPane, "Place a new POI here?", "Create a POI",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        String xVal = Integer.toString(x);
                        String yVal = Integer.toString(y);
                        newUserPOI m = new newUserPOI();
                        m.initialize(xVal, yVal, floorName[1]);
                        mainFrame.dispose();
                    }
                }
            });
    
            mainFrame.add(scrollPane);
        } 
        catch (Exception e) {
            System.out.println("Image not found.");
        }

        // Set up back button to return to previous window
        JButton backButton = new JButton("Back");
        backButton.setFont(mainFont);
        backButton.setPreferredSize(buttonSize);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                buildingSelect(); // Allow user to select a different building
            }
        });

        // Set up changes layers button to allow user to display different pois
        JButton changeLayersBtn = new JButton("Change Layers");
        changeLayersBtn.setFont(mainFont);
        changeLayersBtn.setPreferredSize(new Dimension(150, 30));
        changeLayersBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        changeLayersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Layers l = new Layers(poiObjectList);
                l.changeLayer();
            }
        });

        // Button to display the about page
        JButton aboutBtn = new JButton("About");
        aboutBtn.setFont(mainFont);
        aboutBtn.setPreferredSize(new Dimension(150, 30));
        aboutBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutScreen();
            }
        });

        // Button to display the help pdf file
        JButton helpBtn = new JButton("Help");
        helpBtn.setFont(mainFont);
        helpBtn.setPreferredSize(new Dimension(150, 30));
        helpBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // File path of the PDF file
                String filePath = "officialhelpguide.pdf";
                
                // Open the PDF file with the default PDF viewer
                try {
                    File file = new File(filePath);
                    if (file.exists()) {
                        // On Windows
                        if (System.getProperty("os.name").toLowerCase().contains("win")) {
                            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", filePath);
                            processBuilder.start();
                        }
                        // On macOS
                        else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                            ProcessBuilder processBuilder = new ProcessBuilder("open", filePath);
                            processBuilder.start();
                        }
                        // On Linux
                        else if (System.getProperty("os.name").toLowerCase().contains("nix") ||
                                System.getProperty("os.name").toLowerCase().contains("nux")) {
                            ProcessBuilder processBuilder = new ProcessBuilder("xdg-open", filePath);
                            processBuilder.start();
                        }
                    } else {
                        throw new IOException("File not found: " + filePath);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Failed to open PDF file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Set up drop down for selecting floorss
        JComboBox<String> floorMenu = new JComboBox<>();
        floorMenu.addItem("Ground Floor");
        floorMenu.addItem("1st Floor");
        floorMenu.addItem("2nd Floor");
        floorMenu.addItem("3rd Floor");
        floorMenu.addItem("4th Floor");
        floorMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFloor = (String) floorMenu.getSelectedItem();
                changeFloor(mcDict.get(selectedFloor));
                floorMenu.setSelectedItem(floorMenu.getSelectedItem());
                mainFrame.dispose();
            }
        });

        ArrayList<String[]> POIList = listofPOIs(userName);

        // Create a new button for opening the Search panel
        JButton searchButton = new JButton("Open Search Menu");
        searchButton.setPreferredSize(new Dimension(150, 30));
        // Add an action listener to the button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(POIList, floorName[1], userName);
                mainFrame.dispose();
            }
        });
        // Set up frame
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(Box.createHorizontalGlue()); // Add glue to push button to the left
        menuPanel.add(backButton);
        menuPanel.add(floorMenu);
        menuPanel.add(searchButton);
        menuPanel.add(changeLayersBtn);
        menuPanel.add(aboutBtn);
        menuPanel.add(helpBtn);
        mainFrame.getContentPane().add(menuPanel, BorderLayout.NORTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     * Display Western Science Centre's floor plan and call from any functionality methods outside of the Maps class when needed
     * @param floor, string of the floor to be displayed
     */
    public void displayWSC(String floor) {
        Font mainFont = new Font("Segoe print", Font.PLAIN, 12);
        Dimension buttonSize = new Dimension(100, 25);

        JFrame mainFrame = new JFrame("Western Science Centre");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose current window instead of closing entire
                                                                     // program

        String[] floorName = floor.split("/");

        try {
            MapPane mapPane = new MapPane(floor);

            // loop for poi hashmaps
            // 1: admin hash
            for (int i = 0; i < getAdminPOIHashMap().size(); i++) {
                Map<String, String[]> adminMap = getAdminPOIHashMap();
                String intStr = String.valueOf(i);
                String[] valArr = adminMap.get(intStr);

                if (!valArr[0].equals(floorName[1])) {
                    continue;
                } else {
                    String name = valArr[6];
                    String description = valArr[7];
                    String num = valArr[8];
                    int xVal = Integer.parseInt(valArr[1]);
                    int yVal = Integer.parseInt(valArr[2]);
                    POIMarker poi = new POIMarker(description, name, num);
                    poi.setSize(poi.getPreferredSize());
                    poi.setLocation(xVal, yVal);
                    poi.setType(valArr[5]);
                    poi.setUserType(userName);
                    poiObjectList.add(poi);
                    mapPane.add(poi);
                }
            }

            //add the user POI's
            if (!userName.equals("admin")) {
                for (int i = 0; i < getUserPOIHashMap().size(); i++) {
                    Map<String, String[]> userMap = getUserPOIHashMap();
                    String intStr = String.valueOf(i);
                    String[] valArr = userMap.get(intStr);
    
                    if (!valArr[0].equals(floorName[1])) {
                        continue;
                    } 
                    else {
                        String name = valArr[6];
                        String description = valArr[7];
                        String num = valArr[8];
                        int xVal = Integer.parseInt(valArr[1]);
                        int yVal = Integer.parseInt(valArr[2]);
                        POIMarker userPOI = new POIMarker(description, name, num);
                        userPOI.setSize(userPOI.getPreferredSize());
                        userPOI.setLocation(xVal, yVal);
                        userPOI.setType(valArr[5]);
                        userPOI.setUserType(userName);
                        poiObjectList.add(userPOI);
                        mapPane.add(userPOI);
                    }
                }   
            }

            mainFrame.add(mapPane);
            scrollPane = new JScrollPane(mapPane);
            scrollPane.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX() + scrollPane.getHorizontalScrollBar().getValue();
                    int y = e.getY() + scrollPane.getVerticalScrollBar().getValue();
                    System.out.println("Clicked at (" + x + ", " + y + ")");
                    int result = JOptionPane.showConfirmDialog(scrollPane, "Place a new POI here?", "Create a POI",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        String xVal = Integer.toString(x);
                        String yVal = Integer.toString(y);
                        newUserPOI m = new newUserPOI();
                        m.initialize(xVal, yVal, floorName[1]);
                        mainFrame.dispose();
                    }
                }
            });
    
            mainFrame.add(scrollPane);
        } catch (Exception e) {
            System.out.println("Image not found.");
        }

        // Set up back button to return to previous window
        JButton backButton = new JButton("Back");
        backButton.setFont(mainFont);
        backButton.setPreferredSize(buttonSize);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                buildingSelect(); // Allow user to select a different building
            }
        });

        // Set up changes layers button to allow user to display different pois
        JButton changeLayersBtn = new JButton("Change Layers");
        changeLayersBtn.setFont(mainFont);
        changeLayersBtn.setPreferredSize(new Dimension(150, 30));
        changeLayersBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        changeLayersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Layers l = new Layers(poiObjectList);
                l.changeLayer();
            }
        });

        // Set up changes layers button to allow user to display different pois
        JButton aboutBtn = new JButton("About");
        aboutBtn.setFont(mainFont);
        aboutBtn.setPreferredSize(new Dimension(150, 30));
        aboutBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutScreen();
            }
        });

        // Button to display the help pdf file
        JButton helpBtn = new JButton("Help");
        helpBtn.setFont(mainFont);
        helpBtn.setPreferredSize(new Dimension(150, 30));
        helpBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // File path of the PDF file
                String filePath = "officialhelpguide.pdf";
                
                // Open the PDF file with the default PDF viewer
                try {
                    File file = new File(filePath);
                    if (file.exists()) {
                        // On Windows
                        if (System.getProperty("os.name").toLowerCase().contains("win")) {
                            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", filePath);
                            processBuilder.start();
                        }
                        // On macOS
                        else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                            ProcessBuilder processBuilder = new ProcessBuilder("open", filePath);
                            processBuilder.start();
                        }
                        // On Linux
                        else if (System.getProperty("os.name").toLowerCase().contains("nix") ||
                                System.getProperty("os.name").toLowerCase().contains("nux")) {
                            ProcessBuilder processBuilder = new ProcessBuilder("xdg-open", filePath);
                            processBuilder.start();
                        }
                    } else {
                        throw new IOException("File not found: " + filePath);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Failed to open PDF file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set up drop down for selecting floors
        JComboBox<String> floorMenu = new JComboBox<>();
        floorMenu.addItem("Ground Floor");
        floorMenu.addItem("1st Floor");
        floorMenu.addItem("2nd Floor");
        floorMenu.addItem("3rd Floor");
        floorMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFloor = (String) floorMenu.getSelectedItem();
                changeFloor(wscDict.get(selectedFloor));
                floorMenu.setSelectedItem(selectedFloor);
                mainFrame.dispose();
            }
        });

        ArrayList<String[]> POIList = listofPOIs(userName);

        // Create a new button for opening the Search panel
        JButton searchButton = new JButton("Open Search Menu");
        searchButton.setPreferredSize(new Dimension(150, 30));
        // Add an action listener to the button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(POIList, floorName[1], userName);
                mainFrame.dispose();
            }
        });

        // Set up frame
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(Box.createHorizontalGlue()); // Add glue to push button to the left
        menuPanel.add(backButton);
        menuPanel.add(floorMenu);
        menuPanel.add(searchButton);
        menuPanel.add(changeLayersBtn);
        menuPanel.add(aboutBtn);
        menuPanel.add(helpBtn);
        mainFrame.getContentPane().add(menuPanel, BorderLayout.NORTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     * Display Talbot College's floor plan and call from any functionality methods outside of the Maps class when needed
     * @param floor, string of the floor to be displayed
     */
    public void displayTC(String floor) {
        Font mainFont = new Font("Segoe print", Font.PLAIN, 12);
        Dimension buttonSize = new Dimension(100, 25);

        JFrame mainFrame = new JFrame("Talbot College");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose current window instead of closing entire
                                                                     // program

        String[] floorName = floor.split("/");

        try {
            MapPane mapPane = new MapPane(floor);

            // loop for poi hashmaps
            // 1: admin hash
            for (int i = 0; i < getAdminPOIHashMap().size(); i++) {
                Map<String, String[]> adminMap = getAdminPOIHashMap();
                String intStr = String.valueOf(i);
                String[] valArr = adminMap.get(intStr);

                if (!valArr[0].equals(floorName[1])) {
                    continue;
                } else {
                    String name = valArr[6];
                    String description = valArr[7];
                    String num = valArr[8];
                    int xVal = Integer.parseInt(valArr[1]);
                    int yVal = Integer.parseInt(valArr[2]);
                    POIMarker poi = new POIMarker(description, name, num);
                    poi.setSize(poi.getPreferredSize());
                    poi.setLocation(xVal, yVal);
                    poi.setType(valArr[5]);
                    poi.setUserType(userName);
                    poiObjectList.add(poi);
                    mapPane.add(poi);
                }
            }

            //add the user POI's
            if (!userName.equals("admin")) {
                for (int i = 0; i < getUserPOIHashMap().size(); i++) {
                    Map<String, String[]> userMap = getUserPOIHashMap();
                    String intStr = String.valueOf(i);
                    String[] valArr = userMap.get(intStr);
    
                    if (!valArr[0].equals(floorName[1])) {
                        continue;
                    } 
                    else {
                        String name = valArr[6];
                        String description = valArr[7];
                        String num = valArr[8];
                        int xVal = Integer.parseInt(valArr[1]);
                        int yVal = Integer.parseInt(valArr[2]);
                        POIMarker userPOI = new POIMarker(description, name, num);
                        userPOI.setSize(userPOI.getPreferredSize());
                        userPOI.setLocation(xVal, yVal);
                        userPOI.setType(valArr[5]);
                        userPOI.setUserType(userName);
                        poiObjectList.add(userPOI);
                        mapPane.add(userPOI);
                    }
                }   
            }

            mainFrame.add(mapPane);
            scrollPane = new JScrollPane(mapPane);
            scrollPane.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX() + scrollPane.getHorizontalScrollBar().getValue();
                    int y = e.getY() + scrollPane.getVerticalScrollBar().getValue();
                    System.out.println("Clicked at (" + x + ", " + y + ")");
                    int result = JOptionPane.showConfirmDialog(scrollPane, "Place a new POI here?", "Create a POI",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        String xVal = Integer.toString(x);
                        String yVal = Integer.toString(y);
                        newUserPOI m = new newUserPOI();
                        m.initialize(xVal, yVal, floorName[1]);
                        mainFrame.dispose();
                    }
                }
            });
    
            mainFrame.add(scrollPane);
        } 
        catch (Exception e) {
            System.out.println("Image not found.");
        }

        // Set up back button to return to previous window
        JButton backButton = new JButton("Back");
        backButton.setFont(mainFont);
        backButton.setPreferredSize(buttonSize);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                buildingSelect(); // Allow user to select a different building
            }
        });

        // Set up changes layers button to allow user to display different pois
        JButton changeLayersBtn = new JButton("Change Layers");
        changeLayersBtn.setFont(mainFont);
        changeLayersBtn.setPreferredSize(new Dimension(150, 30));
        changeLayersBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        changeLayersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Layers l = new Layers(poiObjectList);
                l.changeLayer();
            }
        });

        // Set up changes layers button to allow user to display different pois
        JButton aboutBtn = new JButton("About");
        aboutBtn.setFont(mainFont);
        aboutBtn.setPreferredSize(new Dimension(150, 30));
        aboutBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutScreen();
            }
        });

        // Button to display the help pdf file
        JButton helpBtn = new JButton("Help");
        helpBtn.setFont(mainFont);
        helpBtn.setPreferredSize(new Dimension(150, 30));
        helpBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // File path of the PDF file
                String filePath = "officialhelpguide.pdf";
                
                // Open the PDF file with the default PDF viewer
                try {
                    File file = new File(filePath);
                    if (file.exists()) {
                        // On Windows
                        if (System.getProperty("os.name").toLowerCase().contains("win")) {
                            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", filePath);
                            processBuilder.start();
                        }
                        // On macOS
                        else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                            ProcessBuilder processBuilder = new ProcessBuilder("open", filePath);
                            processBuilder.start();
                        }
                        // On Linux
                        else if (System.getProperty("os.name").toLowerCase().contains("nix") ||
                                System.getProperty("os.name").toLowerCase().contains("nux")) {
                            ProcessBuilder processBuilder = new ProcessBuilder("xdg-open", filePath);
                            processBuilder.start();
                        }
                    } else {
                        throw new IOException("File not found: " + filePath);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Failed to open PDF file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set up drop down for selecting floors
        JComboBox<String> floorMenu = new JComboBox<>();
        floorMenu.addItem("1st Floor");
        floorMenu.addItem("2nd Floor");
        floorMenu.addItem("3rd Floor");
        floorMenu.addItem("4th Floor");
        floorMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFloor = (String) floorMenu.getSelectedItem();
                changeFloor(tcDict.get(selectedFloor));
                floorMenu.setSelectedItem(selectedFloor);
                mainFrame.dispose();
            }
        });
        ArrayList<String[]> POIList = listofPOIs(userName);

        // Create a new button for opening the Search panel
        JButton searchButton = new JButton("Open Search Menu");
        searchButton.setPreferredSize(new Dimension(150, 30));
        // Add an action listener to the button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(POIList, floorName[1], userName);
                mainFrame.dispose();
            }
        });

        // Set up frame
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(Box.createHorizontalGlue()); // Add glue to push button to the left
        menuPanel.add(backButton);
        menuPanel.add(floorMenu);
        menuPanel.add(searchButton);
        menuPanel.add(changeLayersBtn);
        menuPanel.add(aboutBtn);
        menuPanel.add(helpBtn);
        mainFrame.getContentPane().add(menuPanel, BorderLayout.NORTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     * Changes floor of the building
     * @param floor, string of the floor to change to
     */
    public void changeFloor(String floor) {
        if (currentBuilding.equals("Middlesex College")) {
            displayMC(floor);
        } 
        else if (currentBuilding.equals("Western Science Centre")) {
            displayWSC(floor);
        } 
        else {
            displayTC(floor);
        }
    }

    /**
     * Returns a dictionary containing the POIs for the current user by reading their json file
     * @return dictionary, or null if it is empty
     */
    private Map<String, String[]> getUserPOIHashMap() {
        String filename = this.userName + ".json";
        try {
            // file contents into a byte array
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            // byte array to string with UTF-8 encoding
            String jsonString = new String(bytes, "UTF-8");
            // JSON string to Map with org.json library
            JSONObject jsonObject = new JSONObject(jsonString);
            Map<String, String[]> dictionary = new HashMap<>();
            for (String key : jsonObject.keySet()) {
                JSONArray jsonArray = jsonObject.getJSONArray(key);
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

    /**
     * Returns a dictionary containing the POIs for the Admin by reading the admin.json file
     * @return dictionary, or null if it is empty
     */
    private Map<String, String[]> getAdminPOIHashMap() {
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
                JSONArray jsonArray = jsonObject.getJSONArray(key);
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

    /**
     * Creates an ArrayList of all POIs on all Maps, does not allow duplicates
     * @param userName, takes in the current user's username
     * @return ArrayList listofPOIs, ArrayList containing all POIs (both built in and not) for the user
     */
    public ArrayList<String[]> listofPOIs(String userName) {
        ArrayList<String[]> listofPOIs = new ArrayList<>();

        for (int i = 1; i < getAdminPOIHashMap().size(); i++) {
            Map<String, String[]> adminMap = getAdminPOIHashMap();
            String intStr = String.valueOf(i);
            String[] valArr = adminMap.get(intStr);
            listofPOIs.add(valArr);
        }
        if (!userName.equals("admin")) {
            for (int i = 1; i < getUserPOIHashMap().size(); i++) {
                Map<String, String[]> userMap = getUserPOIHashMap();
                String intStr = String.valueOf(i);
                String[] val2Arr = userMap.get(intStr);
                // check to see if it is a duplicate before adding to the list
                if (i < listofPOIs.size()) {
                    if (!(listofPOIs.get(i)[3].equals(val2Arr[3]) && listofPOIs.get(i)[7].equals(val2Arr[7]))) {
                        listofPOIs.add(val2Arr);   
                    }
                    else {
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
        }
        return listofPOIs;
    }
}