package miku;

/**
 * Ui class for handling user interface and printing messages to screen.
 * This is a text based ui. Support for javafx to be added.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Prints start message.
     */
    public void printStartMsg() {
        System.out.println(Constants.MIKU_LOGO);
        System.out.println(Constants.INDENT + Constants.START_MSG_1);
        System.out.println(Constants.INDENT + Constants.START_MSG_2);
        System.out.println();
    }

    /**
     * Prints a message prompting user for next instruction.
     */
    public void printNextInstrMsg() {
        System.out.println(Constants.AWAIT_INSTR);
        System.out.println();
    }

    /**
     * Prints exit message.
     */
    public void printExitMsg() {
        System.out.println(Constants.INDENT + Constants.EXIT_MSG);
        System.out.println();
    }

    /**
     * Prints a list item.
     *
     * @param idx the index of the item in the list
     * @param item the item to be printed
     */
    public void printListItem(int idx, Object item) {
        System.out.println(Constants.INDENT + Constants.INDENT + idx + ". " + item.toString());
    }

    /**
     * Prints the message when the user searches.
     */
    public void printSearchMsg() {
        System.out.println(Constants.INDENT + Constants.SEARCH_MSG);
    }

    /**
     * Prints the message when the user sorts.
     */
    public void printSortMsg() {
        System.out.println(Constants.INDENT + Constants.TASK_LIST_SORTED_MSG);
    }

    /**
     * Prints the message when the user edits the tags of a task.
     *
     * @param task the task to be edited
     */
    public <T extends Task> void printEditTagsMsg(T task) {
        System.out.println(Constants.INDENT + Constants.TASK_EDIT_TAG_MSG);
        System.out.println(Constants.INDENT + Constants.INDENT + task.toString());
        System.out.println();
    }

    /**
     * Prints the help message.
     */
    public void printHelpMsg() {
        for (String s:Constants.HELP_LIST) {
            System.out.println(Constants.INDENT + s);
        }
        System.out.println();
    }

    /**
     * Prints the message when the user marks a task as done.
     *
     * @param task the task to be marked done
     * @param response an int denoting if the task is already done or not
     */
    public <T extends Task> void printMarkDoneMsg(T task, int response) {
        if (response == 1) {
            System.out.println(Constants.INDENT + Constants.TASK_MARK_DONE_SUCCESS);
            System.out.println(Constants.INDENT + Constants.INDENT + task.toString());
        } else {
            System.out.println(Constants.INDENT + Constants.TASK_MARK_DONE_FAILURE);
        }
        System.out.println();
    }

    /**
     * Prints the message when the user marks a task as not done.
     *
     * @param task the task to be marked not done
     * @param response an int denoting if the task is already not done or not
     */
    public <T extends Task> void printMarkNotDoneMsg(T task, int response) {
        if (response == 1) {
            System.out.println(Constants.INDENT + Constants.TASK_MARK_NOT_DONE_SUCCESS);
            System.out.println(Constants.INDENT + Constants.INDENT + task.toString());
        } else {
            System.out.println(Constants.INDENT + Constants.TASK_MARK_NOT_DONE_FAILURE);
        }
        System.out.println();
    }

    /**
     * Prints the message when the user adds a task.
     *
     * @param task the task to be added
     * @param num the number of tasks in the task list after the addition
     */
    public <T extends Task> void printAddMsg(T task, int num) {
        System.out.println(Constants.INDENT + Constants.ADD_TASK);
        System.out.println(Constants.INDENT + Constants.INDENT + task.toString());
        System.out.println(Constants.INDENT + "now you have " + num + " tasks in the list.");
        System.out.println();
    }

    /**
     * Prints the message when the user adds a location.
     *
     * @param loc the location to be added
     * @param num the number of locations in the task list after the addition
     */
    public void printAddLocationMsg(Location loc, int num) {
        System.out.println(Constants.INDENT + Constants.ADD_LOCATION);
        System.out.println(Constants.INDENT + Constants.INDENT + loc.toString());
        System.out.println(Constants.INDENT + "now you have " + num + " locations in the list.");
        System.out.println();
    }

    /**
     * Prints the message when the user deletes a task.
     *
     * @param task the task to be deleted
     * @param num the number of tasks in the task list after the deletion
     */
    public <T extends Task> void printDeleteMsg(T task, int num) {
        System.out.println(Constants.INDENT + "UwU i've removed this task:");
        System.out.println(Constants.INDENT + Constants.INDENT + task.toString());
        System.out.println(Constants.INDENT + "now you have " + num + " tasks in the list.");
        System.out.println();
    }

    /**
     * Prints the message when the user deletes a contact.
     *
     * @param contact the contact to be deleted
     * @param num the number of contacts in the contact list after the deletion
     */
    public void printDeleteContactMsg(Contact contact, int num) {
        System.out.println(Constants.INDENT + "UwU i've removed this contact:");
        System.out.println(Constants.INDENT + Constants.INDENT + contact.toString());
        System.out.println(Constants.INDENT + "now you have " + num + " contacts in the list.");
        System.out.println();
    }

    /**
     * Prints the message when the user deletes a location.
     *
     * @param location the location to be deleted
     * @param num the number of locations in the location list after the deletion
     */
    public void printDeleteLocationMsg(Location location, int num) {
        System.out.println(Constants.INDENT + "UwU i've removed this location:");
        System.out.println(Constants.INDENT + Constants.INDENT + location.toString());
        System.out.println(Constants.INDENT + "now you have " + num + " locations in the list.");
        System.out.println();
    }

    /**
     * Prints the message when the user deletes all tasks.
     */
    public void printDeleteAllMsg() {
        System.out.println(Constants.INDENT + "UwU i've removed all tasks");
        System.out.println();
    }

    /**
     * Prints the message when the user deletes all contacts.
     */
    public void printDeleteAllContactsMsg() {
        System.out.println(Constants.INDENT + "UwU i've removed all contacts");
        System.out.println();
    }

    /**
     * Prints the message when the user deletes all locations.
     */
    public void printDeleteAllLocationsMsg() {
        System.out.println(Constants.INDENT + "UwU i've removed all locations");
        System.out.println();
    }

    /**
     * Prints the message when the user sets the priority of a task.
     *
     * @param task the task whose priority is to be set
     * @param response an int denoting if the task is already at the specified priority
     */
    public <T extends Task> void printSetPriorityMsg(T task, int response) {
        if (response == 1) {
            System.out.println(Constants.INDENT + Constants.TASK_SET_PRIORITY_SUCCESS);
            System.out.println(Constants.INDENT + Constants.INDENT + task.toString());
        } else {
            System.out.println(Constants.INDENT + Constants.TASK_SET_PRIORITY_FAILURE);
        }
        System.out.println();
    }

    /**
     * Prints the message when the user asks to list all tasks.
     */
    public void printTaskListMsg() {
        System.out.println(Constants.INDENT + Constants.TASK_LIST_MSG);
    }

    /**
     * Prints the message when the user lists contacts.
     */
    public void printContactListMsg() {
        System.out.println(Constants.INDENT + Constants.CONTACT_LIST_MSG);
    }

    /**
     * Prints the message when the user lists locations.
     */
    public void printLocationListMsg() {
        System.out.println(Constants.INDENT + Constants.LOCATION_LIST_MSG);
    }

    /**
     * Prints the message when the user asks to play a game.
     */
    public void printGamesMsg() {
        System.out.println(Constants.INDENT + Constants.GAMES_MSG);
    }

    /**
     * Prints the message when the user asks to track a statistic.
     */
    public void printTrackMsg() {
        System.out.println(Constants.INDENT + Constants.TRACK_MSG);
    }

    /**
     * Prints the message when the user asks to view statistics.
     */
    public void printStatsMsg() {
        System.out.println(Constants.INDENT + Constants.STATS_MSG);
    }

    /**
     * Prints a delimiter.
     */
    public void printDelim() {
        System.out.println();
    }

    /**
     * Print individual game message.
     *
     * @param game int specifying game the user chose to play
     */
    public void printGameMsg(int game) {
        switch (game) {
        case 1:
            System.out.println(Constants.INDENT + Constants.GAME_MATH_INTRO_MSG);
            break;
        case 2:
            System.out.println(Constants.INDENT + Constants.GAME_WORDLE_INTRO_MSG);
            break;
        default:
            break;
        }
        System.out.println();
    }

    /**
     * Print individual game difficulty message.
     *
     * @param game int specifying game the user chose to play
     */
    public void printDifficultyMsg(int game) {
        switch (game) {
        case 1:
            System.out.println(Constants.INDENT + Constants.GAME_MATH_DIFFICULTY_MSG);
            break;
        case 2:
            System.out.println(Constants.INDENT + Constants.GAME_WORDLE_DIFFICULTY_MSG);
            break;
        default:
            break;
        }
        System.out.println();
    }

    /**
     * Print individual game length message.
     *
     * @param game int specifying game the user chose to play
     */
    public void printLengthMsg(int game) {
        switch (game) {
        case 1:
            System.out.println(Constants.INDENT + Constants.GAME_MATH_LENGTH_MSG);
            break;
        default:
            break;
        }
        System.out.println();
    }

    /**
     * Prints the message when the user terminates the game abruptly.
     */
    public void printGameTerminatedMsg() {
        System.out.println(Constants.INDENT + Constants.GAME_TERMINATED);
        System.out.println();
    }

    /**
     * Prints the message when the user makes an invalid choice in blackjack.
     */
    public void printBlackjackInvalidChoiceMsg() {
        System.out.println(Constants.BJ_INVALID_CHOICE_MSG);
        System.out.println();
    }

    /**
     * Prints the message when the user busts in blackjack.
     */
    public void printBlackjackBustMsg() {
        System.out.println(Constants.BJ_BUST_MSG);
        System.out.println();
    }

    /**
     * Prints the message for the user to make a choice in blackjack.
     */
    public void printBlackjackChoiceMsg() {
        System.out.println(Constants.BJ_CHOICE_MSG);
        System.out.println();
    }

    /**
     * Prints the relevant error message given an error code.
     *
     * @param code an int denoting the specific type of error
     */
    public void printErrorMsg(int code) {
        switch (code) {
        case 1:
            System.out.println(Constants.INDENT + Constants.INSTR_INVALID);
            break;
        case 2:
            System.out.println(Constants.INDENT + Constants.TODO_EMPTY_WS);
            break;
        case 3:
            System.out.println(Constants.INDENT + Constants.DEADLINE_EMPTY_WS);
            break;
        case 4:
            System.out.println(Constants.INDENT + Constants.EVENT_EMPTY_WS);
            break;
        case 5:
            System.out.println(Constants.INDENT + Constants.LIST_IDX_INVALID);
            break;
        case 6:
            System.out.println(Constants.INDENT + Constants.READ_TASK_LIST_FILE_ERROR);
            break;
        case 7:
            System.out.println(Constants.INDENT + Constants.WRITE_TASK_LIST_FILE_ERROR);
            break;
        case 8:
            System.out.println(Constants.INDENT + Constants.SORT_LIST_ERROR);
            break;
        case 9:
            System.out.println(Constants.INDENT + Constants.UNSPECIFIED_IO_ERROR);
            break;
        case 10:
            System.out.println(Constants.INDENT + Constants.READ_CONTACT_LIST_FILE_ERROR);
            break;
        case 11:
            System.out.println(Constants.INDENT + Constants.WRITE_CONTACT_LIST_FILE_ERROR);
            break;
        case 12:
            System.out.println(Constants.INDENT + Constants.READ_LOCATION_LIST_FILE_ERROR);
            break;
        case 13:
            System.out.println(Constants.INDENT + Constants.WRITE_LOCATION_LIST_FILE_ERROR);
            break;
        default:
            break;
        }
        System.out.println();
    }
}
