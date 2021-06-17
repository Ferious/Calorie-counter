package main;

import database.DatabaseUtils;
import meal.Meal;
import org.junit.jupiter.api.Test;
import users.User;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author - annonymous
 * @class - this is test class for testing the whole functionality of class MainMenu
 */

class AdminMenuTest {
    private static final String EMPTY_ERROR_MESSAGE = "";

    @Test
    void addNewMeal() {
        String name = "Hranolky";
        try {
            Meal meal = new Meal();
            meal.setName(name);
            meal.setType("2");
            meal.setKcal("125");
            meal.setProteins("33");
            meal.setFat("10");
            meal.setDrink(false);
            DatabaseUtils.addNewMeal(meal);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void deleteMealByName() {
        String mealName = "Hranolky";
        AdminMenu ad = new AdminMenu();
        String message = ad.deleteMealByName(mealName);
        assertEquals(String.format("Meal with name %s was successfully deleted!", mealName), message);
    }

    @Test
    void deleteMealWithIncorrectId() {
        AdminMenu ad = new AdminMenu();
        String message = ad.deleteMealById(33333);
        assertEquals("Record was not successfully deleted!", message);
    }

    @Test
    void deleteMealWithIncorrectName() {
        AdminMenu ad = new AdminMenu();
        String message = ad.deleteMealByName("FazulkaAAAA");
        assertEquals("Record was not successfully deleted!", message);
    }

    @Test
    void deleteMealWithCorrectId() {
        Meal meal = new Meal();
        int lastIn;
        try {
            meal.setName("pokus");
            meal.setType("2");
            meal.setKcal("125");
            meal.setProteins("33");
            meal.setFat("10");
            meal.setDrink(false);
            DatabaseUtils.addNewMeal(meal);
            lastIn = DatabaseUtils.getallfood().size();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        AdminMenu ad = new AdminMenu();
        String message = ad.deleteMealById(lastIn);
        assertEquals(String.format("Meal with id %d was successfully deleted!", lastIn), message);
    }

    @Test
    void mealAddAndDelete() {
        String mealName = "pokusA";
        Meal meal = new Meal();
        try {
            meal.setName(mealName);
            meal.setType("2");
            meal.setKcal("124");
            meal.setProteins("31");
            meal.setFat("10");
            meal.setDrink(false);
            DatabaseUtils.addNewMeal(meal);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        AdminMenu ad = new AdminMenu();
        String message = ad.deleteMealByName(mealName);
        assertEquals(String.format("Meal with name %s was successfully deleted!", mealName), message);
    }


}