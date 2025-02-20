package miku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class to model association between Task, Contact and Location
 */
public class Association {
    private static final String FILE_PATH = Constants.FILEPATH_ASSOCIATION;
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
     * @param taskList list of tasks
     * @param taskIdx index of task to be associated in task list
     * @param contactList list of contacts
     * @param contactIdx index of contact to be associated in contact list
     */
    public void associateTaskWithContact(TaskList taskList, int taskIdx, ContactList contactList, int contactIdx) {
        Task task = taskList.getTask(taskIdx);
        Contact contact = contactList.getContact(contactIdx);
        taskContacts.computeIfAbsent(task, k -> new HashSet<>()).add(contact);
        contactTasks.computeIfAbsent(contact, k -> new HashSet<>()).add(task);
    }

    /**
     * Associate a task with a location.
     *
     * @param taskList list of tasks
     * @param taskIdx index of task to be associated in task list
     * @param locationList list of locations
     * @param locationIdx index of location to be associated in location list
     */
    public void associateTaskWithLocation(TaskList taskList, int taskIdx, LocationList locationList, int locationIdx) {
        Task task = taskList.getTask(taskIdx);
        Location location = locationList.getLocation(locationIdx);
        taskLocations.computeIfAbsent(task, k -> new HashSet<>()).add(location);
        locationTasks.computeIfAbsent(location, k -> new HashSet<>()).add(task);
    }

    /**
     * Retrieve contacts associated with a task.
     *
     * @param taskList list of tasks
     * @param idx index of task to find
     * @return arraylist of contacts associated with task
     */
    public ArrayList<Contact> getContactsForTask(TaskList taskList, int idx) {
        try {
            Task task = taskList.getTask(idx);
            return new ArrayList<>(taskContacts.getOrDefault(task, Collections.emptySet()));
        } catch (Exception e) {
            return new ArrayList<Contact>();
        }
    }

    /**
     * Retrieve locations associated with a task.
     *
     * @param taskList list of tasks
     * @param idx index of task to find
     * @return arraylist of locations associated with task
     */
    public ArrayList<Location> getLocationsForTask(TaskList taskList, int idx) {
        try {
            Task task = taskList.getTask(idx);
            return new ArrayList<>(taskLocations.getOrDefault(task, Collections.emptySet()));
        } catch (Exception e) {
            return new ArrayList<Location>();
        }
    }

    /**
     * Retrieve tasks associated with a contact.
     *
     * @param contactList list of contacts
     * @param idx index of contact to find
     * @return arraylist of tasks associated with contact
     */
    public ArrayList<Task> getTasksForContact(ContactList contactList, int idx) {
        try {
            Contact contact = contactList.getContact(idx);
            return new ArrayList<>(contactTasks.getOrDefault(contact, Collections.emptySet()));
        } catch (Exception e) {
            return new ArrayList<Task>();
        }
    }

    /**
     * Retrieve tasks associated with a location.
     *
     * @param locationList list of locations
     * @param idx index of location to find
     * @return arraylist of tasks associated with location
     */
    public ArrayList<Task> getTasksForLocation(LocationList locationList, int idx) {
        try {
            Location location = locationList.getLocation(idx);
            return new ArrayList<>(locationTasks.getOrDefault(location, Collections.emptySet()));
        } catch (Exception e) {
            return new ArrayList<Task>();
        }
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

    /**
     * Handles error messages.
     *
     * @param code int denoting the error generated
     */
    private void handleError(int code) {
        ui.printErrorMsg(code);
    }
}

