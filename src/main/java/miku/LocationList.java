package miku;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class to store array of locations and handle operations on locations in list.
 */
public class LocationList {
    private static final String FILE_PATH = Constants.FILEPATH_LOCATIONLIST;
    private ArrayList<Location> locationList = new ArrayList<Location>();
    private Ui ui;

    /**
     * Instantiates a new LocationList instance taking in a Ui ui.
     *
     * @param ui a Ui instance
     */
    public LocationList(Ui ui) {
        this.ui = ui;
    }

    /**
     * Returns an arraylist of locations in the LocationList.
     *
     * @return an arraylist of locations in the LocationList
     */
    public ArrayList<Location> getList() {
        return this.locationList;
    }

    /**
     * Load locations from a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void loadLocations(Storage s) {
        this.locationList = s.readLocations(FILE_PATH);
    }

    /**
     * Save locations to a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void saveLocations(Storage s) {
        s.writeLocations(this.locationList, FILE_PATH);
    }

    /**
     * Add a location object to the list of locations.
     *
     * @param location a location object to be added to the list of locations
     */
    public void addLocation(Location location) {
        locationList.add(location);
        ui.printAddLocationMsg(location, locationList.size());
    }

    /**
     * Search locations by name, allowing partial matches in any of the name fields.
     *
     * @param searchString the search string to be matched (either partially or fully)
     * @return an arraylist of location objects containing the search string
     */
    public ArrayList<Location> findLocation(String searchString) {
        return new ArrayList<Location>(locationList.stream()
                .filter(loc -> loc.getName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList()));
    }

    /**
     * Print all locations in list.
     */
    public void displayAllLocations() {
        for (Location loc : locationList) {
            System.out.println(loc.getLocationDetails());
        }
        System.out.println();
    }

    /**
     * Delete the location at a specified index.
     *
     * @param idx the index of the location in the list
     */
    public void delete(int idx) {
        assert idx >= 0 : "Index must be non-negative";
        try {
            Location l = locationList.get(idx);
            locationList.remove(idx);
            ui.printDeleteLocationMsg(l, locationList.size());
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Delete all locations in the list.
     */
    public void deleteAll() {
        locationList.clear();
        ui.printDeleteAllLocationsMsg();
    }

    /**
     * Handles error messages.
     *
     * @param code int denoting the error generated
     */
    private void handleError(int code) {
        ui.printErrorMsg(code);
    }
}

