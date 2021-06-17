package meal;

/**
 * @author - annonymous team
 * @class - extended class from Meal using the same values
 */

public class Drink extends Meal {

    public Drink() {}

    public void setId(Integer id) {
        super.setId(id);
    }

    public boolean setName(String drinkName) throws Exception {
        return super.setName(drinkName);
    }

    public boolean setKcal(String kcal) throws Exception {
        return super.setKcal(kcal);
    }

    public boolean setProteins(String proteins) throws Exception {
        return super.setProteins(proteins);
    }

    public boolean setFat(String fat) throws Exception {
        return super.setFat(fat);
    }

    public Integer getId() { return super.getId(); }

    public String getDrinkName() { return super.getName(); }

    public String getKcal() {
        return super.getKcal();
    }

    public String getProteins() {
        return super.getProteins();
    }

    public String getFat() {
        return super.getFat();
    }

    public Boolean getDrink() {
        return super.getDrink();
    }

}
