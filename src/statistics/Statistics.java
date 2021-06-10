package statistics;

import activities.Activity;
import enums.ActivityDifficulty;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistics {

    public static int numberOfActiveMonths(List<Activity> activities) {
        LocalDate maxDate = getMaximalDate(activities);
        LocalDate minDate = getMinimalDate(activities);
        if (minDate == null || maxDate == null) {
            return 0;
        }
        return (maxDate.getYear() - minDate.getYear()) * 12 + (maxDate.getMonthValue() - minDate.getMonthValue()) + 1;
    }

    public static LocalDate getMaximalDate(List<Activity> activities) {
        if(activities == null || activities.isEmpty()){
            return null;
        }
        return activities.stream().map(a -> toLocalDate(a.getDate())).max(LocalDate::compareTo).get();
    }

    public static LocalDate getMinimalDate(List<Activity> activities) {
        if(activities == null || activities.isEmpty()){
            return null;
        }
        return activities.stream().map(a -> toLocalDate(a.getDate())).min(LocalDate::compareTo).get();
    }

    public static LocalDate toLocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(stringDate, formatter);
    }

    public static int numberOfActivities(List<Activity> activities) {
        if(activities == null || activities.isEmpty()) {
            return 0;
        }
        return activities.size();
    }

    public static int numberOfActivities(List<Activity> activities, ActivityDifficulty difficulty) {
        if(activities == null || activities.isEmpty()) {
            return 0;
        }
        return (int) activities.stream().filter(a -> a.getDifficulty() == difficulty).count();
    }

    public static String averageTimeSpendByActivity(List<Activity> activities, int numOfActMonths) {
        if (activities == null || activities.isEmpty()) {
            return longToTime(0);
        }
        long time = activities.stream().map(Activity::getTime).reduce(Long::sum).get();
        return longToTime(time/numOfActMonths);
    }


    public static String timeSpendByActivity(List<Activity> activities) {
        if(activities == null|| activities.isEmpty()) {
            return longToTime(0);
        }
        long time = activities.stream().map(Activity::getTime).reduce(Long::sum).get();
        return longToTime(time);
    }

    public static String timeSpendByActivity(List<Activity> activities, ActivityDifficulty difficulty){
        if(activities == null || activities.isEmpty() || difficulty == null) {
            return longToTime(0);
        }
        long time = activities.stream().filter(a -> a.getDifficulty() == difficulty)
                .map(Activity::getTime).reduce(Long::sum).get();
        return longToTime(time);
    }

    public static String differenceFromAverage(String averageTime, String time) {
        Long myTime = Statistics.timeToLong(time);
        Long averageT = Statistics.timeToLong(averageTime);
        if(myTime > averageT){
            return "+ " + Statistics.longToTime(myTime - averageT);
        }
        if(myTime.equals(averageT)){
            return Statistics.longToTime(0);
        }
        return "- " + Statistics.longToTime(averageT - myTime);
    }

    public static String longToTime(long time) {
        int seconds = (int) (time / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        return String.format("%d h %d min %d s", hours, minutes % 60, seconds % 60);
    }

    public static long timeToLong(String strTime) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H 'h' m 'min' s 's'");
        int hour = Integer.parseInt(strTime.substring(0,strTime.indexOf(' ')));
        strTime = strTime.substring(strTime.indexOf('h') + 2 );
        int min = Integer.parseInt(strTime.substring(0, strTime.indexOf(' ')) );
        strTime = strTime.substring(strTime.indexOf('n') + 2 );
        int sec = Integer.parseInt(strTime.substring(0, strTime.indexOf(' ')) );
        return (((hour * 60L) + min) * 60L + sec) * 1000L;
    }

    public static LocalDate getLastYear() {
        LocalDate yearAgo = LocalDate.now().minusYears(1).plusMonths(1);
        LocalDate date = (LocalDate.of(yearAgo.getYear(), yearAgo.getMonthValue(), 1)).minusDays(1);
        return date;
    }

    public static Map<Month, Double> getMonthlyAverageWeights(List<ProgressRecord> progressRecords) {
        Map<Month, Double> monthlyAverages =  progressRecords.stream()
                .collect(Collectors.groupingBy(ProgressRecord::getMonth,Collectors.averagingDouble(ProgressRecord::getValue)));

        return monthlyAverages;
    }
}
