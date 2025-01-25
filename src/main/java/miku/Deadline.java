package miku;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    String by;
    LocalDateTime byLDT;
    
    /**
     * Create a new Deadline instance
     *
     * @param name description of the Deadline
     * @param by a String representing either a colloquial time or valid date time formatted time
     */
    public Deadline(String name, String by){
        super(name);
        try{
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.byLDT=LocalDateTime.parse(by, inputFormat);
            this.by=null;
        }catch(DateTimeParseException e){
            this.by=by;
            this.byLDT=null;
        }
    }

    /**
     * Create a new Deadline instance specifying the doneness of the Deadline.
     *
     * @param name description of the Deadline
     * @param isDone boolean denoting doneness of the Deadline
     * @param by a String representing either a colloquial time or valid date time formatted time
     */
    public Deadline(String name, boolean isDone, String by){
        super(name,isDone);
        try{
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.byLDT=LocalDateTime.parse(by, inputFormat);
            this.by=null;
        }catch(DateTimeParseException e){
            this.by=by;
            this.byLDT=null;
        }
    }
    
    private String getFormattedDateTime(String s, LocalDateTime ldt){
        if(s==null){
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");
            return ldt.format(outputFormat);
        }else{
            return s;
        }
    }

    private String getUnformattedDateTime(String s, LocalDateTime ldt){
        if(s==null){
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyy-MM-dd HHmm");
            return ldt.format(outputFormat);
        }else{
            return s;
        }
    }
    
    /**
     * Returns a string representation of the Deadline for the UI.
     *
     * @return a string representation of the Deadline
     */
    @Override
    public String toString(){
        return "[D] "+super.toString()+" (by: "+getFormattedDateTime(this.by,this.byLDT)+")";
    }

    /**
     * Returns a string representation of the Deadline for the save file.
     *
     * @return a string representation of the Deadline
     */
    public String toSaveFormat(){
        return "D | "+super.toSaveFormat()+" | "+getUnformattedDateTime(this.by,this.byLDT);
    }
}
