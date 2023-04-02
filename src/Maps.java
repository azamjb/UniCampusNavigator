
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Maps extends JFrame {

    // File names for all the floor
    Map<String, String> mcDict = Map.of("Ground Floor", "mc1.png", "1st Floor", "mc1.png", "2nd Floor", "mc2.png", "3rd Floor", "mc3.png", "4th Floor", "mc4.png");
    Map<String, String> wscDict = Map.of("Ground Floor", "wscgf.png", "1st Floor", "wsc1.png", "2nd Floor", "wsc2.png", "3rd Floor", "wsc3.png");
    Map<String, String> tcDict = Map.of("1st Floor", "tc1.png", "2nd Floor", "tc2.png", "3rd Floor", "tc3.png", "4th Floor", "tc4.png");
    String currentBuilding = "";

    
    // Constructor to prompt user for to select building choice
    public Maps(){
        buildingSelect();
    }

    // Prompt the user to select between three buildings to navigate through floor plan
    public void buildingSelect(){

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
        // FIX ME
        

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
                displayMC("mcgf.png");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if Middlesex button clicked, display the map
                currentBuilding = "Western Science Centre";
                displayWSC("wscgf.png");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            currentBuilding = "Talbot College";
            displayTC("tc1.png");
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

    
    // Display middlesex floor plan -- https://www.youtube.com/watch?v=ntirmRhy6Fw&ab_channel=HussainAljafer
    public void displayMC(String floor){

        Font mainFont = new Font("Segoe print", Font.PLAIN, 12); 
        Dimension buttonSize = new Dimension(100, 25);

         
        JFrame mainFrame = new JFrame("Middlesex College");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose current window instead of closing entire program

        try {
            ImageIcon image = new ImageIcon(getClass().getResource(floor));
            JLabel display = new JLabel(image);

            // Allow for scrolling since floor image is too large
            JScrollPane scrollPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            mainFrame.getContentPane().add(scrollPane);

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

        
        // Set up drop down for selecting floors
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

    public void displayWSC(String floor){
        Font mainFont = new Font("Segoe print", Font.PLAIN, 12); 
        Dimension buttonSize = new Dimension(100, 25);

         
        JFrame mainFrame = new JFrame("Western Science Centre");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose current window instead of closing entire program

        try {
            ImageIcon image = new ImageIcon(getClass().getResource(floor));
            JLabel display = new JLabel(image);

            // Allow for scrolling since floor image is too large
            JScrollPane scrollPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            mainFrame.getContentPane().add(scrollPane);

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

    public void displayTC(String floor){
        Font mainFont = new Font("Segoe print", Font.PLAIN, 12); 
        Dimension buttonSize = new Dimension(100, 25);

         
        JFrame mainFrame = new JFrame("Talbot College");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose current window instead of closing entire program

        try {
            ImageIcon image = new ImageIcon(getClass().getResource(floor));
            JLabel display = new JLabel(image);

            // Allow for scrolling since floor image is too large
            JScrollPane scrollPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            mainFrame.getContentPane().add(scrollPane);

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
    public void changeFloor(String floor){
        if (currentBuilding.equals("Middlesex College")){
            displayMC(floor);
        }
        else if (currentBuilding.equals("Western Science Centre")){
            displayWSC(floor);
        }
        else{
            displayTC(floor);
        }
    }

    public static void main(String[] args){
        Maps m = new Maps();
    }


    
}
