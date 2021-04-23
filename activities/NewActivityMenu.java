package activities;

import abstractClasses.Menu;

import enums.ActivityType;

public class NewActivityMenu extends Menu {

    @Override
    public void print() {
        System.out.println("±-------------------New Activity------------------±");
        System.out.println("± 1. -> Cycling                                   ±");
        System.out.println("± 2. -> Running                                   ±");
        System.out.println("± 3. -> Walking                                   ±");
        System.out.println("± 4. -> Workout                                   ±");
        System.out.println("± X. -> Exit to ActivityMenu                      ±");
        System.out.println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1" : new ActivityRecord(ActivityType.CYCLING).run(); break;
                case "2" : new ActivityRecord(ActivityType.RUNNING).run(); break;
                case "3" : new ActivityRecord(ActivityType.WALKING).run(); break;
                case "4" : new ActivityRecord(ActivityType.WORKOUT).run(); break;
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
