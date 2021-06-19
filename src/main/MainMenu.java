package main;

import abstractClasses.Menu;
import database.DatabaseUtils;
import users.User;
import activities.Activity;
import java.util.List;
import activities.ActivityNotifications;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author - Anonymous
 * @class - main application thread used for registration or login application
 *        clients
 */

public class MainMenu extends Menu {

    @Override
    public void print() {
        System.out.println("$$$$$$$$$$$ Welcome to Calorie-Counter $$$$$");
        System.out.println("$$ [ A ] -># Sign in as Admin             $$");
        System.out.println("$$ [ S ] -># Sign in as Client            $$");
        System.out.println("$$ [ R ] -># Register as Client           $$");
        System.out.println("$$ [ X ] -># Exit                         $$");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "A":
                case "a":
                    signAsAdmin();
                    break;
                case "S":
                case "s":
                    signAsClient();
                    break;
                case "R":
                case "r":
                    registerAsNewClient();
                    break;
                case "X":
                case "x":
                    exit();
                    break;
                default:
                    System.err.println(String.format("%s is a unknown option", option));
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void signProcess(boolean client) {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a login name:");
            String loginName = br.readLine();
            System.out.println("Enter a password:");
            String password = br.readLine();
            boolean login = DatabaseUtils.logInUser(loginName, password, client);
            if (login) {
                System.out.println(String.format("Welcome %s!", loginName));
                long lastActivityEndTime = getLastActivityTime(loginName);
                long timeNow = System.currentTimeMillis();
                System.out.println();
                System.out.println(
                        "Activity notification: \"" + printActivityNotification(lastActivityEndTime, timeNow) + "\"");
                if (client) {
                    new ClientMenu(loginName).run();
                } else {
                    new AdminMenu().run();
                }
            } else {
                throw new Exception("Login name or password is incorrect!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void signAsAdmin() {
        signProcess(false);
    }

    private void signAsClient() {
        signProcess(true);
    }

    private void registerAsNewClient() {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            User user = new User();
            System.out.println("Enter a login name:");
            String loginName = br.readLine();
            user.setLoginName(loginName);
            System.out.println("Enter a user first name:");
            user.setFirstName(br.readLine());
            System.out.println("Enter a user last name:");
            user.setLastName(br.readLine());
            System.out.println("Enter your gender like 'Man or Woman'");
            user.setGender(br.readLine());
            System.out.println("Enter your age:");
            user.setAge(br.readLine());
            System.out.println("Enter your height:");
            user.setHeight(br.readLine());
            System.out.println("Enter your weight:");
            user.setWeight(br.readLine());
            System.out.println("Enter your password:");
            String pass1 = br.readLine();
            System.out.println("Confirm your password:");
            String pass2 = br.readLine();
            user.setPassword(pass1, pass2);
            DatabaseUtils.writeNewClient(user);
            System.out.println(String.format("%s you are successfully registered.", loginName));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String printActivityNotification(long lastActivityTime, long timeNow) {
        ActivityNotifications notifications = new ActivityNotifications();
        if (lastActivityTime == 0l) {
            return notifications.NOTIFICATION_BEFORE_FIRST_ACTIVITY;
        }

        long noActivityTime = timeNow - lastActivityTime;

        if (noActivityTime < 43200000l) {
            return notifications.NOTIFICATION_BEFORE_HALF_DAY;
        }
        if (noActivityTime < 86400000l) {
            return notifications.NOTIFICATION_BEFORE_DAY;
        }
        if (noActivityTime < 302400000l) {
            return notifications.NOTIFICATION_BEFORE_HALF_WEEK;
        }
        if (noActivityTime < 604800000l) {
            return notifications.NOTIFICATION_BEFORE_WEEK;
        }
        if (noActivityTime < 1209600000l) {
            return notifications.NOTIFICATION_BEFORE_HALF_MONTH;
        }
        if (noActivityTime < 2592000000l) {
            return notifications.NOTIFICATION_BEFORE_MONTH;
        }
        return notifications.NOTIFICATION_AFTER_MONTH;
    }

    public long getLastActivityTime(String loginName) {
        List<Activity> activities = DatabaseUtils.getUserActivities(loginName);
        long lastActivityTime = 0;
        for (Activity activity : activities) {
            if (lastActivityTime < activity.getEndActivityTime()) {
                lastActivityTime = activity.getEndActivityTime();
            }
        }
        return lastActivityTime;
    }
}
