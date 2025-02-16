package miku;

/**
 * Mood class storing a date and a mood
 */
public class Mood {
    private String date;
    private String moodDescription;

    /**
     * Initializes a new mood instance.
     *
     * @param date string of date
     * @param moodDescription string of mood
     */
    public Mood(String date, String moodDescription) {
        this.date = date;
        this.moodDescription = moodDescription;
    }

    @Override
    public String toString() {
        return date + " | " + moodDescription;
    }

    public String getMoodDescription() {
        return moodDescription;
    }
}

