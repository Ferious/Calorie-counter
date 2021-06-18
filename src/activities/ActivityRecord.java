package activities;

import abstractClasses.Menu;
import database.DatabaseUtils;
import enums.ActivityDifficulty;
import enums.ActivityState;
import enums.ActivityType;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ActivityRecord extends Menu {
    ActivityState state = ActivityState.START;
    long startTime;
    long stopTime;
    Activity activity;

    String userName;

    public ActivityRecord(ActivityType type, String userName) {
        this.activity = new Activity(type);
        this.userName = userName;
        activity.calculateDate();
    }

    @Override
    public void print() {
        System.out.println("±-------------------" + activity.getType().toString() + "-----------------------±");
        if (state == ActivityState.START) {
            System.out.println("± S. -> Start                                     ±");
            System.out.println("± X. -> Exit                                      ±");
        } else if (state == ActivityState.PROGRESS) {
            System.out.println("± P. -> Pause                                     ±");
            System.out.println("± E. -> End                                       ±");
        } else if (state == ActivityState.PAUSE) {
            System.out.println("± R. -> Resume                                    ±");
            System.out.println("± E. -> End                                       ±");
        } else if (state == ActivityState.END) {
            System.out.println("± Choose difficulty:                              ±");
            System.out.println("± 1. -> Easy                                      ±");
            System.out.println("± 2. -> Moderate                                  ±");
            System.out.println("± 3. -> Hard                                      ±");
        }
        System.out.println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            if (state == ActivityState.START) {
                switch (option) {
                    case "S":
                    case "s":
                        setState(ActivityState.PROGRESS);
                        start();
                        break;
                    case "X":
                    case "x":
                        exit();
                        break;
                    default:
                        System.err.println(String.format("%s is a unknown option", option));
                        break;
                }
            } else if (state == ActivityState.PROGRESS) {
                switch (option) {
                    case "P":
                    case "p":
                        setState(ActivityState.PAUSE);
                        stop();
                        break;
                    case "E":
                    case "e":
                        setState(ActivityState.END);
                        stop();
                        enterName();
                        break;
                    default:
                        System.err.println(String.format("%s is a unknown option", option));
                        break;
                }
            } else if (state == ActivityState.PAUSE) {
                switch (option) {
                    case "R":
                    case "r":
                        setState(ActivityState.PROGRESS);
                        start();
                        break;
                    case "E":
                    case "e":
                        setState(ActivityState.END);
                        enterName();
                        break;
                    default:
                        System.err.println(String.format("%s is a unknown option", option));
                        break;
                }
            } else if (state == ActivityState.END) {
                switch (option) {
                    case "1":
                        activity.setDifficulty(ActivityDifficulty.EASY);
                        calculateCalories();
                        save();
                        break;
                    case "2":
                        activity.setDifficulty(ActivityDifficulty.MODERATE);
                        calculateCalories();
                        save();
                        break;
                    case "3":
                        activity.setDifficulty(ActivityDifficulty.HARD);
                        calculateCalories();
                        save();
                        break;
                    default:
                        System.err.println(String.format("%s is a unknown option", option));
                        break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setState(ActivityState state) {
        this.state = state;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        stopTime = System.currentTimeMillis();
        activity.setTime(activity.getTime() + stopTime - startTime);
        System.out.println("Time: " + activity.getFormattedTime());
    }

    public void enterName() {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter activity name:");
            String name = br.readLine();
            activity.setName(name);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void save() {
        DatabaseUtils.writeNewActivity(activity, userName);
        System.out.println("Well done! Keep Moving!");
        activity.setEndActivityTime(System.currentTimeMillis());
        exit();
    }

    public void calculateCalories() {
        activity.calculateCalories();
    }

}
