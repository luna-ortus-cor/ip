package miku;

/**
 * Todo class that extends Task class that stores further fine-grained Todo related properties
 */
public class Todo extends Task {
    /**
     * Creates a new Todo instance.
     *
     * @param name description of the Todo
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Creates a new Todo instance specifying the doneness of the Todo
     *
     * @param name description of the Todo
     * @param isDone boolean denoting the doneness of the Todo
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    public Todo(String name, int priority) {
        super(name, priority);
    }

    public Todo(String name, boolean isDone, int priority) {
        super(name, isDone, priority);
    }

    /**
     * Returns a string representation of the Todo for the UI.
     *
     * @return a string representation of the Todo
     */
    @Override
    public String toString() {
        return "[T] " + super.toString() + super.getFormattedTags();
    }

    /**
     * Returns a string representation of the Todo for the save file.
     *
     * @return a string representation of the Todo
     */
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat() + " |" + super.getUnformattedTags() + " |";
    }
}
