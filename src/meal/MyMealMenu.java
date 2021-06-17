package meal;

import abstractClasses.Menu;
import main.AdminMenu;

public class MyMealMenu extends Menu {
    private String userName;

    public MyMealMenu(String userName) {
        this.userName = userName;
    }

    @Override
    public void print() {
        System.out.println("±--------------------Meal Menu--------------------±");
        System.out.println("± 1. ->$ List all menus                           ±");
        System.out.println("± 2. ->$ Make menu for day                        ±");
        System.out.println("± 3. ->$ Add my own Meal                          ±");
        System.out.println("± X. ->$ Exit to Client Menu                      ±");
        System.out.println("±-------------------------------------------------±");
    }


    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1" : new AdminMenu().listAllMeals(); break;
                case "2" : new GenerateMeal(userName).makeMenuforDay();break;
                case "3" : new AddNewMenu(userName).addIntoMenu();break;
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
