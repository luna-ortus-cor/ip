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
    public static final String TASK_LIST_MSG = "here are the tasks in your list desu:";
    public static final String CONTACT_LIST_MSG = "here are the contacts you have desu:";
    public static final String TASK_LIST_SORTED_MSG = "here are the tasks in your list, sorted desu:";
    public static final String TODO_EMPTY_WS = ":3 oops! name field of todo cannot be empty!";
    public static final String DEADLINE_EMPTY_WS = ":3 oops! name and/or by field of deadline cannot be empty!";
    public static final String EVENT_EMPTY_WS = ":3 oops! name and/or from and/or to field of event cannot be empty!";
    public static final String LIST_IDX_INVALID = ":3 oops! no such task exists or task number is empty!";
    public static final String GAMES_MSG = "yay~ here are the list of games available!";
    public static final ArrayList<String> GAMES_LIST =
        new ArrayList<String>(Arrays.asList("Mental Math", "Wordle", "Back"));
    public static final String TRACK_MSG = "^~^ sure! what would you like to track?";
    public static final ArrayList<String> TRACK_LIST =
        new ArrayList<String>(Arrays.asList("Time", "Mood", "Alcohol", "Back"));
    public static final String STATS_MSG = "^~^ sure! which stats would you like to see?";
    public static final String FILEPATH_WORDLIST = "./data/words.txt";
    public static final String FILEPATH_TIME_TRACKER = "./data/time_tracker.txt";
    public static final String FILEPATH_TASKLIST = "./data/tasks.txt";
    public static final String READ_LIST_FILE_ERROR = ":3 oops! error reading list from file!";
    public static final String WRITE_LIST_FILE_ERROR = ":3 oops! error writing list to file!";
    public static final String READ_WORDLIST_FILE_ERROR = ":3 oops! error reading wordlist!";
    public static final String TASK_MARK_DONE_SUCCESS = "YATTA! I've marked this task as done:";
    public static final String TASK_MARK_DONE_FAILURE = "Task is already done";
    public static final String TASK_MARK_NOT_DONE_SUCCESS = "T_T I've marked this task as not done:";
    public static final String TASK_MARK_NOT_DONE_FAILURE = "Task is already not done";
    public static final String AWAIT_INSTR =
        "Please enter an instruction (type \"help\" for the full list of instructions)";
    public static final String SEARCH_MSG = "here are the results of your search desu:";
    public static final ArrayList<String> HELP_LIST =
        new ArrayList<String>(Arrays.asList("type \"bye\" to exit", "type \"list\" to see list of tasks",
                    "type \"todo <name> [/prio <num>]\" to add a todo",
                    "type \"deadline <name> /by <datetime> [/prio <num>]\" to add a deadline",
                    "type \"event <name> /from <datetime> /to <datetime> [/prio <num>]\" to add an event",
                    "type \"(un)mark <num>\" to mark the task <num> (not) done",
                    "type \"set <num1> <num2>\" to set the priority of task <num1> to <num2>",
                    "type \"sort prio <order>\" to sort the task list in order of priority",
                    "type \"games\" to see the list of games", "type \"track\" to track a statistic",
                    "type \"stats\" to see the list of statistics", "type \"chat\" to chat with Miku",
                    "fields in [square brackets] are optional", "priority is a number from 1 to 5 " +
                    "with 1 the lowest priority and 5 the highest priority, defaults to 3",
                    "order is one of \"/asc\" or \"/desc\" specifying if the sort should be in " +
                    "ascending or descending order respectively"));
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
    public static final String SORT_LIST_ERROR = ":3 oops! error when sorting!";
    public static final String UNSPECIFIED_IO_ERROR = ":3 oops! an unknown IO error occurred";
    public static final String TASK_EDIT_TAG_MSG = "Edited the following task:";

    public static String INPUT_STRING_BUILDER() {
        StringBuilder input = new StringBuilder();
        try {
            int c;
            while ((c = System.in.read()) != -1) {
                input.append((char) c);
                if (c == '\n') break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input.toString().trim();
    }
}
