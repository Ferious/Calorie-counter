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
            int id = 0;
            if (array != null) {
                id = ((int)(long)((JSONObject)array.get(array.size()-1)).get("id"))+1;
            } else {
                array = new JSONArray();
            }
            JSONObject newActivity = new JSONObject();
            newActivity.put("id", id);
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
        JSONArray userActivities =  new JSONArray();
        List<Activity> result = new ArrayList<>();
        try {
            JSONObject allActivities = parseJsonObject(PATH_TO_ACTIVITIES);
            userActivities = (JSONArray) allActivities.get(user);
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


    public static void removeActivity(Activity activity){
        try {
            JSONObject allActivities = parseJsonObject(PATH_TO_ACTIVITIES);
            JSONArray userActivities = (JSONArray) allActivities.get("noro");
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
                allActivities.put("noro", userActivities);
                file.write(allActivities.toJSONString());
                file.flush();
            }
        } catch (IOException | ParseException e) {
            System.err.println(String.format("Problem with reading from %s", PATH_TO_ACTIVITIES));
            e.printStackTrace();
        }
    }

}
