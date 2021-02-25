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
    }

    private void registerAsNewClient() throws IOException {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a login name:");
            String loginName = br.readLine();
            boolean userNameExist = DatabaseUtils.checkIfUserNameExist(loginName);
            if(!userNameExist) {
                System.out.println("Enter a user name:");
                String user = br.readLine();
                System.out.println("Enter a user login name:");
                String personLoginName = br.readLine();
                // TODO - and so on ...
            } else {
                System.out.println(String.format("User with this username [%s] already exists!", loginName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
