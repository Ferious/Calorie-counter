package enums;

public enum ActivityType {
    CYCLING("Cycling"),
    RUNNING("Runing"),
    WALKING("Walking"),
    WORKOUT("Workout");

    private String name;

    ActivityType(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
