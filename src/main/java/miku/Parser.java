package miku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Parser class to parse user instructions and take relevant action.
 */
public class Parser implements ContactListener {
    private static TaskList taskList;
    private static ContactList contactList;
    private static LocationList locationList;
    private static Storage storage;
    private static Ui ui;
    private Scanner sc = new Scanner(System.in);

    /**
     * Instantiates a new Parser instance taking in a Ui ui.
     *
     * @param ui a Ui instance
     */
    public Parser(Ui ui) {
        this.ui = ui;
        this.taskList = new TaskList(ui);
        this.contactList = new ContactList(ui);
        this.storage = new Storage(ui);
    }

    /**
     * Starts the parser and initializes relevant variables,
     * such as the TaskList
     */
    public void start() {
        loadTaskList();
        loadContactList();
        ui.printStartMsg();
    }

    /**
     * Parses an input string from the user and determines subsequent actions.
     * Returns an integer 0 or 1 to determine if the bot has received an instruction to exit.
     *
     * @param in a String of the user input
     * @return an integer denoting if the bot has received an instruction to exit
     */
    public int parse(String in) {
        in = in.trim();
        Pattern listPattern = Pattern.compile("^list$");
        Pattern markPattern = Pattern.compile("^mark (\\d+)$");
        Pattern unmarkPattern = Pattern.compile("^unmark (\\d+)$");
        Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
        Pattern deleteAllPattern = Pattern.compile("^delete /all$");

        Pattern todoPattern = Pattern.compile(
            "^todo\\s+(.+?)(?:\\s+/prio\\s+(\\d))?(?:\\s+/tags\\s+([\\w\\s+]+))?$");
        Pattern deadlinePattern = Pattern.compile(
            "^deadline\\s+(.+?)\\s+/by\\s+(.+?)(?:\\s+/prio\\s+(\\d))?(?:\\s+/tags\\s+([\\w\\s+]+))?$");
        Pattern eventPattern = Pattern.compile(
            "^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+?)(?:\\s+/prio\\s+(\\d))?(?:\\s+/tags\\s+([\\w\\s+]+))?$");

        Pattern simpleCommandsPattern = Pattern.compile("^(games|track|stats|chat|bye|help)$");
        Pattern searchNamePattern = Pattern.compile("^find task (.+)$");
        Pattern setPriorityPattern = Pattern.compile("^set (\\d+) (\\d)$");
        Pattern sortPriorityPattern = Pattern.compile("^sort prio /(asc|desc)$");
        Pattern addTagsPattern = Pattern.compile("^add tags (\\d+) ([\\w\\s+]+)$");
        Pattern deleteTagsPattern = Pattern.compile("^delete tags (\\d+) ([\\w\\s+]+)$");

        Pattern addContactPattern = Pattern.compile("^add contact$");
        Pattern editContactPattern = Pattern.compile("^edit contact (\\d+)$");
        Pattern viewContactsPattern = Pattern.compile("^contacts$");
        Pattern searchContactNamePattern = Pattern.compile("^find name (.+)$");
        Pattern searchContactEmailPattern = Pattern.compile("^find email (.+)$");
        Pattern searchContactAddressPattern = Pattern.compile("^find address (.+)$");

        Pattern addPlacePattern = Pattern.compile("^add place (.+)$");
        Pattern addWebsitePattern = Pattern.compile("^add website (.+)$");
        Pattern searchLocationPattern = Pattern.compile("find location (.+)$");

        Matcher matcher;

        if (listPattern.matcher(in).matches()) {
            printList(taskList.getList(), 0);
        } else if ((matcher = markPattern.matcher(in)).matches()) {
            handleMark(matcher.group(1), 1);
        } else if ((matcher = unmarkPattern.matcher(in)).matches()) {
            handleMark(matcher.group(1), 0);
        } else if ((matcher = deletePattern.matcher(in)).matches()) {
            handleDelete(matcher.group(1));
        } else if (deleteAllPattern.matcher(in).matches()) {
            handleDeleteAll();
        } else if ((matcher = todoPattern.matcher(in)).matches()) {
            String name = matcher.group(1).trim();
            String priority = matcher.group(2); //Optional
            String tags = matcher.group(3); //Optional

            handleTodo(name, priority, tags);
        } else if ((matcher = deadlinePattern.matcher(in)).matches()) {
            String name = matcher.group(1).trim();
            String by = matcher.group(2).trim();
            String priority = matcher.group(3); //Optional
            String tags = matcher.group(4); //Optional

            handleDeadline(name, by, priority, tags);
        } else if ((matcher = eventPattern.matcher(in)).matches()) {
            String name = matcher.group(1).trim();
            String from = matcher.group(2).trim();
            String to = matcher.group(3).trim();
            String priority = matcher.group(4); //Optional
            String tags = matcher.group(5); //Optional

            handleEvent(name, from, to, priority, tags);
        } else if ((matcher = searchNamePattern.matcher(in)).matches()) {
            handleSearchName(matcher.group(1));
        } else if ((matcher = setPriorityPattern.matcher(in)).matches()) {
            handleSetPriority(matcher.group(1), matcher.group(2));
        } else if ((matcher = sortPriorityPattern.matcher(in)).matches()) {
            handleSortPriority(matcher.group(1));
        } else if ((matcher = addTagsPattern.matcher(in)).matches()) {
            handleAddTags(matcher.group(1), matcher.group(2));
        } else if ((matcher = deleteTagsPattern.matcher(in)).matches()) {
            handleDeleteTags(matcher.group(1), matcher.group(2));
        } else if ((matcher = addContactPattern.matcher(in)).matches()) {
            handleAddContact();
        } else if ((matcher = editContactPattern.matcher(in)).matches()) {
            handleEditContact(matcher.group(1));
        } else if ((matcher = viewContactsPattern.matcher(in)).matches()) {
            printList(contactList.getList(), 6);
        } else if ((matcher = searchContactNamePattern.matcher(in)).matches()) {
            handleSearchContactName(matcher.group(1));
        } else if ((matcher = searchContactEmailPattern.matcher(in)).matches()) {
            handleSearchContactEmail(matcher.group(1));
        } else if ((matcher = searchContactAddressPattern.matcher(in)).matches()) {
            handleSearchContactAddress(matcher.group(1));
        } else if ((matcher = simpleCommandsPattern.matcher(in)).matches()) {
            String command = matcher.group(1);
            switch (command) {
            case "games":
                handleGame();
                break;
            case "track":
                handleTrack();
                break;
            case "stats":
                handleStats();
                break;
            case "chat":
                handleChat();
                break;
            case "bye":
                handleExit();
                return 0;
            case "help":
                handleHelp();
                break;
            default:
                handleExit();
                return 0;
            }
        } else {
            handleError(1);
        }
        return 1;
    }

