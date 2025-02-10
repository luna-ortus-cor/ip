package miku;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsWindow {

    @FXML
    private ChoiceBox<String> themeSelector;  // For selecting theme (light/dark)
    @FXML
    private Slider fontSizeSlider;  // For selecting font size
    @FXML
    private CheckBox soundToggle;  // Toggle for sound
    @FXML
    private CheckBox typingIndicatorToggle;  // Toggle for typing indicator
    @FXML
    private Button cancelButton;  // Cancel button
    @FXML
    private Button applyButton;   // Apply button

    private Settings settings;  // Assuming a Settings class to hold the preferences
    
    @FXML
    private void initialize() {
        this.settings = new Settings();
        // Apply settings to the UI elements
        themeSelector.getItems().clear();
        themeSelector.getItems().addAll("light", "dark");
        themeSelector.setValue(settings.getTheme());  // Set the theme

        fontSizeSlider.setValue(settings.getFontSize());  // Set the font size
        soundToggle.setSelected(settings.isSoundEnabled());  // Set the sound toggle
        typingIndicatorToggle.setSelected(settings.isTypingIndicatorEnabled());  // Set the typing indicator toggle
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    // Handle the Apply button click (save settings and apply changes)
    @FXML
    private void handleApply() {
        // Save the selected settings
        settings.setTheme(themeSelector.getValue());

        // Cast fontSizeSlider value to int and then save it
        settings.setFontSize((int) fontSizeSlider.getValue());
        
        settings.setSoundEnabled(soundToggle.isSelected());
        settings.setTypingIndicatorEnabled(typingIndicatorToggle.isSelected());

        // Save settings to persistent storage (e.g., preferences or file)
        settings.saveSettings();

        // Update the global settings manager with the current settings
        GlobalSettingsManager.setCurrentTheme(settings.getTheme());
        GlobalSettingsManager.setFontSize((int) settings.getFontSize()); // Ensure it's an int here

        // Apply settings globally to all open windows
        GlobalSettingsManager.applySettingsToAllWindows();

        // Close the settings window
        Stage stage = (Stage) applyButton.getScene().getWindow();
        stage.close();
    }

    // Handle the Cancel button click (close window without saving)
    @FXML
    private void handleCancel() {
        // Close the settings window without saving any changes
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
