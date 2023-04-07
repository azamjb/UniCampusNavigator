import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import org.json.JSONObject;

/**
 * GUI and login functionality for the initial Login Screen of the program
 * Includes options to login, or create new account
 * Once logged in, will lead to main page
 * @author Andrea Jackson
 * @author Azam Jawad Butt
 */
public class LoginFrame extends JFrame {

    public static String userNameStr;
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18); // Create font type, to be used for text in
                                                                          // project
    JTextField UsernameInput, PasswordInput; // Declare variables for username and password tex input

    /**
     * Functionality for initializing the program's start up screen
    */
    public void initialize() {
        JFrame mainFrame = new JFrame();
        userNameStr = " ";
        // Form Panel
        JPanel formPanel = new JPanel(); // Create new instance of JPanel class
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        formPanel.setBackground(new Color(79, 38, 130)); // Set background colour to Western University purple
        c.weightx = 1;

        JLabel lbUsername = new JLabel("Username: "); // Create new instance of JLabel class, for username label
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

        JLabel lbPassword = new JLabel("Password: ");
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

        // Login Button
        JButton btnLogin = new JButton("Login"); // Create new JButton instance for login button
        btnLogin.setFont(mainFont);
        btnLogin.setPreferredSize(new Dimension(150, 40)); // Set button size
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UsernameInput.getText();
                String password = PasswordInput.getText();

                // turning json into a dictionary
                String filename = "users.json";
                try {
                    // file contents into a byte array
                    byte[] bytes = Files.readAllBytes(Paths.get(filename));
                    // byte array to string with UTF-8 encoding
                    String jsonString = new String(bytes, "UTF-8");
                    // JSON string to Map with org.json library
                    JSONObject jsonObject = new JSONObject(jsonString);
                    Map<String, String> dictionary = new HashMap<>();
                    for (String key : jsonObject.keySet()) {
                        dictionary.put(key, jsonObject.getString(key));
                    }

                    // Use the dictionary to work login
                    if (dictionary.get(username).equals(password)) {
                        userNameStr = username;
                        new Maps();
                        mainFrame.dispose(); // if login is successful, proceed with the program
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException error) {
                    System.out.println("Error reading file: " + error.getMessage());
                }
            }
        });
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 0, 0, 0); // Add slight padding to the top
        formPanel.add(btnLogin, c);

        // New Account Button
        JButton btnNew = new JButton("New User"); // Create new JButton instance for login button
        btnNew.setFont(mainFont);
        btnNew.setPreferredSize(new Dimension(150, 40)); // Set button size
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewUserFrame newUser = new NewUserFrame();
                mainFrame.dispose();
                newUser.initialize();
            }
        });
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 0, 0, 0); // Add slight padding to the top
        formPanel.add(btnNew, c);

        // Main Panel
        JPanel mainPanel = new JPanel(); // Create new JPanel instance for main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.add(formPanel, BorderLayout.CENTER); // Add form panel to main panel, in center region

        // Logo Panel
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/westernlogo.jpg")); // Load Western Logo
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

    /**
     * Returns the user name of the user attempting to log in
     * @return String of the user's user name
    */
    public String getUserStr() {
        return userNameStr;
    }

    /**
     * Handles the execution of the program from the beginning
    */
    public static void main(String[] args) {
        LoginFrame myFrame = new LoginFrame(); // Create new instance of LoginFrame class
        myFrame.initialize(); // Initialize the LoginFrame instance
    }
}