    /**
     * Handle the user exit.
     */
    private void handleExit() {
        saveTaskList();
        saveContactList();
        ui.printExitMsg();
    }

    /**
     * Load the task list from file.
     */
    private void loadTaskList() {
        taskList.loadTasks(storage);
    }

    /**
     * Save the task list to file.
     */
    private void saveTaskList() {
        taskList.saveTasks(storage);
    }

    /**
     * Load the contact list from file.
     */
    private void loadContactList() {
        contactList.loadContacts(storage);
    }

    /**
     * Save the contact list to file.
     */
    private void saveContactList() {
        contactList.saveContacts(storage);
    }

    private void loadLocationList() {

    }

    private void saveLocationList() {

    }

    /**
     * Print an arraylist, type specifies what message to print prior.
     *
     * @param list arraylist to be printed
     * @param type int specifying message to be printed prior to arraylist print
     */
    //type=0 is task, type=1 is game, type=2 is track, type=3 is stats,
    //type=4 is search, type=5 is sort, type=6 is contact
    private <T> void printList(ArrayList<T> list, int type) {
        int idx = 1;
        if (type == 0) {
            ui.printTaskListMsg();
        } else if (type == 1) {
            ui.printGamesMsg();
        } else if (type == 2) {
            ui.printTrackMsg();
        } else if (type == 3) {
            ui.printStatsMsg();
        } else if (type == 4) {
            ui.printSearchMsg();
        } else if (type == 5) {
            ui.printSortMsg();
        } else {
            ui.printContactListMsg();
        }
        for (T t:list) {
            ui.printListItem(idx, t);
            idx++;
        }
        ui.printDelim();
    }

