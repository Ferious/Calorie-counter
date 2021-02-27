package users;

import database.DatabaseUtils;

/**
 * @author - team annonymous
 * @class - object which hold information about user
 */

public class User {
    private String loginName;
    private String firstName;
    private String lastName;
    private String age;
    private String password;

    public User() { }

    public void setLoginName(String loginName) throws Exception {
        if(DatabaseUtils.checkIfUserNameExist(loginName)) {
            throw new Exception(String.format("User with this username [%s] already exists!", loginName));
        } else if(!loginName.matches("[a-zA-Z0-9]+"))
            throw new Exception("Incorrect login name!");
        this.loginName = loginName;
    }

    public void setFirstName(String firstName) throws Exception {
        if(!firstName.matches("[a-zA-Z]+"))
            throw new Exception("Name can contains only alphabetical characters!");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws Exception {
        if(!lastName.matches("[a-zA-Z]+"))
            throw new Exception("Name can contains only alphabetical characters!");
        this.lastName = lastName;
    }

    public void setAge(String age) throws Exception {
        if(!age.matches("[0-9]+"))
            throw new Exception("Age can contains only numeric characters!");
        this.age = age;
    }

    public void setPassword(String pass1, String pass2) throws Exception {
        if(!pass1.equals(pass2))
            throw new Exception(String.format("%s your password not equals!", this.loginName));
        this.password = pass1;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAge() {
        return this.age;
    }

    public String getPassword() {
        return this.password;
    }
}
