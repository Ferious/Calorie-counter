package meal;

public class Meal {
    private Integer id;
    private String type;
    private String name;
    private String kcal;
    private String proteins;
    private String fat;
    private String ingredients;
    private Boolean isDrink;


    public Meal(){};

    public boolean setName(String name) throws Exception {
        if(!name.trim().matches("[a-zA-Z0-9\\s]*")){
            throw new Exception("Incorrect name of Meal!");
        }
        this.name = name;
        return true;
    }

    public boolean setType(String type) throws Exception {
        if(!type.matches("[1-5]") || type.length() > 1){
            throw new Exception("Wrong type!");
        }
        this.type = type;
        return true;
    }

    public boolean setKcal(String kcal) throws Exception {
        if(!kcal.matches("[0-9]+"))
            throw new Exception("Calories can contains only numeric characters!");
        this.kcal = kcal;
        return true;
    }

    public boolean setProteins(String proteins) throws Exception {
        if(!proteins.matches("[0-9]+"))
            throw new Exception("Proteins can contains only numeric characters!");
        this.proteins = proteins;
        return true;
    }

    public boolean setFat(String fat) throws Exception {
        if(!fat.matches("[0-9]+"))
            throw new Exception("Fat can contains only numeric characters!");
        this.fat = fat;
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getKcal() {
        return kcal;
    }

    public String getProteins() {
        return proteins;
    }


    public String getFat() {
        return fat;
    }

    public Boolean getDrink() {
        return isDrink;
    }

    public void setDrink(Boolean drink) {
        isDrink = drink;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
