import meal.Drink;
import meal.Meal;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MealTest {
    private static final String EMPTY_ERROR_MESSAGE = "";

    @Test
    void addIntoMenu() {
        try {
            Meal meal = new Meal();
            meal.setName("halusky");
            meal.setType("2");
            meal.setKcal("890");
            meal.setProteins("20");
            meal.setFat("10");
            meal.setDrink(false);
            Drink drink = new Drink();
            drink.setName("cola");
            drink.setKcal("20");
            drink.setProteins("2");
            drink.setFat("5");
            drink.setDrink(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void addNewMEal1() {
        String name = "Halusky";
        try {
            Meal meal = new Meal();
            meal.setName(name);
            meal.setType("2");
            meal.setKcal("890");
            meal.setProteins("20");
            meal.setFat("10");
            meal.setDrink(false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void addNewMEal2() {
        String name = "Pork with potatoes";
        try {
            Meal meal = new Meal();
            meal.setName(name);
            meal.setType("2");
            meal.setKcal("450");
            meal.setProteins("77");
            meal.setFat("30");
            meal.setDrink(false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void addNewMEal3() {
        String name = "      potatoes";
        try {
            Meal meal = new Meal();
            meal.setName(name);
            meal.setType("2");
            meal.setKcal("120");
            meal.setProteins("30");
            meal.setFat("10");
            meal.setDrink(false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }


    @Test
    void addNewDrink1() {
        String name = "Cola2";
        try {
            Drink drink = new Drink();
            drink.setName(name);
            drink.setKcal("20");
            drink.setProteins("2");
            drink.setFat("5");
            drink.setDrink(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void addMenu() {
        try {
            Meal meal = new Meal();
            meal.setName("Kolac");
            meal.setType("2");
            meal.setKcal("890");
            meal.setProteins("20");
            meal.setFat("10");
            meal.setDrink(false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void addNewMEal() {
        try {
            Meal meal = new Meal();
            meal.setName("Kolac2");
            meal.setType("2");
            meal.setKcal("890");
            meal.setProteins("20");
            meal.setFat("10");
            meal.setDrink(false);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void addNewDrink() {
        try {
            Drink drink = new Drink();
            drink.setName("Cola2");
            drink.setKcal("20");
            drink.setProteins("2");
            drink.setFat("5");
            drink.setDrink(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }


    @Test
    void addNewDrink2() {
        try {
            Drink drink = new Drink();
            drink.setName("Royal cola");
            drink.setKcal("20");
            drink.setProteins("2");
            drink.setFat("5");
            drink.setDrink(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals(EMPTY_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    void addNewDrink3() {
        try {
            Drink drink = new Drink();
            drink.setName("Spri.t");
            drink.setKcal("20");
            drink.setProteins("2");
            drink.setFat("5");
            drink.setDrink(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals("Incorrect name of Meal!", e.getMessage());
        }
    }

    @Test
    void addNewDrink4() {
        try {
            Drink drink = new Drink();
            drink.setName(" ");
            drink.setKcal("20");
            drink.setProteins("2");
            drink.setFat("5");
            drink.setDrink(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals("Incorrect name of Meal!", e.getMessage());
        }
    }

    @Test
    void addNewDrink5() {
        try {
            Drink drink = new Drink();
            drink.setName("---");
            drink.setKcal("20");
            drink.setProteins("2");
            drink.setFat("5");
            drink.setDrink(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            assertEquals("Incorrect name of Meal!", e.getMessage());
        }
    }


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
    void setName1() {
        String name = "   .  EFDº";
        try {
            Meal meal = new Meal();
            meal.setName(name);
        } catch (Exception e) {
            assertEquals("Incorrect name of Meal!", e.getMessage());
        }
    }

    @Test
    void setName2() {
        String name = "     EFD º";
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
    void setProteins2() {
        String type = "0";
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

    @Test
    void setCombination() {
        String name = "    Tomato   ";
        String type = "5456w";
        try {
            Meal meal = new Meal();
            meal.setName(name);
            meal.setFat(type);
        } catch (Exception e) {
            assertEquals("Fat can contains only numeric characters!", e.getMessage());
        }
    }

    @Test
    void setCombination1() {
        String name = "    Tomato   ";
        String type = "2";
        try {
            Meal meal = new Meal();
            meal.setName(name);
            meal.setFat(type);
        } catch (Exception e) {
            assertEquals("Fat can contains only numeric characters!", e.getMessage());
        }
    }


    @Test
    void setCombination2() {
        String name = ".Tomato";
        String type = "www";
        try {
            Meal meal = new Meal();
            meal.setName(name);
            meal.setFat(type);
        } catch (Exception e) {
            assertEquals("Incorrect name of Meal!", e.getMessage());
        }
    }

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
    void checkInput8() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        boolean check = generateMeal.Checkinput("1000");
        assertTrue(check);
    }

    @Test
    void checkInput9() throws Exception {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        boolean check = generateMeal.Checkinput("3000");
        assertTrue(check);
    }


    @Test
    void isOKTest1() {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check =  generateMeal.isOk(2500, 3000);
        assertEquals(check, -1);
    }

    @Test
    void isOKTest2() {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check =  generateMeal.isOk(999, 3000);
        assertEquals(check, -1);
    }

    @Test
    void isOKTest3() {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check =  generateMeal.isOk(998, 1100);
        assertEquals(check, -1);
    }

    @Test
    void isOKTest4() {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check =  generateMeal.isOk(2600, 2500);
        assertEquals(check, 1);
    }

    @Test
    void isOKTest5() {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check =  generateMeal.isOk(2100, 2500);
        assertEquals(check, -1);
    }

    @Test
    void isOKTest6() {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check =  generateMeal.isOk(1900, 1849);
        assertEquals(check, 1);
    }
    @Test
    void isOKTest7() {
        meal.GenerateMeal generateMeal = new meal.GenerateMeal("ivet");
        int check =  generateMeal.isOk(1900, 1799);
        assertEquals(check, 1);
    }
}