    /**
     * Handle the help message.
     */
    private void handleHelp() {
        ui.printHelpMsg();
    }

    /**
     * Handle user mark task instruction.
     *
     * @param idx index of task to be marked
     * @param mark int specifying if task is to be marked done or not done
     */
    //mark done if mark=1, else mark not done if mark=0
    private void handleMark(String idx, int mark) {
        if (mark == 1) {
            taskList.markDone(Integer.valueOf(idx.trim()) - 1);
        } else {
            taskList.markNotDone(Integer.valueOf(idx.trim()) - 1);
        }
    }

    /**
     * Handle user delete task instruction.
     *
     * @param idx index of task to be deleted
     */
    private void handleDelete(String idx) {
        taskList.delete(Integer.valueOf(idx.trim()) - 1);
    }

    /**
     * Handle user delete all tasks instruction.
     */
    private void handleDeleteAll() {
        taskList.deleteAll();
    }

    /**
     * Handle user set task priority instruction.
     *
     * @param idx index of task to set priority of
     * @param priority int specifying priority task is to be set to
     */
    private void handleSetPriority(String idx, String priority) {
        taskList.setPriority(Integer.valueOf(idx.trim()) - 1, Integer.valueOf(priority));
    }

    /**
     * Handle user add Todo Task instruction.
     * 
     * @param in string input arguments of the todo
     * @param priority string of the priority of the todo
     */
    private void handleTodo(String name, String priority, String tags) {
        if (name != null && priority != null && tags != null) {
            taskList.addTodo(name, Integer.valueOf(priority), tags.split("\\s+"));
        } else if (name != null && priority != null) {
            taskList.addTodo(name, Integer.valueOf(priority));
        } else if (name != null && tags != null) {
            taskList.addTodo(name, tags.split("\\s+"));
        } else if (name != null) {
            taskList.addTodo(name);
        } else {
            handleError(2);
        }
    }

    /**
     * Handle user add Deadline Task instruction.
     * 
     * @param in string input arguments of the deadline
     * @param priority string of the priority of the deadline
     */
    private void handleDeadline(String name, String by, String priority, String tags) {
        if (name != null && by != null && priority != null && tags != null) {
            taskList.addDeadline(name, Integer.valueOf(priority), by, tags.split("\\s+"));
        } else if (name != null && by != null && priority != null) {
            taskList.addDeadline(name, Integer.valueOf(priority), by);
        } else if (name != null && by != null && tags != null) {
            taskList.addDeadline(name, by, tags.split("\\s+"));
        } else if (name != null && by != null) {
            taskList.addDeadline(name, by);
        } else {
            handleError(3);
        }
    }

    /**
     * Handle user add Event Task instruction.
     * 
     * @param in string input arguments of the event
     * @param priority string of the priority of the event
     */
    private void handleEvent(String name, String from, String to, String priority, String tags) {
        if (name != null && from != null && to != null && priority != null && tags != null) {
            taskList.addEvent(name, Integer.valueOf(priority), from, to, tags.split("\\s+"));
        } else if (name!= null && from != null && to != null && priority != null) {
            taskList.addEvent(name, Integer.valueOf(priority), from, to);
        } else if (name!= null && from != null && to != null && tags != null) {
            taskList.addEvent(name, from, to, tags.split("\\s+"));
        } else if (name != null && from != null && to != null) {
            taskList.addEvent(name, from, to);
        } else {
            handleError(4);
        }
    }

