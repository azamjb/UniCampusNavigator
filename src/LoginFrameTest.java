import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * J Unit testing for LoginFrame
 * @author Andrea Jackson
 */
public class LoginFrameTest {
    
    /**
     * Testing the functionality of the login frame
     */
    @Test
    public void testInitialize() {
        System.out.println("Login Function:");
        try {
            LoginFrame myFrame = new LoginFrame(); // Create new instance of LoginFrame class
            myFrame.initialize();
        } catch (Exception e) {
            fail("Login Fail");
        }
    }

    /**
     * Testing the retention of the logged in userName with admin user
     */
    @Test
    public void testGetUserStr() {
        
        System.out.println("UserName");
        LoginFrame myFrame = new LoginFrame(); // Create new instance of LoginFrame class
        myFrame.initialize();

        myFrame.userNameStr = "admin";
        String result = myFrame.getUserStr();

        String expResult = ("admin");
        assertEquals("Test Failed", expResult, result);

    }

}

