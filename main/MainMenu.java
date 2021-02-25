package main;

import abstractClasses.Menu;
import database.DatabaseUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author - Anonymous
 * @class - main application thread used for registration or login application clients
 */

public class MainMenu extends Menu {

    @Override
    public void print() {
        System.out.println("$$$$$$$$$$$ Welcome to Calorie-Counter $$$$$");
        System.out.println("$$ [ A ] -># Sign in as Admin             $$");
        System.out.println("$$ [ S ] -># Sign in as Client            $$");
        System.out.println("$$ [ R ] -># Register as Client           $$");
        System.out.println("$$ [ X ] -># Exit                         $$");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "A" : case "a" : AdminMenu aM = new AdminMenu(); aM.run();break;
                case "S" : case "s" : signAsClient();break;
                case "R" : case "r" : registerAsNewClient();break;
                case "X" : case "x" : exit(); break;
                default  : System.out.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void signAsClient() {
        // TODO - implement me ASAP
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a login name:");
            String loginName = br.readLine();
            System.out.println("Enter a password:");
            String password = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerAsNewClient() {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a login name:");
            String loginName = br.readLine();
            boolean userNameExist = DatabaseUtils.checkIfUserNameExist(loginName);
            if(!userNameExist) {
                System.out.println("Enter a user first name:");
                String firstName = br.readLine();
                System.out.println("Enter a user last name:");
                String lastName = br.readLine();
                System.out.println("Enter your age:");
                String age = br.readLine();
                System.out.println("Enter your password:");
                String pass1 = br.readLine();
                System.out.println("Confirm your password:");
                String pass2 = br.readLine();
                if(!pass1.equals(pass2)) {
                    throw new Exception(String.format("%s your password not equals!", loginName));
                }
                DatabaseUtils.writeNewClient(loginName, firstName, lastName, age, pass1);
                System.out.println(String.format("%s you are successfully registered.", loginName));
            } else {
                System.out.println(String.format("User with this username [%s] already exists!", loginName));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
