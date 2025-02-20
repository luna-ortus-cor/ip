package miku;

import java.util.Objects;

/**
 * Abstract class for modelling locations (both physical and virtual)
 */
public abstract class Location {
    private String name;
    private String description;
    private final int id;

    /**
     * Initializes a new Location instance.
     *
     * @param name name of location
     * @param description description of location
     */
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = Objects.hash(System.currentTimeMillis());
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

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Location location = (Location) obj;
        return id == location.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
