package miku;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddContactWindow {
    @FXML private TextField firstNameField, middleNameField, lastNameField;
    @FXML private TextField houseExtensionField, housePhoneField;
    @FXML private TextField mobileExtensionField, mobilePhoneField;
    @FXML private TextField workExtensionField, workPhoneField;
    @FXML private DatePicker birthdayPicker;
    @FXML private TextField bloodTypeField;
    @FXML private TextField primaryEmailField, secondaryEmailField;
    @FXML private TextField primaryAddressField, secondaryAddressField;
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

        Contact newContact = new Contact(firstName, lastName, middleName, housePhone, houseExtension,
                mobilePhone, mobileExtension, workPhone, workExtension, birthday, bloodType,
                primaryEmail, secondaryEmail, primaryAddress, secondaryAddress);

        //System.out.println("Contact added: " + firstNameField.getText());
        //System.out.println();

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

