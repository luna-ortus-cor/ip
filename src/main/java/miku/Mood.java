package miku;

public class Mood {
    private String date;
    private String moodDescription;

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

