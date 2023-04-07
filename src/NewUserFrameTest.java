import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.json.JSONObject;
import org.junit.Test;

/**
 * J Unit testing for NewUserFrame
 * @author Andrea Jackson
 */
public class NewUserFrameTest {

    /**
     * Tests the initialize() function of the NewUserFrame class
     * @param None
     * @return void
     */
    @Test
    public void testInitialize() {
    System.out.println("Initialize New User Frame");
    // Test if the new user was added to users.json
    String filename = "users.json";
    int ogSize;
    int newSize;

    try {
        // Read existing data from file
        String jsonString = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(jsonString);
        // Size before adding user to users
        ogSize = jsonObject.length();

        // initializing frame
        NewUserFrame newUser = new NewUserFrame();
        newUser.initialize();

        // Read existing data from file
        String jsonString1 = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        JSONObject jsonObject1 = new JSONObject(jsonString1);
        // Size before adding user to users
        newSize = jsonObject1.length();

        // check if new user was added
        if (newSize > ogSize) {
        System.out.println("User added : Test Passed");
        } else {
        fail("User not added: Test Failed");
        }

        } catch (IOException error) {
            
        System.out.println("Issue reading users.json");
        }

    }
}