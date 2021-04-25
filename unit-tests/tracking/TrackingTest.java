package tracking;

import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackingTest {

    @Test
    void setCorrectWeight() {
        TrackingMenu tracking = new TrackingMenu("test");
        String weight = "78.45";
        try {
            assertTrue(tracking.setWeight(weight));
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    void setWrongWeight1() {
        TrackingMenu tracking = new TrackingMenu("test");
        String weight = "78.345";
        try {
            tracking.setWeight(weight);
        } catch (Exception e) {
            assertEquals("Weight (kg) can contain only positive numeric and two decimal characters!", e.getMessage());
        }
    }

    @Test
    void setWrongWeight2() {
        TrackingMenu tracking = new TrackingMenu("test");
        String weight = "7s";
        try {
            tracking.setWeight(weight);
        } catch (Exception e) {
            assertEquals("Weight (kg) can contain only positive numeric and two decimal characters!", e.getMessage());
        }
    }

    @Test
    void setWrongWeight3() {
        TrackingMenu tracking = new TrackingMenu("test");
        String weight = "-20";
        try {
            tracking.setWeight(weight);
        } catch (Exception e) {
            assertEquals("Weight (kg) can contain only positive numeric and two decimal characters!", e.getMessage());
        }
    }

    @Test
    void setWrongWeight4() {
        TrackingMenu tracking = new TrackingMenu("test");
        String weight = "75.";
        try {
            tracking.setWeight(weight);
        } catch (Exception e) {
            assertEquals("Weight (kg) can contain only positive numeric and two decimal characters!", e.getMessage());
        }
    }

    @Test
    void setWrongWeight5() {
        TrackingMenu tracking = new TrackingMenu("test");
        String weight = ".45";
        try {
            tracking.setWeight(weight);
        } catch (Exception e) {
            assertEquals("Weight (kg) can contain only positive numeric and two decimal characters!", e.getMessage());
        }
    }

    @Test
    void setCorrectCalories() {
        TrackingMenu tracking = new TrackingMenu("test");
        String calories = "520";
        try {
            assertTrue(tracking.setCalories(calories));
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    void setWrongCalories1() {
        TrackingMenu tracking = new TrackingMenu("test");
        String calories = "75.5";
        try {
            tracking.setCalories(calories);
        } catch (Exception e) {
            assertEquals("Calories (kcal) can contain only positive numeric characters!", e.getMessage());
        }
    }

    @Test
    void setWrongCalories2() {
        TrackingMenu tracking = new TrackingMenu("test");
        String calories = "7s";
        try {
            tracking.setCalories(calories);
        } catch (Exception e) {
            assertEquals("Calories (kcal) can contain only positive numeric characters!", e.getMessage());
        }
    }

    @Test
    void setWrongCalories3() {
        TrackingMenu tracking = new TrackingMenu("test");
        String calories = "-200";
        try {
            tracking.setCalories(calories);
        } catch (Exception e) {
            assertEquals("Calories (kcal) can contain only positive numeric characters!", e.getMessage());
        }
    }

    @Test
    void setCorrectFluid() {
        TrackingMenu tracking = new TrackingMenu("test");
        String fluid = "520";
        try {
            assertTrue(tracking.setFluid(fluid));
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    void setWrongFluid1() {
        TrackingMenu tracking = new TrackingMenu("test");
        String fluid = "75.5";
        try {
            tracking.setCalories(fluid);
        } catch (Exception e) {
            assertEquals("Calories (kcal) can contain only positive numeric characters!", e.getMessage());
        }
    }

    @Test
    void setWrongFluid2() {
        TrackingMenu tracking = new TrackingMenu("test");
        String fluid = "7s";
        try {
            tracking.setCalories(fluid);
        } catch (Exception e) {
            assertEquals("Calories (kcal) can contain only positive numeric characters!", e.getMessage());
        }
    }

    @Test
    void setWrongFluid3() {
        TrackingMenu tracking = new TrackingMenu("test");
        String fluid = "-200";
        try {
            tracking.setCalories(fluid);
        } catch (Exception e) {
            assertEquals("Calories (kcal) can contain only positive numeric characters!", e.getMessage());
        }
    }

    @Test
    void setGetDate() {
        TrackingMenu tracking = new TrackingMenu("test");
        LocalDateTime date = (java.time.LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY HH:mm");
        tracking.setDate(date.format(formatter));
        assertEquals(date.format(formatter), tracking.getDate());

    }

}