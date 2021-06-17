package statistics;

import activities.Activity;
import enums.ActivityDifficulty;
import enums.ActivityType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {
    private List<Activity> nullActivities;
    private List<Activity> emptyActivities;
    private List<Activity> normalActivities;

    @BeforeEach
    void beforeEach() {
        nullActivities = null;
        emptyActivities = new ArrayList<>();
        String jsonString = "[{\"date\":\"23.04.2021\",\"difficulty\":\"EASY\"," +
                "\"name\":\"First Activity\",\"id\":0,\"time\":47620,\"calories\":0,\"type\":\"Workout\"}," +
                "{\"date\":\"21.04.2021\",\"difficulty\":\"MODERATE\"," +
                "\"name\":\"Secooond\",\"id\":1,\"time\":66400,\"calories\":15,\"type\":\"Running\"}," +
                "{\"date\":\"14.03.2021\",\"difficulty\":\"EASY\",\"name\":\"calorie test\",\"id\":2," +
                "\"time\":64819,\"calories\":6,\"type\":\"Workout\"}]";
        final JSONParser parser = new JSONParser();
        JSONArray jsonArray;
        try {
            jsonArray = (JSONArray) parser.parse(jsonString);
        } catch (Exception e) {
            jsonArray = new JSONArray();
        }
        normalActivities = new ArrayList<>();
        for (Object jsonActivity: jsonArray) {
            JSONObject activity = (JSONObject) jsonActivity;
            normalActivities.add(new Activity(activity));
        }
    }

    @Test
    void testNumberOfActiveMonthsNull() {
        assertEquals(0, Statistics.numberOfActiveMonths(nullActivities));
    }

    @Test
    void testNumberOfActiveMonthsEmpty() {
        assertEquals(0, Statistics.numberOfActiveMonths(emptyActivities));
    }

    @Test
    void testNumberOfActiveMonths() {
        assertEquals(2, Statistics.numberOfActiveMonths(normalActivities));
    }

    @Test
    void testGetMaximalDateNull() {
        assertNull(Statistics.getMaximalDate(nullActivities));
    }

    @Test
    void testGetMaximalDateEmpty() {
        assertNull(Statistics.getMaximalDate(emptyActivities));
    }

    @Test
    void testGetMaximalDate() {
        LocalDate expected = LocalDate.of(2021, 4, 23);
        assertEquals(expected, Statistics.getMaximalDate(normalActivities));
    }

    @Test
    void testGetMinimalDateNull() {
        assertNull(Statistics.getMinimalDate(nullActivities));
    }

    @Test
    void testGetMinimalDateEmpty() {
        assertNull(Statistics.getMinimalDate(emptyActivities));
    }

    @Test
    void testGetMinimalDate() {
        LocalDate expected = LocalDate.of(2021, 3, 14);
        assertEquals(expected, Statistics.getMinimalDate(normalActivities));
    }

    @Test
    void testToLocalDate() {
        LocalDate expected = LocalDate.of(2018, 2, 15);
        String dateValue = "15.02.2018";
        assertEquals(expected, Statistics.toLocalDate(dateValue));
    }

    @Test
    void testToLocalDateWrongFormat() {
        String dateValue = "2019-06-01";
        assertThrows(Exception.class, () -> Statistics.toLocalDate(dateValue));
    }

    @Test
    void testToLocalDateWrongDate() {
        String dateValue = "14.17.2014";
        assertThrows(Exception.class, () -> Statistics.toLocalDate(dateValue));
    }

    @Test
    void testNumberOfActivitiesNull() {
        assertEquals(0, Statistics.numberOfActivities(nullActivities));
    }

    @Test
    void testNumberOfActivitiesEmpty() {
        assertEquals(0, Statistics.numberOfActivities(emptyActivities));
    }

    @Test
    void testNumberOfActivities() {
        assertEquals(3, Statistics.numberOfActivities(normalActivities));
    }

    @Test
    void testNumberOfActivitiesWithDifficultyNull() {
        assertEquals(0, Statistics.numberOfActivities(nullActivities, ActivityDifficulty.HARD));
    }

    @Test
    void testNumberOfActivitiesWithDifficultyEmpty() {
        assertEquals(0, Statistics.numberOfActivities(emptyActivities, ActivityDifficulty.MODERATE));
    }

    @Test
    void testNumberOfActivitiesWithDifficulty() {
        assertEquals(2, Statistics.numberOfActivities(normalActivities, ActivityDifficulty.EASY));
    }

    @Test
    void testAverageTimeSpendByActivityNull() {
        String expected = Statistics.longToTime(0);
        assertEquals(expected, Statistics.averageTimeSpendByActivity(nullActivities, 2));
    }

    @Test
    void testAverageTimeSpendByActivityEmpty() {
        String expected = Statistics.longToTime(0);
        assertEquals(expected, Statistics.averageTimeSpendByActivity(emptyActivities, 2));
    }

    @Test
    void testAverageTimeSpendByActivity() {
        String expected = Statistics.longToTime(89419);
        assertEquals(expected, Statistics.averageTimeSpendByActivity(normalActivities, 2));
    }

    @Test
    void testTimeSpendByActivityNull(){
        assertEquals("0 h 0 min 0 s",Statistics.timeSpendByActivity(nullActivities));
    }

    @Test
    void testTimeSpendByActivityEmpty(){
        assertEquals("0 h 0 min 0 s",Statistics.timeSpendByActivity(emptyActivities));
    }

    @Test
    void testTimeSpendByActivityNormal(){
        Long miliSec = 47620 + 66400 + 64819L;
        assertEquals(Statistics.longToTime(miliSec),Statistics.timeSpendByActivity(normalActivities));
    }

    @Test
    void testTimeSpendByActivityLessThanZeroSec(){
        List<Activity> lessThanZero = new ArrayList<>();
        Activity a = new Activity(ActivityType.CYCLING);
        a.setTime(1);
        lessThanZero.add(a);
        assertEquals("0 h 0 min 0 s",Statistics.timeSpendByActivity(lessThanZero));
    }

    @Test
    void testTimeSpendByActivityLotOfSmall(){
        List<Activity> lotOfSmall = new ArrayList<>();
        for(int i = 0; i < 2000; i++){
            Activity a = new Activity(ActivityType.CYCLING);
            a.setTime(1);
            lotOfSmall.add(a);
        }
        assertEquals("0 h 0 min 2 s",Statistics.timeSpendByActivity(lotOfSmall));
    }

    @Test
    void testTimeSpendByActivityDifficultyNull(){
        assertEquals("0 h 0 min 0 s",Statistics.timeSpendByActivity(normalActivities, null));
    }

    @Test
    void testTimeSpendByActivityDiferentDifficulty(){
        List<Activity> dif = new ArrayList<>();
        Activity a = new Activity(ActivityType.CYCLING);
        a.setTime(10_000);
        dif.add(a);
        Activity b = new Activity(ActivityType.CYCLING);
        b.setTime(10_000);
        b.setDifficulty(ActivityDifficulty.EASY);
        dif.add(b);
        Activity c = new Activity(ActivityType.CYCLING);
        c.setTime(10_000);
        c.setDifficulty(ActivityDifficulty.MODERATE);
        dif.add(c);
        Activity d = new Activity(ActivityType.CYCLING);
        d.setTime(10_000);
        d.setDifficulty(ActivityDifficulty.HARD);
        dif.add(d);
        assertEquals("0 h 0 min 10 s",Statistics.timeSpendByActivity(dif, ActivityDifficulty.EASY));
    }

    @Test
    void testTimeSpendByActivityAddingSameDiff(){
        ActivityType[] activityTypes = ActivityType.values();
        List<Activity> dif = new ArrayList<>();
        for(int i = 0; i < 6 ; i++){
            Activity b = new Activity(activityTypes[i%4]);
            b.setTime(10_000);
            b.setDifficulty(ActivityDifficulty.EASY);
            dif.add(b);
        }
        assertEquals("0 h 1 min 0 s",Statistics.timeSpendByActivity(dif, ActivityDifficulty.EASY));
    }

    @Test
    void testDifferenceFromAverageZeroAverageTime() {
        assertEquals("+1 h 1 min 1 s", Statistics.differenceFromAverage("0 h 0 min 0 s","1 h 1 min 1 s"));
    }

    @Test
    void testDifferenceFromAverageLargeAverageTime() {
        assertEquals("-118 h 59 min 59 s", Statistics.differenceFromAverage("120 h 1 min 0 s","1 h 1 min 1 s"));
    }

    @Test
    void testDifferenceFromAverageLargeTime() {
        assertEquals("+59 h 35 min 47 s", Statistics.differenceFromAverage("10 h 25 min 14 s","70 h 1 min 1 s"));
    }

    @Test
    void testTimeToLong() {
        String timeValue = "1 h 17 min 43 s";
        assertEquals(4663000L, Statistics.timeToLong(timeValue));
    }

    @Test
    void testTimeToLongZero() {
        String timeValue = "0 h 0 min 0 s";
        assertEquals(0L, Statistics.timeToLong(timeValue));
    }

    @Test
    void testTimeToLongBig() {
        String timeValue = "105 h 4 min 0 s";
        assertEquals(378240000L, Statistics.timeToLong(timeValue));
    }

    @Test
    void testTimeToLongWrongFormat1() {
        String timeValue = "4h13min1s";
        assertThrows(Exception.class, () -> Statistics.timeToLong(timeValue));
    }

    @Test
    void testTimeToLongWrongFormat2() {
        String timeValue = " 3 h 22 min 11 s  ";
        assertThrows(Exception.class, () -> Statistics.timeToLong(timeValue));
    }

    @Test
    void testLongToTime() {
        String expected = "12 h 47 min 31 s";
        assertEquals(expected, Statistics.longToTime(46051000L));
    }

    @Test
    void testLongToTimeZero() {
        String expected = "0 h 0 min 0 s";
        assertEquals(expected, Statistics.longToTime(0L));
    }

    @Test
    void testLongToTimeBig(){
        String expected = "81 h 40 min 20 s";
        assertEquals(expected, Statistics.longToTime(294020000L));
    }

    @Test
    void testGetLastYear() {
        assertEquals(LocalDate.now().minusYears(1).getYear(), Statistics.getLastYear().getYear());
    }

    @Test
    void testGetMonthlyAverageWeightsEmpty() {
        Map<Month, Double> expected = new HashMap<>();
        assertEquals(expected, Statistics.getMonthlyAverageWeights(new ArrayList<>()));
    }

    @Test
    void testGetMonthlyAverageWeights() {
        List<ProgressRecord> list = new ArrayList<>();
        list.add(new ProgressRecord("12.01.2021", 90));
        list.add(new ProgressRecord("28.01.2021", 70));
        list.add(new ProgressRecord("10.02.2021", 70));
        list.add(new ProgressRecord("10.03.2021", 72));
        list.add(new ProgressRecord("10.03.2021", 80));
        list.add(new ProgressRecord("10.06.2021", 90));
        list.add(new ProgressRecord("10.06.2021", 80));

        Map<Month, Double> expected = new HashMap<>();
        expected.put(Month.of(2), 70.0);
        expected.put(Month.of(1), 80.0);
        expected.put(Month.of(3), 76.0);
        expected.put(Month.of(6), 85.0);

        assertEquals(expected, Statistics.getMonthlyAverageWeights(list));
    }

}