    /**
     * Handle user play game instruction.
     */
    private void handleGame() {
        printList(Constants.GAMES_LIST, 1);
        int choice = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
        if (choice == 1) {
            ui.printGameMsg(choice);
            ui.printDifficultyMsg(choice);
            int difficulty = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
            ui.printLengthMsg(choice);
            int length = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
            MentalMathGame game = new MentalMathGame(difficulty, length, this.ui);
            game.startGame();
        } else if (choice == 2) {
            ui.printGameMsg(choice);
            ui.printDifficultyMsg(choice);
            int difficulty = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
            WordleGame game = new WordleGame(difficulty, this.ui);
            game.startGame();
        } else if (choice == 3) {
            BlackjackGame game = new BlackjackGame(ui);
            game.startGame();
        } else {
            //go back
        }
    }

    /**
     * Handle user track instruction.
     */
    private void handleTrack() {
        printList(Constants.TRACK_LIST, 2);
        int choice = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
        if (choice == 1) {
            System.out.println("Enter START DATE (YYYY-MM-DD): ");
            System.out.println();
            String startDate = Constants.INPUT_STRING_BUILDER();
            System.out.println("Enter START TIME (HH:mm): ");
            System.out.println();
            String startTime = Constants.INPUT_STRING_BUILDER();
            System.out.println("Enter END DATE (YYYY-MM-DD): ");
            System.out.println();
            String endDate = Constants.INPUT_STRING_BUILDER();
            System.out.println("Enter END TIME (HH:mm): ");
            System.out.println();
            String endTime = Constants.INPUT_STRING_BUILDER();
            System.out.println("Enter ACTIVITY: ");
            System.out.println();
            String name = Constants.INPUT_STRING_BUILDER();
            Activity a = new Activity(startDate, startTime, endDate, endTime, name);
            TimeTracker.saveActivityToFile(a);
        } else if (choice == 2) {
            System.out.println("Enter DATE (YYYY-MM-DD): ");
            System.out.println();
            String date = Constants.INPUT_STRING_BUILDER();
            System.out.println("Enter MOOD: ");
            System.out.println();
            String mood = Constants.INPUT_STRING_BUILDER();
            //Mood m = new Mood(date, mood);
            MoodTracker.trackMood(date, mood);
        } else if (choice == 3) {
            System.out.println("Enter DATE (YYYY-MM-DD): ");
            System.out.println();
            String date = Constants.INPUT_STRING_BUILDER();
            System.out.println("Enter DRINK TYPE: ");
            System.out.println();
            String drink = Constants.INPUT_STRING_BUILDER();
            System.out.println("Enter DRINK NAME: ");
            System.out.println();
            String name = Constants.INPUT_STRING_BUILDER();
            System.out.println("Enter DRINK QTY (in standard drinks): ");
            System.out.println();
            int qty = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
            //Alcohol a = new Alcohol(date, drink, name, qty);
            AlcoholTracker.trackAlcohol(date, drink, name, qty);
        } else {
            //go back
        }
    }

    /**
     * Handle user view stats instruction.
     */
    private void handleStats() {
        printList(Constants.TRACK_LIST, 3);
        int choice = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
        if (choice == 1) {
            TimeTracker.displayStatistics();
        } else if (choice == 2) {
            MoodTracker.displayStatistics();
        } else if (choice == 3) {
            AlcoholTracker.displayStatistics();
        } else {
            //go back
        }
    }

    /**
     * Handle user chat instruction.
     */
    private void handleChat() {
        //int choice = sc.nextInt();
        //sc.nextLine(); //choose language
        ChatInstance chat = new ChatInstance();
        chat.chat();
    }

