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

    /**
     * Returns a string representation of the Todo for the UI.
     *
     * @return a string representation of the Todo
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns a string representation of the Todo for the save file.
     *
     * @return a string representation of the Todo
     */
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
