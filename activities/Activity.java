package activities;

import enums.ActivityDifficulty;
import enums.ActivityType;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Activity {
    int id;
    ActivityType type;
    ActivityDifficulty difficulty;
    String name;
    int calories = 0;
    long time = 0;
    String date;

    public Activity(ActivityType type) {
        this.type = type;
    }

    public Activity(JSONObject activity) {
        try {
            type = ActivityType.valueOf(((String)activity.get("type")).toUpperCase());
            difficulty = ActivityDifficulty.valueOf((String)activity.get("difficulty"));
            name = (String) activity.get("name");
            calories = (int)(long)activity.get("calories");
            time = (long) activity.get("time");
            date = (String) activity.get("date");
            id = (int)(long)activity.get("id");
        } catch (Exception e) {
            System.err.println("ActivityError: JSONObject parse error");
        }
    }

    public String convertTime(long time){
        int seconds = (int) (time / 1000) % 60 ;
        int minutes = (int) ((time / (1000*60)) % 60);
        int hours   = (int) ((time / (1000*60*60)) % 24);
        return (hours/10 < 1 ? "0" : "") + hours + ":" +
                (minutes/10 < 1 ? "0" : "") + minutes + ":" +
                (seconds/10 < 1? "0" : "" ) + seconds;
    }

    public void calculateDate(){
        LocalDate date = (java.time.LocalDate.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        this.setDate(date.format(formatter));
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

}
