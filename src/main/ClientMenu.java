package main;

import meal.MyMealMenu;
import activities.ActivityMenu;
import statistics.MyProgressMenu;
import abstractClasses.Menu;
import progress.ProgressMenu;

/**
 * @author - annonymous team
 * @class - menu with multiple choices for clients
 */

public class ClientMenu extends Menu {
    private String userName;

    public ClientMenu(String userName) {
        this.userName = userName;
    }
    //TODO - create menu for clients

    @Override
    public void print() {
        System.out.println("±--------------------Client Menu------------------±");
        System.out.println("± 1. ->$ Activities                               ±");
        System.out.println("± 2. ->$ Update Progress                          ±");
        System.out.println("± 3. ->$ My Progress                              ±");
        System.out.println("± 4. ->$ My meal Menu                             ±");
        System.out.println("± X. ->$ Exit to MainMenu                         ±");
        System.out.println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1" : new ActivityMenu(userName).run(); break;
                case "2" : new ProgressMenu(userName).run(); break;
                case "3" : new MyProgressMenu(userName).run(); break;
                case "4" : new MyMealMenu(userName).run(); break;
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
