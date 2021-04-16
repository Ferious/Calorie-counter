package statistics;

import activities.Activity;
import enums.ActivityDifficulty;

import java.util.List;

public class Statistics {

    public static int numberOfActivities(List<Activity> activities) {
        if(activities == null) {
            return 0;
        }
        return activities.size();
    }

    public static int numberOfActivities(List<Activity> activities, ActivityDifficulty difficulty) {
        if(activities == null) {
            return 0;
        }
        return (int) activities.stream().filter(a -> a.getDifficulty() == difficulty).count();
    }

    public static String averageTimeSpendByActivity(List<Activity> activities, int numOfActMonths) {
        if(activities == null) {
            return longToTime(0);
        }
        long time = activities.stream().map(Activity::getTime).reduce(Long::sum).get();
        return longToTime(time/numOfActMonths);
    }
    public static String timeSpendByActivity(List<Activity> activities) {
        if(activities == null) {
            return longToTime(0);
        }
        long time = activities.stream().map(Activity::getTime).reduce(Long::sum).get();
        return longToTime(time);
    }

    public static String timeSpendByActivity(List<Activity> activities, ActivityDifficulty difficulty){
        if(activities == null) {
            return longToTime(0);
        }
        long time = activities.stream().filter(a -> a.getDifficulty() == difficulty)
                .map(Activity::getTime).reduce(Long::sum).get();
        return longToTime(time);
    }

    public static String longToTime(long time) {
        int seconds = (int) (time / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        return String.format("%d h %d min %d s", hours, minutes % 60, seconds % 60);
    }
}
