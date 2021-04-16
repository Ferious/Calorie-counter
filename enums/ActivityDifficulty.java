package enums;

public enum ActivityDifficulty {
    EASY("EASY"),
    MODERATE("MODERATE"),
    HARD("HARD");

    private String name;

    ActivityDifficulty(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
