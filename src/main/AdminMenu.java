package main;

import abstractClasses.Menu;

/**
 * @author - annonymous team
 * @class - menu with multiple choices for administrator of application
 */

public class AdminMenu extends Menu {

    //TODO - create menu for administrator

    @Override
    public void print() {
        System.out.println("$$ [ X ] -># Exit                         $$");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
