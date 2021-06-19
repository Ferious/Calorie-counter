package activities;

import database.DatabaseUtils;
import enums.ActivityDifficulty;
import enums.ActivityState;
import enums.ActivityType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import main.MainMenu;
import activities.ActivityNotifications;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityTest {

    @Test
    void setActivityName() {
        Activity activity = new Activity(ActivityType.RUNNING);
        String name = "test";
        activity.setName(name);
        assertEquals(name, activity.getName());
    }

    @Test
    void setActivityDate() {
        Activity activity = new Activity(ActivityType.RUNNING);
        LocalDate date = (java.time.LocalDate.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        activity.setDate(date.format(formatter));
        assertEquals(date.format(formatter), activity.getDate());
    }

    @Test
    void setActivityCalories() {
        Activity activity = new Activity(ActivityType.RUNNING);
        int calories = 154;
        activity.setCalories(calories);
        assertEquals(calories, activity.getCalories());
    }

    @Test
    void setActivityType() {
        ActivityType type = ActivityType.RUNNING;
        Activity activity = new Activity(type);
        assertEquals(type, activity.getType());
    }

    @Test
    void setActivityDifficulty() {
        Activity activity = new Activity(ActivityType.RUNNING);
        ActivityDifficulty difficulty = ActivityDifficulty.EASY;
        activity.setDifficulty(difficulty);
        assertEquals(difficulty, activity.getDifficulty());
    }

    @Test
    void setActivityId() {
        Activity activity = new Activity(ActivityType.RUNNING);
        int id = 7;
        activity.setId(id);
        assertEquals(id, activity.getId());
    }

    @Test
    void setActivityTime() {
        Activity activity = new Activity(ActivityType.RUNNING);
        long time = 1264500;
        activity.setTime(time);
        assertEquals(time, activity.getTime());
    }

    @Test
    void checkRunningCalories() {
        ActivityRecord activityRecord = new ActivityRecord(ActivityType.RUNNING, "test");
        activityRecord.activity.setTime(1800000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.EASY);
        activityRecord.calculateCalories();
        assertEquals(370, activityRecord.activity.getCalories());

        activityRecord.activity.setTime(3600000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.MODERATE);
        activityRecord.calculateCalories();
        assertEquals(926, activityRecord.activity.getCalories());

        activityRecord.activity.setTime(600000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.HARD);
        activityRecord.calculateCalories();
        assertEquals(185, activityRecord.activity.getCalories());
    }

    @Test
    void checkCyclingCalories() {
        ActivityRecord activityRecord = new ActivityRecord(ActivityType.CYCLING, "test");
        activityRecord.activity.setTime(1800000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.EASY);
        activityRecord.calculateCalories();
        assertEquals(359, activityRecord.activity.getCalories());

        activityRecord.activity.setTime(3600000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.MODERATE);
        activityRecord.calculateCalories();
        assertEquals(898, activityRecord.activity.getCalories());

        activityRecord.activity.setTime(600000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.HARD);
        activityRecord.calculateCalories();
        assertEquals(180, activityRecord.activity.getCalories());
    }

    @Test
    void checkWalkingCalories() {
        ActivityRecord activityRecord = new ActivityRecord(ActivityType.WALKING, "test");
        activityRecord.activity.setTime(1800000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.EASY);
        activityRecord.calculateCalories();
        assertEquals(144, activityRecord.activity.getCalories());

        activityRecord.activity.setTime(3600000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.MODERATE);
        activityRecord.calculateCalories();
        assertEquals(359, activityRecord.activity.getCalories());

        activityRecord.activity.setTime(600000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.HARD);
        activityRecord.calculateCalories();
        assertEquals(72, activityRecord.activity.getCalories());
    }

    @Test
    void checkWorkoutCalories() {
        ActivityRecord activityRecord = new ActivityRecord(ActivityType.WORKOUT, "test");
        activityRecord.activity.setTime(1800000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.EASY);
        activityRecord.calculateCalories();
        assertEquals(170, activityRecord.activity.getCalories());

        activityRecord.activity.setTime(3600000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.MODERATE);
        activityRecord.calculateCalories();
        assertEquals(425, activityRecord.activity.getCalories());

        activityRecord.activity.setTime(600000);
        activityRecord.activity.setDifficulty(ActivityDifficulty.HARD);
        activityRecord.calculateCalories();
        assertEquals(85, activityRecord.activity.getCalories());
    }

    @Test
    void checkActivityTime() {
        Activity activity = new Activity(ActivityType.CYCLING);
        activity.setTime(-100);
        assertEquals(0, activity.getTime());
        activity.setTime(200);
        assertEquals(200, activity.getTime());
    }

    @Test
    void checkNewActivity() {
        ActivityRecord activityRecord = new ActivityRecord(ActivityType.WORKOUT, "test");
        activityRecord.start();
        activityRecord.stop();
        activityRecord.setState(ActivityState.END);
        activityRecord.activity.setName("Test");
        activityRecord.activity.setDifficulty(ActivityDifficulty.MODERATE);
        int testingId = -1;
        activityRecord.activity.setId(testingId);
        activityRecord.calculateCalories();
        activityRecord.save();
        List<Activity> activities = DatabaseUtils.getUserActivities("test");
        Predicate<Activity> byId = activity -> activity.getId() == testingId;
        List<Activity> testingActivity = activities.stream().filter(byId).collect(Collectors.toList());
        ActivityDetail detail = new ActivityDetail(testingActivity.get(0), "test");
        assertEquals(activityRecord.activity.getId(), detail.activity.getId());
        assertEquals(activityRecord.activity.getMET(), detail.activity.getMET());
        assertEquals(activityRecord.activity.getTime(), detail.activity.getTime());
        assertEquals(activityRecord.activity.getCalories(), detail.activity.getCalories());
        assertEquals(activityRecord.activity.getDate(), detail.activity.getDate());
        assertEquals(activityRecord.activity.getDifficulty(), detail.activity.getDifficulty());
        assertEquals(activityRecord.activity.getName(), detail.activity.getName());
        assertEquals(activityRecord.activity.getType(), detail.activity.getType());
        assertEquals(activityRecord.activity.getFormattedTime(), detail.activity.getFormattedTime());
        detail.delete();
        activities = DatabaseUtils.getUserActivities("test");
        testingActivity = activities.stream().filter(byId).collect(Collectors.toList());
        assertEquals(0, testingActivity.size());
    }

    @Test
    void checkNotificationFirst() {
        MainMenu menu = new MainMenu();
        long timeNow = 9223372036854775807l;
        long lastActivityTime = 0l;
        String notification = menu.printActivityNotification(lastActivityTime, timeNow);
        ActivityNotifications notificationOfActivity = new ActivityNotifications();
        assertEquals(notificationOfActivity.NOTIFICATION_BEFORE_FIRST_ACTIVITY, notification);
    }

    @Test
    void checkNotificationHalfDay() {
        MainMenu menu = new MainMenu();
        long timeNow = 9223372036854775807l;
        long lastActivityTime = 9223372036811575857l;
        String notification = menu.printActivityNotification(lastActivityTime, timeNow);
        ActivityNotifications notificationOfActivity = new ActivityNotifications();
        assertEquals(notificationOfActivity.NOTIFICATION_BEFORE_HALF_DAY, notification);
    }

    @Test
    void checkNotificationDay() {
        MainMenu menu = new MainMenu();
        long timeNow = 9223372036854775807l;
        long lastActivityTime = 9223372036768375808l;
        String notification = menu.printActivityNotification(lastActivityTime, timeNow);
        ActivityNotifications notificationOfActivity = new ActivityNotifications();
        assertEquals(notificationOfActivity.NOTIFICATION_BEFORE_DAY, notification);
    }

    @Test
    void checkNotificationHalfWeek() {
        MainMenu menu = new MainMenu();
        long timeNow = 9223372036854775807l;
        long lastActivityTime = 9223372036552375808l;
        String notification = menu.printActivityNotification(lastActivityTime, timeNow);
        ActivityNotifications notificationOfActivity = new ActivityNotifications();
        assertEquals(notificationOfActivity.NOTIFICATION_BEFORE_HALF_WEEK, notification);
    }

    @Test
    void checkNotificationWeek() {
        MainMenu menu = new MainMenu();
        long timeNow = 9223372036854775807l;
        long lastActivityTime = 9223372036249975808l;
        String notification = menu.printActivityNotification(lastActivityTime, timeNow);
        ActivityNotifications notificationOfActivity = new ActivityNotifications();
        assertEquals(notificationOfActivity.NOTIFICATION_BEFORE_WEEK, notification);
    }

    @Test
    void checkNotificationHalfMonth() {
        MainMenu menu = new MainMenu();
        long timeNow = 9223372036854775807l;
        long lastActivityTime = 9223372035645175808l;
        String notification = menu.printActivityNotification(lastActivityTime, timeNow);
        ActivityNotifications notificationOfActivity = new ActivityNotifications();
        assertEquals(notificationOfActivity.NOTIFICATION_BEFORE_HALF_MONTH, notification);
    }

    @Test
    void checkNotificationMonth() {
        MainMenu menu = new MainMenu();
        long timeNow = 9223372036854775807l;
        long lastActivityTime = 9223372034262775808l;
        String notification = menu.printActivityNotification(lastActivityTime, timeNow);
        ActivityNotifications notificationOfActivity = new ActivityNotifications();
        assertEquals(notificationOfActivity.NOTIFICATION_BEFORE_MONTH, notification);
    }

    @Test
    void checkNotificationManyDays() {
        MainMenu menu = new MainMenu();
        long timeNow = 9223372036854775807l;
        long lastActivityTime = 9223372034262775806l;
        String notification = menu.printActivityNotification(lastActivityTime, timeNow);
        ActivityNotifications notificationOfActivity = new ActivityNotifications();
        assertEquals(notificationOfActivity.NOTIFICATION_AFTER_MONTH, notification);
    }

}