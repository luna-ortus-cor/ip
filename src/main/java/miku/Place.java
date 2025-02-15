package miku;

/**
 * Place class that stores relevant place related properties
 */
public class Place extends Location {
    private String address;
    private double latitude;
    private double longitude;

    /**
     * Creates a new Place instance.
     *
     * @param name place name
     * @param description place description
     * @param address place address
     * @param latitude place address latitude
     * @param longitude place address longitude
     */
    public Place(String name, String description, String address, double latitude, double longitude) {
        super(name, description);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String getLocationDetails() {
        return toString() + "\nAddress: " + address + "\nLatitude: " + latitude + "\nLongitude: " + longitude;
    }

    @Override
    public String toString() {
        return String.format("Place | %s | %s | %s | %f | %f",
            getName(),
            getDescription(),
            getAddress(),
            getLatitude(),
            getLongitude()
        );
    }
}
