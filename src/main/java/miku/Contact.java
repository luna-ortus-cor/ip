package miku;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Contact class to store related details about a contact.
 */
public class Contact {
    private static final String DELIMITER = " | "; //should put in constants or see if better solution
    private static final String EMPTY = "-"; //placeholder for missing values
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String firstName = null;
    private String lastName = null;
    private String middleName = null;
    private String houseExtension = null;
    private String housePhone = null;
    private String mobileExtension = null;
    private String mobilePhone = null;
    private String workExtension = null;
    private String workPhone = null;
    private LocalDate birthday = null;
    private String bloodType = null;
    private String emailPrimary = null;
    private String emailSecondary = null;
    private String addressPrimary = null;
    private String addressSecondary = null;

    /**
     * Initialize a Contact instance with relevant fields.
     */
    public Contact(String firstName, String lastName, String middleName, String housePhone, String houseExtension,
            String mobilePhone, String mobileExtension, String workPhone, String workExtension, LocalDate birthday,
            String bloodType, String emailPrimary, String emailSecondary, String addressPrimary,
            String addressSecondary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.housePhone = housePhone;
        this.houseExtension = houseExtension;
        this.mobilePhone = mobilePhone;
        this.mobileExtension = mobileExtension;
        this.workPhone = workPhone;
        this.workExtension = workExtension;
        this.birthday = birthday;
        this.bloodType = bloodType;
        this.emailPrimary = emailPrimary;
        this.emailSecondary = emailSecondary;
        this.addressPrimary = addressPrimary;
        this.addressSecondary = addressSecondary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getHousePhone() {
        return housePhone;
    }

    public String getHouseExtension() {
        return houseExtension;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getMobileExtension() {
        return mobileExtension;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getWorkExtension() {
        return workExtension;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getPrimaryEmail() {
        return emailPrimary;
    }

    public String getSecondaryEmail() {
        return emailSecondary;
    }

    public String getPrimaryAddress() {
        return addressPrimary;
    }

    public String getSecondaryAddress() {
        return addressSecondary;
    }

    public void setName(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public void setHouseNumber(String extension, String main) {
        this.houseExtension = extension;
        this.housePhone = main;
    }

    public void setMobileNumber(String extension, String main) {
        this.mobileExtension = extension;
        this.mobilePhone = main;
    }

    public void setWorkNumber(String extension, String main) {
        this.workExtension = extension;
        this.workPhone = main;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setEmail(int idx, String email) {
        if (idx == 0) {
            this.emailPrimary = email;
        } else if (idx == 1) {
            this.emailSecondary = email;
        } else {
            //do nothing
        }
    }

    public void setAddress(int idx, String address) {
        if (idx == 0) {
            this.addressPrimary = address;
        } else if (idx == 1) {
            this.addressSecondary = address;
        } else {
            //do nothing
        }
    }

    /**
     * Validate email, check email follows a valid standard email format.
     *
     * @param email string of email to be validated
     * @return boolean specifying if email is valid or not
     */
    public static boolean validateEmail(String email) {
        Pattern validEmailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        return validEmailPattern.matcher(email).matches();
    }

    @Override
    public String toString() {
        return escape(firstName) + DELIMITER
               + escape(lastName) + DELIMITER
               + escape(middleName) + DELIMITER
               + escape(housePhone) + DELIMITER
               + escape(houseExtension) + DELIMITER
               + escape(mobilePhone) + DELIMITER
               + escape(mobileExtension) + DELIMITER
               + escape(workPhone) + DELIMITER
               + escape(workExtension) + DELIMITER
               + (birthday != null ? birthday.format(DATE_FORMATTER) : EMPTY) + DELIMITER
               + escape(bloodType) + DELIMITER
               + escape(emailPrimary) + DELIMITER
               + escape(emailSecondary) + DELIMITER
               + escape(addressPrimary) + DELIMITER
               + escape(addressSecondary) + DELIMITER;
    }

    /**
     * Return a formatted string for each field in the Contact instance, handling empty/null values correctly.
     *
     * @param value string of a field value in the contact instance
     * @return a formatted string of the field value
     */
    private String escape(String value) {
        return (value == null || value.isEmpty()) ? EMPTY : value.replace("|", "\\|");
    }

    /**
     * Convert a string representation of Contact object back to Contact object.
     *
     * @param line string representation of contact object
     * @return contact object from its string representation
     */
    public static Contact fromString(String line) {
        String[] parts = line.split(" \\| ", -1); // -1 keeps trailing empty fields
        if (parts.length < 16) {
            throw new IllegalArgumentException("Invalid contact format: " + line);
        }

        return new Contact(
            unescape(parts[0]), unescape(parts[1]), unescape(parts[2]),
            unescape(parts[3]), unescape(parts[4]),
            unescape(parts[5]), unescape(parts[6]),
            unescape(parts[7]), unescape(parts[8]),
            parts[9].equals(EMPTY) ? null : LocalDate.parse(parts[9], DATE_FORMATTER),
            unescape(parts[10]), unescape(parts[11]), unescape(parts[12]),
            unescape(parts[13]), unescape(parts[14])
        );
    }

    private static String unescape(String value) {
        return value.equals(EMPTY) ? "" : value.replace("\\|", "|");
    }
}
