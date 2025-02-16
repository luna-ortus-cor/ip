package miku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to track time spent on different activities.
 */
public class TimeTracker {
    private static final String FILE_NAME = Constants.FILEPATH_TIME_TRACKER;
    private static ArrayList<Activity> activities = new ArrayList<>();

    /**
     * Initialize the time tracking file.
     */
    public static void initializeTracker() {
        Storage s = new Storage(new Ui());
        s.checkFilePathExistsElseCreate(FILE_NAME);
    }

    /**
     * Save activity to file.
     *
     * @param activity activity to be saved
     */
    public static void saveActivityToFile(Activity activity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(activity.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving activity to file.");
        }
    }

    /**
     * Load activities from file.
     */
    private static void loadActivitiesFromFile() {
        activities.clear(); // Clear the current list to avoid duplication
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split by the " | " delimiter
                String[] parts = line.split(" \\| ");
                if (parts.length == 2) {
                    // Parse start and end date/time from the first part
                    String[] dateTimeParts = parts[0].split(" - ");
                    if (dateTimeParts.length == 2) {
                        String[] startParts = dateTimeParts[0].split(" ");
                        String[] endParts = dateTimeParts[1].split(" ");

                        if (startParts.length == 2 && endParts.length == 2) {
                            String startDate = startParts[0];
                            String startTime = startParts[1];
                            String endDate = endParts[0];
                            String endTime = endParts[1];
                            String activityName = parts[1].trim();

                            // Create and add the activity to the list
                            activities.add(new Activity(startDate, startTime, endDate, endTime, activityName));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading activities from file: " + e.getMessage());
        }
    }

    /**
     * Display statistics.
     */
    public static void displayStatistics() {
        loadActivitiesFromFile();
        System.out.println("\nStatistics:");
        Map<String, Integer> activityCount = new HashMap<>();
        long totalMinutes = 0;
        Map<String, Long> activityTime = new HashMap<>();

        // Calculate statistics
        for (Activity a : activities) {
            // Count activities
            activityCount.put(a.getActivityName(), activityCount.getOrDefault(a.getActivityName(), 0) + 1);
            // Calculate total minutes spent on activities
            totalMinutes += a.calculateMinutesSpent();
        }

        // Display activity count
        System.out.println("Activity count:");
        for (Map.Entry<String, Integer> entry : activityCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
        }
        System.out.println();

        // Display total time spent
        System.out.println("Total time spent on activities: " + totalMinutes + " minutes");

        // Calculate total time spent per activity
        for (Activity a : activities) {
            long minutesSpent = a.calculateMinutesSpent();
            activityTime.put(a.getActivityName(), activityTime.getOrDefault(a.getActivityName(), 0L) + minutesSpent);
        }

        // Display total time spent on each activity
        System.out.println("Total time spent per activity:");
        for (Map.Entry<String, Long> entry : activityTime.entrySet()) {
            System.out.printf("%s%s: %d minutes\n", Constants.INDENT, entry.getKey(), entry.getValue());
        }
        System.out.println();
    }
}
