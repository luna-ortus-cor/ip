package miku;

/**
 * Interface to listen for add contact and edit contact events
 */
public interface ContactListener {
    void onContactAdded(Contact contact);

    void onContactEdited(Contact oldContact, Contact newContact);
}
