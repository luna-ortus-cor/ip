package miku;

import java.util.ArrayList;

public class ContactList {
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

    public Contact getContact(int idx) {
        return this.contactList.get(idx); //need try catch on idx
    }

    public void addContact(Contact c) {
        this.contactList.add(c);
    }

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
            //ui.printDeleteMsg(c, contactList.size()); //check message
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Delete all tasks in the list.
     */
    public void deleteAll() {
        contactList.clear();
        ui.printDeleteAllMsg(); //check message
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
