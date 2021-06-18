package meals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyMealTest {
    @Test
    void checkInput1() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        boolean check = generateMeal.Checkinput("5000");
        assertFalse(check);
    }

    @Test
    void checkInput2() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        boolean check = generateMeal.Checkinput("-300");
        assertFalse(check);
    }

    @Test
    void checkInput3() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        boolean check = generateMeal.Checkinput("0");
        assertFalse(check);
    }

    @Test
    void checkInput4() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        boolean check = generateMeal.Checkinput("999");
        assertFalse(check);
    }

    @Test
    void checkInput5() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        boolean check = generateMeal.Checkinput("fskd");
        assertFalse(check);
    }

    @Test
    void checkInput6() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        boolean check = generateMeal.Checkinput(" ");
        assertFalse(check);
    }

    @Test
    void checkInput7() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        boolean check = generateMeal.Checkinput("098");
        assertFalse(check);
    }

    @Test
    void isOKTest1() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check = generateMeal.isOk(2500, 3000);
        assertEquals(check, -1);
    }

    @Test
    void isOKTest2() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check = generateMeal.isOk(999, 3000);
        assertEquals(check, -1);
    }

    @Test
    void isOKTest3() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check = generateMeal.isOk(998, 1100);
        assertEquals(check, -1);
    }

    @Test
    void isOKTest4() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check = generateMeal.isOk(2600, 2500);
        assertEquals(check, 1);
    }

}
