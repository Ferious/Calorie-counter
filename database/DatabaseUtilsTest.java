package database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author - annonymous
 * @class - this is test class for testing the whole functionality of class DatabaseUtilsTest
 */

class DatabaseUtilsTest {

    @Test
    void checkIfUserNameExist() {
        String user = "Mohamed";
        boolean exist = DatabaseUtils.checkIfUserNameExist(user);
        assertFalse(exist);
    }

    @Test
    void logInUser() {
        String name = "ali ";
        String password = "express ";
        assertFalse(DatabaseUtils.logInUser(name, password));
        String name2 = "Janka";
        String password2 = "vesela";
        assertTrue(DatabaseUtils.logInUser(name2, password2));
    }

}