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
     * Loads tasks from a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void loadTasks(Storage s) {
        this.taskList = s.readTasks(FILE_PATH);
    }

    /**
     * Saves tasks to a file given a Storage instance.
     *
     * @param s a Storage instance
     */
    public void saveTasks(Storage s) {
        s.writeTasks(this.taskList, FILE_PATH);
    }

    /**
     * Gets task based on index in task list.
     *
     * @param idx index of task in task list
     * @return task object at specified index
     */
    public Task getTask(int idx) {
        try {
            return this.taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
        return null;
    }

    /**
     * Marks the task at a specified index as done.
     *
     * @param idx the index of the task in the list
     */
    public void markDone(int idx) {
        assert idx >= 0 : "Index must be non-negative";
        try {
            int response = taskList.get(idx).markDone();
            ui.printMarkDoneMsg(taskList.get(idx), response);
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Marks the task at a specified index as not done.
     *
     * @param idx the index of the task in the list
     */
    public void markNotDone(int idx) {
        assert idx >= 0 : "Index must be non-negative";
        try {
            int response = taskList.get(idx).markNotDone();
            ui.printMarkNotDoneMsg(taskList.get(idx), response);
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Adds tags to the task at a specified index.
     *
     * @param idx the index of the task in the list
     * @param tags varargs of string of tags to be added
     */
    public void addTags(int idx, String... tags) {
        assert idx >= 0 : "Index must be non-negative";
        try {
            for (String s:tags) {
                taskList.get(idx).addTag(s);
            }
            ui.printEditTagsMsg(taskList.get(idx));
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Removes tags from the task at a specified index.
     *
     * @param idx the index of the task in the list
     * @param tags varargs of string of tags to be removed
     */
    public void removeTags(int idx, String... tags) {
        assert idx >= 0 : "Index must be non-negative";
        try {
            for (String s:tags) {
                taskList.get(idx).removeTag(s);
            }
            ui.printEditTagsMsg(taskList.get(idx));
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Removes all tags from the task at a specified index.
     *
     * @param idx the index of the task in the list
     */
    public void removeAllTags(int idx) {
        try {
            taskList.get(idx).removeAllTags();
            ui.printEditTagsMsg(taskList.get(idx));
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Deletes the task at a specified index.
     *
     * @param idx the index of the task in the list
     */
    public void delete(int idx) {
        assert idx >= 0 : "Index must be non-negative";
        try {
            Task t = taskList.get(idx);
            taskList.remove(idx);
            ui.printDeleteMsg(t, taskList.size());
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Deletes all tasks in the list.
     */
    public void deleteAll() {
        taskList.clear();
        ui.printDeleteAllMsg();
    }

    /**
     * Sets the priority of the task at a specified index
     *
     * @param idx the index of the task in the list
     * @param priority the priority to be set to
     */
    public void setPriority(int idx, int priority) {
        assert idx >= 0 : "Index must be non-negative";
        try {
            int response = taskList.get(idx).setPriority(priority);
            ui.printSetPriorityMsg(taskList.get(idx), response);
        } catch (IndexOutOfBoundsException e) {
            handleError(5);
        }
    }

    /**
     * Adds a new Todo Task to the list.
     *
     * @param name description of the Todo
     * @param tags varargs of string tags
     */
    public void addTodo(String name, String... tags) {
        Todo t = new Todo(name);
        for (String s:tags) {
            t.addTag(s);
        }
        taskList.add(t);
        ui.printAddMsg(t, taskList.size());
    }

    /**
     * Adds a new Todo Task to the list.
     *
     * @param name desciption of the Todo
     * @param priority priority of the Todo
     * @param tags varargs of string tags
     */
    public void addTodo(String name, int priority, String... tags) {
        Todo t = new Todo(name, priority);
        for (String s:tags) {
            t.addTag(s);
        }
        taskList.add(t);
        ui.printAddMsg(t, taskList.size());
    }

    /**
     * Adds a new Deadline Task to the list.
     *
     * @param name description of the Deadline
     * @param by a String representing either a colloquial time or valid date time formatted time
     * @param tags varargs of string tags
     */
    public void addDeadline(String name, String by, String... tags) {
        Deadline d = new Deadline(name, by);
        for (String s:tags) {
            d.addTag(s);
        }
        taskList.add(d);
        ui.printAddMsg(d, taskList.size());
    }

    /**
     * Adds a new Deadline Task to the list.
     *
     * @param name description of the Deadline
     * @param priority priority of the Deadline
     * @param by a String representing either a colloquial time or valid date time formatted time
     * @param tags varargs of string tags
     */
    public void addDeadline(String name, int priority, String by, String... tags) {
        Deadline d = new Deadline(name, priority, by);
        for (String s:tags) {
            d.addTag(s);
        }
        taskList.add(d);
        ui.printAddMsg(d, taskList.size());
    }

    /**
     * Adds a new Event Task to the list.
     *
     * @param name description of the Event
     * @param from a String representing either a colloquial time or valid date time formmated time
     * @param to a String representing either a colloquial time or valid date time formatted time
     * @param tags varargs of string tags
     */
    public void addEvent(String name, String from, String to, String ... tags) {
        Event e = new Event(name, from, to);
        for (String s:tags) {
            e.addTag(s);
        }
        taskList.add(e);
        ui.printAddMsg(e, taskList.size());
    }

    /**
     * Adds a new Event Task to the list.
     *
     * @param name description of the Event
     * @param priority priority of the Event
     * @param from a String representing either a colloquial time or valid date time formmated time
     * @param to a String representing either a colloquial time or valid date time formatted time
     * @param tags varargs of string tags
     */
    public void addEvent(String name, int priority, String from, String to, String... tags) {
        Event e = new Event(name, priority, from, to);
        for (String s:tags) {
            e.addTag(s);
        }
        taskList.add(e);
        ui.printAddMsg(e, taskList.size());
    }

    /**
     * Searches for tasks where a particular string appears as a substring in task description.
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

    /**
     * Handles error messages.
     *
     * @param code int denoting the error generated
     */
    private void handleError(int code) {
        ui.printErrorMsg(code);
    }
}
