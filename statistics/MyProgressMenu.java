package Statistics;

import abstractClasses.Menu;
import activities.Activity;
import database.DatabaseUtils;
import enums.ActivityDifficulty;
import enums.ActivityType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MyProgressMenu extends Menu {
    private final String userName;
    private List<Activity> myActivities;

    public MyProgressMenu(String userName) {
        this.userName = userName;
        myActivities = DatabaseUtils.getUserActivities(userName);
    }

    @Override
    public void print() {
        println("±--------------------My Progress------------------±");
        println("± 1. ->$ Weight change                            ±");
        println("± 2. ->$ All Exercises                            ±");
        println("± 3. ->$ Monthly average                          ±");
        println("± 4. ->$ Calorie intake                           ±");
        println("± X. ->$ Exit to Client Menu                      ±");
        println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1" : displayWeightChange(); break;
                case "2" : displayExercise(); break;
                case "3" : displayMonthlyAverageExercise(); break;
                case "4" : displayCalorieIntake(); break;
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayWeightChange() { //TODO
        println("Not available");
    }

    private List<Activity> filterActivityByType(ActivityType type) {
        return myActivities.stream().filter(a -> a.getType() == type).collect(Collectors.toList());
    }

    private void println(String text) {
        System.out.println(text);
    }

    private void displayActivityOfType(ActivityType type) {
        List<Activity> activities = filterActivityByType(type);
        if (activities == null || activities.isEmpty()) {
            return;
        }
        println(activities.get(0).getType().toString() + ":");
        println("\tTime spend by activity: " + Statistics.timeSpendByActivity(activities));
        println("\tNumber of performed activities: " + Statistics.numberOfActivities(activities));

        for (ActivityDifficulty difficulty : ActivityDifficulty.values()) {
            int numberOfActivitiesOfType = Statistics.numberOfActivities(activities, difficulty);
            if (numberOfActivitiesOfType <= 0) {
                continue;
            }
            println("\t"+difficulty.toString() + ":");
            println("\tTime spend by activity: " + Statistics.timeSpendByActivity(activities, difficulty));
            println("\tNumber of performed activities: " + numberOfActivitiesOfType);
        }
    }

    private void displayExercise() {
        for (ActivityType type : ActivityType.values()) {
            displayActivityOfType(type);
            println("");
        }
    }

    private int numberOfActiveMonths() {
        LocalDate now = LocalDate.now();
        LocalDate minDate = getMinimalDate();
        return (now.getYear() - minDate.getYear()) * 12 + (now.getMonthValue() - minDate.getMonthValue());
    }

    private LocalDate getMinimalDate() {
        return myActivities.stream().map(a -> toLocalDate(a.getDate())).min(LocalDate::compareTo).get();
    }

    private LocalDate toLocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    private void displayMonthlyAverageExercise() {
        int numOfActiveMonths = numberOfActiveMonths();
        println("Monthly average time spend by activities:");
        for (ActivityType type : ActivityType.values()) {
            List<Activity> activities = filterActivityByType(type);
            println("\t" + type.toString() + ": " + Statistics.averageTimeSpendByActivity(activities,numOfActiveMonths));
        }
    }

    private void displayCalorieIntake() { //TODO
        println("Not available");
    }

}
