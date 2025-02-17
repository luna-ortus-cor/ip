package miku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class to model association between Task, Contact and Location
 */
public class Association {
    private Map<Task, Set<Contact>> taskContacts;
    private Map<Task, Set<Location>> taskLocations;
    private Map<Contact, Set<Task>> contactTasks; // Reverse mapping
    private Map<Location, Set<Task>> locationTasks; // Reverse mapping
    private Ui ui;

    /**
     * Initializes a new Association instance.
     * 
     * @param ui a Ui instance
     */
    public Association(Ui ui) {
        this.taskContacts = new HashMap<>();
        this.taskLocations = new HashMap<>();
        this.contactTasks = new HashMap<>();
        this.locationTasks = new HashMap<>();
        this.ui = ui;
    }

    /**
     * Associate a task with a contact.
     *
     * @param task task to be associated
     * @param contact contact to be associated
     */
    public void associateTaskWithContact(Task task, Contact contact) {
        taskContacts.computeIfAbsent(task, k -> new HashSet<>()).add(contact);
        contactTasks.computeIfAbsent(contact, k -> new HashSet<>()).add(task);
    }

    /**
     * Associate a task with a location.
     *
     * @param task task to be associated
     * @param location location to be associated
     */
    public void associateTaskWithLocation(Task task, Location location) {
        taskLocations.computeIfAbsent(task, k -> new HashSet<>()).add(location);
        locationTasks.computeIfAbsent(location, k -> new HashSet<>()).add(task);
    }

    /**
     * Retrieve contacts associated with a task.
     *
     * @param task task to retrieve associated contacts from
     */
    public Set<Contact> getContactsForTask(Task task) {
        return taskContacts.getOrDefault(task, Collections.emptySet());
    }

    /**
     * Retrieve locations associated with a task.
     *
     * @param task task to retrieve associated locations from
     */
    public Set<Location> getLocationsForTask(Task task) {
        return taskLocations.getOrDefault(task, Collections.emptySet());
    }

    /**
     * Retrieve tasks associated with a contact.
     *
     * @param contact contact to retrieve associated tasks from
     */
    public Set<Task> getTasksForContact(Contact contact) {
        return contactTasks.getOrDefault(contact, Collections.emptySet());
    }

    /**
     * Retrieve tasks associated with a location.
     *
     * @param location location to retrieve associated tasks from
     */
    public Set<Task> getTasksForLocation(Location location) {
        return locationTasks.getOrDefault(location, Collections.emptySet());
    }

    /**
     * Save associations to file.
     *
     * @param filename file to write to
     */
    public void saveAssociations(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(taskContacts);
            oos.writeObject(taskLocations);
            oos.writeObject(contactTasks);
            oos.writeObject(locationTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load associations from a file.
     *
     * @param filename file to read from
     */
    public void loadAssociations(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            taskContacts = (Map<Task, Set<Contact>>) ois.readObject();
            taskLocations = (Map<Task, Set<Location>>) ois.readObject();
            contactTasks = (Map<Contact, Set<Task>>) ois.readObject();
            locationTasks = (Map<Location, Set<Task>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

