package miku;

import java.io.*;
import java.util.Properties;

public class Settings {

    private String theme;
    private int fontSize;
    private boolean soundEnabled;
    private boolean typingIndicatorEnabled;
    private final String settingsFilePath = "settings.txt";

    public Settings() {
        // Initialize settings and load from file or set defaults if file is missing
        loadOrCreateSettings();
    }

    private void loadOrCreateSettings() {
        File settingsFile = new File(settingsFilePath);
        if (!settingsFile.exists()) {
            // If file doesn't exist, set default settings and save
            setDefaultSettings();
            saveSettings(); // Save default settings to file
        } else {
            loadSettings(); // Load settings from the file if it exists
        }
    }

    private void setDefaultSettings() {
        this.theme = "light";
        this.fontSize = 14;
        this.soundEnabled = true;
        this.typingIndicatorEnabled = true;
    }

    private void loadSettings() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(settingsFilePath)) {
            properties.load(input);
            this.theme = properties.getProperty("theme", "light");
            this.fontSize = Integer.parseInt(properties.getProperty("fontSize", "14"));
            this.soundEnabled = Boolean.parseBoolean(properties.getProperty("soundEnabled", "true"));
            this.typingIndicatorEnabled = Boolean.parseBoolean(properties.getProperty("typingIndicatorEnabled", "true"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSettings() {
        Properties properties = new Properties();
        properties.setProperty("theme", theme);
        properties.setProperty("fontSize", String.valueOf(fontSize));
        properties.setProperty("soundEnabled", String.valueOf(soundEnabled));
        properties.setProperty("typingIndicatorEnabled", String.valueOf(typingIndicatorEnabled));

        try (OutputStream output = new FileOutputStream(settingsFilePath)) {
            properties.store(output, "Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and setters for settings fields
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }

    public boolean isTypingIndicatorEnabled() {
        return typingIndicatorEnabled;
    }

    public void setTypingIndicatorEnabled(boolean typingIndicatorEnabled) {
        this.typingIndicatorEnabled = typingIndicatorEnabled;
    }
}
