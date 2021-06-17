package main;

import abstractClasses.Menu;
import database.DatabaseUtils;
import meal.AddNewMenu;
import meal.MealPrinter;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author - annonymous team
 * @class - menu with multiple choices for administrator of application
 */

public class AdminMenu extends Menu {

    public AdminMenu() { }

    @Override
    public void print() {
        System.out.println("±--------------------Admin Menu-------------------±");
        System.out.println("± 1. ->$ List all users                           ±");
        System.out.println("± 2. ->$ List all meals                           ±");
        System.out.println("± 3. ->$ Add new meal                             ±");
        System.out.println("± 4. ->$ Delete meal                              ±");
        System.out.println("± X. ->$ Exit to MainMenu                         ±");
        System.out.println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1" : listAllUsers(); break;
                case "2" : listAllMeals(); break;
                case "3" : AddNewMenu.addIntoMenu(); break;
                case "4" : deleteMeal(); break;
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listAllUsers() throws IOException {
        List<String> users = DatabaseUtils.getAllUsersNames();
        if (users.isEmpty()) {
            System.out.println("We don't have any active users! ");
        }
        printAllUsers(users);
    }

    public void printAllUsers(List<String> users) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        int count = 10;
        int offset = 0;
        int pageCount = 1;
        count = (count > users.size()) ? users.size() : count;
        while(true) {
            System.out.println(String.format("********************** List of Users Page %d ********************** ", pageCount));
            for(int idx = offset; idx < count+offset; idx++) {
                System.out.println(users.get(idx));
            }
            System.out.println("Go -> next/previous/x");
            input = br.readLine();
            if(input.equals("next")){
                if(offset+count >= users.size()) {
                    System.out.println("End list of Users, you can go previous!");
                    continue;
                } else {
                    offset += count;
                    pageCount++;
                }
                continue;
            }
            if(input.equals("previous")){
                if(offset > 0) {
                    offset -= count;
                    pageCount--;
                }
                continue;
            }
            else{
                System.out.println("********************************************************************");
                break;
            }
        }
    }

    public void listAllMeals() throws IOException {
        List<JSONObject> meals = DatabaseUtils.getallfood();
        if (meals.isEmpty()) {
            System.out.println("We don't have any meal! ");
        }
        printAllMeal(meals);
    }

    public void printAllMeal(List<JSONObject> meal) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        int count = 5;
        int offset = 0;
        int pageCount = 1;
        count = (count > meal.size()) ? meal.size() : count;
        while(true) {
            System.out.println(String.format("********************** List of Meal Page %d ********************** ", pageCount));
            for(int idx = offset; idx < count+offset; idx++) {
                MealPrinter.print(meal.get(idx));
            }
            System.out.println("Go -> next/previous/x");
            input = br.readLine();
            if(input.equals("next")){
                if(offset+count >= meal.size()) {
                    System.out.println("End list of Meal, you can go previous!");
                    continue;
                } else {
                    offset += count;
                    pageCount++;
                }
                continue;
            }
            if(input.equals("previous")){
                if(offset > 0) {
                    offset -= count;
                    pageCount--;
                }
                continue;
            }
            else{
                System.out.println("********************************************************************");
                break;
            }
        }
    }

    public void deleteMeal() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        String message;
        System.out.println("Would you delete Meal by [id or name] -> [Yes/No]");
        input = br.readLine();
        if(input.equalsIgnoreCase("Yes")) {
            System.out.println("Please type Meal id:");
            input = br.readLine();
            int mealId = Integer.parseInt(input);
            message = deleteMealById(mealId);
            System.out.println(message);
         } else if(input.equalsIgnoreCase("No")) {
            System.out.println("Please type Meal name:");
            input = br.readLine();
            message = deleteMealByName(input);
            System.out.println(message);
        } else {
            System.err.println("Unknown option!");
        }
    }

    public String deleteMealById(int mealId) {
        return DatabaseUtils.deleteMealById(mealId) ? String.format("Meal with id %d was successfully deleted!", mealId) : "Record was not successfully deleted!";
    }

    public String deleteMealByName(String mealName) {
        return DatabaseUtils.deleteMealByName(mealName) ? String.format("Meal with name %s was successfully deleted!", mealName) : "Record was not successfully deleted!";
    }

}
