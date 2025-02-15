package miku;

public class Website extends Location {
    private String url;

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
