package miku;

import java.util.HashSet;
import java.util.Set;

/**
 * Task class that stores relevant task related properties
 */
public class Task implements Comparable<Task> {
    private String name;
    private boolean isDone;
    private int priority; //priority values from 1 to 5, 1 least impt, 5 most impt
    private Set<String> tags;

    /**
     * Creates a new Task instance.
     *
     * @param name description of Task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.priority = 3;
        this.tags = new HashSet<>();
    }

    /**
     * Creates a new Task instance specifying the doneness of the Task.
     *
     * @param name description of Task
     * @param isDone boolean denoting if task is done
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.priority = 3;
        this.tags = new HashSet<>();
    }

    /**
     * Creates a new Task instance specifying the priority of the Task.
     *
     * @param name description of Task
     * @param priority int specifying priority of task
     */
    public Task(String name, int priority) {
        this.name = name;
        this.isDone = false;
        this.priority = priority;
        this.tags = new HashSet<>();
    }

    /**
     * Creates a new Task instance specifying the doneness and priority of the Task.
     */
    public Task(String name, boolean isDone, int priority) {
        this.name = name;
        this.isDone = isDone;
        this.priority = priority;
        this.tags = new HashSet<>();
    }

    /**
     * Add a tag to the task.
     * 
     * @param tag tag to be added
     */
    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    /**
     * Remove a tag from the task.
     * 
     * @param tag tag to be removed
     */
    public void removeTag(String tag) {
        //assert tag exists
        tags.remove(tag.toLowerCase());
    }

    /**
     * Returns the tags associated with the task
     * 
     * @return a set of the tags associated with the task
     */
    public Set<String> getTags() {
        return this.tags;
    }

    /**
     * Returns the formatted tags for display.
     * 
     * @return a string of the formatted tags
     */
    public String getFormattedTags() {
        return this.tags.isEmpty() ? "" : " #" + String.join(" #", this.tags);
    }

    /**
     * Returns the unformatted tags for save file.
     * 
     * @return a string of the unformatted tags
     */
    public String getUnformattedTags() {
        return this.tags.isEmpty() ? "" : String.join(" ", this.tags);
    }

    /**
     * Marks the task as done if not done.
     *
     * @return an int denoting a successful change in doneness
     */
    public int markDone() {
        if (!this.isDone) {
            this.isDone = !this.isDone;
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Marks the task as not done if done.
     *
     * @return an int denoting a successful change in doneness
     */
    public int markNotDone() {
        if (this.isDone) {
            this.isDone = !this.isDone;
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Returns the descrption of the task.
     *
     * @return the description of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the priority of the task to a specified value
     *
     * @param priority int specifying the new priority of the task
     * @return an int denoting a successful change in priority
     */
    public int setPriority(int priority) {
        if (this.priority != priority) {
            this.priority = priority;
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Returns a string representation of the task for the UI.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + "[" + String.valueOf(this.priority) + "] " + this.name;
    }

    /**
     * Returns a string representation of the task for the save file.
     *
     * @return a string representation of the task
     */
    public String toSaveFormat() {
        return (this.isDone ? "1" : "0") + " | " + String.valueOf(this.priority) + " | " + this.name;
    }

    @Override
    public int compareTo(Task t) {
        return Integer.compare(this.priority, t.priority);
    }
}
