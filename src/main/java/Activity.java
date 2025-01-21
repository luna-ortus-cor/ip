import java.util.*;
import java.text.*;

public class Activity{
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    private String name;

    public Activity(String startDate,String startTime,String endDate,String endTime,String name){
        this.name=name;
        this.startDate=startDate;
        this.endDate=endDate;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    @Override
    public String toString() {
        return this.startDate + " " + this.startTime + " - " + this.endDate + " " + this.endTime + " | " + this.name;
    }

    public String getActivityName(){
        return this.name;
    }

    public long calculateMinutesSpent() {
       try {
            // Combine date and time into a single format for parsing
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date start = format.parse(this.startDate+" "+this.startTime);
            Date end = format.parse(this.endDate+" "+this.endTime);

            // Calculate the time difference in milliseconds
           long diff = end.getTime() - start.getTime();
    
            // Convert milliseconds to minutes
            return diff / (1000 * 60);
        } catch (ParseException e) {
            System.out.println("Error calculating time difference.");
            return 0;
        }
    }
}
