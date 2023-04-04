import java.awt.*;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Maps {
    // Json file name from username
    LoginFrame temp;
    String userName;

    // File names for all the floor
    Map<String, String> mcDict = Map.of("Ground Floor", "images/mc1.png", "1st Floor", "images/mc1.png", "2nd Floor",
            "images/mc2.png", "3rd Floor", "images/mc3.png", "4th Floor", "images/mc4.png");
    Map<String, String> wscDict = Map.of("Ground Floor", "images/wscgf.png", "1st Floor", "images/wsc1.png",
            "2nd Floor", "images/wsc2.png", "3rd Floor", "images/wsc3.png");
    Map<String, String> tcDict = Map.of("1st Floor", "images/tc1.png", "2nd Floor", "images/tc2.png", "3rd Floor",
            "images/tc3.png", "4th Floor", "images/tc4.png");
    String currentBuilding = "";

    // Constructor to prompt user for to select building choice
    public Maps() {
        buildingSelect();

        // Json file name from username initialization
        temp = new LoginFrame(); // create new login frame object to call the static username object
        userName = temp.getUserStr();
    }

    // Prompt the user to select between three buildings to navigate through floor
    // plan
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
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if Middlesex button clicked, display the map
                currentBuilding = "Western Science Centre";
                displayWSC("images/wscgf.png");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentBuilding = "Talbot College";
                displayTC("images/tc1.png");
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

    // Display middlesex floor plan
    public void displayMC(String floor) {

        Font mainFont = new Font("Segoe print", Font.PLAIN, 12);
        Dimension buttonSize = new Dimension(100, 25);

        JFrame mainFrame = new JFrame("Middlesex College");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose current window instead of closing entire
                                                                     // program

        try {
            MapPane mapPane = new MapPane(floor);
            POIMarker poi = new POIMarker();
            poi.setSize(poi.getPreferredSize());
            poi.setLocation(700, 500 - poi.getHeight());
            mapPane.add(poi);
            mainFrame.add(mapPane);
            JScrollPane scrollPane = new JScrollPane(mapPane);
            coordinates(scrollPane);
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

        // Set up drop down for selecting floors
        JComboBox<String> floorMenu = new JComboBox<>();
        Map<String, Integer> floors = Map.of("Ground Floor", 0, "1st Floor", 1, "2nd Floor", 2, "3rd Floor", 3,
                "4th Floor", 4);
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
                mainFrame.dispose();
            }
        });

        // Set up frame
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(Box.createHorizontalGlue()); // Add glue to push button to the left
        menuPanel.add(backButton);
        menuPanel.add(floorMenu);
        mainFrame.getContentPane().add(menuPanel, BorderLayout.NORTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void displayWSC(String floor) {
        Font mainFont = new Font("Segoe print", Font.PLAIN, 12);
        Dimension buttonSize = new Dimension(100, 25);

        JFrame mainFrame = new JFrame("Western Science Centre");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose current window instead of closing entire
                                                                     // program

        try {
            MapPane mapPane = new MapPane(floor);
            POIMarker poi = new POIMarker();
            poi.setSize(poi.getPreferredSize());
            poi.setLocation(700, 500 - poi.getHeight());
            mapPane.add(poi);
            mainFrame.add(mapPane);
            JScrollPane scrollPane = new JScrollPane(mapPane);
            coordinates(scrollPane);
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

        // Set up frame
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(Box.createHorizontalGlue()); // Add glue to push button to the left
        menuPanel.add(backButton);
        menuPanel.add(floorMenu);
        mainFrame.getContentPane().add(menuPanel, BorderLayout.NORTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void displayTC(String floor) {
        Font mainFont = new Font("Segoe print", Font.PLAIN, 12);
        Dimension buttonSize = new Dimension(100, 25);

        JFrame mainFrame = new JFrame("Talbot College");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose current window instead of closing entire
                                                                     // program

        try {
            MapPane mapPane = new MapPane(floor);
            POIMarker poi = new POIMarker();
            poi.setSize(poi.getPreferredSize());
            poi.setLocation(700, 500 - poi.getHeight());
            mapPane.add(poi);
            mainFrame.add(mapPane);
            JScrollPane scrollPane = new JScrollPane(mapPane);
            coordinates(scrollPane);
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

        // Set up frame
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(Box.createHorizontalGlue()); // Add glue to push button to the left
        menuPanel.add(backButton);
        menuPanel.add(floorMenu);
        mainFrame.getContentPane().add(menuPanel, BorderLayout.NORTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // Changes floor of the building
    public void changeFloor(String floor) {
        if (currentBuilding.equals("Middlesex College")) {
            displayMC(floor);
        } else if (currentBuilding.equals("Western Science Centre")) {
            displayWSC(floor);
        } else {
            displayTC(floor);
        }
    }

    // Get coordinates of where the POI's need to be placed
    public void coordinates(JScrollPane pane) {
        pane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() + pane.getHorizontalScrollBar().getValue();
                int y = e.getY() + pane.getVerticalScrollBar().getValue();
                System.out.println("Clicked at (" + x + ", " + y + ")");
            }
        });
    }

    private void writePOI(String jpgName, String xyVal, String floorNum, String POIType, String POIName,
            String description,
            String roomNumString) {
        // "jpg Name", "x.y", "building name", "floor num", "poi type/name",
        // "description", "room #"
        String filename = this.userName + ".json";
        try {
            // Read existing data from file
            String jsonString = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonString);

            // Add new data to JSONObject
            int keysCount = jsonObject.length();
            String key = Integer.toString(keysCount);
            JSONArray POIarr = new JSONArray();
            POIarr.put(jpgName);
            POIarr.put(xyVal);
            POIarr.put(floorNum);
            POIarr.put(POIType);
            POIarr.put(POIName);
            POIarr.put(description);
            POIarr.put(roomNumString);

            jsonObject.put(key, POIarr);

            // Write updated data back to file
            Files.write(Paths.get(filename), jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private Map<String, String[]> getPOIHashMap() {
        // turning json into a dictionary
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

    public static void main(String[] args) {
        Maps m = new Maps();
    }

}