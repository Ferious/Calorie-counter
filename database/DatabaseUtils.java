package database;


import activities.Activity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import users.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author - team annonymous
 * @class - static method for working with database;
 *          visible for all application classes
 *          singleton design pattern
 */

public class DatabaseUtils {

    private static final String PATH_TO_USERS = "database/users.json";
    private static final String PATH_TO_ACTIVITIES = "database/activities.json";
    private static final String PATH_TO_PROGRESS = "database/progress.json";
    // Private constructor to avoid client applications to use constructor
    // Singleton design pattern
    private DatabaseUtils() {}

    public static JSONArray parseJson(String path) throws IOException, ParseException {
        final JSONParser parser = new JSONParser();
        final JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));
        return jsonArray;
    }

    public static JSONObject parseJsonObject(String path) throws IOException, ParseException {
        final JSONParser parser = new JSONParser();
        final JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(path));
        return jsonObject;
    }

    //TODO - think about interface for database operations;
    public static List<String> getAllUsersNames(){
        final List<String> userNames = new ArrayList<>();
        try {
            final JSONArray users = parseJson(PATH_TO_USERS);
            for(Object o : users) {
                JSONObject jsonObject = (JSONObject) o;
                String strName = (String) jsonObject.get("name");
                userNames.add(strName);
            }
        } catch (IOException | ParseException e) {
            System.err.println(String.format("Problem with reading from %s", PATH_TO_USERS));
            e.printStackTrace();
        }
        return userNames;
    }

    public static boolean checkIfUserNameExist(String userName) {
        boolean exist = false;
        try {
            final JSONArray users = parseJson(PATH_TO_USERS);
            for(Object o : users) {
                JSONObject jsonObject = (JSONObject) o;
                String strName = (String) jsonObject.get("loginName");
                if(strName.equalsIgnoreCase(userName))
                    exist = true;
            }
        } catch (IOException | ParseException e) {
            System.err.println(String.format("Problem with reading from %s", PATH_TO_USERS));
            e.printStackTrace();
        }
        return exist;
    }

    public static boolean logInUser(String userName, String password) {
        boolean login = false;
        try {
            final JSONArray users = parseJson(PATH_TO_USERS);
            for(Object o : users) {
                JSONObject jsonObject = (JSONObject) o;
                String strName = (String) jsonObject.get("loginName");
                String pass = (String) jsonObject.get("password");
                if(strName.equalsIgnoreCase(userName) && pass.equals(password))
                    login = true;
            }
        } catch (IOException | ParseException e) {
            System.err.println(String.format("Problem with reading from %s", PATH_TO_USERS));
            e.printStackTrace();
        }
        return login;
    }

    public static void writeNewClient(User user) {
        try {
            JSONArray array = parseJson(PATH_TO_USERS);
            JSONObject client = new JSONObject();
            client.put("loginName", user.getLoginName());
            client.put("firstName", user.getFirstName());
            client.put("lastName", user.getLastName());
            client.put("gender", user.getGender());
            client.put("age", user.getAge());
            client.put("height", user.getHeight());
            client.put("weight", user.getWeight());
            client.put("password", user.getPassword());
            array.add(client);
            FileWriter file = new FileWriter(PATH_TO_USERS);
            file.write(array.toJSONString());
            file.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void writeNewActivity(Activity activity, String userName) {
        try {
            JSONObject obj = parseJsonObject(PATH_TO_ACTIVITIES);
            JSONArray array = (JSONArray) obj.get(userName);
            if (array != null) {
                if (activity.getId() == 0) {
                    activity.setId(((int)(long)((JSONObject)array.get(array.size()-1)).get("id"))+1);
                }
            } else {
                array = new JSONArray();
            }
            JSONObject newActivity = new JSONObject();
            newActivity.put("id", activity.getId());
            newActivity.put("name", activity.getName());
            newActivity.put("type", activity.getType().toString());
            newActivity.put("time", activity.getTime());
            newActivity.put("date", activity.getDate());
            newActivity.put("calories", activity.getCalories());
            newActivity.put("difficulty", activity.getDifficulty().toString());
            array.add(newActivity);
            FileWriter file = new FileWriter(PATH_TO_ACTIVITIES);
            obj.put(userName, array);
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static List<Activity> getUserActivities(String user){
        List<Activity> result = new ArrayList<>();
        try {
            JSONObject allActivities = parseJsonObject(PATH_TO_ACTIVITIES);
            JSONArray userActivities = (JSONArray) allActivities.get(user);
            if (userActivities != null) {
                for (int i = 0; i < userActivities.size(); i++) {
                    JSONObject activityObject = (JSONObject) userActivities.get(i);
                    Activity activity = new Activity(activityObject);
                    result.add(activity);
                }
            }
        } catch (IOException | ParseException e) {
            System.err.println(String.format("Problem with reading from %s", PATH_TO_ACTIVITIES));
            e.printStackTrace();
        }
        return result;
    }


    public static void removeActivity(String userName, Activity activity){
        try {
            JSONObject allActivities = parseJsonObject(PATH_TO_ACTIVITIES);
            JSONArray userActivities = (JSONArray) allActivities.get(userName);
            int resultIndex = -1;
            for (int i = 0; i < userActivities.size(); i++){
                JSONObject activityObject = (JSONObject)userActivities.get(i);
                int activityId = (int)(long) activityObject.get("id");
                if (activity.getId() == activityId) {
                    resultIndex = i;
                    break;
                }
            }
            if (resultIndex >= 0) {
                userActivities.remove(resultIndex);
                FileWriter file = new FileWriter(PATH_TO_ACTIVITIES);
                allActivities.put(userName, userActivities);
                file.write(allActivities.toJSONString());
                file.flush();
            }
        } catch (IOException | ParseException e) {
            System.err.println(String.format("Problem with reading from %s", PATH_TO_ACTIVITIES));
            e.printStackTrace();
        }
    }

    public static void updateWeightChange(double weight, String date, String userName) {
        try {
            JSONObject obj = parseJsonObject(PATH_TO_PROGRESS);
            JSONObject trackingData = (JSONObject) obj.get(userName);
            if (trackingData == null) {
                trackingData = initializeTrackingData();
            }
            JSONArray weightTracking = (JSONArray) trackingData.get("weightChange");
            JSONObject newWeight = new JSONObject();
            newWeight.put("weight", weight);
            newWeight.put("date", date);
            weightTracking.add(newWeight);
            trackingData.put("weightChange", weightTracking);
            obj.put(userName, trackingData);
            FileWriter file = new FileWriter(PATH_TO_PROGRESS);
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void updateCalorieIntake(int calories, String date, String userName) {
        try {
            JSONObject obj = parseJsonObject(PATH_TO_PROGRESS);
            JSONObject trackingData = (JSONObject) obj.get(userName);
            if (trackingData == null) {
                trackingData = initializeTrackingData();
            }
            JSONArray weightTracking = (JSONArray) trackingData.get("calorieIntake");
            JSONObject newCalories = new JSONObject();
            newCalories.put("calories", calories);
            newCalories.put("date", date);
            weightTracking.add(newCalories);
            trackingData.put("calorieIntake", weightTracking);
            obj.put(userName, trackingData);
            FileWriter file = new FileWriter(PATH_TO_PROGRESS);
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void updateFluidIntake(int fluid, String date, String userName) {
        try {
            JSONObject obj = parseJsonObject(PATH_TO_PROGRESS);
            JSONObject trackingData = (JSONObject) obj.get(userName);
            if (trackingData == null) {
                trackingData = initializeTrackingData();
            }
            JSONArray weightTracking = (JSONArray) trackingData.get("fluidIntake");
            JSONObject newFluid = new JSONObject();
            newFluid.put("fluid", fluid);
            newFluid.put("date", date);
            weightTracking.add(newFluid);
            trackingData.put("fluidIntake", weightTracking);
            obj.put(userName, trackingData);
            FileWriter file = new FileWriter(PATH_TO_PROGRESS);
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject initializeTrackingData() {
        JSONObject trackingData = new JSONObject();
        trackingData.put("weightChange", new JSONArray());
        trackingData.put("calorieIntake", new JSONArray());
        trackingData.put("fluidIntake", new JSONArray());
        return trackingData;
    }

}
