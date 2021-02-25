package database;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    private static final String PREFIX_PATH_USERS = "src/database/users.json";
    // Private constructor to avoid client applications to use constructor
    // Singleton design pattern
    private DatabaseUtils() {}

    public static JSONArray parseJson(String path) throws IOException, ParseException {
        final JSONParser parser = new JSONParser();
        final JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));
        return jsonArray;
    }

    //TODO - think about interface for database operations;
    public static List<String> getAllUsersNames(){
        final List<String> userNames = new ArrayList<>();
        try {
            final JSONArray users = parseJson(PREFIX_PATH_USERS);
            for(Object o : users) {
                JSONObject jsonObject = (JSONObject) o;
                String strName = (String) jsonObject.get("name");
                userNames.add(strName);
            }
        } catch (IOException | ParseException e) {
            System.out.println(String.format("Problem with reading from %s", PREFIX_PATH_USERS));
            e.printStackTrace();
        }
        return userNames;
    }

    public static boolean checkIfUserNameExist(String userName) {
        boolean exist = false;
        try {
            final JSONArray users = parseJson(PREFIX_PATH_USERS);
            for(Object o : users) {
                JSONObject jsonObject = (JSONObject) o;
                String strName = (String) jsonObject.get("loginName");
                if(strName.equalsIgnoreCase(userName))
                    exist = true;
            }
        } catch (IOException | ParseException e) {
            System.out.println(String.format("Problem with reading from %s", PREFIX_PATH_USERS));
            e.printStackTrace();
        }
        return exist;
    }

    public static void writeNewClient(String loginName, String firstName, String lastName, String age, String password) {
        try {
            JSONArray array = parseJson(PREFIX_PATH_USERS);
            JSONObject client = new JSONObject();
            client.put("loginName", loginName);
            client.put("firstName", firstName);
            client.put("lastName", lastName);
            client.put("age", age);
            client.put("password", password);
            array.add(client);
            FileWriter file = new FileWriter(PREFIX_PATH_USERS);
            file.write(array.toJSONString());
            file.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
