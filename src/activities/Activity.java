package activities;

import enums.ActivityDifficulty;
import enums.ActivityType;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Activity {
    int id = 0;
    ActivityType type;
    ActivityDifficulty difficulty;
    String name;
    int calories = 0;
    long time = 0;
    String date;
    long endActivityTime;

    public Activity(ActivityType type) {
        this.type = type;
    }

    public Activity(JSONObject activity) {
        try {
            type = ActivityType.valueOf(((String) activity.get("type")).toUpperCase());
            difficulty = ActivityDifficulty.valueOf((String) activity.get("difficulty"));
            name = (String) activity.get("name");
            calories = (int) (long) activity.get("calories");
            time = (long) activity.get("time");
            date = (String) activity.get("date");
            id = (int) (long) activity.get("id");
            endActivityTime = (long) activity.get("endActivityTime");
        } catch (Exception e) {
            System.err.println("ActivityError: JSONObject parse error");
        }
    }

    public String convertTime(long time) {
        int seconds = (int) (time / 1000) % 60;
        int minutes = (int) ((time / (1000 * 60)) % 60);
        int hours = (int) ((time / (1000 * 60 * 60)) % 24);
        return (hours / 10 < 1 ? "0" : "") + hours + ":" + (minutes / 10 < 1 ? "0" : "") + minutes + ":"
                + (seconds / 10 < 1 ? "0" : "") + seconds;
    }

    public void calculateDate() {
        LocalDate date = (java.time.LocalDate.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        this.setDate(date.format(formatter));
    }

    public void calculateCalories() {
        setCalories((int) Math.round(getTime() / 1000 / 60 * (getMET() * 3.5 * 90) / 200));
    }

    public double getMET() {
        double met = 0;
        switch (getType()) {
            case CYCLING:
                met = 9.5;
                break;
            case RUNNING:
                met = 9.8;
                break;
            case WALKING:
                met = 3.8;
                break;
            case WORKOUT:
                met = 4.5;
                break;
            default:
                met = 1;
        }
        met *= (getDifficulty() == ActivityDifficulty.EASY ? 0.8
                : getDifficulty() == ActivityDifficulty.HARD ? 1.2 : 1);
        return met;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public void setDifficulty(ActivityDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setTime(long time) {
        if (time < 0) {
            time = 0;
        }
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }

    public ActivityType getType() {
        return type;
    }

    public ActivityDifficulty getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getFormattedTime() {
        return convertTime(time);
    }

    public int getId() {
        return id;
    }

    public void setEndActivityTime(long endActivityTime) {
        this.endActivityTime = endActivityTime;
    }

    public long getEndActivityTime() {
        return endActivityTime;
    }
}
