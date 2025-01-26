package miku;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class that extends Task class that stores further fine-grained Event related properties
 */
public class Event extends Task {
    private String from;
    private String to;
    private LocalDateTime fromLdt;
    private LocalDateTime toLdt;

    /**
     * Create a new Event instance.
     *
     * @param name description of the Event
     * @param from a String representing either a colloquial time or valid date time formatted time
     * @param to a String representing either a colloquial time or valid date timf formatted time
     */
    public Event(String name, String from, String to) {
        super(name);
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.fromLdt = LocalDateTime.parse(from, inputFormat);
            this.toLdt = LocalDateTime.parse(to, inputFormat);
            this.from = null;
            this.to = null;
        } catch (DateTimeParseException e) {
            this.from = from;
            this.to = to;
            this.fromLdt = null;
            this.toLdt = null;
        }
    }

    /**
     * Create a new Event instance specifying the doneness of the Event.
     *
     * @param name description of the Event
     * @param isDone boolean denoting doneness of the Event
     * @param from a String representing either a colloquial time or valid date time formatted time
     */
    public Event(String name, boolean done, String from, String to) {
        super(name, done);
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.fromLdt = LocalDateTime.parse(from, inputFormat);
            this.toLdt = LocalDateTime.parse(to, inputFormat);
            this.from = null;
            this.to = null;
        } catch (DateTimeParseException e) {
            this.from = from;
            this.to = to;
            this.fromLdt = null;
            this.toLdt = null;
        }
    }

    private String getFormattedDateTime(String s, LocalDateTime ldt) {
        if (s == null) {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");
            return ldt.format(outputFormat);
        } else {
            return s;
        }
    }

    private String getUnformattedDateTime(String s, LocalDateTime ldt) {
        if (s == null) {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyy-MM-dd HHmm");
            return ldt.format(outputFormat);
        } else {
            return s;
        }
    }

    /**
     * Returns a string representation of the Event for the UI.
     *
     * @return a string representation of the Event
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getFormattedDateTime(this.from, this.fromLdt)
            + " to: " + getFormattedDateTime(this.to, this.toLdt) + ")";
    }

    /**
     * Returns a string representation of the Event for the save file.
     *
     * @return a string representation of the Event
     */
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + getUnformattedDateTime(this.from, this.fromLdt)
            + " | " + getUnformattedDateTime(this.to, this.toLdt);
    }
}
