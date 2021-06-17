import database.DatabaseUtils;
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
        String loginName = "ivet";
        String password = "ivet";
        boolean login = DatabaseUtils.logInUser(loginName, password, true);
        assertEquals(true, login);
        password = "iveeet";
        login = DatabaseUtils.logInUser(loginName, password, true);
        assertEquals(false, login);
    }

    @Test
    void signAsAdmin() {
        String loginName = "a";
        String password = "a";
        boolean login = DatabaseUtils.logInUser(loginName, password, false);
        assertEquals(true, login);
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
    void registerAsNewClientWithBadName() {
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
    void registerAsNewClientWithIncorrectName() {
        try {
            User user = new User();
            user.setFirstName("AA22");
        } catch (Exception e) {
            assertEquals("Name can contains only alphabetical characters!",e.getMessage());
        }
    }

    @Test
    void registerAsNewClientWithIncorrectAge() {
        try {
            User user = new User();
            String age = "ee44";
            user.setAge(age);
        } catch (Exception e) {
            assertEquals("Age can contains only numeric characters!", e.getMessage());
        }
    }


}