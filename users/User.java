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
    private String gender;
    private String age;
    private String height;
    private String weight;
    private String password;

    public User() { }

    public boolean setLoginName(String loginName) throws Exception {
        if(DatabaseUtils.checkIfUserNameExist(loginName)) {
            throw new Exception(String.format("User with this username [%s] already exists!", loginName));
        } else if(!loginName.matches("[a-zA-Z0-9]+"))
            throw new Exception("Incorrect login name!");
        this.loginName = loginName;
        return true;
    }

    public boolean setFirstName(String firstName) throws Exception {
        if(!firstName.matches("[a-zA-Z]+"))
            throw new Exception("Name can contains only alphabetical characters!");
        this.firstName = firstName;
        return true;
    }

    public boolean setLastName(String lastName) throws Exception {
        if(!lastName.matches("[a-zA-Z]+"))
            throw new Exception("Name can contains only alphabetical characters!");
        this.lastName = lastName;
        return true;
    }

    public boolean setAge(String age) throws Exception {
        if(!age.matches("[0-9]+"))
            throw new Exception("Age can contains only numeric characters!");
        this.age = age;
        return true;
    }

    public boolean setPassword(String pass1, String pass2) throws Exception {
        if(!pass1.equals(pass2))
            throw new Exception(String.format("%s your password not equals!", this.loginName));
        this.password = pass1;
        return true;
    }

    public boolean setGender(String gender) throws Exception {
        if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Man")) {
            this.gender = "Man";
        }
        else if(gender.equalsIgnoreCase("W") || gender.equalsIgnoreCase("Woman")) {
            this.gender = "Woman";
        } else {
            throw new Exception("Gender was not set correctly!");
        }
        return true;
    }

    public boolean setHeight(String height) throws Exception {
        if(!height.matches("[0-9]+"))
            throw new Exception("Height can contains only numeric characters!");
        this.height = height;
        return true;
    }

    public boolean setWeight(String weight) throws Exception {
        if(!weight.matches("[0-9]+"))
            throw new Exception("Weight can contains only numeric characters!");
        this.weight = weight;
        return true;
    }

    public String getLoginName() { return this.loginName; }

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getGender() { return this.gender; }

    public String getAge() { return this.age; }

    public String getHeight() { return this.height; }

    public String getWeight() { return this.weight; }

    public String getPassword() { return this.password; }
}
