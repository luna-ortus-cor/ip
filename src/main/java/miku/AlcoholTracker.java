package miku;

import java.io.*;
import java.util.*;

public class AlcoholTracker {
    private static final String FILE_NAME = Constants.FILEPATH_ALCOHOL_TRACKER;
    private static ArrayList<Alcohol> alcoholLogs = new ArrayList<>();

    public static void trackAlcohol(String date, String drink, String name, int quantity) {
        Alcohol newLog = new Alcohol(date, drink, name, quantity);
        alcoholLogs.add(newLog);
        saveToFile(newLog);
    }

    private static void saveToFile(Alcohol alcohol) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(alcohol.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving alcohol log.");
        }
    }

    private static void loadAlcoholLogsFromFile() {
        alcoholLogs.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length == 4) {
                    try {
                        int quantity = Integer.parseInt(parts[3].trim());
                        alcoholLogs.add(new Alcohol(parts[0].trim(), parts[1].trim(), parts[2].trim(), quantity));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid alcohol quantity in file.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading alcohol logs from file: " + e.getMessage());
        }
    }

    public static void displayStatistics() {
        loadAlcoholLogsFromFile();
        System.out.println("\nAlcohol Consumption Statistics:");
        Map<String, Integer> drinkCount = new HashMap<>();
        int totalDrinks = 0;

        for (Alcohol a : alcoholLogs) {
            totalDrinks += a.getQuantity();
            drinkCount.put(a.getDrink(), drinkCount.getOrDefault(a.getDrink(), 0) + a.getQuantity());
        }

        System.out.println("Total alcohol consumed: " + totalDrinks + " drinks");

        System.out.println("Drink type count:");
        for (Map.Entry<String, Integer> entry : drinkCount.entrySet()) {
            System.out.printf("%s%s: %d drinks\n", Constants.INDENT, entry.getKey(), entry.getValue());
        }
        System.out.println();
    }
}

