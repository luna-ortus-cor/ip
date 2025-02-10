package miku;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EditContactWindow {
    @FXML private TextField firstNameField, middleNameField, lastNameField;
    @FXML private TextField houseExtensionField, housePhoneField;
    @FXML private TextField mobileExtensionField, mobilePhoneField;
    @FXML private TextField workExtensionField, workPhoneField;
    @FXML private DatePicker birthdayPicker;
    @FXML private TextField bloodTypeField;
    @FXML private TextField primaryEmailField, secondaryEmailField;
    @FXML private TextField primaryAddressField, secondaryAddressField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private ContactListener contactListener;
    private Contact originalContact;

    public void setContact(Contact contact) {
        this.originalContact = contact;

        // Populate fields with existing contact info
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        middleNameField.setText(contact.getMiddleName());
        housePhoneField.setText(contact.getHousePhone());
        houseExtensionField.setText(contact.getHouseExtension());
        mobilePhoneField.setText(contact.getMobilePhone());
        mobileExtensionField.setText(contact.getMobileExtension());
        workPhoneField.setText(contact.getWorkPhone());
        workExtensionField.setText(contact.getWorkExtension());
        birthdayPicker.setValue(contact.getBirthday());
        bloodTypeField.setText(contact.getBloodType());
        primaryEmailField.setText(contact.getPrimaryEmail());
        secondaryEmailField.setText(contact.getSecondaryEmail());
        primaryAddressField.setText(contact.getPrimaryAddress());
        secondaryAddressField.setText(contact.getSecondaryAddress());
    }

    public void setContactListener(ContactListener listener) {
        this.contactListener = listener;
    }

    @FXML
    private void handleEdit() {
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

        Contact updatedContact = new Contact(firstName, lastName, middleName, housePhone, houseExtension,
                mobilePhone, mobileExtension, workPhone, workExtension, birthday, bloodType,
                primaryEmail, secondaryEmail, primaryAddress, secondaryAddress);

        if (contactListener != null) {
            contactListener.onContactEdited(originalContact, updatedContact);
        }

        closeWindow();
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
