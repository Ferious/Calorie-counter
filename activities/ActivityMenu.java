package activities;

import abstractClasses.Menu;
import activities.MyActivitiesMenu;
import activities.NewActivityMenu;

public class ActivityMenu extends Menu {
    String userName;

    public ActivityMenu(String userName) {
        this.userName = userName;
    }

    @Override
    public void print() {
        System.out.println("±------------------Activity Menu------------------±");
        System.out.println("± 1. -> New Activity                              ±");
        System.out.println("± 2. -> My Activities                             ±");
        System.out.println("± X. -> Exit to ClientMenu                        ±");
        System.out.println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1" : new NewActivityMenu(userName).run(); break;
                case "2" : new MyActivitiesMenu(userName).run(); break;
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
