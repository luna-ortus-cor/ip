package miku;

public abstract class Location {
    protected String name;
    protected String description;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getLocationDetails();

    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: " + description;
    }
}
