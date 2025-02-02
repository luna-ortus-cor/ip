package miku;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser class to parse user instructions and take relevant action.
 */
public class Parser {
    private static TaskList taskList;
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
        this.storage = new Storage(ui);
    }

    /**
     * Starts the parser and initializes relevant variables,
     * such as the TaskList
     */
    public void start() {
        loadTaskList();
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
        Pattern todoPattern = Pattern.compile("^todo (.+)$");
        Pattern deadlinePattern = Pattern.compile("^deadline (.+)$");
        Pattern eventPattern = Pattern.compile("^event (.+)$");
        Pattern todoPatternPrio = Pattern.compile("^todo (.+)\\s+/prio\\s+(\\d)$");
        Pattern deadlinePatternPrio = Pattern.compile("^deadline (.+)\\s+/prio\\s+(\\d)$");
        Pattern eventPatternPrio = Pattern.compile("^event (.+)\\s+/prio\\s+(\\d)$");
        Pattern simpleCommandsPattern = Pattern.compile("^(games|track|stats|chat|bye|help)$");
        Pattern searchNamePattern = Pattern.compile("^find (.+)$");
        Pattern setPriorityPattern = Pattern.compile("^set (\\d+) (\\d)$");
        Pattern sortPriorityPattern = Pattern.compile("^sort prio /(asc|desc)$");
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
        } else if ((matcher = todoPatternPrio.matcher(in)).matches()) {
            handleTodo(matcher.group(1), matcher.group(2));
        } else if ((matcher = deadlinePatternPrio.matcher(in)).matches()) {
            handleDeadline(matcher.group(1), matcher.group(2));
        } else if ((matcher = eventPatternPrio.matcher(in)).matches()) {
            handleEvent(matcher.group(1), matcher.group(2));
        } else if ((matcher = todoPattern.matcher(in)).matches()) {
            handleTodo(matcher.group(1));
        } else if ((matcher = deadlinePattern.matcher(in)).matches()) {
            handleDeadline(matcher.group(1));
        } else if ((matcher = eventPattern.matcher(in)).matches()) {
            handleEvent(matcher.group(1));
        } else if ((matcher = searchNamePattern.matcher(in)).matches()) {
            handleSearchName(matcher.group(1));
        } else if ((matcher = setPriorityPattern.matcher(in)).matches()) {
            handleSetPriority(matcher.group(1), matcher.group(2));
        } else if ((matcher = sortPriorityPattern.matcher(in)).matches()) {
            handleSortPriority(matcher.group(1));
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
     * Print an arraylist, type specifies what message to print prior.
     *
     * @param list arraylist to be printed
     * @param type int specifying message to be printed prior to arraylist print
     */
    //type=0 is task, type=1 is game, type=2 is track, type=3 is stats, type=4 is search, type=5 is sort
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
        } else {
            ui.printSortMsg();
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

    private void handleTodo(String in) {
        Pattern todoPattern = Pattern.compile("^(.*)$");
        Matcher matcher = todoPattern.matcher(in);

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            if (name.isEmpty()) {
                handleError(2); // Empty task description
            } else {
                taskList.addTodo(name);
            }
        } else {
            handleError(2); // Invalid input
        }
    }

    private void handleTodo(String in, String priority) {
        Pattern todoPattern = Pattern.compile("^(.*)$");
        Matcher matcher = todoPattern.matcher(in);

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            if (name.isEmpty()) {
                handleError(2); // Empty task description
            } else {
                taskList.addTodo(name, Integer.valueOf(priority.trim()));
            }
        } else {
            handleError(2); // Invalid input
        }
    }

    private void handleDeadline(String in) {
        Pattern deadlinePattern = Pattern.compile("^(.*?)\\s+/by\\s+(.*)$");
        Matcher matcher = deadlinePattern.matcher(in);

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            String by = matcher.group(2).trim();

            if (name.isEmpty() || by.isEmpty()) {
                handleError(3); // Empty task description or deadline
            } else {
                taskList.addDeadline(name, by);
            }
        } else {
            handleError(3); // Invalid input
        }
    }

    private void handleDeadline(String in, String priority) {
        Pattern deadlinePattern = Pattern.compile("^(.*?)\\s+/by\\s+(.*)$");
        Matcher matcher = deadlinePattern.matcher(in);

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            String by = matcher.group(2).trim();

            if (name.isEmpty() || by.isEmpty()) {
                handleError(3); // Empty task description or deadline
            } else {
                taskList.addDeadline(name, Integer.valueOf(priority.trim()), by);
            }
        } else {
            handleError(3); // Invalid input
        }
    }

    private void handleEvent(String in) {
        Pattern eventPattern = Pattern.compile("^(.*?)\\s+/from\\s+(.*?)\\s+/to\\s+(.*)$");
        Matcher matcher = eventPattern.matcher(in);

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            String from = matcher.group(2).trim();
            String to = matcher.group(3).trim();

            if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                handleError(4); // Empty task description, from, or to fields
            } else {
                taskList.addEvent(name, from, to);
            }
        } else {
            handleError(4); // Invalid input
        }
    }

    private void handleEvent(String in, String priority) {
        Pattern eventPattern = Pattern.compile("^(.*?)\\s+/from\\s+(.*?)\\s+/to\\s+(.*)$");
        Matcher matcher = eventPattern.matcher(in);

        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            String from = matcher.group(2).trim();
            String to = matcher.group(3).trim();

            if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                handleError(4); // Empty task description, from, or to fields
            } else {
                taskList.addEvent(name, Integer.valueOf(priority.trim()), from, to);
            }
        } else {
            handleError(4); // Invalid input
        }
    }

    private void handleGame() {
        printList(Constants.GAMES_LIST, 1);
        int choice = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
        if (choice == 1) {
            ui.printGameMsg(choice);
            ui.printDifficultyMsg(choice);
            int difficulty = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
            ui.printLengthMsg(choice);
            int length = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
            MentalMathGame game = new MentalMathGame(difficulty, length);
            game.startGame();
        } else if (choice == 2) {
            ui.printGameMsg(choice);
            ui.printDifficultyMsg(choice);
            int difficulty = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
            WordleGame game = new WordleGame(difficulty);
            game.startGame();
        } else {
            //go back
        }
    }

    private void handleTrack() {
        printList(Constants.TRACK_LIST, 2);
        int choice = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
        if (choice == 1) {
            System.out.print("Enter START DATE (YYYY-MM-DD): ");
            String startDate = Constants.INPUT_STRING_BUILDER();
            System.out.print("Enter START TIME (HH:mm): ");
            String startTime = Constants.INPUT_STRING_BUILDER();
            System.out.print("Enter END DATE (YYYY-MM-DD): ");
            String endDate = Constants.INPUT_STRING_BUILDER();
            System.out.print("Enter END TIME (HH:mm): ");
            String endTime = Constants.INPUT_STRING_BUILDER();
            System.out.print("Enter ACTIVITY: ");
            String name = Constants.INPUT_STRING_BUILDER();
            Activity a = new Activity(startDate, startTime, endDate, endTime, name);
            TimeTracker.saveActivityToFile(a);
        } else {
            //go back
        }
    }

    private void handleStats() {
        printList(Constants.TRACK_LIST, 3);
        int choice = Integer.valueOf(Constants.INPUT_STRING_BUILDER());
        if (choice == 1) {
            TimeTracker.displayStatistics();
        } else {
            //go back
        }
    }

    private void handleChat() {
        //int choice = sc.nextInt();
        //sc.nextLine(); //choose language
        ChatInstance chat = new ChatInstance();
        chat.chat();
    }

    private void handleSearchName(String in) {
        ArrayList<Task> searchList = taskList.searchName(in);
        printList(searchList, 4);
    }

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

    //error codes
    //1:invalid instruction
    //2:name field of todo is empty or whitespace
    //3:name and/or by field of deadline is empty or whitespace
    //4:name and/or from and/or to field of event is empty or whitespace
    //5:no such task exists or task number is empty or whitespace
    //6:error reading task list from file
    //7:error writing task list to file
    //8:error sorting
    private void handleError(int code) {
        ui.printErrorMsg(code);
    }
}
