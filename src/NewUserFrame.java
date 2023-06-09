import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * GUI for new user frame, provides option to create a new user
 * @author Andrea Jackson
 * @author Azam Jawad Butt
 */
public class NewUserFrame extends JFrame {
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18); // Create font type, to be used for text in
                                                                          // project

    JTextField UsernameInput, PasswordInput; // Declare variables for username and password tex input

    /**
     * Initializes the GUI and functionality required to create a new User.
     */
    public void initialize() {

        // Initialize frame to display
        JFrame mainFrame = new JFrame();

        // Form Panel
        JPanel formPanel = new JPanel(); // Create new instance of JPanel class
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        formPanel.setBackground(new Color(79, 38, 130)); // Set background colour to Western University purple
        c.weightx = 1;

        JLabel lbUsername = new JLabel("Enter New Username: "); // Create new instance of JLabel class, for username
                                                                // label
        lbUsername.setFont(mainFont);
        lbUsername.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST; // Set component to right side of its cell
        c.insets = new Insets(0, 0, 0, 10); // Add slight padding to the right
        formPanel.add(lbUsername, c);

        UsernameInput = new JTextField(15); // Create instance of JTextField class, for username input
        UsernameInput.setFont(mainFont);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST; // Set component to left side of its cell
        formPanel.add(UsernameInput, c);

        JLabel lbPassword = new JLabel("Enter New Password: ");
        lbPassword.setFont(mainFont);
        lbPassword.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST; // Set component to right side of its cell
        c.insets = new Insets(0, 0, 0, 10); // Add slight padding to the right
        formPanel.add(lbPassword, c);

        PasswordInput = new JPasswordField(15); // Create instance of JTextField class, for Password input
        PasswordInput.setFont(mainFont);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST; // Set component to left side of its cell
        formPanel.add(PasswordInput, c);

        // Create Button
        JButton btnCreate = new JButton("Create"); // Create new JButton instance for login button
        btnCreate.setFont(mainFont);
        btnCreate.setPreferredSize(new Dimension(150, 40)); // Set button size
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = UsernameInput.getText();
                String newPassword = PasswordInput.getText();

                String filename = "users.json";
                try {
                    // Read existing data from file
                    String jsonString = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
                    JSONObject jsonObject = new JSONObject(jsonString);

                    // Add new data to JSONObject
                    jsonObject.put(newUsername, newPassword);

                    // Write updated data back to file
                    Files.write(Paths.get(filename), jsonObject.toString().getBytes(StandardCharsets.UTF_8));

                    // Create user specific json file for POIs
                    JSONObject jsonObj = new JSONObject();
                    String fileName = newUsername + ".json";
                    // Add new data to JSONObject
                    JSONArray POIarr = new JSONArray();
                    POIarr.put("0");
                    POIarr.put("0");
                    POIarr.put("0");
                    POIarr.put("0");
                    POIarr.put("0");
                    POIarr.put("0");
                    POIarr.put("0");
                    POIarr.put("0");
                    POIarr.put("0");

                    jsonObj.put("0", POIarr);
                    try (FileWriter fileWriter = new FileWriter(fileName)) {
                        fileWriter.write(jsonObj.toString());
                        System.out.println("Successfully created empty JSON file: " + fileName);
                    } catch (IOException error) {
                        System.out.println("An error occurred while creating the JSON file: " + fileName);
                        error.printStackTrace();
                    }
                } catch (IOException error) {
                    System.out.println("Error: " + error.getMessage());
                }
            }
        });
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 0, 0, 0); // Add slight padding to the top
        formPanel.add(btnCreate, c);

        // Back to login Button
        JButton btnLogin = new JButton("Done"); // Create new JButton instance for login button
        btnLogin.setFont(mainFont);
        btnLogin.setPreferredSize(new Dimension(150, 40)); // Set button size
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose(); // When new user created, dispose current frame and return to login menu
                LoginFrame temp = new LoginFrame(); // create new login menu to return to after new user is created
                temp.initialize();
            }
        });
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 0, 0, 0); // Add slight padding to the top
        formPanel.add(btnLogin, c);

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
        mainFrame.setTitle("Login to the UWO Campus Map!");
        mainFrame.setSize(800, 400); // Set dimensions for screen
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Exit application when frame is closed
        mainFrame.setLocationRelativeTo(null); // Center the window on the screen
        mainFrame.setVisible(true);

    }
}
