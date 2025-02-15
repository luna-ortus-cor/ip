package miku;

/**
 * Website class that stores relevant website related properties
 */
public class Website extends Location {
    private String url;

    /**
     * Creates a new Website instance.
     *
     * @param name website name
     * @param description website description
     * @param url website url
     */
    public Website(String name, String description, String url) {
        super(name, description);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String getLocationDetails() {
        return toString() + "\nURL: " + url;
    }

    @Override
    public String toString() {
        return String.format("Website | %s | %s | %s",
            getName(),
            getDescription(),
            getUrl()
        );
    }
}
