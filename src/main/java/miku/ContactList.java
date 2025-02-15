package miku;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class to store array of contacts and handle operations on contacts in list.
 */
public class ContactList {
    private static final String FILE_PATH = Constants.FILEPATH_CONTACTLIST;
    private ArrayList<Contact> contactList = new ArrayList<Contact>();
    private Ui ui;

    /**
     * Instantiates a new ContactList instance taking in a Ui ui.
     *
     * @param ui a Ui instance
     */
    public ContactList(Ui ui) {
        this.ui = ui;
    }

    /**
     * Returns an arraylist of contacts in the ContactList.
     *
     * @return an arraylist of contacts in the ContactList
     */
    public ArrayList<Contact> getList() {
        return this.contactList;
    }

    /**
     * Load contacts from a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void loadContacts(Storage s) {
        this.contactList = s.readContacts(FILE_PATH);
    }

    /**
     * Save contacts to a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void saveContacts(Storage s) {
        s.writeContacts(this.contactList, FILE_PATH);
    }

    /**
     * Get contact based on index in contact list.
     * 
     * @param idx index of contact in contact list
     * @return contact object at specified index
     */
    public Contact getContact(int idx) {
        return this.contactList.get(idx); //need try catch on idx
    }

    /**
     * Search contacts by name, allowing partial matches in any of the name fields.
     * 
     * @param searchString the search string to be matched (either partially or fully)
     * @return an arraylist of contact objects containing the search string
     */
    public ArrayList<Contact> findContactByName(String searchString) {
        return new ArrayList<Contact>(contactList.stream()
                .filter(contact -> (contact.getFirstName() != null &&
                                    contact.getFirstName().toLowerCase().contains(searchString.toLowerCase())) ||
                                   (contact.getMiddleName() != null &&
                                    contact.getMiddleName().toLowerCase().contains(searchString.toLowerCase())) ||
                                   (contact.getLastName() != null &&
                                    contact.getLastName().toLowerCase().contains(searchString.toLowerCase())))
                .collect(Collectors.toList()));
    }

    /**
     * Search contacts by email, allowing partial matches in any of the email fields.
     * 
     * @param searchString the search string to be matched (either partially or fully)
     * @return an arraylist of contact objects containing the search string
     */
    public ArrayList<Contact> findContactsByEmail(String searchString) {
        return new ArrayList<Contact>(contactList.stream()
                .filter(contact -> (contact.getPrimaryEmail() != null &&
                                    contact.getPrimaryEmail().toLowerCase().contains(searchString.toLowerCase())) ||
                                   (contact.getSecondaryEmail() != null &&
                                    contact.getSecondaryEmail().toLowerCase().contains(searchString.toLowerCase())))
                .collect(Collectors.toList()));
    }

    /**
     * Search contacts by address, allowing partial matches in any of the address fields.
     * 
     * @param searchString the search string to be matched (either partially or fully)
     * @return an arraylist of contact objects containing the search string
     */
    public ArrayList<Contact> findContactsByAddress(String searchString) {
        return new ArrayList<Contact>(contactList.stream()
                .filter(contact -> (contact.getPrimaryAddress() != null &&
                                    contact.getPrimaryAddress().toLowerCase().contains(searchString.toLowerCase())) ||
                                   (contact.getSecondaryAddress() != null &&
                                    contact.getSecondaryAddress().toLowerCase().contains(searchString.toLowerCase())))
                .collect(Collectors.toList()));
    }

    /**
     * Add a contact object to the list of contacts.
     * 
     * @param c a contact object to be added to the list of contacts
     */
    public void addContact(Contact c) {
        this.contactList.add(c);
    }

    /**
     * Edit an existing contact object in the list of contacts.
     * 
     * @param c1 old contact to be edited
     * @param c2 new contact to replace the old contact
     */
    public void editContact(Contact c1, Contact c2) {
        int idx = this.contactList.indexOf(c1);
        this.contactList.set(idx, c2);
    }

    /**
     * Delete the contact at a specified index.
     *
     * @param idx the index of the contact in the list
     */
    public void delete(int idx) {
        assert idx >= 0: "Index must be non-negative";
        try {
            Contact c = contactList.get(idx);
            contactList.remove(idx);
            ui.printDeleteContactMsg(c, contactList.size());
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Delete all contacts in the list.
     */
    public void deleteAll() {
        contactList.clear();
        ui.printDeleteAllContactsMsg();
    }

    /**
     * Handles error messages.
     * 
     * @param code int denoting the error generated
     */
    private void handleError(int code) {
        ui.printErrorMsg(code);
    }
}
