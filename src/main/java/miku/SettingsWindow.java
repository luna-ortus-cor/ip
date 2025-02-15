package miku;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 * Controller for settings
 */
public class SettingsWindow {
    @FXML
    private ChoiceBox<String> themeSelector;
    @FXML
    private Slider fontSizeSlider;
    @FXML
    private CheckBox soundToggle;
    @FXML
    private CheckBox typingIndicatorToggle;
    @FXML
    private Button cancelButton;
    @FXML
    private Button applyButton;

    private Settings settings;

    @FXML
    private void initialize() {
        this.settings = new Settings();
        // Apply settings to the UI elements
        themeSelector.getItems().clear();
        themeSelector.getItems().addAll("light", "dark");
        themeSelector.setValue(settings.getTheme());

        fontSizeSlider.setValue(settings.getFontSize());
        soundToggle.setSelected(settings.isSoundEnabled());
        typingIndicatorToggle.setSelected(settings.isTypingIndicatorEnabled());
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
