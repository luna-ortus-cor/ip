package miku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Constant class that just holds constants.
 */
public class Constants {
    public static final String INDENT = "    ";
    public static final String MIKU_DELIMITER = "~Miku";
    public static final String MIKU_LOGO =
        " __  __ _ _\n|  \\/  (_) | ___   _\n| |\\/| | | |/ / | | |\n"
        + "| |  | | |   <| |_| |\n|_|  |_|_|_|\\_\\\\__,_|";
    public static final String START_MSG_1 = "yahallo! i'm Miku desuyo!";
    public static final String START_MSG_2 = "what can i do for you?";
    public static final String EXIT_MSG = "byee~ hope to see you again soon!";
    public static final String INSTR_INVALID = ":3 oops! i don't understand that!";
    public static final String ADD_TASK = "got it! I've added this task desu:";
    public static final String ADD_LOCATION = "got it! I've added this location desu:";
    public static final String TASK_LIST_MSG = "here are the tasks in your list desu:";
    public static final String CONTACT_LIST_MSG = "here are the contacts you have desu:";
    public static final String LOCATION_LIST_MSG = "here are the locations you have desu:";
    public static final String TASK_LIST_SORTED_MSG = "here are the tasks in your list, sorted desu:";
    public static final String TODO_EMPTY_WS = ":3 oops! name field of todo cannot be empty!";
    public static final String DEADLINE_EMPTY_WS = ":3 oops! name and/or by field of deadline cannot be empty!";
    public static final String EVENT_EMPTY_WS = ":3 oops! name and/or from and/or to field of event cannot be empty!";
    public static final String LIST_IDX_INVALID = ":3 oops! no such list item exists or list index is empty!";
    public static final String GAMES_MSG = "yay~ here are the list of games available!";
    public static final ArrayList<String> GAMES_LIST =
        new ArrayList<String>(Arrays.asList("Mental Math", "Wordle", "Blackjack", "Back"));
    public static final String TRACK_MSG = "^~^ sure! what would you like to track?";
    public static final ArrayList<String> TRACK_LIST =
        new ArrayList<String>(Arrays.asList("Time", "Mood", "Alcohol", "Back"));
    public static final String STATS_MSG = "^~^ sure! which stats would you like to see?";
    public static final String FILEPATH_WORDLIST = "/data/words.txt";
    public static final String FILEPATH_TIME_TRACKER = "./data/time_tracker.txt";
    public static final String FILEPATH_MOOD_TRACKER = "./data/mood_tracker.txt";
    public static final String FILEPATH_ALCOHOL_TRACKER = "./data/alcohol_tracker.txt";
    public static final String FILEPATH_TASKLIST = "./data/tasks.txt";
    public static final String FILEPATH_CONTACTLIST = "./data/contacts.txt";
    public static final String FILEPATH_LOCATIONLIST = "./data/locations.txt";
    public static final String FILEPATH_ASSOCIATION = "./data/associations.txt";
    public static final String FILEPATH_SETTINGS = "./data/settings.txt";
    public static final String READ_TASK_LIST_FILE_ERROR = ":3 oops! error reading task list from file!";
    public static final String WRITE_TASK_LIST_FILE_ERROR = ":3 oops! error writing task list to file!";
    public static final String READ_CONTACT_LIST_FILE_ERROR = ":3 oops! error reading contact list from file!";
    public static final String WRITE_CONTACT_LIST_FILE_ERROR = ":3 oops! error writing contact list to file!";
    public static final String READ_LOCATION_LIST_FILE_ERROR = ":3 oops! error reading location list from file!";
    public static final String WRITE_LOCATION_LIST_FILE_ERROR = ":3 oops! error writing location list to file!";
    public static final String READ_ASSOCIATIONS_FILE_ERROR = ":3 oops! error reading associations from file!";
    public static final String WRITE_ASSOCIATIONS_FILE_ERROR = ":3 oops! error writing associations to file!";
    public static final String READ_WORDLIST_FILE_ERROR = ":3 oops! error reading wordlist!";
    public static final String TASK_MARK_DONE_SUCCESS = "YATTA! I've marked this task as done:";
    public static final String TASK_MARK_DONE_FAILURE = "Task is already done";
    public static final String TASK_MARK_NOT_DONE_SUCCESS = "T_T I've marked this task as not done:";
    public static final String TASK_MARK_NOT_DONE_FAILURE = "Task is already not done";
    public static final String AWAIT_INSTR =
        "Please enter an instruction (type \"help\" for the full list of instructions)";
    public static final String SEARCH_MSG = "here are the results of your search desu:";
    public static final int MIN_PRIORITY = 1;
    public static final int MAX_PRIORITY = 5;
    public static final ArrayList<String> HELP_LIST =
        new ArrayList<String>(Arrays.asList(
            "Help Guide:",
            "────────────────────────────────",
            "General Commands:",
            " - type \"bye\" to exit the bot",
            " - type \"help\" to see this help message",
            " - type \"chat\" to chat with Miku",
            " - type \"games\" to see the list of games",
            " - type \"track\" to track a statistic",
            " - type \"stats\" to see the list of statistics",
            "────────────────────────────────",
            "Task Management:",
            " - type \"list\" to see all tasks",
            " - type \"todo <name> [/prio <num>] [/tags <tag1 tag2>]\" to add a to-do task",
            " - type \"deadline <name> /by <datetime> [/prio <num>] [/tags <tag1 tag2>]\" to add a deadline task",
            " - type \"event <name> /from <datetime> /to <datetime> "
            + "[/prio <num>] [/tags <tag1 tag2>]\" to add an event",
            " - type \"(un)mark <num>\" to mark/unmark task <num> as done",
            " - type \"set <num1> <num2>\" to set priority of task <num1> to <num2>",
            " - type \"sort prio <order>\" to sort tasks by priority in ascending or descending order",
            " - type \"find task name <name>\" to search for tasks by name",
            " - type \"add tags <num> <tag1 tag2>\" to add tags to task <num>",
            " - type \"delete tags <num> <tag1 tag2>\" to remove specific tags from task <num>",
            " - type \"delete all tags <num>\" to remove all tags from task <num>",
            " - type \"delete <num>\" to delete task <num>",
            " - type \"delete all\" to delete all tasks",
            "────────────────────────────────",
            "Contact Management:",
            " - type \"add contact\" to add a new contact",
            " - type \"edit contact <num>\" to edit contact <num>",
            " - type \"contacts\" to view all contacts",
            " - type \"find name <name>\" to search for a contact by name",
            " - type \"find email <email>\" to search for a contact by email",
            " - type \"find address <address>\" to search for a contact by address",
            " - type \"delete contact <num>\" to delete a contact",
            " - type \"delete all contacts\" to delete all contacts",
            "────────────────────────────────",
            "Location Management:",
            " - type \"add place <name> /desc <description> /address <address> "
            + "/lat <latitude> /lon <longitude>\" to add a place",
            " - type \"add website <name> /desc <description> /url <URL>\" to add a website",
            " - type \"find location name <name>\" to search for a location",
            " - type \"locations\" to list all locations",
            " - type \"delete location <num>\" to delete a location",
            " - type \"delete all locations\" to delete all locations",
            "────────────────────────────────",
            "Task Associations:",
            " - type \"associate /task <task_id> /contact <contact_id>\" to associate a task with a contact",
            " - type \"associate /task <task_id> /location <location_id>\" to associate a task with a location",
            " - type \"find task by contact <contact_id>\" to find tasks associated with a contact",
            " - type \"find task by location <location_id>\" to find tasks associated with a location",
            " - type \"find contact by task <task_id>\" to find contacts associated with a task",
            " - type \"find location by task <task_id>\" to find locations associated with a task",
            "────────────────────────────────",
            "Notes:",
            " - Fields in [square brackets] are optional",
            " - Priority is a number from 1 (lowest) to 5 (highest), defaults to 3",
            " - Order for sorting is either \"/asc\" (ascending) or \"/desc\" (descending)"
        ));
    public static final ArrayList<String> MISC_LIST =
        new ArrayList<String>(Arrays.asList("Note that chat functionality is a proof-of-concept and does not "
                    + "function optimally; it is likely to timeout and crash due to the nature of the LLM API "
                    + "inferencing used. Work is in progress to transition to a locally hosted model instead.",
                    "Settings functionality is also not complete and is just a rough outline, only some elements "
                    + "of the theme setting have been implemented. Font size changes are only reflected on the next "
                    + "app startup.", "Association functionality does not support persistence yet."));
    public static final String TASK_SET_PRIORITY_SUCCESS = "YATTA! I've changed the priority of this task:";
    public static final String TASK_SET_PRIORITY_FAILURE = "Task is already at that priority";
    public static final String GAME_MATH_INTRO_MSG = "Welcome to Mental Math Game!";
    public static final String GAME_WORDLE_INTRO_MSG = "Welcome to Wordle Game!";
    public static final String GAME_MATH_DIFFICULTY_MSG =
        "Select difficulty (1: Easy, 2: Normal, 3: Hard, 4: Insane): ";
    public static final String GAME_WORDLE_DIFFICULTY_MSG =
        "Select difficulty (1: Easy, 2: Normal, 3: Hard): ";
    public static final String GAME_MATH_LENGTH_MSG =
        "Select length (1: Short, 2: Normal, 3: Long): ";
    public static final String GAME_TERMINATED = "T_T game stopped";
    public static final String SORT_LIST_ERROR = ":3 oops! error when sorting!";
    public static final String UNSPECIFIED_IO_ERROR = ":3 oops! an unknown IO error occurred";
    public static final String TASK_EDIT_TAG_MSG = "Edited the following task:";
    public static final String INVALID_CONTACT_EDIT = "No changes detected. Please modify at least one field.";
    public static final String INVALID_EMAIL_ERROR = "One or more of the emails provided is invalid.";
    public static final String BJ_INVALID_CHOICE_MSG = "Invalid choice. Type 'h' to hit or 's' to stand.";
    public static final String BJ_BUST_MSG = "You busted on this hand!";
    public static final String BJ_CHOICE_MSG = "Do you want to (h)it or (s)tand? ";
    public static final String ASSOCIATION_CREATED_MSG = "Association successfully created! :D";

    /**
     * Builds input string character by character from the user input to the GUI.
     */
    public static final String buildInputString() {
        StringBuilder input = new StringBuilder();
        try {
            int c;
            while ((c = System.in.read()) != -1) {
                input.append((char) c);
                if (c == '\n') {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input.toString().trim();
    }
}
