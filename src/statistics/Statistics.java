package statistics;

import activities.Activity;
import enums.ActivityDifficulty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Statistics {

    public static int numberOfActiveMonths(List<Activity> activities) {
        LocalDate now = LocalDate.now();
        LocalDate minDate = getMinimalDate(activities);
        return (now.getYear() - minDate.getYear()) * 12 + (now.getMonthValue() - minDate.getMonthValue());
    }

    private static LocalDate getMinimalDate(List<Activity> activities) {
        return activities.stream().map(a -> toLocalDate(a.getDate())).min(LocalDate::compareTo).get();
    }

    public static LocalDate toLocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

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

    public static String differenceWithAverage(String averageTime, String time) {
        LocalTime myTime = LocalTime.ofNanoOfDay(Statistics.timeToLong(time));
        LocalTime averageT = LocalTime.ofNanoOfDay(Statistics.timeToLong(averageTime));
        if(myTime.isAfter(averageT)){
            return "+" + Statistics.longToTime(myTime.toNanoOfDay() - averageT.toNanoOfDay());
        }
        return "-" + Statistics.longToTime(averageT.toNanoOfDay() - myTime.toNanoOfDay());
    }

    public static String longToTime(long time) {
        int seconds = (int) (time / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        return String.format("%d h %d min %d s", hours, minutes % 60, seconds % 60);
    }

    public static long timeToLong(String strTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H 'h' m 'min' s 's'");
        LocalTime time = LocalTime.parse(strTime, formatter);
        return (((time.getHour() * 60L) + time.getMinute()) * 60L + time.getSecond()) * 1000L;
    }
}
