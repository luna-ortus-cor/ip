package miku;

public interface ContactListener {
    void onContactAdded(Contact contact);

    void onContactEdited(Contact oldContact, Contact newContact);
}
