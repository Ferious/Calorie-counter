package meal;

import database.DatabaseUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateMeal {
    private static final int MAX_NUMBER = 3000;
    private static final int MIN_NUMBER = 1000;

    String userName;
    public GenerateMeal(String userName) {
        this.userName = userName;
    }

    public void food_database(){
        JSONArray food = DatabaseUtils.getallfood();
        for(Object o : food){
            JSONObject jsonObject = (JSONObject) o;
            MealPrinter.print(jsonObject);

        }
    }

    public Boolean Checkinput(String number) throws Exception {
        if(!number.matches("[0-9]+")) {
            System.out.println("Wrong input! Have to be numeric! ");
            return false;
        }
        else{
            int kcal = Integer.parseInt(number);
            if(kcal < MIN_NUMBER){
                System.out.println("Please, Don't starve yourself!");
                return false;
            }
            else if(kcal > MAX_NUMBER){
                System.out.println("Please, Don't overeat!");
                return false;
            }
            else {
                return true;
            }
        }
    }

    public void makeMenuforDay() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("How many calories do you want to have daily?\n");
        System.out.println("Your daily income have to be in range 1000 to 3000");
        String calories = br.readLine();
        if(Checkinput(calories)){
            int kcal = Integer.parseInt(calories);
            findMenu(kcal);
        }
    }

    public int isOk(int sum, int kcal) {
        int min = (kcal - 50) >= MIN_NUMBER ? (kcal - 50) : MIN_NUMBER;
        int max = (kcal + 50) <= MAX_NUMBER ? (kcal + 50) : MAX_NUMBER;
        if (sum > max)
            return 1;
        else if(sum < min){
            return -1;
        }
        return 0;
    }

    public List<Map.Entry<Integer, JSONObject>> TryFind(int kcal) throws Exception {
        if(MIN_NUMBER > kcal || MAX_NUMBER < kcal){
            throw new Exception("Wrong number!");
        }
        int type = 1;
        int countCalories = 0;
        List<Map.Entry<Integer, JSONObject>> foods = new ArrayList();
        boolean isOk  = (kcal - 50  <= countCalories && countCalories <= kcal + 50);
        int tmp = 0;
        while(!isOk && tmp <= 5 ){
            Map.Entry<Integer, JSONObject> food = menu(type);
            int status = isOk(countCalories +  food.getKey(), kcal);
            if (status == 0){
                isOk = true;
                foods.add(food);
                countCalories += food.getKey();
            }
            else if(status == -1) {
                foods.add(food);
                countCalories += food.getKey();
            }
            else{
                tmp += 1;
            }
            type = ((type) % 5) + 1;
        }
        if(countCalories < MIN_NUMBER){
            foods.add(menu(5));
        }
        return foods;
    }


    public void findMenu(int kcal) throws Exception {
        List<Map.Entry<Integer, JSONObject>> foods = TryFind(kcal);
        int count = 0;
        System.out.println("******** Your Menu for TODAY ********\n");
        for(Map.Entry<Integer, JSONObject> food: foods){
            MealPrinter.print(food.getValue());
            count += food.getKey();
        }
        System.out.println("Your income: " + count);

    }

    public Map.Entry<Integer, JSONObject> menu(int type){
        JSONArray food = DatabaseUtils.getallfood();
        List<JSONObject> objectList = new ArrayList<>();
        for(Object o : food){
            JSONObject jsonObject = (JSONObject) o;
            int tmp = Integer.parseInt(jsonObject.get("Type").toString());
            if(tmp == type){
                objectList.add(jsonObject);
            }
        }
        int random = (int) (Math.random() * ( objectList.size() - 1 ));
        JSONObject he = objectList.get(random);
        return new AbstractMap.SimpleEntry<>(Integer.parseInt(he.get("Kcal").toString()), he);
    }

}
