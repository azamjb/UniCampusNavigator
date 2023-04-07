/**
 * @author Andrea Jackson
 * J Unit testing for LoginFrame
 */


import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class LoginFrameTest {
    
    @Test
    public void testInitialize() {
        /**
         * Testing the functionality of the login frame
         */
        System.out.println("Login Function:");
        try {
            LoginFrame myFrame = new LoginFrame(); // Create new instance of LoginFrame class
            myFrame.initialize();
        } catch (Exception e) {
            fail("Login Fail");
        }
    }

    @Test
    public void testGetUserStr() {
        /**
         * Testing the retention of the logged in userName with admin user
         */
        System.out.println("UserName");
        LoginFrame myFrame = new LoginFrame(); // Create new instance of LoginFrame class
        myFrame.initialize();

        myFrame.userNameStr = "admin";
        String result = myFrame.getUserStr();

        String expResult = ("admin");
        assertEquals("Test Failed", expResult, result);

    }

}

