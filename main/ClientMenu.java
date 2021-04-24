package main;

import statistics.MyProgressMenu;
import abstractClasses.Menu;
import activities.NewActivityMenu;

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
        System.out.println("± 1. ->$ Activities                            ±");
        System.out.println("± 2. ->$ List                                  ±");
        System.out.println("± 3. ->$ My Progress                           ±");
        System.out.println("± X. ->$ Exit to MainMenu                         ±");
        System.out.println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1" : new ActivityMenu(userName).run(); break;
                case "2" : break;
                case "3" : new MyProgressMenu(userName).run(); break;
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}