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
 * Controller for Edit Contact Window
 */
public class EditContactWindow {
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

        boolean isFirstNameChanged = (
            originalContact.getFirstName() == null && firstName != null)
                || (originalContact.getFirstName() != null
                    && !originalContact.getFirstName().equals(firstName));
        boolean isLastNameChanged = (
            originalContact.getLastName() == null && lastName != null)
                || (originalContact.getLastName() != null
                    && !originalContact.getLastName().equals(lastName));
        boolean isMiddleNameChanged = (
            originalContact.getMiddleName() == null && middleName != null)
                || (originalContact.getMiddleName() != null
                    && !originalContact.getMiddleName().equals(middleName));
        boolean isHousePhoneChanged = (
            originalContact.getHousePhone() == null && housePhone != null)
                || (originalContact.getHousePhone() != null
                    && !originalContact.getHousePhone().equals(housePhone));
        boolean isHouseExtensionChanged = (
            originalContact.getHouseExtension() == null && houseExtension != null)
                || (originalContact.getHouseExtension() != null
                    && !originalContact.getHouseExtension().equals(houseExtension));
        boolean isMobilePhoneChanged = (
            originalContact.getMobilePhone() == null && mobilePhone != null)
                || (originalContact.getMobilePhone() != null
                    && !originalContact.getMobilePhone().equals(mobilePhone));
        boolean isMobileExtensionChanged = (
            originalContact.getMobileExtension() == null && mobileExtension != null)
                || (originalContact.getMobileExtension() != null
                    && !originalContact.getMobileExtension().equals(mobileExtension));
        boolean isWorkPhoneChanged = (
            originalContact.getWorkPhone() == null && workPhone != null)
                || (originalContact.getWorkPhone() != null
                    && !originalContact.getWorkPhone().equals(workPhone));
        boolean isWorkExtensionChanged = (
            originalContact.getWorkExtension() == null && workExtension != null)
                || (originalContact.getWorkExtension() != null
                    && !originalContact.getWorkExtension().equals(workExtension));
        boolean isBirthdayChanged = (
            originalContact.getBirthday() == null && birthday != null)
                || (originalContact.getBirthday() != null
                    && !originalContact.getBirthday().equals(birthday));
        boolean isBloodTypeChanged = (
            originalContact.getBloodType() == null && bloodType != null)
                || (originalContact.getBloodType() != null
                    && !originalContact.getBloodType().equals(bloodType));
        boolean isPrimaryEmailChanged = (
            originalContact.getPrimaryEmail() == null && primaryEmail != null)
                || (originalContact.getPrimaryEmail() != null
                    && !originalContact.getPrimaryEmail().equals(primaryEmail));
        boolean isSecondaryEmailChanged = (
            originalContact.getSecondaryEmail() == null && secondaryEmail != null)
                || (originalContact.getSecondaryEmail() != null
                    && !originalContact.getSecondaryEmail().equals(secondaryEmail));
        boolean isPrimaryAddressChanged = (
            originalContact.getPrimaryAddress() == null && primaryAddress != null)
                || (originalContact.getPrimaryAddress() != null
                    && !originalContact.getPrimaryAddress().equals(primaryAddress));
        boolean isSecondaryAddressChanged = (
            originalContact.getSecondaryAddress() == null && secondaryAddress != null)
                || (originalContact.getSecondaryAddress() != null
                    && !originalContact.getSecondaryAddress().equals(secondaryAddress));

        boolean isAnyFieldChanged = isFirstNameChanged || isLastNameChanged || isMiddleNameChanged
                            || isHousePhoneChanged || isHouseExtensionChanged || isMobilePhoneChanged
                            || isMobileExtensionChanged || isWorkPhoneChanged || isWorkExtensionChanged
                            || isBirthdayChanged || isBloodTypeChanged || isPrimaryEmailChanged
                            || isSecondaryEmailChanged || isPrimaryAddressChanged || isSecondaryAddressChanged;

        if (!isAnyFieldChanged) {
            Alert alert = new Alert(AlertType.ERROR, Constants.INVALID_CONTACT_EDIT);
            alert.showAndWait();
            return;
        }

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
