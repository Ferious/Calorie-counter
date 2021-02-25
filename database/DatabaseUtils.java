package database;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
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

    //TODO - think about interface for database operations;
    public static List<String> getAllUsersNames(){
        final List<String> userNames = new ArrayList<>();
        final String pathName = "src/database/users.json";
        try {
            final JSONParser parser = new JSONParser();
            final JSONArray users = (JSONArray) parser.parse(new FileReader(PREFIX_PATH_USERS));
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
            final JSONParser parser = new JSONParser();
            final JSONArray users = (JSONArray) parser.parse(new FileReader(PREFIX_PATH_USERS));
            for(Object o : users) {
                JSONObject jsonObject = (JSONObject) o;
                String strName = (String) jsonObject.get("name");
                if(strName.equalsIgnoreCase(userName))
                    exist = true;
            }
        } catch (IOException | ParseException e) {
            System.out.println(String.format("Problem with reading from %s", PREFIX_PATH_USERS));
            e.printStackTrace();
        }
        return exist;
    }
}
