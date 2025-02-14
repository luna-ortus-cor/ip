package miku;

import java.io.*;
import java.util.*;

public class MoodTracker {
    private static final String FILE_NAME = Constants.FILEPATH_MOOD_TRACKER;
    private static ArrayList<Mood> moods = new ArrayList<>();

    public static void trackMood(String date, String moodDescription) {
        Mood newMood = new Mood(date, moodDescription);
        moods.add(newMood);
        saveToFile(newMood);
    }

    private static void saveToFile(Mood mood) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(mood.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving mood to file.");
        }
    }

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

