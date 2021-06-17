package meal;

import database.DatabaseUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AddNewMenu {

    String userName;
    public AddNewMenu(String userName) {
        this.userName = userName;
    }

    public static void addIntoMenu() {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Do you wish add a meal write [yes for meal / no for drink]?");
            String option = br.readLine();
            if(option.equalsIgnoreCase("yes") || option.equalsIgnoreCase("y")) {
                addNewMEal();
            } else if(option.equalsIgnoreCase("no") || option.equalsIgnoreCase("n")){
                addNewDrink();
            } else {
                throw new Exception("Unknown option!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addNewMEal(){
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Meal meal = new Meal();
            System.out.println("Name of the Meal:");
            String nameMeal = br.readLine();
            meal.setName(nameMeal);

            System.out.println("Enter type of meal:");
            System.out.println("Breakfast =>    1 ");
            System.out.println("Lunch     =>    2");
            System.out.println("Dinner    =>    3");
            System.out.println("Lead      =>    4");
            System.out.println("Snack     =>    5");
            meal.setType(br.readLine());

            System.out.println("Enter Calories:");
            meal.setKcal(br.readLine());

            System.out.println("Enter Proteins:");
            meal.setProteins(br.readLine());

            System.out.println("Enter fat");
            meal.setFat(br.readLine());

            meal.setDrink(false);
            meal.setIngredients("");
            DatabaseUtils.addNewMeal(meal);
            System.out.println(String.format(" Your meal %s was successfully added.", nameMeal));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static void addNewDrink() {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Drink drink = new Drink();
            System.out.println("Name of the Drink:");
            String drinkName = br.readLine();
            drink.setName(drinkName);

            System.out.println("Enter Calories:");
            drink.setKcal(br.readLine());

            System.out.println("Enter Proteins:");
            drink.setProteins(br.readLine());

            System.out.println("Enter fat");
            drink.setFat(br.readLine());

            drink.setDrink(true);

            DatabaseUtils.addNewDrink(drink);
            System.out.println(String.format(" Your drink %s was successfully added.", drink));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
