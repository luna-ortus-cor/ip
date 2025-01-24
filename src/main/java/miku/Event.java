package miku;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    String from;
    String to;
    LocalDateTime fromLDT;
    LocalDateTime toLDT;

    public Event(String name, String from, String to){
        super(name);
        try{
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.fromLDT=LocalDateTime.parse(from, inputFormat);
            this.toLDT=LocalDateTime.parse(to, inputFormat);
            this.from=null;
            this.to=null;
        }catch(DateTimeParseException e){
            this.from=from;
            this.to=to;
            this.fromLDT=null;
            this.toLDT=null;
        }
    }
    
    public Event(String name, boolean done, String from, String to){
        super(name,done);
        try{
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.fromLDT=LocalDateTime.parse(from, inputFormat);
            this.toLDT=LocalDateTime.parse(to, inputFormat);
            this.from=null;
            this.to=null;
        }catch(DateTimeParseException e){
            this.from=from;
            this.to=to;
            this.fromLDT=null;
            this.toLDT=null;
        }
    }

    public String getFormattedDateTime(String s, LocalDateTime ldt){
        if(s==null){
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");
            return ldt.format(outputFormat);
        }else{
            return s;
        }
    }

    public String getUnformattedDateTime(String s, LocalDateTime ldt){
        if(s==null){
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyy-MM-dd HHmm");
            return ldt.format(outputFormat);
        }else{
            return s;
        }
    }

    @Override
    public String toString(){
        return "[E] "+super.toString()+" (from: "+getFormattedDateTime(this.from,this.fromLDT)+" to: "+getFormattedDateTime(this.to,this.toLDT)+")";
    }

    public String toSaveFormat(){
        return "E | "+super.toSaveFormat()+" | "+getUnformattedDateTime(this.from,this.fromLDT)+" | "+getUnformattedDateTime(this.to,this.toLDT);
    }
}
