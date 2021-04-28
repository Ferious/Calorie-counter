package users;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author - teams annonymous
 * @class - this is test class for testing user during registration process
 */

class UserTest {

    @Test
    void setLoginName() {
        String loginName = "###$R#$R$#%#%#R#EWEFDº";
        try {
            User user = new User();
            user.setLoginName(loginName);
        } catch (Exception e) {
            assertEquals("Incorrect login name!", e.getMessage());
        }
    }

    @Test
    void setLoginName2() throws Exception {
        String loginName = "user123";
        User user = new User();
        assertTrue(user.setLoginName(loginName));
    }

    @Test
    void setFirstName() {
        String firstName = "Frantisek22";
        try {
            User user = new User();
            user.setFirstName(firstName);
        } catch (Exception e) {
            assertEquals("Name can contains only alphabetical characters!", e.getMessage());
        }
    }

    @Test
    void setFirstName2() throws Exception {
        String firstName = "Frantisek";
        User user = new User();
        assertTrue(user.setFirstName(firstName));
    }

    @Test
    void setLastName() {
        String lastName = "velesy24";
        try {
            User user = new User();
            user.setLastName(lastName);
        } catch (Exception e) {
            assertEquals("Name can contains only alphabetical characters!", e.getMessage());
        }
    }

    @Test
    void setLastName2() throws Exception {
        String lastName = "Vesely";
        User user = new User();
        assertTrue(user.setLastName(lastName));
    }

    @Test
    void setAge() {
        String age = "nie44";
        try {
            User user = new User();
            user.setAge(age);
        } catch (Exception e) {
            assertEquals("Age can contains only numeric characters!", e.getMessage());
        }
    }

    @Test
    void setAge2() throws Exception {
        String age = "23";
        User user = new User();
        assertTrue(user.setAge(age));
    }

    @Test
    void setPassword() {
        String loginName = "jozko";
        String password1 = "n$$$####@@@@@@@@e44";
        String password2 = " kfsdk ";
        try {
            User user = new User();
            user.setLoginName(loginName);
            user.setPassword(password1, password2);
        } catch (Exception e) {
            assertEquals(String.format("%s your password not equals!", loginName), e.getMessage());
        }
    }

    @Test
    void setPassword2() throws Exception {
        String password1 = "ahoj";
        String password2 = "ahoj";
        User user = new User();
        assertTrue(user.setPassword(password1, password2));
    }

    @Test
    void setGender() {
        String gender = "lie";
        try {
            User user = new User();
            user.setGender(gender);
        } catch (Exception e) {
            assertEquals("Gender was not set correctly!", e.getMessage());
        }
    }

    @Test
    void setGender2() throws Exception {
        String gender = "Man";
        User user = new User();
        assertTrue(user.setGender(gender));
    }

    @Test
    void setHeight() {
        String height = "lie";
        try {
            User user = new User();
            user.setHeight(height);
        } catch (Exception e) {
            assertEquals("Height can contains only numeric characters!", e.getMessage());
        }
    }

    @Test
    void setHeight2() throws Exception {
        String height = "22";
        User user = new User();
        assertTrue(user.setHeight(height));
    }

    @Test
    void setWeight() {
        String weight = "lie";
        try {
            User user = new User();
            user.setWeight(weight);
        } catch (Exception e) {
            assertEquals("Weight can contains only numeric characters!", e.getMessage());
        }
    }

    @Test
    void setWeight2() throws Exception {
        String weight = "23";
        User user = new User();
        assertTrue(user.setWeight(weight));
    }
}