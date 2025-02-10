package miku;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GlobalSettingsManager {

    private static String currentTheme = "light";  // Default theme
    private static int fontSize = 14;  // Default font size

    // Getters and setters for the settings
    public static String getCurrentTheme() {
        return currentTheme;
    }

    public static void setCurrentTheme(String theme) {
        currentTheme = theme;
    }

    public static int getFontSize() {
        return fontSize;
    }

    public static void setFontSize(int size) {
        fontSize = size;
    }

    // Apply the settings globally to the primary stage (or any new stage)
    public static void applyGlobalSettings(Stage stage) {
        // Apply theme (Dark or Light)
        if (currentTheme.equals("dark")) {
            stage.getScene().getStylesheets().clear();
            stage.getScene().getStylesheets().add(GlobalSettingsManager.class.getResource("/css/dark-theme.css").toExternalForm());
        } else {
            stage.getScene().getStylesheets().clear();
            stage.getScene().getStylesheets().add(GlobalSettingsManager.class.getResource("/css/light-theme.css").toExternalForm());
        }

        // Apply font size globally to all text components (buttons, labels, etc.)
        String fontSizeStyle = "-fx-font-size: " + fontSize + "px;";
        stage.getScene().getRoot().setStyle(fontSizeStyle);

        // Ensure settings are applied to any child nodes in the scene
        applySettingsToAllNodes(stage.getScene());
    }

    // Apply font size and theme to all nodes (buttons, labels, text fields, etc.)
    private static void applySettingsToAllNodes(Scene scene) {
        scene.getRoot().getStylesheets().forEach(sheet -> {
            // Force CSS refresh
            scene.getStylesheets().clear();
            scene.getStylesheets().add(sheet);
        });

        // Apply global styles for all UI elements
        scene.getRoot().setStyle("-fx-font-size: " + fontSize + "px;");
    }

    // Apply settings to all open windows (also handles new windows)
    public static void applySettingsToAllWindows() {
        Platform.runLater(() -> {
            Stage primaryStage = Main.getPrimaryStage();
            if (primaryStage != null) {
                applyGlobalSettings(primaryStage);  // Apply to the main window
            }

            // Apply to any other open windows (settings, dialogs, etc.)
            for (Window window : Stage.getWindows()) {
                if (window.isShowing() && window instanceof Stage) {
                    Stage stage = (Stage) window;
                    applyGlobalSettings(stage);
                }
            }
        });
    }
}
