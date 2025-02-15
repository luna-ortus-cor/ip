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

    public ArrayList<Location> getList() {
        return this.locationList;
    }

    public void loadLocations(Storage s) {
        this.locationList = s.readLocations(FILE_PATH);
    }

    public void saveLocations(Storage s) {
        s.writeLocations(this.locationList, FILE_PATH);
    }

    public void addLocation(Location location) {
        locationList.add(location);
        ui.printAddLocationMsg(location, locationList.size());
    }

    public ArrayList<Location> findLocation(String searchString) {
        return new ArrayList<Location>(locationList.stream()
                .filter(loc -> loc.getName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList()));
    }

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
        assert idx >= 0: "Index must be non-negative";
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

