package miku;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for Add Contact Window
 */
public class AddContactWindow {
    @FXML private TextField firstNameField;
    @FXML private TextField middleNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField houseExtensionField;
    @FXML private TextField housePhoneField;
    @FXML private TextField mobileExtensionField;
    @FXML private TextField mobilePhoneField;
    @FXML private TextField workExtensionField;
    @FXML private TextField workPhoneField;
    @FXML private DatePicker birthdayPicker;
    @FXML private TextField bloodTypeField;
    @FXML private TextField primaryEmailField;
    @FXML private TextField secondaryEmailField;
    @FXML private TextField primaryAddressField;
    @FXML private TextField secondaryAddressField;
    @FXML private Button addButton;
    @FXML private Button cancelButton;

    private ContactListener contactListener;

    public void setContactListener(ContactListener listener) {
        this.contactListener = listener;
    }

    @FXML
    private void handleCancel() {
        closeWindow();
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAdd() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String middleName = middleNameField.getText();
        String housePhone = housePhoneField.getText();
        String houseExtension = houseExtensionField.getText();
        String mobilePhone = mobilePhoneField.getText();
        String mobileExtension = mobileExtensionField.getText();
        String workPhone = workPhoneField.getText();
        String workExtension = workExtensionField.getText();
        LocalDate birthday = birthdayPicker.getValue();
        String bloodType = bloodTypeField.getText();
        String primaryEmail = primaryEmailField.getText();
        String secondaryEmail = secondaryEmailField.getText();
        String primaryAddress = primaryAddressField.getText();
        String secondaryAddress = secondaryAddressField.getText();

        boolean isValidPrimaryEmail = true;
        boolean isValidSecondaryEmail = true;
        if (!primaryEmail.isEmpty() && !Contact.validateEmail(primaryEmail)) {
            isValidPrimaryEmail = false;
        }
        if (!secondaryEmail.isEmpty() && !Contact.validateEmail(secondaryEmail)) {
            isValidSecondaryEmail = false;
        }
        if (!isValidPrimaryEmail || !isValidSecondaryEmail) {
            Alert alert = new Alert(AlertType.ERROR, Constants.INVALID_EMAIL_ERROR);
            alert.showAndWait();
            return;
        }

        Contact newContact = new Contact(firstName, lastName, middleName, housePhone, houseExtension,
                mobilePhone, mobileExtension, workPhone, workExtension, birthday, bloodType,
                primaryEmail, secondaryEmail, primaryAddress, secondaryAddress);

        if (contactListener != null) {
            contactListener.onContactAdded(newContact);
        }

        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}

