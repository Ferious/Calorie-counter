package meal;


import org.json.simple.JSONObject;

public class MealPrinter {

    public static void print(JSONObject food) {
        if (food == null) {
            throw new NullPointerException("food cant be null");
        }
        if(food.get("Name") != null) {
            String typeOfFood = ChekType(Integer.parseInt((String) food.get("Type")));
            System.out.println("Id:              " + food.get("id"));
            System.out.println("Name:            " + food.get("Name"));
            System.out.println("Type:            " + typeOfFood);
            System.out.println("Kcal:            " + food.get("Kcal"));
            System.out.println("Proteins:        " + food.get("Proteins"));
            System.out.println("Fat:             " + food.get("Fat")+"\n");

        }
    }

    private static String ChekType(Integer typeOfFood){
        String result = "";
        switch (typeOfFood){
            case 1:
                result = "Breakfast";
                break;
            case 2:
                result = "Lunch";
                break;
            case 3:
                result = "Dinner";
                break;
            case 4:
                result = "Lead";
                break;
            case 5:
                result = "Snack";
                break;
        }
        return result;

    }
}
