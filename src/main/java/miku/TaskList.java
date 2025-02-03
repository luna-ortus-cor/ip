package miku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to store array of tasks and handle operations on tasks in list.
 */
public class TaskList {
    private static final String FILE_PATH = Constants.FILEPATH_TASKLIST;
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private Ui ui;

    /**
     * Instantiates a new TaskList instance taking in a Ui ui.
     *
     * @param ui a Ui instance
     */
    public TaskList(Ui ui) {
        this.ui = ui;
    }

    /**
     * Returns an arraylist of tasks in the TaskList.
     *
     * @return an arraylist of tasks in the TaskList
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Returns an arraylist of tasks in the TaskList, sorted by priority.
     * 
     * @param order int specifying order (ascending or descending) of the sort
     * @return an arraylist of tasks in the TaskList sorted by priority
     */
    public ArrayList<Task> sortPriorityTaskList(int order) {
        ArrayList<Task> sortedTaskList = new ArrayList<>(this.taskList);
        Collections.sort(sortedTaskList);
        if (order == 1) {
            Collections.reverse(sortedTaskList);
        }
        return sortedTaskList;
    }

    /**
     * Load tasks from a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void loadTasks(Storage s) {
        this.taskList = s.readTasks(FILE_PATH);
    }

    /**
     * Save tasks to a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void saveTasks(Storage s) {
        s.writeTasks(this.taskList, FILE_PATH);
    }

    /**
     * Mark the task at a specified index as done.
     *
     * @param idx the index of the task in the list
     */
    public void markDone(int idx) {
        try {
            int response = taskList.get(idx).markDone();
            ui.printMarkDoneMsg(taskList.get(idx), response);
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Mark the task at a specified index as not done.
     *
     * @param idx the index of the task in the list
     */
    public void markNotDone(int idx) {
        try {
            int response = taskList.get(idx).markNotDone();
            ui.printMarkNotDoneMsg(taskList.get(idx), response);
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    public void addTags(int idx, String... tags) {
        try {
            for (String s:tags) {
                taskList.get(idx).addTag(s);
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public void removeTags(int idx, String... tags) {
        try {
            for (String s:tags) {
                taskList.get(idx).removeTag(s);
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }

    /**
     * Delete the task at a specified index.
     *
     * @param idx the index of the task in the list
     */
    public void delete(int idx) {
        try {
            Task t = taskList.get(idx);
            taskList.remove(idx);
            ui.printDeleteMsg(t, taskList.size());
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Delete all tasks in the list.
     */
    public void deleteAll() {
        taskList.clear();
        ui.printDeleteAllMsg();
    }

    /**
     * Set the priority of the task at a specified index
     *
     * @param idx the index of the task in the list
     * @param priority the priority to be set to
     */
    public void setPriority(int idx, int priority) {
        try {
            int response = taskList.get(idx).setPriority(priority);
            ui.printSetPriorityMsg(taskList.get(idx), response);
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Add a new Todo Task to the list.
     *
     * @param name description of the Todo
     */
    public void addTodo(String name) {
        Todo t = new Todo(name);
        taskList.add(t);
        ui.printAddMsg(t, taskList.size());
    }

    /**
     * Add a new Todo Task to the list.
     *
     * @param name desciption of the Todo
     * @param priority priority of the Todo
     */
    public void addTodo(String name, int priority) {
        Todo t = new Todo(name, priority);
        taskList.add(t);
        ui.printAddMsg(t, taskList.size());
    }

    /**
     * Add a new Deadline Task to the list.
     *
     * @param name description of the Deadline
     * @param by a String representing either a colloquial time or valid date time formatted time
     */
    public void addDeadline(String name, String by) {
        Deadline d = new Deadline(name, by);
        taskList.add(d);
        ui.printAddMsg(d, taskList.size());
    }

    /**
     * Add a new Deadline Task to the list.
     *
     * @param name description of the Deadline
     * @param priority priority of the Deadline
     * @param by a String representing either a colloquial time or valid date time formatted time
     */
    public void addDeadline(String name, int priority, String by) {
        Deadline d = new Deadline(name, priority, by);
        taskList.add(d);
        ui.printAddMsg(d, taskList.size());
    }

    /**
     * Add a new Event Task to the list.
     *
     * @param name description of the Event
     * @param from a String representing either a colloquial time or valid date time formmated time
     * @param to a String representing either a colloquial time or valid date time formatted time
     */
    public void addEvent(String name, String from, String to) {
        Event e = new Event(name, from, to);
        taskList.add(e);
        ui.printAddMsg(e, taskList.size());
    }

    /**
     * Add a new Event Task to the list.
     *
     * @param name description of the Event
     * @param priority priority of the Event
     * @param from a String representing either a colloquial time or valid date time formmated time
     * @param to a String representing either a colloquial time or valid date time formatted time
     */
    public void addEvent(String name, int priority, String from, String to) {
        Event e = new Event(name, priority, from, to);
        taskList.add(e);
        ui.printAddMsg(e, taskList.size());
    }

    /**
     * Search for tasks where a particular string appears as a substring in task description.
     *
     * @param in string to be searched for in task descriptions
     * @return arraylist of tasks where search string appears as substring in task description
     */
    public ArrayList<Task> searchName(String in) {
        ArrayList<Task> temp = new ArrayList<Task>();
        Pattern searchPattern = Pattern.compile(".*" + Pattern.quote(in) + ".*");
        Matcher matcher;
        for (Task t:taskList) {
            matcher = searchPattern.matcher(t.getName());
            if (matcher.find()) {
                temp.add(t);
            }
        }
        return temp;
    }

    private void handleError(int code) {
        ui.printErrorMsg(code);
    }
}
