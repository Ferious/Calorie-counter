package activities;

import abstractClasses.Menu;
import database.DatabaseUtils;

import java.util.List;

public class MyActivitiesMenu extends Menu {

    List<Activity> activities;
    String user;

    public MyActivitiesMenu(String user) {
        this.user = user;
        activities = DatabaseUtils.getUserActivities(user);
    }

    @Override
    public void print() {
        System.out.println("±-------------------My Activities-----------------±");
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            String activityName = activity.getName();
            System.out.println("± "+ (i+1) +". -> "+ activityName +
                    new String(new char[41-activityName.length()-activity.getDate().length()-i/10]).replace("\0", " ") +
                    activity.getDate()+" ±");
        }
        System.out.println("± X. -> Exit to ActivityMenu                      ±");
        System.out.println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "X" : case "x" : exit(); break;
                default  :  {
                    try {
                        int number = Integer.parseInt(option);
                        if (number > 0 && number <= activities.size()) {
                            new ActivityDetail(activities.get(number-1), user).run();
                            activities = DatabaseUtils.getUserActivities(user);
                        } else {
                            System.err.println(String.format("%s is out of range", option));
                        }
                    } catch(Exception e) {
                        System.err.println(String.format("%s is a unknown option", option));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
