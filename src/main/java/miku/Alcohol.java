package miku;

/**
 * Alcohol class storing date, drink type, drink name, quantity of drink
 */
public class Alcohol {
    private String date;
    private String drink;
    private String name;
    private int quantity;

    /**
     * Initializes a new alcohol instance.
     *
     * @param date string of date
     * @param drink string of drink type
     * @param name string of drink name
     * @param qty number of standard drinks
     */
    public Alcohol(String date, String drink, String name, int quantity) {
        this.date = date;
        this.drink = drink;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return date + " | " + drink + " | " + name + " | " + quantity;
    }

    public String getDrink() {
        return drink;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}

