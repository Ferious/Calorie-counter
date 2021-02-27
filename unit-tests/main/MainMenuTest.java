import org.junit.jupiter.api.Test;
import users.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author - annonymous
 * @class - this is test class for testing the whole functionality of class MainMenu
 */

class MainMenuTest {

    @Test
    void signAsClient() {

    }

    @Test
    void registerAsNewClient() {
        String loginName = "Janka";
        try {
            User user = new User();
            user.setLoginName(loginName);
        } catch (Exception e) {
            assertEquals(String.format("User with this username [%s] already exists!", loginName),e.getMessage());
        }
    }

    @Test
    void registerAsNewClient1() {
        try {
            User user = new User();
            String loginName = "Joz#ko";
            user.setLoginName(loginName);
            String firstName = "AA22";
            user.setFirstName(firstName);
        } catch (Exception e) {
            assertEquals("Incorrect login name!",e.getMessage());
        }
    }

    @Test
    void registerAsNewClient2() {
        try {
            User user = new User();
            user.setFirstName("AA22");
        } catch (Exception e) {
            assertEquals("Name can contains only alphabetical characters!",e.getMessage());
        }
    }

    @Test
    void registerAsNewClient3() {
        try {
            User user = new User();
            user.setLastName("AA22");
        } catch (Exception e) {
            assertEquals("Name can contains only alphabetical characters!",e.getMessage());
        }
    }

    @Test
    void registerAsNewClient4() {
        try {
            User user = new User();
            user.setAge("AA22");
        } catch (Exception e) {
            assertEquals("Age can contains only numeric characters!",e.getMessage());
        }
    }

}