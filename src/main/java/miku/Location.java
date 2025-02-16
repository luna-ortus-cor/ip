package miku;

/**
 * Abstract class for modelling locations (both physical and virtual)
 */
public abstract class Location {
    private String name;
    private String description;

    /**
     * Initializes a new Location instance.
     *
     * @param name name of location
     * @param description description of location
     */
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