    /**
     * Handle user search by task name (partial name acceptable).
     * 
     * @param in string to be searched for
     */
    private void handleSearchName(String in) {
        ArrayList<Task> searchList = taskList.searchName(in);
        printList(searchList, 4);
    }

    /**
     * Handle user sort by priority (ascending or descending).
     * 
     * @param in string specifying order of sort (ascending or descending)
     */
    private void handleSortPriority(String in) {
        ArrayList<Task> sortedTaskList = taskList.getList();
        if (in.equals("asc")) {
            sortedTaskList = taskList.sortPriorityTaskList(0);
        } else if (in.equals("desc")) {
            sortedTaskList = taskList.sortPriorityTaskList(1);
        } else {
            handleError(8);
        }
        printList(sortedTaskList, 5);
    }

    /**
     * Handle user add tags to task.
     * 
     * @param idx index of task to add tags to
     * @param tags whitespace delimited string of tags to be added
     */
    private void handleAddTags(String idx, String tags) {
        taskList.addTags(Integer.valueOf(idx.trim()) - 1, tags.split("\\s+"));
    }

    /**
     * Handle user delete tags from task.
     * 
     * @param idx index of task to delete tags from
     * @param tags whitespace delimited string of tags to be deleted
     */
    private void handleDeleteTags(String idx, String tags) {
        taskList.removeTags(Integer.valueOf(idx.trim()) - 1, tags.split("\\s+"));
    }

    /**
     * Handle user add contact.
     */
    private void handleAddContact() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddContactWindow.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Add Contact");
                stage.setScene(new Scene(loader.load()));
                AddContactWindow controller = loader.getController();
                controller.setContactListener(this);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onContactAdded(Contact contact) {
        contactList.addContact(contact);
        System.out.println("New contact added: " + contact);
        System.out.println();
    }

    /**
     * Handle user edit contact.
     */
    private void handleEditContact(String idx) {
        Platform.runLater(() -> {
            try {
                Contact contact = contactList.getContact(Integer.valueOf(idx) - 1);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditContactWindow.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Add Contact");
                stage.setScene(new Scene(loader.load()));
                EditContactWindow controller = loader.getController();
                controller.setContact(contact);
                controller.setContactListener(this);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onContactEdited(Contact oldContact, Contact newContact) {
        if (newContact != null) {
            contactList.editContact(oldContact, newContact);
            System.out.println("Existing contact edited: " + newContact);
            System.out.println();
        }
    }

    /**
     * Handle user search for contact by name (partial name acceptable).
     * 
     * @param in string to be searched for
     */
    private void handleSearchContactName(String in) {
        ArrayList<Contact> searchList = contactList.findContactByName(in);
        printList(searchList, 4);
    }

    /**
     * Handle user search for contact by email (partial email acceptable).
     * 
     * @param in string to be searched for
     */
    private void handleSearchContactEmail(String in) {
        ArrayList<Contact> searchList = contactList.findContactsByEmail(in);
        printList(searchList, 4);
    }

    /**
     * Handle user search for contact by address (partial address acceptable).
     * 
     * @param in string to be searched for
     */
    private void handleSearchContactAddress(String in) {
        ArrayList<Contact> searchList = contactList.findContactsByAddress(in);
        printList(searchList, 4);
    }

    //error codes
    //1:invalid instruction
    //2:name field of todo is empty or whitespace
    //3:name and/or by field of deadline is empty or whitespace
    //4:name and/or from and/or to field of event is empty or whitespace
    //5:no such task exists or task number is empty or whitespace
    //6:error reading task list from file
    //7:error writing task list to file
    //8:error sorting
    //9:IO error (unspecified)
    /**
     * Handles error messages.
     * 
     * @param code int denoting the error generated
     */
    private void handleError(int code) {
        ui.printErrorMsg(code);
    }
}
