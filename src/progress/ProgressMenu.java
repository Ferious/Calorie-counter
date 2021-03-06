package progress;

import abstractClasses.Menu;
import database.DatabaseUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProgressMenu extends Menu {
    String userName;

    double weight;
    int calories;
    int fluid;

    String date;

    public ProgressMenu(String userName) {
        this.userName = userName;
    }

    @Override
    public void print() {
        System.out.println("±------------------Progress Menu------------------±");
        System.out.println("± 1. -> Change weight                             ±");
        System.out.println("± 2. -> Calorie intake                            ±");
        System.out.println("± 3. -> Fluid intake                              ±");
        System.out.println("± X. -> Exit to ClientMenu                        ±");
        System.out.println("±-------------------------------------------------±");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1" : updateWeight(); break;
                case "2" : updateCalories();  break;
                case "3" : updateFluid();  break;
                case "X" : case "x" : exit(); break;
                default  : System.err.println(String.format("%s is a unknown option", option)); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateWeight(){
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter new weight (kg):");
            String weight = br.readLine();
            if (setWeight(weight)) {
                calculateDateTime();
                DatabaseUtils.updateWeightChange(this.weight, date, userName);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateCalories(){
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter calorie intake (kcal):");
            String calories = br.readLine();
            if (setCalories(calories)) {
                calculateDateTime();
                DatabaseUtils.updateCalorieIntake(this.calories, date, userName);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateFluid(){
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter fluid intake (milliliters):");
            String fluid = br.readLine();
            if (setFluid(fluid)) {
                calculateDateTime();
                DatabaseUtils.updateFluidIntake(this.fluid, date, userName);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean setWeight(String weight) throws Exception {
        if(!weight.matches("[0-9]+(\\.[0-9][0-9]?)?"))
            throw new Exception("Weight (kg) can contain only positive numeric and two decimal characters!");
        this.weight = Double.parseDouble(weight);
        return true;
    }

    public boolean setCalories(String calories) throws Exception {
        if(!calories.matches("[0-9]+"))
            throw new Exception("Calories (kcal) can contain only positive numeric characters!");
        this.calories = Integer.parseInt(calories);
        return true;
    }

    public boolean setFluid(String fluid) throws Exception {
        if(!fluid.matches("[0-9]+"))
            throw new Exception("Fluid (mililiters) can contain only positive numeric characters!");
        this.fluid = Integer.parseInt(fluid);
        return true;
    }

    public void calculateDateTime(){
        LocalDateTime date = (java.time.LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY HH:mm");
        this.setDate(date.format(formatter));
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    public int getCalories() { return calories; }

    public int getFluid() {
        return fluid;
    }

}
