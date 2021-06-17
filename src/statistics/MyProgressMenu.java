package statistics;

import abstractClasses.Menu;
import activities.Activity;
import database.DatabaseUtils;
import enums.ActivityDifficulty;
import enums.ActivityType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        println("± 4. ->$ Progress of this month                   ±");
        println("± 5. ->$ Calorie intake today                     ±");
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
                case "4" : displayThisMonthProgress(); break;
                case "5" : displayCalorieIntakeToday(); break;
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<ProgressRecord> getLastYearWeightRecords() {
        JSONArray records = DatabaseUtils.getWeightChange(userName);
        List<ProgressRecord> progressRecords = new ArrayList<>();
        if (records == null) {
            return progressRecords;
        }
        LocalDate yearAgo = Statistics.getLastYear();
        for (Object obj: records){
            JSONObject jsonObj = (JSONObject) obj;
            ProgressRecord record = new ProgressRecord(jsonObj, "weight");
            if (record.getDate().isAfter(yearAgo)) {
                progressRecords.add(record);
            }
        }
        return progressRecords;
    }

    private void displayWeightChange() {
        List<ProgressRecord> progressRecords = getLastYearWeightRecords();
        Map<Month, Double> monthlyAverages = Statistics.getMonthlyAverageWeights(progressRecords);
        int month = LocalDate.now().getMonthValue() + 1;
        for (int i = 0; i < 12; i++) {
            Month m = Month.of(month);
            if (monthlyAverages.containsKey(m)) {
                println(m.name() + ": " + monthlyAverages.get(m));
            }
            month++;
            if (month > 12) {
                month = 1;
            }
        }
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
        println("\tTotal time spend by activity: " + Statistics.timeSpendByActivity(activities));
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

    private void displayMonthlyAverageExercise() {
        int numOfActiveMonths = Statistics.numberOfActiveMonths(myActivities);
        println("Monthly average time spend by activities:");
        for (ActivityType type : ActivityType.values()) {
            List<Activity> activities = filterActivityByType(type);
            println("\t" + type.toString() + ": " + Statistics.averageTimeSpendByActivity(activities,numOfActiveMonths));
        }
    }

    private boolean hasSameMonth(Activity activity, LocalDate presentDate) {
        LocalDate date = Statistics.toLocalDate(activity.getDate());
        return presentDate.getYear() == date.getYear() && presentDate.getMonth() == date.getMonth();
    }

    private void displayThisMonthProgress() {
        int numOfActiveMonths = Statistics.numberOfActiveMonths(myActivities);
        LocalDate presentDate = LocalDate.now();
        println("This month:");
        for (ActivityType type : ActivityType.values()) {
            List<Activity> thisMonthActivities = filterActivityByType(type).stream()
                    .filter(a -> hasSameMonth(a, presentDate)).collect(Collectors.toList());
            String timeSpentThisMonth = Statistics.timeSpendByActivity(thisMonthActivities);
            println("\t" + type.toString());
            println("\tTime spend: " + timeSpentThisMonth);
            String averageTimeSpend = Statistics.averageTimeSpendByActivity(filterActivityByType(type),numOfActiveMonths);
            println("\tComparison to month average: " + Statistics.differenceFromAverage(averageTimeSpend, timeSpentThisMonth));
        }
    }

    private Double getCalorieIntakeToday() {
        JSONArray records = DatabaseUtils.getCalorieIntake(userName);
        if (records == null) {
            return 0.0;
        }
        Double todaysCalorieIntake = 0.0;
        LocalDate today = LocalDate.now();
        for (Object obj: records){
            JSONObject jsonObj = (JSONObject) obj;
            ProgressRecord record = new ProgressRecord(jsonObj, "calories");
            if (record.getDate().equals(today)) {
                todaysCalorieIntake += record.getValue();
            }
        }
        return todaysCalorieIntake;
    }

    private Double getBurntCaloriesToday() {
        List<Activity> activities = DatabaseUtils.getUserActivities(userName);
        double caloriesBurnt = 0.0;
        LocalDate today = LocalDate.now();
        for (Activity activity: activities) {
            LocalDate activityDate = Statistics.toLocalDate(activity.getDate());
            if (activityDate.equals(today)) {
                caloriesBurnt += activity.getCalories();
            }
        }
        return caloriesBurnt;
    }

    private void displayCalorieIntakeToday() {
        Double calorieIntake = getCalorieIntakeToday();
        Double burntCalories = getBurntCaloriesToday();
        println("Today's calorie intake: " + calorieIntake);
        println("Calories burnt today: " + burntCalories);
        println("Total: " + (calorieIntake - burntCalories));
    }

}
