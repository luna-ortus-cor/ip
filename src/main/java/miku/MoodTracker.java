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
 * Class to track moods by day.
 */
public class MoodTracker {
    private static final String FILE_NAME = Constants.FILEPATH_MOOD_TRACKER;
    private static ArrayList<Mood> moods = new ArrayList<>();

    /**
     * Create and save a mood instance given inputs.
     *
     * @param date string of date
     * @param moodDescription string of mood
     */
    public static void trackMood(String date, String moodDescription) {
        Mood newMood = new Mood(date, moodDescription);
        moods.add(newMood);
        saveToFile(newMood);
    }

    /**
     * Save mood to file.
     *
     * @param mood mood to be saved
     */
    private static void saveToFile(Mood mood) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(mood.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving mood to file.");
        }
    }

    /**
     * Load moods from file.
     */
    private static void loadMoodsFromFile() {
        moods.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length == 2) {
                    moods.add(new Mood(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading moods from file: " + e.getMessage());
        }
    }

    /**
     * Display statistics.
     */
    public static void displayStatistics() {
        loadMoodsFromFile();
        System.out.println("\nMood Statistics:");
        Map<String, Integer> moodCount = new HashMap<>();

        for (Mood m : moods) {
            moodCount.put(m.getMoodDescription(), moodCount.getOrDefault(m.getMoodDescription(), 0) + 1);
        }

        System.out.println("Mood count:");
        for (Map.Entry<String, Integer> entry : moodCount.entrySet()) {
            System.out.printf("%s%s: %d times\n", Constants.INDENT, entry.getKey(), entry.getValue());
        }
        System.out.println();
    }
}

