package activities;

import abstractClasses.Menu;
import database.DatabaseUtils;
import enums.ActivityDifficulty;
import enums.ActivityState;
import enums.ActivityType;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ActivityDetail extends Menu {
    Activity activity;
    String user;

    public ActivityDetail(Activity activity, String user) {
        this.activity = activity;
        this.user = user;
    }

    @Override
    public void print() {
        System.out.println("Activity detail");
        System.out.println("Name: " + activity.getName());
        System.out.println("Type: " + activity.getType().toString());
        System.out.println("Difficulty: " + activity.getDifficulty().toString());
        System.out.println("Burned kcal: " + activity.getCalories());
        System.out.println("Time: " + activity.getFormattedTime());
        System.out.println("Date: " + activity.getDate());
        String fillBefore = new String(new char[25 - Math.round((float) activity.getName().length() / (float) 2)])
                .replace("\0", "-");
        String fillAfter = new String(new char[24 - activity.getName().length() / 2]).replace("\0", "-");
        System.out.println("±" + fillBefore + activity.getName() + fillAfter + "±");
        System.out.println("± D. -> Delete                                    ±");
        System.out.println("± X. -> Exit                                      ±");
        System.out.println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "D":
                case "d":
                    delete();
                    exit();
                    break;
                case "X":
                case "x":
                    exit();
                    break;
                default:
                    System.err.println(String.format("%s is a unknown option", option));
                    break;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        DatabaseUtils.removeActivity(user, activity);
    }

}
