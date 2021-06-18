package meals;

import meal.Meal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MealTest {

    @Test
    void setName() {
        String name = "###$R#$R$#%#%#R#EWEFDº";
        try {
            Meal meal = new Meal();
            meal.setName(name);
        } catch (Exception e) {
            assertEquals("Incorrect name of Meal!", e.getMessage());
        }
    }

    @Test
    void setType() {
        String type = "23";
        try {
            Meal meal = new Meal();
            meal.setType(type);
        } catch (Exception e) {
            assertEquals("Wrong type!", e.getMessage());
        }
    }

    @Test
    void setType2() {
        String type = "6";
        try {
            Meal meal = new Meal();
            meal.setType(type);
        } catch (Exception e) {
            assertEquals("Wrong type!", e.getMessage());
        }
    }

    @Test
    void setKcal() {
        String type = "-122";
        try {
            Meal meal = new Meal();
            meal.setKcal(type);
        } catch (Exception e) {
            assertEquals("Calories can contains only numeric characters!", e.getMessage());
        }
    }

    @Test
    void setKcal1() {
        String type = "qawse";
        try {
            Meal meal = new Meal();
            meal.setKcal(type);
        } catch (Exception e) {
            assertEquals("Calories can contains only numeric characters!", e.getMessage());
        }
    }

    @Test
    void setKcal2() {
        String type = "1ws23";
        try {
            Meal meal = new Meal();
            meal.setKcal(type);
        } catch (Exception e) {
            assertEquals("Calories can contains only numeric characters!", e.getMessage());
        }
    }

    @Test
    void setProteins() {
        String type = " ";
        try {
            Meal meal = new Meal();
            meal.setProteins(type);
        } catch (Exception e) {
            assertEquals("Proteins can contains only numeric characters!", e.getMessage());
        }
    }

    @Test
    void setFat() {
        String type = "5456w";
        try {
            Meal meal = new Meal();
            meal.setFat(type);
        } catch (Exception e) {
            assertEquals("Fat can contains only numeric characters!", e.getMessage());
        }
    }

}
